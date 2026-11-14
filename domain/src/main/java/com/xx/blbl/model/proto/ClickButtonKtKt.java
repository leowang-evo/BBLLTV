package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ClickButtonKtKt {
    public static final /* synthetic */ Dm.ClickButton clickButton(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ClickButtonKt.Dsl.Companion companion = ClickButtonKt.Dsl.Companion;
        Dm.ClickButton.Builder builderNewBuilder = Dm.ClickButton.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ClickButtonKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.ClickButton copy(Dm.ClickButton clickButton, Function1 block) {
        Intrinsics.checkNotNullParameter(clickButton, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ClickButtonKt.Dsl.Companion companion = ClickButtonKt.Dsl.Companion;
        Dm.ClickButton.Builder builder = clickButton.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ClickButtonKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
