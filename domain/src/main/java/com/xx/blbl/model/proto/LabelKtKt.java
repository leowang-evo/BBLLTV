package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class LabelKtKt {
    public static final /* synthetic */ Dm.Label copy(Dm.Label label, Function1 block) {
        Intrinsics.checkNotNullParameter(label, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        LabelKt.Dsl.Companion companion = LabelKt.Dsl.Companion;
        Dm.Label.Builder builder = label.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        LabelKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Label label(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        LabelKt.Dsl.Companion companion = LabelKt.Dsl.Companion;
        Dm.Label.Builder builderNewBuilder = Dm.Label.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        LabelKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
