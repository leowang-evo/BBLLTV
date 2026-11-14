package com.xx.blbl.model.search;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchAllResponseData implements Serializable {

    @SerializedName("pageinfo")
    private SearchAllCountWrapper pageinfo;

    @SerializedName("result")
    private List<SearchAllResult> result;

    @SerializedName("seid")
    private String seid = "";

    @SerializedName("show_module_list")
    private List<String> show_module_list;

    public final SearchAllCountWrapper getPageinfo() {
        return this.pageinfo;
    }

    public final List<SearchAllResult> getResult() {
        return this.result;
    }

    public final String getSeid() {
        return this.seid;
    }

    public final List<String> getShow_module_list() {
        return this.show_module_list;
    }

    public final void setPageinfo(SearchAllCountWrapper searchAllCountWrapper) {
        this.pageinfo = searchAllCountWrapper;
    }

    public final void setResult(List<SearchAllResult> list) {
        this.result = list;
    }

    public final void setSeid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.seid = str;
    }

    public final void setShow_module_list(List<String> list) {
        this.show_module_list = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchAllResponseData(seid='");
        sb.append(this.seid);
        sb.append("', show_module_list=");
        sb.append(this.show_module_list);
        sb.append(", pageinfo=");
        sb.append(this.pageinfo);
        sb.append(", result=");
        return DomainSupport.appendList(sb, this.result, ')');
    }
}
