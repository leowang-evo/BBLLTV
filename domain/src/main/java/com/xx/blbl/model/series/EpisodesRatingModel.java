package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodesRatingModel implements Serializable {

    @SerializedName("score")
    private String score = "";

    public final String getScore() {
        return this.score;
    }

    public final void setScore(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.score = str;
    }

    public String toString() {
        return DomainSupport.appendString(new StringBuilder("EpisodesRatingModel(score='"), this.score, "')");
    }
}
