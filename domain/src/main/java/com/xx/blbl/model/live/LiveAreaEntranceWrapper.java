package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveAreaEntranceWrapper implements Serializable {

    @SerializedName("list")
    private List<LiveAreaCategory> list;

    public final List<LiveAreaCategory> getList() {
        return this.list;
    }

    public final void setList(List<LiveAreaCategory> list) {
        this.list = list;
    }

    public String toString() {
        return DomainSupport.appendList(new StringBuilder("LiveAreaEntranceWrapper(list="), this.list, ')');
    }
}
