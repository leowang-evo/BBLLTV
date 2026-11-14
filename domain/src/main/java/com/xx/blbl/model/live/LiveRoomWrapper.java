package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveRoomWrapper implements Serializable {

    @SerializedName("list")
    private List<LiveRoomItem> list;

    @SerializedName("module_info")
    private LiveRoomModule module_info;

    public final List<LiveRoomItem> getList() {
        return this.list;
    }

    public final LiveRoomModule getModule_info() {
        return this.module_info;
    }

    public final void setList(List<LiveRoomItem> list) {
        this.list = list;
    }

    public final void setModule_info(LiveRoomModule liveRoomModule) {
        this.module_info = liveRoomModule;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveRoomWrapper(module_info=");
        sb.append(this.module_info);
        sb.append(", room_list=");
        return DomainSupport.appendList(sb, this.list, ')');
    }
}
