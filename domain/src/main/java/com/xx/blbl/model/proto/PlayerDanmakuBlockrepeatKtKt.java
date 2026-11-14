package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlockrepeatKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuBlockrepeat copy(Dm.PlayerDanmakuBlockrepeat playerDanmakuBlockrepeat, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuBlockrepeat, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockrepeatKt.Dsl.Companion companion = PlayerDanmakuBlockrepeatKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockrepeat.Builder builder = playerDanmakuBlockrepeat.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuBlockrepeatKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuBlockrepeat playerDanmakuBlockrepeat(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockrepeatKt.Dsl.Companion companion = PlayerDanmakuBlockrepeatKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockrepeat.Builder builderNewBuilder = Dm.PlayerDanmakuBlockrepeat.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuBlockrepeatKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
