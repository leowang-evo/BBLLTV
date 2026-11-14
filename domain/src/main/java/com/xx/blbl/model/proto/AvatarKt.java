package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AvatarKt {
    public static final AvatarKt INSTANCE = new AvatarKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Avatar.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Avatar.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.Avatar.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Avatar.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Avatar _build() {
            Dm.Avatar avatarBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(avatarBuild, "build(...)");
            return avatarBuild;
        }

        public final void clearAvatarType() {
            this._builder.clearAvatarType();
        }

        public final void clearId() {
            this._builder.clearId();
        }

        public final void clearUrl() {
            this._builder.clearUrl();
        }

        public final Dm.AvatarType getAvatarType() {
            Dm.AvatarType avatarType = this._builder.getAvatarType();
            Intrinsics.checkNotNullExpressionValue(avatarType, "getAvatarType(...)");
            return avatarType;
        }

        public final String getId() {
            String id = this._builder.getId();
            Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
            return id;
        }

        public final String getUrl() {
            String url = this._builder.getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
            return url;
        }

        public final void setAvatarType(Dm.AvatarType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAvatarType(value);
        }

        public final void setId(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setId(value);
        }

        public final void setUrl(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setUrl(value);
        }
    }

    private AvatarKt() {
    }
}
