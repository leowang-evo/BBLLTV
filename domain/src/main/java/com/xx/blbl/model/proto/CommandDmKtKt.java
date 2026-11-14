package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CommandDmKtKt {
    public static final /* synthetic */ Dm.CommandDm commandDm(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        CommandDmKt.Dsl.Companion companion = CommandDmKt.Dsl.Companion;
        Dm.CommandDm.Builder builderNewBuilder = Dm.CommandDm.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        CommandDmKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.CommandDm copy(Dm.CommandDm commandDm, Function1 block) {
        Intrinsics.checkNotNullParameter(commandDm, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        CommandDmKt.Dsl.Companion companion = CommandDmKt.Dsl.Companion;
        Dm.CommandDm.Builder builder = commandDm.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        CommandDmKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
