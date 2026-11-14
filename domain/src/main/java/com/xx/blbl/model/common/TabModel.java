package com.xx.blbl.model.common;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class TabModel implements Serializable {

    @SerializedName("type")
    private String type = "";

    @SerializedName("name")
    private String name = "";

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TabModel(type='");
        sb.append(this.type);
        sb.append("', name='");
        return DomainSupport.appendString(sb, this.name, "')");
    }
}
