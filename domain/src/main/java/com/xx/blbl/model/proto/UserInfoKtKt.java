package com.xx.blbl.model.proto;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class UserInfoKtKt {
    public static final /* synthetic */ Dm.UserInfo copy(Dm.UserInfo userInfo, Function1 block) {
        Intrinsics.checkNotNullParameter(userInfo, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        UserInfoKt.Dsl.Companion companion = UserInfoKt.Dsl.Companion;
        Dm.UserInfo.Builder builder = userInfo.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "toBuilder(...)");
        UserInfoKt.Dsl dsl_create = companion._create(builder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }

    public static final /* synthetic */ Dm.UserInfo userInfo(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        UserInfoKt.Dsl.Companion companion = UserInfoKt.Dsl.Companion;
        Dm.UserInfo.Builder builderNewBuilder = Dm.UserInfo.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        UserInfoKt.Dsl dsl_create = companion._create(builderNewBuilder);
        block.invoke(dsl_create);
        return dsl_create._build();
    }
}
