package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PostPanelKtKt {
    public static final /* synthetic */ Dm.PostPanel copy(Dm.PostPanel postPanel, Function1 block) {
        Intrinsics.checkNotNullParameter(postPanel, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PostPanelKt.Dsl.Companion companion = PostPanelKt.Dsl.Companion;
        Dm.PostPanel.Builder builder = postPanel.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PostPanelKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PostPanel postPanel(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PostPanelKt.Dsl.Companion companion = PostPanelKt.Dsl.Companion;
        Dm.PostPanel.Builder builderNewBuilder = Dm.PostPanel.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PostPanelKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
