package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegMobileReqKt {
    public static final DmSegMobileReqKt INSTANCE = new DmSegMobileReqKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmSegMobileReq.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmSegMobileReq.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmSegMobileReq.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmSegMobileReq.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmSegMobileReq _build() {
            Dm.DmSegMobileReq dmSegMobileReqBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmSegMobileReqBuild, "build(...)");
            return dmSegMobileReqBuild;
        }

        public final void clearFromScene() {
            this._builder.clearFromScene();
        }

        public final void clearOid() {
            this._builder.clearOid();
        }

        public final void clearPe() {
            this._builder.clearPe();
        }

        public final void clearPid() {
            this._builder.clearPid();
        }

        public final void clearPs() {
            this._builder.clearPs();
        }

        public final void clearPullMode() {
            this._builder.clearPullMode();
        }

        public final void clearSegmentIndex() {
            this._builder.clearSegmentIndex();
        }

        public final void clearTeenagersMode() {
            this._builder.clearTeenagersMode();
        }

        public final void clearType() {
            this._builder.clearType();
        }

        public final int getFromScene() {
            return this._builder.getFromScene();
        }

        public final long getOid() {
            return this._builder.getOid();
        }

        public final long getPe() {
            return this._builder.getPe();
        }

        public final long getPid() {
            return this._builder.getPid();
        }

        public final long getPs() {
            return this._builder.getPs();
        }

        public final int getPullMode() {
            return this._builder.getPullMode();
        }

        public final long getSegmentIndex() {
            return this._builder.getSegmentIndex();
        }

        public final int getTeenagersMode() {
            return this._builder.getTeenagersMode();
        }

        public final int getType() {
            return this._builder.getType();
        }

        public final void setFromScene(int i4) {
            this._builder.setFromScene(i4);
        }

        public final void setOid(long j7) {
            this._builder.setOid(j7);
        }

        public final void setPe(long j7) {
            this._builder.setPe(j7);
        }

        public final void setPid(long j7) {
            this._builder.setPid(j7);
        }

        public final void setPs(long j7) {
            this._builder.setPs(j7);
        }

        public final void setPullMode(int i4) {
            this._builder.setPullMode(i4);
        }

        public final void setSegmentIndex(long j7) {
            this._builder.setSegmentIndex(j7);
        }

        public final void setTeenagersMode(int i4) {
            this._builder.setTeenagersMode(i4);
        }

        public final void setType(int i4) {
            this._builder.setType(i4);
        }
    }

    private DmSegMobileReqKt() {
    }
}
