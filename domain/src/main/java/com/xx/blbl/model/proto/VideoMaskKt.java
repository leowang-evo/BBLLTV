package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoMaskKt {
    public static final VideoMaskKt INSTANCE = new VideoMaskKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.VideoMask.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.VideoMask.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.VideoMask.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.VideoMask.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.VideoMask _build() {
            Dm.VideoMask videoMaskBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(videoMaskBuild, "build(...)");
            return videoMaskBuild;
        }

        public final void clearCid() {
            this._builder.clearCid();
        }

        public final void clearFps() {
            this._builder.clearFps();
        }

        public final void clearMaskUrl() {
            this._builder.clearMaskUrl();
        }

        public final void clearPlat() {
            this._builder.clearPlat();
        }

        public final void clearTime() {
            this._builder.clearTime();
        }

        public final long getCid() {
            return this._builder.getCid();
        }

        public final int getFps() {
            return this._builder.getFps();
        }

        public final String getMaskUrl() {
            String maskUrl = this._builder.getMaskUrl();
            Intrinsics.checkNotNullExpressionValue(maskUrl, "getMaskUrl(...)");
            return maskUrl;
        }

        public final int getPlat() {
            return this._builder.getPlat();
        }

        public final long getTime() {
            return this._builder.getTime();
        }

        public final void setCid(long j7) {
            this._builder.setCid(j7);
        }

        public final void setFps(int i4) {
            this._builder.setFps(i4);
        }

        public final void setMaskUrl(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setMaskUrl(value);
        }

        public final void setPlat(int i4) {
            this._builder.setPlat(i4);
        }

        public final void setTime(long j7) {
            this._builder.setTime(j7);
        }
    }

    private VideoMaskKt() {
    }
}
