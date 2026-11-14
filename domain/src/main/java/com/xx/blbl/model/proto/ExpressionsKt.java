package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ExpressionsKt {
    public static final ExpressionsKt INSTANCE = new ExpressionsKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Expressions.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Expressions.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class DataProxy extends DslProxy {
            private DataProxy() {
            }
        }

        private Dsl(Dm.Expressions.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Expressions.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Expressions _build() {
            Dm.Expressions expressionsBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(expressionsBuild, "build(...)");
            return expressionsBuild;
        }

        public final /* synthetic */ void addAllData(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllData(values);
        }

        public final /* synthetic */ void addData(DslList dslList, Dm.Expression value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addData(value);
        }

        public final /* synthetic */ void clearData(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearData();
        }

        public final /* synthetic */ DslList getData() {
            List<Dm.Expression> dataList = this._builder.getDataList();
            Intrinsics.checkNotNullExpressionValue(dataList, "getDataList(...)");
            return new DslList(dataList);
        }

        public final /* synthetic */ void plusAssignAllData(DslList<Dm.Expression, DataProxy> dslList, Iterable<Dm.Expression> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllData(dslList, values);
        }

        public final /* synthetic */ void plusAssignData(DslList<Dm.Expression, DataProxy> dslList, Dm.Expression value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addData(dslList, value);
        }

        public final /* synthetic */ void setData(DslList dslList, int i4, Dm.Expression value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setData(i4, value);
        }
    }

    private ExpressionsKt() {
    }
}
