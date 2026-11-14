package com.xx.blbl.model.lane;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LaneStatModel implements Serializable {

    @SerializedName("danmaku")
    private long danmaku;

    @SerializedName("follow")
    private long follow;

    @SerializedName("follow_view")
    private String follow_view = "";

    @SerializedName("view")
    private long view;

    public final long getDanmaku() {
        return this.danmaku;
    }

    public final long getFollow() {
        return this.follow;
    }

    public final String getFollow_view() {
        return this.follow_view;
    }

    public final long getView() {
        return this.view;
    }

    public final void setDanmaku(long j7) {
        this.danmaku = j7;
    }

    public final void setFollow(long j7) {
        this.follow = j7;
    }

    public final void setFollow_view(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.follow_view = str;
    }

    public final void setView(long j7) {
        this.view = j7;
    }

    public String toString() {
        return "LaneStatModel(danmaku=" + this.danmaku + ", follow=" + this.follow + ", follow_view='" + this.follow_view + "', view=" + this.view + ')';
    }
}
