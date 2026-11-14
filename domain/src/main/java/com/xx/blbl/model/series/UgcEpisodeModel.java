package com.xx.blbl.model.series;

import com.xx.blbl.model.video.VideoModel;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UgcEpisodeModel implements Serializable {

    @SerializedName("aid")
    private long aid;

    @SerializedName("arc")
    private VideoModel arc;

    @SerializedName("cid")
    private long cid;

    @SerializedName("id")
    private long id;

    @SerializedName("bvid")
    private String bvid = "";

    @SerializedName("title")
    private String title = "";

    public final long getAid() {
        return this.aid;
    }

    public final VideoModel getArc() {
        return this.arc;
    }

    public final String getBvid() {
        return this.bvid;
    }

    public final long getCid() {
        return this.cid;
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setAid(long j7) {
        this.aid = j7;
    }

    public final void setArc(VideoModel videoModel) {
        this.arc = videoModel;
    }

    public final void setBvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bvid = str;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        return "UgcEpisodeModel(aid=" + this.aid + ", bvid='" + this.bvid + "', cid=" + this.cid + ", id=" + this.id + ", title='" + this.title + "', arc=" + this.arc + ')';
    }
}
