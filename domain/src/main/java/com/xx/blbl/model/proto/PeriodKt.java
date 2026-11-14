package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PeriodKt {
    public static final PeriodKt INSTANCE = new PeriodKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Period.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Period.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.Period.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Period.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Period _build() {
            Dm.Period periodBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(periodBuild, "build(...)");
            return periodBuild;
        }

        public final void clearEnd() {
            this._builder.clearEnd();
        }

        public final void clearStart() {
            this._builder.clearStart();
        }

        public final long getEnd() {
            return this._builder.getEnd();
        }

        public final long getStart() {
            return this._builder.getStart();
        }

        public final void setEnd(long j7) {
            this._builder.setEnd(j7);
        }

        public final void setStart(long j7) {
            this._builder.setStart(j7);
        }
    }

    private PeriodKt() {
    }
}
