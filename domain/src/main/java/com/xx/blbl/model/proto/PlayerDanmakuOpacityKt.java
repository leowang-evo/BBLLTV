package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuOpacityKt {
    public static final PlayerDanmakuOpacityKt INSTANCE = new PlayerDanmakuOpacityKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuOpacity.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuOpacity.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuOpacity.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuOpacity.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuOpacity _build() {
            Dm.PlayerDanmakuOpacity playerDanmakuOpacityBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuOpacityBuild, "build(...)");
            return playerDanmakuOpacityBuild;
        }

        public final void clearValue() {
            this._builder.clearValue();
        }

        public final float getValue() {
            return this._builder.getValue();
        }

        public final void setValue(float f7) {
            this._builder.setValue(f7);
        }
    }

    private PlayerDanmakuOpacityKt() {
    }
}
