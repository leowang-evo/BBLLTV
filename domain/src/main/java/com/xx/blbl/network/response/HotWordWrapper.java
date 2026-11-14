package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.search.HotWordModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class HotWordWrapper implements Serializable {

    @SerializedName("list")
    private List<HotWordModel> list;

    @SerializedName("message")
    private String message = "";

    public final List<HotWordModel> getList() {
        return this.list;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setList(List<HotWordModel> list) {
        this.list = list;
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HotWordWrapper(list=");
        sb.append(this.list);
        sb.append(", message='");
        return DomainSupport.appendString(sb, this.message, "')");
    }
}
