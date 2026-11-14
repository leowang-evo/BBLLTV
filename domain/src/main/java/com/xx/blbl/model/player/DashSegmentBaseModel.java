package com.xx.blbl.model.player;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class DashSegmentBaseModel implements Serializable {

    @SerializedName("initialization")
    private String initialization = "";

    @SerializedName("index_range")
    private String index_range = "";

    public final String getIndex_range() {
        return this.index_range;
    }

    public final String getInitialization() {
        return this.initialization;
    }

    public final void setIndex_range(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.index_range = str;
    }

    public final void setInitialization(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.initialization = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DashSegmentBaseModel(initialization='");
        sb.append(this.initialization);
        sb.append("', index_range='");
        return DomainSupport.appendString(sb, this.index_range, "')");
    }
}
