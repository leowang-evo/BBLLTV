package com.xx.blbl.model.search;

import com.xx.blbl.model.series.BadgeInfoModel;
import com.xx.blbl.ui.adapter.search.SearchLiveWatchedStat;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchItemModel implements Serializable {

    @SerializedName("aid")
    private long aid;

    @SerializedName("badges")
    private List<BadgeInfoModel> badges;

    @SerializedName("danmaku")
    private long danmaku;

    @SerializedName("favorites")
    private long favorites;

    @SerializedName("id")
    private long id;

    @SerializedName("media_id")
    private long media_id;

    @SerializedName("online")
    private long online;

    @SerializedName("pgc_season_id")
    private long pgc_season_id;

    @SerializedName("play")
    private long play;

    @SerializedName("pubdate")
    private long pubdate;

    @SerializedName("review")
    private long review;

    @SerializedName("roomid")
    private long roomid;

    @SerializedName("season_type")
    private int season_type;

    @SerializedName("video_review")
    private long video_review;

    @SerializedName("watched_show")
    private SearchLiveWatchedStat watched_show;

    @SerializedName("type")
    private String type = "";

    @SerializedName("author")
    private String author = "";

    @SerializedName("mid")
    private String mid = "";

    @SerializedName("usign")
    private String usign = "";

    @SerializedName("typeid")
    private String typeid = "";

    @SerializedName("typename")
    private String typename = "";

    @SerializedName("arcurl")
    private String arcurl = "";

    @SerializedName("bvid")
    private String bvid = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("pic")
    private String pic = "";

    @SerializedName("tag")
    private String tag = "";

    @SerializedName("duration")
    private String duration = "";

    @SerializedName("like")
    private String like = "";

    @SerializedName("upic")
    private String upic = "";

    @SerializedName("uname")
    private String uname = "";

    @SerializedName("index_show")
    private String index_show = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("desc")
    private String desc = "";

    public final long getAid() {
        return this.aid;
    }

    public final String getArcurl() {
        return this.arcurl;
    }

    public final String getAuthor() {
        return this.author;
    }

    public final List<BadgeInfoModel> getBadges() {
        return this.badges;
    }

    public final String getBvid() {
        return this.bvid;
    }

    public final String getCover() {
        return this.cover;
    }

    public final long getDanmaku() {
        return this.danmaku;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final long getFavorites() {
        return this.favorites;
    }

    public final long getId() {
        return this.id;
    }

    public final String getIndex_show() {
        return this.index_show;
    }

    public final String getLike() {
        return this.like;
    }

    public final long getMedia_id() {
        return this.media_id;
    }

    public final String getMid() {
        return this.mid;
    }

    public final long getOnline() {
        return this.online;
    }

    public final long getPgc_season_id() {
        return this.pgc_season_id;
    }

    public final String getPic() {
        return this.pic;
    }

    public final long getPlay() {
        return this.play;
    }

    public final long getPubdate() {
        return this.pubdate;
    }

    public final long getReview() {
        return this.review;
    }

    public final long getRoomid() {
        return this.roomid;
    }

    public final int getSeason_type() {
        return this.season_type;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public final String getTypeid() {
        return this.typeid;
    }

    public final String getTypename() {
        return this.typename;
    }

    public final String getUname() {
        return this.uname;
    }

    public final String getUpic() {
        return this.upic;
    }

    public final String getUsign() {
        return this.usign;
    }

    public final long getVideo_review() {
        return this.video_review;
    }

    public final SearchLiveWatchedStat getWatched_show() {
        return this.watched_show;
    }

    public final void setAid(long j7) {
        this.aid = j7;
    }

    public final void setArcurl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.arcurl = str;
    }

    public final void setAuthor(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.author = str;
    }

    public final void setBadges(List<BadgeInfoModel> list) {
        this.badges = list;
    }

    public final void setBvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bvid = str;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setDanmaku(long j7) {
        this.danmaku = j7;
    }

    public final void setDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.desc = str;
    }

    public final void setDuration(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.duration = str;
    }

    public final void setFavorites(long j7) {
        this.favorites = j7;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setIndex_show(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.index_show = str;
    }

    public final void setLike(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.like = str;
    }

    public final void setMedia_id(long j7) {
        this.media_id = j7;
    }

    public final void setMid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mid = str;
    }

    public final void setOnline(long j7) {
        this.online = j7;
    }

    public final void setPgc_season_id(long j7) {
        this.pgc_season_id = j7;
    }

    public final void setPic(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pic = str;
    }

    public final void setPlay(long j7) {
        this.play = j7;
    }

    public final void setPubdate(long j7) {
        this.pubdate = j7;
    }

    public final void setReview(long j7) {
        this.review = j7;
    }

    public final void setRoomid(long j7) {
        this.roomid = j7;
    }

    public final void setSeason_type(int i4) {
        this.season_type = i4;
    }

    public final void setTag(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tag = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    public final void setTypeid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.typeid = str;
    }

    public final void setTypename(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.typename = str;
    }

    public final void setUname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uname = str;
    }

    public final void setUpic(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.upic = str;
    }

    public final void setUsign(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.usign = str;
    }

    public final void setVideo_review(long j7) {
        this.video_review = j7;
    }

    public final void setWatched_show(SearchLiveWatchedStat searchLiveWatchedStat) {
        this.watched_show = searchLiveWatchedStat;
    }

    public String toString() {
        return "SearchItemModel(type='" + this.type + "', id=" + this.id + ", author='" + this.author + "', mid='" + this.mid + "', typeid=" + this.typeid + ", typename='" + this.typename + "', arcurl='" + this.arcurl + "', aid=" + this.aid + ", bvid='" + this.bvid + "', title='" + this.title + "', pic='" + this.pic + "', play=" + this.play + ", video_review=" + this.video_review + ", favorites=" + this.favorites + ", tag='" + this.tag + "', review=" + this.review + ", pubdate=" + this.pubdate + ", duration='" + this.duration + "', like='" + this.like + "', upic='" + this.upic + "', uname='" + this.uname + "', danmaku=" + this.danmaku + ", index_show='" + this.index_show + "', media_id=" + this.media_id + ", pgc_season_id=" + this.pgc_season_id + ", season_type=" + this.season_type + ", cover='" + this.cover + "', badges=" + this.badges + ", roomid=" + this.roomid + ", desc='" + this.desc + "', watched_show=" + this.watched_show + ')';
    }
}
