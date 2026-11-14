package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlocktopKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuBlocktop copy(Dm.PlayerDanmakuBlocktop playerDanmakuBlocktop, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuBlocktop, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlocktopKt.Dsl.Companion companion = PlayerDanmakuBlocktopKt.Dsl.Companion;
        Dm.PlayerDanmakuBlocktop.Builder builder = playerDanmakuBlocktop.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuBlocktopKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuBlocktop playerDanmakuBlocktop(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuBlocktopKt.Dsl.Companion companion = PlayerDanmakuBlocktopKt.Dsl.Companion;
        Dm.PlayerDanmakuBlocktop.Builder builderNewBuilder = Dm.PlayerDanmakuBlocktop.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuBlocktopKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
