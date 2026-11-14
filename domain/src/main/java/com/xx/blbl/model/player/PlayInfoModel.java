package com.xx.blbl.model.player;

import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class PlayInfoModel implements Serializable {

    @SerializedName("accept_description")
    private ArrayList<String> accept_description;

    @SerializedName("accept_quality")
    private ArrayList<Integer> accept_quality;

    @SerializedName("dash")
    private DashInfoModel dash;

    @SerializedName("durl")
    private ArrayList<VideoSegmentModel> durl;

    @SerializedName("v_voucher")
    private String verificationVoucher;

    public boolean requiresVerification() {
        return verificationVoucher != null && !verificationVoucher.trim().isEmpty();
    }

    @SerializedName("format")
    private String format = "";

    @SerializedName("quality")
    private int quality;

    @SerializedName("support_formats")
    private ArrayList<SupportFormatModel> support_formats;

    public final ArrayList<String> getAccept_description() {
        return this.accept_description;
    }

    public final ArrayList<Integer> getAccept_quality() {
        return this.accept_quality;
    }

    public final DashInfoModel getDash() {
        return this.dash;
    }

    public final ArrayList<VideoSegmentModel> getDurl() {
        return this.durl;
    }

    public final String getFormat() {
        return this.format;
    }

    public final int getQuality() {
        return this.quality;
    }

    public final ArrayList<SupportFormatModel> getSupport_formats() {
        return this.support_formats;
    }

    public final void setAccept_description(ArrayList<String> arrayList) {
        this.accept_description = arrayList;
    }

    public final void setAccept_quality(ArrayList<Integer> arrayList) {
        this.accept_quality = arrayList;
    }

    public final void setDash(DashInfoModel dashInfoModel) {
        this.dash = dashInfoModel;
    }

    public final void setDurl(ArrayList<VideoSegmentModel> arrayList) {
        this.durl = arrayList;
    }

    public final void setFormat(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.format = str;
    }

    public final void setQuality(int i4) {
        this.quality = i4;
    }

    public final void setSupport_formats(ArrayList<SupportFormatModel> arrayList) {
        this.support_formats = arrayList;
    }

    public String toString() {
        return "PlayInfoModel(accept_description=" + this.accept_description + ", accept_quality=" + this.accept_quality + ", quality=" + this.quality + ", durl=" + this.durl + ", format='" + this.format + "', dash=" + this.dash + ", support_formats=" + this.support_formats + ')';
    }
}
