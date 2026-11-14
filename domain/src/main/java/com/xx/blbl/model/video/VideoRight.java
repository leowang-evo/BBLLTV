package com.xx.blbl.model.video;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoRight implements Serializable {

    @SerializedName("autoplay")
    private int autoplay;

    @SerializedName("hd5")
    private int hd5;

    @SerializedName("is_360")
    private int is_360;

    @SerializedName("is_cooperation")
    private int is_cooperation;

    @SerializedName("is_stein_gate")
    private int is_stein_gate;

    @SerializedName("movie")
    private int movie;

    @SerializedName("no_reprint")
    private int no_reprint;

    @SerializedName("pay")
    private int pay;

    @SerializedName("ugc_pay")
    private int ugc_pay;

    public final int getAutoplay() {
        return this.autoplay;
    }

    public final int getHd5() {
        return this.hd5;
    }

    public final int getMovie() {
        return this.movie;
    }

    public final int getNo_reprint() {
        return this.no_reprint;
    }

    public final int getPay() {
        return this.pay;
    }

    public final int getUgc_pay() {
        return this.ugc_pay;
    }

    public final int is_360() {
        return this.is_360;
    }

    public final int is_cooperation() {
        return this.is_cooperation;
    }

    public final int is_stein_gate() {
        return this.is_stein_gate;
    }

    public final void setAutoplay(int i4) {
        this.autoplay = i4;
    }

    public final void setHd5(int i4) {
        this.hd5 = i4;
    }

    public final void setMovie(int i4) {
        this.movie = i4;
    }

    public final void setNo_reprint(int i4) {
        this.no_reprint = i4;
    }

    public final void setPay(int i4) {
        this.pay = i4;
    }

    public final void setUgc_pay(int i4) {
        this.ugc_pay = i4;
    }

    public final void set_360(int i4) {
        this.is_360 = i4;
    }

    public final void set_cooperation(int i4) {
        this.is_cooperation = i4;
    }

    public final void set_stein_gate(int i4) {
        this.is_stein_gate = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VideoRight(movie=");
        sb.append(this.movie);
        sb.append(", hd5=");
        sb.append(this.hd5);
        sb.append(", pay=");
        sb.append(this.pay);
        sb.append(", no_reprint=");
        sb.append(this.no_reprint);
        sb.append(", autoplay=");
        sb.append(this.autoplay);
        sb.append(", ugc_pay=");
        sb.append(this.ugc_pay);
        sb.append(", is_cooperation=");
        sb.append(this.is_cooperation);
        sb.append(", is_stein_gate=");
        sb.append(this.is_stein_gate);
        sb.append(", is_360=");
        return DomainSupport.appendInt(sb, this.is_360, ')');
    }
}
