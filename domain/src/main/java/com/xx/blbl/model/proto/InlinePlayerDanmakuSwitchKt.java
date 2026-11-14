package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class InlinePlayerDanmakuSwitchKt {
    public static final InlinePlayerDanmakuSwitchKt INSTANCE = new InlinePlayerDanmakuSwitchKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.InlinePlayerDanmakuSwitch.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.InlinePlayerDanmakuSwitch.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.InlinePlayerDanmakuSwitch.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.InlinePlayerDanmakuSwitch.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.InlinePlayerDanmakuSwitch _build() {
            Dm.InlinePlayerDanmakuSwitch inlinePlayerDanmakuSwitchBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(inlinePlayerDanmakuSwitchBuild, "build(...)");
            return inlinePlayerDanmakuSwitchBuild;
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

    private InlinePlayerDanmakuSwitchKt() {
    }
}
