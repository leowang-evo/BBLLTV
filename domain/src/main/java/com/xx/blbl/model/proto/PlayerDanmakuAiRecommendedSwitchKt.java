package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuAiRecommendedSwitchKt {
    public static final PlayerDanmakuAiRecommendedSwitchKt INSTANCE = new PlayerDanmakuAiRecommendedSwitchKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuAiRecommendedSwitch.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuAiRecommendedSwitch.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuAiRecommendedSwitch.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuAiRecommendedSwitch.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuAiRecommendedSwitch _build() {
            Dm.PlayerDanmakuAiRecommendedSwitch playerDanmakuAiRecommendedSwitchBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuAiRecommendedSwitchBuild, "build(...)");
            return playerDanmakuAiRecommendedSwitchBuild;
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

    private PlayerDanmakuAiRecommendedSwitchKt() {
    }
}
