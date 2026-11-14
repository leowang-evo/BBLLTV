package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class TextInputV2Kt {
    public static final TextInputV2Kt INSTANCE = new TextInputV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.TextInputV2.Builder _builder;

        public static final class AvatarProxy extends DslProxy {
            private AvatarProxy() {
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.TextInputV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class LandscapePlaceholderProxy extends DslProxy {
            private LandscapePlaceholderProxy() {
            }
        }

        public static final class PortraitPlaceholderProxy extends DslProxy {
            private PortraitPlaceholderProxy() {
            }
        }

        private Dsl(Dm.TextInputV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.TextInputV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.TextInputV2 _build() {
            Dm.TextInputV2 textInputV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(textInputV2Build, "build(...)");
            return textInputV2Build;
        }

        public final /* synthetic */ void addAllAvatar(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllAvatar(values);
        }

        public final /* synthetic */ void addAllLandscapePlaceholder(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllLandscapePlaceholder(values);
        }

        public final /* synthetic */ void addAllPortraitPlaceholder(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllPortraitPlaceholder(values);
        }

        public final /* synthetic */ void addAvatar(DslList dslList, Dm.Avatar value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addAvatar(value);
        }

        public final /* synthetic */ void addLandscapePlaceholder(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addLandscapePlaceholder(value);
        }

        public final /* synthetic */ void addPortraitPlaceholder(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addPortraitPlaceholder(value);
        }

        public final /* synthetic */ void clearAvatar(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearAvatar();
        }

        public final /* synthetic */ void clearLandscapePlaceholder(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearLandscapePlaceholder();
        }

        public final void clearPlaceholderPost() {
            this._builder.clearPlaceholderPost();
        }

        public final /* synthetic */ void clearPortraitPlaceholder(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPortraitPlaceholder();
        }

        public final void clearRenderType() {
            this._builder.clearRenderType();
        }

        public final void clearTextInputLimit() {
            this._builder.clearTextInputLimit();
        }

        public final /* synthetic */ DslList getAvatar() {
            List<Dm.Avatar> avatarList = this._builder.getAvatarList();
            Intrinsics.checkNotNullExpressionValue(avatarList, "getAvatarList(...)");
            return new DslList(avatarList);
        }

        public final /* synthetic */ DslList getLandscapePlaceholder() {
            List<String> landscapePlaceholderList = this._builder.getLandscapePlaceholderList();
            Intrinsics.checkNotNullExpressionValue(landscapePlaceholderList, "getLandscapePlaceholderList(...)");
            return new DslList(landscapePlaceholderList);
        }

        public final boolean getPlaceholderPost() {
            return this._builder.getPlaceholderPost();
        }

        public final /* synthetic */ DslList getPortraitPlaceholder() {
            List<String> portraitPlaceholderList = this._builder.getPortraitPlaceholderList();
            Intrinsics.checkNotNullExpressionValue(portraitPlaceholderList, "getPortraitPlaceholderList(...)");
            return new DslList(portraitPlaceholderList);
        }

        public final Dm.RenderType getRenderType() {
            Dm.RenderType renderType = this._builder.getRenderType();
            Intrinsics.checkNotNullExpressionValue(renderType, "getRenderType(...)");
            return renderType;
        }

        public final int getTextInputLimit() {
            return this._builder.getTextInputLimit();
        }

        public final /* synthetic */ void plusAssignAllAvatar(DslList<Dm.Avatar, AvatarProxy> dslList, Iterable<Dm.Avatar> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllAvatar(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllLandscapePlaceholder(DslList<String, LandscapePlaceholderProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllLandscapePlaceholder(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllPortraitPlaceholder(DslList<String, PortraitPlaceholderProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllPortraitPlaceholder(dslList, values);
        }

        public final /* synthetic */ void plusAssignAvatar(DslList<Dm.Avatar, AvatarProxy> dslList, Dm.Avatar value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addAvatar(dslList, value);
        }

        public final /* synthetic */ void plusAssignLandscapePlaceholder(DslList<String, LandscapePlaceholderProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addLandscapePlaceholder(dslList, value);
        }

        public final /* synthetic */ void plusAssignPortraitPlaceholder(DslList<String, PortraitPlaceholderProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addPortraitPlaceholder(dslList, value);
        }

        public final /* synthetic */ void setAvatar(DslList dslList, int i4, Dm.Avatar value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAvatar(i4, value);
        }

        public final /* synthetic */ void setLandscapePlaceholder(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLandscapePlaceholder(i4, value);
        }

        public final void setPlaceholderPost(boolean z7) {
            this._builder.setPlaceholderPost(z7);
        }

        public final /* synthetic */ void setPortraitPlaceholder(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPortraitPlaceholder(i4, value);
        }

        public final void setRenderType(Dm.RenderType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setRenderType(value);
        }

        public final void setTextInputLimit(int i4) {
            this._builder.setTextInputLimit(i4);
        }
    }

    private TextInputV2Kt() {
    }
}
