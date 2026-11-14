package com.xx.blbl.model.series;

import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.episode.NewEpisodeInfoModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodesDetailModel implements Serializable {

    @SerializedName("episodes")
    private List<EpisodeModel> episodes;

    @SerializedName("media_id")
    private long media_id;

    @SerializedName("new_ep")
    private NewEpisodeInfoModel new_ep;

    @SerializedName("rating")
    private EpisodesRatingModel rating;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("seasons")
    private List<SeriesModel> seasons;

    @SerializedName("section")
    private List<SectionModel> section;

    @SerializedName("stat")
    private com.xx.blbl.model.episode.EpisodeStatModel stat;

    @SerializedName("total")
    private int total;

    @SerializedName("type")
    private int type;

    @SerializedName("user_status")
    private SeriesUserState user_status;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("evaluate")
    private String evaluate = "";

    @SerializedName("season_title")
    private String season_title = "";

    @SerializedName("share_url")
    private String share_url = "";

    @SerializedName("subtitle")
    private String subtitle = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("square_cover")
    private String square_cover = "";

    public final String getCover() {
        return this.cover;
    }

    public final List<EpisodeModel> getEpisodes() {
        return this.episodes;
    }

    public final String getEvaluate() {
        return this.evaluate;
    }

    public final long getMedia_id() {
        return this.media_id;
    }

    public final NewEpisodeInfoModel getNew_ep() {
        return this.new_ep;
    }

    public final EpisodesRatingModel getRating() {
        return this.rating;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final String getSeason_title() {
        return this.season_title;
    }

    public final List<SeriesModel> getSeasons() {
        return this.seasons;
    }

    public final List<SectionModel> getSection() {
        return this.section;
    }

    public final String getShare_url() {
        return this.share_url;
    }

    public final String getSquare_cover() {
        return this.square_cover;
    }

    public final com.xx.blbl.model.episode.EpisodeStatModel getStat() {
        return this.stat;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTotal() {
        return this.total;
    }

    public final int getType() {
        return this.type;
    }

    public final SeriesUserState getUser_status() {
        return this.user_status;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setEpisodes(List<EpisodeModel> list) {
        this.episodes = list;
    }

    public final void setEvaluate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.evaluate = str;
    }

    public final void setMedia_id(long j7) {
        this.media_id = j7;
    }

    public final void setNew_ep(NewEpisodeInfoModel newEpisodeInfoModel) {
        this.new_ep = newEpisodeInfoModel;
    }

    public final void setRating(EpisodesRatingModel episodesRatingModel) {
        this.rating = episodesRatingModel;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setSeason_title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.season_title = str;
    }

    public final void setSeasons(List<SeriesModel> list) {
        this.seasons = list;
    }

    public final void setSection(List<SectionModel> list) {
        this.section = list;
    }

    public final void setShare_url(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.share_url = str;
    }

    public final void setSquare_cover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.square_cover = str;
    }

    public final void setStat(com.xx.blbl.model.episode.EpisodeStatModel episodeStatModel) {
        this.stat = episodeStatModel;
    }

    public final void setSubtitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitle = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setTotal(int i4) {
        this.total = i4;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public final void setUser_status(SeriesUserState seriesUserState) {
        this.user_status = seriesUserState;
    }

    public String toString() {
        return "EpisodesDetailModel(cover='" + this.cover + "', evaluate='" + this.evaluate + "', media_id=" + this.media_id + ", season_id=" + this.season_id + ", season_title='" + this.season_title + "', share_url='" + this.share_url + "', subtitle='" + this.subtitle + "', title='" + this.title + "', total=" + this.total + ", type=" + this.type + ", square_cover='" + this.square_cover + "', episodes=" + this.episodes + ", seasons=" + this.seasons + ", section=" + this.section + ", stat=" + this.stat + ", new_ep=" + this.new_ep + ", user_status=" + this.user_status + ')';
    }
}
