package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BuzzwordConfigKtKt {
    public static final /* synthetic */ Dm.BuzzwordConfig buzzwordConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        BuzzwordConfigKt.Dsl.Companion companion = BuzzwordConfigKt.Dsl.Companion;
        Dm.BuzzwordConfig.Builder builderNewBuilder = Dm.BuzzwordConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        BuzzwordConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.BuzzwordConfig copy(Dm.BuzzwordConfig buzzwordConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(buzzwordConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BuzzwordConfigKt.Dsl.Companion companion = BuzzwordConfigKt.Dsl.Companion;
        Dm.BuzzwordConfig.Builder builder = buzzwordConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        BuzzwordConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
