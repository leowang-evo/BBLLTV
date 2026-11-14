package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuSeniorModeSwitchKt {
    public static final PlayerDanmakuSeniorModeSwitchKt INSTANCE = new PlayerDanmakuSeniorModeSwitchKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuSeniorModeSwitch.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuSeniorModeSwitch.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuSeniorModeSwitch.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuSeniorModeSwitch.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuSeniorModeSwitch _build() {
            Dm.PlayerDanmakuSeniorModeSwitch playerDanmakuSeniorModeSwitchBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuSeniorModeSwitchBuild, "build(...)");
            return playerDanmakuSeniorModeSwitchBuild;
        }

        public final void clearValue() {
            this._builder.clearValue();
        }

        public final int getValue() {
            return this._builder.getValue();
        }

        public final void setValue(int i4) {
            this._builder.setValue(i4);
        }
    }

    private PlayerDanmakuSeniorModeSwitchKt() {
    }
}
