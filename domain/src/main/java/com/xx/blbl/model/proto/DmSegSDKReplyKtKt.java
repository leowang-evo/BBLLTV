package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegSDKReplyKtKt {
    public static final /* synthetic */ Dm.DmSegSDKReply copy(Dm.DmSegSDKReply dmSegSDKReply, Function1 block) {
        Intrinsics.checkNotNullParameter(dmSegSDKReply, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegSDKReplyKt.Dsl.Companion companion = DmSegSDKReplyKt.Dsl.Companion;
        Dm.DmSegSDKReply.Builder builder = dmSegSDKReply.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmSegSDKReplyKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmSegSDKReply dmSegSDKReply(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegSDKReplyKt.Dsl.Companion companion = DmSegSDKReplyKt.Dsl.Companion;
        Dm.DmSegSDKReply.Builder builderNewBuilder = Dm.DmSegSDKReply.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmSegSDKReplyKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
