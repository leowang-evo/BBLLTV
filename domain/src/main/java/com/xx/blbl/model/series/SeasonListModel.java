package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SeasonListModel implements Serializable {

    @SerializedName("badge_info")
    private BadgeInfoModel badge_info;

    @SerializedName("media_id")
    private long media_id;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("season_type")
    private int season_type;

    @SerializedName("season_title")
    private String season_title = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("badge")
    private String badge = "";

    @SerializedName("horizontal_cover_1610")
    private String horizontal_cover_1610 = "";

    @SerializedName("horizontal_cover_169")
    private String horizontal_cover_169 = "";

    public final String getBadge() {
        return this.badge;
    }

    public final BadgeInfoModel getBadge_info() {
        return this.badge_info;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getHorizontal_cover_1610() {
        return this.horizontal_cover_1610;
    }

    public final String getHorizontal_cover_169() {
        return this.horizontal_cover_169;
    }

    public final long getMedia_id() {
        return this.media_id;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final String getSeason_title() {
        return this.season_title;
    }

    public final int getSeason_type() {
        return this.season_type;
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

    public final void setHorizontal_cover_1610(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.horizontal_cover_1610 = str;
    }

    public final void setHorizontal_cover_169(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.horizontal_cover_169 = str;
    }

    public final void setMedia_id(long j7) {
        this.media_id = j7;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setSeason_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.season_title = str;
    }

    public final void setSeason_type(int i4) {
        this.season_type = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SeasonModel(season_id=");
        sb.append(this.season_id);
        sb.append(", season_title='");
        sb.append(this.season_title);
        sb.append("', season_type=");
        sb.append(this.season_type);
        sb.append(", media_id=");
        sb.append(this.media_id);
        sb.append(", cover='");
        sb.append(this.cover);
        sb.append("', badge='");
        sb.append(this.badge);
        sb.append("', badge_info=");
        sb.append(this.badge_info);
        sb.append(", horizontal_cover_1610='");
        sb.append(this.horizontal_cover_1610);
        sb.append("', horizontal_cover_169='");
        return DomainSupport.appendString(sb, this.horizontal_cover_169, "')");
    }
}
