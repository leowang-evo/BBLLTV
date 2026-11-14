package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UgcSectionModel implements Serializable {

    @SerializedName("episodes")
    private List<UgcEpisodeModel> episodes;

    @SerializedName("id")
    private long id;

    @SerializedName("season_id")
    private long season_id;

    @SerializedName("title")
    private String title = "";

    public final List<UgcEpisodeModel> getEpisodes() {
        return this.episodes;
    }

    public final long getId() {
        return this.id;
    }

    public final long getSeason_id() {
        return this.season_id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setEpisodes(List<UgcEpisodeModel> list) {
        this.episodes = list;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setSeason_id(long j7) {
        this.season_id = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UgcSectionModel(id=");
        sb.append(this.id);
        sb.append(", season_id=");
        sb.append(this.season_id);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', episodes=");
        return DomainSupport.appendList(sb, this.episodes, ')');
    }
}
