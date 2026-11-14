package com.xx.blbl.model.common;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GiveCoinResultModel implements Serializable {

    @SerializedName("like")
    private boolean like;

    public final boolean getLike() {
        return this.like;
    }

    public final void setLike(boolean z7) {
        this.like = z7;
    }

    public String toString() {
        return "LikeResultModel(like=" + this.like + ')';
    }
}
