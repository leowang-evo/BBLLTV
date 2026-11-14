package com.xx.blbl.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum StartPageEnum {
    Hot(1, "热门"),
    Recommend(0, "推荐"),
    Category(2, "分区"),
    Dynamic(3, "动态"),
    History(4, "历史"),
    Collection(5, "收藏"),
    FollowingAnimation(6, "追番"),
    FollowingSeries(7, "追剧"),
    LaterWatch(8, "稍后观看");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int order;
    private final String showName;

    StartPageEnum(int i4, String str) {
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
