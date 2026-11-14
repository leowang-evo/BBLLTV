package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmExpoReportResKt {
    public static final DmExpoReportResKt INSTANCE = new DmExpoReportResKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmExpoReportRes.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmExpoReportRes.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmExpoReportRes.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmExpoReportRes.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmExpoReportRes _build() {
            Dm.DmExpoReportRes dmExpoReportResBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmExpoReportResBuild, "build(...)");
            return dmExpoReportResBuild;
        }
    }

    private DmExpoReportResKt() {
    }
}
