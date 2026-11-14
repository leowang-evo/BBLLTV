package com.xx.blbl.model.common;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class CheckGiveCoinModel implements Serializable {

    @SerializedName("multiply")
    private int multiply;

    public final int getMultiply() {
        return this.multiply;
    }

    public final void setMultiply(int i4) {
        this.multiply = i4;
    }

    public String toString() {
        return DomainSupport.appendInt(new StringBuilder("CheckGiveCoinModel(multiply="), this.multiply, ')');
    }
}
