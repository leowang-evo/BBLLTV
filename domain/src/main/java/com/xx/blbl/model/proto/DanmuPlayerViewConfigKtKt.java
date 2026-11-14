package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerViewConfigKtKt {
    public static final /* synthetic */ Dm.DanmuPlayerViewConfig copy(Dm.DanmuPlayerViewConfig danmuPlayerViewConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(danmuPlayerViewConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerViewConfigKt.Dsl.Companion companion = DanmuPlayerViewConfigKt.Dsl.Companion;
        Dm.DanmuPlayerViewConfig.Builder builder = danmuPlayerViewConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmuPlayerViewConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmuPlayerViewConfig danmuPlayerViewConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerViewConfigKt.Dsl.Companion companion = DanmuPlayerViewConfigKt.Dsl.Companion;
        Dm.DanmuPlayerViewConfig.Builder builderNewBuilder = Dm.DanmuPlayerViewConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmuPlayerViewConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
