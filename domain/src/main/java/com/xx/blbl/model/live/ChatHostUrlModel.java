package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ChatHostUrlModel implements Serializable {

    @SerializedName("host")
    private String host = "";

    @SerializedName("port")
    private int port;

    @SerializedName("ws_port")
    private int ws_port;

    @SerializedName("wss_port")
    private int wss_port;

    public final String getHost() {
        return this.host;
    }

    public final int getPort() {
        return this.port;
    }

    public final int getWs_port() {
        return this.ws_port;
    }

    public final int getWss_port() {
        return this.wss_port;
    }

    public final void setHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.host = str;
    }

    public final void setPort(int i4) {
        this.port = i4;
    }

    public final void setWs_port(int i4) {
        this.ws_port = i4;
    }

    public final void setWss_port(int i4) {
        this.wss_port = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChatHostUrlModel(host='");
        sb.append(this.host);
        sb.append("', port=");
        sb.append(this.port);
        sb.append(", wss_port=");
        sb.append(this.wss_port);
        sb.append(", ws_port=");
        return DomainSupport.appendInt(sb, this.ws_port, ')');
    }
}
