package com.xx.blbl.model.search;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum SearchVideoOrder {
    TotalRank("totalrank", "综合排序"),
    Click("click", "最多点击"),
    PubDate("pubdate", "最新发布"),
    Dm("dm", "最多弹幕"),
    MostCollection("stow", "最多收藏"),
    MostComment("scores", "最多评论");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String showName;
    private final String value;

    SearchVideoOrder(String str, String str2) {
        this.value = str;
        this.showName = str2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final String getShowName() {
        return this.showName;
    }

    public final String getValue() {
        return this.value;
    }
}
