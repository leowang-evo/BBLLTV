package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmViewReqKtKt {
    public static final /* synthetic */ Dm.DmViewReq copy(Dm.DmViewReq dmViewReq, Function1 block) {
        Intrinsics.checkNotNullParameter(dmViewReq, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmViewReqKt.Dsl.Companion companion = DmViewReqKt.Dsl.Companion;
        Dm.DmViewReq.Builder builder = dmViewReq.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmViewReqKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmViewReq dmViewReq(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmViewReqKt.Dsl.Companion companion = DmViewReqKt.Dsl.Companion;
        Dm.DmViewReq.Builder builderNewBuilder = Dm.DmViewReq.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmViewReqKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
