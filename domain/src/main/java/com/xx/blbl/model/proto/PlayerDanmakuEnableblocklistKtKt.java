package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuEnableblocklistKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuEnableblocklist copy(Dm.PlayerDanmakuEnableblocklist playerDanmakuEnableblocklist, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuEnableblocklist, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuEnableblocklistKt.Dsl.Companion companion = PlayerDanmakuEnableblocklistKt.Dsl.Companion;
        Dm.PlayerDanmakuEnableblocklist.Builder builder = playerDanmakuEnableblocklist.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuEnableblocklistKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuEnableblocklist playerDanmakuEnableblocklist(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuEnableblocklistKt.Dsl.Companion companion = PlayerDanmakuEnableblocklistKt.Dsl.Companion;
        Dm.PlayerDanmakuEnableblocklist.Builder builderNewBuilder = Dm.PlayerDanmakuEnableblocklist.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuEnableblocklistKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
