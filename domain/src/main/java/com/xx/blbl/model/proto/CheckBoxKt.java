package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CheckBoxKt {
    public static final CheckBoxKt INSTANCE = new CheckBoxKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.CheckBox.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.CheckBox.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.CheckBox.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.CheckBox.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.CheckBox _build() {
            Dm.CheckBox checkBoxBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(checkBoxBuild, "build(...)");
            return checkBoxBuild;
        }

        public final void clearDefaultValue() {
            this._builder.clearDefaultValue();
        }

        public final void clearShow() {
            this._builder.clearShow();
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final void clearType() {
            this._builder.clearType();
        }

        public final boolean getDefaultValue() {
            return this._builder.getDefaultValue();
        }

        public final boolean getShow() {
            return this._builder.getShow();
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final Dm.CheckboxType getType() {
            Dm.CheckboxType type = this._builder.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            return type;
        }

        public final void setDefaultValue(boolean z7) {
            this._builder.setDefaultValue(z7);
        }

        public final void setShow(boolean z7) {
            this._builder.setShow(z7);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }

        public final void setType(Dm.CheckboxType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setType(value);
        }
    }

    private CheckBoxKt() {
    }
}
