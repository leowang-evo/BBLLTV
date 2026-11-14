package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuAiRecommendedLevelV2Kt {
    public static final PlayerDanmakuAiRecommendedLevelV2Kt INSTANCE = new PlayerDanmakuAiRecommendedLevelV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuAiRecommendedLevelV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuAiRecommendedLevelV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuAiRecommendedLevelV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuAiRecommendedLevelV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuAiRecommendedLevelV2 _build() {
            Dm.PlayerDanmakuAiRecommendedLevelV2 playerDanmakuAiRecommendedLevelV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuAiRecommendedLevelV2Build, "build(...)");
            return playerDanmakuAiRecommendedLevelV2Build;
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

    private PlayerDanmakuAiRecommendedLevelV2Kt() {
    }
}
