package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuAIFlagKtKt {
    public static final /* synthetic */ Dm.DanmakuAIFlag copy(Dm.DanmakuAIFlag danmakuAIFlag, Function1 block) {
        Intrinsics.checkNotNullParameter(danmakuAIFlag, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuAIFlagKt.Dsl.Companion companion = DanmakuAIFlagKt.Dsl.Companion;
        Dm.DanmakuAIFlag.Builder builder = danmakuAIFlag.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmakuAIFlagKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmakuAIFlag danmakuAIFlag(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuAIFlagKt.Dsl.Companion companion = DanmakuAIFlagKt.Dsl.Companion;
        Dm.DanmakuAIFlag.Builder builderNewBuilder = Dm.DanmakuAIFlag.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmakuAIFlagKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
