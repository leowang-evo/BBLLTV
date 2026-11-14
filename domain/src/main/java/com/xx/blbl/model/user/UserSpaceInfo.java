package com.xx.blbl.model.user;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UserSpaceInfo implements Serializable {

    @SerializedName("mid")
    private long mid;

    @SerializedName("vip")
    private UserSpaceVipInfo vip;

    @SerializedName("face")
    private String face = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("top_photo")
    private String top_photo = "";

    @SerializedName("sign")
    private String sign = "";

    public final String getFace() {
        return this.face;
    }

    public final long getMid() {
        return this.mid;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSign() {
        return this.sign;
    }

    public final String getTop_photo() {
        return this.top_photo;
    }

    public final UserSpaceVipInfo getVip() {
        return this.vip;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setSign(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sign = str;
    }

    public final void setTop_photo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.top_photo = str;
    }

    public final void setVip(UserSpaceVipInfo userSpaceVipInfo) {
        this.vip = userSpaceVipInfo;
    }

    public String toString() {
        return "UserSpaceInfo(face='" + this.face + "', mid=" + this.mid + ", name='" + this.name + "', top_photo='" + this.top_photo + "', sign='" + this.sign + "', vip=" + this.vip + ')';
    }
}
