package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CheckBoxV2Kt {
    public static final CheckBoxV2Kt INSTANCE = new CheckBoxV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.CheckBoxV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.CheckBoxV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.CheckBoxV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.CheckBoxV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.CheckBoxV2 _build() {
            Dm.CheckBoxV2 checkBoxV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(checkBoxV2Build, "build(...)");
            return checkBoxV2Build;
        }

        public final void clearDefaultValue() {
            this._builder.clearDefaultValue();
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

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final int getType() {
            return this._builder.getType();
        }

        public final void setDefaultValue(boolean z7) {
            this._builder.setDefaultValue(z7);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }

        public final void setType(int i4) {
            this._builder.setType(i4);
        }
    }

    private CheckBoxV2Kt() {
    }
}
