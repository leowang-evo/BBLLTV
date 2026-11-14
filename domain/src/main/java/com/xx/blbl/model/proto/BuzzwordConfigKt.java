package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BuzzwordConfigKt {
    public static final BuzzwordConfigKt INSTANCE = new BuzzwordConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.BuzzwordConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.BuzzwordConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class KeywordsProxy extends DslProxy {
            private KeywordsProxy() {
            }
        }

        private Dsl(Dm.BuzzwordConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.BuzzwordConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.BuzzwordConfig _build() {
            Dm.BuzzwordConfig buzzwordConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(buzzwordConfigBuild, "build(...)");
            return buzzwordConfigBuild;
        }

        public final /* synthetic */ void addAllKeywords(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllKeywords(values);
        }

        public final /* synthetic */ void addKeywords(DslList dslList, Dm.BuzzwordShowConfig value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addKeywords(value);
        }

        public final /* synthetic */ void clearKeywords(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearKeywords();
        }

        public final /* synthetic */ DslList getKeywords() {
            List<Dm.BuzzwordShowConfig> keywordsList = this._builder.getKeywordsList();
            Intrinsics.checkNotNullExpressionValue(keywordsList, "getKeywordsList(...)");
            return new DslList(keywordsList);
        }

        public final /* synthetic */ void plusAssignAllKeywords(DslList<Dm.BuzzwordShowConfig, KeywordsProxy> dslList, Iterable<Dm.BuzzwordShowConfig> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllKeywords(dslList, values);
        }

        public final /* synthetic */ void plusAssignKeywords(DslList<Dm.BuzzwordShowConfig, KeywordsProxy> dslList, Dm.BuzzwordShowConfig value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addKeywords(dslList, value);
        }

        public final /* synthetic */ void setKeywords(DslList dslList, int i4, Dm.BuzzwordShowConfig value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setKeywords(i4, value);
        }
    }

    private BuzzwordConfigKt() {
    }
}
