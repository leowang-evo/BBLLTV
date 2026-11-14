package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuDomainKtKt {
    public static final /* synthetic */ Dm.PlayerDanmakuDomain copy(Dm.PlayerDanmakuDomain playerDanmakuDomain, Function1 block) {
        Intrinsics.checkNotNullParameter(playerDanmakuDomain, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuDomainKt.Dsl.Companion companion = PlayerDanmakuDomainKt.Dsl.Companion;
        Dm.PlayerDanmakuDomain.Builder builder = playerDanmakuDomain.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        PlayerDanmakuDomainKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.PlayerDanmakuDomain playerDanmakuDomain(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        PlayerDanmakuDomainKt.Dsl.Companion companion = PlayerDanmakuDomainKt.Dsl.Companion;
        Dm.PlayerDanmakuDomain.Builder builderNewBuilder = Dm.PlayerDanmakuDomain.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        PlayerDanmakuDomainKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
