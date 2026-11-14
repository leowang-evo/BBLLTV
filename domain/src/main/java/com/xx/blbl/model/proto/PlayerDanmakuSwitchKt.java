package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuSwitchKt {
    public static final PlayerDanmakuSwitchKt INSTANCE = new PlayerDanmakuSwitchKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuSwitch.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuSwitch.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuSwitch.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuSwitch.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuSwitch _build() {
            Dm.PlayerDanmakuSwitch playerDanmakuSwitchBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuSwitchBuild, "build(...)");
            return playerDanmakuSwitchBuild;
        }

        public final void clearCanIgnore() {
            this._builder.clearCanIgnore();
        }

        public final void clearValue() {
            this._builder.clearValue();
        }

        public final boolean getCanIgnore() {
            return this._builder.getCanIgnore();
        }

        public final boolean getValue() {
            return this._builder.getValue();
        }

        public final void setCanIgnore(boolean z7) {
            this._builder.setCanIgnore(z7);
        }

        public final void setValue(boolean z7) {
            this._builder.setValue(z7);
        }
    }

    private PlayerDanmakuSwitchKt() {
    }
}
