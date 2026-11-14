package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlockbottomKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuBlockbottom copy(Dm.PlayerDanmakuBlockbottom playerDanmakuBlockbottom, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuBlockbottom, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockbottomKt.Dsl.Companion companion = PlayerDanmakuBlockbottomKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockbottom.Builder builder = playerDanmakuBlockbottom.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuBlockbottomKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuBlockbottom playerDanmakuBlockbottom(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockbottomKt.Dsl.Companion companion = PlayerDanmakuBlockbottomKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockbottom.Builder builderNewBuilder = Dm.PlayerDanmakuBlockbottom.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuBlockbottomKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
