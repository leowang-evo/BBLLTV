package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuSwitchKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuSwitch copy(Dm.PlayerDanmakuSwitch playerDanmakuSwitch, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuSwitch, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuSwitchKt.Dsl.Companion companion = PlayerDanmakuSwitchKt.Dsl.Companion;
        Dm.PlayerDanmakuSwitch.Builder builder = playerDanmakuSwitch.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuSwitchKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuSwitch playerDanmakuSwitch(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuSwitchKt.Dsl.Companion companion = PlayerDanmakuSwitchKt.Dsl.Companion;
        Dm.PlayerDanmakuSwitch.Builder builderNewBuilder = Dm.PlayerDanmakuSwitch.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuSwitchKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
