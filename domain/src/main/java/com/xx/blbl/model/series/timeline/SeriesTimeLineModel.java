package com.xx.blbl.model.series.timeline;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SeriesTimeLineModel implements Serializable {
    private int dayOfWeek;

    @SerializedName("delay")
    private int delay;

    @SerializedName("episode_id")
    private long episode_id;

    @SerializedName("pub_ts")
    private long pub_ts;

    @SerializedName("published")
    private int published;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("delay_reason")
    private String delay_reason = "";

    @SerializedName("ep_cover")
    private String ep_cover = "";

    @SerializedName("pub_index")
    private String pub_index = "";

    @SerializedName("pub_time")
    private String pub_time = "";

    @SerializedName("square_cover")
    private String square_cover = "";

    @SerializedName("title")
    private String title = "";

    public final String getCover() {
        return this.cover;
    }

    public final int getDayOfWeek() {
        return this.dayOfWeek;
    }

    public final int getDelay() {
        return this.delay;
    }

    public final String getDelay_reason() {
        return this.delay_reason;
    }

    public final String getEp_cover() {
        return this.ep_cover;
    }

    public final long getEpisode_id() {
        return this.episode_id;
    }

    public final String getPub_index() {
        return this.pub_index;
    }

    public final String getPub_time() {
        return this.pub_time;
    }

    public final long getPub_ts() {
        return this.pub_ts;
    }

    public final int getPublished() {
        return this.published;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final String getSquare_cover() {
        return this.square_cover;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setDayOfWeek(int i4) {
        this.dayOfWeek = i4;
    }

    public final void setDelay(int i4) {
        this.delay = i4;
    }

    public final void setDelay_reason(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.delay_reason = str;
    }

    public final void setEp_cover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ep_cover = str;
    }

    public final void setEpisode_id(long j7) {
        this.episode_id = j7;
    }

    public final void setPub_index(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pub_index = str;
    }

    public final void setPub_time(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pub_time = str;
    }

    public final void setPub_ts(long j7) {
        this.pub_ts = j7;
    }

    public final void setPublished(int i4) {
        this.published = i4;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setSquare_cover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.square_cover = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SeriesTimeLineModel(cover='");
        sb.append(this.cover);
        sb.append("', delay=");
        sb.append(this.delay);
        sb.append(", delay_reason='");
        sb.append(this.delay_reason);
        sb.append("', ep_cover='");
        sb.append(this.ep_cover);
        sb.append("', episode_id='");
        sb.append(this.episode_id);
        sb.append("', pub_index=");
        sb.append(this.pub_index);
        sb.append(", pub_time='");
        sb.append(this.pub_time);
        sb.append("', pub_ts=");
        sb.append(this.pub_ts);
        sb.append(", published=");
        sb.append(this.published);
        sb.append(", season_id=");
        sb.append(this.season_id);
        sb.append(", square_cover='");
        sb.append(this.square_cover);
        sb.append("', title='");
        return DomainSupport.appendString(sb, this.title, "')");
    }
}
