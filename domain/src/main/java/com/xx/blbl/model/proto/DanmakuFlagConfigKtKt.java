package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuFlagConfigKtKt {
    public static final /* synthetic */ Dm.DanmakuFlagConfig copy(Dm.DanmakuFlagConfig danmakuFlagConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(danmakuFlagConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuFlagConfigKt.Dsl.Companion companion = DanmakuFlagConfigKt.Dsl.Companion;
        Dm.DanmakuFlagConfig.Builder builder = danmakuFlagConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmakuFlagConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmakuFlagConfig danmakuFlagConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuFlagConfigKt.Dsl.Companion companion = DanmakuFlagConfigKt.Dsl.Companion;
        Dm.DanmakuFlagConfig.Builder builderNewBuilder = Dm.DanmakuFlagConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmakuFlagConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
