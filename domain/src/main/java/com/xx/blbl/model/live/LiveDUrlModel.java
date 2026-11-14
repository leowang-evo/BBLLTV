package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveDUrlModel implements Serializable {

    @SerializedName("order")
    private int order;

    @SerializedName("url")
    private String url = "";

    public final int getOrder() {
        return this.order;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setOrder(int i4) {
        this.order = i4;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveDUrlModel(url='");
        sb.append(this.url);
        sb.append("', order=");
        return DomainSupport.appendInt(sb, this.order, ')');
    }
}
