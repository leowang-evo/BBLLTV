package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SeriesModel implements Serializable {

    @SerializedName("badge_info")
    private BadgeInfoModel badge_info;

    @SerializedName("first_ep")
    private long first_ep;

    @SerializedName("first_ep_info")
    private AnimEpisodeInfoModel first_ep_info;

    @SerializedName("media_attr")
    private long media_attr;

    @SerializedName("new_ep")
    private AnimEpisodeInfoModel new_ep;

    @SerializedName("season_attr")
    private long season_attr;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("badge")
    private String badge = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("evaluate")
    private String evaluate = "";

    @SerializedName("horizontal_cover_16_9")
    private String horizontal_cover_16_9 = "";

    @SerializedName("media_id")
    private String media_id = "";

    @SerializedName("season_title")
    private String season_title = "";

    @SerializedName("summary")
    private String summary = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("url")
    private String url = "";

    @SerializedName("progress")
    private String progress = "";

    public final String getBadge() {
        return this.badge;
    }

    public final BadgeInfoModel getBadge_info() {
        return this.badge_info;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getEvaluate() {
        return this.evaluate;
    }

    public final long getFirst_ep() {
        return this.first_ep;
    }

    public final AnimEpisodeInfoModel getFirst_ep_info() {
        return this.first_ep_info;
    }

    public final String getHorizontal_cover_16_9() {
        return this.horizontal_cover_16_9;
    }

    public final long getMedia_attr() {
        return this.media_attr;
    }

    public final String getMedia_id() {
        return this.media_id;
    }

    public final AnimEpisodeInfoModel getNew_ep() {
        return this.new_ep;
    }

    public final String getProgress() {
        return this.progress;
    }

    public final long getSeason_attr() {
        return this.season_attr;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final String getSeason_title() {
        return this.season_title;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setBadge(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.badge = str;
    }

    public final void setBadge_info(BadgeInfoModel badgeInfoModel) {
        this.badge_info = badgeInfoModel;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setEvaluate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.evaluate = str;
    }

    public final void setFirst_ep(long j7) {
        this.first_ep = j7;
    }

    public final void setFirst_ep_info(AnimEpisodeInfoModel animEpisodeInfoModel) {
        this.first_ep_info = animEpisodeInfoModel;
    }

    public final void setHorizontal_cover_16_9(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.horizontal_cover_16_9 = str;
    }

    public final void setMedia_attr(long j7) {
        this.media_attr = j7;
    }

    public final void setMedia_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.media_id = str;
    }

    public final void setNew_ep(AnimEpisodeInfoModel animEpisodeInfoModel) {
        this.new_ep = animEpisodeInfoModel;
    }

    public final void setProgress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.progress = str;
    }

    public final void setSeason_attr(long j7) {
        this.season_attr = j7;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setSeason_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.season_title = str;
    }

    public final void setSummary(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.summary = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MyFollowingSeriesModel(badge='");
        sb.append(this.badge);
        sb.append("', badge_info=");
        sb.append(this.badge_info);
        sb.append(", cover='");
        sb.append(this.cover);
        sb.append("', evaluate='");
        sb.append(this.evaluate);
        sb.append("', first_ep=");
        sb.append(this.first_ep);
        sb.append(", first_ep_info=");
        sb.append(this.first_ep_info);
        sb.append(", horizontal_cover_16_9='");
        sb.append(this.horizontal_cover_16_9);
        sb.append("', media_attr=");
        sb.append(this.media_attr);
        sb.append(", media_id=");
        sb.append(this.media_id);
        sb.append(", new_ep=");
        sb.append(this.new_ep);
        sb.append(", season_attr=");
        sb.append(this.season_attr);
        sb.append(", season_id='");
        sb.append(this.season_id);
        sb.append("', summary='");
        sb.append(this.summary);
        sb.append("', title='");
        sb.append(this.title);
        sb.append("', url='");
        sb.append(this.url);
        sb.append("', progress='");
        return DomainSupport.appendString(sb, this.progress, "')");
    }
}
