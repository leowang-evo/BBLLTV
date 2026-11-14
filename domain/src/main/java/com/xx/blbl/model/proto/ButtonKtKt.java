package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ButtonKtKt {
    public static final /* synthetic */ Dm.Button button(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ButtonKt.Dsl.Companion companion = ButtonKt.Dsl.Companion;
        Dm.Button.Builder builderNewBuilder = Dm.Button.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ButtonKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Button copy(Dm.Button button, Function1 block) {
        Intrinsics.checkNotNullParameter(button, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ButtonKt.Dsl.Companion companion = ButtonKt.Dsl.Companion;
        Dm.Button.Builder builder = button.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ButtonKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
