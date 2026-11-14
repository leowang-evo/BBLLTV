package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoSubtitleKtKt {
    public static final /* synthetic */ Dm.VideoSubtitle copy(Dm.VideoSubtitle videoSubtitle, Function1 block) {
        Intrinsics.checkNotNullParameter(videoSubtitle, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        VideoSubtitleKt.Dsl.Companion companion = VideoSubtitleKt.Dsl.Companion;
        Dm.VideoSubtitle.Builder builder = videoSubtitle.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        VideoSubtitleKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.VideoSubtitle videoSubtitle(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        VideoSubtitleKt.Dsl.Companion companion = VideoSubtitleKt.Dsl.Companion;
        Dm.VideoSubtitle.Builder builderNewBuilder = Dm.VideoSubtitle.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        VideoSubtitleKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
