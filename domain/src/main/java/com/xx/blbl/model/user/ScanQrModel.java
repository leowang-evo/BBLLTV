package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ScanQrModel implements Serializable {

    @SerializedName("url")
    private String url = "";

    @SerializedName("qrcode_key")
    private String qrcode_key = "";

    public final String getQrcode_key() {
        return this.qrcode_key;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setQrcode_key(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.qrcode_key = str;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ScanQrModel(url='");
        sb.append(this.url);
        sb.append("', qrcode_key='");
        return DomainSupport.appendString(sb, this.qrcode_key, "')");
    }
}
