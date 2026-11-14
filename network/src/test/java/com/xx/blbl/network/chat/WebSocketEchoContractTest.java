package com.xx.blbl.network.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xx.blbl.model.dm.DmModel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import okio.ByteString;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

/** Offline frame and lifecycle fixtures, using the published OkHttp/Okio/Brotli APIs. */
public final class WebSocketEchoContractTest {
    private static int assertions, frameCases;

    private static void same(Object expected, Object actual) {
        assertions++;
        if (!Objects.equals(expected, actual))
            throw new AssertionError("expected=" + expected + "; actual=" + actual);
    }

    private static final class Pending implements WebSocketEcho.ScheduledTask {
        final Runnable runnable;
        final long delay;
        boolean cancelled;

        Pending(Runnable runnable, long delay) {
            this.runnable = runnable;
            this.delay = delay;
        }

        public void cancel() {
            cancelled = true;
        }

        void fire() {
            if (!cancelled) runnable.run();
        }
    }

    private static final class FakeScheduler implements WebSocketEcho.Scheduler {
        final List<Pending> tasks = new ArrayList<>();

        public WebSocketEcho.ScheduledTask schedule(Runnable runnable, long delay) {
            Pending pending = new Pending(runnable, delay);
            tasks.add(pending);
            return pending;
        }
    }

    private static final class Socket implements WebSocket {
        final List<ByteString> sent = new ArrayList<>();
        int closeCalls, closeCode;
        String closeReason;

        public Request request() {
            return new Request.Builder().url("https://fixture.invalid/live").build();
        }

        public long queueSize() {
            return 0;
        }

        public boolean send(String text) {
            throw new AssertionError("Unexpected text frame");
        }

        public boolean send(ByteString bytes) {
            sent.add(bytes);
            return true;
        }

        public boolean close(int code, String reason) {
            closeCalls++;
            closeCode = code;
            closeReason = reason;
            return true;
        }

        public void cancel() {}
    }

    private static final class Echo extends WebSocketEcho {
        final List<Throwable> errors = new ArrayList<>();

        Echo(FakeScheduler scheduler) {
            super(new OkHttpClient(), scheduler);
        }

        @Override
        protected void onProtocolError(Throwable failure) {
            errors.add(failure);
        }
    }

    private static Method method(String name, Class<?>... types) throws Exception {
        Method method = WebSocketEcho.class.getDeclaredMethod(name, types);
        method.setAccessible(true);
        return method;
    }

    private static Object call(Method method, WebSocketEcho echo, Object... args) throws Throwable {
        try {
            return method.invoke(echo, args);
        } catch (InvocationTargetException error) {
            throw error.getCause();
        }
    }

    private static void set(Echo echo, String name, Object value) throws Exception {
        Field field = WebSocketEcho.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(echo, value);
    }

    private static byte[] frame(byte[] payload, int version) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(out);
        data.writeInt(payload.length + 16);
        data.writeShort(16);
        data.writeShort(version);
        data.writeInt(5);
        data.writeInt(1);
        data.write(payload);
        data.close();
        return out.toByteArray();
    }

    private static byte[] gzip(byte[] payload) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(payload);
        }
        return out.toByteArray();
    }

    private static String parse(Echo echo, byte[] data) throws Throwable {
        return (String) call(method("parseData", ByteString.class), echo, ByteString.of(data));
    }

    private static void frameFixtures() throws Throwable {
        Echo echo = new Echo(new FakeScheduler());
        Method header =
                method("buildHeader", int.class, short.class, short.class, int.class, int.class);
        int[][] headers = {
            {16, 16, 1, 2, 1},
            {31, 16, 2, 5, 1},
            {65535, 16, 3, 7, 0x11223344},
            {Integer.MIN_VALUE, -1, 0x7fff, -2, -1},
            {0, 0, 0, 0, 0}
        };
        for (int[] h : headers) {
            ByteArrayOutputStream expected = new ByteArrayOutputStream();
            DataOutputStream bytes = new DataOutputStream(expected);
            bytes.writeInt(h[0]);
            bytes.writeShort(h[1]);
            bytes.writeShort(h[2]);
            bytes.writeInt(h[3]);
            bytes.writeInt(h[4]);
            bytes.close();
            same(
                    Arrays.toString(expected.toByteArray()),
                    Arrays.toString(
                            (byte[])
                                    call(
                                            header,
                                            echo,
                                            h[0],
                                            (short) h[1],
                                            (short) h[2],
                                            h[3],
                                            h[4])));
            frameCases++;
        }
        Method data = method("buildData", String.class, int.class);
        String[] texts = {"", "[object Object]", "{\"cmd\":\"DANMU_MSG\"}", "恢复验证·弹幕", null};
        for (int i = 0; i < texts.length; i++) {
            if (texts[i] == null) {
                try {
                    call(data, echo, texts[i], 7);
                    throw new AssertionError("null data accepted");
                } catch (NullPointerException expected) {
                    assertions++;
                }
            } else {
                ByteString bytes = (ByteString) call(data, echo, texts[i], i == 1 ? 2 : 7);
                same(texts[i], bytes.substring(16).utf8());
                same(texts[i].getBytes(StandardCharsets.UTF_8).length + 16, bytes.size());
                same(1, (int) bytes.getByte(7));
                same(i == 1 ? 2 : 7, (int) bytes.getByte(11));
            }
            frameCases++;
        }
        Method integer = method("parseInt", byte[].class, int.class, int.class);
        byte[][] ints = {
            {0, 0, 0, 16},
            {0, 0, 0, 16},
            {(byte) 0x80, (byte) 0xff, (byte) 0xff, (byte) 0xfe},
            {1, 2, 3, 4},
            {1, 2},
            {1, 2},
            {1, 2},
            {}
        };
        int[][] spans = {{0, 4}, {2, 2}, {0, 4}, {1, 2}, {0, 0}, {-1, 2}, {1, 3}, {0, 1}};
        int[] expected = {16, 16, Integer.MIN_VALUE, 515, 0};
        for (int i = 0; i < ints.length; i++) {
            if (i < 5) same(expected[i], call(integer, echo, ints[i], spans[i][0], spans[i][1]));
            else
                try {
                    call(integer, echo, ints[i], spans[i][0], spans[i][1]);
                    throw new AssertionError("Truncated int accepted");
                } catch (ArrayIndexOutOfBoundsException accepted) {
                    assertions++;
                }
            frameCases++;
        }
        String json = "{\"cmd\":\"DANMU_MSG\",\"text\":\"恢复验证\"}";
        byte[] plain = json.getBytes(StandardCharsets.UTF_8);
        byte[] packed = gzip(("prefix\n" + json + "\n").getBytes(StandardCharsets.UTF_8));
        List<byte[]> frames = new ArrayList<>();
        for (int n : new int[] {0, 4, 7, 8, 15, 16}) frames.add(new byte[n]);
        frames.add(frame(plain, 0));
        frames.add(frame(plain, 1));
        frames.add(frame(plain, 4));
        frames.add(frame(packed, 2));
        frames.add(
                frame(
                        Base64.getDecoder()
                                .decode(
                                        "CxiAcHJlZml4CnsiY21kIjoiREFOTVVfTVNHIiwidGV4dCI6IuaBouWkjemqjOivgSJ9CgM="),
                        3));
        frames.add(frame(Arrays.copyOf(packed, 5), 2));
        frames.add(frame("invalid compressed data".getBytes(StandardCharsets.UTF_8), 2));
        frames.add(frame(new byte[] {0x7f, 0, 0, 0}, 3));
        frames.add(frame(gzip("no-json".getBytes(StandardCharsets.UTF_8)), 2));
        byte[] first = frame(plain, 1),
                second = frame("second".getBytes(StandardCharsets.UTF_8), 1),
                both = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, both, first.length, second.length);
        frames.add(both);
        for (int i = 0; i < frames.size(); i++) {
            if (i < 3)
                try {
                    parse(echo, frames.get(i));
                    throw new AssertionError("Short frame accepted");
                } catch (ArrayIndexOutOfBoundsException expectedFailure) {
                    assertions++;
                }
            else if (i == 14)
                try {
                    parse(echo, frames.get(i));
                    throw new AssertionError("Non-JSON compressed data accepted");
                } catch (StringIndexOutOfBoundsException expectedFailure) {
                    assertions++;
                }
            else {
                String expectedText =
                        i < 6 || (i >= 11 && i <= 13)
                                ? ""
                                : i == 15
                                        ? new String(Arrays.copyOfRange(both, 16, both.length))
                                        : json;
                same(expectedText, parse(echo, frames.get(i)));
            }
            frameCases++;
        }
        same(34, frameCases);
        echo.release();
    }

    private static void lifecycleAndDm() throws Exception {
        FakeScheduler scheduler = new FakeScheduler();
        Echo echo = new Echo(scheduler);
        Socket socket = new Socket();
        set(echo, "wSocket", socket);
        set(echo, "roomId", 1234L);
        set(echo, "userId", 5678L);
        set(echo, "token", "fixture-token");
        echo.onOpen(socket, null);
        same(1, socket.sent.size());
        same(7, (int) socket.sent.get(0).getByte(11));
        JsonObject auth =
                new Gson().fromJson(socket.sent.get(0).substring(16).utf8(), JsonObject.class);
        same(1234L, auth.get("roomid").getAsLong());
        same(5678L, auth.get("uid").getAsLong());
        same(3, auth.get("protover").getAsInt());
        same("web", auth.get("platform").getAsString());
        same(2, auth.get("type").getAsInt());
        same("fixture-token", auth.get("key").getAsString());
        echo.onMessage(
                socket, ByteString.of(frame("{\"code\":0}".getBytes(StandardCharsets.UTF_8), 1)));
        same(1, scheduler.tasks.size());
        same(100L, scheduler.tasks.get(0).delay);
        scheduler.tasks.get(0).fire();
        same(2, socket.sent.size());
        same(2, (int) socket.sent.get(1).getByte(11));
        same("[object Object]", socket.sent.get(1).substring(16).utf8());
        same(2, scheduler.tasks.size());
        same(30000L, scheduler.tasks.get(1).delay);
        List<DmModel> messages = new ArrayList<>();
        echo.setOnDmListener(messages::add);
        JsonObject envelope = new JsonObject();
        envelope.addProperty("cmd", "DANMU_MSG");
        envelope.addProperty("extra", "{\"content\":\"恢复弹幕\",\"color\":16711680,\"tail\":\"x\"}");
        echo.onMessage(
                socket,
                ByteString.of(frame(envelope.toString().getBytes(StandardCharsets.UTF_8), 1)));
        same(1, messages.size());
        same("恢复弹幕", messages.get(0).getContent());
        same(16711680, messages.get(0).getColor());
        echo.onMessage(socket, ByteString.of(new byte[2]));
        same(1, messages.size());
        same(1, echo.errors.size());
        echo.onMessage(socket, "ignored text");
        same(1, messages.size());
        echo.onClosing(socket, 1001, "fixture");
        same(1000, socket.closeCode);
        same(null, socket.closeReason);
        echo.release();
        same(true, scheduler.tasks.get(1).cancelled);
        same("Goodbye", socket.closeReason);
        int count = socket.sent.size(), closes = socket.closeCalls;
        scheduler.tasks.get(1).fire();
        echo.lambda$run$0();
        echo.release();
        same(count, socket.sent.size());
        same(closes, socket.closeCalls);
        echo.onMessage(
                socket,
                ByteString.of(frame(envelope.toString().getBytes(StandardCharsets.UTF_8), 1)));
        same(1, messages.size());
        Echo idle = new Echo(new FakeScheduler());
        idle.release();
        idle.release();
        FakeScheduler failedScheduler = new FakeScheduler();
        Echo failed = new Echo(failedScheduler);
        Socket failedSocket = new Socket();
        set(failed, "wSocket", failedSocket);
        failed.onMessage(
                failedSocket,
                ByteString.of(frame("{\"code\":0}".getBytes(StandardCharsets.UTF_8), 1)));
        failed.onFailure(failedSocket, new java.io.IOException("fixture"), null);
        same(true, failedScheduler.tasks.get(0).cancelled);
        same(1, failed.errors.size());
        failed.release();
    }

    public static void main(String[] args) throws Throwable {
        frameFixtures();
        lifecycleAndDm();
        System.out.println(
                "WEBSOCKET_CONTRACT_OK legacy_frame_cases="
                        + frameCases
                        + " assertions="
                        + assertions
                        + " network_requests=0 apk_inputs=0 header_jars=0");
    }
}
