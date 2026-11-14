package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AvatarKtKt {
    public static final /* synthetic */ Dm.Avatar avatar(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        AvatarKt.Dsl.Companion companion = AvatarKt.Dsl.Companion;
        Dm.Avatar.Builder builderNewBuilder = Dm.Avatar.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        AvatarKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.Avatar copy(Dm.Avatar avatar, Function1 block) {
        Intrinsics.checkNotNullParameter(avatar, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        AvatarKt.Dsl.Companion companion = AvatarKt.Dsl.Companion;
        Dm.Avatar.Builder builder = avatar.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        AvatarKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
