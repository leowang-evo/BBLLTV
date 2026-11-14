package com.xx.blbl.model.search;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class HotWordModel implements Serializable {

    @SerializedName("pos")
    private int pos;

    @SerializedName("keyword")
    private String keyword = "";

    @SerializedName("show_name")
    private String show_name = "";

    @SerializedName("hot_id")
    private String hot_id = "-1";

    public final String getHot_id() {
        return this.hot_id;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public final int getPos() {
        return this.pos;
    }

    public final String getShow_name() {
        return this.show_name;
    }

    public final void setHot_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hot_id = str;
    }

    public final void setKeyword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.keyword = str;
    }

    public final void setPos(int i4) {
        this.pos = i4;
    }

    public final void setShow_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.show_name = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HotWordModel(keyword='");
        sb.append(this.keyword);
        sb.append("', show_name='");
        sb.append(this.show_name);
        sb.append("', pos=");
        sb.append(this.pos);
        sb.append(", hot_id='");
        return DomainSupport.appendString(sb, this.hot_id, "')");
    }
}
