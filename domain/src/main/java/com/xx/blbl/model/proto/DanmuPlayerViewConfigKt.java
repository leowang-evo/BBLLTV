package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslList;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerViewConfigKt {
    public static final DanmuPlayerViewConfigKt INSTANCE = new DanmuPlayerViewConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmuPlayerViewConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmuPlayerViewConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class DanmukuPlayerDynamicConfigProxy extends DslProxy {
            private DanmukuPlayerDynamicConfigProxy() {
            }
        }

        private Dsl(Dm.DanmuPlayerViewConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmuPlayerViewConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmuPlayerViewConfig _build() {
            Dm.DanmuPlayerViewConfig danmuPlayerViewConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmuPlayerViewConfigBuild, "build(...)");
            return danmuPlayerViewConfigBuild;
        }

        public final /* synthetic */ void addAllDanmukuPlayerDynamicConfig(DslList dslList, Iterable values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            this._builder.addAllDanmukuPlayerDynamicConfig(values);
        }

        public final /* synthetic */ void addDanmukuPlayerDynamicConfig(DslList dslList, Dm.DanmuPlayerDynamicConfig value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.addDanmukuPlayerDynamicConfig(value);
        }

        public final void clearDanmukuDefaultPlayerConfig() {
            this._builder.clearDanmukuDefaultPlayerConfig();
        }

        public final void clearDanmukuPlayerConfig() {
            this._builder.clearDanmukuPlayerConfig();
        }

        public final void clearDanmukuPlayerConfigPanel() {
            this._builder.clearDanmukuPlayerConfigPanel();
        }

        public final /* synthetic */ void clearDanmukuPlayerDynamicConfig(DslList dslList) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            this._builder.clearDanmukuPlayerDynamicConfig();
        }

        public final Dm.DanmuDefaultPlayerConfig getDanmukuDefaultPlayerConfig() {
            Dm.DanmuDefaultPlayerConfig danmukuDefaultPlayerConfig = this._builder.getDanmukuDefaultPlayerConfig();
            Intrinsics.checkNotNullExpressionValue(danmukuDefaultPlayerConfig, "getDanmukuDefaultPlayerConfig(...)");
            return danmukuDefaultPlayerConfig;
        }

        public final Dm.DanmuPlayerConfig getDanmukuPlayerConfig() {
            Dm.DanmuPlayerConfig danmukuPlayerConfig = this._builder.getDanmukuPlayerConfig();
            Intrinsics.checkNotNullExpressionValue(danmukuPlayerConfig, "getDanmukuPlayerConfig(...)");
            return danmukuPlayerConfig;
        }

        public final Dm.DanmuPlayerConfigPanel getDanmukuPlayerConfigPanel() {
            Dm.DanmuPlayerConfigPanel danmukuPlayerConfigPanel = this._builder.getDanmukuPlayerConfigPanel();
            Intrinsics.checkNotNullExpressionValue(danmukuPlayerConfigPanel, "getDanmukuPlayerConfigPanel(...)");
            return danmukuPlayerConfigPanel;
        }

        public final /* synthetic */ DslList getDanmukuPlayerDynamicConfig() {
            List<Dm.DanmuPlayerDynamicConfig> danmukuPlayerDynamicConfigList = this._builder.getDanmukuPlayerDynamicConfigList();
            Intrinsics.checkNotNullExpressionValue(danmukuPlayerDynamicConfigList, "getDanmukuPlayerDynamicConfigList(...)");
            return new DslList(danmukuPlayerDynamicConfigList);
        }

        public final boolean hasDanmukuDefaultPlayerConfig() {
            return this._builder.hasDanmukuDefaultPlayerConfig();
        }

        public final boolean hasDanmukuPlayerConfig() {
            return this._builder.hasDanmukuPlayerConfig();
        }

        public final boolean hasDanmukuPlayerConfigPanel() {
            return this._builder.hasDanmukuPlayerConfigPanel();
        }

        public final /* synthetic */ void plusAssignAllDanmukuPlayerDynamicConfig(DslList<Dm.DanmuPlayerDynamicConfig, DanmukuPlayerDynamicConfigProxy> dslList, Iterable<Dm.DanmuPlayerDynamicConfig> values) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(values, "values");
            addAllDanmukuPlayerDynamicConfig(dslList, values);
        }

        public final /* synthetic */ void plusAssignDanmukuPlayerDynamicConfig(DslList<Dm.DanmuPlayerDynamicConfig, DanmukuPlayerDynamicConfigProxy> dslList, Dm.DanmuPlayerDynamicConfig value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            addDanmukuPlayerDynamicConfig(dslList, value);
        }

        public final void setDanmukuDefaultPlayerConfig(Dm.DanmuDefaultPlayerConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDanmukuDefaultPlayerConfig(value);
        }

        public final void setDanmukuPlayerConfig(Dm.DanmuPlayerConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDanmukuPlayerConfig(value);
        }

        public final void setDanmukuPlayerConfigPanel(Dm.DanmuPlayerConfigPanel value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDanmukuPlayerConfigPanel(value);
        }

        public final /* synthetic */ void setDanmukuPlayerDynamicConfig(DslList dslList, int i4, Dm.DanmuPlayerDynamicConfig value) {
            Intrinsics.checkNotNullParameter(dslList, "<this>");
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDanmukuPlayerDynamicConfig(i4, value);
        }
    }

    private DanmuPlayerViewConfigKt() {
    }
}
