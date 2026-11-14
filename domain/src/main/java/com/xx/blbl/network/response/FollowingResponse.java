package com.xx.blbl.network.response;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.user.following.FollowingProfileModelWrapper;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FollowingResponse implements Serializable {

    @SerializedName("button_statement")
    private String button_statement = "";

    @SerializedName("items")
    private List<FollowingProfileModelWrapper> items;

    public final String getButton_statement() {
        return this.button_statement;
    }

    public final List<FollowingProfileModelWrapper> getItems() {
        return this.items;
    }

    public final void setButton_statement(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.button_statement = str;
    }

    public final void setItems(List<FollowingProfileModelWrapper> list) {
        this.items = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FollowingResponse(items=");
        sb.append(this.items);
        sb.append(", button_statement='");
        return DomainSupport.appendString(sb, this.button_statement, "')");
    }
}
