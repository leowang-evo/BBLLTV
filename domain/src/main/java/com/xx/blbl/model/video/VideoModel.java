package com.xx.blbl.model.video;

import com.xx.blbl.model.series.UgcSeriesModel;
import com.xx.blbl.model.user.OwnerModel;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoModel implements Serializable {

    @SerializedName("aid")
    private long aid;

    @SerializedName("bangumi")
    private BangumiModel bangumi;

    @SerializedName("cid")
    private long cid;

    @SerializedName("duration")
    private long duration;

    @SerializedName("epid")
    private long epid;

    @SerializedName("id")
    private long id;

    @SerializedName("owner")
    private OwnerModel owner;

    @SerializedName("pages")
    private ArrayList<VideoPvModel> pages;
    private long progress;

    @SerializedName("pub_time")
    private long pub_time;

    @SerializedName("pubdate")
    private long pubdate;

    @SerializedName("rights")
    private VideoRight rights;
    private long sid;

    @SerializedName("stat")
    private VideoStatModel stat;

    @SerializedName("ugc_season")
    private UgcSeriesModel ugc_season;

    @SerializedName("title")
    private String title = "";

    @SerializedName("bvid")
    private String bvid = "";
    private String mediaId = "";

    @SerializedName("pic")
    private String pic = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("desc")
    private String desc = "";

    @SerializedName("redirect_url")
    private String redirect_url = "";

    @SerializedName("first_frame")
    private String first_frame = "";

    public final long getAid() {
        return this.aid;
    }

    public final BangumiModel getBangumi() {
        return this.bangumi;
    }

    public final String getBvid() {
        return this.bvid;
    }

    public final long getCid() {
        return this.cid;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final long getEpid() {
        return this.epid;
    }

    public final String getFirst_frame() {
        return this.first_frame;
    }

    public final long getId() {
        return this.id;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final OwnerModel getOwner() {
        return this.owner;
    }

    public final ArrayList<VideoPvModel> getPages() {
        return this.pages;
    }

    public final String getPic() {
        return this.pic;
    }

    public final long getProgress() {
        return this.progress;
    }

    public final long getPub_time() {
        return this.pub_time;
    }

    public final long getPubdate() {
        return this.pubdate;
    }

    public final String getRedirect_url() {
        return this.redirect_url;
    }

    public final VideoRight getRights() {
        return this.rights;
    }

    public final long getSid() {
        return this.sid;
    }

    public final VideoStatModel getStat() {
        return this.stat;
    }

    public final String getTitle() {
        return this.title;
    }

    public final UgcSeriesModel getUgc_season() {
        return this.ugc_season;
    }

    public final void setAid(long j7) {
        this.aid = j7;
    }

    public final void setBangumi(BangumiModel bangumiModel) {
        this.bangumi = bangumiModel;
    }

    public final void setBvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bvid = str;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.desc = str;
    }

    public final void setDuration(long j7) {
        this.duration = j7;
    }

    public final void setEpid(long j7) {
        this.epid = j7;
    }

    public final void setFirst_frame(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.first_frame = str;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setMediaId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mediaId = str;
    }

    public final void setOwner(OwnerModel ownerModel) {
        this.owner = ownerModel;
    }

    public final void setPages(ArrayList<VideoPvModel> arrayList) {
        this.pages = arrayList;
    }

    public final void setPic(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pic = str;
    }

    public final void setProgress(long j7) {
        this.progress = j7;
    }

    public final void setPub_time(long j7) {
        this.pub_time = j7;
    }

    public final void setPubdate(long j7) {
        this.pubdate = j7;
    }

    public final void setRedirect_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.redirect_url = str;
    }

    public final void setRights(VideoRight videoRight) {
        this.rights = videoRight;
    }

    public final void setSid(long j7) {
        this.sid = j7;
    }

    public final void setStat(VideoStatModel videoStatModel) {
        this.stat = videoStatModel;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setUgc_season(UgcSeriesModel ugcSeriesModel) {
        this.ugc_season = ugcSeriesModel;
    }

    public String toString() {
        return "VideoModel(title='" + this.title + "', bvid='" + this.bvid + "', cid=" + this.cid + ", aid=" + this.aid + ", id=" + this.id + ", epid=" + this.epid + ", sid=" + this.sid + ", mediaId='" + this.mediaId + "', pic='" + this.pic + "', cover='" + this.cover + "', pubdate=" + this.pubdate + ", pub_time=" + this.pub_time + ", desc='" + this.desc + "', duration=" + this.duration + ", redirect_url='" + this.redirect_url + "', owner=" + this.owner + ", stat=" + this.stat + ", pages=" + this.pages + ", bangumi=" + this.bangumi + ", first_frame='" + this.first_frame + "', progress=" + this.progress + ", ugc_season=" + this.ugc_season + ", rights=" + this.rights + ')';
    }
}
