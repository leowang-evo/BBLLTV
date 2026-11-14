package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuOpacityKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuOpacity copy(Dm.PlayerDanmakuOpacity playerDanmakuOpacity, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuOpacity, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuOpacityKt.Dsl.Companion companion = PlayerDanmakuOpacityKt.Dsl.Companion;
        Dm.PlayerDanmakuOpacity.Builder builder = playerDanmakuOpacity.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuOpacityKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuOpacity playerDanmakuOpacity(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuOpacityKt.Dsl.Companion companion = PlayerDanmakuOpacityKt.Dsl.Companion;
        Dm.PlayerDanmakuOpacity.Builder builderNewBuilder = Dm.PlayerDanmakuOpacity.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuOpacityKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
