package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmPlayerConfigReqKtKt {
    public static final /* synthetic */ Dm.DmPlayerConfigReq copy(Dm.DmPlayerConfigReq dmPlayerConfigReq, Function1 block) {
        Intrinsics.checkNotNullParameter(dmPlayerConfigReq, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmPlayerConfigReqKt.Dsl.Companion companion = DmPlayerConfigReqKt.Dsl.Companion;
        Dm.DmPlayerConfigReq.Builder builder = dmPlayerConfigReq.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmPlayerConfigReqKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmPlayerConfigReq dmPlayerConfigReq(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmPlayerConfigReqKt.Dsl.Companion companion = DmPlayerConfigReqKt.Dsl.Companion;
        Dm.DmPlayerConfigReq.Builder builderNewBuilder = Dm.DmPlayerConfigReq.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmPlayerConfigReqKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
