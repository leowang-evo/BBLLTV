package com.xx.blbl.model.favorite;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FolderDetailModel implements Serializable {

    @SerializedName("cnt_info")
    private FolderStatModel cnt_info;

    @SerializedName("cover_type")
    private int cover_type;

    @SerializedName("ctime")
    private long ctime;

    @SerializedName("fid")
    private long fid;

    @SerializedName("id")
    private long id;

    @SerializedName("media_count")
    private int media_count;

    @SerializedName("mid")
    private long mid;

    @SerializedName("mtime")
    private long mtime;

    @SerializedName("title")
    private String title = "";

    @SerializedName("cover")
    private String cover = "";

    public final FolderStatModel getCnt_info() {
        return this.cnt_info;
    }

    public final String getCover() {
        return this.cover;
    }

    public final int getCover_type() {
        return this.cover_type;
    }

    public final long getCtime() {
        return this.ctime;
    }

    public final long getFid() {
        return this.fid;
    }

    public final long getId() {
        return this.id;
    }

    public final int getMedia_count() {
        return this.media_count;
    }

    public final long getMid() {
        return this.mid;
    }

    public final long getMtime() {
        return this.mtime;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setCnt_info(FolderStatModel folderStatModel) {
        this.cnt_info = folderStatModel;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setCover_type(int i4) {
        this.cover_type = i4;
    }

    public final void setCtime(long j7) {
        this.ctime = j7;
    }

    public final void setFid(long j7) {
        this.fid = j7;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setMedia_count(int i4) {
        this.media_count = i4;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public final void setMtime(long j7) {
        this.mtime = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FolderDetailModel(id=");
        sb.append(this.id);
        sb.append(", fid=");
        sb.append(this.fid);
        sb.append(", mid=");
        sb.append(this.mid);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', cover='");
        sb.append(this.cover);
        sb.append("', cover_type=");
        sb.append(this.cover_type);
        sb.append(", cnt_info=");
        sb.append(this.cnt_info);
        sb.append(", ctime=");
        sb.append(this.ctime);
        sb.append(", mtime=");
        sb.append(this.mtime);
        sb.append(", media_count=");
        return DomainSupport.appendInt(sb, this.media_count, ')');
    }
}
