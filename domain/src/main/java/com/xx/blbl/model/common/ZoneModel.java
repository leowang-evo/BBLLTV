package com.xx.blbl.model.common;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class ZoneModel implements Serializable {

    @SerializedName("addr")
    private String addr = "";

    @SerializedName("country")
    private String country = "";

    @SerializedName("province")
    private String province = "";

    @SerializedName("city")
    private String city = "";

    @SerializedName("")
    private String isp = "";

    public final String getAddr() {
        return this.addr;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final String getProvince() {
        return this.province;
    }

    public final void setAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.addr = str;
    }

    public final void setCity(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.city = str;
    }

    public final void setCountry(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.country = str;
    }

    public final void setIsp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.isp = str;
    }

    public final void setProvince(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.province = str;
    }

    public String toString() {
        return this.addr + ' ' + this.country + this.province + this.city + this.isp;
    }
}
