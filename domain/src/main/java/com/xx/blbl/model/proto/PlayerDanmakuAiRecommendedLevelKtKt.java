package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuAiRecommendedLevelKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuAiRecommendedLevel copy(Dm.PlayerDanmakuAiRecommendedLevel playerDanmakuAiRecommendedLevel, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuAiRecommendedLevel, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuAiRecommendedLevelKt.Dsl.Companion companion = PlayerDanmakuAiRecommendedLevelKt.Dsl.Companion;
        Dm.PlayerDanmakuAiRecommendedLevel.Builder builder = playerDanmakuAiRecommendedLevel.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuAiRecommendedLevelKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuAiRecommendedLevel playerDanmakuAiRecommendedLevel(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuAiRecommendedLevelKt.Dsl.Companion companion = PlayerDanmakuAiRecommendedLevelKt.Dsl.Companion;
        Dm.PlayerDanmakuAiRecommendedLevel.Builder builderNewBuilder = Dm.PlayerDanmakuAiRecommendedLevel.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuAiRecommendedLevelKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
