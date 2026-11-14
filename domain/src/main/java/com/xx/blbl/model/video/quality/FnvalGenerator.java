package com.xx.blbl.model.video.quality;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class FnvalGenerator {
    public static final FnvalGenerator INSTANCE = new FnvalGenerator();

    private FnvalGenerator() {}

    /** Advertise supported stream formats independently of the requested default quality. */
    public final int getFnval(VideoQuality quality) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        return 4048;
    }

    public final int getFourk(VideoQuality quality) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        return 1;
    }
}
