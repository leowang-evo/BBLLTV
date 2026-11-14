package com.xx.blbl.network.response;

import com.xx.blbl.model.NavWbi;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GetBiliTicketWrapper implements Serializable {

    @SerializedName("created_at")
    private long created_at;

    @SerializedName("nav")
    private NavWbi nav;

    @SerializedName("ticket")
    private String ticket = "";

    @SerializedName("ttl")
    private long ttl;

    public final long getCreated_at() {
        return this.created_at;
    }

    public final NavWbi getNav() {
        return this.nav;
    }

    public final String getTicket() {
        return this.ticket;
    }

    public final long getTtl() {
        return this.ttl;
    }

    public final void setCreated_at(long j7) {
        this.created_at = j7;
    }

    public final void setNav(NavWbi navWbi) {
        this.nav = navWbi;
    }

    public final void setTicket(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ticket = str;
    }

    public final void setTtl(long j7) {
        this.ttl = j7;
    }

    public String toString() {
        return "GetBiliTicketWrapper(ticket='" + this.ticket + "', created_at=" + this.created_at + ", ttl=" + this.ttl + ", nav=" + this.nav + ')';
    }
}
