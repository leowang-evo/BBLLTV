package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class LabelV2Kt {
    public static final LabelV2Kt INSTANCE = new LabelV2Kt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.LabelV2.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.LabelV2.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class ContentProxy extends DslProxy {
            private ContentProxy() {
            }
        }

        private Dsl(Dm.LabelV2.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.LabelV2.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.LabelV2 _build() {
            Dm.LabelV2 labelV2Build = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(labelV2Build, "build(...)");
            return labelV2Build;
        }

        public final /* synthetic */ void addAllContent(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllContent(values);
        }

        public final /* synthetic */ void addContent(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addContent(value);
        }

        public final /* synthetic */ void clearContent(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearContent();
        }

        public final void clearExposureOnce() {
            this._builder.clearExposureOnce();
        }

        public final void clearExposureType() {
            this._builder.clearExposureType();
        }

        public final void clearTitle() {
            this._builder.clearTitle();
        }

        public final /* synthetic */ DslList getContent() {
            List<String> contentList = this._builder.getContentList();
            Intrinsics.checkNotNullExpressionValue(contentList, "getContentList(...)");
            return new DslList(contentList);
        }

        public final boolean getExposureOnce() {
            return this._builder.getExposureOnce();
        }

        public final int getExposureType() {
            return this._builder.getExposureType();
        }

        public final String getTitle() {
            String title = this._builder.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            return title;
        }

        public final /* synthetic */ void plusAssignAllContent(DslList<String, ContentProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllContent(dslList, values);
        }

        public final /* synthetic */ void plusAssignContent(DslList<String, ContentProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addContent(dslList, value);
        }

        public final /* synthetic */ void setContent(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setContent(i4, value);
        }

        public final void setExposureOnce(boolean z7) {
            this._builder.setExposureOnce(z7);
        }

        public final void setExposureType(int i4) {
            this._builder.setExposureType(i4);
        }

        public final void setTitle(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setTitle(value);
        }
    }

    private LabelV2Kt() {
    }
}
