package com.xx.blbl.model.user.following;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FollowingOwnerModel implements Serializable {

    @SerializedName("has_update")
    private int has_update;

    @SerializedName("is_reserve_recall")
    private int is_reserve_recall;

    @SerializedName("user_profile")
    private FollowingProfileModel user_profile;

    public final int getHas_update() {
        return this.has_update;
    }

    public final FollowingProfileModel getUser_profile() {
        return this.user_profile;
    }

    public final int is_reserve_recall() {
        return this.is_reserve_recall;
    }

    public final void setHas_update(int i4) {
        this.has_update = i4;
    }

    public final void setUser_profile(FollowingProfileModel followingProfileModel) {
        this.user_profile = followingProfileModel;
    }

    public final void set_reserve_recall(int i4) {
        this.is_reserve_recall = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FollowingOwnerModel(user_profile=");
        sb.append(this.user_profile);
        sb.append(", has_update=");
        sb.append(this.has_update);
        sb.append(", is_reserve_recall=");
        return DomainSupport.appendInt(sb, this.is_reserve_recall, ')');
    }
}
