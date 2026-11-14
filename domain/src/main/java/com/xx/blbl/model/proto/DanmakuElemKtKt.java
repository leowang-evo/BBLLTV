package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuElemKtKt {
    public static final /* synthetic */ Dm.DanmakuElem copy(Dm.DanmakuElem danmakuElem, Function1 block) {
        Intrinsics.checkNotNullParameter(danmakuElem, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuElemKt.Dsl.Companion companion = DanmakuElemKt.Dsl.Companion;
        Dm.DanmakuElem.Builder builder = danmakuElem.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmakuElemKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmakuElem danmakuElem(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmakuElemKt.Dsl.Companion companion = DanmakuElemKt.Dsl.Companion;
        Dm.DanmakuElem.Builder builderNewBuilder = Dm.DanmakuElem.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmakuElemKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
