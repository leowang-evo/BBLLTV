package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class OwnerModel implements Serializable {

    @SerializedName("mid")
    private String mid = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("face")
    private String face = "";

    public final String getFace() {
        return this.face;
    }

    public final String getMid() {
        return this.mid;
    }

    public final String getName() {
        return this.name;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setMid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mid = str;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VideoOwnerModel(mid=");
        sb.append(this.mid);
        sb.append(", name='");
        sb.append(this.name);
        sb.append("', face='");
        return DomainSupport.appendString(sb, this.face, "')");
    }
}
