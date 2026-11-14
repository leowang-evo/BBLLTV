package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerConfigPanelKtKt {
    public static final /* synthetic */ Dm.DanmuPlayerConfigPanel copy(Dm.DanmuPlayerConfigPanel danmuPlayerConfigPanel, Function1 block) {
        Intrinsics.checkNotNullParameter(danmuPlayerConfigPanel, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerConfigPanelKt.Dsl.Companion companion = DanmuPlayerConfigPanelKt.Dsl.Companion;
        Dm.DanmuPlayerConfigPanel.Builder builder = danmuPlayerConfigPanel.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        DanmuPlayerConfigPanelKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.DanmuPlayerConfigPanel danmuPlayerConfigPanel(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DanmuPlayerConfigPanelKt.Dsl.Companion companion = DanmuPlayerConfigPanelKt.Dsl.Companion;
        Dm.DanmuPlayerConfigPanel.Builder builderNewBuilder = Dm.DanmuPlayerConfigPanel.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        DanmuPlayerConfigPanelKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
