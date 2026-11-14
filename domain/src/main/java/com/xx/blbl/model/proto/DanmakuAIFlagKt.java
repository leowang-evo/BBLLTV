package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuAIFlagKt {
    public static final DanmakuAIFlagKt INSTANCE = new DanmakuAIFlagKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmakuAIFlag.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmakuAIFlag.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class DmFlagsProxy extends DslProxy {
            private DmFlagsProxy() {
            }
        }

        private Dsl(Dm.DanmakuAIFlag.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmakuAIFlag.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmakuAIFlag _build() {
            Dm.DanmakuAIFlag danmakuAIFlagBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmakuAIFlagBuild, "build(...)");
            return danmakuAIFlagBuild;
        }

        public final /* synthetic */ void addAllDmFlags(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllDmFlags(values);
        }

        public final /* synthetic */ void addDmFlags(DslList dslList, Dm.DanmakuFlag value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addDmFlags(value);
        }

        public final /* synthetic */ void clearDmFlags(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearDmFlags();
        }

        public final /* synthetic */ DslList getDmFlags() {
            List<Dm.DanmakuFlag> dmFlagsList = this._builder.getDmFlagsList();
            Intrinsics.checkNotNullExpressionValue(dmFlagsList, "getDmFlagsList(...)");
            return new DslList(dmFlagsList);
        }

        public final /* synthetic */ void plusAssignAllDmFlags(DslList<Dm.DanmakuFlag, DmFlagsProxy> dslList, Iterable<Dm.DanmakuFlag> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllDmFlags(dslList, values);
        }

        public final /* synthetic */ void plusAssignDmFlags(DslList<Dm.DanmakuFlag, DmFlagsProxy> dslList, Dm.DanmakuFlag value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addDmFlags(dslList, value);
        }

        public final /* synthetic */ void setDmFlags(DslList dslList, int i4, Dm.DanmakuFlag value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDmFlags(i4, value);
        }
    }

    private DanmakuAIFlagKt() {
    }
}
