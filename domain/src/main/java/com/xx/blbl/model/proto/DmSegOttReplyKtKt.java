package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegOttReplyKtKt {
    public static final /* synthetic */ Dm.DmSegOttReply copy(Dm.DmSegOttReply dmSegOttReply, Function1 block) {
        Intrinsics.checkNotNullParameter(dmSegOttReply, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegOttReplyKt.Dsl.Companion companion = DmSegOttReplyKt.Dsl.Companion;
        Dm.DmSegOttReply.Builder builder = dmSegOttReply.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmSegOttReplyKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmSegOttReply dmSegOttReply(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegOttReplyKt.Dsl.Companion companion = DmSegOttReplyKt.Dsl.Companion;
        Dm.DmSegOttReply.Builder builderNewBuilder = Dm.DmSegOttReply.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmSegOttReplyKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
