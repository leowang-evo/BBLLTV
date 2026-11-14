package com.xx.blbl.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum ContinuationPlayEnum {
    Disable(0, "什么都不做"),
    Recommend(1, "播推荐视频"),
    CurrentList(2, "播列表中的下一个"),
    PlayEpisode(3, "播剧集和PV中的下一个");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int order;
    private final String showName;

    ContinuationPlayEnum(int i4, String str) {
        this.order = i4;
        this.showName = str;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String getShowName() {
        return this.showName;
    }
}
