package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.video.VideoModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SectionModel implements Serializable {

    @SerializedName("episode_id")
    private long episode_id;

    @SerializedName("episodes")
    private List<VideoModel> episodes;

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title = "";

    @SerializedName("type")
    private int type;

    public final long getEpisode_id() {
        return this.episode_id;
    }

    public final List<VideoModel> getEpisodes() {
        return this.episodes;
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getType() {
        return this.type;
    }

    public final void setEpisode_id(long j7) {
        this.episode_id = j7;
    }

    public final void setEpisodes(List<VideoModel> list) {
        this.episodes = list;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SectionModel(episode_id=");
        sb.append(this.episode_id);
        sb.append(", episodes=");
        sb.append(this.episodes);
        sb.append(", id=");
        sb.append(this.id);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", title='");
        return DomainSupport.appendString(sb, this.title, "')");
    }
}
