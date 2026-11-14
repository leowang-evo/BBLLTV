package com.xx.blbl.model.dm;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum DmScreenArea {
    OneEighth(-1, "1/8"),
    OneSixth(0, "1/6"),
    Quarter(1, "1/4"),
    Half(3, "1/2"),
    ThreeQuarter(7, "3/4"),
    Full(15, "全屏");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int area;
    private final String showName;

    DmScreenArea(int i4, String str) {
        this.area = i4;
        this.showName = str;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final int getArea() {
        return this.area;
    }

    public final String getShowName() {
        return this.showName;
    }
}
