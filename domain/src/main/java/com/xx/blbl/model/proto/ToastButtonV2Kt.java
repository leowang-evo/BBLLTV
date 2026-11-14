package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ToastButtonV2Kt {
    public static final ToastButtonV2Kt INSTANCE = new ToastButtonV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.ToastButtonV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.ToastButtonV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.ToastButtonV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.ToastButtonV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.ToastButtonV2 _build() {
            Dm.ToastButtonV2 toastButtonV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(toastButtonV2Build, "build(...)");
            return toastButtonV2Build;
        }

        public final void clearAction() {
            this._builder.clearAction();
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final int getAction() {
            return this._builder.getAction();
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final void setAction(int i4) {
            this._builder.setAction(i4);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }
    }

    private ToastButtonV2Kt() {
    }
}
