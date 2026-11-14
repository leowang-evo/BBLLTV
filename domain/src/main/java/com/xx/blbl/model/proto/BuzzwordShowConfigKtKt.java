package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BuzzwordShowConfigKtKt {
    public static final /* synthetic */ Dm.BuzzwordShowConfig buzzwordShowConfig(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        BuzzwordShowConfigKt.Dsl.Companion companion = BuzzwordShowConfigKt.Dsl.Companion;
        Dm.BuzzwordShowConfig.Builder builderNewBuilder = Dm.BuzzwordShowConfig.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        BuzzwordShowConfigKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.BuzzwordShowConfig copy(Dm.BuzzwordShowConfig buzzwordShowConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(buzzwordShowConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BuzzwordShowConfigKt.Dsl.Companion companion = BuzzwordShowConfigKt.Dsl.Companion;
        Dm.BuzzwordShowConfig.Builder builder = buzzwordShowConfig.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        BuzzwordShowConfigKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
