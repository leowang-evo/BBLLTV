package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class UserInfoKt {
    public static final UserInfoKt INSTANCE = new UserInfoKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.UserInfo.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.UserInfo.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.UserInfo.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.UserInfo.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.UserInfo _build() {
            Dm.UserInfo userInfoBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(userInfoBuild, "build(...)");
            return userInfoBuild;
        }

        public final void clearFace() {
            this._builder.clearFace();
        }

        public final void clearMid() {
            this._builder.clearMid();
        }

        public final void clearName() {
            this._builder.clearName();
        }

        public final void clearRank() {
            this._builder.clearRank();
        }

        public final void clearSex() {
            this._builder.clearSex();
        }

        public final void clearSign() {
            this._builder.clearSign();
        }

        public final String getFace() {
            String face = this._builder.getFace();
            Intrinsics.checkNotNullExpressionValue(face, "getFace(...)");
            return face;
        }

        public final long getMid() {
            return this._builder.getMid();
        }

        public final String getName() {
            String name = this._builder.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            return name;
        }

        public final int getRank() {
            return this._builder.getRank();
        }

        public final String getSex() {
            String sex = this._builder.getSex();
            Intrinsics.checkNotNullExpressionValue(sex, "getSex(...)");
            return sex;
        }

        public final String getSign() {
            String sign = this._builder.getSign();
            Intrinsics.checkNotNullExpressionValue(sign, "getSign(...)");
            return sign;
        }

        public final void setFace(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setFace(value);
        }

        public final void setMid(long j7) {
            this._builder.setMid(j7);
        }

        public final void setName(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setName(value);
        }

        public final void setRank(int i4) {
            this._builder.setRank(i4);
        }

        public final void setSex(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSex(value);
        }

        public final void setSign(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSign(value);
        }
    }

    private UserInfoKt() {
    }
}
