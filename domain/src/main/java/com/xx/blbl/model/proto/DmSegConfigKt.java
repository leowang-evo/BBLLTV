package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmSegConfigKt {
    public static final DmSegConfigKt INSTANCE = new DmSegConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmSegConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmSegConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmSegConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmSegConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmSegConfig _build() {
            Dm.DmSegConfig dmSegConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmSegConfigBuild, "build(...)");
            return dmSegConfigBuild;
        }

        public final void clearPageSize() {
            this._builder.clearPageSize();
        }

        public final void clearTotal() {
            this._builder.clearTotal();
        }

        public final long getPageSize() {
            return this._builder.getPageSize();
        }

        public final long getTotal() {
            return this._builder.getTotal();
        }

        public final void setPageSize(long j7) {
            this._builder.setPageSize(j7);
        }

        public final void setTotal(long j7) {
            this._builder.setTotal(j7);
        }
    }

    private DmSegConfigKt() {
    }
}
