package com.xx.blbl.model.search;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchSuggestModel implements Serializable {

    @SerializedName("value")
    private String value = "";

    @SerializedName("name")
    private String name = "";

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SuggestWordModel(value='");
        sb.append(this.value);
        sb.append("', name='");
        return DomainSupport.appendString(sb, this.name, "')");
    }
}
