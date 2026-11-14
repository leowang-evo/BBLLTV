package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.lane.LaneItemModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GetAllSeriesWrapper implements Serializable {

    @SerializedName("list")
    private List<LaneItemModel> list;

    public final List<LaneItemModel> getList() {
        return this.list;
    }

    public final void setList(List<LaneItemModel> list) {
        this.list = list;
    }

    public String toString() {
        return DomainSupport.appendList(new StringBuilder("GetAllSeriesWrapper(list="), this.list, ')');
    }
}
