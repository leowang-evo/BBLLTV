package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleItemKtKt {
    public static final /* synthetic */ Dm.SubtitleItem copy(Dm.SubtitleItem subtitleItem, Function1 block) {
        Intrinsics.checkNotNullParameter(subtitleItem, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        SubtitleItemKt.Dsl.Companion companion = SubtitleItemKt.Dsl.Companion;
        Dm.SubtitleItem.Builder builder = subtitleItem.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        SubtitleItemKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.SubtitleItem subtitleItem(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SubtitleItemKt.Dsl.Companion companion = SubtitleItemKt.Dsl.Companion;
        Dm.SubtitleItem.Builder builderNewBuilder = Dm.SubtitleItem.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        SubtitleItemKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
