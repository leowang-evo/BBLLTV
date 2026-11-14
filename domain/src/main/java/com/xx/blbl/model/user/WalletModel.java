package com.xx.blbl.model.user;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class WalletModel implements Serializable {

    @SerializedName("bcoin_balance")
    private String bcoin_balance = "";

    @SerializedName("coupon_balance")
    private String coupon_balance = "";

    @SerializedName("mid")
    private long mid;

    public final String getBcoin_balance() {
        return this.bcoin_balance;
    }

    public final String getCoupon_balance() {
        return this.coupon_balance;
    }

    public final long getMid() {
        return this.mid;
    }

    public final void setBcoin_balance(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bcoin_balance = str;
    }

    public final void setCoupon_balance(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.coupon_balance = str;
    }

    public final void setMid(long j7) {
        this.mid = j7;
    }

    public String toString() {
        return "WalletModel(mid=" + this.mid + ", bcoin_balance=" + this.bcoin_balance + ", coupon_balance=" + this.coupon_balance + ')';
    }
}
