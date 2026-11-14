package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ExpoReportKt {
    public static final ExpoReportKt INSTANCE = new ExpoReportKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.ExpoReport.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.ExpoReport.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.ExpoReport.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.ExpoReport.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.ExpoReport _build() {
            Dm.ExpoReport expoReportBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(expoReportBuild, "build(...)");
            return expoReportBuild;
        }

        public final void clearShouldReportAtEnd() {
            this._builder.clearShouldReportAtEnd();
        }

        public final boolean getShouldReportAtEnd() {
            return this._builder.getShouldReportAtEnd();
        }

        public final void setShouldReportAtEnd(boolean z7) {
            this._builder.setShouldReportAtEnd(z7);
        }
    }

    private ExpoReportKt() {
    }
}
