package com.xx.blbl.model.video;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoStatModel implements Serializable {

    @SerializedName("aid")
    private long aid;

    @SerializedName("coin")
    private long coin;

    @SerializedName("danmaku")
    private long danmaku;

    @SerializedName("dislike")
    private long dislike;

    @SerializedName("favorite")
    private long favorite;

    @SerializedName("his_rank")
    private long his_rank;

    @SerializedName("like")
    private long like;

    @SerializedName("now_rank")
    private long now_rank;

    @SerializedName("reply")
    private long reply;

    @SerializedName("share")
    private long share;

    @SerializedName("view")
    private long view;

    public final long getAid() {
        return this.aid;
    }

    public final long getCoin() {
        return this.coin;
    }

    public final long getDanmaku() {
        return this.danmaku;
    }

    public final long getDislike() {
        return this.dislike;
    }

    public final long getFavorite() {
        return this.favorite;
    }

    public final long getHis_rank() {
        return this.his_rank;
    }

    public final long getLike() {
        return this.like;
    }

    public final long getNow_rank() {
        return this.now_rank;
    }

    public final long getReply() {
        return this.reply;
    }

    public final long getShare() {
        return this.share;
    }

    public final long getView() {
        return this.view;
    }

    public final void setAid(long j7) {
        this.aid = j7;
    }

    public final void setCoin(long j7) {
        this.coin = j7;
    }

    public final void setDanmaku(long j7) {
        this.danmaku = j7;
    }

    public final void setDislike(long j7) {
        this.dislike = j7;
    }

    public final void setFavorite(long j7) {
        this.favorite = j7;
    }

    public final void setHis_rank(long j7) {
        this.his_rank = j7;
    }

    public final void setLike(long j7) {
        this.like = j7;
    }

    public final void setNow_rank(long j7) {
        this.now_rank = j7;
    }

    public final void setReply(long j7) {
        this.reply = j7;
    }

    public final void setShare(long j7) {
        this.share = j7;
    }

    public final void setView(long j7) {
        this.view = j7;
    }

    public String toString() {
        return "VideoStatModel(aid=" + this.aid + ", view=" + this.view + ", danmaku=" + this.danmaku + ", reply=" + this.reply + ", favorite=" + this.favorite + ", coin=" + this.coin + ", share=" + this.share + ", now_rank=" + this.now_rank + ", his_rank=" + this.his_rank + ", like=" + this.like + ", dislike=" + this.dislike + ')';
    }
}
