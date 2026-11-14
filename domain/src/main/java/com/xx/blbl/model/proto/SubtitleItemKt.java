package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleItemKt {
    public static final SubtitleItemKt INSTANCE = new SubtitleItemKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.SubtitleItem.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.SubtitleItem.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.SubtitleItem.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.SubtitleItem.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.SubtitleItem _build() {
            Dm.SubtitleItem subtitleItemBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(subtitleItemBuild, "build(...)");
            return subtitleItemBuild;
        }

        public final void clearAiStatus() {
            this._builder.clearAiStatus();
        }

        public final void clearAiType() {
            this._builder.clearAiType();
        }

        public final void clearAuthor() {
            this._builder.clearAuthor();
        }

        public final void clearId() {
            this._builder.clearId();
        }

        public final void clearIdStr() {
            this._builder.clearIdStr();
        }

        public final void clearLan() {
            this._builder.clearLan();
        }

        public final void clearLanDoc() {
            this._builder.clearLanDoc();
        }

        public final void clearLanDocBrief() {
            this._builder.clearLanDocBrief();
        }

        public final void clearSubtitleUrl() {
            this._builder.clearSubtitleUrl();
        }

        public final void clearType() {
            this._builder.clearType();
        }

        public final Dm.SubtitleAiStatus getAiStatus() {
            Dm.SubtitleAiStatus aiStatus = this._builder.getAiStatus();
            Intrinsics.checkNotNullExpressionValue(aiStatus, "getAiStatus(...)");
            return aiStatus;
        }

        public final Dm.SubtitleAiType getAiType() {
            Dm.SubtitleAiType aiType = this._builder.getAiType();
            Intrinsics.checkNotNullExpressionValue(aiType, "getAiType(...)");
            return aiType;
        }

        public final Dm.UserInfo getAuthor() {
            Dm.UserInfo author = this._builder.getAuthor();
            Intrinsics.checkNotNullExpressionValue(author, "getAuthor(...)");
            return author;
        }

        public final long getId() {
            return this._builder.getId();
        }

        public final String getIdStr() {
            String idStr = this._builder.getIdStr();
            Intrinsics.checkNotNullExpressionValue(idStr, "getIdStr(...)");
            return idStr;
        }

        public final String getLan() {
            String lan = this._builder.getLan();
            Intrinsics.checkNotNullExpressionValue(lan, "getLan(...)");
            return lan;
        }

        public final String getLanDoc() {
            String lanDoc = this._builder.getLanDoc();
            Intrinsics.checkNotNullExpressionValue(lanDoc, "getLanDoc(...)");
            return lanDoc;
        }

        public final String getLanDocBrief() {
            String lanDocBrief = this._builder.getLanDocBrief();
            Intrinsics.checkNotNullExpressionValue(lanDocBrief, "getLanDocBrief(...)");
            return lanDocBrief;
        }

        public final String getSubtitleUrl() {
            String subtitleUrl = this._builder.getSubtitleUrl();
            Intrinsics.checkNotNullExpressionValue(subtitleUrl, "getSubtitleUrl(...)");
            return subtitleUrl;
        }

        public final Dm.SubtitleType getType() {
            Dm.SubtitleType type = this._builder.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            return type;
        }

        public final boolean hasAuthor() {
            return this._builder.hasAuthor();
        }

        public final void setAiStatus(Dm.SubtitleAiStatus value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiStatus(value);
        }

        public final void setAiType(Dm.SubtitleAiType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiType(value);
        }

        public final void setAuthor(Dm.UserInfo value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAuthor(value);
        }

        public final void setId(long j7) {
            this._builder.setId(j7);
        }

        public final void setIdStr(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setIdStr(value);
        }

        public final void setLan(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLan(value);
        }

        public final void setLanDoc(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLanDoc(value);
        }

        public final void setLanDocBrief(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLanDocBrief(value);
        }

        public final void setSubtitleUrl(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSubtitleUrl(value);
        }

        public final void setType(Dm.SubtitleType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setType(value);
        }
    }

    private SubtitleItemKt() {
    }
}
