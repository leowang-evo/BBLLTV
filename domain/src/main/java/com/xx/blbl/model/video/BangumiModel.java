package com.xx.blbl.model.video;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class BangumiModel implements Serializable {

    @SerializedName("ep_id")
    private long ep_id;

    @SerializedName("season")
    private SeasonModel season;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("long_title")
    private String long_title = "";

    public final String getCover() {
        return this.cover;
    }

    public final long getEp_id() {
        return this.ep_id;
    }

    public final String getLong_title() {
        return this.long_title;
    }

    public final SeasonModel getSeason() {
        return this.season;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setEp_id(long j7) {
        this.ep_id = j7;
    }

    public final void setLong_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.long_title = str;
    }

    public final void setSeason(SeasonModel seasonModel) {
        this.season = seasonModel;
    }

    public String toString() {
        return "BangumiModel(cover='" + this.cover + "', ep_id=" + this.ep_id + ", long_title='" + this.long_title + "', season=" + this.season + ')';
    }
}
