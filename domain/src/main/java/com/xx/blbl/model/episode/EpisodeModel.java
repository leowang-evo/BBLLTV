package com.xx.blbl.model.episode;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.series.BadgeInfoModel;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodeModel implements Serializable {

    @SerializedName("aid")
    private long aid;

    @SerializedName("badge_info")
    private BadgeInfoModel badge_info;

    @SerializedName("cid")
    private long cid;

    @SerializedName("id")
    private long id;

    @SerializedName("pub_time")
    private long pub_time;

    @SerializedName("badge")
    private String badge = "";

    @SerializedName("bvid")
    private String bvid = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("link")
    private String link = "";

    @SerializedName("long_title")
    private String long_title = "";

    @SerializedName("share_url")
    private String share_url = "";

    @SerializedName("short_link")
    private String short_link = "";

    @SerializedName("subtitle")
    private String subtitle = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("vid")
    private String vid = "";

    public final long getAid() {
        return this.aid;
    }

    public final String getBadge() {
        return this.badge;
    }

    public final BadgeInfoModel getBadge_info() {
        return this.badge_info;
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

    public final long getId() {
        return this.id;
    }

    public final String getLink() {
        return this.link;
    }

    public final String getLong_title() {
        return this.long_title;
    }

    public final long getPub_time() {
        return this.pub_time;
    }

    public final String getShare_url() {
        return this.share_url;
    }

    public final String getShort_link() {
        return this.short_link;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getVid() {
        return this.vid;
    }

    public final void setAid(long j7) {
        this.aid = j7;
    }

    public final void setBadge(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.badge = str;
    }

    public final void setBadge_info(BadgeInfoModel badgeInfoModel) {
        this.badge_info = badgeInfoModel;
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

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setLink(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.link = str;
    }

    public final void setLong_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.long_title = str;
    }

    public final void setPub_time(long j7) {
        this.pub_time = j7;
    }

    public final void setShare_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.share_url = str;
    }

    public final void setShort_link(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.short_link = str;
    }

    public final void setSubtitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitle = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setVid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vid = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EpisodeModel(aid=");
        sb.append(this.aid);
        sb.append(", badge='");
        sb.append(this.badge);
        sb.append("', bvid='");
        sb.append(this.bvid);
        sb.append("', cid=");
        sb.append(this.cid);
        sb.append(", cover='");
        sb.append(this.cover);
        sb.append("', id=");
        sb.append(this.id);
        sb.append(", link='");
        sb.append(this.link);
        sb.append("', long_title='");
        sb.append(this.long_title);
        sb.append("', pub_time=");
        sb.append(this.pub_time);
        sb.append(", share_url='");
        sb.append(this.share_url);
        sb.append("', short_link='");
        sb.append(this.short_link);
        sb.append("', subtitle='");
        sb.append(this.subtitle);
        sb.append("', title='");
        sb.append(this.title);
        sb.append("', vid='");
        return DomainSupport.appendString(sb, this.vid, "')");
    }
}
