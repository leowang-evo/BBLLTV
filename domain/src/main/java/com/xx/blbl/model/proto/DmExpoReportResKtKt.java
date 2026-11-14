package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmExpoReportResKtKt {
    public static final /* synthetic */ Dm.DmExpoReportRes copy(Dm.DmExpoReportRes dmExpoReportRes, Function1 block) {
        Intrinsics.checkNotNullParameter(dmExpoReportRes, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmExpoReportResKt.Dsl.Companion companion = DmExpoReportResKt.Dsl.Companion;
        Dm.DmExpoReportRes.Builder builder = dmExpoReportRes.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmExpoReportResKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmExpoReportRes dmExpoReportRes(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmExpoReportResKt.Dsl.Companion companion = DmExpoReportResKt.Dsl.Companion;
        Dm.DmExpoReportRes.Builder builderNewBuilder = Dm.DmExpoReportRes.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmExpoReportResKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
