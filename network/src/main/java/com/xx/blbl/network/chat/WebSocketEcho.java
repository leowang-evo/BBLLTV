package com.xx.blbl.network.chat;

import com.google.gson.Gson;
import com.xx.blbl.model.dm.DmModel;
import com.xx.blbl.model.live.ChatAuthModel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import okio.ByteString;

import org.brotli.dec.BrotliInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

/** Published OkHttp transport with BBLL's original frame parsing and authentication contract. */
public class WebSocketEcho extends WebSocketListener implements AutoCloseable {
    public interface DmListener {
        /** Called on the transport callback thread, as in the original client. */
        void onDm(DmModel danmaku);
    }

    public interface ScheduledTask {
        void cancel();
    }

    public interface Scheduler {
        ScheduledTask schedule(Runnable task, long delayMillis);
    }

    /**
     * Hosts can inject an Android main-thread scheduler without coupling this module to Android.
     */
    private static final class ExecutorScheduler implements Scheduler, AutoCloseable {
        private final ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor(
                        task -> {
                            Thread thread = new Thread(task, "BBLL-live-heartbeat");
                            thread.setDaemon(true);
                            return thread;
                        });

        @Override
        public ScheduledTask schedule(Runnable task, long delayMillis) {
            ScheduledFuture<?> future = executor.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
            return () -> future.cancel(false);
        }

        @Override
        public void close() {
            executor.shutdownNow();
        }
    }

    private final OkHttpClient client;
    private final Scheduler scheduler;
    private final boolean ownsResources;
    private final Runnable heartBeatRunnable = this::sendHeartbeat;
    private ScheduledTask heartBeatTask;
    private volatile DmListener onDmListener;
    private volatile WebSocket wSocket;
    private volatile boolean released;
    private String token = "";
    private Long roomId = 0L;
    private Long userId = 0L;

    public WebSocketEcho() {
        this(
                new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build(),
                new ExecutorScheduler(),
                true);
    }

    /** Injected client and scheduler remain owned by the caller. */
    public WebSocketEcho(OkHttpClient client, Scheduler scheduler) {
        this(client, scheduler, false);
    }

    private WebSocketEcho(OkHttpClient client, Scheduler scheduler, boolean ownsResources) {
        this.client = Objects.requireNonNull(client, "client");
        this.scheduler = Objects.requireNonNull(scheduler, "scheduler");
        this.ownsResources = ownsResources;
    }

    public static WebSocketEcho build(String url, String token, Long roomId, Long userId) {
        WebSocketEcho echo = new WebSocketEcho();
        echo.run(url, token, roomId, userId);
        return echo;
    }

    public synchronized void run(String url, String token, Long roomId, Long userId) {
        if (released)
            throw new IllegalStateException("This live chat connection has been released");
        if (wSocket != null)
            throw new IllegalStateException("This live chat connection has already started");
        this.token = token;
        this.roomId = roomId;
        this.userId = userId;
        Request request =
                new Request.Builder()
                        .url(url)
                        .header(
                                "User-Agent",
                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
                                        + " (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                        .header("Origin", "https://live.bilibili.com")
                        .build();
        wSocket = client.newWebSocket(request, this);
    }

    private ByteString buildData(String text, int operation) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length + 16;
        byte[] header = buildHeader(length, (short) 16, (short) 1, operation, 1);
        byte[] frame = new byte[length];
        System.arraycopy(header, 0, frame, 0, header.length);
        System.arraycopy(bytes, 0, frame, header.length, bytes.length);
        return ByteString.of(frame);
    }

    private byte[] buildHeader(
            int length, short headerLength, short version, int operation, int sequence) {
        return new byte[] {
            (byte) (length >> 24),
            (byte) (length >> 16),
            (byte) (length >> 8),
            (byte) length,
            (byte) (headerLength >> 8),
            (byte) headerLength,
            (byte) (version >> 8),
            (byte) version,
            (byte) (operation >> 24),
            (byte) (operation >> 16),
            (byte) (operation >> 8),
            (byte) operation,
            (byte) (sequence >> 24),
            (byte) (sequence >> 16),
            (byte) (sequence >> 8),
            (byte) sequence
        };
    }

    private synchronized void scheduleHeartbeat(long delayMillis) {
        if (released) return;
        if (heartBeatTask != null) heartBeatTask.cancel();
        heartBeatTask = scheduler.schedule(heartBeatRunnable, delayMillis);
    }

    private void sendHeartbeat() {
        WebSocket socket = wSocket;
        if (!released && socket != null) {
            socket.send(buildData("[object Object]", 2));
            scheduleHeartbeat(30000L);
        }
    }

    /** Compatibility entry point for an already recovered app callback. */
    public void lambda$run$0() {
        sendHeartbeat();
    }

    public static void a(WebSocketEcho echo) {
        echo.sendHeartbeat();
    }

    private String parseData(ByteString data) {
        byte[] frame = data.toByteArray();
        int version = parseInt(frame, 6, 2);
        if (frame.length <= 16) return "";
        byte[] payload = new byte[frame.length - 16];
        System.arraycopy(frame, 16, payload, 0, payload.length);
        if (version != 2 && version != 3) return new String(payload);
        try (InputStream compressed =
                        version == 3
                                ? new BrotliInputStream(new ByteArrayInputStream(payload))
                                : new GZIPInputStream(new ByteArrayInputStream(payload));
                BufferedReader reader = new BufferedReader(new InputStreamReader(compressed))) {
            StringBuilder decoded = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) decoded.append(line);
            String result = decoded.toString();
            return result.substring(result.indexOf('{'));
        } catch (IOException failure) {
            onProtocolError(failure);
            return "";
        }
    }

    /** Preserve the original signed-byte arithmetic, including malformed-input exceptions. */
    private int parseInt(byte[] bytes, int offset, int length) {
        int result = 0;
        for (int index = length - 1; index >= 0; index--) {
            result =
                    (int)
                            ((Math.pow(256.0d, (length - index) - 1)
                                            * ((double) bytes[offset + index]))
                                    + result);
        }
        return result;
    }

    @Override
    public void onOpen(WebSocket socket, Response response) {
        if (released) {
            socket.close(1000, "Goodbye");
            return;
        }
        ChatAuthModel auth = new ChatAuthModel();
        auth.setRoomid(roomId.longValue());
        auth.setKey(token);
        auth.setUid(userId.longValue());
        socket.send(buildData(new Gson().toJson(auth), 7));
    }

    @Override
    public void onMessage(WebSocket socket, String text) {}

    @Override
    public void onMessage(WebSocket socket, ByteString bytes) {
        if (released) return;
        try {
            String data = parseData(bytes);
            if (data.contains("{\"code\":0}")) {
                scheduleHeartbeat(100L);
                return;
            }
            if (data.contains("DANMU_MSG")) {
                int contentStart = data.indexOf("content\\\":\\\"");
                int contentEnd = contentStart + 14;
                for (int index = contentEnd; index < data.length(); index++) {
                    if (data.charAt(index) == '\"') {
                        contentEnd = index;
                        break;
                    }
                }
                String content = data.substring(contentStart + 12, contentEnd - 1);
                int colorStart = data.indexOf("color\\\":");
                int colorEnd = colorStart + 7;
                for (int index = colorEnd; index < data.length(); index++) {
                    if (data.charAt(index) == '\"') {
                        colorEnd = index;
                        break;
                    }
                }
                int color = Integer.parseInt(data.substring(colorStart + 8, colorEnd - 2));
                DmListener listener = onDmListener;
                if (listener != null) {
                    DmModel model = new DmModel();
                    model.setContent(content);
                    model.setColor(color);
                    listener.onDm(model);
                }
            }
        } catch (Exception failure) {
            onProtocolError(failure);
        }
    }

    @Override
    public void onClosing(WebSocket socket, int code, String reason) {
        socket.close(1000, null);
    }

    @Override
    public void onFailure(WebSocket socket, Throwable failure, Response response) {
        cancelHeartbeat();
        onProtocolError(failure);
    }

    @Override
    public void onClosed(WebSocket socket, int code, String reason) {
        cancelHeartbeat();
    }

    protected void onProtocolError(Throwable failure) {
        failure.printStackTrace();
    }

    private synchronized void cancelHeartbeat() {
        if (heartBeatTask != null) {
            heartBeatTask.cancel();
            heartBeatTask = null;
        }
    }

    public void setOnDmListener(DmListener listener) {
        onDmListener = listener;
    }

    public synchronized void release() {
        if (released) return;
        released = true;
        cancelHeartbeat();
        onDmListener = null;
        WebSocket socket = wSocket;
        wSocket = null;
        if (socket != null) socket.close(1000, "Goodbye");
        if (ownsResources) {
            ((ExecutorScheduler) scheduler).close();
            client.dispatcher().executorService().shutdown();
        }
    }

    @Override
    public void close() {
        release();
    }
}
