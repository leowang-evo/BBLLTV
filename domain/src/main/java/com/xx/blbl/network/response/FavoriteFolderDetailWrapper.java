package com.xx.blbl.network.response;

import com.xx.blbl.model.favorite.FolderDetailModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FavoriteFolderDetailWrapper implements Serializable {

    @SerializedName("has_more")
    private boolean has_more;

    @SerializedName("info")
    private FolderDetailModel info;

    @SerializedName("medias")
    private List<HistoryVideoModel> medias;

    public final boolean getHas_more() {
        return this.has_more;
    }

    public final FolderDetailModel getInfo() {
        return this.info;
    }

    public final List<HistoryVideoModel> getMedias() {
        return this.medias;
    }

    public final void setHas_more(boolean z7) {
        this.has_more = z7;
    }

    public final void setInfo(FolderDetailModel folderDetailModel) {
        this.info = folderDetailModel;
    }

    public final void setMedias(List<HistoryVideoModel> list) {
        this.medias = list;
    }

    public String toString() {
        return "FavoriteFolderDetailWrapper(has_more=" + this.has_more + ", medias=" + this.medias + ", info=" + this.info + ')';
    }
}
