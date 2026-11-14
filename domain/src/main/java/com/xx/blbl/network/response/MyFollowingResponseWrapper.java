package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.series.SeriesModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class MyFollowingResponseWrapper implements Serializable {

    @SerializedName("list")
    private List<SeriesModel> list;

    @SerializedName("pn")
    private int pn = 1;

    @SerializedName("ps")
    private int ps = 20;

    @SerializedName("total")
    private int total;

    public final List<SeriesModel> getList() {
        return this.list;
    }

    public final int getPn() {
        return this.pn;
    }

    public final int getPs() {
        return this.ps;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setList(List<SeriesModel> list) {
        this.list = list;
    }

    public final void setPn(int i4) {
        this.pn = i4;
    }

    public final void setPs(int i4) {
        this.ps = i4;
    }

    public final void setTotal(int i4) {
        this.total = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MyFollowingResponseWrapper(list=");
        sb.append(this.list);
        sb.append(", pn=");
        sb.append(this.pn);
        sb.append(", ps=");
        sb.append(this.ps);
        sb.append(", total=");
        return DomainSupport.appendInt(sb, this.total, ')');
    }
}
