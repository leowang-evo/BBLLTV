package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CommandDmKt {
    public static final CommandDmKt INSTANCE = new CommandDmKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.CommandDm.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.CommandDm.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.CommandDm.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.CommandDm.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.CommandDm _build() {
            Dm.CommandDm commandDmBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(commandDmBuild, "build(...)");
            return commandDmBuild;
        }

        public final void clearCommand() {
            this._builder.clearCommand();
        }

        public final void clearContent() {
            this._builder.clearContent();
        }

        public final void clearCtime() {
            this._builder.clearCtime();
        }

        public final void clearExtra() {
            this._builder.clearExtra();
        }

        public final void clearId() {
            this._builder.clearId();
        }

        public final void clearIdStr() {
            this._builder.clearIdStr();
        }

        public final void clearMid() {
            this._builder.clearMid();
        }

        public final void clearMtime() {
            this._builder.clearMtime();
        }

        public final void clearOid() {
            this._builder.clearOid();
        }

        public final void clearProgress() {
            this._builder.clearProgress();
        }

        public final String getCommand() {
            String command = this._builder.getCommand();
            Intrinsics.checkNotNullExpressionValue(command, "getCommand(...)");
            return command;
        }

        public final String getContent() {
            String content = this._builder.getContent();
            Intrinsics.checkNotNullExpressionValue(content, "getContent(...)");
            return content;
        }

        public final String getCtime() {
            String ctime = this._builder.getCtime();
            Intrinsics.checkNotNullExpressionValue(ctime, "getCtime(...)");
            return ctime;
        }

        public final String getExtra() {
            String extra = this._builder.getExtra();
            Intrinsics.checkNotNullExpressionValue(extra, "getExtra(...)");
            return extra;
        }

        public final long getId() {
            return this._builder.getId();
        }

        public final String getIdStr() {
            String idStr = this._builder.getIdStr();
            Intrinsics.checkNotNullExpressionValue(idStr, "getIdStr(...)");
            return idStr;
        }

        public final String getMid() {
            String mid = this._builder.getMid();
            Intrinsics.checkNotNullExpressionValue(mid, "getMid(...)");
            return mid;
        }

        public final String getMtime() {
            String mtime = this._builder.getMtime();
            Intrinsics.checkNotNullExpressionValue(mtime, "getMtime(...)");
            return mtime;
        }

        public final long getOid() {
            return this._builder.getOid();
        }

        public final int getProgress() {
            return this._builder.getProgress();
        }

        public final void setCommand(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCommand(value);
        }

        public final void setContent(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setContent(value);
        }

        public final void setCtime(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setCtime(value);
        }

        public final void setExtra(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setExtra(value);
        }

        public final void setId(long j7) {
            this._builder.setId(j7);
        }

        public final void setIdStr(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setIdStr(value);
        }

        public final void setMid(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setMid(value);
        }

        public final void setMtime(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setMtime(value);
        }

        public final void setOid(long j7) {
            this._builder.setOid(j7);
        }

        public final void setProgress(int i4) {
            this._builder.setProgress(i4);
        }
    }

    private CommandDmKt() {
    }
}
