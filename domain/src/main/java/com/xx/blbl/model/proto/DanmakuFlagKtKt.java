package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuFlagKtKt {
    public static final /* synthetic */ Dm.DanmakuFlag copy(Dm.DanmakuFlag danmakuFlag, Function1 block) {
        Intrinsics.checkNotNullParameter(danmakuFlag, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuFlagKt.Dsl.Companion companion = DanmakuFlagKt.Dsl.Companion;
        Dm.DanmakuFlag.Builder builder = danmakuFlag.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmakuFlagKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmakuFlag danmakuFlag(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuFlagKt.Dsl.Companion companion = DanmakuFlagKt.Dsl.Companion;
        Dm.DanmakuFlag.Builder builderNewBuilder = Dm.DanmakuFlag.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmakuFlagKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
