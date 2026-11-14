package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class BubbleKt {
    public static final BubbleKt INSTANCE = new BubbleKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.Bubble.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.Bubble.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.Bubble.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.Bubble.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.Bubble _build() {
            Dm.Bubble bubbleBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(bubbleBuild, "build(...)");
            return bubbleBuild;
        }

        public final void clearText() {
            this._builder.clearText();
        }

        public final void clearUrl() {
            this._builder.clearUrl();
        }

        public final String getText() {
            String text = this._builder.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            return text;
        }

        public final String getUrl() {
            String url = this._builder.getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
            return url;
        }

        public final void setText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setText(value);
        }

        public final void setUrl(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setUrl(value);
        }
    }

    private BubbleKt() {
    }
}
