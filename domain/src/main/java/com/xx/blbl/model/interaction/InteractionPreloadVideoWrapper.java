package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionPreloadVideoWrapper implements Serializable {

    @SerializedName("video")
    private List<InteractionPreloadVideoModel> video;

    public final List<InteractionPreloadVideoModel> getVideo() {
        return this.video;
    }

    public final void setVideo(List<InteractionPreloadVideoModel> list) {
        this.video = list;
    }

    public String toString() {
        return DomainSupport.appendList(new StringBuilder("InteractionPreloadVideoWrapper(video="), this.video, ')');
    }
}
