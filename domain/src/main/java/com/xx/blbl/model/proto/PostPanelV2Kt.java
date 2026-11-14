package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PostPanelV2Kt {
    public static final PostPanelV2Kt INSTANCE = new PostPanelV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.PostPanelV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.PostPanelV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.PostPanelV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.PostPanelV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.PostPanelV2 _build() {
            Dm.PostPanelV2 postPanelV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(postPanelV2Build, "build(...)");
            return postPanelV2Build;
        }

        public final void clearBizType() {
            this._builder.clearBizType();
        }

        public final void clearBubble() {
            this._builder.clearBubble();
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

        public final void clearLabel() {
            this._builder.clearLabel();
        }

        public final void clearPostStatus() {
            this._builder.clearPostStatus();
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

        public final int getBizType() {
            return this._builder.getBizType();
        }

        public final Dm.BubbleV2 getBubble() {
            Dm.BubbleV2 bubble = this._builder.getBubble();
            Intrinsics.checkNotNullExpressionValue(bubble, "getBubble(...)");
            return bubble;
        }

        public final Dm.CheckBoxV2 getCheckBox() {
            Dm.CheckBoxV2 checkBox = this._builder.getCheckBox();
            Intrinsics.checkNotNullExpressionValue(checkBox, "getCheckBox(...)");
            return checkBox;
        }

        public final Dm.ClickButtonV2 getClickButton() {
            Dm.ClickButtonV2 clickButton = this._builder.getClickButton();
            Intrinsics.checkNotNullExpressionValue(clickButton, "getClickButton(...)");
            return clickButton;
        }

        public final long getEnd() {
            return this._builder.getEnd();
        }

        public final Dm.LabelV2 getLabel() {
            Dm.LabelV2 label = this._builder.getLabel();
            Intrinsics.checkNotNullExpressionValue(label, "getLabel(...)");
            return label;
        }

        public final int getPostStatus() {
            return this._builder.getPostStatus();
        }

        public final long getStart() {
            return this._builder.getStart();
        }

        public final Dm.TextInputV2 getTextInput() {
            Dm.TextInputV2 textInput = this._builder.getTextInput();
            Intrinsics.checkNotNullExpressionValue(textInput, "getTextInput(...)");
            return textInput;
        }

        public final Dm.ToastV2 getToast() {
            Dm.ToastV2 toast = this._builder.getToast();
            Intrinsics.checkNotNullExpressionValue(toast, "getToast(...)");
            return toast;
        }

        public final boolean hasBubble() {
            return this._builder.hasBubble();
        }

        public final boolean hasCheckBox() {
            return this._builder.hasCheckBox();
        }

        public final boolean hasClickButton() {
            return this._builder.hasClickButton();
        }

        public final boolean hasLabel() {
            return this._builder.hasLabel();
        }

        public final boolean hasTextInput() {
            return this._builder.hasTextInput();
        }

        public final boolean hasToast() {
            return this._builder.hasToast();
        }

        public final void setBizType(int i4) {
            this._builder.setBizType(i4);
        }

        public final void setBubble(Dm.BubbleV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBubble(value);
        }

        public final void setCheckBox(Dm.CheckBoxV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCheckBox(value);
        }

        public final void setClickButton(Dm.ClickButtonV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setClickButton(value);
        }

        public final void setEnd(long j7) {
            this._builder.setEnd(j7);
        }

        public final void setLabel(Dm.LabelV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLabel(value);
        }

        public final void setPostStatus(int i4) {
            this._builder.setPostStatus(i4);
        }

        public final void setStart(long j7) {
            this._builder.setStart(j7);
        }

        public final void setTextInput(Dm.TextInputV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setTextInput(value);
        }

        public final void setToast(Dm.ToastV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setToast(value);
        }
    }

    private PostPanelV2Kt() {
    }
}
