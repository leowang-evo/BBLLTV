package com.xx.blbl.model.series;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FollowSeriesResult implements Serializable {

    @SerializedName("fmid")
    private long fmid;

    @SerializedName("relation")
    private boolean relation;

    @SerializedName("status")
    private int status;

    @SerializedName("toast")
    private String toast = "";

    public final long getFmid() {
        return this.fmid;
    }

    public final boolean getRelation() {
        return this.relation;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getToast() {
        return this.toast;
    }

    public final void setFmid(long j7) {
        this.fmid = j7;
    }

    public final void setRelation(boolean z7) {
        this.relation = z7;
    }

    public final void setStatus(int i4) {
        this.status = i4;
    }

    public final void setToast(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.toast = str;
    }

    public String toString() {
        return "FollowSeriesResult(toast='" + this.toast + "', status=" + this.status + ", fmid=" + this.fmid + ", relation=" + this.relation + ')';
    }
}
