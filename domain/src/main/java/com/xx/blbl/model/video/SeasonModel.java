package com.xx.blbl.model.video;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SeasonModel implements Serializable {

    @SerializedName("is_finish")
    private int is_finish;

    @SerializedName("newest_ep_id")
    private int newest_ep_id;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("total_count")
    private int total_count;

    @SerializedName("newest_ep_index")
    private String newest_ep_index = "";

    @SerializedName("title")
    private String title = "";

    public final int getNewest_ep_id() {
        return this.newest_ep_id;
    }

    public final String getNewest_ep_index() {
        return this.newest_ep_index;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTotal_count() {
        return this.total_count;
    }

    public final int is_finish() {
        return this.is_finish;
    }

    public final void setNewest_ep_id(int i4) {
        this.newest_ep_id = i4;
    }

    public final void setNewest_ep_index(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.newest_ep_index = str;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setTotal_count(int i4) {
        this.total_count = i4;
    }

    public final void set_finish(int i4) {
        this.is_finish = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SeasonModel(is_finish=");
        sb.append(this.is_finish);
        sb.append(", newest_ep_id=");
        sb.append(this.newest_ep_id);
        sb.append(", newest_ep_index='");
        sb.append(this.newest_ep_index);
        sb.append("', season_id=");
        sb.append(this.season_id);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', total_count=");
        return DomainSupport.appendInt(sb, this.total_count, ')');
    }
}
