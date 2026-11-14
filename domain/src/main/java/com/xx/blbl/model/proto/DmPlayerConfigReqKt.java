package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmPlayerConfigReqKt {
    public static final DmPlayerConfigReqKt INSTANCE = new DmPlayerConfigReqKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DmPlayerConfigReq.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DmPlayerConfigReq.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DmPlayerConfigReq.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DmPlayerConfigReq.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DmPlayerConfigReq _build() {
            Dm.DmPlayerConfigReq dmPlayerConfigReqBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(dmPlayerConfigReqBuild, "build(...)");
            return dmPlayerConfigReqBuild;
        }

        public final void clearAiRecommendedLevel() {
            this._builder.clearAiRecommendedLevel();
        }

        public final void clearAiRecommendedLevelV2() {
            this._builder.clearAiRecommendedLevelV2();
        }

        public final void clearAiRecommendedSwitch() {
            this._builder.clearAiRecommendedSwitch();
        }

        public final void clearBlockbottom() {
            this._builder.clearBlockbottom();
        }

        public final void clearBlockcolorful() {
            this._builder.clearBlockcolorful();
        }

        public final void clearBlockrepeat() {
            this._builder.clearBlockrepeat();
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

        public final void clearDomain() {
            this._builder.clearDomain();
        }

        public final void clearEnableblocklist() {
            this._builder.clearEnableblocklist();
        }

        public final void clearInlinePlayerDanmakuSwitch() {
            this._builder.clearInlinePlayerDanmakuSwitch();
        }

        public final void clearOpacity() {
            this._builder.clearOpacity();
        }

        public final void clearScalingfactor() {
            this._builder.clearScalingfactor();
        }

        public final void clearSeniorModeSwitch() {
            this._builder.clearSeniorModeSwitch();
        }

        public final void clearSpeed() {
            this._builder.clearSpeed();
        }

        public final void clearSwitch() {
            this._builder.clearSwitch();
        }

        public final void clearSwitchSave() {
            this._builder.clearSwitchSave();
        }

        public final void clearTs() {
            this._builder.clearTs();
        }

        public final void clearUseDefaultConfig() {
            this._builder.clearUseDefaultConfig();
        }

        public final Dm.PlayerDanmakuAiRecommendedLevel getAiRecommendedLevel() {
            Dm.PlayerDanmakuAiRecommendedLevel aiRecommendedLevel = this._builder.getAiRecommendedLevel();
            Intrinsics.checkNotNullExpressionValue(aiRecommendedLevel, "getAiRecommendedLevel(...)");
            return aiRecommendedLevel;
        }

        public final Dm.PlayerDanmakuAiRecommendedLevelV2 getAiRecommendedLevelV2() {
            Dm.PlayerDanmakuAiRecommendedLevelV2 aiRecommendedLevelV2 = this._builder.getAiRecommendedLevelV2();
            Intrinsics.checkNotNullExpressionValue(aiRecommendedLevelV2, "getAiRecommendedLevelV2(...)");
            return aiRecommendedLevelV2;
        }

        public final Dm.PlayerDanmakuAiRecommendedSwitch getAiRecommendedSwitch() {
            Dm.PlayerDanmakuAiRecommendedSwitch aiRecommendedSwitch = this._builder.getAiRecommendedSwitch();
            Intrinsics.checkNotNullExpressionValue(aiRecommendedSwitch, "getAiRecommendedSwitch(...)");
            return aiRecommendedSwitch;
        }

        public final Dm.PlayerDanmakuBlockbottom getBlockbottom() {
            Dm.PlayerDanmakuBlockbottom blockbottom = this._builder.getBlockbottom();
            Intrinsics.checkNotNullExpressionValue(blockbottom, "getBlockbottom(...)");
            return blockbottom;
        }

        public final Dm.PlayerDanmakuBlockcolorful getBlockcolorful() {
            Dm.PlayerDanmakuBlockcolorful blockcolorful = this._builder.getBlockcolorful();
            Intrinsics.checkNotNullExpressionValue(blockcolorful, "getBlockcolorful(...)");
            return blockcolorful;
        }

        public final Dm.PlayerDanmakuBlockrepeat getBlockrepeat() {
            Dm.PlayerDanmakuBlockrepeat blockrepeat = this._builder.getBlockrepeat();
            Intrinsics.checkNotNullExpressionValue(blockrepeat, "getBlockrepeat(...)");
            return blockrepeat;
        }

        public final Dm.PlayerDanmakuBlockscroll getBlockscroll() {
            Dm.PlayerDanmakuBlockscroll blockscroll = this._builder.getBlockscroll();
            Intrinsics.checkNotNullExpressionValue(blockscroll, "getBlockscroll(...)");
            return blockscroll;
        }

        public final Dm.PlayerDanmakuBlockspecial getBlockspecial() {
            Dm.PlayerDanmakuBlockspecial blockspecial = this._builder.getBlockspecial();
            Intrinsics.checkNotNullExpressionValue(blockspecial, "getBlockspecial(...)");
            return blockspecial;
        }

        public final Dm.PlayerDanmakuBlocktop getBlocktop() {
            Dm.PlayerDanmakuBlocktop blocktop = this._builder.getBlocktop();
            Intrinsics.checkNotNullExpressionValue(blocktop, "getBlocktop(...)");
            return blocktop;
        }

        public final Dm.PlayerDanmakuDomain getDomain() {
            Dm.PlayerDanmakuDomain domain = this._builder.getDomain();
            Intrinsics.checkNotNullExpressionValue(domain, "getDomain(...)");
            return domain;
        }

        public final Dm.PlayerDanmakuEnableblocklist getEnableblocklist() {
            Dm.PlayerDanmakuEnableblocklist enableblocklist = this._builder.getEnableblocklist();
            Intrinsics.checkNotNullExpressionValue(enableblocklist, "getEnableblocklist(...)");
            return enableblocklist;
        }

        public final Dm.InlinePlayerDanmakuSwitch getInlinePlayerDanmakuSwitch() {
            Dm.InlinePlayerDanmakuSwitch inlinePlayerDanmakuSwitch = this._builder.getInlinePlayerDanmakuSwitch();
            Intrinsics.checkNotNullExpressionValue(inlinePlayerDanmakuSwitch, "getInlinePlayerDanmakuSwitch(...)");
            return inlinePlayerDanmakuSwitch;
        }

        public final Dm.PlayerDanmakuOpacity getOpacity() {
            Dm.PlayerDanmakuOpacity opacity = this._builder.getOpacity();
            Intrinsics.checkNotNullExpressionValue(opacity, "getOpacity(...)");
            return opacity;
        }

        public final Dm.PlayerDanmakuScalingfactor getScalingfactor() {
            Dm.PlayerDanmakuScalingfactor scalingfactor = this._builder.getScalingfactor();
            Intrinsics.checkNotNullExpressionValue(scalingfactor, "getScalingfactor(...)");
            return scalingfactor;
        }

        public final Dm.PlayerDanmakuSeniorModeSwitch getSeniorModeSwitch() {
            Dm.PlayerDanmakuSeniorModeSwitch seniorModeSwitch = this._builder.getSeniorModeSwitch();
            Intrinsics.checkNotNullExpressionValue(seniorModeSwitch, "getSeniorModeSwitch(...)");
            return seniorModeSwitch;
        }

        public final Dm.PlayerDanmakuSpeed getSpeed() {
            Dm.PlayerDanmakuSpeed speed = this._builder.getSpeed();
            Intrinsics.checkNotNullExpressionValue(speed, "getSpeed(...)");
            return speed;
        }

        public final Dm.PlayerDanmakuSwitch getSwitch() {
            Dm.PlayerDanmakuSwitch playerDanmakuSwitch = this._builder.getSwitch();
            Intrinsics.checkNotNullExpressionValue(playerDanmakuSwitch, "getSwitch(...)");
            return playerDanmakuSwitch;
        }

        public final Dm.PlayerDanmakuSwitchSave getSwitchSave() {
            Dm.PlayerDanmakuSwitchSave switchSave = this._builder.getSwitchSave();
            Intrinsics.checkNotNullExpressionValue(switchSave, "getSwitchSave(...)");
            return switchSave;
        }

        public final long getTs() {
            return this._builder.getTs();
        }

        public final Dm.PlayerDanmakuUseDefaultConfig getUseDefaultConfig() {
            Dm.PlayerDanmakuUseDefaultConfig useDefaultConfig = this._builder.getUseDefaultConfig();
            Intrinsics.checkNotNullExpressionValue(useDefaultConfig, "getUseDefaultConfig(...)");
            return useDefaultConfig;
        }

        public final boolean hasAiRecommendedLevel() {
            return this._builder.hasAiRecommendedLevel();
        }

        public final boolean hasAiRecommendedLevelV2() {
            return this._builder.hasAiRecommendedLevelV2();
        }

        public final boolean hasAiRecommendedSwitch() {
            return this._builder.hasAiRecommendedSwitch();
        }

        public final boolean hasBlockbottom() {
            return this._builder.hasBlockbottom();
        }

        public final boolean hasBlockcolorful() {
            return this._builder.hasBlockcolorful();
        }

        public final boolean hasBlockrepeat() {
            return this._builder.hasBlockrepeat();
        }

        public final boolean hasBlockscroll() {
            return this._builder.hasBlockscroll();
        }

        public final boolean hasBlockspecial() {
            return this._builder.hasBlockspecial();
        }

        public final boolean hasBlocktop() {
            return this._builder.hasBlocktop();
        }

        public final boolean hasDomain() {
            return this._builder.hasDomain();
        }

        public final boolean hasEnableblocklist() {
            return this._builder.hasEnableblocklist();
        }

        public final boolean hasInlinePlayerDanmakuSwitch() {
            return this._builder.hasInlinePlayerDanmakuSwitch();
        }

        public final boolean hasOpacity() {
            return this._builder.hasOpacity();
        }

        public final boolean hasScalingfactor() {
            return this._builder.hasScalingfactor();
        }

        public final boolean hasSeniorModeSwitch() {
            return this._builder.hasSeniorModeSwitch();
        }

        public final boolean hasSpeed() {
            return this._builder.hasSpeed();
        }

        public final boolean hasSwitch() {
            return this._builder.hasSwitch();
        }

        public final boolean hasSwitchSave() {
            return this._builder.hasSwitchSave();
        }

        public final boolean hasUseDefaultConfig() {
            return this._builder.hasUseDefaultConfig();
        }

        public final void setAiRecommendedLevel(Dm.PlayerDanmakuAiRecommendedLevel value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiRecommendedLevel(value);
        }

        public final void setAiRecommendedLevelV2(Dm.PlayerDanmakuAiRecommendedLevelV2 value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiRecommendedLevelV2(value);
        }

        public final void setAiRecommendedSwitch(Dm.PlayerDanmakuAiRecommendedSwitch value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAiRecommendedSwitch(value);
        }

        public final void setBlockbottom(Dm.PlayerDanmakuBlockbottom value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBlockbottom(value);
        }

        public final void setBlockcolorful(Dm.PlayerDanmakuBlockcolorful value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBlockcolorful(value);
        }

        public final void setBlockrepeat(Dm.PlayerDanmakuBlockrepeat value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBlockrepeat(value);
        }

        public final void setBlockscroll(Dm.PlayerDanmakuBlockscroll value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBlockscroll(value);
        }

        public final void setBlockspecial(Dm.PlayerDanmakuBlockspecial value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBlockspecial(value);
        }

        public final void setBlocktop(Dm.PlayerDanmakuBlocktop value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setBlocktop(value);
        }

        public final void setDomain(Dm.PlayerDanmakuDomain value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setDomain(value);
        }

        public final void setEnableblocklist(Dm.PlayerDanmakuEnableblocklist value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setEnableblocklist(value);
        }

        public final void setInlinePlayerDanmakuSwitch(Dm.InlinePlayerDanmakuSwitch value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setInlinePlayerDanmakuSwitch(value);
        }

        public final void setOpacity(Dm.PlayerDanmakuOpacity value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setOpacity(value);
        }

        public final void setScalingfactor(Dm.PlayerDanmakuScalingfactor value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setScalingfactor(value);
        }

        public final void setSeniorModeSwitch(Dm.PlayerDanmakuSeniorModeSwitch value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSeniorModeSwitch(value);
        }

        public final void setSpeed(Dm.PlayerDanmakuSpeed value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSpeed(value);
        }

        public final void setSwitch(Dm.PlayerDanmakuSwitch value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSwitch(value);
        }

        public final void setSwitchSave(Dm.PlayerDanmakuSwitchSave value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setSwitchSave(value);
        }

        public final void setTs(long j7) {
            this._builder.setTs(j7);
        }

        public final void setUseDefaultConfig(Dm.PlayerDanmakuUseDefaultConfig value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setUseDefaultConfig(value);
        }
    }

    private DmPlayerConfigReqKt() {
    }
}
