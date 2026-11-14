package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ExpressionsKtKt {
    public static final /* synthetic */ Dm.Expressions copy(Dm.Expressions expressions, Function1 block) {
        Intrinsics.checkNotNullParameter(expressions, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ExpressionsKt.Dsl.Companion companion = ExpressionsKt.Dsl.Companion;
        Dm.Expressions.Builder builder = expressions.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ExpressionsKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Expressions expressions(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ExpressionsKt.Dsl.Companion companion = ExpressionsKt.Dsl.Companion;
        Dm.Expressions.Builder builderNewBuilder = Dm.Expressions.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ExpressionsKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
