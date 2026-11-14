package com.xx.blbl.model.interaction;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionStoryModel implements Serializable {

    @SerializedName("cid")
    private long cid;

    @SerializedName("cursor")
    private long cursor;

    @SerializedName("edge_id")
    private int edge_id;

    @SerializedName("is_current")
    private int is_current;

    @SerializedName("node_id")
    private int node_id;

    @SerializedName("start_pos")
    private long start_pos;

    @SerializedName("title")
    private String title = "";

    @SerializedName("cover")
    private String cover = "";

    public final long getCid() {
        return this.cid;
    }

    public final String getCover() {
        return this.cover;
    }

    public final long getCursor() {
        return this.cursor;
    }

    public final int getEdge_id() {
        return this.edge_id;
    }

    public final int getNode_id() {
        return this.node_id;
    }

    public final long getStart_pos() {
        return this.start_pos;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int is_current() {
        return this.is_current;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setCursor(long j7) {
        this.cursor = j7;
    }

    public final void setEdge_id(int i4) {
        this.edge_id = i4;
    }

    public final void setNode_id(int i4) {
        this.node_id = i4;
    }

    public final void setStart_pos(long j7) {
        this.start_pos = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void set_current(int i4) {
        this.is_current = i4;
    }

    public String toString() {
        return "InteractionStoryModel(node_id=" + this.node_id + ", edge_id=" + this.edge_id + ", title='" + this.title + "', cid=" + this.cid + ", start_pos=" + this.start_pos + ", cover='" + this.cover + "', is_current=" + this.is_current + ", cursor=" + this.cursor + ')';
    }
}
