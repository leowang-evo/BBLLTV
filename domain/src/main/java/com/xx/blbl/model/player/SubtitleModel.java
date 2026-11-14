package com.xx.blbl.model.player;

import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleModel implements Serializable {

    @SerializedName("subtitleContents")
    private ArrayList<SubtitleContentModel> subtitleContents;

    @SerializedName("lan_doc")
    private String lan_doc = "";

    @SerializedName("subtitle_url")
    private String subtitle_url = "";

    @SerializedName("lan")
    private String lan = "";

    @SerializedName("url")
    private String url = "";

    public final String getLan() {
        return this.lan;
    }

    public final String getLan_doc() {
        return this.lan_doc;
    }

    public final ArrayList<SubtitleContentModel> getSubtitleContents() {
        return this.subtitleContents;
    }

    public final String getSubtitle_url() {
        return this.subtitle_url;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setLan(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lan = str;
    }

    public final void setLan_doc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lan_doc = str;
    }

    public final void setSubtitleContents(ArrayList<SubtitleContentModel> arrayList) {
        this.subtitleContents = arrayList;
    }

    public final void setSubtitle_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitle_url = str;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        return "SubtitleModel(lan_doc='" + this.lan_doc + "', subtitle_url='" + this.subtitle_url + "', lan='" + this.lan + "', url='" + this.url + "', subtitleContents=" + this.subtitleContents + ')';
    }
}
