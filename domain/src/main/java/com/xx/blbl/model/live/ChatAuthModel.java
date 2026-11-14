package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ChatAuthModel implements Serializable {

    @SerializedName("roomid")
    private long roomid;

    @SerializedName("uid")
    private long uid;

    @SerializedName("protover")
    private int protover = 3;

    @SerializedName("platform")
    private String platform = "web";

    @SerializedName("type")
    private int type = 2;

    @SerializedName("key")
    private String key = "";

    public final String getKey() {
        return this.key;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final int getProtover() {
        return this.protover;
    }

    public final long getRoomid() {
        return this.roomid;
    }

    public final int getType() {
        return this.type;
    }

    public final long getUid() {
        return this.uid;
    }

    public final void setKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.key = str;
    }

    public final void setPlatform(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.platform = str;
    }

    public final void setProtover(int i4) {
        this.protover = i4;
    }

    public final void setRoomid(long j7) {
        this.roomid = j7;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public final void setUid(long j7) {
        this.uid = j7;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChatAuthModel(uid=");
        sb.append(this.uid);
        sb.append(", roomid=");
        sb.append(this.roomid);
        sb.append(", protover=");
        sb.append(this.protover);
        sb.append(", platform='");
        sb.append(this.platform);
        sb.append("', type=");
        sb.append(this.type);
        sb.append(", key='");
        return DomainSupport.appendString(sb, this.key, "')");
    }
}
