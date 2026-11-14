package com.xx.blbl.ui.adapter.search;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchLiveWatchedStat implements Serializable {

    @SerializedName("num")
    private long num;

    @SerializedName("text_small")
    private String text_small = "";

    public final long getNum() {
        return this.num;
    }

    public final String getText_small() {
        return this.text_small;
    }

    public final void setNum(long j7) {
        this.num = j7;
    }

    public final void setText_small(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text_small = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchLiveWatchedStat(num=");
        sb.append(this.num);
        sb.append(", text_small='");
        return DomainSupport.appendString(sb, this.text_small, "')");
    }
}
