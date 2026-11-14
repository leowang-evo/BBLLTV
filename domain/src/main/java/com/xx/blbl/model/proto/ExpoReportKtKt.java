package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ExpoReportKtKt {
    public static final /* synthetic */ Dm.ExpoReport copy(Dm.ExpoReport expoReport, Function1 block) {
        Intrinsics.checkNotNullParameter(expoReport, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ExpoReportKt.Dsl.Companion companion = ExpoReportKt.Dsl.Companion;
        Dm.ExpoReport.Builder builder = expoReport.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        ExpoReportKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.ExpoReport expoReport(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ExpoReportKt.Dsl.Companion companion = ExpoReportKt.Dsl.Companion;
        Dm.ExpoReport.Builder builderNewBuilder = Dm.ExpoReport.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        ExpoReportKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
