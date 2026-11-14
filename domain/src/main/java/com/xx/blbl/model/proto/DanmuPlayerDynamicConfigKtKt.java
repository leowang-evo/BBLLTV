package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerDynamicConfigKtKt {
    public static final /* synthetic */ Dm.DanmuPlayerDynamicConfig copy(Dm.DanmuPlayerDynamicConfig danmuPlayerDynamicConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(danmuPlayerDynamicConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerDynamicConfigKt.Dsl.Companion companion = DanmuPlayerDynamicConfigKt.Dsl.Companion;
        Dm.DanmuPlayerDynamicConfig.Builder builder = danmuPlayerDynamicConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmuPlayerDynamicConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmuPlayerDynamicConfig danmuPlayerDynamicConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerDynamicConfigKt.Dsl.Companion companion = DanmuPlayerDynamicConfigKt.Dsl.Companion;
        Dm.DanmuPlayerDynamicConfig.Builder builderNewBuilder = Dm.DanmuPlayerDynamicConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmuPlayerDynamicConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
