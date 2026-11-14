package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.subtitle.SubtitleModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleResponse implements Serializable {

    @SerializedName("background_alpha")
    private double background_alpha;

    @SerializedName("body")
    private List<SubtitleModel> body;

    @SerializedName("font_size")
    private double font_size;

    @SerializedName("font_color")
    private String font_color = "";

    @SerializedName("background_color")
    private String background_color = "";

    public final double getBackground_alpha() {
        return this.background_alpha;
    }

    public final String getBackground_color() {
        return this.background_color;
    }

    public final List<SubtitleModel> getBody() {
        return this.body;
    }

    public final String getFont_color() {
        return this.font_color;
    }

    public final double getFont_size() {
        return this.font_size;
    }

    public final void setBackground_alpha(double d) {
        this.background_alpha = d;
    }

    public final void setBackground_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.background_color = str;
    }

    public final void setBody(List<SubtitleModel> list) {
        this.body = list;
    }

    public final void setFont_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.font_color = str;
    }

    public final void setFont_size(double d) {
        this.font_size = d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SubtitleResponse(font_size=");
        sb.append(this.font_size);
        sb.append(", font_color='");
        sb.append(this.font_color);
        sb.append("', background_alpha=");
        sb.append(this.background_alpha);
        sb.append(", background_color='");
        sb.append(this.background_color);
        sb.append("', body=");
        return DomainSupport.appendList(sb, this.body, ')');
    }
}
