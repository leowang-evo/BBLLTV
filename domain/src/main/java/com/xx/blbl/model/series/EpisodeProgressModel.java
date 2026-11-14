package com.xx.blbl.model.series;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodeProgressModel implements Serializable {

    @SerializedName("last_ep_id")
    private long last_ep_id;

    @SerializedName("last_ep_index")
    private String last_ep_index = "";

    @SerializedName("last_time")
    private long last_time;

    public final long getLast_ep_id() {
        return this.last_ep_id;
    }

    public final String getLast_ep_index() {
        return this.last_ep_index;
    }

    public final long getLast_time() {
        return this.last_time;
    }

    public final void setLast_ep_id(long j7) {
        this.last_ep_id = j7;
    }

    public final void setLast_ep_index(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.last_ep_index = str;
    }

    public final void setLast_time(long j7) {
        this.last_time = j7;
    }

    public String toString() {
        return "EpisodeProgressModel(last_ep_id=" + this.last_ep_id + ", last_ep_index='" + this.last_ep_index + "', last_time=" + this.last_time + ')';
    }
}
