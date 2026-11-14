package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmColorfulKt {
    public static final DmColorfulKt INSTANCE = new DmColorfulKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmColorful.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmColorful.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmColorful.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmColorful.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmColorful _build() {
            Dm.DmColorful dmColorfulBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmColorfulBuild, "build(...)");
            return dmColorfulBuild;
        }

        public final void clearSrc() {
            this._builder.clearSrc();
        }

        public final void clearType() {
            this._builder.clearType();
        }

        public final String getSrc() {
            String src = this._builder.getSrc();
            Intrinsics.checkNotNullExpressionValue(src, "getSrc(...)");
            return src;
        }

        public final Dm.DmColorfulType getType() {
            Dm.DmColorfulType type = this._builder.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            return type;
        }

        public final void setSrc(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSrc(value);
        }

        public final void setType(Dm.DmColorfulType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setType(value);
        }
    }

    private DmColorfulKt() {
    }
}
