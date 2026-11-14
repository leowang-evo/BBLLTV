package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PeriodKtKt {
    public static final /* synthetic */ Dm.Period copy(Dm.Period period, Function1 block) {
        Intrinsics.checkNotNullParameter(period, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PeriodKt.Dsl.Companion companion = PeriodKt.Dsl.Companion;
        Dm.Period.Builder builder = period.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PeriodKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Period period(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PeriodKt.Dsl.Companion companion = PeriodKt.Dsl.Companion;
        Dm.Period.Builder builderNewBuilder = Dm.Period.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PeriodKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
