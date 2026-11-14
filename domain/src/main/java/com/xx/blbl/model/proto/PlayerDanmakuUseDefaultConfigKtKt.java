package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuUseDefaultConfigKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuUseDefaultConfig copy(Dm.PlayerDanmakuUseDefaultConfig playerDanmakuUseDefaultConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuUseDefaultConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuUseDefaultConfigKt.Dsl.Companion companion = PlayerDanmakuUseDefaultConfigKt.Dsl.Companion;
        Dm.PlayerDanmakuUseDefaultConfig.Builder builder = playerDanmakuUseDefaultConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuUseDefaultConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuUseDefaultConfig playerDanmakuUseDefaultConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuUseDefaultConfigKt.Dsl.Companion companion = PlayerDanmakuUseDefaultConfigKt.Dsl.Companion;
        Dm.PlayerDanmakuUseDefaultConfig.Builder builderNewBuilder = Dm.PlayerDanmakuUseDefaultConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuUseDefaultConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
