package com.xx.blbl.model.search;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum SearchLiveOrder {
    Hot("online"),
    StartTime("live_time");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String value;

    SearchLiveOrder(String str) {
        this.value = str;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final String getValue() {
        return this.value;
    }
}
