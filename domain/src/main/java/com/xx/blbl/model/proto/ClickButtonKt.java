package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ClickButtonKt {
    public static final ClickButtonKt INSTANCE = new ClickButtonKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.ClickButton.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.ClickButton.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class LandscapeTextFocusProxy extends DslProxy {
            private LandscapeTextFocusProxy() {
            }
        }

        public static final class LandscapeTextProxy extends DslProxy {
            private LandscapeTextProxy() {
            }
        }

        public static final class PortraitTextFocusProxy extends DslProxy {
            private PortraitTextFocusProxy() {
            }
        }

        public static final class PortraitTextProxy extends DslProxy {
            private PortraitTextProxy() {
            }
        }

        private Dsl(Dm.ClickButton.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.ClickButton.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.ClickButton _build() {
            Dm.ClickButton clickButtonBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(clickButtonBuild, "build(...)");
            return clickButtonBuild;
        }

        public final /* synthetic */ void addAllLandscapeText(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllLandscapeText(values);
        }

        public final /* synthetic */ void addAllLandscapeTextFocus(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllLandscapeTextFocus(values);
        }

        public final /* synthetic */ void addAllPortraitText(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllPortraitText(values);
        }

        public final /* synthetic */ void addAllPortraitTextFocus(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllPortraitTextFocus(values);
        }

        public final /* synthetic */ void addLandscapeText(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addLandscapeText(value);
        }

        public final /* synthetic */ void addLandscapeTextFocus(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addLandscapeTextFocus(value);
        }

        public final /* synthetic */ void addPortraitText(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addPortraitText(value);
        }

        public final /* synthetic */ void addPortraitTextFocus(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addPortraitTextFocus(value);
        }

        public final void clearBubble() {
            this._builder.clearBubble();
        }

        public final /* synthetic */ void clearLandscapeText(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearLandscapeText();
        }

        public final /* synthetic */ void clearLandscapeTextFocus(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearLandscapeTextFocus();
        }

        public final /* synthetic */ void clearPortraitText(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPortraitText();
        }

        public final /* synthetic */ void clearPortraitTextFocus(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPortraitTextFocus();
        }

        public final void clearRenderType() {
            this._builder.clearRenderType();
        }

        public final void clearShow() {
            this._builder.clearShow();
        }

        public final Dm.Bubble getBubble() {
            Dm.Bubble bubble = this._builder.getBubble();
            Intrinsics.checkNotNullExpressionValue(bubble, "getBubble(...)");
            return bubble;
        }

        public final /* synthetic */ DslList getLandscapeText() {
            List<String> landscapeTextList = this._builder.getLandscapeTextList();
            Intrinsics.checkNotNullExpressionValue(landscapeTextList, "getLandscapeTextList(...)");
            return new DslList(landscapeTextList);
        }

        public final /* synthetic */ DslList getLandscapeTextFocus() {
            List<String> landscapeTextFocusList = this._builder.getLandscapeTextFocusList();
            Intrinsics.checkNotNullExpressionValue(landscapeTextFocusList, "getLandscapeTextFocusList(...)");
            return new DslList(landscapeTextFocusList);
        }

        public final /* synthetic */ DslList getPortraitText() {
            List<String> portraitTextList = this._builder.getPortraitTextList();
            Intrinsics.checkNotNullExpressionValue(portraitTextList, "getPortraitTextList(...)");
            return new DslList(portraitTextList);
        }

        public final /* synthetic */ DslList getPortraitTextFocus() {
            List<String> portraitTextFocusList = this._builder.getPortraitTextFocusList();
            Intrinsics.checkNotNullExpressionValue(portraitTextFocusList, "getPortraitTextFocusList(...)");
            return new DslList(portraitTextFocusList);
        }

        public final Dm.RenderType getRenderType() {
            Dm.RenderType renderType = this._builder.getRenderType();
            Intrinsics.checkNotNullExpressionValue(renderType, "getRenderType(...)");
            return renderType;
        }

        public final boolean getShow() {
            return this._builder.getShow();
        }

        public final boolean hasBubble() {
            return this._builder.hasBubble();
        }

        public final /* synthetic */ void plusAssignAllLandscapeText(DslList<String, LandscapeTextProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllLandscapeText(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllLandscapeTextFocus(DslList<String, LandscapeTextFocusProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllLandscapeTextFocus(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllPortraitText(DslList<String, PortraitTextProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllPortraitText(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllPortraitTextFocus(DslList<String, PortraitTextFocusProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllPortraitTextFocus(dslList, values);
        }

        public final /* synthetic */ void plusAssignLandscapeText(DslList<String, LandscapeTextProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addLandscapeText(dslList, value);
        }

        public final /* synthetic */ void plusAssignLandscapeTextFocus(DslList<String, LandscapeTextFocusProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addLandscapeTextFocus(dslList, value);
        }

        public final /* synthetic */ void plusAssignPortraitText(DslList<String, PortraitTextProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addPortraitText(dslList, value);
        }

        public final /* synthetic */ void plusAssignPortraitTextFocus(DslList<String, PortraitTextFocusProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addPortraitTextFocus(dslList, value);
        }

        public final void setBubble(Dm.Bubble value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBubble(value);
        }

        public final /* synthetic */ void setLandscapeText(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLandscapeText(i4, value);
        }

        public final /* synthetic */ void setLandscapeTextFocus(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLandscapeTextFocus(i4, value);
        }

        public final /* synthetic */ void setPortraitText(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPortraitText(i4, value);
        }

        public final /* synthetic */ void setPortraitTextFocus(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPortraitTextFocus(i4, value);
        }

        public final void setRenderType(Dm.RenderType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setRenderType(value);
        }

        public final void setShow(boolean z7) {
            this._builder.setShow(z7);
        }
    }

    private ClickButtonKt() {
    }
}
