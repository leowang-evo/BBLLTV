package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegSDKReqKtKt {
    public static final /* synthetic */ Dm.DmSegSDKReq copy(Dm.DmSegSDKReq dmSegSDKReq, Function1 block) {
        Intrinsics.checkNotNullParameter(dmSegSDKReq, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegSDKReqKt.Dsl.Companion companion = DmSegSDKReqKt.Dsl.Companion;
        Dm.DmSegSDKReq.Builder builder = dmSegSDKReq.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmSegSDKReqKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmSegSDKReq dmSegSDKReq(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegSDKReqKt.Dsl.Companion companion = DmSegSDKReqKt.Dsl.Companion;
        Dm.DmSegSDKReq.Builder builderNewBuilder = Dm.DmSegSDKReq.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmSegSDKReqKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
