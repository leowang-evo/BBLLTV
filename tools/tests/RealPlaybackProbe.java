package com.damon.bblab.playbacktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.PopupWindow;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/** Exercise actual Bilibili media through the application's own APIs and player. */
public final class RealPlaybackProbe extends Instrumentation {
    private Activity host;
    private ClassLoader loader;
    private Object fragment, network;
    private Class<?> fragmentType;
    private View playerView, controls;
    private int checks;
    private String stage = "startup";

    interface Task {
        void run() throws Exception;
    }

    private static Class<?> wrap(Class<?> c) {
        if (c == int.class) return Integer.class;
        if (c == long.class) return Long.class;
        if (c == boolean.class) return Boolean.class;
        if (c == float.class) return Float.class;
        return c;
    }

    private Object call(Object target, String name, Object... args) throws Exception {
        Class<?> type = target instanceof Class ? (Class<?>) target : target.getClass();
        outer:
        for (Method method : type.getMethods()) {
            if (!method.getName().equals(name) || method.getParameterTypes().length != args.length)
                continue;
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < args.length; i++)
                if (args[i] != null && !wrap(params[i]).isInstance(args[i])) continue outer;
            method.setAccessible(true);
            try {
                return method.invoke(target instanceof Class ? null : target, args);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(e.getCause());
            }
        }
        throw new NoSuchMethodException(type.getName() + "." + name);
    }

    private void main(Task task) throws Exception {
        AtomicReference<Throwable> error = new AtomicReference<>();
        runOnMainSync(
                () -> {
                    try {
                        task.run();
                    } catch (Throwable e) {
                        error.set(e);
                    }
                });
        if (error.get() != null) throw new IllegalStateException(error.get());
    }

    private Object field(Object target, String name) throws Exception {
        return target.getClass().getField(name).get(target);
    }

    private void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(stage + ": " + label);
        checks++;
        android.util.Log.i("BBLL_REAL_TEST", "PASS " + stage + " / " + label);
    }

    private Object api(String name, Object... args) throws Exception {
        Class<?> callback = loader.loadClass("com.xx.blbl.network.NetResultCallback");
        CountDownLatch ready = new CountDownLatch(1);
        AtomicReference<Object> result = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        Object proxy =
                Proxy.newProxyInstance(
                        loader,
                        new Class<?>[] {callback},
                        (p, m, values) -> {
                            if (m.getName().equals("onResponse")) {
                                result.set(values[0]);
                                ready.countDown();
                            }
                            if (m.getName().equals("onFailure")) {
                                error.set((Throwable) values[0]);
                                ready.countDown();
                            }
                            return null;
                        });
        Object[] parameters = Arrays.copyOf(args, args.length + 1);
        parameters[args.length] = proxy;
        call(network, name, parameters);
        if (!ready.await(20, TimeUnit.SECONDS)) throw new AssertionError(name + " timeout");
        if (error.get() != null)
            throw new IllegalStateException(
                    name + " failed: " + error.get().getClass().getSimpleName());
        Object response = result.get();
        check(response != null, name + " received response");
        check(
                ((Integer) call(response, "getCode")) == 0,
                name + " business code=" + call(response, "getCode"));
        return call(response, "getData");
    }

    private void open(Object model) throws Exception {
        fragment = fragmentType.getConstructor().newInstance();
        Bundle arguments = new Bundle();
        arguments.putSerializable("model", (Serializable) model);
        call(fragment, "setArguments", arguments);
        main(
                () -> {
                    Object manager = call(host, "fragments");
                    call(manager, "executePendingTransactions");
                    call(host, "showScreen", fragment, "realPlaybackProbe");
                    call(manager, "executePendingTransactions");
                });
        SystemClock.sleep(500);
    }

    private long[] snapshot() throws Exception {
        long[] state = new long[7];
        main(
                () -> {
                    Object engine = field(fragment, "player");
                    if (engine == null) return;
                    state[0] = (Long) call(engine, "getCurrentPosition");
                    state[1] = (Integer) call(engine, "getPlaybackState");
                    Object size = call(engine, "getVideoSize");
                    state[2] = (Integer) field(size, "width");
                    state[3] = (Integer) field(size, "height");
                    state[4] = (Boolean) call(engine, "getPlayWhenReady") ? 1 : 0;
                    state[5] = (Long) call(engine, "getDuration");
                    state[6] = (Boolean) call(engine, "isPlaying") ? 1 : 0;
                });
        return state;
    }

    private long[] playing(long minimum) throws Exception {
        long[] state = null;
        for (int i = 0; i < 100; i++) {
            state = snapshot();
            if (state[0] > minimum && state[2] > 0 && state[3] > 0 && state[6] == 1) return state;
            SystemClock.sleep(300);
        }
        throw new AssertionError(stage + " did not decode/advance: " + Arrays.toString(state));
    }

    private int id(String name) {
        return host.getResources().getIdentifier(name, "id", host.getPackageName());
    }

    private void key(int code) {
        sendKeyDownUpSync(code);
        SystemClock.sleep(180);
    }

    private void bindViews() throws Exception {
        playerView = (View) field(fragment, "playerView");
        controls = (View) call(playerView, "getAppController");
    }

    private void focusIs(String name) throws Exception {
        boolean[] value = {false};
        main(
                () -> {
                    View focused = host.getCurrentFocus();
                    value[0] = focused != null && focused.getId() == id(name);
                });
        check(value[0], "focus " + name);
    }

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        start();
    }

    @Override
    public void onStart() {
        Bundle result = new Bundle();
        int status = Activity.RESULT_CANCELED;
        try {
            loader = getTargetContext().getClassLoader();
            Intent intent =
                    new Intent()
                            .setClassName(getTargetContext(), "com.xx.blbl.ui.MainActivity")
                            .addFlags(
                                    Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            host = startActivitySync(intent);
            setInTouchMode(false);
            SystemClock.sleep(1500);
            network = call(loader.loadClass("com.xx.blbl.AppController"), "network");
            fragmentType = loader.loadClass("com.xx.blbl.ui.playback.PlaybackFragment");
            stage = "VOD";
            Object videos = api("getHotVideos", 1, 5);
            List<?> list = (List<?>) call(videos, "getList");
            check(!list.isEmpty(), "popular list has a real video");
            open(list.get(0));
            long[] decoded = playing(1200);
            check(decoded[2] > 0 && decoded[3] > 0, "decoded " + decoded[2] + "x" + decoded[3]);
            bindViews();
            main(
                    () -> {
                        call(controls, "hideImmediately");
                        playerView.requestFocus();
                    });
            SystemClock.sleep(400);
            key(20);
            SystemClock.sleep(400);
            focusIs("button_play");
            key(23);
            check(snapshot()[4] == 0, "remote Center pauses actual media");
            long paused = snapshot()[0];
            SystemClock.sleep(1000);
            check(Math.abs(snapshot()[0] - paused) < 150, "paused position remains stable");
            key(19);
            focusIs("exo_progress");
            key(22);
            key(23);
            SystemClock.sleep(400);
            long sought = snapshot()[0];
            check(Math.abs(sought - paused) > 3000, "remote time bar seeks actual media");
            key(20);
            focusIs("button_play");
            key(23);
            check(playing(sought + 1000)[6] == 1, "remote Center resumes after seek");
            key(82);
            SystemClock.sleep(450);
            focusIs("click_view");
            key(20);
            key(23);
            SystemClock.sleep(200);
            focusIs("click_view");
            sendKeyDownUpSync(4);
            sendKeyDownUpSync(4);
            SystemClock.sleep(400);
            Object settings = playerView.findViewById(id("setting_view"));
            check(!(Boolean) call(settings, "isShowing"), "fast Back closes settings");
            check((Boolean) call(fragment, "isAdded"), "fast Back preserves video screen");
            // Select a real, entitled quality; wait for a new API result rather than accepting the
            // old stream.
            List<?> qualities = (List<?>) call(settings, "getVideoQualities");
            check(qualities != null && !qualities.isEmpty(), "actual quality choices available");
            Object coordinator = field(fragment, "coordinator"),
                    oldInfo = field(coordinator, "playInfo");
            Object appState = field(fragment, "appState");
            boolean premium = (Boolean) field(appState, "premiumMember");
            int selected = premium ? 0 : qualities.size() - 1;
            main(
                    () -> {
                        call(playerView, "showHideSettingView", true);
                    });
            SystemClock.sleep(400);
            main(() -> call(settings, "showVideoQualityMenu"));
            SystemClock.sleep(200);
            for (int i = 0; i < selected; i++) key(20);
            key(23);
            for (int i = 0; i < 100 && field(coordinator, "playInfo") == oldInfo; i++)
                SystemClock.sleep(300);
            check(
                    field(coordinator, "playInfo") != oldInfo,
                    "quality selection returned fresh play info");
            decoded = playing(1200);
            check(
                    decoded[6] == 1,
                    "selected quality decodes "
                            + decoded[2]
                            + "x"
                            + decoded[3]
                            + " API quality="
                            + call(field(coordinator, "playInfo"), "getQuality")
                            + " premium="
                            + premium);
            List<?> audios = (List<?>) call(settings, "getAudioQualities");
            check(audios != null && !audios.isEmpty(), "actual audio choices available");
            main(() -> call(playerView, "showHideSettingView", true));
            SystemClock.sleep(400);
            main(() -> call(settings, "showAudioQuality"));
            SystemClock.sleep(200);
            for (int i = 1; i < audios.size(); i++) key(20);
            key(23);
            SystemClock.sleep(500);
            check(
                    call(call(settings, "getCurrentAudioQuality"), "getCode")
                            .equals(call(audios.get(audios.size() - 1), "getCode")),
                    "audio selection updates selected quality");
            decoded = playing(1200);
            check(decoded[6] == 1, "actual media continues with selected audio");
            List<?> codecs = (List<?>) call(settings, "getVideoCodecs");
            int codecIndex = -1;
            for (int i = 0; i < codecs.size(); i++)
                if (((Integer) call(codecs.get(i), "getCode")) == 0) {
                    codecIndex = i;
                    break;
                }
            check(codecIndex >= 0, "AVC is offered by the actual stream");
            main(() -> call(playerView, "showHideSettingView", true));
            SystemClock.sleep(400);
            main(() -> call(settings, "showVideoCodec"));
            SystemClock.sleep(200);
            for (int i = 0; i < codecIndex; i++) key(20);
            key(23);
            boolean codecReady = false;
            for (int i = 0; i < 100; i++) {
                Object[] format = {null};
                main(() -> format[0] = call(field(fragment, "player"), "getVideoFormat"));
                if (format[0] != null
                        && String.valueOf(field(format[0], "codecs")).startsWith("avc")
                        && snapshot()[6] == 1) {
                    codecReady = true;
                    break;
                }
                SystemClock.sleep(250);
            }
            check(codecReady, "AVC selection reaches the real decoder");
            // Leave the VOD screen using the same Fragment stack as normal navigation.
            main(
                    () -> {
                        Object manager = call(host, "fragments");
                        call(manager, "popBackStackImmediate");
                        call(manager, "executePendingTransactions");
                    });
            stage = "LIVE";
            Object live = api("getLiveList");
            List<?> rooms = (List<?>) call(live, "getRecommend_room_list");
            if (rooms == null || rooms.isEmpty()) {
                List<?> lanes = (List<?>) call(live, "getRoom_list");
                rooms = (List<?>) call(lanes.get(0), "getList");
            }
            check(rooms != null && !rooms.isEmpty(), "live recommendations available");
            Object room = rooms.get(0);
            Object model =
                    loader.loadClass("com.xx.blbl.model.video.VideoModel")
                            .getConstructor()
                            .newInstance();
            long roomId = (Long) call(room, "getRoomid");
            call(model, "setAid", roomId);
            call(model, "setRedirect_url", "https://live.bilibili.com/" + roomId);
            call(model, "setTitle", call(room, "getTitle"));
            open(model);
            decoded = playing(1500);
            check(
                    decoded[2] > 0 && decoded[3] > 0,
                    "live decoded " + decoded[2] + "x" + decoded[3]);
            bindViews();
            main(
                    () -> {
                        call(playerView, "showController");
                        controls.findViewById(id("button_live_settings")).performClick();
                    });
            SystemClock.sleep(450);
            Field popupField = controls.getClass().getDeclaredField("settingsWindow");
            popupField.setAccessible(true);
            PopupWindow popup = (PopupWindow) popupField.get(controls);
            check(popup != null && popup.isShowing(), "live quality popup opens");
            for (int i = 0; i < 40 && !popup.getContentView().hasWindowFocus(); i++)
                SystemClock.sleep(100);
            check(popup.getContentView().hasWindowFocus(), "live quality popup owns input window");
            check(popup.getContentView().findFocus() != null, "live quality popup focuses a row");
            Object liveCoordinator = field(fragment, "coordinator"),
                    oldStreams = field(liveCoordinator, "liveStreams");
            String beforeQuality = String.valueOf(field(liveCoordinator, "f12270N"));
            key(20);
            String[] focusLabel = {""};
            main(() -> {
                View focused = popup.getContentView().findFocus();
                android.widget.TextView text = focused == null ? null : focused.findViewById(id("exo_text"));
                focusLabel[0] = text == null ? "none" : text.getText().toString();
            });
            android.util.Log.i("BBLL_REAL_TEST", "LIVE target row=" + focusLabel[0] + " previous=" + beforeQuality);
            key(23);
            String afterQuality = String.valueOf(field(liveCoordinator, "f12270N"));
            check(!beforeQuality.equals(afterQuality), "remote selection changes live quality: " + afterQuality);
            for (int i = 0; i < 100 && field(liveCoordinator, "liveStreams") == oldStreams; i++)
                SystemClock.sleep(250);
            check(
                    field(liveCoordinator, "liveStreams") != oldStreams,
                    "live quality selection requests fresh stream URLs");
            decoded = playing(1500);
            check(decoded[6] == 1, "changed live quality decodes " + decoded[2] + "x" + decoded[3]);
            key(4);
            long before = snapshot()[0];
            sendKeyDownUpSync(3);
            SystemClock.sleep(900);
            main(
                    () ->
                            host.startActivity(
                                    new Intent(host, host.getClass())
                                            .addFlags(
                                                    Intent.FLAG_ACTIVITY_NEW_TASK
                                                            | Intent
                                                                    .FLAG_ACTIVITY_REORDER_TO_FRONT)));
            SystemClock.sleep(1200);
            decoded = playing(1500);
            check(decoded[6] == 1, "live resumes after Home and foreground return");
            result.putString(
                    "stream",
                    "BILI_REAL_PASS assertions="
                            + checks
                            + " VOD remote pause/seek/resume, settings Back, real quality request,"
                            + " LIVE decode/popup/foreground resume");
            status = Activity.RESULT_OK;
        } catch (Throwable error) {
            result.putString("stream", "FAIL " + stage + " " + error);
        } finally {
            if (host != null)
                try {
                    main(() -> host.finish());
                } catch (Exception ignored) {
                }
            finish(status, result);
        }
    }
}
