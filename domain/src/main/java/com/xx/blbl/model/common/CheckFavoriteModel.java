package com.xx.blbl.model.common;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class CheckFavoriteModel implements Serializable {

    @SerializedName("count")
    private int count;

    @SerializedName("favoured")
    private boolean favoured;

    public final int getCount() {
        return this.count;
    }

    public final boolean getFavoured() {
        return this.favoured;
    }

    public final void setCount(int i4) {
        this.count = i4;
    }

    public final void setFavoured(boolean z7) {
        this.favoured = z7;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CheckFavoriteModel(favoured=");
        sb.append(this.favoured);
        sb.append(", count=");
        return DomainSupport.appendInt(sb, this.count, ')');
    }
}
