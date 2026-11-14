package com.xx.blbl.model.series;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodeStatModel implements Serializable {

    @SerializedName("coin")
    private long coin;

    @SerializedName("danmakus")
    private long danmakus;

    @SerializedName("likes")
    private long likes;

    @SerializedName("play")
    private long play;

    @SerializedName("reply")
    private long reply;

    public final long getCoin() {
        return this.coin;
    }

    public final long getDanmakus() {
        return this.danmakus;
    }

    public final long getLikes() {
        return this.likes;
    }

    public final long getPlay() {
        return this.play;
    }

    public final long getReply() {
        return this.reply;
    }

    public final void setCoin(long j7) {
        this.coin = j7;
    }

    public final void setDanmakus(long j7) {
        this.danmakus = j7;
    }

    public final void setLikes(long j7) {
        this.likes = j7;
    }

    public final void setPlay(long j7) {
        this.play = j7;
    }

    public final void setReply(long j7) {
        this.reply = j7;
    }

    public String toString() {
        return "EpisodeStatModel(coin=" + this.coin + ", danmakus=" + this.danmakus + ", likes=" + this.likes + ", play=" + this.play + ", reply=" + this.reply + ')';
    }
}
