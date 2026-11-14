package com.xx.blbl.model.series;

import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AllSeriesFilterModel implements Serializable {
    private int currentSelect;
    private int iconResourceId;
    private List<AllSeriesFilterOption> options;
    private String title = "";
    private String key = "";

    public final int getCurrentSelect() {
        return this.currentSelect;
    }

    public final int getIconResourceId() {
        return this.iconResourceId;
    }

    public final String getKey() {
        return this.key;
    }

    public final List<AllSeriesFilterOption> getOptions() {
        return this.options;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setCurrentSelect(int i4) {
        this.currentSelect = i4;
    }

    public final void setIconResourceId(int i4) {
        this.iconResourceId = i4;
    }

    public final void setKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.key = str;
    }

    public final void setOptions(List<AllSeriesFilterOption> list) {
        this.options = list;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }
}
