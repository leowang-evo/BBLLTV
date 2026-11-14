package com.xx.blbl.model.live;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum LiveQuality {
    Fluent(2, "流畅"),
    StandardDefinition(3, "高清"),
    HighDefinition(4, "原画");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int code;
    private final String showName;

    LiveQuality(int i4, String str) {
        this.code = i4;
        this.showName = str;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getShowName() {
        return this.showName;
    }
}
