package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoMaskKtKt {
    public static final /* synthetic */ Dm.VideoMask copy(Dm.VideoMask videoMask, Function1 block) {
        Intrinsics.checkNotNullParameter(videoMask, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        VideoMaskKt.Dsl.Companion companion = VideoMaskKt.Dsl.Companion;
        Dm.VideoMask.Builder builder = videoMask.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        VideoMaskKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.VideoMask videoMask(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        VideoMaskKt.Dsl.Companion companion = VideoMaskKt.Dsl.Companion;
        Dm.VideoMask.Builder builderNewBuilder = Dm.VideoMask.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        VideoMaskKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
