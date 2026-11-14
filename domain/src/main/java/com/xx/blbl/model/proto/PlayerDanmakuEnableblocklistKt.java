package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuEnableblocklistKt {
    public static final PlayerDanmakuEnableblocklistKt INSTANCE = new PlayerDanmakuEnableblocklistKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuEnableblocklist.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuEnableblocklist.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuEnableblocklist.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuEnableblocklist.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuEnableblocklist _build() {
            Dm.PlayerDanmakuEnableblocklist playerDanmakuEnableblocklistBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuEnableblocklistBuild, "build(...)");
            return playerDanmakuEnableblocklistBuild;
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

    private PlayerDanmakuEnableblocklistKt() {
    }
}
