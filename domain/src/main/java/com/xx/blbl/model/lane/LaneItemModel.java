package com.xx.blbl.model.lane;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.series.AnimEpisodeInfoModel;
import com.xx.blbl.model.series.BadgeInfoModel;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LaneItemModel implements Serializable {

    @SerializedName("badge_info")
    private BadgeInfoModel badge_info;

    @SerializedName("link_type")
    private int link_type;

    @SerializedName("link_value")
    private int link_value;

    @SerializedName("new_ep")
    private AnimEpisodeInfoModel new_ep;

    @SerializedName("oid")
    private long oid;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("season_type")
    private int season_type;

    @SerializedName("stat")
    private LaneStatModel stat;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("desc")
    private String desc = "";

    @SerializedName("link")
    private String link = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("subTitle")
    private String subTitle = "";

    public final BadgeInfoModel getBadge_info() {
        return this.badge_info;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getLink() {
        return this.link;
    }

    public final int getLink_type() {
        return this.link_type;
    }

    public final int getLink_value() {
        return this.link_value;
    }

    public final AnimEpisodeInfoModel getNew_ep() {
        return this.new_ep;
    }

    public final long getOid() {
        return this.oid;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final int getSeason_type() {
        return this.season_type;
    }

    public final LaneStatModel getStat() {
        return this.stat;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setBadge_info(BadgeInfoModel badgeInfoModel) {
        this.badge_info = badgeInfoModel;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.desc = str;
    }

    public final void setLink(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.link = str;
    }

    public final void setLink_type(int i4) {
        this.link_type = i4;
    }

    public final void setLink_value(int i4) {
        this.link_value = i4;
    }

    public final void setNew_ep(AnimEpisodeInfoModel animEpisodeInfoModel) {
        this.new_ep = animEpisodeInfoModel;
    }

    public final void setOid(long j7) {
        this.oid = j7;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setSeason_type(int i4) {
        this.season_type = i4;
    }

    public final void setStat(LaneStatModel laneStatModel) {
        this.stat = laneStatModel;
    }

    public final void setSubTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subTitle = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LaneItemModel(badge_info=");
        sb.append(this.badge_info);
        sb.append(", cover='");
        sb.append(this.cover);
        sb.append("', desc='");
        sb.append(this.desc);
        sb.append("', link='");
        sb.append(this.link);
        sb.append("', link_type=");
        sb.append(this.link_type);
        sb.append(", link_value=");
        sb.append(this.link_value);
        sb.append(", new_ep=");
        sb.append(this.new_ep);
        sb.append(", oid=");
        sb.append(this.oid);
        sb.append(", season_id=");
        sb.append(this.season_id);
        sb.append(", season_type=");
        sb.append(this.season_type);
        sb.append(", stat=");
        sb.append(this.stat);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', subTitle='");
        return DomainSupport.appendString(sb, this.subTitle, "')");
    }
}
