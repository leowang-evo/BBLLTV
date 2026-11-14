package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PostPanelV2KtKt {
    public static final /* synthetic */ Dm.PostPanelV2 copy(Dm.PostPanelV2 postPanelV2, Function1 block) {
        Intrinsics.checkNotNullParameter(postPanelV2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PostPanelV2Kt.Dsl.Companion companion = PostPanelV2Kt.Dsl.Companion;
        Dm.PostPanelV2.Builder builder = postPanelV2.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PostPanelV2Kt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PostPanelV2 postPanelV2(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PostPanelV2Kt.Dsl.Companion companion = PostPanelV2Kt.Dsl.Companion;
        Dm.PostPanelV2.Builder builderNewBuilder = Dm.PostPanelV2.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PostPanelV2Kt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
