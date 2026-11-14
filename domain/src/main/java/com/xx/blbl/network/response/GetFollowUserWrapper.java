package com.xx.blbl.network.response;

import com.xx.blbl.model.user.UserInfoModel;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GetFollowUserWrapper implements Serializable {

    @SerializedName("list")
    private List<UserInfoModel> list;

    public final List<UserInfoModel> getList() {
        return this.list;
    }

    public final void setList(List<UserInfoModel> list) {
        this.list = list;
    }
}
