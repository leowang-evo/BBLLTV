package com.xx.blbl.model.search;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchAllCount implements Serializable {

    @SerializedName("numResults")
    private int numResults;

    @SerializedName("pages")
    private int pages;

    @SerializedName("total")
    private int total;

    public final int getNumResults() {
        return this.numResults;
    }

    public final int getPages() {
        return this.pages;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setNumResults(int i4) {
        this.numResults = i4;
    }

    public final void setPages(int i4) {
        this.pages = i4;
    }

    public final void setTotal(int i4) {
        this.total = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchAllCount(numResults=");
        sb.append(this.numResults);
        sb.append(", pages=");
        sb.append(this.pages);
        sb.append(", total=");
        return DomainSupport.appendInt(sb, this.total, ')');
    }
}
