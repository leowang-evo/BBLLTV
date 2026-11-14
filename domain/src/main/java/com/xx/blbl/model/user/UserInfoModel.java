package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UserInfoModel implements Serializable {

    @SerializedName("mid")
    private String mid = "";

    @SerializedName("uname")
    private String uname = "";

    @SerializedName("userid")
    private String userid = "";

    @SerializedName("sign")
    private String sign = "";

    @SerializedName("birthday")
    private String birthday = "";

    @SerializedName("sex")
    private String sex = "";

    @SerializedName("rank")
    private String rank = "";

    @SerializedName("face")
    private String face = "";

    public final String getBirthday() {
        return this.birthday;
    }

    public final String getFace() {
        return this.face;
    }

    public final String getMid() {
        return this.mid;
    }

    public final String getRank() {
        return this.rank;
    }

    public final String getSex() {
        return this.sex;
    }

    public final String getSign() {
        return this.sign;
    }

    public final String getUname() {
        return this.uname;
    }

    public final String getUserid() {
        return this.userid;
    }

    public final void setBirthday(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.birthday = str;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setMid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mid = str;
    }

    public final void setRank(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.rank = str;
    }

    public final void setSex(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sex = str;
    }

    public final void setSign(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sign = str;
    }

    public final void setUname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uname = str;
    }

    public final void setUserid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userid = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UserInfoModel(mid='");
        sb.append(this.mid);
        sb.append("', uname='");
        sb.append(this.uname);
        sb.append("', userid='");
        sb.append(this.userid);
        sb.append("', sign='");
        sb.append(this.sign);
        sb.append("', birthday='");
        sb.append(this.birthday);
        sb.append("', sex='");
        sb.append(this.sex);
        sb.append("', rank='");
        sb.append(this.rank);
        sb.append("', face='");
        return DomainSupport.appendString(sb, this.face, "')");
    }
}
