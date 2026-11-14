package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.live.LiveAreaEntranceWrapper;
import com.xx.blbl.model.live.LiveRoomItem;
import com.xx.blbl.model.live.LiveRoomWrapper;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveListWrapper implements Serializable {

    @SerializedName("area_entrance_v2")
    private LiveAreaEntranceWrapper area_entrance_v2;

    @SerializedName("recommend_room_list")
    private List<LiveRoomItem> recommend_room_list;

    @SerializedName("room_list")
    private List<LiveRoomWrapper> room_list;

    public final LiveAreaEntranceWrapper getArea_entrance_v2() {
        return this.area_entrance_v2;
    }

    public final List<LiveRoomItem> getRecommend_room_list() {
        return this.recommend_room_list;
    }

    public final List<LiveRoomWrapper> getRoom_list() {
        return this.room_list;
    }

    public final void setArea_entrance_v2(LiveAreaEntranceWrapper liveAreaEntranceWrapper) {
        this.area_entrance_v2 = liveAreaEntranceWrapper;
    }

    public final void setRecommend_room_list(List<LiveRoomItem> list) {
        this.recommend_room_list = list;
    }

    public final void setRoom_list(List<LiveRoomWrapper> list) {
        this.room_list = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveListWrapper(area_entrance_v2=");
        sb.append(this.area_entrance_v2);
        sb.append(", room_list=");
        sb.append(this.room_list);
        sb.append(", recommend_room_list=");
        return DomainSupport.appendList(sb, this.recommend_room_list, ')');
    }
}
