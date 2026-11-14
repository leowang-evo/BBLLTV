package com.xx.blbl.model.user;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UserStatModel implements Serializable {

    @SerializedName("dynamic_count")
    private long dynamic_count;

    @SerializedName("follower")
    private long follower;

    @SerializedName("following")
    private long following;

    public final long getDynamic_count() {
        return this.dynamic_count;
    }

    public final long getFollower() {
        return this.follower;
    }

    public final long getFollowing() {
        return this.following;
    }

    public final void setDynamic_count(long j7) {
        this.dynamic_count = j7;
    }

    public final void setFollower(long j7) {
        this.follower = j7;
    }

    public final void setFollowing(long j7) {
        this.following = j7;
    }
}
