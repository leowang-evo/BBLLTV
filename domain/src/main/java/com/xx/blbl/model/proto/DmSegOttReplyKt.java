package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegOttReplyKt {
    public static final DmSegOttReplyKt INSTANCE = new DmSegOttReplyKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmSegOttReply.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmSegOttReply.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class ElemsProxy extends DslProxy {
            private ElemsProxy() {
            }
        }

        private Dsl(Dm.DmSegOttReply.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmSegOttReply.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmSegOttReply _build() {
            Dm.DmSegOttReply dmSegOttReplyBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmSegOttReplyBuild, "build(...)");
            return dmSegOttReplyBuild;
        }

        public final /* synthetic */ void addAllElems(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllElems(values);
        }

        public final /* synthetic */ void addElems(DslList dslList, Dm.DanmakuElem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addElems(value);
        }

        public final void clearClosed() {
            this._builder.clearClosed();
        }

        public final /* synthetic */ void clearElems(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearElems();
        }

        public final boolean getClosed() {
            return this._builder.getClosed();
        }

        public final /* synthetic */ DslList getElems() {
            List<Dm.DanmakuElem> elemsList = this._builder.getElemsList();
            Intrinsics.checkNotNullExpressionValue(elemsList, "getElemsList(...)");
            return new DslList(elemsList);
        }

        public final /* synthetic */ void plusAssignAllElems(DslList<Dm.DanmakuElem, ElemsProxy> dslList, Iterable<Dm.DanmakuElem> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllElems(dslList, values);
        }

        public final /* synthetic */ void plusAssignElems(DslList<Dm.DanmakuElem, ElemsProxy> dslList, Dm.DanmakuElem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addElems(dslList, value);
        }

        public final void setClosed(boolean z7) {
            this._builder.setClosed(z7);
        }

        public final /* synthetic */ void setElems(DslList dslList, int i4, Dm.DanmakuElem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setElems(i4, value);
        }
    }

    private DmSegOttReplyKt() {
    }
}
