package com.xx.blbl.model.subtitle;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleInfoModel implements Serializable {

    @SerializedName("id")
    private String id = "";

    @SerializedName("lan_doc")
    private String lan_doc = "";

    @SerializedName("lan")
    private String lan = "";

    @SerializedName("subtitle_url")
    private String subtitle_url = "";

    public final String getId() {
        return this.id;
    }

    public final String getLan() {
        return this.lan;
    }

    public final String getLan_doc() {
        return this.lan_doc;
    }

    public final String getSubtitle_url() {
        return this.subtitle_url;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final void setLan(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lan = str;
    }

    public final void setLan_doc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lan_doc = str;
    }

    public final void setSubtitle_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitle_url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SubtitleInfoModel(id='");
        sb.append(this.id);
        sb.append("', lan_doc='");
        sb.append(this.lan_doc);
        sb.append("', lan='");
        sb.append(this.lan);
        sb.append("', subtitle_url='");
        return DomainSupport.appendString(sb, this.subtitle_url, "')");
    }
}
