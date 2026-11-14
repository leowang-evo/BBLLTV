package com.xx.blbl.model.player;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class DashMediaModel implements Serializable {

    @SerializedName("backup_url")
    private ArrayList<String> backup_url;

    @SerializedName("bandwidth")
    private int bandwidth;

    @SerializedName("codecid")
    private int codecid;

    @SerializedName("height")
    private int height;

    @SerializedName("id")
    private int id;

    @SerializedName("segment_base")
    private DashSegmentBaseModel segment_base;

    @SerializedName("start_with_sap")
    private int start_with_sap;

    @SerializedName("width")
    private int width;

    @SerializedName("base_url")
    private String base_url = "";

    @SerializedName("mime_type")
    private String mime_type = "";

    @SerializedName("codecs")
    private String codecs = "";

    @SerializedName("frame_rate")
    private String frame_rate = "";

    @SerializedName("sar")
    private String sar = "";

    @SerializedName("widevine_pssh")
    private String widevine_pssh = "";

    public final ArrayList<String> getBackup_url() {
        return this.backup_url;
    }

    public final int getBandwidth() {
        return this.bandwidth;
    }

    public final String getBase_url() {
        return this.base_url;
    }

    public final int getCodecid() {
        return this.codecid;
    }

    public final String getCodecs() {
        return this.codecs;
    }

    public final String getFrame_rate() {
        return this.frame_rate;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMime_type() {
        return this.mime_type;
    }

    public final String getSar() {
        return this.sar;
    }

    public final DashSegmentBaseModel getSegment_base() {
        return this.segment_base;
    }

    public final int getStart_with_sap() {
        return this.start_with_sap;
    }

    public final String getWidevine_pssh() {
        return this.widevine_pssh;
    }

    public final int getWidth() {
        return this.width;
    }

    public final boolean isHevc() {
        return StringsKt.startsWith(this.codecs, "hev", true) || StringsKt.startsWith(this.codecs, "hvc", true);
    }

    public final ArrayList<String> playableURLs() {
        String base = this.base_url;
        ArrayList<String> arrayList = this.backup_url;
        Intrinsics.checkNotNullParameter(base, "base");
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add(base);
        if (arrayList != null) {
            arrayList2.addAll(arrayList);
        }
        java.util.Collections.sort(arrayList2, com.xx.blbl.recovery.DomainSupport.CDN_ORDER);
        return arrayList2;
    }

    public final void setBackup_url(ArrayList<String> arrayList) {
        this.backup_url = arrayList;
    }

    public final void setBandwidth(int i4) {
        this.bandwidth = i4;
    }

    public final void setBase_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.base_url = str;
    }

    public final void setCodecid(int i4) {
        this.codecid = i4;
    }

    public final void setCodecs(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.codecs = str;
    }

    public final void setFrame_rate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.frame_rate = str;
    }

    public final void setHeight(int i4) {
        this.height = i4;
    }

    public final void setId(int i4) {
        this.id = i4;
    }

    public final void setMime_type(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mime_type = str;
    }

    public final void setSar(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sar = str;
    }

    public final void setSegment_base(DashSegmentBaseModel dashSegmentBaseModel) {
        this.segment_base = dashSegmentBaseModel;
    }

    public final void setStart_with_sap(int i4) {
        this.start_with_sap = i4;
    }

    public final void setWidevine_pssh(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.widevine_pssh = str;
    }

    public final void setWidth(int i4) {
        this.width = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DashMediaModel(id=");
        sb.append(this.id);
        sb.append(", base_url='");
        sb.append(this.base_url);
        sb.append("', backup_url=");
        sb.append(this.backup_url);
        sb.append(", bandwidth=");
        sb.append(this.bandwidth);
        sb.append(", mime_type='");
        sb.append(this.mime_type);
        sb.append("', codecs='");
        sb.append(this.codecs);
        sb.append("', width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", frame_rate='");
        sb.append(this.frame_rate);
        sb.append("', sar='");
        sb.append(this.sar);
        sb.append("', start_with_sap=");
        sb.append(this.start_with_sap);
        sb.append(", segment_base=");
        sb.append(this.segment_base);
        sb.append(", codecid=");
        sb.append(this.codecid);
        sb.append(", widevine_pssh='");
        return DomainSupport.appendString(sb, this.widevine_pssh, "')");
    }
}
