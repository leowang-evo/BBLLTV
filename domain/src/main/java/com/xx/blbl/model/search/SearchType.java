package com.xx.blbl.model.search;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum SearchType {
    Video("video"),
    Animation("media_bangumi"),
    FilmAndTv("media_ft"),
    LiveRoom("live_room"),
    User("bili_user");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String value;

    SearchType(String str) {
        this.value = str;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public final String getValue() {
        return this.value;
    }
}
