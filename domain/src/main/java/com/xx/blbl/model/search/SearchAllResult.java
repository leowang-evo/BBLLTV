package com.xx.blbl.model.search;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchAllResult implements Serializable {

    @SerializedName("data")
    private List<SearchItemModel> data;

    @SerializedName("result_type")
    private String result_type = "";

    public final List<SearchItemModel> getData() {
        return this.data;
    }

    public final String getResult_type() {
        return this.result_type;
    }

    public final void setData(List<SearchItemModel> list) {
        this.data = list;
    }

    public final void setResult_type(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.result_type = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchAllResult(result_type='");
        sb.append(this.result_type);
        sb.append("', data=");
        return DomainSupport.appendList(sb, this.data, ')');
    }
}
