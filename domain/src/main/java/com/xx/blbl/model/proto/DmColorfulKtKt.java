package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmColorfulKtKt {
    public static final /* synthetic */ Dm.DmColorful copy(Dm.DmColorful dmColorful, Function1 block) {
        Intrinsics.checkNotNullParameter(dmColorful, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DmColorfulKt.Dsl.Companion companion = DmColorfulKt.Dsl.Companion;
        Dm.DmColorful.Builder builder = dmColorful.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DmColorfulKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DmColorful dmColorful(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DmColorfulKt.Dsl.Companion companion = DmColorfulKt.Dsl.Companion;
        Dm.DmColorful.Builder builderNewBuilder = Dm.DmColorful.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DmColorfulKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
