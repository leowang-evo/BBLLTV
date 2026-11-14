package com.xx.blbl.model.video.detail;

import com.xx.blbl.model.user.OwnerModel;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class VideoDetailOwnerInfoModel implements Serializable {

    @SerializedName("card")
    private OwnerModel card;

    @SerializedName("following")
    private boolean following;

    public final OwnerModel getCard() {
        return this.card;
    }

    public final boolean getFollowing() {
        return this.following;
    }

    public final void setCard(OwnerModel ownerModel) {
        this.card = ownerModel;
    }

    public final void setFollowing(boolean z7) {
        this.following = z7;
    }

    public String toString() {
        return "VideoDetailOwnerInfoModel(card=" + this.card + ", following=" + this.following + ')';
    }
}
