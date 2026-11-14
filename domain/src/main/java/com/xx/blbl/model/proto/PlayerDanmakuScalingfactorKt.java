package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerDanmakuScalingfactorKt {
    public static final PlayerDanmakuScalingfactorKt INSTANCE = new PlayerDanmakuScalingfactorKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PlayerDanmakuScalingfactor.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PlayerDanmakuScalingfactor.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PlayerDanmakuScalingfactor.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PlayerDanmakuScalingfactor.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PlayerDanmakuScalingfactor _build() {
            Dm.PlayerDanmakuScalingfactor playerDanmakuScalingfactorBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuScalingfactorBuild, "build(...)");
            return playerDanmakuScalingfactorBuild;
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

    private PlayerDanmakuScalingfactorKt() {
    }
}
