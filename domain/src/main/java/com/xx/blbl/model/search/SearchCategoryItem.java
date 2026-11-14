package com.xx.blbl.model.search;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class SearchCategoryItem {
    private SearchType type = SearchType.Video;
    private String showText = "";

    public final String getShowText() {
        return this.showText;
    }

    public final SearchType getType() {
        return this.type;
    }

    public final void setShowText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.showText = str;
    }

    public final void setType(SearchType searchType) {
        Intrinsics.checkNotNullParameter(searchType, "<set-?>");
        this.type = searchType;
    }
}
