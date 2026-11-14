package com.xx.blbl.network.response;

import com.xx.blbl.model.common.CursorModel;
import com.xx.blbl.model.common.TabModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryListResponse implements Serializable {

    @SerializedName("cursor")
    private CursorModel cursor;

    @SerializedName("list")
    private ArrayList<HistoryVideoModel> list;

    @SerializedName("tab")
    private ArrayList<TabModel> tab;

    public final CursorModel getCursor() {
        return this.cursor;
    }

    public final ArrayList<HistoryVideoModel> getList() {
        return this.list;
    }

    public final ArrayList<TabModel> getTab() {
        return this.tab;
    }

    public final void setCursor(CursorModel cursorModel) {
        this.cursor = cursorModel;
    }

    public final void setList(ArrayList<HistoryVideoModel> arrayList) {
        this.list = arrayList;
    }

    public final void setTab(ArrayList<TabModel> arrayList) {
        this.tab = arrayList;
    }
}
