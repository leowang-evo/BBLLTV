package com.xx.blbl.model.dm;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DmModel implements Serializable {
    private int color;
    private String content = "";
    private int mode;
    private int progress;

    public final int getColor() {
        return this.color;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final void setColor(int i4) {
        this.color = i4;
    }

    public final void setContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content = str;
    }

    public final void setMode(int i4) {
        this.mode = i4;
    }

    public final void setProgress(int i4) {
        this.progress = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DmModel(content='");
        sb.append(this.content);
        sb.append("', color=");
        sb.append(this.color);
        sb.append(", progress=");
        sb.append(this.progress);
        sb.append(", mode=");
        return DomainSupport.appendInt(sb, this.mode, ')');
    }
}
