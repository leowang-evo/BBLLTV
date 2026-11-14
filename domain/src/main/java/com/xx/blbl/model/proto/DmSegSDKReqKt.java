package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegSDKReqKt {
    public static final DmSegSDKReqKt INSTANCE = new DmSegSDKReqKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmSegSDKReq.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmSegSDKReq.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmSegSDKReq.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmSegSDKReq.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmSegSDKReq _build() {
            Dm.DmSegSDKReq dmSegSDKReqBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmSegSDKReqBuild, "build(...)");
            return dmSegSDKReqBuild;
        }

        public final void clearOid() {
            this._builder.clearOid();
        }

        public final void clearPid() {
            this._builder.clearPid();
        }

        public final void clearSegmentIndex() {
            this._builder.clearSegmentIndex();
        }

        public final void clearType() {
            this._builder.clearType();
        }

        public final long getOid() {
            return this._builder.getOid();
        }

        public final long getPid() {
            return this._builder.getPid();
        }

        public final long getSegmentIndex() {
            return this._builder.getSegmentIndex();
        }

        public final int getType() {
            return this._builder.getType();
        }

        public final void setOid(long j7) {
            this._builder.setOid(j7);
        }

        public final void setPid(long j7) {
            this._builder.setPid(j7);
        }

        public final void setSegmentIndex(long j7) {
            this._builder.setSegmentIndex(j7);
        }

        public final void setType(int i4) {
            this._builder.setType(i4);
        }
    }

    private DmSegSDKReqKt() {
    }
}
