package com.xx.blbl.model.video.channel;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ChannelVideoModel implements Serializable {

    @SerializedName("danmaku")
    private long danmaku;

    @SerializedName("id")
    private long id;

    @SerializedName("author_id")
    private String author_id = "";

    @SerializedName("author_name")
    private String author_name = "";

    @SerializedName("bvid")
    private String bvid = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("duration")
    private String duration = "";

    @SerializedName("like_count")
    private String like_count = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("view_count")
    private String view_count = "";

    public final String getAuthor_id() {
        return this.author_id;
    }

    public final String getAuthor_name() {
        return this.author_name;
    }

    public final String getBvid() {
        return this.bvid;
    }

    public final String getCover() {
        return this.cover;
    }

    public final long getDanmaku() {
        return this.danmaku;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final long getId() {
        return this.id;
    }

    public final String getLike_count() {
        return this.like_count;
    }

    public final String getName() {
        return this.name;
    }

    public final String getView_count() {
        return this.view_count;
    }

    public final void setAuthor_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.author_id = str;
    }

    public final void setAuthor_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.author_name = str;
    }

    public final void setBvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bvid = str;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setDanmaku(long j7) {
        this.danmaku = j7;
    }

    public final void setDuration(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.duration = str;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setLike_count(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.like_count = str;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setView_count(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.view_count = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TagVideoModel(author_id='");
        sb.append(this.author_id);
        sb.append("', author_name='");
        sb.append(this.author_name);
        sb.append("', bvid='");
        sb.append(this.bvid);
        sb.append("', cover='");
        sb.append(this.cover);
        sb.append("', danmaku=");
        sb.append(this.danmaku);
        sb.append(", duration='");
        sb.append(this.duration);
        sb.append("', id=");
        sb.append(this.id);
        sb.append(", like_count='");
        sb.append(this.like_count);
        sb.append("', name='");
        sb.append(this.name);
        sb.append("', view_count='");
        return DomainSupport.appendString(sb, this.view_count, "')");
    }
}
