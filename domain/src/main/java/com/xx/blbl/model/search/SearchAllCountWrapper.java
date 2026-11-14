package com.xx.blbl.model.search;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchAllCountWrapper implements Serializable {

    @SerializedName("bili_user")
    private SearchAllCount bili_user;

    @SerializedName("live_room")
    private SearchAllCount live_room;

    @SerializedName("media_bangumi")
    private SearchAllCount media_bangumi;

    @SerializedName("media_ft")
    private SearchAllCount media_ft;

    @SerializedName("video")
    private SearchAllCount video;

    public final SearchAllCount getBili_user() {
        return this.bili_user;
    }

    public final SearchAllCount getLive_room() {
        return this.live_room;
    }

    public final SearchAllCount getMedia_bangumi() {
        return this.media_bangumi;
    }

    public final SearchAllCount getMedia_ft() {
        return this.media_ft;
    }

    public final SearchAllCount getVideo() {
        return this.video;
    }

    public final void setBili_user(SearchAllCount searchAllCount) {
        this.bili_user = searchAllCount;
    }

    public final void setLive_room(SearchAllCount searchAllCount) {
        this.live_room = searchAllCount;
    }

    public final void setMedia_bangumi(SearchAllCount searchAllCount) {
        this.media_bangumi = searchAllCount;
    }

    public final void setMedia_ft(SearchAllCount searchAllCount) {
        this.media_ft = searchAllCount;
    }

    public final void setVideo(SearchAllCount searchAllCount) {
        this.video = searchAllCount;
    }

    public String toString() {
        return "SearchAllCountWrapper(video=" + this.video + ", bili_user=" + this.bili_user + ", live_room=" + this.live_room + ", media_bangumi=" + this.media_bangumi + ", media_ft=" + this.media_ft + ')';
    }
}
