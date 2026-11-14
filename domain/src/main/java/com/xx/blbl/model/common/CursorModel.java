package com.xx.blbl.model.common;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class CursorModel implements Serializable {

    @SerializedName("business")
    private String business = "";

    @SerializedName("max")
    private long max;

    @SerializedName("ps")
    private int ps;

    @SerializedName("view_at")
    private long view_at;

    public final String getBusiness() {
        return this.business;
    }

    public final long getMax() {
        return this.max;
    }

    public final int getPs() {
        return this.ps;
    }

    public final long getView_at() {
        return this.view_at;
    }

    public final void setBusiness(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.business = str;
    }

    public final void setMax(long j7) {
        this.max = j7;
    }

    public final void setPs(int i4) {
        this.ps = i4;
    }

    public final void setView_at(long j7) {
        this.view_at = j7;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CursorModel(max=");
        sb.append(this.max);
        sb.append(", view_at=");
        sb.append(this.view_at);
        sb.append(", business='");
        sb.append(this.business);
        sb.append("', ps=");
        return DomainSupport.appendInt(sb, this.ps, ')');
    }
}
