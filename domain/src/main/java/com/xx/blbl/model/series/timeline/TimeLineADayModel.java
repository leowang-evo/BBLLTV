package com.xx.blbl.model.series.timeline;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class TimeLineADayModel implements Serializable {

    @SerializedName("date")
    private String date = "";

    @SerializedName("date_ts")
    private long date_ts;

    @SerializedName("day_of_week")
    private int day_of_week;

    @SerializedName("episodes")
    private List<SeriesTimeLineModel> episodes;

    @SerializedName("is_today")
    private int is_today;

    public final String getDate() {
        return this.date;
    }

    public final long getDate_ts() {
        return this.date_ts;
    }

    public final int getDay_of_week() {
        return this.day_of_week;
    }

    public final List<SeriesTimeLineModel> getEpisodes() {
        return this.episodes;
    }

    public final int is_today() {
        return this.is_today;
    }

    public final void setDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setDate_ts(long j7) {
        this.date_ts = j7;
    }

    public final void setDay_of_week(int i4) {
        this.day_of_week = i4;
    }

    public final void setEpisodes(List<SeriesTimeLineModel> list) {
        this.episodes = list;
    }

    public final void set_today(int i4) {
        this.is_today = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TimeLineADayModel(date='");
        sb.append(this.date);
        sb.append("', date_ts=");
        sb.append(this.date_ts);
        sb.append(", day_of_week=");
        sb.append(this.day_of_week);
        sb.append(", is_today=");
        sb.append(this.is_today);
        sb.append(", episodes=");
        return DomainSupport.appendList(sb, this.episodes, ')');
    }
}
