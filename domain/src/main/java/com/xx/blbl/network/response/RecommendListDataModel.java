package com.xx.blbl.network.response;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class RecommendListDataModel<T> {

    @SerializedName("item")
    private ArrayList<T> item;

    @SerializedName("mid")
    private long mid;

    public final ArrayList<T> getItem() {
        return this.item;
    }

    public final long getMid() {
        return this.mid;
    }

    public final void setItem(ArrayList<T> arrayList) {
        this.item = arrayList;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public String toString() {
        return "RecommendListDataModel(item=" + this.item + ", mid=" + this.mid + ')';
    }
}
