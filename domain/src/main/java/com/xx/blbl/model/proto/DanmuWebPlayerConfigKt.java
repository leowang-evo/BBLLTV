package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslMap;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuWebPlayerConfigKt {
    public static final DanmuWebPlayerConfigKt INSTANCE = new DanmuWebPlayerConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmuWebPlayerConfig.Builder _builder;

        public static final class AiLevelV2MapProxy extends DslProxy {
            private AiLevelV2MapProxy() {
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmuWebPlayerConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DanmuWebPlayerConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmuWebPlayerConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmuWebPlayerConfig _build() {
            Dm.DanmuWebPlayerConfig danmuWebPlayerConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmuWebPlayerConfigBuild, "build(...)");
            return danmuWebPlayerConfigBuild;
        }

        public final void clearAiLevel() {
            this._builder.clearAiLevel();
        }

        public final void clearAiLevelV2() {
            this._builder.clearAiLevelV2();
        }

        public final /* synthetic */ void clearAiLevelV2Map(DslMap dslMap) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            this._builder.clearAiLevelV2Map();
        }

        public final void clearAiSwitch() {
            this._builder.clearAiSwitch();
        }

        public final void clearBlockbottom() {
            this._builder.clearBlockbottom();
        }

        public final void clearBlockcolor() {
            this._builder.clearBlockcolor();
        }

        public final void clearBlockscroll() {
            this._builder.clearBlockscroll();
        }

        public final void clearBlockspecial() {
            this._builder.clearBlockspecial();
        }

        public final void clearBlocktop() {
            this._builder.clearBlocktop();
        }

        public final void clearBold() {
            this._builder.clearBold();
        }

        public final void clearDmSwitch() {
            this._builder.clearDmSwitch();
        }

        public final void clearDmarea() {
            this._builder.clearDmarea();
        }

        public final void clearDmask() {
            this._builder.clearDmask();
        }

        public final void clearDrawType() {
            this._builder.clearDrawType();
        }

        public final void clearFontborder() {
            this._builder.clearFontborder();
        }

        public final void clearFontfamily() {
            this._builder.clearFontfamily();
        }

        public final void clearFontsize() {
            this._builder.clearFontsize();
        }

        public final void clearOpacity() {
            this._builder.clearOpacity();
        }

        public final void clearPreventshade() {
            this._builder.clearPreventshade();
        }

        public final void clearScreensync() {
            this._builder.clearScreensync();
        }

        public final void clearSeniorModeSwitch() {
            this._builder.clearSeniorModeSwitch();
        }

        public final void clearSpeedplus() {
            this._builder.clearSpeedplus();
        }

        public final void clearSpeedsync() {
            this._builder.clearSpeedsync();
        }

        public final int getAiLevel() {
            return this._builder.getAiLevel();
        }

        public final int getAiLevelV2() {
            return this._builder.getAiLevelV2();
        }

        public final /* synthetic */ DslMap getAiLevelV2MapMap() {
            Map<Integer, Integer> aiLevelV2MapMap = this._builder.getAiLevelV2MapMap();
            Intrinsics.checkNotNullExpressionValue(aiLevelV2MapMap, "getAiLevelV2MapMap(...)");
            return new DslMap(aiLevelV2MapMap);
        }

        public final boolean getAiSwitch() {
            return this._builder.getAiSwitch();
        }

        public final boolean getBlockbottom() {
            return this._builder.getBlockbottom();
        }

        public final boolean getBlockcolor() {
            return this._builder.getBlockcolor();
        }

        public final boolean getBlockscroll() {
            return this._builder.getBlockscroll();
        }

        public final boolean getBlockspecial() {
            return this._builder.getBlockspecial();
        }

        public final boolean getBlocktop() {
            return this._builder.getBlocktop();
        }

        public final boolean getBold() {
            return this._builder.getBold();
        }

        public final boolean getDmSwitch() {
            return this._builder.getDmSwitch();
        }

        public final int getDmarea() {
            return this._builder.getDmarea();
        }

        public final boolean getDmask() {
            return this._builder.getDmask();
        }

        public final String getDrawType() {
            String drawType = this._builder.getDrawType();
            Intrinsics.checkNotNullExpressionValue(drawType, "getDrawType(...)");
            return drawType;
        }

        public final int getFontborder() {
            return this._builder.getFontborder();
        }

        public final String getFontfamily() {
            String fontfamily = this._builder.getFontfamily();
            Intrinsics.checkNotNullExpressionValue(fontfamily, "getFontfamily(...)");
            return fontfamily;
        }

        public final float getFontsize() {
            return this._builder.getFontsize();
        }

        public final float getOpacity() {
            return this._builder.getOpacity();
        }

        public final boolean getPreventshade() {
            return this._builder.getPreventshade();
        }

        public final boolean getScreensync() {
            return this._builder.getScreensync();
        }

        public final int getSeniorModeSwitch() {
            return this._builder.getSeniorModeSwitch();
        }

        public final float getSpeedplus() {
            return this._builder.getSpeedplus();
        }

        public final boolean getSpeedsync() {
            return this._builder.getSpeedsync();
        }

        public final void putAiLevelV2Map(DslMap<Integer, Integer, AiLevelV2MapProxy> dslMap, int i4, int i7) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            this._builder.putAiLevelV2Map(i4, i7);
        }

        public final /* synthetic */ void putAllAiLevelV2Map(DslMap dslMap, Map map) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            Intrinsics.checkNotNullParameter(map, "map");
            this._builder.putAllAiLevelV2Map(map);
        }

        public final /* synthetic */ void removeAiLevelV2Map(DslMap dslMap, int i4) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            this._builder.removeAiLevelV2Map(i4);
        }

        public final void setAiLevel(int i4) {
            this._builder.setAiLevel(i4);
        }

        public final void setAiLevelV2(int i4) {
            this._builder.setAiLevelV2(i4);
        }

        public final /* synthetic */ void setAiLevelV2Map(DslMap<Integer, Integer, AiLevelV2MapProxy> dslMap, int i4, int i7) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            putAiLevelV2Map(dslMap, i4, i7);
        }

        public final void setAiSwitch(boolean z7) {
            this._builder.setAiSwitch(z7);
        }

        public final void setBlockbottom(boolean z7) {
            this._builder.setBlockbottom(z7);
        }

        public final void setBlockcolor(boolean z7) {
            this._builder.setBlockcolor(z7);
        }

        public final void setBlockscroll(boolean z7) {
            this._builder.setBlockscroll(z7);
        }

        public final void setBlockspecial(boolean z7) {
            this._builder.setBlockspecial(z7);
        }

        public final void setBlocktop(boolean z7) {
            this._builder.setBlocktop(z7);
        }

        public final void setBold(boolean z7) {
            this._builder.setBold(z7);
        }

        public final void setDmSwitch(boolean z7) {
            this._builder.setDmSwitch(z7);
        }

        public final void setDmarea(int i4) {
            this._builder.setDmarea(i4);
        }

        public final void setDmask(boolean z7) {
            this._builder.setDmask(z7);
        }

        public final void setDrawType(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDrawType(value);
        }

        public final void setFontborder(int i4) {
            this._builder.setFontborder(i4);
        }

        public final void setFontfamily(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setFontfamily(value);
        }

        public final void setFontsize(float f7) {
            this._builder.setFontsize(f7);
        }

        public final void setOpacity(float f7) {
            this._builder.setOpacity(f7);
        }

        public final void setPreventshade(boolean z7) {
            this._builder.setPreventshade(z7);
        }

        public final void setScreensync(boolean z7) {
            this._builder.setScreensync(z7);
        }

        public final void setSeniorModeSwitch(int i4) {
            this._builder.setSeniorModeSwitch(i4);
        }

        public final void setSpeedplus(float f7) {
            this._builder.setSpeedplus(f7);
        }

        public final void setSpeedsync(boolean z7) {
            this._builder.setSpeedsync(z7);
        }
    }

    private DanmuWebPlayerConfigKt() {
    }
}
