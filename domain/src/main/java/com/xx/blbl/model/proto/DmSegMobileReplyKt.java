package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegMobileReplyKt {
    public static final DmSegMobileReplyKt INSTANCE = new DmSegMobileReplyKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmSegMobileReply.Builder _builder;

        public static final class ColorfulSrcProxy extends DslProxy {
            private ColorfulSrcProxy() {
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmSegMobileReply.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class ElemsProxy extends DslProxy {
            private ElemsProxy() {
            }
        }

        private Dsl(Dm.DmSegMobileReply.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmSegMobileReply.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmSegMobileReply _build() {
            Dm.DmSegMobileReply dmSegMobileReplyBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmSegMobileReplyBuild, "build(...)");
            return dmSegMobileReplyBuild;
        }

        public final /* synthetic */ void addAllColorfulSrc(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllColorfulSrc(values);
        }

        public final /* synthetic */ void addAllElems(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllElems(values);
        }

        public final /* synthetic */ void addColorfulSrc(DslList dslList, Dm.DmColorful value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addColorfulSrc(value);
        }

        public final /* synthetic */ void addElems(DslList dslList, Dm.DanmakuElem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addElems(value);
        }

        public final void clearAiFlag() {
            this._builder.clearAiFlag();
        }

        public final /* synthetic */ void clearColorfulSrc(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearColorfulSrc();
        }

        public final /* synthetic */ void clearElems(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearElems();
        }

        public final void clearState() {
            this._builder.clearState();
        }

        public final Dm.DanmakuAIFlag getAiFlag() {
            Dm.DanmakuAIFlag aiFlag = this._builder.getAiFlag();
            Intrinsics.checkNotNullExpressionValue(aiFlag, "getAiFlag(...)");
            return aiFlag;
        }

        public final /* synthetic */ DslList getColorfulSrc() {
            List<Dm.DmColorful> colorfulSrcList = this._builder.getColorfulSrcList();
            Intrinsics.checkNotNullExpressionValue(colorfulSrcList, "getColorfulSrcList(...)");
            return new DslList(colorfulSrcList);
        }

        public final /* synthetic */ DslList getElems() {
            List<Dm.DanmakuElem> elemsList = this._builder.getElemsList();
            Intrinsics.checkNotNullExpressionValue(elemsList, "getElemsList(...)");
            return new DslList(elemsList);
        }

        public final int getState() {
            return this._builder.getState();
        }

        public final boolean hasAiFlag() {
            return this._builder.hasAiFlag();
        }

        public final /* synthetic */ void plusAssignAllColorfulSrc(DslList<Dm.DmColorful, ColorfulSrcProxy> dslList, Iterable<Dm.DmColorful> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllColorfulSrc(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllElems(DslList<Dm.DanmakuElem, ElemsProxy> dslList, Iterable<Dm.DanmakuElem> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllElems(dslList, values);
        }

        public final /* synthetic */ void plusAssignColorfulSrc(DslList<Dm.DmColorful, ColorfulSrcProxy> dslList, Dm.DmColorful value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addColorfulSrc(dslList, value);
        }

        public final /* synthetic */ void plusAssignElems(DslList<Dm.DanmakuElem, ElemsProxy> dslList, Dm.DanmakuElem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addElems(dslList, value);
        }

        public final void setAiFlag(Dm.DanmakuAIFlag value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiFlag(value);
        }

        public final /* synthetic */ void setColorfulSrc(DslList dslList, int i4, Dm.DmColorful value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setColorfulSrc(i4, value);
        }

        public final /* synthetic */ void setElems(DslList dslList, int i4, Dm.DanmakuElem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setElems(i4, value);
        }

        public final void setState(int i4) {
            this._builder.setState(i4);
        }
    }

    private DmSegMobileReplyKt() {
    }
}
