package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlockscrollKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuBlockscroll copy(Dm.PlayerDanmakuBlockscroll playerDanmakuBlockscroll, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuBlockscroll, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockscrollKt.Dsl.Companion companion = PlayerDanmakuBlockscrollKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockscroll.Builder builder = playerDanmakuBlockscroll.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuBlockscrollKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuBlockscroll playerDanmakuBlockscroll(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockscrollKt.Dsl.Companion companion = PlayerDanmakuBlockscrollKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockscroll.Builder builderNewBuilder = Dm.PlayerDanmakuBlockscroll.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuBlockscrollKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
