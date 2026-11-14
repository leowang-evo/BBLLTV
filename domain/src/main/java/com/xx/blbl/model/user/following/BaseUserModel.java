package com.xx.blbl.model.user.following;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class BaseUserModel implements Serializable {

    @SerializedName("uid")
    private String uid = "";

    @SerializedName("uname")
    private String uname = "";

    @SerializedName("face")
    private String face = "";

    public final String getFace() {
        return this.face;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getUname() {
        return this.uname;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setUid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uid = str;
    }

    public final void setUname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uname = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("VideoOwnerModel(mid=");
        sb.append(this.uid);
        sb.append(", name='");
        sb.append(this.uname);
        sb.append("', face='");
        return DomainSupport.appendString(sb, this.face, "')");
    }
}
