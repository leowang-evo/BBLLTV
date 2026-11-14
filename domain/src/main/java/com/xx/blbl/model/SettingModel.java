package com.xx.blbl.model;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class SettingModel implements Serializable {
    private String info;
    private String title;

    public SettingModel() {
        this.title = "";
        this.info = "";
    }

    public SettingModel(String title, String info) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(info, "info");
        this.title = title;
        this.info = info;
    }

    public final String getInfo() {
        return this.info;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setInfo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.info = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }
}
