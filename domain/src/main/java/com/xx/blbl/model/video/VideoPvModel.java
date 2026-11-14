package com.xx.blbl.model.video;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoPvModel implements Serializable {

    @SerializedName("cid")
    private long cid;

    @SerializedName("duration")
    private int duration;

    @SerializedName("page")
    private int page;

    @SerializedName("from")
    private String from = "";

    @SerializedName("part")
    private String part = "";

    @SerializedName("vid")
    private String vid = "";

    @SerializedName("weblink")
    private String weblink = "";

    @SerializedName("first_frame")
    private String first_frame = "";

    public final long getCid() {
        return this.cid;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final String getFirst_frame() {
        return this.first_frame;
    }

    public final String getFrom() {
        return this.from;
    }

    public final int getPage() {
        return this.page;
    }

    public final String getPart() {
        return this.part;
    }

    public final String getVid() {
        return this.vid;
    }

    public final String getWeblink() {
        return this.weblink;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public final void setDuration(int i4) {
        this.duration = i4;
    }

    public final void setFirst_frame(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.first_frame = str;
    }

    public final void setFrom(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.from = str;
    }

    public final void setPage(int i4) {
        this.page = i4;
    }

    public final void setPart(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.part = str;
    }

    public final void setVid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vid = str;
    }

    public final void setWeblink(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.weblink = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VideoPvModel(cid=");
        sb.append(this.cid);
        sb.append(", page=");
        sb.append(this.page);
        sb.append(", from='");
        sb.append(this.from);
        sb.append("', part='");
        sb.append(this.part);
        sb.append("', duration=");
        sb.append(this.duration);
        sb.append(", vid='");
        sb.append(this.vid);
        sb.append("', weblink='");
        sb.append(this.weblink);
        sb.append("', first_frame='");
        return DomainSupport.appendString(sb, this.first_frame, "')");
    }
}
