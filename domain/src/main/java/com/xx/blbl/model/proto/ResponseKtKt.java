package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseKtKt {
    public static final /* synthetic */ Dm.Response copy(Dm.Response response, Function1 block) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ResponseKt.Dsl.Companion companion = ResponseKt.Dsl.Companion;
        Dm.Response.Builder builder = response.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ResponseKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Response response(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ResponseKt.Dsl.Companion companion = ResponseKt.Dsl.Companion;
        Dm.Response.Builder builderNewBuilder = Dm.Response.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ResponseKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
