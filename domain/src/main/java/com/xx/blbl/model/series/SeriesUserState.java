package com.xx.blbl.model.series;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SeriesUserState implements Serializable {

    @SerializedName("follow")
    private int follow;

    @SerializedName("follow_status")
    private int follow_status;

    @SerializedName("progress")
    private EpisodeProgressModel progress;

    public final int getFollow() {
        return this.follow;
    }

    public final int getFollow_status() {
        return this.follow_status;
    }

    public final EpisodeProgressModel getProgress() {
        return this.progress;
    }

    public final void setFollow(int i4) {
        this.follow = i4;
    }

    public final void setFollow_status(int i4) {
        this.follow_status = i4;
    }

    public final void setProgress(EpisodeProgressModel episodeProgressModel) {
        this.progress = episodeProgressModel;
    }

    public String toString() {
        return "SeriesUserState(follow_status=" + this.follow_status + ", follow=" + this.follow + ", progress=" + this.progress + ')';
    }
}
