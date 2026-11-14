package com.xx.blbl.model.user.following;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FollowingProfileModel implements Serializable {

    @SerializedName("info")
    private BaseUserModel info;

    public final BaseUserModel getInfo() {
        return this.info;
    }

    public final void setInfo(BaseUserModel baseUserModel) {
        this.info = baseUserModel;
    }

    public String toString() {
        return "FollowingProfileModel(info=" + this.info + ')';
    }
}
