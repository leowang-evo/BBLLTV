package com.xx.blbl.model.lane;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LaneHeaderModel implements Serializable {

    @SerializedName("title")
    private String title = "";

    @SerializedName("url")
    private String url = "";

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LaneHeadModel(title='");
        sb.append(this.title);
        sb.append("', url='");
        return DomainSupport.appendString(sb, this.url, "')");
    }
}
