package com.xx.blbl.model.favorite;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FavoriteFolderModel implements Serializable {

    @SerializedName("attr")
    private int attr;

    @SerializedName("fav_state")
    private int fav_state;

    @SerializedName("fid")
    private long fid;

    @SerializedName("id")
    private long id;

    @SerializedName("media_count")
    private int media_count;

    @SerializedName("mid")
    private long mid;

    @SerializedName("title")
    private String title = "";
    private String imageUrl = "";

    public final int getAttr() {
        return this.attr;
    }

    public final int getFav_state() {
        return this.fav_state;
    }

    public final long getFid() {
        return this.fid;
    }

    public final long getId() {
        return this.id;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final int getMedia_count() {
        return this.media_count;
    }

    public final long getMid() {
        return this.mid;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setAttr(int i4) {
        this.attr = i4;
    }

    public final void setFav_state(int i4) {
        this.fav_state = i4;
    }

    public final void setFid(long j7) {
        this.fid = j7;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imageUrl = str;
    }

    public final void setMedia_count(int i4) {
        this.media_count = i4;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FavoriteFolderModel(id=");
        sb.append(this.id);
        sb.append(", fid=");
        sb.append(this.fid);
        sb.append(", mid=");
        sb.append(this.mid);
        sb.append(", attr=");
        sb.append(this.attr);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', fav_state=");
        sb.append(this.fav_state);
        sb.append(", media_count=");
        return DomainSupport.appendInt(sb, this.media_count, ')');
    }
}
