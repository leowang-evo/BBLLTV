package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.search.SearchSuggestModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SearchSuggestWrapper implements Serializable {

    @SerializedName("tag")
    private List<SearchSuggestModel> tag;

    public final List<SearchSuggestModel> getTag() {
        return this.tag;
    }

    public final void setTag(List<SearchSuggestModel> list) {
        this.tag = list;
    }

    public String toString() {
        return DomainSupport.appendList(new StringBuilder("SearchSuggestWrapper(tag="), this.tag, ')');
    }
}
