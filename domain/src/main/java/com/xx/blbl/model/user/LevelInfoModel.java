package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LevelInfoModel implements Serializable {

    @SerializedName("current_exp")
    private int current_exp;

    @SerializedName("current_level")
    private int current_level;

    @SerializedName("current_min")
    private int current_min;

    @SerializedName("next_exp")
    private String next_exp = "";

    public final int getCurrent_exp() {
        return this.current_exp;
    }

    public final int getCurrent_level() {
        return this.current_level;
    }

    public final int getCurrent_min() {
        return this.current_min;
    }

    public final String getNext_exp() {
        return this.next_exp;
    }

    public final void setCurrent_exp(int i4) {
        this.current_exp = i4;
    }

    public final void setCurrent_level(int i4) {
        this.current_level = i4;
    }

    public final void setCurrent_min(int i4) {
        this.current_min = i4;
    }

    public final void setNext_exp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.next_exp = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LevelInfoModel(current_level=");
        sb.append(this.current_level);
        sb.append(", current_min=");
        sb.append(this.current_min);
        sb.append(", current_exp=");
        sb.append(this.current_exp);
        sb.append(", next_exp='");
        return DomainSupport.appendString(sb, this.next_exp, "')");
    }
}
