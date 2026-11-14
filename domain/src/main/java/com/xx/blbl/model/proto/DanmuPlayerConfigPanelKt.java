package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerConfigPanelKt {
    public static final DanmuPlayerConfigPanelKt INSTANCE = new DanmuPlayerConfigPanelKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmuPlayerConfigPanel.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmuPlayerConfigPanel.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DanmuPlayerConfigPanel.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmuPlayerConfigPanel.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmuPlayerConfigPanel _build() {
            Dm.DanmuPlayerConfigPanel danmuPlayerConfigPanelBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmuPlayerConfigPanelBuild, "build(...)");
            return danmuPlayerConfigPanelBuild;
        }

        public final void clearSelectionText() {
            this._builder.clearSelectionText();
        }

        public final String getSelectionText() {
            String selectionText = this._builder.getSelectionText();
            Intrinsics.checkNotNullExpressionValue(selectionText, "getSelectionText(...)");
            return selectionText;
        }

        public final void setSelectionText(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSelectionText(value);
        }
    }

    private DanmuPlayerConfigPanelKt() {
    }
}
