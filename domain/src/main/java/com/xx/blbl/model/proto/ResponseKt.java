package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseKt {
    public static final ResponseKt INSTANCE = new ResponseKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Response.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Response.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.Response.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Response.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Response _build() {
            Dm.Response responseBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(responseBuild, "build(...)");
            return responseBuild;
        }

        public final void clearCode() {
            this._builder.clearCode();
        }

        public final void clearMessage() {
            this._builder.clearMessage();
        }

        public final int getCode() {
            return this._builder.getCode();
        }

        public final String getMessage() {
            String message = this._builder.getMessage();
            Intrinsics.checkNotNullExpressionValue(message, "getMessage(...)");
            return message;
        }

        public final void setCode(int i4) {
            this._builder.setCode(i4);
        }

        public final void setMessage(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setMessage(value);
        }
    }

    private ResponseKt() {
    }
}
