package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SignInResultModel implements Serializable {

    @SerializedName("code")
    private int code;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("url")
    private String url = "";

    @SerializedName("refresh_token")
    private String refresh_token = "";

    @SerializedName("message")
    private String message = "";

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getRefresh_token() {
        return this.refresh_token;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setCode(int i4) {
        this.code = i4;
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setRefresh_token(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.refresh_token = str;
    }

    public final void setTimestamp(long j7) {
        this.timestamp = j7;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SignInResultModel(url='");
        sb.append(this.url);
        sb.append("', refresh_token='");
        sb.append(this.refresh_token);
        sb.append("', timestamp=");
        sb.append(this.timestamp);
        sb.append(", code=");
        sb.append(this.code);
        sb.append(", message='");
        return DomainSupport.appendString(sb, this.message, "')");
    }
}
