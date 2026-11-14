package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveQualityModel implements Serializable {

    @SerializedName("desc")
    private String desc = "";

    @SerializedName("qn")
    private int qn;

    public final String getDesc() {
        return this.desc;
    }

    public final int getQn() {
        return this.qn;
    }

    public final void setDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.desc = str;
    }

    public final void setQn(int i4) {
        this.qn = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveQualityModel(qn=");
        sb.append(this.qn);
        sb.append(", desc='");
        return DomainSupport.appendString(sb, this.desc, "')");
    }
}
