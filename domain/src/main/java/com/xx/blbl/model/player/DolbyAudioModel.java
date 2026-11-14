package com.xx.blbl.model.player;

import com.xx.blbl.recovery.DomainSupport;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class DolbyAudioModel {

    @SerializedName("backup_url")
    private ArrayList<String> backup_url;

    @SerializedName("bandwidth")
    private int bandwidth;

    @SerializedName("id")
    private long id;

    @SerializedName("segment_base")
    private DashSegmentBaseModel segment_base;

    @SerializedName("size")
    private int size;

    @SerializedName("base_url")
    private String base_url = "";

    @SerializedName("mime_type")
    private String mime_type = "";

    @SerializedName("codecs")
    private String codecs = "";

    public final ArrayList<String> getBackup_url() {
        return this.backup_url;
    }

    public final int getBandwidth() {
        return this.bandwidth;
    }

    public final String getBase_url() {
        return this.base_url;
    }

    public final String getCodecs() {
        return this.codecs;
    }

    public final long getId() {
        return this.id;
    }

    public final String getMime_type() {
        return this.mime_type;
    }

    public final DashSegmentBaseModel getSegment_base() {
        return this.segment_base;
    }

    public final int getSize() {
        return this.size;
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

    public final void setCodecs(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.codecs = str;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setMime_type(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mime_type = str;
    }

    public final void setSegment_base(DashSegmentBaseModel dashSegmentBaseModel) {
        this.segment_base = dashSegmentBaseModel;
    }

    public final void setSize(int i4) {
        this.size = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DolbyAudioModel(id=");
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
        sb.append("', segment_base=");
        sb.append(this.segment_base);
        sb.append(", size=");
        return DomainSupport.appendInt(sb, this.size, ')');
    }
}
