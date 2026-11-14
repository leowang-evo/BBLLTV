package com.xx.blbl.network.response;

import com.xx.blbl.model.video.VideoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UserDynamicResponse implements Serializable {

    @SerializedName("archives")
    private List<VideoModel> archives;

    @SerializedName("has_more")
    private boolean has_more;

    public final List<VideoModel> getArchives() {
        return this.archives;
    }

    public final boolean getHas_more() {
        return this.has_more;
    }

    public final void setArchives(List<VideoModel> list) {
        this.archives = list;
    }

    public final void setHas_more(boolean z7) {
        this.has_more = z7;
    }

    public String toString() {
        return "UserDynamicResponse(archives=" + this.archives + ", has_more=" + this.has_more + ')';
    }
}
