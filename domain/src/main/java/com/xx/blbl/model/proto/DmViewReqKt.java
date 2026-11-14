package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmViewReqKt {
    public static final DmViewReqKt INSTANCE = new DmViewReqKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmViewReq.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmViewReq.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmViewReq.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmViewReq.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmViewReq _build() {
            Dm.DmViewReq dmViewReqBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmViewReqBuild, "build(...)");
            return dmViewReqBuild;
        }

        public final void clearIsHardBoot() {
            this._builder.clearIsHardBoot();
        }

        public final void clearOid() {
            this._builder.clearOid();
        }

        public final void clearPid() {
            this._builder.clearPid();
        }

        public final void clearSpmid() {
            this._builder.clearSpmid();
        }

        public final void clearType() {
            this._builder.clearType();
        }

        public final int getIsHardBoot() {
            return this._builder.getIsHardBoot();
        }

        public final long getOid() {
            return this._builder.getOid();
        }

        public final long getPid() {
            return this._builder.getPid();
        }

        public final String getSpmid() {
            String spmid = this._builder.getSpmid();
            Intrinsics.checkNotNullExpressionValue(spmid, "getSpmid(...)");
            return spmid;
        }

        public final int getType() {
            return this._builder.getType();
        }

        public final void setIsHardBoot(int i4) {
            this._builder.setIsHardBoot(i4);
        }

        public final void setOid(long j7) {
            this._builder.setOid(j7);
        }

        public final void setPid(long j7) {
            this._builder.setPid(j7);
        }

        public final void setSpmid(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSpmid(value);
        }

        public final void setType(int i4) {
            this._builder.setType(i4);
        }
    }

    private DmViewReqKt() {
    }
}
