package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveRoomModule implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("sort")
    private int sort;

    @SerializedName("type")
    private int type;

    @SerializedName("pic")
    private String pic = "";

    @SerializedName("title")
    private String title = "";

    public final long getId() {
        return this.id;
    }

    public final String getPic() {
        return this.pic;
    }

    public final int getSort() {
        return this.sort;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getType() {
        return this.type;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setPic(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pic = str;
    }

    public final void setSort(int i4) {
        this.sort = i4;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveRoomModule(id=");
        sb.append(this.id);
        sb.append(", pic='");
        sb.append(this.pic);
        sb.append("', title='");
        sb.append(this.title);
        sb.append("', type=");
        sb.append(this.type);
        sb.append(", sort=");
        return DomainSupport.appendInt(sb, this.sort, ')');
    }
}
