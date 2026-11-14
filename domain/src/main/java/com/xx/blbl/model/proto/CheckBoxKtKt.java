package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CheckBoxKtKt {
    public static final /* synthetic */ Dm.CheckBox checkBox(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        CheckBoxKt.Dsl.Companion companion = CheckBoxKt.Dsl.Companion;
        Dm.CheckBox.Builder builderNewBuilder = Dm.CheckBox.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        CheckBoxKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.CheckBox copy(Dm.CheckBox checkBox, Function1 block) {
        Intrinsics.checkNotNullParameter(checkBox, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        CheckBoxKt.Dsl.Companion companion = CheckBoxKt.Dsl.Companion;
        Dm.CheckBox.Builder builder = checkBox.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        CheckBoxKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
