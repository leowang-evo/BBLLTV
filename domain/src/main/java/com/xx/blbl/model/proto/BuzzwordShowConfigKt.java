package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BuzzwordShowConfigKt {
    public static final BuzzwordShowConfigKt INSTANCE = new BuzzwordShowConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.BuzzwordShowConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.BuzzwordShowConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.BuzzwordShowConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.BuzzwordShowConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.BuzzwordShowConfig _build() {
            Dm.BuzzwordShowConfig buzzwordShowConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(buzzwordShowConfigBuild, "build(...)");
            return buzzwordShowConfigBuild;
        }

        public final void clearBuzzwordId() {
            this._builder.clearBuzzwordId();
        }

        public final void clearId() {
            this._builder.clearId();
        }

        public final void clearName() {
            this._builder.clearName();
        }

        public final void clearSchema() {
            this._builder.clearSchema();
        }

        public final void clearSchemaType() {
            this._builder.clearSchemaType();
        }

        public final void clearSource() {
            this._builder.clearSource();
        }

        public final long getBuzzwordId() {
            return this._builder.getBuzzwordId();
        }

        public final long getId() {
            return this._builder.getId();
        }

        public final String getName() {
            String name = this._builder.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            return name;
        }

        public final String getSchema() {
            String schema = this._builder.getSchema();
            Intrinsics.checkNotNullExpressionValue(schema, "getSchema(...)");
            return schema;
        }

        public final int getSchemaType() {
            return this._builder.getSchemaType();
        }

        public final int getSource() {
            return this._builder.getSource();
        }

        public final void setBuzzwordId(long j7) {
            this._builder.setBuzzwordId(j7);
        }

        public final void setId(long j7) {
            this._builder.setId(j7);
        }

        public final void setName(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setName(value);
        }

        public final void setSchema(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSchema(value);
        }

        public final void setSchemaType(int i4) {
            this._builder.setSchemaType(i4);
        }

        public final void setSource(int i4) {
            this._builder.setSource(i4);
        }
    }

    private BuzzwordShowConfigKt() {
    }
}
