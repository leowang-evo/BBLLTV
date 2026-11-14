package com.xx.blbl.model.video;

import com.xx.blbl.model.favorite.FolderStatModel;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryVideoModel implements Serializable {

    @SerializedName("cnt_info")
    private FolderStatModel cnt_info;

    @SerializedName("covers")
    private ArrayList<String> covers;

    @SerializedName("duration")
    private long duration;

    @SerializedName("fav_time")
    private long fav_time;

    @SerializedName("history")
    private HistoryModel history;

    @SerializedName("is_fav")
    private int is_fav;

    @SerializedName("is_finish")
    private int is_finish;

    @SerializedName("kid")
    private long kid;

    @SerializedName("live_status")
    private int live_status;

    @SerializedName("progress")
    private long progress;

    @SerializedName("total")
    private int total;

    @SerializedName("videos")
    private int videos;

    @SerializedName("view_at")
    private long view_at;

    @SerializedName("title")
    private String title = "";

    @SerializedName("long_title")
    private String long_title = "";

    @SerializedName("bvid")
    private String bvid = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("uri")
    private String uri = "";

    @SerializedName("author_name")
    private String author_name = "";

    @SerializedName("author_face")
    private String author_face = "";

    @SerializedName("author_mid")
    private String author_mid = "";

    @SerializedName("badge")
    private String badge = "";

    @SerializedName("show_title")
    private String show_title = "";

    @SerializedName("new_desc")
    private String new_desc = "";

    @SerializedName("tag_name")
    private String tag_name = "";

    public final String getAuthor_face() {
        return this.author_face;
    }

    public final String getAuthor_mid() {
        return this.author_mid;
    }

    public final String getAuthor_name() {
        return this.author_name;
    }

    public final String getBadge() {
        return this.badge;
    }

    public final String getBvid() {
        return this.bvid;
    }

    public final FolderStatModel getCnt_info() {
        return this.cnt_info;
    }

    public final String getCover() {
        return this.cover;
    }

    public final ArrayList<String> getCovers() {
        return this.covers;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final long getFav_time() {
        return this.fav_time;
    }

    public final HistoryModel getHistory() {
        return this.history;
    }

    public final long getKid() {
        return this.kid;
    }

    public final int getLive_status() {
        return this.live_status;
    }

    public final String getLong_title() {
        return this.long_title;
    }

    public final String getNew_desc() {
        return this.new_desc;
    }

    public final long getProgress() {
        return this.progress;
    }

    public final String getShow_title() {
        return this.show_title;
    }

    public final String getTag_name() {
        return this.tag_name;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTotal() {
        return this.total;
    }

    public final String getUri() {
        return this.uri;
    }

    public final int getVideos() {
        return this.videos;
    }

    public final long getView_at() {
        return this.view_at;
    }

    public final int is_fav() {
        return this.is_fav;
    }

    public final int is_finish() {
        return this.is_finish;
    }

    public final void setAuthor_face(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.author_face = str;
    }

    public final void setAuthor_mid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.author_mid = str;
    }

    public final void setAuthor_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.author_name = str;
    }

    public final void setBadge(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.badge = str;
    }

    public final void setBvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bvid = str;
    }

    public final void setCnt_info(FolderStatModel folderStatModel) {
        this.cnt_info = folderStatModel;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setCovers(ArrayList<String> arrayList) {
        this.covers = arrayList;
    }

    public final void setDuration(long j7) {
        this.duration = j7;
    }

    public final void setFav_time(long j7) {
        this.fav_time = j7;
    }

    public final void setHistory(HistoryModel historyModel) {
        this.history = historyModel;
    }

    public final void setKid(long j7) {
        this.kid = j7;
    }

    public final void setLive_status(int i4) {
        this.live_status = i4;
    }

    public final void setLong_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.long_title = str;
    }

    public final void setNew_desc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.new_desc = str;
    }

    public final void setProgress(long j7) {
        this.progress = j7;
    }

    public final void setShow_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.show_title = str;
    }

    public final void setTag_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tag_name = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setTotal(int i4) {
        this.total = i4;
    }

    public final void setUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uri = str;
    }

    public final void setVideos(int i4) {
        this.videos = i4;
    }

    public final void setView_at(long j7) {
        this.view_at = j7;
    }

    public final void set_fav(int i4) {
        this.is_fav = i4;
    }

    public final void set_finish(int i4) {
        this.is_finish = i4;
    }

    public String toString() {
        return "HistoryVideoModel(title='" + this.title + "', long_title='" + this.long_title + "', bvid='" + this.bvid + "', cover='" + this.cover + "', covers=" + this.covers + ", uri='" + this.uri + "', history=" + this.history + ", videos=" + this.videos + ", author_name='" + this.author_name + "', author_face='" + this.author_face + "', author_mid='" + this.author_mid + "', view_at=" + this.view_at + ", progress=" + this.progress + ", badge='" + this.badge + "', show_title='" + this.show_title + "', duration=" + this.duration + ", total=" + this.total + ", new_desc='" + this.new_desc + "', is_finish=" + this.is_finish + ", is_fav=" + this.is_fav + ", kid=" + this.kid + ", tag_name='" + this.tag_name + "', fav_time=" + this.fav_time + ", live_status=" + this.live_status + ", cnt_info=" + this.cnt_info + ')';
    }
}
