package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ToastKt {
    public static final ToastKt INSTANCE = new ToastKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Toast.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Toast.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.Toast.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Toast.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Toast _build() {
            Dm.Toast toastBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(toastBuild, "build(...)");
            return toastBuild;
        }

        public final void clearButton() {
            this._builder.clearButton();
        }

        public final void clearDuration() {
            this._builder.clearDuration();
        }

        public final void clearShow() {
            this._builder.clearShow();
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final Dm.Button getButton() {
            Dm.Button button = this._builder.getButton();
            Intrinsics.checkNotNullExpressionValue(button, "getButton(...)");
            return button;
        }

        public final int getDuration() {
            return this._builder.getDuration();
        }

        public final boolean getShow() {
            return this._builder.getShow();
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final boolean hasButton() {
            return this._builder.hasButton();
        }

        public final void setButton(Dm.Button value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setButton(value);
        }

        public final void setDuration(int i4) {
            this._builder.setDuration(i4);
        }

        public final void setShow(boolean z7) {
            this._builder.setShow(z7);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }
    }

    private ToastKt() {
    }
}
