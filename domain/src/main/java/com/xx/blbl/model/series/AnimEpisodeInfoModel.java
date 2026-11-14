package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class AnimEpisodeInfoModel implements Serializable {

    @SerializedName("duration")
    private long duration;

    @SerializedName("id")
    private long id;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("index_show")
    private String index_show = "";

    @SerializedName("long_title")
    private String long_title = "";

    @SerializedName("pub_time")
    private String pub_time = "";

    @SerializedName("title")
    private String title = "";

    public final String getCover() {
        return this.cover;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final long getId() {
        return this.id;
    }

    public final String getIndex_show() {
        return this.index_show;
    }

    public final String getLong_title() {
        return this.long_title;
    }

    public final String getPub_time() {
        return this.pub_time;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setDuration(long j7) {
        this.duration = j7;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setIndex_show(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.index_show = str;
    }

    public final void setLong_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.long_title = str;
    }

    public final void setPub_time(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pub_time = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AnimEpisodeInfoModel(cover='");
        sb.append(this.cover);
        sb.append("', duration=");
        sb.append(this.duration);
        sb.append(", id=");
        sb.append(this.id);
        sb.append(", index_show='");
        sb.append(this.index_show);
        sb.append("', long_title='");
        sb.append(this.long_title);
        sb.append("', pub_time='");
        sb.append(this.pub_time);
        sb.append("', title='");
        return DomainSupport.appendString(sb, this.title, "')");
    }
}
