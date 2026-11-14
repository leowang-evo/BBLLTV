package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ToastKtKt {
    public static final /* synthetic */ Dm.Toast copy(Dm.Toast toast, Function1 block) {
        Intrinsics.checkNotNullParameter(toast, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ToastKt.Dsl.Companion companion = ToastKt.Dsl.Companion;
        Dm.Toast.Builder builder = toast.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ToastKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Toast toast(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ToastKt.Dsl.Companion companion = ToastKt.Dsl.Companion;
        Dm.Toast.Builder builderNewBuilder = Dm.Toast.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ToastKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
