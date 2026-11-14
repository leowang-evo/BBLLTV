package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class TextInputV2KtKt {
    public static final /* synthetic */ Dm.TextInputV2 copy(Dm.TextInputV2 textInputV2, Function1 block) {
        Intrinsics.checkNotNullParameter(textInputV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        TextInputV2Kt.Dsl.Companion companion = TextInputV2Kt.Dsl.Companion;
        Dm.TextInputV2.Builder builder = textInputV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        TextInputV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.TextInputV2 textInputV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        TextInputV2Kt.Dsl.Companion companion = TextInputV2Kt.Dsl.Companion;
        Dm.TextInputV2.Builder builderNewBuilder = Dm.TextInputV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        TextInputV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
