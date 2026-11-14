package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.search.SearchItemModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchResponseWrapper implements Serializable {

    @SerializedName("numPages")
    private int numPages;

    @SerializedName("numResults")
    private long numResults;

    @SerializedName("page")
    private int page;

    @SerializedName("pagesize")
    private int pagesize;

    @SerializedName("result")
    private List<SearchItemModel> result;

    public final int getNumPages() {
        return this.numPages;
    }

    public final long getNumResults() {
        return this.numResults;
    }

    public final int getPage() {
        return this.page;
    }

    public final int getPagesize() {
        return this.pagesize;
    }

    public final List<SearchItemModel> getResult() {
        return this.result;
    }

    public final void setNumPages(int i4) {
        this.numPages = i4;
    }

    public final void setNumResults(long j7) {
        this.numResults = j7;
    }

    public final void setPage(int i4) {
        this.page = i4;
    }

    public final void setPagesize(int i4) {
        this.pagesize = i4;
    }

    public final void setResult(List<SearchItemModel> list) {
        this.result = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchResponseWrapper(result=");
        sb.append(this.result);
        sb.append(", numResults=");
        sb.append(this.numResults);
        sb.append(", numPages=");
        sb.append(this.numPages);
        sb.append(", pagesize=");
        sb.append(this.pagesize);
        sb.append(", page=");
        return DomainSupport.appendInt(sb, this.page, ')');
    }
}
