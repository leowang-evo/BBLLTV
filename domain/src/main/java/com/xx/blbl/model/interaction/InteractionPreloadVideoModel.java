package com.xx.blbl.model.interaction;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionPreloadVideoModel implements Serializable {

    @SerializedName("aid")
    private long aid;

    @SerializedName("cid")
    private long cid;

    public final long getAid() {
        return this.aid;
    }

    public final long getCid() {
        return this.cid;
    }

    public final void setAid(long j7) {
        this.aid = j7;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public String toString() {
        return "InteractionPreloadVideoModel(aid=" + this.aid + ", cid=" + this.cid + ')';
    }
}
