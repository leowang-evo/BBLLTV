package com.damon.bblab.playbacktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/** Exercise actual Bilibili media through the application's own APIs and player. */
public final class ExtendedPlaybackProbe extends Instrumentation {
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
        android.util.Log.i("BBLL_EXTRA_TEST", "PASS " + stage + " / " + label);
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
        try {
            return call(response, "getData");
        } catch (NoSuchMethodException ignored) {
            return call(response, "getResult");
        }
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

    private Object lastPlaybackInfo;
    private final List<String> outcomes = new ArrayList<>();
    private int failures;

    private void exercise(String name, Task task) throws Exception {
        stage = name;
        try {
            task.run();
            outcomes.add("PASS " + name);
        } catch (Throwable failure) {
            failures++;
            outcomes.add("FAIL " + name + ": " + failure);
            android.util.Log.e("BBLL_EXTRA_TEST", outcomes.get(outcomes.size() - 1));
        } finally {
            for (int cleanup = 0; cleanup < 6; cleanup++) {
                View top = dialogRoot();
                if (top == host.getWindow().getDecorView()) break;
                key(4);
                SystemClock.sleep(250);
            }
            main(
                    () -> {
                        if (fragment != null && (Boolean) call(fragment, "isAdded"))
                            call(call(host, "fragments"), "popBackStackImmediate");
                    });
            SystemClock.sleep(300);
        }
    }

    private Object openVideo(String bvid) throws Exception {
        Object detail = api("getVideoDetail", null, bvid);
        Object model = call(detail, "getView");
        return openVideoModel(model);
    }

    private Object openVideoModel(Object model) throws Exception {
        check(model != null, "actual video metadata");
        open(model);
        playing(500);
        bindViews();
        lastPlaybackInfo = field(field(fragment, "coordinator"), "playInfo");
        return field(fragment, "coordinator");
    }

    private View dialogRoot() throws Exception {
        View[] active = {null};
        main(
                () -> {
                    Class<?> type = Class.forName("android.view.WindowManagerGlobal");
                    Method get = type.getDeclaredMethod("getInstance");
                    get.setAccessible(true);
                    Field views = type.getDeclaredField("mViews");
                    views.setAccessible(true);
                    List<?> list = (List<?>) views.get(get.invoke(null));
                    for (int i = list.size() - 1; i >= 0; i--) {
                        View root = (View) list.get(i);
                        if (root.isShown() && root.hasWindowFocus()) {
                            active[0] = root;
                            break;
                        }
                    }
                    if (active[0] == null) active[0] = host.getWindow().getDecorView();
                });
        return active[0];
    }

    private View awaitDialog(String marker) throws Exception {
        for (int i = 0; i < 100; i++) {
            View root = dialogRoot();
            if (root != host.getWindow().getDecorView()
                    && root.hasWindowFocus()
                    && root.findViewById(id(marker)) != null
                    && root.findFocus() != null) return root;
            SystemClock.sleep(50);
        }
        throw new AssertionError("Dialog not focused: " + marker);
    }

    private void awaitFocus(View view) throws Exception {
        for (int i = 0; i < 100; i++) {
            boolean[] focused = {false};
            main(() -> focused[0] = view.findFocus() != null && view.findFocus() != view);
            if (focused[0]) return;
            SystemClock.sleep(50);
        }
        throw new AssertionError("Content focus did not settle");
    }

    private void awaitTop(View root) throws Exception {
        for (int i = 0; i < 80; i++) {
            if (dialogRoot() == root && root.hasWindowFocus()) return;
            SystemClock.sleep(50);
        }
        throw new AssertionError("Window focus did not return to expected parent");
    }

    private void awaitAlert() throws Exception {
        for (int i = 0; i < 80; i++) {
            View root = dialogRoot();
            if (root.hasWindowFocus() && root.findViewById(android.R.id.button1) != null) return;
            SystemClock.sleep(50);
        }
        throw new AssertionError("Alert did not receive window focus");
    }

    private void click(String name) throws Exception {
        main(
                () -> {
                    call(playerView, "showController");
                    View v = controls.findViewById(id(name));
                    check(v != null && v.getVisibility() == View.VISIBLE, "available " + name);
                    v.performClick();
                });
        SystemClock.sleep(350);
    }

    private void awaitCid(Object state, long cid) throws Exception {
        boolean ready = false;
        for (int i = 0; i < 150; i++) {
            boolean[] active = {false};
            main(
                    () -> {
                        Object engine = field(fragment, "player");
                        if (engine == null) return;
                        Object item = call(engine, "getCurrentMediaItem");
                        if (item == null) return;
                        Object config = field(item, "localConfiguration");
                        active[0] =
                                config != null
                                        && String.valueOf(field(config, "uri"))
                                                .equals(call(state, "selectedVideoUrl"));
                    });
            if (((Long) call(field(state, "video"), "getCid")) == cid
                    && field(state, "playInfo") != lastPlaybackInfo
                    && active[0]
                    && snapshot()[0] > 700
                    && snapshot()[6] == 1) {
                ready = true;
                break;
            }
            SystemClock.sleep(200);
        }
        check(((Long) call(field(state, "video"), "getCid")) == cid, "selected CID changed");
        check(
                field(state, "playInfo") != lastPlaybackInfo,
                "fresh media response for selected CID");
        check(ready, "new selected media source attached and decoded");
        check(playing(700)[6] == 1, "selected part decodes");
        lastPlaybackInfo = field(state, "playInfo");
    }

    private void subtitles() throws Exception {
        Object state = openVideo("BV1BbKw6XEWq");
        for (int i = 0; i < 40 && field(state, "f12291f") == null; i++) SystemClock.sleep(200);
        List<?> tracks = (List<?>) field(state, "f12291f");
        check(tracks != null && !tracks.isEmpty(), "actual subtitle tracks available");
        main(
                () -> {
                    state.getClass().getField("f12264G").setInt(state, 0);
                    call(state, "loadSubtitle");
                });
        for (int i = 0; i < 60 && field(state, "subtitleLines") == null; i++)
            SystemClock.sleep(200);
        List<?> lines = (List<?>) field(state, "subtitleLines");
        check(lines != null && !lines.isEmpty(), "actual subtitle JSON parsed");
        Object line = lines.get(Math.min(2, lines.size() - 1));
        double from = ((Number) call(line, "getFrom")).doubleValue(),
                to = ((Number) call(line, "getTo")).doubleValue();
        long position = (long) ((from + to) * 500);
        main(() -> call(field(fragment, "player"), "seekTo", position));
        boolean visible = false;
        for (int i = 0; i < 30; i++) {
            boolean[] shown = {false};
            main(
                    () -> {
                        android.widget.TextView t = host.findViewById(id("text_subtitle"));
                        shown[0] =
                                t != null
                                        && t.getVisibility() == View.VISIBLE
                                        && t.getText().toString().equals(call(line, "getContent"));
                    });
            if (shown[0]) {
                visible = true;
                break;
            }
            SystemClock.sleep(150);
        }
        check(visible, "real timed subtitle rendered");
        main(
                () -> {
                    state.getClass().getField("f12264G").setInt(state, tracks.size());
                    call(state, "loadSubtitle");
                });
        SystemClock.sleep(650);
        check(field(state, "subtitleLines") == null, "subtitle Off clears active lines");
    }

    private void multipart() throws Exception {
        Object state = openVideo("BV1qW4y1a7fU");
        List<?> pages = (List<?>) field(state, "f12287c");
        check(pages != null && pages.size() > 2, "actual multi-part metadata");
        long second = (Long) call(pages.get(1), "getCid"),
                first = (Long) call(pages.get(0), "getCid");
        click("button_choose_episode");
        View root = awaitDialog("top_title");
        View recycler = root.findViewById(id("recyclerView"));
        check(recycler != null, "episode chooser opens");
        awaitFocus(recycler);
        check(recycler.findFocus() != null, "episode chooser row has focus");
        key(22);
        key(23);
        awaitCid(state, second);
        main(
                () -> {
                    call(playerView, "showHideNextPrevious", true);
                });
        click("button_previous");
        awaitCid(state, first);
        click("button_next");
        awaitCid(state, second);
        Object app = field(fragment, "appState");
        int original = app.getClass().getField("continuationMode").getInt(app);
        try {
            main(
                    () -> {
                        app.getClass().getField("continuationMode").setInt(app, 3);
                        call(field(fragment, "player"), "seekTo", snapshotDuration() - 700);
                    });
            awaitCid(state, (Long) call(pages.get(2), "getCid"));
        } finally {
            main(() -> app.getClass().getField("continuationMode").setInt(app, original));
        }
    }

    private void s16MultipartCollection() throws Exception {
        String bvid = "BV15es76gEgL";
        long first = 43611326038L, second = 43611851876L;
        HttpURLConnection request = (HttpURLConnection)
                new URL("https://api.bilibili.com/x/web-interface/view?bvid=" + bvid)
                        .openConnection();
        request.setConnectTimeout(10000);
        request.setReadTimeout(10000);
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
        } finally {
            request.disconnect();
        }
        JSONObject detail = new JSONObject(response.toString());
        check(detail.getInt("code") == 0, "official S16 replay metadata available");
        Object gson = loader.loadClass("com.google.gson.Gson").getConstructor().newInstance();
        Object model = call(gson, "fromJson", detail.getJSONObject("data").toString(),
                loader.loadClass("com.xx.blbl.model.video.VideoModel"));
        Object state = openVideoModel(model);
        List<?> collection = (List<?>) call(state, "collectionEpisodes");
        check(collection.size() > 1
                        && "BV1TgWp6MEHJ".equals(call(collection.get(1), "getBvid")),
                "BLG vs T1 follows the final in the same collection");
        check(((Long) call(field(state, "video"), "getCid")) == first,
                "final starts at game one");

        click("button_choose_episode");
        View root = awaitDialog("top_title");
        View recycler = root.findViewById(id("recyclerView"));
        awaitFocus(recycler);
        key(19);
        check(root.findFocus().getId() == id("button_more_info"),
                "DPAD up reaches the collection toggle");
        key(23);
        awaitFocus(recycler);
        check(((TextView) recycler.findFocus().findViewById(id("text_title")))
                        .getText().toString().contains("GEN vs BLG"),
                "collection opens on the current match");
        key(19);
        key(23);
        awaitFocus(recycler);
        check(
                "第一局".contentEquals(
                        ((TextView) recycler.findFocus().findViewById(id("text_title"))).getText()),
                "chooser opens on game one");
        key(22);
        check(
                "第二局".contentEquals(
                        ((TextView) recycler.findFocus().findViewById(id("text_title"))).getText()),
                "DPAD right focuses game two");
        key(23);
        awaitCid(state, second);

        click("button_choose_episode");
        root = awaitDialog("top_title");
        recycler = root.findViewById(id("recyclerView"));
        awaitFocus(recycler);
        key(21);
        key(23);
        awaitCid(state, first);

        Object app = field(fragment, "appState");
        Field continuation = app.getClass().getField("continuationMode");
        int original = continuation.getInt(app);
        try {
            main(() -> {
                continuation.setInt(app, 3);
                call(field(fragment, "player"), "seekTo", snapshotDuration() - 700);
            });
            awaitCid(state, second);
            check(bvid.equals(call(field(state, "video"), "getBvid")),
                    "game one ends and game two plays within the same match");
        } finally {
            main(() -> continuation.setInt(app, original));
        }
    }

    private long snapshotDuration() throws Exception {
        return (Long) call(field(fragment, "player"), "getDuration");
    }

    private void series() throws Exception {
        Object season = api("getVideoEpisodes", 1733L, null);
        List<?> episodes = (List<?>) call(season, "getEpisodes");
        check(episodes.size() > 2, "actual PGC episodes");
        Object first = episodes.get(0), second = episodes.get(1);
        Object model =
                loader.loadClass("com.xx.blbl.model.video.VideoModel")
                        .getConstructor()
                        .newInstance();
        call(model, "setSid", 1733L);
        call(model, "setEpid", call(first, "getId"));
        call(model, "setAid", call(first, "getAid"));
        call(model, "setBvid", call(first, "getBvid"));
        call(model, "setCid", call(first, "getCid"));
        call(model, "setTitle", "PGC regression");
        open(model);
        bindViews();
        Object state = field(fragment, "coordinator");
        String error = "";
        for (int attempt = 0; attempt < 60; attempt++) {
            android.widget.TextView[] text = {null};
            main(() -> text[0] = playerView.findViewById(id("exo_error_message")));
            if (text[0] != null && text[0].getVisibility() == View.VISIBLE)
                error = text[0].getText().toString();
            if (field(fragment, "player") != null || !error.isEmpty()) break;
            SystemClock.sleep(150);
        }
        boolean limited =
                error.contains("-351") || error.contains("-352") || error.contains("-412");
        if (!limited) {
            playing(500);
            lastPlaybackInfo = field(state, "playInfo");
        } else check(error.contains("受限"), "PGC service restriction has a visible explanation");
        click("button_choose_episode");
        View recycler = awaitDialog("top_title").findViewById(id("recyclerView"));
        check(recycler != null, "PGC chooser opens");
        awaitFocus(recycler);
        check(recycler.findFocus() != null, "PGC chooser populated and focused");
        key(22);
        key(23);
        if (limited) {
            check(
                    ((Long) call(field(state, "video"), "getCid")).equals(call(second, "getCid")),
                    "PGC chooser selects correct episode despite unavailable stream");
            outcomes.add("LIMITED actual PGC decoding: " + error);
            return;
        }
        awaitCid(state, (Long) call(second, "getCid"));
        main(() -> call(playerView, "showHideNextPrevious", true));
        click("button_previous");
        awaitCid(state, (Long) call(first, "getCid"));
        click("button_next");
        awaitCid(state, (Long) call(second, "getCid"));
        Object app = field(fragment, "appState");
        int original = app.getClass().getField("continuationMode").getInt(app);
        try {
            main(
                    () -> {
                        app.getClass().getField("continuationMode").setInt(app, 3);
                        state.getClass().getField("f12284a0").setBoolean(state, true);
                        call(field(fragment, "player"), "seekTo", snapshotDuration() - 700);
                    });
            awaitCid(state, (Long) call(episodes.get(2), "getCid"));
            check(
                    (Boolean) call(fragment, "isAdded"),
                    "PGC continues before exit-after-play is applied");
        } finally {
            main(() -> app.getClass().getField("continuationMode").setInt(app, original));
        }
    }

    private void lifecycle() throws Exception {
        Object state = openVideo("BV1GJ411x7h7"),
                detail = api("getVideoDetail", null, "BV1GJ411x7h7");
        Object response =
                loader.loadClass("com.xx.blbl.network.response.BaseResponse")
                        .getConstructor()
                        .newInstance();
        call(response, "setData", detail);
        Object callback =
                loader.loadClass("com.xx.blbl.playback.PlaybackDetailsCallback")
                        .getConstructor(state.getClass(), int.class)
                        .newInstance(state, 5);
        main(() -> call(call(host, "fragments"), "popBackStackImmediate"));
        check(!(Boolean) call(fragment, "isAdded"), "playback page detached before late response");
        main(() -> call(callback, "onResponse", response));
        main(() -> call(callback, "onFailure", new java.io.IOException("fixture late failure")));
        check(!host.isFinishing(), "late video response cannot crash or reopen the detached page");
    }

    private void continuation() throws Exception {
        Object state = openVideo("BV1GJ411x7h7"), first = field(state, "video");
        Object next = call(api("getVideoDetail", null, "BV1BbKw6XEWq"), "getView");
        Object app = field(fragment, "appState");
        int mode = app.getClass().getField("continuationMode").getInt(app);
        try {
            main(
                    () -> {
                        state.getClass().getField("f12287c").set(state, null);
                        state.getClass().getField("d").set(state, null);
                        state.getClass().getField("f12273Q").set(state, null);
                        state.getClass()
                                .getField("f12272P")
                                .set(state, new ArrayList<>(Collections.singletonList(next)));
                        state.getClass().getField("f12282Z").setBoolean(state, true);
                        state.getClass().getField("f12284a0").setBoolean(state, false);
                    });
            for (int policy : new int[] {0, 3, 2}) {
                main(
                        () -> {
                            app.getClass().getField("continuationMode").setInt(app, policy);
                            call(state, "playNext", false);
                        });
                SystemClock.sleep(350);
                check(
                        field(state, "video") == first,
                        "continuation policy " + policy + " stops without a next episode/queue");
            }
            main(
                    () -> {
                        app.getClass().getField("continuationMode").setInt(app, 2);
                        state.getClass()
                                .getField("f12273Q")
                                .set(state, new ArrayList<>(Collections.singletonList(next)));
                        call(state, "playNext", false);
                    });
            awaitCid(state, (Long) call(next, "getCid"));
            main(
                    () -> {
                        app.getClass().getField("continuationMode").setInt(app, 1);
                        state.getClass().getField("f12273Q").set(state, null);
                        state.getClass().getField("f12287c").set(state, null);
                        state.getClass().getField("d").set(state, null);
                        state.getClass()
                                .getField("f12272P")
                                .set(state, new ArrayList<>(Collections.singletonList(first)));
                        call(state, "playNext", false);
                    });
            awaitCid(state, (Long) call(first, "getCid"));
            main(
                    () -> {
                        app.getClass().getField("continuationMode").setInt(app, 0);
                        state.getClass().getField("f12284a0").setBoolean(state, true);
                        call(state, "playNext", false);
                    });
            check(!(Boolean) call(fragment, "isAdded"), "exit after playback returns to list");
        } finally {
            main(() -> app.getClass().getField("continuationMode").setInt(app, mode));
        }
    }

    private void specialProfiles() throws Exception {
        Object type =
                loader.loadClass("com.xx.blbl.model.search.SearchType").getField("Video").get(null);
        for (String query : new String[] {"杜比视界 测试", "HiRes 无损 音乐"}) {
            Object search = api("searchByTypeWbi", type, query, 1, "totalrank");
            List<?> hits = (List<?>) call(search, "getResult");
            String selected = null;
            boolean dolby = query.startsWith("杜比");
            for (int n = 0; n < Math.min(3, hits.size()); n++) {
                String bv = (String) call(hits.get(n), "getBvid");
                if (bv == null || bv.isEmpty()) continue;
                Object video = call(api("getVideoDetail", null, bv), "getView");
                Object info =
                        api(
                                "getVideoPlayInfoWbi",
                                call(video, "getAid"),
                                bv,
                                call(video, "getCid"),
                                126,
                                4048,
                                1);
                Object dash = call(info, "getDash");
                if (dash == null) continue;
                boolean found = false;
                if (dolby) {
                    List<?> tracks = (List<?>) call(dash, "getVideo");
                    if (tracks != null)
                        for (Object track : tracks)
                            if (((Integer) call(track, "getId")) == 126) found = true;
                } else {
                    Object flac = call(dash, "getFlac");
                    found = flac != null && call(flac, "getAudio") != null;
                }
                if (found) {
                    selected = bv;
                    break;
                }
            }
            if (selected == null) {
                outcomes.add(
                        "LIMITED " + query + ": three samples did not offer an entitled track");
                continue;
            }
            Object state = openVideo(selected);
            if (dolby) {
                List<?> qualities = (List<?>) field(state, "f12265H");
                int index = -1;
                for (int n = 0; n < qualities.size(); n++)
                    if (((Integer) call(qualities.get(n), "getCode")) == 126) index = n;
                check(index >= 0, "Dolby Vision menu entry");
                final int chosen = index;
                Object old = field(state, "playInfo");
                main(() -> call(fragmentType, "T", fragment, chosen));
                for (int n = 0; n < 80 && old == field(state, "playInfo"); n++)
                    SystemClock.sleep(200);
                check(playing(500)[6] == 1, "Dolby Vision stream decodes");
                Object[] format = {null};
                main(() -> format[0] = call(field(fragment, "player"), "getVideoFormat"));
                String codec = String.valueOf(field(format[0], "codecs"));
                check(
                        codec.startsWith("dvhe") || codec.startsWith("dvh1"),
                        "actual Dolby Vision codec");
                outcomes.add("DOLBY " + codec + " color=" + field(format[0], "colorInfo"));
            }
            List<?> audios = (List<?>) field(state, "f12266I");
            for (int n = 0; n < audios.size(); n++) {
                int code = (Integer) call(audios.get(n), "getCode");
                if (code != 30250 && code != 30251) continue;
                final int chosen = n;
                main(() -> call(fragmentType, "S", fragment, chosen));
                check(playing(500)[6] == 1, "special audio stream decodes " + code);
                Object[] format = {null};
                main(() -> format[0] = call(field(fragment, "player"), "getAudioFormat"));
                check(format[0] != null, "special audio decoder active " + code);
                outcomes.add(
                        "SPECIAL AUDIO "
                                + code
                                + " mime="
                                + field(format[0], "sampleMimeType")
                                + " codecs="
                                + field(format[0], "codecs"));
            }
            main(() -> call(call(host, "fragments"), "popBackStackImmediate"));
            SystemClock.sleep(300);
        }
    }

    private void highProfiles() throws Exception {
        Object type =
                loader.loadClass("com.xx.blbl.model.search.SearchType").getField("Video").get(null);
        Object search = api("searchByTypeWbi", type, "4K HDR 杜比 测试", 1, "totalrank");
        List<?> hits = (List<?>) call(search, "getResult");
        check(hits != null && !hits.isEmpty(), "signed Chinese search results");
        String selected = null;
        List<?> available = null;
        for (int n = 0; n < Math.min(3, hits.size()); n++) {
            String bv = (String) call(hits.get(n), "getBvid");
            if (bv == null || bv.isEmpty()) continue;
            Object video = call(api("getVideoDetail", null, bv), "getView");
            Object info =
                    api(
                            "getVideoPlayInfoWbi",
                            call(video, "getAid"),
                            bv,
                            call(video, "getCid"),
                            127,
                            4048,
                            1);
            List<?> q = (List<?>) call(info, "getAccept_quality");
            if (q != null && (q.contains(120) || q.contains(125) || q.contains(126))) {
                selected = bv;
                available = q;
                break;
            }
        }
        if (selected == null) {
            outcomes.add("LIMITED high profiles: sampled media did not offer 4K/HDR/Dolby");
            return;
        }
        Object state = openVideo(selected);
        for (int code : new int[] {120, 125, 126}) {
            if (!available.contains(code)) {
                outcomes.add("LIMITED profile " + code + ": not offered by sampled media");
                continue;
            }
            List<?> qualities = (List<?>) field(state, "f12265H");
            int index = -1;
            for (int i = 0; i < qualities.size(); i++)
                if (((Integer) call(qualities.get(i), "getCode")) == code) index = i;
            check(index >= 0, "quality menu offers " + code);
            final int chosen = index;
            Object old = field(state, "playInfo");
            main(() -> call(fragmentType, "T", fragment, chosen));
            for (int i = 0; i < 80 && field(state, "playInfo") == old; i++) SystemClock.sleep(200);
            check(field(state, "playInfo") != old, "fresh high profile response " + code);
            long[] decoded = playing(500);
            check(
                    (Integer) call(field(state, "playInfo"), "getQuality") == code,
                    "server selected requested profile " + code);
            for (Object track : (List<?>) field(state, "videoTracks"))
                check(
                        ((Integer) call(track, "getId")) == code,
                        "selected tracks match profile " + code);
            Object[] format = {null};
            main(() -> format[0] = call(field(fragment, "player"), "getVideoFormat"));
            check(format[0] != null, "high profile decoder active " + code);
            if (code == 125) {
                Object color = field(format[0], "colorInfo");
                check(
                        color != null
                                && ((Integer) field(color, "colorTransfer") == 6
                                        || (Integer) field(color, "colorTransfer") == 7),
                        "HDR decoder has PQ/HLG transfer");
            }
            outcomes.add(
                    "PROFILE "
                            + code
                            + " decoded "
                            + decoded[2]
                            + "x"
                            + decoded[3]
                            + " codecs="
                            + field(format[0], "codecs")
                            + " color="
                            + field(format[0], "colorInfo"));
        }
        List<?> audios = (List<?>) field(state, "f12266I");
        outcomes.add("AUDIO offered " + String.valueOf(audios));
        for (int i = 0; i < audios.size(); i++) {
            final int index = i;
            main(() -> call(fragmentType, "S", fragment, index));
            check(
                    playing(500)[6] == 1,
                    "actual audio decodes code=" + call(audios.get(i), "getCode"));
        }
    }

    private void interactive() throws Exception {
        Object state = openVideo("BV1WE421A79J");
        View overlay = (View) field(fragment, "f9693F0");
        for (int i = 0; i < 80 && !(Boolean) field(state, "f12306t0"); i++) SystemClock.sleep(150);
        check((Boolean) field(state, "f12306t0"), "real interaction graph recognized");
        main(() -> call(field(fragment, "player"), "seekTo", snapshotDuration() - 800));
        for (int i = 0; i < 100; i++) {
            if (((android.view.ViewGroup) overlay).getChildCount() > 0) break;
            SystemClock.sleep(150);
        }
        View choice = findChoice(overlay);
        check(choice != null, "real branch choices rendered");
        awaitFocus(overlay);
        check(overlay.findFocus() != null, "interactive choices receive focus");
        Object model = choice.getTag();
        long target = (Long) call(model, "getCid");
        check(target != 0, "choice has target CID");
        main(() -> choice.requestFocus());
        key(23);
        awaitCid(state, target);
        check((Boolean) call(fragment, "isAdded"), "interactive player stays open");
    }

    private View findChoice(View root) {
        if (root instanceof android.widget.Button) return root;
        if (root instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) root;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = findChoice(group.getChildAt(i));
                if (child != null) return child;
            }
        }
        return null;
    }

    private void dialogs() throws Exception {
        Object state = openVideo("BV1GJ411x7h7");
        Object video = field(state, "video");
        api("hasLike", call(video, "getAid"), call(video, "getBvid"));
        api("hasCollection", call(video, "getAid"), call(video, "getBvid"));
        api("hasGiveCoin", call(video, "getAid"), call(video, "getBvid"));
        click("button_more");
        View root = awaitDialog("button_coin");
        check(root.findViewById(id("button_coin")) != null, "video actions dialog");
        check(root.findFocus() != null, "video actions receives remote focus");
        View coin = root.findViewById(id("button_coin"));
        main(() -> coin.performLongClick());
        awaitAlert();
        check(
                dialogRoot().findViewById(android.R.id.button1) != null,
                "coin amount confirmation dialog opens");
        key(4);
        awaitTop(root);
        View favorite = root.findViewById(id("button_collection"));
        main(() -> favorite.performLongClick());
        awaitAlert();
        check(
                dialogRoot().findViewById(android.R.id.button1) != null,
                "favorite folder picker opens");
        key(4);
        awaitTop(root);
        key(4);
        awaitTop(host.getWindow().getDecorView());
        check(playing(700)[6] == 1, "closing actions resumes video");
        click("button_up_info");
        root = awaitDialog("button_follow");
        View recycler = root.findViewById(id("recyclerView"));
        check(recycler != null, "uploader dialog opens");
        for (int i = 0; i < 50; i++) {
            Object adapter = call(recycler, "getAdapter");
            if ((Integer) call(adapter, "getItemCount") > 0) break;
            SystemClock.sleep(200);
        }
        check(
                (Integer) call(call(recycler, "getAdapter"), "getItemCount") > 0,
                "uploader videos load");
        key(4);
        check(playing(700)[6] == 1, "closing uploader resumes video");
        Object engine = field(fragment, "player");
        int[] repeat = new int[1];
        main(() -> repeat[0] = (Integer) call(engine, "getRepeatMode"));
        int previousRepeat = repeat[0];
        click("button_repeat");
        int expectedRepeat = previousRepeat == 1 ? 0 : 1;
        main(() -> repeat[0] = (Integer) call(engine, "getRepeatMode"));
        check(repeat[0] == expectedRepeat, "play order toggles actual repeat mode");
        if (expectedRepeat == 0) click("button_repeat");
        long duration = snapshot()[5];
        check(duration > 5000, "repeat media has a known duration");
        main(() -> { call(engine, "seekTo", duration - 350); call(engine, "play"); });
        boolean looped = false;
        for (int i = 0; i < 60; i++) {
            SystemClock.sleep(150);
            long[] current = snapshot();
            if (current[0] < 3000 && current[6] == 1) { looped = true; break; }
        }
        check(looped, "single-item loop returns from actual media end to beginning");
    }

    private String onlyCase;

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        onlyCase = args.getString("case");
        start();
    }

    private void selected(String name, Task task) throws Exception {
        if (onlyCase == null || onlyCase.equals(name)) exercise(name, task);
    }

    @Override
    public void onStart() {
        Bundle result = new Bundle();
        int status = Activity.RESULT_CANCELED;
        try {
            loader = getTargetContext().getClassLoader();
            host =
                    startActivitySync(
                            new Intent()
                                    .setClassName(getTargetContext(), "com.xx.blbl.ui.MainActivity")
                                    .addFlags(
                                            Intent.FLAG_ACTIVITY_NEW_TASK
                                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            setInTouchMode(false);
            SystemClock.sleep(1200);
            network = call(loader.loadClass("com.xx.blbl.AppController"), "network");
            fragmentType = loader.loadClass("com.xx.blbl.ui.playback.PlaybackFragment");
            selected("subtitles", this::subtitles);
            selected("multipart", this::multipart);
            selected("s16-multipart-collection", this::s16MultipartCollection);
            selected("series", this::series);
            selected("interactive", this::interactive);
            selected("dialogs", this::dialogs);
            selected("highProfiles", this::highProfiles);
            selected("continuation", this::continuation);
            selected("specialProfiles", this::specialProfiles);
            selected("lifecycle", this::lifecycle);
            result.putString(
                    "stream",
                    (failures == 0 ? "EXTENDED_PASS" : "EXTENDED_FAIL")
                            + " assertions="
                            + checks
                            + "\n"
                            + String.join("\n", outcomes));
            if (failures == 0) status = Activity.RESULT_OK;
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
