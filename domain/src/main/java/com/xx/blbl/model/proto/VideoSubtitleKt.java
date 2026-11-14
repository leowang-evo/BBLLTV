package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoSubtitleKt {
    public static final VideoSubtitleKt INSTANCE = new VideoSubtitleKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.VideoSubtitle.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.VideoSubtitle.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class SubtitlesProxy extends DslProxy {
            private SubtitlesProxy() {
            }
        }

        private Dsl(Dm.VideoSubtitle.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.VideoSubtitle.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.VideoSubtitle _build() {
            Dm.VideoSubtitle videoSubtitleBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(videoSubtitleBuild, "build(...)");
            return videoSubtitleBuild;
        }

        public final /* synthetic */ void addAllSubtitles(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllSubtitles(values);
        }

        public final /* synthetic */ void addSubtitles(DslList dslList, Dm.SubtitleItem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addSubtitles(value);
        }

        public final void clearLan() {
            this._builder.clearLan();
        }

        public final void clearLanDoc() {
            this._builder.clearLanDoc();
        }

        public final /* synthetic */ void clearSubtitles(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearSubtitles();
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

        public final /* synthetic */ DslList getSubtitles() {
            List<Dm.SubtitleItem> subtitlesList = this._builder.getSubtitlesList();
            Intrinsics.checkNotNullExpressionValue(subtitlesList, "getSubtitlesList(...)");
            return new DslList(subtitlesList);
        }

        public final /* synthetic */ void plusAssignAllSubtitles(DslList<Dm.SubtitleItem, SubtitlesProxy> dslList, Iterable<Dm.SubtitleItem> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllSubtitles(dslList, values);
        }

        public final /* synthetic */ void plusAssignSubtitles(DslList<Dm.SubtitleItem, SubtitlesProxy> dslList, Dm.SubtitleItem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addSubtitles(dslList, value);
        }

        public final void setLan(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLan(value);
        }

        public final void setLanDoc(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setLanDoc(value);
        }

        public final /* synthetic */ void setSubtitles(DslList dslList, int i4, Dm.SubtitleItem value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSubtitles(i4, value);
        }
    }

    private VideoSubtitleKt() {
    }
}
