package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveAreaCategory implements Serializable {

    @SerializedName("area_type")
    private int area_type;

    @SerializedName("area_v2_id")
    private long area_v2_id;

    @SerializedName("area_v2_parent_id")
    private long area_v2_parent_id;

    @SerializedName("id")
    private long id;

    @SerializedName("parent_id")
    private long parent_id;

    @SerializedName("name")
    private String name = "";

    @SerializedName("pic")
    private String pic = "";

    @SerializedName("parent_name")
    private String parent_name = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("link")
    private String link = "";

    public final int getArea_type() {
        return this.area_type;
    }

    public final long getArea_v2_id() {
        return this.area_v2_id;
    }

    public final long getArea_v2_parent_id() {
        return this.area_v2_parent_id;
    }

    public final long getId() {
        return this.id;
    }

    public final String getLink() {
        return this.link;
    }

    public final String getName() {
        return this.name;
    }

    public final long getParent_id() {
        return this.parent_id;
    }

    public final String getParent_name() {
        return this.parent_name;
    }

    public final String getPic() {
        return this.pic;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setArea_type(int i4) {
        this.area_type = i4;
    }

    public final void setArea_v2_id(long j7) {
        this.area_v2_id = j7;
    }

    public final void setArea_v2_parent_id(long j7) {
        this.area_v2_parent_id = j7;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setLink(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.link = str;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setParent_id(long j7) {
        this.parent_id = j7;
    }

    public final void setParent_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.parent_name = str;
    }

    public final void setPic(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pic = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveAreaCategory(id=");
        sb.append(this.id);
        sb.append(", parent_id=");
        sb.append(this.parent_id);
        sb.append(", name='");
        sb.append(this.name);
        sb.append("', pic='");
        sb.append(this.pic);
        sb.append("', parent_name='");
        sb.append(this.parent_name);
        sb.append("', area_type=");
        sb.append(this.area_type);
        sb.append(", area_v2_id=");
        sb.append(this.area_v2_id);
        sb.append(", area_v2_parent_id=");
        sb.append(this.area_v2_parent_id);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', link='");
        return DomainSupport.appendString(sb, this.link, "')");
    }
}
