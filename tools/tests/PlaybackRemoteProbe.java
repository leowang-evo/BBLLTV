package com.damon.bblab.playbacktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/** Emulator-only probe of the real player fragment with a local, generated video. */
public final class PlaybackRemoteProbe extends Instrumentation {
    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        start();
    }

    @Override
    public void onStart() {
        Bundle result = new Bundle();
        Activity activity = null;
        File video = new File(getTargetContext().getFilesDir(), "bbll-playback-test.mp4");
        int status = Activity.RESULT_CANCELED;
        try {
            copyFixture(video);
            Context target = getTargetContext();
            target.getSharedPreferences("BLBL", 0).edit().putBoolean("userAgree", true).apply();
            Intent launch =
                    new Intent()
                            .setClassName(target, "com.xx.blbl.ui.MainActivity")
                            .addFlags(
                                    Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity = startActivitySync(launch);
            ClassLoader loader = target.getClassLoader();
            Class<?> playerFragment = loader.loadClass("com.xx.blbl.ui.playback.PlaybackFragment");
            Object fragment = playerFragment.getConstructor().newInstance();
            Class<?> fragmentType = loader.loadClass("androidx.fragment.app.Fragment");
            Class<?> infoType = loader.loadClass("com.xx.blbl.model.player.PlayInfoModel");
            Class<?> gsonType = loader.loadClass("com.google.gson.Gson");
            String json = "{\"durl\":[{\"url\":\"" + Uri.fromFile(video) + "\"}]}";
            Object info =
                    gsonType.getMethod("fromJson", String.class, Class.class)
                            .invoke(gsonType.getConstructor().newInstance(), json, infoType);
            Activity host = activity;
            runOnMainSync(
                    () -> {
                        try {
                            host.getClass()
                                    .getMethod("showScreen", fragmentType, String.class)
                                    .invoke(host, fragment, "fixturePlayback");
                            Object manager = host.getClass().getMethod("fragments").invoke(host);
                            loader.loadClass("androidx.fragment.app.FragmentManager")
                                    .getMethod("executePendingTransactions")
                                    .invoke(manager);
                            Object state = playerFragment.getField("coordinator").get(fragment);
                            state.getClass().getMethod("onPlayInfo", infoType).invoke(state, info);
                        } catch (ReflectiveOperationException error) {
                            throw new IllegalStateException(error);
                        }
                    });
            long[] observed = new long[3];
            Class<?> player = loader.loadClass("androidx.media3.common.Player");
            for (int poll = 0; poll < 40; poll++) {
                SystemClock.sleep(320);
                runOnMainSync(
                        () -> {
                            try {
                                Object engine = playerFragment.getField("player").get(fragment);
                                if (engine == null) return;
                                observed[0] =
                                        (Long)
                                                player.getMethod("getCurrentPosition")
                                                        .invoke(engine);
                                Object size = player.getMethod("getVideoSize").invoke(engine);
                                observed[1] = size.getClass().getField("width").getInt(size);
                                observed[2] = size.getClass().getField("height").getInt(size);
                            } catch (ReflectiveOperationException error) {
                                throw new IllegalStateException(error);
                            }
                        });
                if (observed[0] > 1200 && observed[1] == 640 && observed[2] == 360) break;
            }
            if (observed[0] <= 1200 || observed[1] != 640 || observed[2] != 360) {
                throw new AssertionError("Fixture did not decode and advance");
            }
            result.putString(
                    "stream",
                    "PASS local H.264 video decoded at 640x360; position=" + observed[0] + " ms");
            testRemote(activity, fragment, playerFragment, loader);
            result.putString(
                    "remote", "PASS " + checks + " remote control and settings assertions");
            status = Activity.RESULT_OK;
        } catch (Exception | AssertionError error) {
            result.putString("stream", "FAIL " + error);
        } finally {
            if (activity != null) runOnMainSync(activity::finish);
            video.delete();
            result.putString(
                    "stream",
                    result.getString("stream", "") + "\n" + result.getString("remote", ""));
            finish(status, result);
        }
    }

    private int checks;
    private Activity host;
    private android.view.View playerView, controlsView;
    private Class<?> viewType;

    private void check(boolean value, String message) {
        checks++;
        if (!value) throw new AssertionError(message + "; focus=" + focusName());
        android.util.Log.i("BBLL_REMOTE_TEST", "PASS " + message);
    }

    private String focusName() {
        String[] value = {"none"};
        runOnMainSync(
                () -> {
                    android.view.View focus = host.getCurrentFocus();
                    if (focus != null) {
                        try {
                            value[0] = focus.getResources().getResourceEntryName(focus.getId());
                        } catch (Exception ignored) {
                            value[0] = focus.getClass().getSimpleName();
                        }
                    }
                });
        return value[0];
    }

    private void focusIs(String name, String message) {
        check(name.equals(focusName()), message);
        if (name.startsWith("button_") || name.equals("exo_settings")) {
            boolean[] valid = new boolean[2];
            runOnMainSync(() -> {
                android.view.View focused = host.getCurrentFocus();
                android.widget.TextView caption = controlsView.findViewById(host.getResources()
                        .getIdentifier("tv_player_action_text", "id", host.getPackageName()));
                valid[0] = focused != null && caption != null
                        && caption.getText().toString().contentEquals(focused.getContentDescription());
                android.graphics.Rect rect = new android.graphics.Rect();
                valid[1] = focused != null && focused.getGlobalVisibleRect(rect)
                        && rect.width() >= focused.getWidth() && rect.height() >= focused.getHeight();
            });
            check(valid[0], "Focused action caption follows " + name);
            check(valid[1], "Focused action fully fits screen: " + name);
        }
    }

    private void firstChoice(Object settings, ClassLoader loader) throws Exception {
        java.lang.reflect.Field field = settings.getClass().getDeclaredField("recyclerView");
        field.setAccessible(true);
        Object recycler = field.get(settings);
        runOnMainSync(() -> {
            try {
                loader.loadClass("com.xx.blbl.platform.RecyclerFocus")
                        .getMethod("request", loader.loadClass("androidx.recyclerview.widget.RecyclerView"), int.class)
                        .invoke(null, recycler, 1);
            } catch (Exception e) { throw new IllegalStateException(e); }
        });
        SystemClock.sleep(200);
    }

    private void key(int key) {
        sendKeyDownUpSync(key);
        SystemClock.sleep(100);
    }

    private void method(String name, boolean value) {
        runOnMainSync(
                () -> {
                    try {
                        viewType.getMethod(name, boolean.class).invoke(playerView, value);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
    }

    private String focusedText() {
        String[] value = {""};
        runOnMainSync(
                () -> {
                    android.view.View focused = host.getCurrentFocus();
                    if (focused instanceof android.view.ViewGroup) {
                        android.widget.TextView title =
                                focused.findViewById(
                                        host.getResources()
                                                .getIdentifier(
                                                        "text_view_primary",
                                                        "id",
                                                        host.getPackageName()));
                        if (title != null) value[0] = title.getText().toString();
                    }
                });
        return value[0];
    }

    private void testRemote(
            Activity activity, Object fragment, Class<?> fragmentType, ClassLoader loader)
            throws Exception {
        // Prior mouse-driven tests leave Android in touch mode; remote keys use non-touch mode.
        setInTouchMode(false);
        host = activity;
        playerView = (android.view.View) fragmentType.getField("playerView").get(fragment);
        viewType = playerView.getClass();
        controlsView =
                (android.view.View) viewType.getMethod("getAppController").invoke(playerView);
        Object engine = fragmentType.getField("player").get(fragment);
        android.view.View indicator = controlsView.findViewById(host.getResources()
                .getIdentifier("loading_progress_bar", "id", host.getPackageName()));
        check(indicator != null && indicator.getVisibility() == android.view.View.GONE,
                "Ready local playback hides its buffering spinner");
        runOnMainSync(
                () -> {
                    try {
                        loader.loadClass("androidx.media3.common.Player")
                                .getMethod("pause")
                                .invoke(engine);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        SystemClock.sleep(500);
        runOnMainSync(
                () -> {
                    try {
                        Object controls = viewType.getMethod("getAppController").invoke(playerView);
                        controls.getClass().getMethod("hideImmediately").invoke(controls);
                        playerView.requestFocus();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        SystemClock.sleep(600);
        key(android.view.KeyEvent.KEYCODE_DPAD_DOWN);
        SystemClock.sleep(600);
        runOnMainSync(
                () -> {
                    android.view.View play =
                            playerView.findViewById(
                                    host.getResources()
                                            .getIdentifier(
                                                    "button_play", "id", host.getPackageName()));
                    android.util.Log.i(
                            "BBLL_REMOTE_TEST",
                            "Initial controls: playerShown="
                                    + playerView.isShown()
                                    + " controllerVis="
                                    + controlsView.getVisibility()
                                    + " controllerShown="
                                    + controlsView.isShown()
                                    + " playerFocused="
                                    + playerView.isFocused()
                                    + " shown="
                                    + play.isShown()
                                    + " focusable="
                                    + play.isFocusable()
                                    + " enabled="
                                    + play.isEnabled()
                                    + " vis="
                                    + play.getVisibility()
                                    + " window="
                                    + play.hasWindowFocus()
                                    + " touch="
                                    + play.isInTouchMode());
                });
        focusIs("button_play", "Down opens controls and focuses play");
        java.util.List<String> buttons = new java.util.ArrayList<>();
        runOnMainSync(
                () -> {
                    for (String name :
                            new String[] {
                                "button_play",
                                "button_previous",
                                "button_next",
                                "button_rewind",
                                "button_fast_forward",
                                "button_dm_switch",
                                "exo_settings",
                                "button_choose_episode",
                                "button_more",
                                "button_up_info",
                                "button_subtitle",
                                "button_related",
                                "button_repeat",
                                "button_live_settings",
                                "button_restart",
                                "button_close"
                            }) {
                        int id =
                                host.getResources()
                                        .getIdentifier(name, "id", host.getPackageName());
                        android.view.View view = controlsView.findViewById(id);
                        if (view != null
                                && view.isShown()
                                && view.isEnabled()
                                && view.isFocusable()) buttons.add(name);
                    }
                });
        android.util.Log.i("BBLL_REMOTE_TEST", "Buttons: " + buttons);
        for (int i = 1; i < buttons.size(); i++) {
            key(22);
            focusIs(buttons.get(i), "Right to " + buttons.get(i));
        }
        for (int i = buttons.size() - 2; i >= 0; i--) {
            key(21);
            focusIs(buttons.get(i), "Left to " + buttons.get(i));
        }
        key(19);
        focusIs("exo_progress", "Up enters time bar");
        key(20);
        focusIs("button_play", "Down returns from time bar");
        key(23);
        boolean[] playing = {false};
        runOnMainSync(
                () -> {
                    try {
                        playing[0] =
                                (Boolean)
                                        loader.loadClass("androidx.media3.common.Player")
                                                .getMethod("getPlayWhenReady")
                                                .invoke(engine);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        check(playing[0], "Center resumes video");
        focusIs("button_play", "Play caption updates without moving focus");
        runOnMainSync(
                () -> {
                    try {
                        viewType.getMethod("setControllerShowTimeoutMs", int.class)
                                .invoke(playerView, 1000);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        SystemClock.sleep(3500);
        boolean[] fullyVisible = {true};
        runOnMainSync(
                () -> {
                    try {
                        fullyVisible[0] =
                                (Boolean)
                                        viewType.getMethod("isControllerFullyVisible")
                                                .invoke(playerView);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        check(!fullyVisible[0], "Resuming restores the playing controls auto-hide timeout");
        runOnMainSync(
                () -> {
                    try {
                        viewType.getMethod("setControllerShowTimeoutMs", int.class)
                                .invoke(playerView, 5000);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        key(20);
        SystemClock.sleep(400);
        focusIs("button_play", "Down restores focus after playing controls auto-hide");
        key(23);
        runOnMainSync(
                () -> {
                    try {
                        playing[0] =
                                (Boolean)
                                        loader.loadClass("androidx.media3.common.Player")
                                                .getMethod("getPlayWhenReady")
                                                .invoke(engine);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        check(!playing[0], "Center pauses video");
        focusIs("button_play", "Pause caption updates without moving focus");
        SystemClock.sleep(5500);
        check(controlsView.isShown(), "Paused controls remain available beyond auto-hide timeout");
        key(82);
        SystemClock.sleep(350);
        focusIs("click_view", "Menu enters first settings row");
        key(20);
        focusIs("click_view", "Down selects speed row");
        key(23);
        focusIs("click_view", "Speed submenu focuses an option");
        key(20);
        focusIs("click_view", "Down moves inside speed submenu");
        key(4);
        focusIs("click_view", "Back restores settings options");
        key(4);
        SystemClock.sleep(350);
        check(!"click_view".equals(focusName()), "Back closes settings");
        Object settings =
                playerView.findViewById(
                        host.getResources()
                                .getIdentifier("setting_view", "id", host.getPackageName()));
        runOnMainSync(
                () -> {
                    try {
                        for (String[] pair :
                                new String[][] {
                                    {"setVideoCodec", "com.xx.blbl.model.player.VideoCodecEnum"},
                                    {
                                        "setAudiosSelect",
                                        "com.xx.blbl.model.video.quality.AudioQuality"
                                    },
                                    {"setQualities", "com.xx.blbl.model.video.quality.VideoQuality"}
                                }) {
                            Object[] values = loader.loadClass(pair[1]).getEnumConstants();
                            viewType.getMethod(pair[0], java.util.List.class)
                                    .invoke(playerView, java.util.Arrays.asList(values));
                        }
                        java.util.List<Object> subtitles = new java.util.ArrayList<>();
                        Class<?> subtitle =
                                loader.loadClass("com.xx.blbl.model.subtitle.SubtitleInfoModel");
                        for (String label : new String[] {"关闭", "简体中文"}) {
                            Object item = subtitle.getConstructor().newInstance();
                            subtitle.getMethod("setLan_doc", String.class).invoke(item, label);
                            subtitles.add(item);
                        }
                        viewType.getMethod("setSubtitles", java.util.List.class)
                                .invoke(playerView, subtitles);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        java.util.List<String> selections = new java.util.ArrayList<>();
        Class<?> changeType =
                loader.loadClass("com.xx.blbl.ui.view.exoplayer.OnPlayerSettingChange");
        Object originalChange =
                settings.getClass().getMethod("getOnPlayerSettingChange").invoke(settings);
        Object recorder =
                java.lang.reflect.Proxy.newProxyInstance(
                        loader,
                        new Class<?>[] {changeType},
                        (proxy, called, args) -> {
                            selections.add(called.getName() + ":" + args[0]);
                            return null;
                        });
        runOnMainSync(
                () -> {
                    try {
                        settings.getClass()
                                .getMethod("setOnPlayerSettingChange", changeType)
                                .invoke(settings, recorder);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        String[] headers = {
            "video_quality",
            "playSpeed",
            "subtitle",
            "video_codec",
            "audioTrack",
            "screen_ratio",
            "dm_setting"
        };
        for (int row = 1; row <= 7; row++) {
            method("showHideSettingView", true);
            SystemClock.sleep(350);
            for (int down = 1; down < row; down++) key(20);
            String parent = focusedText();
            key(23);
            SystemClock.sleep(150);
            final String[] title = {""};
            runOnMainSync(
                    () -> {
                        android.widget.TextView heading =
                                ((android.view.View) settings)
                                        .findViewById(
                                                host.getResources()
                                                        .getIdentifier(
                                                                "textView",
                                                                "id",
                                                                host.getPackageName()));
                        if (heading != null) title[0] = heading.getText().toString();
                    });
            String expected =
                    host.getString(
                            host.getResources()
                                    .getIdentifier(
                                            headers[row - 1], "string", host.getPackageName()));
            check(
                    expected.equals(title[0]),
                    "Remote opens actual "
                            + expected
                            + " submenu (parent="
                            + parent
                            + ", heading="
                            + title[0]
                            + ")");
            focusIs("click_view", expected + " initial option");
            if (row <= 6) firstChoice(settings, loader);
            String first = focusedText();
            key(20);
            focusIs("click_view", expected + " moves down");
            check(!first.equals(focusedText()), expected + " changes focused row");
            key(19);
            check(first.equals(focusedText()), expected + " moves up to original row");
            if (row <= 6) {
                key(20);
                key(23);
                SystemClock.sleep(150);
                if (row == 1 || row == 3 || row == 4 || row == 5) {
                    String callback =
                            row == 1
                                    ? "onVideoQualityChange"
                                    : row == 3
                                            ? "onSubtitleChange"
                                            : row == 4 ? "onVideoCodecChange" : "onAudioChange";
                    check(
                            selections.contains(callback + ":1"),
                            expected + " dispatches selected index once");
                }
                if (row == 2) {
                    float[] speed = {0};
                    runOnMainSync(
                            () -> {
                                try {
                                    Object parameters =
                                            loader.loadClass("androidx.media3.common.Player")
                                                    .getMethod("getPlaybackParameters")
                                                    .invoke(engine);
                                    speed[0] =
                                            parameters
                                                    .getClass()
                                                    .getField("speed")
                                                    .getFloat(parameters);
                                } catch (Exception e) {
                                    throw new IllegalStateException(e);
                                }
                            });
                    check(speed[0] == .5f, "Speed selection reaches actual player");
                }
                if (row == 6) {
                    int ratio =
                            (Integer)
                                    settings.getClass()
                                            .getMethod("getCurrentScreenRatio")
                                            .invoke(settings);
                    check(ratio == 1, "Aspect ratio selection updates state");
                }
            } else {
                // Boolean settings toggle in place; numeric settings still open choices.
                for (int dmRow = 1; dmRow <= 7; dmRow++) {
                    if (dmRow > 1) key(20);
                    String dmParent = focusedText();
                    key(23);
                    focusIs("click_view", "Danmaku setting " + dmRow + " receives focus");
                    if (dmRow == 1 || dmRow == 6 || dmRow == 7) {
                        check(dmParent.equals(focusedText()), "Boolean switch stays on its launching row");
                        key(23); // Restore the original preference.
                        check(dmParent.equals(focusedText()), "Boolean switch can be restored without a submenu");
                    } else {
                        firstChoice(settings, loader);
                        key(20);
                        focusIs("click_view", "Danmaku setting " + dmRow + " moves down");
                        key(4);
                        check(dmParent.equals(focusedText()), "Danmaku setting " + dmRow + " returns to its row");
                    }
                }
                key(4);
            }
            check(parent.equals(focusedText()), expected + " returns to launching row");
            key(4);
            SystemClock.sleep(350);
            check(!"click_view".equals(focusName()), expected + " closes back to player");
        }
        check(selections.size() == 4, "Track selections fire exactly four callbacks");
        runOnMainSync(
                () -> {
                    try {
                        settings.getClass()
                                .getMethod("setOnPlayerSettingChange", changeType)
                                .invoke(settings, originalChange);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        method("showHideSettingView", true);
        SystemClock.sleep(400);
        runOnMainSync(
                () -> {
                    try {
                        settings.getClass().getMethod("showPlaybackSpeed").invoke(settings);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        SystemClock.sleep(150);
        sendKeyDownUpSync(4);
        sendKeyDownUpSync(4);
        SystemClock.sleep(400);
        check(
                !(Boolean) settings.getClass().getMethod("isShowing").invoke(settings),
                "Two fast Back presses close a submenu and the settings panel");
        check(
                (Boolean)
                        loader.loadClass("androidx.fragment.app.Fragment")
                                .getMethod("isAdded")
                                .invoke(fragment),
                "Two fast Back presses keep the playback fragment attached");
        // Exercise every optional control, including buttons hidden by the default preferences.
        for (String toggle :
                new String[] {
                    "showHideNextPrevious",
                    "showHideFfRe",
                    "showHideDmSwitchButton",
                    "setShowHideOwnerInfo",
                    "showHideSubtitleButton",
                    "showHideLiveSettingButton"
                }) method(toggle, true);
        runOnMainSync(
                () -> {
                    try {
                        controlsView
                                .getClass()
                                .getMethod("requestPlayPauseFocus")
                                .invoke(controlsView);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        runOnMainSync(
                () -> {
                    try {
                        viewType.getMethod("showController").invoke(playerView);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                    for (String name :
                            new String[] {
                                "button_previous",
                                "button_next",
                                "button_rewind",
                                "button_fast_forward",
                                "button_dm_switch",
                                "exo_settings",
                                "button_choose_episode",
                                "button_more",
                                "button_up_info",
                                "button_subtitle",
                                "button_related",
                                "button_repeat",
                                "button_live_settings",
                                "button_restart",
                                "button_close"
                            }) {
                        android.view.View button =
                                controlsView.findViewById(
                                        host.getResources()
                                                .getIdentifier(name, "id", host.getPackageName()));
                        button.setEnabled(true);
                        button.setFocusable(true);
                        if (name.equals("button_restart")) button.setVisibility(android.view.View.VISIBLE);
                    }
                });
        SystemClock.sleep(500);
        String[] all = {
            "button_play",
            "button_previous",
            "button_next",
            "button_rewind",
            "button_fast_forward",
            "button_dm_switch",
            "exo_settings",
            "button_choose_episode",
            "button_more",
            "button_up_info",
            "button_subtitle",
            "button_related",
            "button_repeat",
            "button_live_settings",
            "button_restart",
            "button_close"
        };
        for (int index = 1; index < all.length; index++) {
            key(22);
            focusIs(all[index], "Optional control right to " + all[index]);
        }
        for (int index = all.length - 2; index >= 0; index--) {
            key(21);
            focusIs(all[index], "Optional control left to " + all[index]);
        }
        // Mouse/touch can leave focus on the RecyclerView itself rather than a row.
        method("showHideSettingView", true);
        SystemClock.sleep(400);
        runOnMainSync(
                () -> {
                    android.view.View list =
                            ((android.view.View) settings)
                                    .findViewById(
                                            host.getResources()
                                                    .getIdentifier(
                                                            "recyclerView",
                                                            "id",
                                                            host.getPackageName()));
                    list.setFocusableInTouchMode(true);
                    list.requestFocus();
                    try {
                        Object holder =
                                list.getClass()
                                        .getMethod("findViewHolderForAdapterPosition", int.class)
                                        .invoke(list, 1);
                        android.view.View row =
                                (android.view.View)
                                        loader.loadClass(
                                                        "androidx.recyclerview.widget.RecyclerView$ViewHolder")
                                                .getField("itemView")
                                                .get(holder);
                        row.findViewById(
                                        host.getResources()
                                                .getIdentifier(
                                                        "click_view", "id", host.getPackageName()))
                                .performClick();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                    list.setFocusableInTouchMode(false);
                });
        SystemClock.sleep(150);
        focusIs("click_view", "Container focus can open a settings option without crashing");
        key(4);
        check(!focusedText().isEmpty(), "Container focus returns to launching option");
        method("showHideSettingView", false);
        SystemClock.sleep(350);
        // An invalid subtitle index must not crash while the available tracks change.
        runOnMainSync(
                () -> {
                    try {
                        settings.getClass()
                                .getMethod("setCurrentSubtitlePosition", int.class)
                                .invoke(settings, -1);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        method("showHideSettingView", true);
        SystemClock.sleep(350);
        runOnMainSync(
                () -> {
                    try {
                        settings.getClass().getMethod("showSubtitles").invoke(settings);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        SystemClock.sleep(150);
        focusIs("click_view", "Invalid subtitle selection remains navigable");
        method("showHideSettingView", false);
        SystemClock.sleep(350);
    }

    private void copyFixture(File target) throws Exception {
        try (InputStream input = getContext().getAssets().open("sample.mp4");
                FileOutputStream output = new FileOutputStream(target)) {
            byte[] buffer = new byte[16384];
            int count;
            while ((count = input.read(buffer)) != -1) output.write(buffer, 0, count);
        }
    }
}
