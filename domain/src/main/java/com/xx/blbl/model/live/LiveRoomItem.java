package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LiveRoomItem implements Serializable {

    @SerializedName("area_v2_id")
    private long area_v2_id;

    @SerializedName("area_v2_parent_id")
    private long area_v2_parent_id;

    @SerializedName("broadcast_type")
    private int broadcast_type;

    @SerializedName("online")
    private long online;

    @SerializedName("roomid")
    private long roomid;

    @SerializedName("area_v2_name")
    private String area_v2_name = "";

    @SerializedName("area_v2_parent_name")
    private String area_v2_parent_name = "";

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("link")
    private String link = "";

    @SerializedName("title")
    private String title = "";

    @SerializedName("uname")
    private String uname = "";

    @SerializedName("face")
    private String face = "";

    @SerializedName("uid")
    private String uid = "";

    public final long getArea_v2_id() {
        return this.area_v2_id;
    }

    public final String getArea_v2_name() {
        return this.area_v2_name;
    }

    public final long getArea_v2_parent_id() {
        return this.area_v2_parent_id;
    }

    public final String getArea_v2_parent_name() {
        return this.area_v2_parent_name;
    }

    public final int getBroadcast_type() {
        return this.broadcast_type;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getFace() {
        return this.face;
    }

    public final String getLink() {
        return this.link;
    }

    public final long getOnline() {
        return this.online;
    }

    public final long getRoomid() {
        return this.roomid;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getUname() {
        return this.uname;
    }

    public final void setArea_v2_id(long j7) {
        this.area_v2_id = j7;
    }

    public final void setArea_v2_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.area_v2_name = str;
    }

    public final void setArea_v2_parent_id(long j7) {
        this.area_v2_parent_id = j7;
    }

    public final void setArea_v2_parent_name(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.area_v2_parent_name = str;
    }

    public final void setBroadcast_type(int i4) {
        this.broadcast_type = i4;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.face = str;
    }

    public final void setLink(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.link = str;
    }

    public final void setOnline(long j7) {
        this.online = j7;
    }

    public final void setRoomid(long j7) {
        this.roomid = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setUid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uid = str;
    }

    public final void setUname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uname = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LiveRoomItem(area_v2_id=");
        sb.append(this.area_v2_id);
        sb.append(", area_v2_parent_id=");
        sb.append(this.area_v2_parent_id);
        sb.append(", area_v2_name='");
        sb.append(this.area_v2_name);
        sb.append("', area_v2_parent_name='");
        sb.append(this.area_v2_parent_name);
        sb.append("', broadcast_type=");
        sb.append(this.broadcast_type);
        sb.append(", cover='");
        sb.append(this.cover);
        sb.append("', link='");
        sb.append(this.link);
        sb.append("', online=");
        sb.append(this.online);
        sb.append(", roomid=");
        sb.append(this.roomid);
        sb.append(", title='");
        sb.append(this.title);
        sb.append("', uname='");
        sb.append(this.uname);
        sb.append("', face='");
        sb.append(this.face);
        sb.append("', uid='");
        return DomainSupport.appendString(sb, this.uid, "')");
    }
}
