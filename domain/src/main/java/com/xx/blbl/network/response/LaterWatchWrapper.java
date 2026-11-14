package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.video.VideoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LaterWatchWrapper implements Serializable {

    @SerializedName("count")
    private int count;

    @SerializedName("list")
    private List<VideoModel> list;

    public final int getCount() {
        return this.count;
    }

    public final List<VideoModel> getList() {
        return this.list;
    }

    public final void setCount(int i4) {
        this.count = i4;
    }

    public final void setList(List<VideoModel> list) {
        this.list = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LaterWatchWrapper(count=");
        sb.append(this.count);
        sb.append(", list=");
        return DomainSupport.appendList(sb, this.list, ')');
    }
}
