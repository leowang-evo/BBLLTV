package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveAreaCategoryParent implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("list")
    private List<LiveAreaCategory> list;

    @SerializedName("name")
    private String name = "";

    public final long getId() {
        return this.id;
    }

    public final List<LiveAreaCategory> getList() {
        return this.list;
    }

    public final String getName() {
        return this.name;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setList(List<LiveAreaCategory> list) {
        this.list = list;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveAreaCategoryParent(id=");
        sb.append(this.id);
        sb.append(", name='");
        sb.append(this.name);
        sb.append("', list=");
        return DomainSupport.appendList(sb, this.list, ')');
    }
}
