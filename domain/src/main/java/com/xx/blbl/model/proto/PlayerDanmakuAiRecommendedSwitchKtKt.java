package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuAiRecommendedSwitchKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuAiRecommendedSwitch copy(Dm.PlayerDanmakuAiRecommendedSwitch playerDanmakuAiRecommendedSwitch, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuAiRecommendedSwitch, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuAiRecommendedSwitchKt.Dsl.Companion companion = PlayerDanmakuAiRecommendedSwitchKt.Dsl.Companion;
        Dm.PlayerDanmakuAiRecommendedSwitch.Builder builder = playerDanmakuAiRecommendedSwitch.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuAiRecommendedSwitchKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuAiRecommendedSwitch playerDanmakuAiRecommendedSwitch(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuAiRecommendedSwitchKt.Dsl.Companion companion = PlayerDanmakuAiRecommendedSwitchKt.Dsl.Companion;
        Dm.PlayerDanmakuAiRecommendedSwitch.Builder builderNewBuilder = Dm.PlayerDanmakuAiRecommendedSwitch.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuAiRecommendedSwitchKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
