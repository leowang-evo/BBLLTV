package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionInfo implements Serializable {

    @SerializedName("graph_version")
    private String graph_version = "";

    @SerializedName("msg")
    private String msg = "";

    public final String getGraph_version() {
        return this.graph_version;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setGraph_version(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.graph_version = str;
    }

    public final void setMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.msg = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InteractionInfo(graph_version='");
        sb.append(this.graph_version);
        sb.append("', msg='");
        return DomainSupport.appendString(sb, this.msg, "')");
    }
}
