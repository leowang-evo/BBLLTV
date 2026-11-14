package com.xx.blbl.model.episode;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class NewEpisodeInfoModel implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("desc")
    private String desc = "";

    @SerializedName("title")
    private String title = "";

    public final String getDesc() {
        return this.desc;
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.desc = str;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NewEpisodeInfoModel(desc='");
        sb.append(this.desc);
        sb.append("', id=");
        sb.append(this.id);
        sb.append(", title='");
        return DomainSupport.appendString(sb, this.title, "')");
    }
}
