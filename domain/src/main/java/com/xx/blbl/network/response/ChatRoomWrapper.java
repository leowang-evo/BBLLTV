package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.live.ChatHostUrlModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ChatRoomWrapper implements Serializable {

    @SerializedName("host_list")
    private List<ChatHostUrlModel> host_list;

    @SerializedName("group")
    private String group = "";

    @SerializedName("token")
    private String token = "";

    public final String getGroup() {
        return this.group;
    }

    public final List<ChatHostUrlModel> getHost_list() {
        return this.host_list;
    }

    public final String getToken() {
        return this.token;
    }

    public final void setGroup(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.group = str;
    }

    public final void setHost_list(List<ChatHostUrlModel> list) {
        this.host_list = list;
    }

    public final void setToken(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.token = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChatRoomWrapper(group='");
        sb.append(this.group);
        sb.append("', token='");
        sb.append(this.token);
        sb.append("', host_list=");
        return DomainSupport.appendList(sb, this.host_list, ')');
    }
}
