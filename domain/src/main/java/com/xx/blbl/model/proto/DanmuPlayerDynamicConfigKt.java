package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerDynamicConfigKt {
    public static final DanmuPlayerDynamicConfigKt INSTANCE = new DanmuPlayerDynamicConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmuPlayerDynamicConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmuPlayerDynamicConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DanmuPlayerDynamicConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmuPlayerDynamicConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmuPlayerDynamicConfig _build() {
            Dm.DanmuPlayerDynamicConfig danmuPlayerDynamicConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmuPlayerDynamicConfigBuild, "build(...)");
            return danmuPlayerDynamicConfigBuild;
        }

        public final void clearPlayerDanmakuDomain() {
            this._builder.clearPlayerDanmakuDomain();
        }

        public final void clearProgress() {
            this._builder.clearProgress();
        }

        public final float getPlayerDanmakuDomain() {
            return this._builder.getPlayerDanmakuDomain();
        }

        public final int getProgress() {
            return this._builder.getProgress();
        }

        public final void setPlayerDanmakuDomain(float f7) {
            this._builder.setPlayerDanmakuDomain(f7);
        }

        public final void setProgress(int i4) {
            this._builder.setProgress(i4);
        }
    }

    private DanmuPlayerDynamicConfigKt() {
    }
}
