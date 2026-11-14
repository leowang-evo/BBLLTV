package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuFlagConfigKt {
    public static final DanmakuFlagConfigKt INSTANCE = new DanmakuFlagConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmakuFlagConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmakuFlagConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DanmakuFlagConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmakuFlagConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmakuFlagConfig _build() {
            Dm.DanmakuFlagConfig danmakuFlagConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmakuFlagConfigBuild, "build(...)");
            return danmakuFlagConfigBuild;
        }

        public final void clearRecFlag() {
            this._builder.clearRecFlag();
        }

        public final void clearRecSwitch() {
            this._builder.clearRecSwitch();
        }

        public final void clearRecText() {
            this._builder.clearRecText();
        }

        public final int getRecFlag() {
            return this._builder.getRecFlag();
        }

        public final int getRecSwitch() {
            return this._builder.getRecSwitch();
        }

        public final String getRecText() {
            String recText = this._builder.getRecText();
            Intrinsics.checkNotNullExpressionValue(recText, "getRecText(...)");
            return recText;
        }

        public final void setRecFlag(int i4) {
            this._builder.setRecFlag(i4);
        }

        public final void setRecSwitch(int i4) {
            this._builder.setRecSwitch(i4);
        }

        public final void setRecText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setRecText(value);
        }
    }

    private DanmakuFlagConfigKt() {
    }
}
