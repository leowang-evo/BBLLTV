package com.xx.blbl.network.response;

import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class Base2Response<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message = "";

    @SerializedName("result")
    private T result;

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final T getResult() {
        return this.result;
    }

    public final void setCode(int i4) {
        this.code = i4;
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setResult(T t7) {
        this.result = t7;
    }

    public String toString() {
        return "BaseResponse(code=" + this.code + ", message='" + this.message + "', result=" + this.result + ')';
    }
}
