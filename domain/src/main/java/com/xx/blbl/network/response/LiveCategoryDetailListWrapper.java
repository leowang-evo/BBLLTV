package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.live.LiveRoomItem;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveCategoryDetailListWrapper implements Serializable {

    @SerializedName("has_more")
    private int has_more;

    @SerializedName("count")
    private int count;

    public final int getCount() { return count; }

    @SerializedName("list")
    private List<LiveRoomItem> list;

    public final int getHas_more() {
        return this.has_more;
    }

    public final List<LiveRoomItem> getList() {
        return this.list;
    }

    public final void setHas_more(int i4) {
        this.has_more = i4;
    }

    public final void setList(List<LiveRoomItem> list) {
        this.list = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveCategoryDetailListWrapper(list=");
        sb.append(this.list);
        sb.append(", has_more=");
        return DomainSupport.appendInt(sb, this.has_more, ')');
    }
}
