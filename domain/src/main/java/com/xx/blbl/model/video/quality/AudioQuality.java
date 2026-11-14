package com.xx.blbl.model.video.quality;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum AudioQuality {
    Q64K(30216, "64Kbps"),
    Q132K(30232, "132Kbps"),
    Q192K(30280, "192Kbps"),
    DolbyAtoms(30250, "Dolby Atmos"),
    Lossless(30251, "Hi-Res");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int code;
    private final String showName;

    AudioQuality(int i4, String str) {
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
