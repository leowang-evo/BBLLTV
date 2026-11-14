package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class InlinePlayerDanmakuSwitchKtKt {
    public static final /* synthetic */ Dm.InlinePlayerDanmakuSwitch copy(Dm.InlinePlayerDanmakuSwitch inlinePlayerDanmakuSwitch, Function1 block) {
        Intrinsics.checkNotNullParameter(inlinePlayerDanmakuSwitch, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        InlinePlayerDanmakuSwitchKt.Dsl.Companion companion = InlinePlayerDanmakuSwitchKt.Dsl.Companion;
        Dm.InlinePlayerDanmakuSwitch.Builder builder = inlinePlayerDanmakuSwitch.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        InlinePlayerDanmakuSwitchKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.InlinePlayerDanmakuSwitch inlinePlayerDanmakuSwitch(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        InlinePlayerDanmakuSwitchKt.Dsl.Companion companion = InlinePlayerDanmakuSwitchKt.Dsl.Companion;
        Dm.InlinePlayerDanmakuSwitch.Builder builderNewBuilder = Dm.InlinePlayerDanmakuSwitch.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        InlinePlayerDanmakuSwitchKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
