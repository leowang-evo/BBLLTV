package com.xx.blbl.model.video;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryModel implements Serializable {

    @SerializedName("cid")
    private long cid;

    @SerializedName("dt")
    private int dt;

    @SerializedName("epid")
    private long epid;

    @SerializedName("oid")
    private long oid;

    @SerializedName("page")
    private int page;

    @SerializedName("bvid")
    private String bvid = "";

    @SerializedName("part")
    private String part = "";

    @SerializedName("business")
    private String business = "";

    public final String getBusiness() {
        return this.business;
    }

    public final String getBvid() {
        return this.bvid;
    }

    public final long getCid() {
        return this.cid;
    }

    public final int getDt() {
        return this.dt;
    }

    public final long getEpid() {
        return this.epid;
    }

    public final long getOid() {
        return this.oid;
    }

    public final int getPage() {
        return this.page;
    }

    public final String getPart() {
        return this.part;
    }

    public final void setBusiness(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.business = str;
    }

    public final void setBvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bvid = str;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public final void setDt(int i4) {
        this.dt = i4;
    }

    public final void setEpid(long j7) {
        this.epid = j7;
    }

    public final void setOid(long j7) {
        this.oid = j7;
    }

    public final void setPage(int i4) {
        this.page = i4;
    }

    public final void setPart(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.part = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HistoryModel(oid=");
        sb.append(this.oid);
        sb.append(", epid=");
        sb.append(this.epid);
        sb.append(", bvid='");
        sb.append(this.bvid);
        sb.append("', page=");
        sb.append(this.page);
        sb.append(", cid=");
        sb.append(this.cid);
        sb.append(", part='");
        sb.append(this.part);
        sb.append("', business='");
        sb.append(this.business);
        sb.append("', dt=");
        return DomainSupport.appendInt(sb, this.dt, ')');
    }
}
