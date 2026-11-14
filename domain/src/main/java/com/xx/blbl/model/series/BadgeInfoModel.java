package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class BadgeInfoModel implements Serializable {

    @SerializedName("text")
    private String text = "";

    @SerializedName("bg_color_night")
    private String bg_color_night = "";

    @SerializedName("bg_color")
    private String bg_color = "";

    public final String getBg_color() {
        return this.bg_color;
    }

    public final String getBg_color_night() {
        return this.bg_color_night;
    }

    public final String getText() {
        return this.text;
    }

    public final void setBg_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bg_color = str;
    }

    public final void setBg_color_night(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bg_color_night = str;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BadgeInfoModel(text='");
        sb.append(this.text);
        sb.append("', bg_color_night='");
        sb.append(this.bg_color_night);
        sb.append("', bg_color='");
        return DomainSupport.appendString(sb, this.bg_color, "')");
    }
}
