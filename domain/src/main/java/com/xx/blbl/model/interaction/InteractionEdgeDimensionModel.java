package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionEdgeDimensionModel implements Serializable {

    @SerializedName("height")
    private int height;

    @SerializedName("rotate")
    private int rotate;

    @SerializedName("width")
    private int width;

    public final int getHeight() {
        return this.height;
    }

    public final int getRotate() {
        return this.rotate;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setHeight(int i4) {
        this.height = i4;
    }

    public final void setRotate(int i4) {
        this.rotate = i4;
    }

    public final void setWidth(int i4) {
        this.width = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InteractionEdgeDimensionModel(width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", rotate=");
        return DomainSupport.appendInt(sb, this.rotate, ')');
    }
}
