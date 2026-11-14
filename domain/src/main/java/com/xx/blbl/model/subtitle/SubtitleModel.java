package com.xx.blbl.model.subtitle;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleModel implements Serializable {

    @SerializedName("content")
    private String content = "";

    @SerializedName("from")
    private double from;

    @SerializedName("to")
    private double to;

    public final String getContent() {
        return this.content;
    }

    public final double getFrom() {
        return this.from;
    }

    public final double getTo() {
        return this.to;
    }

    public final void setContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content = str;
    }

    public final void setFrom(double d) {
        this.from = d;
    }

    public final void setTo(double d) {
        this.to = d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SubtitleModel(from=");
        sb.append(this.from);
        sb.append(", to=");
        sb.append(this.to);
        sb.append(", content='");
        return DomainSupport.appendString(sb, this.content, "')");
    }
}
