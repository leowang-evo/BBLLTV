package com.xx.blbl.model.proto;

import com.google.protobuf.kotlin.ProtoDslMarker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DanmakuElemKt {
    public static final DanmakuElemKt INSTANCE = new DanmakuElemKt();

    @ProtoDslMarker
    public static final class Dsl {
        public static final Companion Companion = new Companion(null);
        private final Dm.DanmakuElem.Builder _builder;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
                this();
            }

            public final /* synthetic */ Dsl _create(Dm.DanmakuElem.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                return new Dsl(builder, null);
            }
        }

        private Dsl(Dm.DanmakuElem.Builder builder) {
            this._builder = builder;
        }

        public /* synthetic */ Dsl(Dm.DanmakuElem.Builder builder, DefaultConstructorMarker dVar) {
            this(builder);
        }

        public final /* synthetic */ Dm.DanmakuElem _build() {
            Dm.DanmakuElem danmakuElemBuild = this._builder.build();
            Intrinsics.checkNotNullExpressionValue(danmakuElemBuild, "build(...)");
            return danmakuElemBuild;
        }

        public final void clearAction() {
            this._builder.clearAction();
        }

        public final void clearAnimation() {
            this._builder.clearAnimation();
        }

        public final void clearAttr() {
            this._builder.clearAttr();
        }

        public final void clearColor() {
            this._builder.clearColor();
        }

        public final void clearColorful() {
            this._builder.clearColorful();
        }

        public final void clearContent() {
            this._builder.clearContent();
        }

        public final void clearCtime() {
            this._builder.clearCtime();
        }

        public final void clearFontsize() {
            this._builder.clearFontsize();
        }

        public final void clearId() {
            this._builder.clearId();
        }

        public final void clearIdStr() {
            this._builder.clearIdStr();
        }

        public final void clearMidHash() {
            this._builder.clearMidHash();
        }

        public final void clearMode() {
            this._builder.clearMode();
        }

        public final void clearPool() {
            this._builder.clearPool();
        }

        public final void clearProgress() {
            this._builder.clearProgress();
        }

        public final void clearWeight() {
            this._builder.clearWeight();
        }

        public final String getAction() {
            String action = this._builder.getAction();
            Intrinsics.checkNotNullExpressionValue(action, "getAction(...)");
            return action;
        }

        public final String getAnimation() {
            String animation = this._builder.getAnimation();
            Intrinsics.checkNotNullExpressionValue(animation, "getAnimation(...)");
            return animation;
        }

        public final int getAttr() {
            return this._builder.getAttr();
        }

        public final int getColor() {
            return this._builder.getColor();
        }

        public final Dm.DmColorfulType getColorful() {
            Dm.DmColorfulType colorful = this._builder.getColorful();
            Intrinsics.checkNotNullExpressionValue(colorful, "getColorful(...)");
            return colorful;
        }

        public final String getContent() {
            String content = this._builder.getContent();
            Intrinsics.checkNotNullExpressionValue(content, "getContent(...)");
            return content;
        }

        public final long getCtime() {
            return this._builder.getCtime();
        }

        public final int getFontsize() {
            return this._builder.getFontsize();
        }

        public final long getId() {
            return this._builder.getId();
        }

        public final String getIdStr() {
            String idStr = this._builder.getIdStr();
            Intrinsics.checkNotNullExpressionValue(idStr, "getIdStr(...)");
            return idStr;
        }

        public final String getMidHash() {
            String midHash = this._builder.getMidHash();
            Intrinsics.checkNotNullExpressionValue(midHash, "getMidHash(...)");
            return midHash;
        }

        public final int getMode() {
            return this._builder.getMode();
        }

        public final int getPool() {
            return this._builder.getPool();
        }

        public final int getProgress() {
            return this._builder.getProgress();
        }

        public final int getWeight() {
            return this._builder.getWeight();
        }

        public final void setAction(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAction(value);
        }

        public final void setAnimation(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setAnimation(value);
        }

        public final void setAttr(int i4) {
            this._builder.setAttr(i4);
        }

        public final void setColor(int i4) {
            this._builder.setColor(i4);
        }

        public final void setColorful(Dm.DmColorfulType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setColorful(value);
        }

        public final void setContent(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setContent(value);
        }

        public final void setCtime(long j7) {
            this._builder.setCtime(j7);
        }

        public final void setFontsize(int i4) {
            this._builder.setFontsize(i4);
        }

        public final void setId(long j7) {
            this._builder.setId(j7);
        }

        public final void setIdStr(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setIdStr(value);
        }

        public final void setMidHash(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._builder.setMidHash(value);
        }

        public final void setMode(int i4) {
            this._builder.setMode(i4);
        }

        public final void setPool(int i4) {
            this._builder.setPool(i4);
        }

        public final void setProgress(int i4) {
            this._builder.setProgress(i4);
        }

        public final void setWeight(int i4) {
            this._builder.setWeight(i4);
        }
    }

    private DanmakuElemKt() {
    }
}
