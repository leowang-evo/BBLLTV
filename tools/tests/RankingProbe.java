package com.damon.bblab.playbacktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/** Exercise actual Bilibili media through the application's own APIs and player. */
public final class RankingProbe extends Instrumentation {
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
        android.util.Log.i("BBLL_RANK_TEST", "PASS " + stage + " / " + label);
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
        StringBuilder report = new StringBuilder();
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
            fragmentType = loader.loadClass("com.xx.blbl.ui.catalog.RankingFragment");
            int[] ids = {1003, 1008, 1010, 1020, 177};
            String[] names = {"music", "game", "knowledge", "food", "documentary"};
            for (int i = 0; i < ids.length; i++) {
                stage = names[i];
                Object data = api("getRanking", ids[i]);
                List<?> videos = (List<?>) call(data, "getList");
                check(
                        videos != null && videos.size() >= 2,
                        "API content count=" + (videos == null ? 0 : videos.size()));
                Object model =
                        loader.loadClass("com.xx.blbl.model.CategoryModel")
                                .getConstructor(int.class, String.class)
                                .newInstance(ids[i], names[i]);
                open(model);
                final int[] count = {0};
                for (int attempt = 0; attempt < 60; attempt++) {
                    main(
                            () -> {
                                Object adapter = field(fragment, "listAdapter");
                                if (adapter != null)
                                    count[0] = ((List<?>) field(adapter, "items")).size();
                            });
                    if (count[0] >= 2) break;
                    SystemClock.sleep(200);
                }
                if (count[0] < 2) {
                    String[] message = {""};
                    main(
                            () ->
                                    message[0] =
                                            String.valueOf(
                                                    ((android.widget.TextView)
                                                                    field(fragment, "statusText"))
                                                            .getText()));
                    report.append(stage).append(": UI status ").append(message[0]).append("\n");
                }
                check(count[0] >= 2, "UI adapter count=" + count[0]);
                boolean[] focused = {false};
                main(
                        () -> {
                            View recycler = (View) field(fragment, "recyclerView");
                            recycler.requestFocus();
                            focused[0] = recycler.hasFocus();
                        });
                check(focused[0], "rendered grid can receive focus");
                report.append(stage)
                        .append(": API ")
                        .append(videos.size())
                        .append(", UI ")
                        .append(count[0])
                        .append("\n");
                main(() -> call(call(host, "fragments"), "popBackStackImmediate"));
                SystemClock.sleep(300);
            }
            result.putString("stream", "RANKING_PASS assertions=" + checks + "\n" + report);
            status = Activity.RESULT_OK;
        } catch (Throwable error) {
            result.putString("stream", "FAIL " + stage + " " + error + "\n" + report);
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
