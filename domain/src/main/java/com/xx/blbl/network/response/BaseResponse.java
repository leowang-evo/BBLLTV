package com.xx.blbl.network.response;

import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class BaseResponse<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private T data;

    @SerializedName("message")
    private String message = "";

    public final int getCode() {
        return this.code;
    }

    public final T getData() {
        return this.data;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setCode(int i4) {
        this.code = i4;
    }

    public final void setData(T t7) {
        this.data = t7;
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public String toString() {
        return "BaseResponse(code=" + this.code + ", message='" + this.message + "', data=" + this.data + ')';
    }
}
