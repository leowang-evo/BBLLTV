package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PostPanelKt {
    public static final PostPanelKt INSTANCE = new PostPanelKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PostPanel.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PostPanel.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PostPanel.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PostPanel.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PostPanel _build() {
            Dm.PostPanel postPanelBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(postPanelBuild, "build(...)");
            return postPanelBuild;
        }

        public final void clearBizId() {
            this._builder.clearBizId();
        }

        public final void clearBizType() {
            this._builder.clearBizType();
        }

        public final void clearCheckBox() {
            this._builder.clearCheckBox();
        }

        public final void clearClickButton() {
            this._builder.clearClickButton();
        }

        public final void clearEnd() {
            this._builder.clearEnd();
        }

        public final void clearPriority() {
            this._builder.clearPriority();
        }

        public final void clearStart() {
            this._builder.clearStart();
        }

        public final void clearTextInput() {
            this._builder.clearTextInput();
        }

        public final void clearToast() {
            this._builder.clearToast();
        }

        public final long getBizId() {
            return this._builder.getBizId();
        }

        public final Dm.PostPanelBizType getBizType() {
            Dm.PostPanelBizType bizType = this._builder.getBizType();
            Intrinsics.checkNotNullExpressionValue(bizType, "getBizType(...)");
            return bizType;
        }

        public final Dm.CheckBox getCheckBox() {
            Dm.CheckBox checkBox = this._builder.getCheckBox();
            Intrinsics.checkNotNullExpressionValue(checkBox, "getCheckBox(...)");
            return checkBox;
        }

        public final Dm.ClickButton getClickButton() {
            Dm.ClickButton clickButton = this._builder.getClickButton();
            Intrinsics.checkNotNullExpressionValue(clickButton, "getClickButton(...)");
            return clickButton;
        }

        public final long getEnd() {
            return this._builder.getEnd();
        }

        public final long getPriority() {
            return this._builder.getPriority();
        }

        public final long getStart() {
            return this._builder.getStart();
        }

        public final Dm.TextInput getTextInput() {
            Dm.TextInput textInput = this._builder.getTextInput();
            Intrinsics.checkNotNullExpressionValue(textInput, "getTextInput(...)");
            return textInput;
        }

        public final Dm.Toast getToast() {
            Dm.Toast toast = this._builder.getToast();
            Intrinsics.checkNotNullExpressionValue(toast, "getToast(...)");
            return toast;
        }

        public final boolean hasCheckBox() {
            return this._builder.hasCheckBox();
        }

        public final boolean hasClickButton() {
            return this._builder.hasClickButton();
        }

        public final boolean hasTextInput() {
            return this._builder.hasTextInput();
        }

        public final boolean hasToast() {
            return this._builder.hasToast();
        }

        public final void setBizId(long j7) {
            this._builder.setBizId(j7);
        }

        public final void setBizType(Dm.PostPanelBizType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBizType(value);
        }

        public final void setCheckBox(Dm.CheckBox value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCheckBox(value);
        }

        public final void setClickButton(Dm.ClickButton value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setClickButton(value);
        }

        public final void setEnd(long j7) {
            this._builder.setEnd(j7);
        }

        public final void setPriority(long j7) {
            this._builder.setPriority(j7);
        }

        public final void setStart(long j7) {
            this._builder.setStart(j7);
        }

        public final void setTextInput(Dm.TextInput value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setTextInput(value);
        }

        public final void setToast(Dm.Toast value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setToast(value);
        }
    }

    private PostPanelKt() {
    }
}
