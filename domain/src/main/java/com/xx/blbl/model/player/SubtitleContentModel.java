package com.xx.blbl.model.player;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleContentModel implements Serializable {

    @SerializedName("content")
    private String content = "";

    @SerializedName("from")
    private double from;

    @SerializedName("location")
    private int location;

    @SerializedName("to")
    private double to;

    public final String getContent() {
        return this.content;
    }

    public final double getFrom() {
        return this.from;
    }

    public final int getLocation() {
        return this.location;
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

    public final void setLocation(int i4) {
        this.location = i4;
    }

    public final void setTo(double d) {
        this.to = d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SubtitleContentData(from=");
        sb.append(this.from);
        sb.append(", to=");
        sb.append(this.to);
        sb.append(", location=");
        sb.append(this.location);
        sb.append(", content='");
        return DomainSupport.appendString(sb, this.content, "')");
    }
}
