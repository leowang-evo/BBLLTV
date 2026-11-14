package com.xx.blbl.model;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class WbiImage implements Serializable {

    @SerializedName("img_url")
    private String img_url = "";

    @SerializedName("sub_url")
    private String sub_url = "";

    public final String getImg_url() {
        return this.img_url;
    }

    public final String getSub_url() {
        return this.sub_url;
    }

    public final void setImg_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.img_url = str;
    }

    public final void setSub_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sub_url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("WbiImage(img_url='");
        sb.append(this.img_url);
        sb.append("', sub_url='");
        return DomainSupport.appendString(sb, this.sub_url, "')");
    }
}
