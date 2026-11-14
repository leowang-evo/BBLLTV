package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuFlagKt {
    public static final DanmakuFlagKt INSTANCE = new DanmakuFlagKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmakuFlag.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmakuFlag.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DanmakuFlag.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmakuFlag.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmakuFlag _build() {
            Dm.DanmakuFlag danmakuFlagBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmakuFlagBuild, "build(...)");
            return danmakuFlagBuild;
        }

        public final void clearDmid() {
            this._builder.clearDmid();
        }

        public final void clearFlag() {
            this._builder.clearFlag();
        }

        public final long getDmid() {
            return this._builder.getDmid();
        }

        public final int getFlag() {
            return this._builder.getFlag();
        }

        public final void setDmid(long j7) {
            this._builder.setDmid(j7);
        }

        public final void setFlag(int i4) {
            this._builder.setFlag(i4);
        }
    }

    private DanmakuFlagKt() {
    }
}
