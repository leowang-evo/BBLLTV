package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ToastButtonV2KtKt {
    public static final /* synthetic */ Dm.ToastButtonV2 copy(Dm.ToastButtonV2 toastButtonV2, Function1 block) {
        Intrinsics.checkNotNullParameter(toastButtonV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ToastButtonV2Kt.Dsl.Companion companion = ToastButtonV2Kt.Dsl.Companion;
        Dm.ToastButtonV2.Builder builder = toastButtonV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ToastButtonV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.ToastButtonV2 toastButtonV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ToastButtonV2Kt.Dsl.Companion companion = ToastButtonV2Kt.Dsl.Companion;
        Dm.ToastButtonV2.Builder builderNewBuilder = Dm.ToastButtonV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ToastButtonV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
