package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ExpressionKt {
    public static final ExpressionKt INSTANCE = new ExpressionKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Expression.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Expression.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class KeywordProxy extends DslProxy {
            private KeywordProxy() {
            }
        }

        public static final class PeriodProxy extends DslProxy {
            private PeriodProxy() {
            }
        }

        private Dsl(Dm.Expression.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Expression.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Expression _build() {
            Dm.Expression expressionBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(expressionBuild, "build(...)");
            return expressionBuild;
        }

        public final /* synthetic */ void addAllKeyword(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllKeyword(values);
        }

        public final /* synthetic */ void addAllPeriod(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllPeriod(values);
        }

        public final /* synthetic */ void addKeyword(DslList dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addKeyword(value);
        }

        public final /* synthetic */ void addPeriod(DslList dslList, Dm.Period value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addPeriod(value);
        }

        public final /* synthetic */ void clearKeyword(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearKeyword();
        }

        public final /* synthetic */ void clearPeriod(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearPeriod();
        }

        public final void clearUrl() {
            this._builder.clearUrl();
        }

        public final /* synthetic */ DslList getKeyword() {
            List<String> keywordList = this._builder.getKeywordList();
            Intrinsics.checkNotNullExpressionValue(keywordList, "getKeywordList(...)");
            return new DslList(keywordList);
        }

        public final /* synthetic */ DslList getPeriod() {
            List<Dm.Period> periodList = this._builder.getPeriodList();
            Intrinsics.checkNotNullExpressionValue(periodList, "getPeriodList(...)");
            return new DslList(periodList);
        }

        public final String getUrl() {
            String url = this._builder.getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
            return url;
        }

        public final /* synthetic */ void plusAssignAllKeyword(DslList<String, KeywordProxy> dslList, Iterable<String> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllKeyword(dslList, values);
        }

        public final /* synthetic */ void plusAssignAllPeriod(DslList<Dm.Period, PeriodProxy> dslList, Iterable<Dm.Period> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllPeriod(dslList, values);
        }

        public final /* synthetic */ void plusAssignKeyword(DslList<String, KeywordProxy> dslList, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addKeyword(dslList, value);
        }

        public final /* synthetic */ void plusAssignPeriod(DslList<Dm.Period, PeriodProxy> dslList, Dm.Period value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addPeriod(dslList, value);
        }

        public final /* synthetic */ void setKeyword(DslList dslList, int i4, String value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setKeyword(i4, value);
        }

        public final /* synthetic */ void setPeriod(DslList dslList, int i4, Dm.Period value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setPeriod(i4, value);
        }

        public final void setUrl(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setUrl(value);
        }
    }

    private ExpressionKt() {
    }
}
