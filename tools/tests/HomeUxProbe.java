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
public final class HomeUxProbe extends Instrumentation {
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
        android.util.Log.i("BBLL_HOME_TEST", "PASS " + stage + " / " + label);
    }

    private Object model() throws Exception {
        Object video = loader.loadClass("com.xx.blbl.model.video.VideoModel").getConstructor().newInstance();
        call(video, "setAid", 170001L); call(video, "setCid", 279786L);
        call(video, "setBvid", "BV17x411w7KC"); call(video, "setTitle", "Continue watching persistence probe");
        call(video, "setDuration", 120L); call(video, "setPic", "");
        return video;
    }
    private void actualResume(Object home, Object popular, Class<?> resume) throws Exception {
        stage = "actual resume";
        Object candidate = null;
        for (Object item : (List<?>) field(field(popular, "O0"), "items")) {
            if ((Long) call(item, "getDuration") > 60L && (Long) call(item, "getCid") > 0L) {
                candidate = item; break;
            }
        }
        check(candidate != null, "a real resumable video is available");
        call(resume, "save", candidate, 12000L, (Long) call(candidate, "getDuration") * 1000L);
        main(() -> call(home, "screenResumed"));
        View card = host.findViewById(getTargetContext().getResources().getIdentifier("tv_resume", "id", getTargetContext().getPackageName()));
        main(() -> card.requestFocus()); SystemClock.sleep(250);
        check(card.hasFocus(), "remote enters continue watching");
        Object manager = call(host, "fragments");
        Object playback = null;
        try {
            sendKeyDownUpSync(23);
            for (int i = 0; i < 50 && playback == null; i++) {
                SystemClock.sleep(200);
                for (Object item : (List<?>) call(manager, "getFragments"))
                    if (item.getClass().getName().endsWith(".PlaybackFragment")) playback = item;
            }
            check(playback != null, "continue watching opens the real player");
            final Object target = playback;
            long[] observed = new long[4];
            for (int i = 0; i < 100; i++) {
                SystemClock.sleep(200);
                main(() -> {
                    Object engine = field(target, "player");
                    if (engine == null) return;
                    observed[0] = (Long) call(engine, "getCurrentPosition");
                    observed[1] = ((Number) field(call(engine, "getVideoSize"), "width")).longValue();
                    observed[2] = (Boolean) call(engine, "isPlaying") ? 1 : 0;
                    observed[3] = (Integer) call(engine, "getPlaybackState");
                });
                if (observed[1] > 0 && observed[2] == 1 && observed[0] >= 11500) break;
            }
            check(observed[1] > 0 && observed[2] == 1 && observed[0] >= 11500,
                    "actual video decodes at saved progress: " + observed[0] + " ms");
            Object current = field(field(target, "coordinator"), "video");
            check(call(current, "getCid").equals(call(candidate, "getCid")), "actual resume preserves the selected CID");
            long start = observed[0]; SystemClock.sleep(1500);
            main(() -> observed[0] = (Long) call(field(target, "player"), "getCurrentPosition"));
            check(observed[0] > start + 600, "resumed video advances normally");
            View playerView = (View) field(target, "playerView");
            View controls = (View) call(playerView, "getAppController");
            View indicator = controls.findViewById(getTargetContext().getResources().getIdentifier("loading_progress_bar", "id", getTargetContext().getPackageName()));
            check(indicator.getVisibility() == View.GONE, "ready playback does not show a buffering spinner");
        } finally {
            main(() -> { if ((Integer) call(manager, "getBackStackEntryCount") > 0) call(manager, "popBackStackImmediate"); });
            SystemClock.sleep(650);
        }
        check(card.hasFocus(), "return from continue watching restores its entry focus");
    }
    @Override public void onCreate(Bundle arguments) { super.onCreate(arguments); start(); }
    @Override public void onStart() {
        Bundle result = new Bundle(); int status = Activity.RESULT_CANCELED;
        Object state = null, home = null; long originalUser = 0;
        android.content.SharedPreferences prefs = getTargetContext().getSharedPreferences("continue_watching", 0);
        try {
            loader = getTargetContext().getClassLoader();
            host = startActivitySync(new Intent().setClassName(getTargetContext(), "com.xx.blbl.ui.MainActivity")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            setInTouchMode(false); SystemClock.sleep(1800);
            main(() -> call(host, "selectTab", 0)); SystemClock.sleep(700);
            Object manager = call(host, "fragments");
            for (Object item : (List<?>) call(manager, "getFragments"))
                if (item.getClass().getName().endsWith(".HomeFragment")) home = item;
            check(home != null, "home retained");
            Object pager = field(home, "f9662H0");
            check(((Integer) call(field(home, "f9663I0"), "getCount")) == 4, "all four home tabs retained");
            main(() -> call(pager, "setCurrentItem", 1));
            Object popular = null;
            for (int i = 0; i < 35 && popular == null; i++) {
                SystemClock.sleep(300);
                for (Object child : (List<?>) call(call(home, "childFragments"), "getFragments")) {
                    if (child.getClass().getName().endsWith(".PopularVideosFragment")) {
                        Object adapter = field(child, "O0");
                        if (adapter != null && ((List<?>) field(adapter, "items")).size() >= 12) popular = child;
                    }
                }
            }
            check(popular != null, "popular videos loaded");
            final Object list = popular;
            Object recycler = field(list, "recyclerView"), layout = call(recycler, "getLayoutManager");
            check(((Integer) call(layout, "getSpanCount")) == 4, "four-column grid retained");
            check(call(recycler, "getItemAnimator") == null, "home updates do not run remove/insert fades");
            int size = ((List<?>) field(field(list, "O0"), "items")).size();
            main(() -> call(list, "renderCachedData", new ArrayList<>()));
            check(((List<?>) field(field(list, "O0"), "items")).size() == size, "late cache cannot replace fresh data");
            main(() -> call(recycler, "scrollToPosition", 8)); SystemClock.sleep(650);
            View card = (View) call(layout, "findViewByPosition", 8);
            check(card != null, "scrolled card attached");
            main(() -> card.requestFocus()); SystemClock.sleep(250);
            check(card.hasFocus(), "remote focus enters card");
            check(Math.abs(card.getScaleX() - 1.025f) < .001f, "focus transform finishes within 250 ms");
            SystemClock.sleep(800);
            int[] before = new int[2]; card.getLocationOnScreen(before);
            Object blank = loader.loadClass("androidx.fragment.app.Fragment").getConstructor().newInstance();
            main(() -> { call(host, "showScreen", blank, "homeFocusProbe"); call(manager, "executePendingTransactions"); });
            SystemClock.sleep(400); main(() -> call(host, "onBackPressed")); SystemClock.sleep(600);
            check(card.hasFocus(), "back restores original focused video");
            int[] after = new int[2]; card.getLocationOnScreen(after);
            check(before[1] == after[1], "back preserves scroll position " + before[1] + " -> " + after[1]);
            stage = "continue watching";
            state = call(loader.loadClass("com.xx.blbl.AppController"), "state");
            originalUser = (Long) field(state, "userId");
            state.getClass().getField("userId").setLong(state, -9139L);
            Class<?> resume = loader.loadClass("com.xx.blbl.platform.ContinueWatching");
            prefs.edit().remove("video_-9139").remove("video_-9140").commit();
            Object video = model(); call(resume, "save", video, 2000L, 120000L);
            check(call(resume, "load") == null, "accidental short opens are ignored");
            call(resume, "save", video, 12000L, 120000L);
            Object loaded = call(resume, "load");
            check(loaded != null && ((Long) call(loaded, "getProgress")) == 12000L, "resume keeps milliseconds");
            check(((Long) call(loaded, "getCid")) == 279786L, "resume keeps selected part");
            final Object activeHome = home;
            main(() -> call(activeHome, "screenResumed"));
            View resumeCard = host.findViewById(getTargetContext().getResources().getIdentifier("tv_resume", "id", getTargetContext().getPackageName()));
            check(resumeCard.getVisibility() == View.VISIBLE, "resume entry appears for saved item");
            check(resumeCard.getContentDescription().toString().contains("Continue watching persistence probe"), "resume entry describes video");
            state.getClass().getField("userId").setLong(state, -9140L);
            check(call(resume, "load") == null, "accounts do not share local history");
            main(() -> call(activeHome, "screenResumed"));
            check(resumeCard.getVisibility() == View.GONE, "empty account hides resume entry");
            state.getClass().getField("userId").setLong(state, -9139L);
            Object another = model(); call(another, "setCid", 999L); call(resume, "save", another, 119000L, 120000L);
            check(call(resume, "load") != null, "completing another part keeps previous resume");
            call(resume, "save", video, 119000L, 120000L);
            check(call(resume, "load") == null, "completed video clears its resume");
            call(video, "setCid", 0L); call(resume, "save", video, 12000L, 120000L);
            check(call(resume, "load") == null, "invalid playback identity is ignored");
            prefs.edit().putString("video_-9139", "broken JSON").commit();
            check(call(resume, "load") == null, "damaged local history is harmless");
            actualResume(home, list, resume);
            result.putString("stream", "HOME_UX_PASS " + checks + " assertions\n"); status = Activity.RESULT_OK;
        } catch (Throwable error) {
            result.putString("stream", "HOME_UX_FAIL " + stage + " " + android.util.Log.getStackTraceString(error));
        } finally {
            try {
                if (state != null) state.getClass().getField("userId").setLong(state, originalUser);
                prefs.edit().remove("video_-9139").remove("video_-9140").commit();
                if (home != null) { final Object target = home; main(() -> call(target, "screenResumed")); }
            } catch (Exception ignored) {}
            finish(status, result);
        }
    }
}
