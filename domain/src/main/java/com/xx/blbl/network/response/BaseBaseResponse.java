package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class BaseBaseResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message = "";

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setCode(int i4) {
        this.code = i4;
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BaseBaseResponse(code=");
        sb.append(this.code);
        sb.append(", message='");
        return DomainSupport.appendString(sb, this.message, "')");
    }
}
