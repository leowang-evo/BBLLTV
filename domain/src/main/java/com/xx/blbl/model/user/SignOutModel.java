package com.xx.blbl.model.user;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SignOutModel implements Serializable {

    @SerializedName("redirectUrl")
    private String redirectUrl = "";

    public final String getRedirectUrl() {
        return this.redirectUrl;
    }

    public final void setRedirectUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.redirectUrl = str;
    }

    public String toString() {
        return DomainSupport.appendString(new StringBuilder("SignOutModel(redirectUrl='"), this.redirectUrl, "')");
    }
}
