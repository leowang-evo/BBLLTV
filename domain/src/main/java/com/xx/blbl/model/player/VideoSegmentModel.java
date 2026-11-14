package com.xx.blbl.model.player;

import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoSegmentModel {

    @SerializedName("backup_url")
    private ArrayList<String> backup_url;

    @SerializedName("length")
    private long length;

    @SerializedName("order")
    private int order;

    @SerializedName("url")
    private String url = "";

    public final ArrayList<String> getBackup_url() {
        return this.backup_url;
    }

    public final long getLength() {
        return this.length;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setBackup_url(ArrayList<String> arrayList) {
        this.backup_url = arrayList;
    }

    public final void setLength(long j7) {
        this.length = j7;
    }

    public final void setOrder(int i4) {
        this.order = i4;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        return "VideoSegmentModel(order=" + this.order + ", length=" + this.length + ", url='" + this.url + "', backup_url=" + this.backup_url + ')';
    }
}
