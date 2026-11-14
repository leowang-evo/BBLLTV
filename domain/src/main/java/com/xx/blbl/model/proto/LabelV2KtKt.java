package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class LabelV2KtKt {
    public static final /* synthetic */ Dm.LabelV2 copy(Dm.LabelV2 labelV2, Function1 block) {
        Intrinsics.checkNotNullParameter(labelV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        LabelV2Kt.Dsl.Companion companion = LabelV2Kt.Dsl.Companion;
        Dm.LabelV2.Builder builder = labelV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        LabelV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.LabelV2 labelV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        LabelV2Kt.Dsl.Companion companion = LabelV2Kt.Dsl.Companion;
        Dm.LabelV2.Builder builderNewBuilder = Dm.LabelV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        LabelV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
