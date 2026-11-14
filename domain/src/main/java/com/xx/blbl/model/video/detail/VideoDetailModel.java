package com.xx.blbl.model.video.detail;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.video.TagModel;
import com.xx.blbl.model.video.VideoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoDetailModel implements Serializable {

    @SerializedName("Card")
    private VideoDetailOwnerInfoModel card;

    @SerializedName("Related")
    private List<VideoModel> related;

    @SerializedName("Tags")
    private List<TagModel> tag;

    @SerializedName("View")
    private VideoModel view;

    public final VideoDetailOwnerInfoModel getCard() {
        return this.card;
    }

    public final List<VideoModel> getRelated() {
        return this.related;
    }

    public final List<TagModel> getTag() {
        return this.tag;
    }

    public final VideoModel getView() {
        return this.view;
    }

    public final void setCard(VideoDetailOwnerInfoModel videoDetailOwnerInfoModel) {
        this.card = videoDetailOwnerInfoModel;
    }

    public final void setRelated(List<VideoModel> list) {
        this.related = list;
    }

    public final void setTag(List<TagModel> list) {
        this.tag = list;
    }

    public final void setView(VideoModel videoModel) {
        this.view = videoModel;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VideoDetailModel(view=");
        sb.append(this.view);
        sb.append(", card=");
        sb.append(this.card);
        sb.append(", related=");
        return DomainSupport.appendList(sb, this.related, ')');
    }
}
