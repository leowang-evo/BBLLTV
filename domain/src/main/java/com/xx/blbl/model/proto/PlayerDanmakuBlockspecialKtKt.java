package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlockspecialKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuBlockspecial copy(Dm.PlayerDanmakuBlockspecial playerDanmakuBlockspecial, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuBlockspecial, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockspecialKt.Dsl.Companion companion = PlayerDanmakuBlockspecialKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockspecial.Builder builder = playerDanmakuBlockspecial.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuBlockspecialKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuBlockspecial playerDanmakuBlockspecial(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockspecialKt.Dsl.Companion companion = PlayerDanmakuBlockspecialKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockspecial.Builder builderNewBuilder = Dm.PlayerDanmakuBlockspecial.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuBlockspecialKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
