package com.xx.blbl.model;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class NavWbi implements Serializable {

    @SerializedName("img")
    private String img = "";

    @SerializedName("sub")
    private String sub = "";

    public final String getImg() {
        return this.img;
    }

    public final String getSub() {
        return this.sub;
    }

    public final void setImg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.img = str;
    }

    public final void setSub(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sub = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NavWbi(img='");
        sb.append(this.img);
        sb.append("', sub='");
        return DomainSupport.appendString(sb, this.sub, "')");
    }
}
