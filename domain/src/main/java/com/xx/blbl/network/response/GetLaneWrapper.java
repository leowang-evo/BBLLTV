package com.xx.blbl.network.response;

import com.xx.blbl.model.lane.LaneInfoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GetLaneWrapper implements Serializable {

    @SerializedName("has_next")
    private int has_next = 1;

    @SerializedName("modules")
    private List<LaneInfoModel> modules;

    @SerializedName("next_cursor")
    private long next_cursor;

    public final int getHas_next() {
        return this.has_next;
    }

    public final List<LaneInfoModel> getModules() {
        return this.modules;
    }

    public final long getNext_cursor() {
        return this.next_cursor;
    }

    public final void setHas_next(int i4) {
        this.has_next = i4;
    }

    public final void setModules(List<LaneInfoModel> list) {
        this.modules = list;
    }

    public final void setNext_cursor(long j7) {
        this.next_cursor = j7;
    }

    public String toString() {
        return "GetLaneWrapper(has_next=" + this.has_next + ", modules=" + this.modules + ", next_cursor=" + this.next_cursor + ')';
    }
}
