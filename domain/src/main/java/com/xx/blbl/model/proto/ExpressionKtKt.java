package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ExpressionKtKt {
    public static final /* synthetic */ Dm.Expression copy(Dm.Expression expression, Function1 block) {
        Intrinsics.checkNotNullParameter(expression, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ExpressionKt.Dsl.Companion companion = ExpressionKt.Dsl.Companion;
        Dm.Expression.Builder builder = expression.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ExpressionKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Expression expression(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ExpressionKt.Dsl.Companion companion = ExpressionKt.Dsl.Companion;
        Dm.Expression.Builder builderNewBuilder = Dm.Expression.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ExpressionKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
