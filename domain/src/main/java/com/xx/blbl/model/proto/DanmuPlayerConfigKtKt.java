package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerConfigKtKt {
    public static final /* synthetic */ Dm.DanmuPlayerConfig copy(Dm.DanmuPlayerConfig danmuPlayerConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(danmuPlayerConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerConfigKt.Dsl.Companion companion = DanmuPlayerConfigKt.Dsl.Companion;
        Dm.DanmuPlayerConfig.Builder builder = danmuPlayerConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmuPlayerConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmuPlayerConfig danmuPlayerConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerConfigKt.Dsl.Companion companion = DanmuPlayerConfigKt.Dsl.Companion;
        Dm.DanmuPlayerConfig.Builder builderNewBuilder = Dm.DanmuPlayerConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmuPlayerConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
