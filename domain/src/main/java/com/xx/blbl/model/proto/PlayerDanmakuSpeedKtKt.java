package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuSpeedKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuSpeed copy(Dm.PlayerDanmakuSpeed playerDanmakuSpeed, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuSpeed, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuSpeedKt.Dsl.Companion companion = PlayerDanmakuSpeedKt.Dsl.Companion;
        Dm.PlayerDanmakuSpeed.Builder builder = playerDanmakuSpeed.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuSpeedKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuSpeed playerDanmakuSpeed(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuSpeedKt.Dsl.Companion companion = PlayerDanmakuSpeedKt.Dsl.Companion;
        Dm.PlayerDanmakuSpeed.Builder builderNewBuilder = Dm.PlayerDanmakuSpeed.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuSpeedKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
