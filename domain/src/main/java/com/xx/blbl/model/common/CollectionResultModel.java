package com.xx.blbl.model.common;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionResultModel implements Serializable {

    @SerializedName("prompt")
    private boolean prompt;

    public final boolean getPrompt() {
        return this.prompt;
    }

    public final void setPrompt(boolean z7) {
        this.prompt = z7;
    }

    public String toString() {
        return "CollectionResultModel(prompt=" + this.prompt + ')';
    }
}
