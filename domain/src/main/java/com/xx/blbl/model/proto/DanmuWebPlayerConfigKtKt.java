package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuWebPlayerConfigKtKt {
    public static final /* synthetic */ Dm.DanmuWebPlayerConfig copy(Dm.DanmuWebPlayerConfig danmuWebPlayerConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(danmuWebPlayerConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuWebPlayerConfigKt.Dsl.Companion companion = DanmuWebPlayerConfigKt.Dsl.Companion;
        Dm.DanmuWebPlayerConfig.Builder builder = danmuWebPlayerConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmuWebPlayerConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmuWebPlayerConfig danmuWebPlayerConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuWebPlayerConfigKt.Dsl.Companion companion = DanmuWebPlayerConfigKt.Dsl.Companion;
        Dm.DanmuWebPlayerConfig.Builder builderNewBuilder = Dm.DanmuWebPlayerConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmuWebPlayerConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
