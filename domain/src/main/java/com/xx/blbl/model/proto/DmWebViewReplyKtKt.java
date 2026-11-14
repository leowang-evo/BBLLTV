package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmWebViewReplyKtKt {
    public static final /* synthetic */ Dm.DmWebViewReply copy(Dm.DmWebViewReply dmWebViewReply, Function1 block) {
        Intrinsics.checkNotNullParameter(dmWebViewReply, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmWebViewReplyKt.Dsl.Companion companion = DmWebViewReplyKt.Dsl.Companion;
        Dm.DmWebViewReply.Builder builder = dmWebViewReply.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmWebViewReplyKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmWebViewReply dmWebViewReply(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmWebViewReplyKt.Dsl.Companion companion = DmWebViewReplyKt.Dsl.Companion;
        Dm.DmWebViewReply.Builder builderNewBuilder = Dm.DmWebViewReply.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmWebViewReplyKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
