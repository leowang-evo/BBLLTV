package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegOttReqKtKt {
    public static final /* synthetic */ Dm.DmSegOttReq copy(Dm.DmSegOttReq dmSegOttReq, Function1 block) {
        Intrinsics.checkNotNullParameter(dmSegOttReq, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegOttReqKt.Dsl.Companion companion = DmSegOttReqKt.Dsl.Companion;
        Dm.DmSegOttReq.Builder builder = dmSegOttReq.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmSegOttReqKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmSegOttReq dmSegOttReq(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmSegOttReqKt.Dsl.Companion companion = DmSegOttReqKt.Dsl.Companion;
        Dm.DmSegOttReq.Builder builderNewBuilder = Dm.DmSegOttReq.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmSegOttReqKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
