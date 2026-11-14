package com.xx.blbl.model.user.following;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FollowingProfileModelWrapper implements Serializable {
    private int defaultItem;

    @SerializedName("user_profile")
    private FollowingProfileModel user_profile;

    public final int getDefaultItem() {
        return this.defaultItem;
    }

    public final FollowingProfileModel getUser_profile() {
        return this.user_profile;
    }

    public final void setDefaultItem(int i4) {
        this.defaultItem = i4;
    }

    public final void setUser_profile(FollowingProfileModel followingProfileModel) {
        this.user_profile = followingProfileModel;
    }

    public String toString() {
        return "FollowingProfileModel(info=" + this.user_profile + ')';
    }
}
