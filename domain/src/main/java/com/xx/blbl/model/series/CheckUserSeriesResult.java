package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class CheckUserSeriesResult implements Serializable {

    @SerializedName("area_limit")
    private int area_limit;

    @SerializedName("ban_area_show")
    private int ban_area_show;

    @SerializedName("follow")
    private int follow;

    @SerializedName("follow_status")
    private int follow_status;

    @SerializedName("login")
    private int login;

    @SerializedName("pay")
    private int pay;

    public final int getArea_limit() {
        return this.area_limit;
    }

    public final int getBan_area_show() {
        return this.ban_area_show;
    }

    public final int getFollow() {
        return this.follow;
    }

    public final int getFollow_status() {
        return this.follow_status;
    }

    public final int getLogin() {
        return this.login;
    }

    public final int getPay() {
        return this.pay;
    }

    public final void setArea_limit(int i4) {
        this.area_limit = i4;
    }

    public final void setBan_area_show(int i4) {
        this.ban_area_show = i4;
    }

    public final void setFollow(int i4) {
        this.follow = i4;
    }

    public final void setFollow_status(int i4) {
        this.follow_status = i4;
    }

    public final void setLogin(int i4) {
        this.login = i4;
    }

    public final void setPay(int i4) {
        this.pay = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CheckUserSeriesResult(area_limit=");
        sb.append(this.area_limit);
        sb.append(", ban_area_show=");
        sb.append(this.ban_area_show);
        sb.append(", follow=");
        sb.append(this.follow);
        sb.append(", follow_status=");
        sb.append(this.follow_status);
        sb.append(", login=");
        sb.append(this.login);
        sb.append(", pay=");
        return DomainSupport.appendInt(sb, this.pay, ')');
    }
}
