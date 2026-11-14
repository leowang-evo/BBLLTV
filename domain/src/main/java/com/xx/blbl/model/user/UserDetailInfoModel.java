package com.xx.blbl.model.user;

import com.xx.blbl.model.WbiImage;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UserDetailInfoModel implements Serializable {

    @SerializedName("isLogin")
    private boolean isLogin;

    @SerializedName("level_info")
    private LevelInfoModel level_info;

    @SerializedName("mid")
    private long mid;

    @SerializedName("money")
    private float money;

    @SerializedName("vipStatus")
    private int vipStatus;

    @SerializedName("vipType")
    private int vipType;

    @SerializedName("vip_label")
    private VipInfoModel vip_label;

    @SerializedName("wallet")
    private WalletModel wallet;

    @SerializedName("wbi_img")
    private WbiImage wbi_img;

    @SerializedName("face")
    private String face = "";

    @SerializedName("uname")
    private String uname = "";

    @SerializedName("vip_nickname_color")
    private String vip_nickname_color = "";

    public final String getFace() {
        return this.face;
    }

    public final LevelInfoModel getLevel_info() {
        return this.level_info;
    }

    public final long getMid() {
        return this.mid;
    }

    public final float getMoney() {
        return this.money;
    }

    public final String getUname() {
        return this.uname;
    }

    public final int getVipStatus() {
        return this.vipStatus;
    }

    public final int getVipType() {
        return this.vipType;
    }

    public final VipInfoModel getVip_label() {
        return this.vip_label;
    }

    public final String getVip_nickname_color() {
        return this.vip_nickname_color;
    }

    public final WalletModel getWallet() {
        return this.wallet;
    }

    public final WbiImage getWbi_img() {
        return this.wbi_img;
    }

    public final boolean isLogin() {
        return this.isLogin;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setLevel_info(LevelInfoModel levelInfoModel) {
        this.level_info = levelInfoModel;
    }

    public final void setLogin(boolean z7) {
        this.isLogin = z7;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public final void setMoney(float f7) {
        this.money = f7;
    }

    public final void setUname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uname = str;
    }

    public final void setVipStatus(int i4) {
        this.vipStatus = i4;
    }

    public final void setVipType(int i4) {
        this.vipType = i4;
    }

    public final void setVip_label(VipInfoModel vipInfoModel) {
        this.vip_label = vipInfoModel;
    }

    public final void setVip_nickname_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vip_nickname_color = str;
    }

    public final void setWallet(WalletModel walletModel) {
        this.wallet = walletModel;
    }

    public final void setWbi_img(WbiImage wbiImage) {
        this.wbi_img = wbiImage;
    }

    public String toString() {
        return "UserDetailInfoModel(isLogin=" + this.isLogin + ", face='" + this.face + "', level_info=" + this.level_info + ", mid=" + this.mid + ", money=" + this.money + ", uname='" + this.uname + "', vipStatus=" + this.vipStatus + ", vipType=" + this.vipType + ", vip_label=" + this.vip_label + ", vip_nickname_color='" + this.vip_nickname_color + "', wallet=" + this.wallet + ", wbi_img=" + this.wbi_img + ')';
    }
}
