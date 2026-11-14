package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CheckBoxV2KtKt {
    public static final /* synthetic */ Dm.CheckBoxV2 checkBoxV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        CheckBoxV2Kt.Dsl.Companion companion = CheckBoxV2Kt.Dsl.Companion;
        Dm.CheckBoxV2.Builder builderNewBuilder = Dm.CheckBoxV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        CheckBoxV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.CheckBoxV2 copy(Dm.CheckBoxV2 checkBoxV2, Function1 block) {
        Intrinsics.checkNotNullParameter(checkBoxV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        CheckBoxV2Kt.Dsl.Companion companion = CheckBoxV2Kt.Dsl.Companion;
        Dm.CheckBoxV2.Builder builder = checkBoxV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        CheckBoxV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
