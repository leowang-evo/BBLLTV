package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmViewReplyKtKt {
    public static final /* synthetic */ Dm.DmViewReply copy(Dm.DmViewReply dmViewReply, Function1 block) {
        Intrinsics.checkNotNullParameter(dmViewReply, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmViewReplyKt.Dsl.Companion companion = DmViewReplyKt.Dsl.Companion;
        Dm.DmViewReply.Builder builder = dmViewReply.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmViewReplyKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmViewReply dmViewReply(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmViewReplyKt.Dsl.Companion companion = DmViewReplyKt.Dsl.Companion;
        Dm.DmViewReply.Builder builderNewBuilder = Dm.DmViewReply.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmViewReplyKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
