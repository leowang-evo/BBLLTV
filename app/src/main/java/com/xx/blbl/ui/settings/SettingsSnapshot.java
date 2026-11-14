package com.xx.blbl.ui.settings;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.SettingModel;
import com.xx.blbl.model.StartPageEnum;
import com.xx.blbl.model.dm.DmScreenArea;
import com.xx.blbl.model.player.VideoCodecEnum;
import com.xx.blbl.model.video.quality.AudioQuality;
import com.xx.blbl.model.video.quality.VideoQuality;
import com.xx.blbl.platform.CacheMaintenance;

import java.util.List;
import java.util.Locale;

/** Reads the original preference keys and formats their current UI values. */
final class SettingsSnapshot {
    private SettingsSnapshot() {}

    static String version() {
        try {
            PackageInfo info =
                    AppController.instance()
                            .getPackageManager()
                            .getPackageInfo(AppController.instance().getPackageName(), 0);
            return info.versionName + " (" + info.versionCode + ")";
        } catch (android.content.pm.PackageManager.NameNotFoundException impossible) {
            throw new IllegalStateException(impossible);
        }
    }

    private static void set(List values, int index, String text) {
        ((SettingModel) values.get(index)).setInfo(text);
    }

    private static String bool(boolean value) {
        return value ? "开" : "关";
    }

    private static void array(List list, int row, String[] values, int index) {
        if (index >= 0 && index < values.length) set(list, row, values[index]);
    }

    static void populate(SettingsFragment page, View view) {
        SharedPreferences prefs = AppController.instance().getSharedPreferences("BLBL", 0);
        int limit = prefs.getInt("cacheLimit", 0);
        set(page.f9549R0, 1, limit == 0 ? "无限制" : limit + "MB");
        set(page.f9549R0, 2, version());
        for (StartPageEnum item : StartPageEnum.values())
            if (item.getOrder() == prefs.getInt("defaultStartPage", 1))
                set(page.f9549R0, 3, item.getShowName());
        array(
                page.f9549R0,
                4,
                view.getResources().getStringArray(R.array.image_qualities),
                prefs.getInt("imageQualityLevel", 1));
        array(
                page.f9549R0,
                5,
                view.getResources().getStringArray(R.array.themes),
                prefs.getInt("theme", 1));
        set(page.f9549R0, 6, bool(prefs.getBoolean("fullscreenEnable", true)));
        for (VideoQuality item : VideoQuality.values())
            if (item.getCode() == prefs.getInt("defaultResolution", 80))
                set(page.f9550S0, 0, item.getShowName());
        for (AudioQuality item : AudioQuality.values())
            if (item.getCode() == prefs.getInt("defaultAudioTrack", 30280))
                set(page.f9550S0, 1, item.getShowName());
        set(page.f9550S0, 2, String.valueOf(prefs.getFloat("defaultPlaySpeed", 1.0f)));
        for (ContinuationPlayEnum item : ContinuationPlayEnum.values())
            if (item.getOrder() == prefs.getInt("continuationPlayType", 0))
                set(page.f9550S0, 3, item.getShowName());
        set(page.f9550S0, 4, bool(prefs.getBoolean("exitPlayerAfterPlay", false)));
        set(page.f9550S0, 5, bool(prefs.getBoolean("showFfRe", false)));
        for (VideoCodecEnum item : VideoCodecEnum.values())
            if (item.getCode() == prefs.getInt("videoCodec", 1))
                set(page.f9550S0, 6, item.getShowName());
        set(page.f9550S0, 7, bool(prefs.getInt("showSubtitle", 0) == 1));
        set(page.f9550S0, 8, String.valueOf(prefs.getInt("subtitleTextSize", 45)));
        String[] playerBooleans = {
            "showDebug", "showVideoDetail", "showBottomProgressBar", "simpleKeyPress"
        };
        for (int index = 0; index < playerBooleans.length; index++)
            set(page.f9550S0, index + 9, bool(prefs.getBoolean(playerBooleans[index], false)));
        set(page.f9550S0, 13, String.valueOf(prefs.getInt("giveCoinNumber", 2)));
        set(page.f9550S0, 14, bool(prefs.getBoolean("showNextPrevious", false)));
        set(page.f9550S0, 15, bool(prefs.getBoolean("showDmSwitch", false)));
        set(page.f9550S0, 16, prefs.getInt("ffSeekSecond", 10) + "s");
        set(page.T0, 0, bool(prefs.getInt("dmSwitch", 1) == 1));
        set(page.T0, 1, String.format(Locale.CHINA, "%.2f", prefs.getFloat("dmAlpha", 1.0f)));
        set(page.T0, 2, String.valueOf(prefs.getInt("dmTextSize", 40)));
        for (DmScreenArea item : DmScreenArea.values())
            if (item.getArea() == prefs.getInt("dmScreenArea", 3))
                set(page.T0, 3, item.getShowName());
        set(page.T0, 4, String.valueOf(prefs.getInt("dmSpeed", 4)));
        set(page.T0, 5, bool(prefs.getBoolean("dmAllowTop", false)));
        set(page.T0, 6, bool(prefs.getBoolean("dmAllowBottom", false)));
        int weight = prefs.getInt("dmFilterWeight", 12);
        set(page.T0, 7, weight < 12 ? String.valueOf(weight) : "关");
        set(page.T0, 8, bool(prefs.getBoolean("allowVipColorfulDm", false)));
        page.f9551U0.clear();
        page.f9551U0.add(
                new SettingModel(
                        "CPU",
                        Build.HARDWARE
                                + " "
                                + android.text.TextUtils.join(" ", Build.SUPPORTED_ABIS)));
        page.f9551U0.add(new SettingModel("设备", Build.MANUFACTURER + " " + Build.MODEL));
        page.f9551U0.add(
                new SettingModel(
                        "系统", "Android " + Build.VERSION.RELEASE + " API" + Build.VERSION.SDK_INT));
        page.f9551U0.add(
                new SettingModel(
                        "分辨率", page.f9541G0.displayWidth + "x" + page.f9541G0.displayHeight));
        page.f9551U0.add(new SettingModel("网络", ""));
        page.f9551U0.add(
                new SettingModel("User-Agent(点击可以刷新)", page.f9542H0.getValue().getCurrentUA()));
        page.f9547P0.b(page.f9549R0);
        page.f9547P0.f10357c = page.f9552V0;
        refreshCache(page);
    }

    static void refreshCache(SettingsFragment page) {
        View requesting = page.screenView;
        page.runIo(
                () -> {
                    long bytes = CacheMaintenance.size(AppController.instance());
                    String text = String.format(Locale.CHINA, "%.2f MB", bytes / 1048576.0);
                    page.runOnMain(
                            () -> {
                                if (requesting != null
                                        && requesting == page.screenView
                                        && page.isAdded()) SettingsFragment.j0(page, 0, 0, text);
                            });
                });
    }
}
