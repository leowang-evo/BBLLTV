package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlockcolorfulKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuBlockcolorful copy(Dm.PlayerDanmakuBlockcolorful playerDanmakuBlockcolorful, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuBlockcolorful, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockcolorfulKt.Dsl.Companion companion = PlayerDanmakuBlockcolorfulKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockcolorful.Builder builder = playerDanmakuBlockcolorful.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuBlockcolorfulKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuBlockcolorful playerDanmakuBlockcolorful(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlockcolorfulKt.Dsl.Companion companion = PlayerDanmakuBlockcolorfulKt.Dsl.Companion;
        Dm.PlayerDanmakuBlockcolorful.Builder builderNewBuilder = Dm.PlayerDanmakuBlockcolorful.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuBlockcolorfulKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
