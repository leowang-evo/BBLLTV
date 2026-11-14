package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FollowingModel implements Serializable {

    @SerializedName("attribute")
    private int attribute;

    @SerializedName("mid")
    private long mid;

    @SerializedName("mtime")
    private long mtime;

    @SerializedName("special")
    private int special;

    @SerializedName("tag")
    private int tag;

    @SerializedName("uname")
    private String uname = "";

    @SerializedName("face")
    private String face = "";

    @SerializedName("sign")
    private String sign = "";

    public final int getAttribute() {
        return this.attribute;
    }

    public final String getFace() {
        return this.face;
    }

    public final long getMid() {
        return this.mid;
    }

    public final long getMtime() {
        return this.mtime;
    }

    public final String getSign() {
        return this.sign;
    }

    public final int getSpecial() {
        return this.special;
    }

    public final int getTag() {
        return this.tag;
    }

    public final String getUname() {
        return this.uname;
    }

    public final void setAttribute(int i4) {
        this.attribute = i4;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public final void setMtime(long j7) {
        this.mtime = j7;
    }

    public final void setSign(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sign = str;
    }

    public final void setSpecial(int i4) {
        this.special = i4;
    }

    public final void setTag(int i4) {
        this.tag = i4;
    }

    public final void setUname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uname = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FollowingModel(mid=");
        sb.append(this.mid);
        sb.append(", attribute=");
        sb.append(this.attribute);
        sb.append(", mtime=");
        sb.append(this.mtime);
        sb.append(", tag=");
        sb.append(this.tag);
        sb.append(", special=");
        sb.append(this.special);
        sb.append(", uname='");
        sb.append(this.uname);
        sb.append("', face='");
        sb.append(this.face);
        sb.append("', sign='");
        return DomainSupport.appendString(sb, this.sign, "')");
    }
}
