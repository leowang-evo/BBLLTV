package com.xx.blbl.model.video.quality;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum VideoQuality {
    Q240P(6, "240P"),
    Q360P(16, "360P"),
    Q480P(32, "480P"),
    Q720P(64, "720P"),
    Q720P60(74, "720P60"),
    Q1080P(80, "1080P"),
    Q1080PLUS(112, "1080P+"),
    Q1080P60(116, "1080P60"),
    Q4K(120, "4K"),
    HDR(125, "HDR"),
    Dolby(126, "Dolby Vision"),
    Q8K(127, "8K");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int code;
    private final String showName;

    VideoQuality(int i4, String str) {
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
