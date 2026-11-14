package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmViewReplyKt {
    public static final DmViewReplyKt INSTANCE = new DmViewReplyKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmViewReply.Builder _builder;

        public static final class ActivityMetaProxy extends DslProxy {
            private ActivityMetaProxy() {
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmViewReply.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class ExpressionsProxy extends DslProxy {
            private ExpressionsProxy() {
            }
        }

        public static final class PostPanel2Proxy extends DslProxy {
            private PostPanel2Proxy() {
            }
        }

        public static final class PostPanelProxy extends DslProxy {
            private PostPanelProxy() {
            }
        }

        public static final class ReportFilterContentProxy extends DslProxy {
            private ReportFilterContentProxy() {
            }
        }

        public static final class SpecialDmsProxy extends DslProxy {
            private SpecialDmsProxy() {
            }
        }

        private Dsl(Dm.DmViewReply.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmViewReply.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmViewReply _build() {
            Dm.DmViewReply dmViewReplyBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmViewReplyBuild, "build(...)");
            return dmViewReplyBuild;
        }

        public final /* synthetic */ void addActivityMeta(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addActivityMeta(value);
        }

        public final /* synthetic */ void addAllActivityMeta(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllActivityMeta(values);
        }

        public final /* synthetic */ void addAllExpressions(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllExpressions(values);
        }

        public final /* synthetic */ void addAllPostPanel(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllPostPanel(values);
        }

        public final /* synthetic */ void addAllPostPanel2(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllPostPanel2(values);
        }

        public final /* synthetic */ void addAllReportFilterContent(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllReportFilterContent(values);
        }

        public final /* synthetic */ void addAllSpecialDms(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllSpecialDms(values);
        }

        public final /* synthetic */ void addExpressions(DslList dslList, Dm.Expressions value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addExpressions(value);
        }

        public final /* synthetic */ void addPostPanel(DslList dslList, Dm.PostPanel value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addPostPanel(value);
        }

        public final /* synthetic */ void addPostPanel2(DslList dslList, Dm.PostPanelV2 value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addPostPanel2(value);
        }

        public final /* synthetic */ void addReportFilterContent(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addReportFilterContent(value);
        }

        public final /* synthetic */ void addSpecialDms(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addSpecialDms(value);
        }

        public final /* synthetic */ void clearActivityMeta(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearActivityMeta();
        }

        public final void clearAiFlag() {
            this._builder.clearAiFlag();
        }

        public final void clearAllow() {
            this._builder.clearAllow();
        }

        public final void clearBuzzwordConfig() {
            this._builder.clearBuzzwordConfig();
        }

        public final void clearCheckBox() {
            this._builder.clearCheckBox();
        }

        public final void clearCheckBoxShowMsg() {
            this._builder.clearCheckBoxShowMsg();
        }

        public final void clearClosed() {
            this._builder.clearClosed();
        }

        public final void clearExpoReport() {
            this._builder.clearExpoReport();
        }

        public final /* synthetic */ void clearExpressions(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearExpressions();
        }

        public final void clearInputPlaceholder() {
            this._builder.clearInputPlaceholder();
        }

        public final void clearMask() {
            this._builder.clearMask();
        }

        public final void clearPlayerConfig() {
            this._builder.clearPlayerConfig();
        }

        public final /* synthetic */ void clearPostPanel(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPostPanel();
        }

        public final /* synthetic */ void clearPostPanel2(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPostPanel2();
        }

        public final /* synthetic */ void clearReportFilterContent(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearReportFilterContent();
        }

        public final void clearSendBoxStyle() {
            this._builder.clearSendBoxStyle();
        }

        public final /* synthetic */ void clearSpecialDms(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearSpecialDms();
        }

        public final void clearSubtitle() {
            this._builder.clearSubtitle();
        }

        public final void clearTextPlaceholder() {
            this._builder.clearTextPlaceholder();
        }

        public final /* synthetic */ DslList getActivityMeta() {
            List<String> activityMetaList = this._builder.getActivityMetaList();
            Intrinsics.checkNotNullExpressionValue(activityMetaList, "getActivityMetaList(...)");
            return new DslList(activityMetaList);
        }

        public final Dm.DanmakuFlagConfig getAiFlag() {
            Dm.DanmakuFlagConfig aiFlag = this._builder.getAiFlag();
            Intrinsics.checkNotNullExpressionValue(aiFlag, "getAiFlag(...)");
            return aiFlag;
        }

        public final boolean getAllow() {
            return this._builder.getAllow();
        }

        public final Dm.BuzzwordConfig getBuzzwordConfig() {
            Dm.BuzzwordConfig buzzwordConfig = this._builder.getBuzzwordConfig();
            Intrinsics.checkNotNullExpressionValue(buzzwordConfig, "getBuzzwordConfig(...)");
            return buzzwordConfig;
        }

        public final String getCheckBox() {
            String checkBox = this._builder.getCheckBox();
            Intrinsics.checkNotNullExpressionValue(checkBox, "getCheckBox(...)");
            return checkBox;
        }

        public final String getCheckBoxShowMsg() {
            String checkBoxShowMsg = this._builder.getCheckBoxShowMsg();
            Intrinsics.checkNotNullExpressionValue(checkBoxShowMsg, "getCheckBoxShowMsg(...)");
            return checkBoxShowMsg;
        }

        public final boolean getClosed() {
            return this._builder.getClosed();
        }

        public final Dm.ExpoReport getExpoReport() {
            Dm.ExpoReport expoReport = this._builder.getExpoReport();
            Intrinsics.checkNotNullExpressionValue(expoReport, "getExpoReport(...)");
            return expoReport;
        }

        public final /* synthetic */ DslList getExpressions() {
            List<Dm.Expressions> expressionsList = this._builder.getExpressionsList();
            Intrinsics.checkNotNullExpressionValue(expressionsList, "getExpressionsList(...)");
            return new DslList(expressionsList);
        }

        public final String getInputPlaceholder() {
            String inputPlaceholder = this._builder.getInputPlaceholder();
            Intrinsics.checkNotNullExpressionValue(inputPlaceholder, "getInputPlaceholder(...)");
            return inputPlaceholder;
        }

        public final Dm.VideoMask getMask() {
            Dm.VideoMask mask = this._builder.getMask();
            Intrinsics.checkNotNullExpressionValue(mask, "getMask(...)");
            return mask;
        }

        public final Dm.DanmuPlayerViewConfig getPlayerConfig() {
            Dm.DanmuPlayerViewConfig playerConfig = this._builder.getPlayerConfig();
            Intrinsics.checkNotNullExpressionValue(playerConfig, "getPlayerConfig(...)");
            return playerConfig;
        }

        public final /* synthetic */ DslList getPostPanel() {
            List<Dm.PostPanel> postPanelList = this._builder.getPostPanelList();
            Intrinsics.checkNotNullExpressionValue(postPanelList, "getPostPanelList(...)");
            return new DslList(postPanelList);
        }

        public final /* synthetic */ DslList getPostPanel2() {
            List<Dm.PostPanelV2> postPanel2List = this._builder.getPostPanel2List();
            Intrinsics.checkNotNullExpressionValue(postPanel2List, "getPostPanel2List(...)");
            return new DslList(postPanel2List);
        }

        public final /* synthetic */ DslList getReportFilterContent() {
            List<String> reportFilterContentList = this._builder.getReportFilterContentList();
            Intrinsics.checkNotNullExpressionValue(reportFilterContentList, "getReportFilterContentList(...)");
            return new DslList(reportFilterContentList);
        }

        public final int getSendBoxStyle() {
            return this._builder.getSendBoxStyle();
        }

        public final /* synthetic */ DslList getSpecialDms() {
            List<String> specialDmsList = this._builder.getSpecialDmsList();
            Intrinsics.checkNotNullExpressionValue(specialDmsList, "getSpecialDmsList(...)");
            return new DslList(specialDmsList);
        }

        public final Dm.VideoSubtitle getSubtitle() {
            Dm.VideoSubtitle subtitle = this._builder.getSubtitle();
            Intrinsics.checkNotNullExpressionValue(subtitle, "getSubtitle(...)");
            return subtitle;
        }

        public final String getTextPlaceholder() {
            String textPlaceholder = this._builder.getTextPlaceholder();
            Intrinsics.checkNotNullExpressionValue(textPlaceholder, "getTextPlaceholder(...)");
            return textPlaceholder;
        }

        public final boolean hasAiFlag() {
            return this._builder.hasAiFlag();
        }

        public final boolean hasBuzzwordConfig() {
            return this._builder.hasBuzzwordConfig();
        }

        public final boolean hasExpoReport() {
            return this._builder.hasExpoReport();
        }

        public final boolean hasMask() {
            return this._builder.hasMask();
        }

        public final boolean hasPlayerConfig() {
            return this._builder.hasPlayerConfig();
        }

        public final boolean hasSubtitle() {
            return this._builder.hasSubtitle();
        }

        public final /* synthetic */ void plusAssignActivityMeta(DslList<String, ActivityMetaProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addActivityMeta(dslList, value);
        }

        public final /* synthetic */ void plusAssignAllActivityMeta(DslList<String, ActivityMetaProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllActivityMeta(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllExpressions(DslList<Dm.Expressions, ExpressionsProxy> dslList, Iterable<Dm.Expressions> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllExpressions(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllPostPanel(DslList<Dm.PostPanel, PostPanelProxy> dslList, Iterable<Dm.PostPanel> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllPostPanel(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllPostPanel2(DslList<Dm.PostPanelV2, PostPanel2Proxy> dslList, Iterable<Dm.PostPanelV2> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllPostPanel2(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllReportFilterContent(DslList<String, ReportFilterContentProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllReportFilterContent(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllSpecialDms(DslList<String, SpecialDmsProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllSpecialDms(dslList, values);
        }

        public final /* synthetic */ void plusAssignExpressions(DslList<Dm.Expressions, ExpressionsProxy> dslList, Dm.Expressions value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addExpressions(dslList, value);
        }

        public final /* synthetic */ void plusAssignPostPanel(DslList<Dm.PostPanel, PostPanelProxy> dslList, Dm.PostPanel value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addPostPanel(dslList, value);
        }

        public final /* synthetic */ void plusAssignPostPanel2(DslList<Dm.PostPanelV2, PostPanel2Proxy> dslList, Dm.PostPanelV2 value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addPostPanel2(dslList, value);
        }

        public final /* synthetic */ void plusAssignReportFilterContent(DslList<String, ReportFilterContentProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addReportFilterContent(dslList, value);
        }

        public final /* synthetic */ void plusAssignSpecialDms(DslList<String, SpecialDmsProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addSpecialDms(dslList, value);
        }

        public final /* synthetic */ void setActivityMeta(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setActivityMeta(i4, value);
        }

        public final void setAiFlag(Dm.DanmakuFlagConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiFlag(value);
        }

        public final void setAllow(boolean z7) {
            this._builder.setAllow(z7);
        }

        public final void setBuzzwordConfig(Dm.BuzzwordConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBuzzwordConfig(value);
        }

        public final void setCheckBox(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCheckBox(value);
        }

        public final void setCheckBoxShowMsg(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCheckBoxShowMsg(value);
        }

        public final void setClosed(boolean z7) {
            this._builder.setClosed(z7);
        }

        public final void setExpoReport(Dm.ExpoReport value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setExpoReport(value);
        }

        public final /* synthetic */ void setExpressions(DslList dslList, int i4, Dm.Expressions value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setExpressions(i4, value);
        }

        public final void setInputPlaceholder(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setInputPlaceholder(value);
        }

        public final void setMask(Dm.VideoMask value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setMask(value);
        }

        public final void setPlayerConfig(Dm.DanmuPlayerViewConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPlayerConfig(value);
        }

        public final /* synthetic */ void setPostPanel(DslList dslList, int i4, Dm.PostPanel value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPostPanel(i4, value);
        }

        public final /* synthetic */ void setPostPanel2(DslList dslList, int i4, Dm.PostPanelV2 value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPostPanel2(i4, value);
        }

        public final /* synthetic */ void setReportFilterContent(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setReportFilterContent(i4, value);
        }

        public final void setSendBoxStyle(int i4) {
            this._builder.setSendBoxStyle(i4);
        }

        public final /* synthetic */ void setSpecialDms(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSpecialDms(i4, value);
        }

        public final void setSubtitle(Dm.VideoSubtitle value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSubtitle(value);
        }

        public final void setTextPlaceholder(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setTextPlaceholder(value);
        }
    }

    private DmViewReplyKt() {
    }
}
