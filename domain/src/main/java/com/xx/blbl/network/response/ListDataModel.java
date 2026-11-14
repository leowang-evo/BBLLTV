package com.xx.blbl.network.response;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ListDataModel<T> {

    @SerializedName("list")
    private ArrayList<T> list;

    @SerializedName("no_more")
    private boolean no_more;

    public final ArrayList<T> getList() {
        return this.list;
    }

    public final boolean getNo_more() {
        return this.no_more;
    }

    public final void setList(ArrayList<T> arrayList) {
        this.list = arrayList;
    }

    public final void setNo_more(boolean z7) {
        this.no_more = z7;
    }

    public String toString() {
        return "ListDataModel(list=" + this.list + ", no_more=" + this.no_more + ')';
    }
}
