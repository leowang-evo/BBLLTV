package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmExpoReportReqKt {
    public static final DmExpoReportReqKt INSTANCE = new DmExpoReportReqKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmExpoReportReq.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmExpoReportReq.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmExpoReportReq.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmExpoReportReq.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmExpoReportReq _build() {
            Dm.DmExpoReportReq dmExpoReportReqBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmExpoReportReqBuild, "build(...)");
            return dmExpoReportReqBuild;
        }

        public final void clearOid() {
            this._builder.clearOid();
        }

        public final void clearSessionId() {
            this._builder.clearSessionId();
        }

        public final void clearSpmid() {
            this._builder.clearSpmid();
        }

        public final long getOid() {
            return this._builder.getOid();
        }

        public final String getSessionId() {
            String sessionId = this._builder.getSessionId();
            Intrinsics.checkNotNullExpressionValue(sessionId, "getSessionId(...)");
            return sessionId;
        }

        public final String getSpmid() {
            String spmid = this._builder.getSpmid();
            Intrinsics.checkNotNullExpressionValue(spmid, "getSpmid(...)");
            return spmid;
        }

        public final void setOid(long j7) {
            this._builder.setOid(j7);
        }

        public final void setSessionId(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSessionId(value);
        }

        public final void setSpmid(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSpmid(value);
        }
    }

    private DmExpoReportReqKt() {
    }
}
