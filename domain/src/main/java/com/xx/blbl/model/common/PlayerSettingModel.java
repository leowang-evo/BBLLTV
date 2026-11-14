package com.xx.blbl.model.common;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerSettingModel {
    private boolean check;
    private int icon;
    private int type;
    private String title = "";
    private String showValue = "";

    public final boolean getCheck() {
        return this.check;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final String getShowValue() {
        return this.showValue;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getType() {
        return this.type;
    }

    public final void setCheck(boolean z7) {
        this.check = z7;
    }

    public final void setIcon(int i4) {
        this.icon = i4;
    }

    public final void setShowValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.showValue = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setType(int i4) {
        this.type = i4;
    }
}
