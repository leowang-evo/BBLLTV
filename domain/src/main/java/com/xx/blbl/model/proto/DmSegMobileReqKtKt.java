package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegMobileReqKtKt {
    public static final /* synthetic */ Dm.DmSegMobileReq copy(Dm.DmSegMobileReq dmSegMobileReq, Function1 block) {
        Intrinsics.checkNotNullParameter(dmSegMobileReq, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegMobileReqKt.Dsl.Companion companion = DmSegMobileReqKt.Dsl.Companion;
        Dm.DmSegMobileReq.Builder builder = dmSegMobileReq.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmSegMobileReqKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmSegMobileReq dmSegMobileReq(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegMobileReqKt.Dsl.Companion companion = DmSegMobileReqKt.Dsl.Companion;
        Dm.DmSegMobileReq.Builder builderNewBuilder = Dm.DmSegMobileReq.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmSegMobileReqKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
