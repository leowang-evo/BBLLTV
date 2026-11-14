package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ButtonKt {
    public static final ButtonKt INSTANCE = new ButtonKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Button.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Button.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.Button.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Button.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Button _build() {
            Dm.Button buttonBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(buttonBuild, "build(...)");
            return buttonBuild;
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

    private ButtonKt() {
    }
}
