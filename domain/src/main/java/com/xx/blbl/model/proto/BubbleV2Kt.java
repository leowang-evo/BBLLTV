package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BubbleV2Kt {
    public static final BubbleV2Kt INSTANCE = new BubbleV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.BubbleV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.BubbleV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.BubbleV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.BubbleV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.BubbleV2 _build() {
            Dm.BubbleV2 bubbleV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(bubbleV2Build, "build(...)");
            return bubbleV2Build;
        }

        public final void clearBubbleType() {
            this._builder.clearBubbleType();
        }

        public final void clearExposureOnce() {
            this._builder.clearExposureOnce();
        }

        public final void clearExposureType() {
            this._builder.clearExposureType();
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final void clearUrl() {
            this._builder.clearUrl();
        }

        public final Dm.BubbleType getBubbleType() {
            Dm.BubbleType bubbleType = this._builder.getBubbleType();
            Intrinsics.checkNotNullExpressionValue(bubbleType, "getBubbleType(...)");
            return bubbleType;
        }

        public final boolean getExposureOnce() {
            return this._builder.getExposureOnce();
        }

        public final Dm.ExposureType getExposureType() {
            Dm.ExposureType exposureType = this._builder.getExposureType();
            Intrinsics.checkNotNullExpressionValue(exposureType, "getExposureType(...)");
            return exposureType;
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final String getUrl() {
            String url = this._builder.getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
            return url;
        }

        public final void setBubbleType(Dm.BubbleType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBubbleType(value);
        }

        public final void setExposureOnce(boolean z7) {
            this._builder.setExposureOnce(z7);
        }

        public final void setExposureType(Dm.ExposureType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setExposureType(value);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }

        public final void setUrl(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setUrl(value);
        }
    }

    private BubbleV2Kt() {
    }
}
