package com.xx.blbl.network.response;

import com.xx.blbl.model.video.VideoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class AllDynamicResponse implements Serializable {

    @SerializedName("has_more")
    private boolean has_more;

    @SerializedName("items")
    private List<VideoModel> items;

    @SerializedName("offset")
    private long offset;

    public final boolean getHas_more() {
        return this.has_more;
    }

    public final List<VideoModel> getItems() {
        return this.items;
    }

    public final long getOffset() {
        return this.offset;
    }

    public final void setHas_more(boolean z7) {
        this.has_more = z7;
    }

    public final void setItems(List<VideoModel> list) {
        this.items = list;
    }

    public final void setOffset(long j7) {
        this.offset = j7;
    }

    public String toString() {
        return "AllDynamicResponse( has_more=" + this.has_more + ", offset=" + this.offset + ')';
    }
}
