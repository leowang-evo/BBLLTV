package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ToastV2Kt {
    public static final ToastV2Kt INSTANCE = new ToastV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.ToastV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.ToastV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.ToastV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.ToastV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.ToastV2 _build() {
            Dm.ToastV2 toastV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(toastV2Build, "build(...)");
            return toastV2Build;
        }

        public final void clearDuration() {
            this._builder.clearDuration();
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final void clearToastButtonV2() {
            this._builder.clearToastButtonV2();
        }

        public final int getDuration() {
            return this._builder.getDuration();
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final Dm.ToastButtonV2 getToastButtonV2() {
            Dm.ToastButtonV2 toastButtonV2 = this._builder.getToastButtonV2();
            Intrinsics.checkNotNullExpressionValue(toastButtonV2, "getToastButtonV2(...)");
            return toastButtonV2;
        }

        public final boolean hasToastButtonV2() {
            return this._builder.hasToastButtonV2();
        }

        public final void setDuration(int i4) {
            this._builder.setDuration(i4);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }

        public final void setToastButtonV2(Dm.ToastButtonV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setToastButtonV2(value);
        }
    }

    private ToastV2Kt() {
    }
}
