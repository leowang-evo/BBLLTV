package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class TextInputKtKt {
    public static final /* synthetic */ Dm.TextInput copy(Dm.TextInput textInput, Function1 block) {
        Intrinsics.checkNotNullParameter(textInput, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        TextInputKt.Dsl.Companion companion = TextInputKt.Dsl.Companion;
        Dm.TextInput.Builder builder = textInput.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        TextInputKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.TextInput textInput(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        TextInputKt.Dsl.Companion companion = TextInputKt.Dsl.Companion;
        Dm.TextInput.Builder builderNewBuilder = Dm.TextInput.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        TextInputKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
