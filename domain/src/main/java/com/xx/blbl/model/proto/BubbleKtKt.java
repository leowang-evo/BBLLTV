package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BubbleKtKt {
    public static final /* synthetic */ Dm.Bubble bubble(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        BubbleKt.Dsl.Companion companion = BubbleKt.Dsl.Companion;
        Dm.Bubble.Builder builderNewBuilder = Dm.Bubble.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        BubbleKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Bubble copy(Dm.Bubble bubble, Function1 block) {
        Intrinsics.checkNotNullParameter(bubble, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BubbleKt.Dsl.Companion companion = BubbleKt.Dsl.Companion;
        Dm.Bubble.Builder builder = bubble.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        BubbleKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
