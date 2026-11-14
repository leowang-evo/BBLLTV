package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ClickButtonV2KtKt {
    public static final /* synthetic */ Dm.ClickButtonV2 clickButtonV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ClickButtonV2Kt.Dsl.Companion companion = ClickButtonV2Kt.Dsl.Companion;
        Dm.ClickButtonV2.Builder builderNewBuilder = Dm.ClickButtonV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ClickButtonV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.ClickButtonV2 copy(Dm.ClickButtonV2 clickButtonV2, Function1 block) {
        Intrinsics.checkNotNullParameter(clickButtonV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ClickButtonV2Kt.Dsl.Companion companion = ClickButtonV2Kt.Dsl.Companion;
        Dm.ClickButtonV2.Builder builder = clickButtonV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ClickButtonV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
