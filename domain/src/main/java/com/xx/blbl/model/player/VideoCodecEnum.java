package com.xx.blbl.model.player;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: classes.dex */
public enum VideoCodecEnum {
    AVC(0, "AVC", "avc"),
    HEVC(1, "HEVC", "hev"),
    AV1(2, "AV1", "av01"),
    HVC1(3, "HVC1", "hvc"),
    HEVCDolbyVision(4, "DVH", "dvh"),
    VP9(5, "VP9", "vp");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int code;
    private final String showName;
    private final String value;

    VideoCodecEnum(int i4, String str, String str2) {
        this.code = i4;
        this.showName = str;
        this.value = str2;
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

    public final String getValue() {
        return this.value;
    }
}
