package com.xx.blbl.model.user;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UserSpaceVipInfo implements Serializable {

    @SerializedName("label")
    private VipInfoModel label;

    @SerializedName("role")
    private int role;

    @SerializedName("status")
    private int status;

    @SerializedName("type")
    private int type;

    public final VipInfoModel getLabel() {
        return this.label;
    }

    public final int getRole() {
        return this.role;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getType() {
        return this.type;
    }

    public final void setLabel(VipInfoModel vipInfoModel) {
        this.label = vipInfoModel;
    }

    public final void setRole(int i4) {
        this.role = i4;
    }

    public final void setStatus(int i4) {
        this.status = i4;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public String toString() {
        return "UserSpaceVipInfo(type=" + this.type + ", status=" + this.status + ", role=" + this.role + ", label=" + this.label + ')';
    }
}
