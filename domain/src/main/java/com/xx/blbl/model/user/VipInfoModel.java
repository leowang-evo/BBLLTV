package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VipInfoModel implements Serializable {

    @SerializedName("text")
    private String text = "";

    @SerializedName("label_theme")
    private String label_theme = "";

    public final String getLabel_theme() {
        return this.label_theme;
    }

    public final String getText() {
        return this.text;
    }

    public final void setLabel_theme(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.label_theme = str;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VipInfoModel(text='");
        sb.append(this.text);
        sb.append("', label_theme='");
        return DomainSupport.appendString(sb, this.label_theme, "')");
    }
}
