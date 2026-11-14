package com.xx.blbl.model.episode;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodeStatModel implements Serializable {

    @SerializedName("coins")
    private long coins;

    @SerializedName("danmakus")
    private long danmakus;

    @SerializedName("favorites")
    private long favorites;

    @SerializedName("likes")
    private long likes;

    @SerializedName("reply")
    private long reply;

    @SerializedName("share")
    private long share;

    @SerializedName("views")
    private long views;

    public final long getCoins() {
        return this.coins;
    }

    public final long getDanmakus() {
        return this.danmakus;
    }

    public final long getFavorites() {
        return this.favorites;
    }

    public final long getLikes() {
        return this.likes;
    }

    public final long getReply() {
        return this.reply;
    }

    public final long getShare() {
        return this.share;
    }

    public final long getViews() {
        return this.views;
    }

    public final void setCoins(long j7) {
        this.coins = j7;
    }

    public final void setDanmakus(long j7) {
        this.danmakus = j7;
    }

    public final void setFavorites(long j7) {
        this.favorites = j7;
    }

    public final void setLikes(long j7) {
        this.likes = j7;
    }

    public final void setReply(long j7) {
        this.reply = j7;
    }

    public final void setShare(long j7) {
        this.share = j7;
    }

    public final void setViews(long j7) {
        this.views = j7;
    }

    public String toString() {
        return "EpisodeStatModel(coins=" + this.coins + ", danmakus=" + this.danmakus + ", favorites=" + this.favorites + ", likes=" + this.likes + ", reply=" + this.reply + ", share=" + this.share + ", views=" + this.views + ')';
    }
}
