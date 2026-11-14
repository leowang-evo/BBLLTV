package com.xx.blbl.ui.view.exoplayer;

import java.util.Objects;

/** Boundary fixtures for the source-owned player preference and remote-seek decisions. */
public final class PlayerUiLogicTest {
    private static int assertions;

    private static void same(Object expected, Object actual) {
        assertions++;
        if (!Objects.equals(expected, actual))
            throw new AssertionError("expected=" + expected + "; actual=" + actual);
    }

    public static void main(String[] args) {
        String[] times = {
            "00:00",
            "00:00",
            "00:01",
            "00:59",
            "01:00",
            "59:59",
            "01:00:00",
            "01:01:01",
            "10:00:00",
            "100:00:00"
        };
        long[] seconds = {-1, 0, 1, 59, 60, 3599, 3600, 3661, 36000, 360000};
        for (int i = 0; i < seconds.length; i++)
            same(times[i], PlayerUiLogic.formatSeconds(seconds[i]));
        long[] durations = {7000, 6000, 5000, 3800, 3200, 2600, 2200, 1800, 1200};
        for (int i = 0; i < durations.length; i++)
            same(durations[i], PlayerUiLogic.danmakuDuration(i + 1));
        same(3800L, PlayerUiLogic.danmakuDuration(0));
        same(3800L, PlayerUiLogic.danmakuDuration(10));
        float[] scales = {
            .55f, .6f, .65f, .7f, .75f, .8f, .85f, .9f, .95f, 1f, 1.14f, 1.3f, 1.4f, 1.5f, 1.6f,
            1.7f, 1.8f, 2f, 2.1f, 2.2f, 2.3f, 2.4f, 2.5f, 2.6f, 2.7f, 2.8f
        };
        for (int i = 0; i < scales.length; i++)
            same(scales[i], PlayerUiLogic.danmakuTextScale(i + 30));
        same(1.14f, PlayerUiLogic.danmakuTextScale(0));
        same(1.14f, PlayerUiLogic.danmakuTextScale(56));
        int[] areas = {-1, 0, 1, 3, 7, 9};
        float[] fraction = {.125f, .16f, .25f, .5f, .75f, 1f};
        for (int i = 0; i < areas.length; i++)
            same(fraction[i], PlayerUiLogic.danmakuArea(areas[i]));
        same(null, PlayerUiLogic.seekDirection(1, 1000, 10000, 1000, 100));
        same(null, PlayerUiLogic.seekDirection(0, 1000, 10000, 1000, 900));
        same(null, PlayerUiLogic.seekDirection(7, 1000, 10000, 1000, 900));
        same(Boolean.FALSE, PlayerUiLogic.seekDirection(3, 501, 10000, 1000, 349));
        same(null, PlayerUiLogic.seekDirection(3, 500, 10000, 1000, 349));
        same(null, PlayerUiLogic.seekDirection(3, 1000, 10000, 1000, 350));
        same(null, PlayerUiLogic.seekDirection(3, 1000, 10000, 1000, 650));
        same(Boolean.TRUE, PlayerUiLogic.seekDirection(3, 1000, 10000, 1000, 651));
        same(null, PlayerUiLogic.seekDirection(4, 10000, 10000, 1000, 900));
        same(Boolean.FALSE, PlayerUiLogic.seekDirection(4, 10000, 10000, 1000, 100));
        same(Boolean.FALSE, PlayerUiLogic.seekDirection(3, 1000, -9223372036854775807L, 1000, 100));
        same(null, PlayerUiLogic.seekDirection(3, 1000, -9223372036854775807L, 1000, 900));
        same(0L, PlayerUiLogic.clampSeek(-100, 10000));
        same(0L, PlayerUiLogic.clampSeek(0, 10000));
        same(10000L, PlayerUiLogic.clampSeek(20000, 10000));
        same(5000L, PlayerUiLogic.clampSeek(5000, 10000));
        same(5000L, PlayerUiLogic.clampSeek(5000, -9223372036854775807L));
        System.out.println(
                "PLAYER_UI_LOGIC_OK assertions="
                        + assertions
                        + " apk_inputs=0 android_runtime_required=false");
    }
}
