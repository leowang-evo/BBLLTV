package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmExpoReportReqKtKt {
    public static final /* synthetic */ Dm.DmExpoReportReq copy(Dm.DmExpoReportReq dmExpoReportReq, Function1 block) {
        Intrinsics.checkNotNullParameter(dmExpoReportReq, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmExpoReportReqKt.Dsl.Companion companion = DmExpoReportReqKt.Dsl.Companion;
        Dm.DmExpoReportReq.Builder builder = dmExpoReportReq.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmExpoReportReqKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmExpoReportReq dmExpoReportReq(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmExpoReportReqKt.Dsl.Companion companion = DmExpoReportReqKt.Dsl.Companion;
        Dm.DmExpoReportReq.Builder builderNewBuilder = Dm.DmExpoReportReq.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmExpoReportReqKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
