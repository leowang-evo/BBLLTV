package com.xx.blbl.model.favorite;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FolderStatModel implements Serializable {

    @SerializedName("collect")
    private long collect;

    @SerializedName("danmaku")
    private long danmaku;

    @SerializedName("play")
    private long play;

    @SerializedName("share")
    private long share;

    @SerializedName("thumb_up")
    private long thumb_up;

    public final long getCollect() {
        return this.collect;
    }

    public final long getDanmaku() {
        return this.danmaku;
    }

    public final long getPlay() {
        return this.play;
    }

    public final long getShare() {
        return this.share;
    }

    public final long getThumb_up() {
        return this.thumb_up;
    }

    public final void setCollect(long j7) {
        this.collect = j7;
    }

    public final void setDanmaku(long j7) {
        this.danmaku = j7;
    }

    public final void setPlay(long j7) {
        this.play = j7;
    }

    public final void setShare(long j7) {
        this.share = j7;
    }

    public final void setThumb_up(long j7) {
        this.thumb_up = j7;
    }

    public String toString() {
        return "FolderStatModel(collect=" + this.collect + ", play=" + this.play + ", thumb_up=" + this.thumb_up + ", share=" + this.share + ')';
    }
}
