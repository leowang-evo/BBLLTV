package com.xx.blbl.model.player;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class DashInfoModel {

    @SerializedName("audio")
    private List<DashMediaModel> audio;

    @SerializedName("dolby")
    private DolbyModel dolby;

    @SerializedName("duration")
    private long duration;

    @SerializedName("flac")
    private FlacInfoModel flac;

    @SerializedName("minBufferTime")
    private double minBufferTime;

    @SerializedName("video")
    private List<DashMediaModel> video;

    public final List<DashMediaModel> getAudio() {
        return this.audio;
    }

    public final DolbyModel getDolby() {
        return this.dolby;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final FlacInfoModel getFlac() {
        return this.flac;
    }

    public final double getMinBufferTime() {
        return this.minBufferTime;
    }

    public final List<DashMediaModel> getVideo() {
        return this.video;
    }

    public final void setAudio(List<DashMediaModel> list) {
        this.audio = list;
    }

    public final void setDolby(DolbyModel dolbyModel) {
        this.dolby = dolbyModel;
    }

    public final void setDuration(long j7) {
        this.duration = j7;
    }

    public final void setFlac(FlacInfoModel flacInfoModel) {
        this.flac = flacInfoModel;
    }

    public final void setMinBufferTime(double d) {
        this.minBufferTime = d;
    }

    public final void setVideo(List<DashMediaModel> list) {
        this.video = list;
    }

    public String toString() {
        return "DashInfoModel(duration=" + this.duration + ", minBufferTime=" + this.minBufferTime + ", video=" + this.video + ", audio=" + this.audio + ", dolby=" + this.dolby + ", flac=" + this.flac + ')';
    }
}
