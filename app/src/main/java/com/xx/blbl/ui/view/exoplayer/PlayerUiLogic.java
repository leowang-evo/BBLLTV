package com.xx.blbl.ui.view.exoplayer;

/** Original BBLL player preference translations, independent of player/Android implementation. */
public final class PlayerUiLogic {
    private PlayerUiLogic() {}

    public static String formatSeconds(long seconds) {
        if (seconds <= 0) return "00:00";
        long hours = seconds / 3600, minutes = (seconds % 3600) / 60, remainder = seconds % 60;
        return (hours == 0 ? "" : (hours < 10 ? "0" : "") + hours + ":")
                + (minutes < 10 ? "0" : "")
                + minutes
                + ":"
                + (remainder < 10 ? "0" : "")
                + remainder;
    }

    public static long danmakuDuration(int setting) {
        switch (setting) {
            case 1:
                return 7000;
            case 2:
                return 6000;
            case 3:
                return 5000;
            case 5:
                return 3200;
            case 6:
                return 2600;
            case 7:
                return 2200;
            case 8:
                return 1800;
            case 9:
                return 1200;
            default:
                return 3800;
        }
    }

    public static float danmakuArea(int setting) {
        switch (setting) {
            case -1:
                return .125f;
            case 0:
                return .16f;
            case 1:
                return .25f;
            case 3:
                return .5f;
            case 7:
                return .75f;
            default:
                return 1f;
        }
    }

    public static float danmakuTextScale(int setting) {
        float[] scales = {
            .55f, .6f, .65f, .7f, .75f, .8f, .85f, .9f, .95f, 1f, 1.14f, 1.3f, 1.4f, 1.5f, 1.6f,
            1.7f, 1.8f, 2f, 2.1f, 2.2f, 2.3f, 2.4f, 2.5f, 2.6f, 2.7f, 2.8f
        };
        return setting >= 30 && setting <= 55 ? scales[setting - 30] : 1.14f;
    }

    public static Boolean seekDirection(
            int playbackState, long position, long duration, int width, float x) {
        if (playbackState == 0 || playbackState == 1 || playbackState == 7) return null;
        if (position > 500 && x < width * .35d) return Boolean.FALSE;
        if (position >= duration || x <= width * .65d) return null;
        return Boolean.TRUE;
    }

    public static long clampSeek(long position, long duration) {
        if (position <= 0) return 0;
        return duration >= 0 && position >= duration ? duration : position;
    }
}
