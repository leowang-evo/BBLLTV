package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.favorite.FavoriteFolderModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FavoriteFoldersWrapper implements Serializable {

    @SerializedName("count")
    private int count;

    @SerializedName("list")
    private List<FavoriteFolderModel> list;

    public final int getCount() {
        return this.count;
    }

    public final List<FavoriteFolderModel> getList() {
        return this.list;
    }

    public final void setCount(int i4) {
        this.count = i4;
    }

    public final void setList(List<FavoriteFolderModel> list) {
        this.list = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FavoriteFoldersWrapper(count=");
        sb.append(this.count);
        sb.append(", list=");
        return DomainSupport.appendList(sb, this.list, ')');
    }
}
