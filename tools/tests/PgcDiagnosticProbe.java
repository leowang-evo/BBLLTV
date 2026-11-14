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
public final class PgcDiagnosticProbe extends Instrumentation {
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

    private Object privateField(Object target, String name) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(target);
    }

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        start();
    }

    @Override
    public void onStart() {
        Bundle result = new Bundle();
        StringBuilder report = new StringBuilder();
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
            SystemClock.sleep(1200);
            network = call(loader.loadClass("com.xx.blbl.AppController"), "network");
            Object environment = privateField(network, "environment");
            Object builder =
                    loader.loadClass("okhttp3.OkHttpClient$Builder").getConstructor().newInstance();
            call(builder, "cookieJar", call(environment, "cookieJar"));
            Object interceptor =
                    loader.loadClass("com.xx.blbl.network.NetworkManager$HeaderInterceptor")
                            .getConstructor(network.getClass())
                            .newInstance(network);
            call(builder, "addInterceptor", interceptor);
            Object client = call(builder, "build");
            for (int variant = 0; variant < 3; variant++) {
                HashMap<String, String> params = new HashMap<>();
                params.put("avid", "1082731");
                params.put("bvid", "BV1fx411K7F1");
                params.put("ep_id", "32374");
                params.put("cid", "34441989712");
                params.put("qn", "80");
                params.put("fnval", "4048");
                params.put("fourk", "1");
                params.put("fnver", "0");
                params.put("from_client", "BROWSER");
                params.put("drm_tech_type", variant == 0 ? "0" : "2");
                params.put("web_location", "1315873");
                Map<?, ?> signed =
                        (Map<?, ?>)
                                call(
                                        privateField(network, "wbiGenerator"),
                                        "generatorWbi",
                                        params,
                                        privateField(network, "wbiImageKey"),
                                        privateField(network, "wbiSubKey"));
                Object url =
                        call(
                                loader.loadClass("okhttp3.HttpUrl"),
                                "get",
                                "https://api.bilibili.com/pgc/player/web/"
                                        + (variant == 2 ? "v2/" : "")
                                        + "playurl");
                Object urlBuilder = call(url, "newBuilder");
                for (Map.Entry<?, ?> e : signed.entrySet())
                    call(
                            urlBuilder,
                            "addQueryParameter",
                            String.valueOf(e.getKey()),
                            String.valueOf(e.getValue()));
                Object request =
                        loader.loadClass("okhttp3.Request$Builder").getConstructor().newInstance();
                call(request, "url", call(urlBuilder, "build"));
                call(request, "header", "Referer", "https://www.bilibili.com/bangumi/play/ep32374");
                Object response = call(call(client, "newCall", call(request, "build")), "execute");
                String text = (String) call(call(response, "body"), "string");
                org.json.JSONObject json = new org.json.JSONObject(text);
                report.append("variant ")
                        .append(variant)
                        .append(" HTTP ")
                        .append(call(response, "code"))
                        .append(" code ")
                        .append(json.optInt("code"))
                        .append(" keys ")
                        .append(json.names())
                        .append("\n");
                for (String key : new String[] {"result", "data"}) {
                    org.json.JSONObject data = json.optJSONObject(key);
                    if (data != null) {
                        report.append(key).append(" keys ").append(data.names()).append("\n");
                        org.json.JSONObject info = data.optJSONObject("video_info");
                        if (info != null)
                            report.append("video_info keys ").append(info.names()).append("\n");
                    }
                }
                call(response, "close");
                SystemClock.sleep(400);
            }
            result.putString("stream", "PGC_DIAGNOSTIC_COMPLETE\n" + report);
            status = Activity.RESULT_OK;
        } catch (Throwable error) {
            result.putString("stream", "FAIL " + error + "\n" + report);
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
