package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuScalingfactorKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuScalingfactor copy(Dm.PlayerDanmakuScalingfactor playerDanmakuScalingfactor, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuScalingfactor, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuScalingfactorKt.Dsl.Companion companion = PlayerDanmakuScalingfactorKt.Dsl.Companion;
        Dm.PlayerDanmakuScalingfactor.Builder builder = playerDanmakuScalingfactor.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuScalingfactorKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuScalingfactor playerDanmakuScalingfactor(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuScalingfactorKt.Dsl.Companion companion = PlayerDanmakuScalingfactorKt.Dsl.Companion;
        Dm.PlayerDanmakuScalingfactor.Builder builderNewBuilder = Dm.PlayerDanmakuScalingfactor.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuScalingfactorKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
