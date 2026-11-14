package com.xx.blbl.model.player;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class WatchingTotalNumberModel implements Serializable {

    @SerializedName("total")
    private String total = "";

    @SerializedName("count")
    private String count = "";

    public final String getCount() {
        return this.count;
    }

    public final String getTotal() {
        return this.total;
    }

    public final void setCount(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.count = str;
    }

    public final void setTotal(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.total = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("WatchingTotalNumberModel(total='");
        sb.append(this.total);
        sb.append("', count='");
        return DomainSupport.appendString(sb, this.count, "')");
    }
}
