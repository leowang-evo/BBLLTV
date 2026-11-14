package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class CheckRelationModel implements Serializable {

    @SerializedName("attribute")
    private int attribute;

    @SerializedName("mid")
    private long mid;

    @SerializedName("special")
    private int special;

    public final int getAttribute() {
        return this.attribute;
    }

    public final long getMid() {
        return this.mid;
    }

    public final int getSpecial() {
        return this.special;
    }

    public final void setAttribute(int i4) {
        this.attribute = i4;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public final void setSpecial(int i4) {
        this.special = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CheckRelationModel(mid=");
        sb.append(this.mid);
        sb.append(", attribute=");
        sb.append(this.attribute);
        sb.append(", special=");
        return DomainSupport.appendInt(sb, this.special, ')');
    }
}
