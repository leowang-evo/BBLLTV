package com.xx.blbl.state;

import android.content.SharedPreferences;

import com.xx.blbl.AppController;
import com.xx.blbl.platform.CacheFacade;

import java.io.File;

/** Recovered account/player state. Fields retain application names during UI migration. */
public final class AppState {
    public boolean signedIn, premiumMember;
    public String avatar, nickname, vipLabel;
    public long userId, defaultFavoriteId;
    public int userLevel, displayWidth = 1920, displayHeight = 1080, continuationMode, imageQuality;
    public float coinBalance;
    public final CacheFacade cache;
    public boolean showVideoDetails, simpleRemoteKeys;

    public AppState() {
        AppController application = AppController.instance();
        SharedPreferences p = application.getSharedPreferences("BLBL", 0);
        this.cache = new CacheFacade(new File(application.getCacheDir(), "BBLLCache"));
        this.avatar = p.getString("avatar", "");
        this.nickname = p.getString("nickname", "");
        this.userId = p.getLong("user_mid", 0);
        this.userLevel = p.getInt("user_level", 0);
        this.vipLabel = p.getString("user_vip", "");
        this.coinBalance = p.getFloat("coinMoney", 0);
        this.defaultFavoriteId = p.getLong("defaultFavoriteId", 0);
        this.continuationMode = p.getInt("continuationPlayType", 0);
        this.imageQuality = p.getInt("imageQualityLevel", 1);
        this.showVideoDetails = p.getBoolean("showVideoDetail", false);
        this.simpleRemoteKeys = p.getBoolean("simpleKeyPress", false);
    }

    public void clearAccount() {
        this.avatar = "";
        this.nickname = "";
        this.userId = 0;
        this.userLevel = 0;
        this.vipLabel = "";
        this.coinBalance = 0;
        this.signedIn = false;
        this.premiumMember = false;
        AppController.instance()
                .getSharedPreferences("BLBL", 0)
                .edit()
                .putLong("user_mid", 0)
                .putString("nickname", "")
                .putString("avatar", "")
                .putInt("user_level", 0)
                .putString("user_vip", "")
                .remove("cookies")
                .apply();
        AppController.clearCookies();
    }
}
