package com.xx.blbl.model.video;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class TagModel implements Serializable {

    @SerializedName("tag_id")
    private long tag_id;

    @SerializedName("tag_name")
    private String tag_name = "";

    @SerializedName("tag_type")
    private String tag_type = "";

    public final long getTag_id() {
        return this.tag_id;
    }

    public final String getTag_name() {
        return this.tag_name;
    }

    public final String getTag_type() {
        return this.tag_type;
    }

    public final void setTag_id(long j7) {
        this.tag_id = j7;
    }

    public final void setTag_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tag_name = str;
    }

    public final void setTag_type(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tag_type = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TagModel(tag_id=");
        sb.append(this.tag_id);
        sb.append(", tag_name='");
        return DomainSupport.appendString(sb, this.tag_name, "')");
    }
}
