package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmWebViewReplyKt {
    public static final DmWebViewReplyKt INSTANCE = new DmWebViewReplyKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmWebViewReply.Builder _builder;

        public static final class ActivityMetaProxy extends DslProxy {
            private ActivityMetaProxy() {
            }
        }

        public static final class CommandDmsProxy extends DslProxy {
            private CommandDmsProxy() {
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmWebViewReply.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class ExpressionsProxy extends DslProxy {
            private ExpressionsProxy() {
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

        private Dsl(Dm.DmWebViewReply.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmWebViewReply.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmWebViewReply _build() {
            Dm.DmWebViewReply dmWebViewReplyBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmWebViewReplyBuild, "build(...)");
            return dmWebViewReplyBuild;
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

        public final /* synthetic */ void addAllCommandDms(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllCommandDms(values);
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

        public final /* synthetic */ void addCommandDms(DslList dslList, Dm.CommandDm value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addCommandDms(value);
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

        public final void clearCheckBox() {
            this._builder.clearCheckBox();
        }

        public final /* synthetic */ void clearCommandDms(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearCommandDms();
        }

        public final void clearCount() {
            this._builder.clearCount();
        }

        public final void clearDmSge() {
            this._builder.clearDmSge();
        }

        public final /* synthetic */ void clearExpressions(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearExpressions();
        }

        public final void clearFlag() {
            this._builder.clearFlag();
        }

        public final void clearPlayerConfig() {
            this._builder.clearPlayerConfig();
        }

        public final /* synthetic */ void clearPostPanel(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPostPanel();
        }

        public final /* synthetic */ void clearReportFilterContent(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearReportFilterContent();
        }

        public final /* synthetic */ void clearSpecialDms(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearSpecialDms();
        }

        public final void clearState() {
            this._builder.clearState();
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final void clearTextSide() {
            this._builder.clearTextSide();
        }

        public final /* synthetic */ DslList getActivityMeta() {
            List<String> activityMetaList = this._builder.getActivityMetaList();
            Intrinsics.checkNotNullExpressionValue(activityMetaList, "getActivityMetaList(...)");
            return new DslList(activityMetaList);
        }

        public final boolean getCheckBox() {
            return this._builder.getCheckBox();
        }

        public final /* synthetic */ DslList getCommandDms() {
            List<Dm.CommandDm> commandDmsList = this._builder.getCommandDmsList();
            Intrinsics.checkNotNullExpressionValue(commandDmsList, "getCommandDmsList(...)");
            return new DslList(commandDmsList);
        }

        public final long getCount() {
            return this._builder.getCount();
        }

        public final Dm.DmSegConfig getDmSge() {
            Dm.DmSegConfig dmSge = this._builder.getDmSge();
            Intrinsics.checkNotNullExpressionValue(dmSge, "getDmSge(...)");
            return dmSge;
        }

        public final /* synthetic */ DslList getExpressions() {
            List<Dm.Expressions> expressionsList = this._builder.getExpressionsList();
            Intrinsics.checkNotNullExpressionValue(expressionsList, "getExpressionsList(...)");
            return new DslList(expressionsList);
        }

        public final Dm.DanmakuFlagConfig getFlag() {
            Dm.DanmakuFlagConfig flag = this._builder.getFlag();
            Intrinsics.checkNotNullExpressionValue(flag, "getFlag(...)");
            return flag;
        }

        public final Dm.DanmuWebPlayerConfig getPlayerConfig() {
            Dm.DanmuWebPlayerConfig playerConfig = this._builder.getPlayerConfig();
            Intrinsics.checkNotNullExpressionValue(playerConfig, "getPlayerConfig(...)");
            return playerConfig;
        }

        public final /* synthetic */ DslList getPostPanel() {
            List<Dm.PostPanel> postPanelList = this._builder.getPostPanelList();
            Intrinsics.checkNotNullExpressionValue(postPanelList, "getPostPanelList(...)");
            return new DslList(postPanelList);
        }

        public final /* synthetic */ DslList getReportFilterContent() {
            List<String> reportFilterContentList = this._builder.getReportFilterContentList();
            Intrinsics.checkNotNullExpressionValue(reportFilterContentList, "getReportFilterContentList(...)");
            return new DslList(reportFilterContentList);
        }

        public final /* synthetic */ DslList getSpecialDms() {
            List<String> specialDmsList = this._builder.getSpecialDmsList();
            Intrinsics.checkNotNullExpressionValue(specialDmsList, "getSpecialDmsList(...)");
            return new DslList(specialDmsList);
        }

        public final int getState() {
            return this._builder.getState();
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final String getTextSide() {
            String textSide = this._builder.getTextSide();
            Intrinsics.checkNotNullExpressionValue(textSide, "getTextSide(...)");
            return textSide;
        }

        public final boolean hasDmSge() {
            return this._builder.hasDmSge();
        }

        public final boolean hasFlag() {
            return this._builder.hasFlag();
        }

        public final boolean hasPlayerConfig() {
            return this._builder.hasPlayerConfig();
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

        public final /* synthetic */ void plusAssignAllCommandDms(DslList<Dm.CommandDm, CommandDmsProxy> dslList, Iterable<Dm.CommandDm> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllCommandDms(dslList, values);
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

        public final /* synthetic */ void plusAssignCommandDms(DslList<Dm.CommandDm, CommandDmsProxy> dslList, Dm.CommandDm value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addCommandDms(dslList, value);
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

        public final void setCheckBox(boolean z7) {
            this._builder.setCheckBox(z7);
        }

        public final /* synthetic */ void setCommandDms(DslList dslList, int i4, Dm.CommandDm value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCommandDms(i4, value);
        }

        public final void setCount(long j7) {
            this._builder.setCount(j7);
        }

        public final void setDmSge(Dm.DmSegConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDmSge(value);
        }

        public final /* synthetic */ void setExpressions(DslList dslList, int i4, Dm.Expressions value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setExpressions(i4, value);
        }

        public final void setFlag(Dm.DanmakuFlagConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setFlag(value);
        }

        public final void setPlayerConfig(Dm.DanmuWebPlayerConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPlayerConfig(value);
        }

        public final /* synthetic */ void setPostPanel(DslList dslList, int i4, Dm.PostPanel value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPostPanel(i4, value);
        }

        public final /* synthetic */ void setReportFilterContent(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setReportFilterContent(i4, value);
        }

        public final /* synthetic */ void setSpecialDms(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSpecialDms(i4, value);
        }

        public final void setState(int i4) {
            this._builder.setState(i4);
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }

        public final void setTextSide(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setTextSide(value);
        }
    }

    private DmWebViewReplyKt() {
    }
}
