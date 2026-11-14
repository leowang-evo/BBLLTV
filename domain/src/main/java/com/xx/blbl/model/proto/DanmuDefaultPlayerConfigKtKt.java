package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuDefaultPlayerConfigKtKt {
    public static final /* synthetic */ Dm.DanmuDefaultPlayerConfig copy(Dm.DanmuDefaultPlayerConfig danmuDefaultPlayerConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(danmuDefaultPlayerConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuDefaultPlayerConfigKt.Dsl.Companion companion = DanmuDefaultPlayerConfigKt.Dsl.Companion;
        Dm.DanmuDefaultPlayerConfig.Builder builder = danmuDefaultPlayerConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmuDefaultPlayerConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmuDefaultPlayerConfig danmuDefaultPlayerConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuDefaultPlayerConfigKt.Dsl.Companion companion = DanmuDefaultPlayerConfigKt.Dsl.Companion;
        Dm.DanmuDefaultPlayerConfig.Builder builderNewBuilder = Dm.DanmuDefaultPlayerConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmuDefaultPlayerConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
