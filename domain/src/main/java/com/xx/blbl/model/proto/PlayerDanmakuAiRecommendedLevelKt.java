package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuAiRecommendedLevelKt {
    public static final PlayerDanmakuAiRecommendedLevelKt INSTANCE = new PlayerDanmakuAiRecommendedLevelKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuAiRecommendedLevel.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuAiRecommendedLevel.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuAiRecommendedLevel.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuAiRecommendedLevel.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuAiRecommendedLevel _build() {
            Dm.PlayerDanmakuAiRecommendedLevel playerDanmakuAiRecommendedLevelBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuAiRecommendedLevelBuild, "build(...)");
            return playerDanmakuAiRecommendedLevelBuild;
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

    private PlayerDanmakuAiRecommendedLevelKt() {
    }
}
