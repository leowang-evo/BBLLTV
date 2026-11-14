package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.DslMap;
import com.google.protobuf.kotlin.DslProxy;
import com.google.protobuf.kotlin.ProtoDslMarker;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmuPlayerConfigKt {
    public static final DanmuPlayerConfigKt INSTANCE = new DanmuPlayerConfigKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmuPlayerConfig.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmuPlayerConfig.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        public static final class PlayerDanmakuAiRecommendedLevelV2MapProxy extends DslProxy {
            private PlayerDanmakuAiRecommendedLevelV2MapProxy() {
            }
        }

        private Dsl(Dm.DanmuPlayerConfig.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmuPlayerConfig.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmuPlayerConfig _build() {
            Dm.DanmuPlayerConfig danmuPlayerConfigBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmuPlayerConfigBuild, "build(...)");
            return danmuPlayerConfigBuild;
        }

        public final void clearInlinePlayerDanmakuConfig() {
            this._builder.clearInlinePlayerDanmakuConfig();
        }

        public final void clearInlinePlayerDanmakuSwitch() {
            this._builder.clearInlinePlayerDanmakuSwitch();
        }

        public final void clearPlayerDanmakuAiRecommendedLevel() {
            this._builder.clearPlayerDanmakuAiRecommendedLevel();
        }

        public final void clearPlayerDanmakuAiRecommendedLevelV2() {
            this._builder.clearPlayerDanmakuAiRecommendedLevelV2();
        }

        public final /* synthetic */ void clearPlayerDanmakuAiRecommendedLevelV2Map(DslMap dslMap) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            this._builder.clearPlayerDanmakuAiRecommendedLevelV2Map();
        }

        public final void clearPlayerDanmakuAiRecommendedSwitch() {
            this._builder.clearPlayerDanmakuAiRecommendedSwitch();
        }

        public final void clearPlayerDanmakuBlockbottom() {
            this._builder.clearPlayerDanmakuBlockbottom();
        }

        public final void clearPlayerDanmakuBlockcolorful() {
            this._builder.clearPlayerDanmakuBlockcolorful();
        }

        public final void clearPlayerDanmakuBlockrepeat() {
            this._builder.clearPlayerDanmakuBlockrepeat();
        }

        public final void clearPlayerDanmakuBlockscroll() {
            this._builder.clearPlayerDanmakuBlockscroll();
        }

        public final void clearPlayerDanmakuBlockspecial() {
            this._builder.clearPlayerDanmakuBlockspecial();
        }

        public final void clearPlayerDanmakuBlocktop() {
            this._builder.clearPlayerDanmakuBlocktop();
        }

        public final void clearPlayerDanmakuDomain() {
            this._builder.clearPlayerDanmakuDomain();
        }

        public final void clearPlayerDanmakuEnableblocklist() {
            this._builder.clearPlayerDanmakuEnableblocklist();
        }

        public final void clearPlayerDanmakuIosSwitchSave() {
            this._builder.clearPlayerDanmakuIosSwitchSave();
        }

        public final void clearPlayerDanmakuOpacity() {
            this._builder.clearPlayerDanmakuOpacity();
        }

        public final void clearPlayerDanmakuScalingfactor() {
            this._builder.clearPlayerDanmakuScalingfactor();
        }

        public final void clearPlayerDanmakuSeniorModeSwitch() {
            this._builder.clearPlayerDanmakuSeniorModeSwitch();
        }

        public final void clearPlayerDanmakuSpeed() {
            this._builder.clearPlayerDanmakuSpeed();
        }

        public final void clearPlayerDanmakuSwitch() {
            this._builder.clearPlayerDanmakuSwitch();
        }

        public final void clearPlayerDanmakuSwitchSave() {
            this._builder.clearPlayerDanmakuSwitchSave();
        }

        public final void clearPlayerDanmakuUseDefaultConfig() {
            this._builder.clearPlayerDanmakuUseDefaultConfig();
        }

        public final int getInlinePlayerDanmakuConfig() {
            return this._builder.getInlinePlayerDanmakuConfig();
        }

        public final boolean getInlinePlayerDanmakuSwitch() {
            return this._builder.getInlinePlayerDanmakuSwitch();
        }

        public final int getPlayerDanmakuAiRecommendedLevel() {
            return this._builder.getPlayerDanmakuAiRecommendedLevel();
        }

        public final int getPlayerDanmakuAiRecommendedLevelV2() {
            return this._builder.getPlayerDanmakuAiRecommendedLevelV2();
        }

        public final /* synthetic */ DslMap getPlayerDanmakuAiRecommendedLevelV2MapMap() {
            Map<Integer, Integer> playerDanmakuAiRecommendedLevelV2MapMap = this._builder.getPlayerDanmakuAiRecommendedLevelV2MapMap();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuAiRecommendedLevelV2MapMap, "getPlayerDanmakuAiRecommendedLevelV2MapMap(...)");
            return new DslMap(playerDanmakuAiRecommendedLevelV2MapMap);
        }

        public final boolean getPlayerDanmakuAiRecommendedSwitch() {
            return this._builder.getPlayerDanmakuAiRecommendedSwitch();
        }

        public final boolean getPlayerDanmakuBlockbottom() {
            return this._builder.getPlayerDanmakuBlockbottom();
        }

        public final boolean getPlayerDanmakuBlockcolorful() {
            return this._builder.getPlayerDanmakuBlockcolorful();
        }

        public final boolean getPlayerDanmakuBlockrepeat() {
            return this._builder.getPlayerDanmakuBlockrepeat();
        }

        public final boolean getPlayerDanmakuBlockscroll() {
            return this._builder.getPlayerDanmakuBlockscroll();
        }

        public final boolean getPlayerDanmakuBlockspecial() {
            return this._builder.getPlayerDanmakuBlockspecial();
        }

        public final boolean getPlayerDanmakuBlocktop() {
            return this._builder.getPlayerDanmakuBlocktop();
        }

        public final float getPlayerDanmakuDomain() {
            return this._builder.getPlayerDanmakuDomain();
        }

        public final boolean getPlayerDanmakuEnableblocklist() {
            return this._builder.getPlayerDanmakuEnableblocklist();
        }

        public final int getPlayerDanmakuIosSwitchSave() {
            return this._builder.getPlayerDanmakuIosSwitchSave();
        }

        public final float getPlayerDanmakuOpacity() {
            return this._builder.getPlayerDanmakuOpacity();
        }

        public final float getPlayerDanmakuScalingfactor() {
            return this._builder.getPlayerDanmakuScalingfactor();
        }

        public final int getPlayerDanmakuSeniorModeSwitch() {
            return this._builder.getPlayerDanmakuSeniorModeSwitch();
        }

        public final int getPlayerDanmakuSpeed() {
            return this._builder.getPlayerDanmakuSpeed();
        }

        public final boolean getPlayerDanmakuSwitch() {
            return this._builder.getPlayerDanmakuSwitch();
        }

        public final boolean getPlayerDanmakuSwitchSave() {
            return this._builder.getPlayerDanmakuSwitchSave();
        }

        public final boolean getPlayerDanmakuUseDefaultConfig() {
            return this._builder.getPlayerDanmakuUseDefaultConfig();
        }

        public final /* synthetic */ void putAllPlayerDanmakuAiRecommendedLevelV2Map(DslMap dslMap, Map map) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            Intrinsics.checkNotNullParameter(map, "map");
            this._builder.putAllPlayerDanmakuAiRecommendedLevelV2Map(map);
        }

        public final void putPlayerDanmakuAiRecommendedLevelV2Map(DslMap<Integer, Integer, PlayerDanmakuAiRecommendedLevelV2MapProxy> dslMap, int i4, int i7) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            this._builder.putPlayerDanmakuAiRecommendedLevelV2Map(i4, i7);
        }

        public final /* synthetic */ void removePlayerDanmakuAiRecommendedLevelV2Map(DslMap dslMap, int i4) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            this._builder.removePlayerDanmakuAiRecommendedLevelV2Map(i4);
        }

        public final void setInlinePlayerDanmakuConfig(int i4) {
            this._builder.setInlinePlayerDanmakuConfig(i4);
        }

        public final void setInlinePlayerDanmakuSwitch(boolean z7) {
            this._builder.setInlinePlayerDanmakuSwitch(z7);
        }

        public final void setPlayerDanmakuAiRecommendedLevel(int i4) {
            this._builder.setPlayerDanmakuAiRecommendedLevel(i4);
        }

        public final void setPlayerDanmakuAiRecommendedLevelV2(int i4) {
            this._builder.setPlayerDanmakuAiRecommendedLevelV2(i4);
        }

        public final /* synthetic */ void setPlayerDanmakuAiRecommendedLevelV2Map(DslMap<Integer, Integer, PlayerDanmakuAiRecommendedLevelV2MapProxy> dslMap, int i4, int i7) {
            Intrinsics.checkNotNullParameter(dslMap, "<this>");
            putPlayerDanmakuAiRecommendedLevelV2Map(dslMap, i4, i7);
        }

        public final void setPlayerDanmakuAiRecommendedSwitch(boolean z7) {
            this._builder.setPlayerDanmakuAiRecommendedSwitch(z7);
        }

        public final void setPlayerDanmakuBlockbottom(boolean z7) {
            this._builder.setPlayerDanmakuBlockbottom(z7);
        }

        public final void setPlayerDanmakuBlockcolorful(boolean z7) {
            this._builder.setPlayerDanmakuBlockcolorful(z7);
        }

        public final void setPlayerDanmakuBlockrepeat(boolean z7) {
            this._builder.setPlayerDanmakuBlockrepeat(z7);
        }

        public final void setPlayerDanmakuBlockscroll(boolean z7) {
            this._builder.setPlayerDanmakuBlockscroll(z7);
        }

        public final void setPlayerDanmakuBlockspecial(boolean z7) {
            this._builder.setPlayerDanmakuBlockspecial(z7);
        }

        public final void setPlayerDanmakuBlocktop(boolean z7) {
            this._builder.setPlayerDanmakuBlocktop(z7);
        }

        public final void setPlayerDanmakuDomain(float f7) {
            this._builder.setPlayerDanmakuDomain(f7);
        }

        public final void setPlayerDanmakuEnableblocklist(boolean z7) {
            this._builder.setPlayerDanmakuEnableblocklist(z7);
        }

        public final void setPlayerDanmakuIosSwitchSave(int i4) {
            this._builder.setPlayerDanmakuIosSwitchSave(i4);
        }

        public final void setPlayerDanmakuOpacity(float f7) {
            this._builder.setPlayerDanmakuOpacity(f7);
        }

        public final void setPlayerDanmakuScalingfactor(float f7) {
            this._builder.setPlayerDanmakuScalingfactor(f7);
        }

        public final void setPlayerDanmakuSeniorModeSwitch(int i4) {
            this._builder.setPlayerDanmakuSeniorModeSwitch(i4);
        }

        public final void setPlayerDanmakuSpeed(int i4) {
            this._builder.setPlayerDanmakuSpeed(i4);
        }

        public final void setPlayerDanmakuSwitch(boolean z7) {
            this._builder.setPlayerDanmakuSwitch(z7);
        }

        public final void setPlayerDanmakuSwitchSave(boolean z7) {
            this._builder.setPlayerDanmakuSwitchSave(z7);
        }

        public final void setPlayerDanmakuUseDefaultConfig(boolean z7) {
            this._builder.setPlayerDanmakuUseDefaultConfig(z7);
        }
    }

    private DanmuPlayerConfigKt() {
    }
}
