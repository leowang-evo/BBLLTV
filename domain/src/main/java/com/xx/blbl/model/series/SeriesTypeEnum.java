package com.xx.blbl.model.series;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum SeriesTypeEnum {
    Anime(1, "番剧"),
    ChinaAnime(4, "国创"),
    Movie(2, "电影"),
    Variety(7, "综艺"),
    Drama(5, "电视剧"),
    Documentary(3, "纪录片");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String showName;
    private final int type;

    SeriesTypeEnum(int i4, String str) {
        this.type = i4;
        this.showName = str;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final String getShowName() {
        return this.showName;
    }

    public final int getType() {
        return this.type;
    }
}
