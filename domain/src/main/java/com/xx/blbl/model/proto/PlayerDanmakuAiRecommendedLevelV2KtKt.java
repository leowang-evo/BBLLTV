package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuAiRecommendedLevelV2KtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuAiRecommendedLevelV2 copy(Dm.PlayerDanmakuAiRecommendedLevelV2 playerDanmakuAiRecommendedLevelV2, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuAiRecommendedLevelV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuAiRecommendedLevelV2Kt.Dsl.Companion companion = PlayerDanmakuAiRecommendedLevelV2Kt.Dsl.Companion;
        Dm.PlayerDanmakuAiRecommendedLevelV2.Builder builder = playerDanmakuAiRecommendedLevelV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuAiRecommendedLevelV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuAiRecommendedLevelV2 playerDanmakuAiRecommendedLevelV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuAiRecommendedLevelV2Kt.Dsl.Companion companion = PlayerDanmakuAiRecommendedLevelV2Kt.Dsl.Companion;
        Dm.PlayerDanmakuAiRecommendedLevelV2.Builder builderNewBuilder = Dm.PlayerDanmakuAiRecommendedLevelV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuAiRecommendedLevelV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
