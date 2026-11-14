package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionEdgeSkinModel implements Serializable {

    @SerializedName("title_shadow_offset_y")
    private int title_shadow_offset_y;

    @SerializedName("title_shadow_radius")
    private int title_shadow_radius;

    @SerializedName("choice_image")
    private String choice_image = "";

    @SerializedName("title_text_color")
    private String title_text_color = "";

    @SerializedName("title_shadow_color")
    private String title_shadow_color = "";

    @SerializedName("progressbar_color")
    private String progressbar_color = "";

    @SerializedName("progressbar_shadow_color")
    private String progressbar_shadow_color = "";

    public final String getChoice_image() {
        return this.choice_image;
    }

    public final String getProgressbar_color() {
        return this.progressbar_color;
    }

    public final String getProgressbar_shadow_color() {
        return this.progressbar_shadow_color;
    }

    public final String getTitle_shadow_color() {
        return this.title_shadow_color;
    }

    public final int getTitle_shadow_offset_y() {
        return this.title_shadow_offset_y;
    }

    public final int getTitle_shadow_radius() {
        return this.title_shadow_radius;
    }

    public final String getTitle_text_color() {
        return this.title_text_color;
    }

    public final void setChoice_image(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.choice_image = str;
    }

    public final void setProgressbar_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.progressbar_color = str;
    }

    public final void setProgressbar_shadow_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.progressbar_shadow_color = str;
    }

    public final void setTitle_shadow_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title_shadow_color = str;
    }

    public final void setTitle_shadow_offset_y(int i4) {
        this.title_shadow_offset_y = i4;
    }

    public final void setTitle_shadow_radius(int i4) {
        this.title_shadow_radius = i4;
    }

    public final void setTitle_text_color(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title_text_color = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InteractionEdgeSkinModel(choice_image='");
        sb.append(this.choice_image);
        sb.append("', title_text_color='");
        sb.append(this.title_text_color);
        sb.append("', title_shadow_color='");
        sb.append(this.title_shadow_color);
        sb.append("', title_shadow_offset_y=");
        sb.append(this.title_shadow_offset_y);
        sb.append(", title_shadow_radius=");
        sb.append(this.title_shadow_radius);
        sb.append(", progressbar_color='");
        sb.append(this.progressbar_color);
        sb.append("', progressbar_shadow_color='");
        return DomainSupport.appendString(sb, this.progressbar_shadow_color, "')");
    }
}
