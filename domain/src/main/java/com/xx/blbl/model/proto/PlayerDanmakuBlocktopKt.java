package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuBlocktopKt {
    public static final PlayerDanmakuBlocktopKt INSTANCE = new PlayerDanmakuBlocktopKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuBlocktop.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuBlocktop.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuBlocktop.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuBlocktop.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuBlocktop _build() {
            Dm.PlayerDanmakuBlocktop playerDanmakuBlocktopBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuBlocktopBuild, "build(...)");
            return playerDanmakuBlocktopBuild;
        }

        public final void clearValue() {
            this._builder.clearValue();
        }

        public final boolean getValue() {
            return this._builder.getValue();
        }

        public final void setValue(boolean z7) {
            this._builder.setValue(z7);
        }
    }

    private PlayerDanmakuBlocktopKt() {
    }
}
