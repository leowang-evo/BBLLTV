package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BubbleV2KtKt {
    public static final /* synthetic */ Dm.BubbleV2 bubbleV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        BubbleV2Kt.Dsl.Companion companion = BubbleV2Kt.Dsl.Companion;
        Dm.BubbleV2.Builder builderNewBuilder = Dm.BubbleV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        BubbleV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.BubbleV2 copy(Dm.BubbleV2 bubbleV2, Function1 block) {
        Intrinsics.checkNotNullParameter(bubbleV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BubbleV2Kt.Dsl.Companion companion = BubbleV2Kt.Dsl.Companion;
        Dm.BubbleV2.Builder builder = bubbleV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        BubbleV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
