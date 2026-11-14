package com.xx.blbl.model.common;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class TripleActionResultModel implements Serializable {

    @SerializedName("coin")
    private boolean coin;

    @SerializedName("fav")
    private boolean fav;

    @SerializedName("like")
    private boolean like;

    @SerializedName("multiply")
    private int multiply;

    public final boolean getCoin() {
        return this.coin;
    }

    public final boolean getFav() {
        return this.fav;
    }

    public final boolean getLike() {
        return this.like;
    }

    public final int getMultiply() {
        return this.multiply;
    }

    public final void setCoin(boolean z7) {
        this.coin = z7;
    }

    public final void setFav(boolean z7) {
        this.fav = z7;
    }

    public final void setLike(boolean z7) {
        this.like = z7;
    }

    public final void setMultiply(int i4) {
        this.multiply = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TripleActionResultModel(like=");
        sb.append(this.like);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", fav=");
        sb.append(this.fav);
        sb.append(", multiply=");
        return DomainSupport.appendInt(sb, this.multiply, ')');
    }
}
