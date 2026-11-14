package com.xx.blbl.network.response;

import com.xx.blbl.model.video.channel.ChannelVideoModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GetVideoByChannelWrapper implements Serializable {

    @SerializedName("list")
    private List<ChannelVideoModel> list;

    @SerializedName("offset")
    private String offset = "";

    @SerializedName("")
    private boolean has_more = true;

    public final boolean getHas_more() {
        return this.has_more;
    }

    public final List<ChannelVideoModel> getList() {
        return this.list;
    }

    public final String getOffset() {
        return this.offset;
    }

    public final void setHas_more(boolean z7) {
        this.has_more = z7;
    }

    public final void setList(List<ChannelVideoModel> list) {
        this.list = list;
    }

    public final void setOffset(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.offset = str;
    }

    public String toString() {
        return "GetVideoByTagWrapper(list=" + this.list + ", offset='" + this.offset + "', has_more=" + this.has_more + ')';
    }
}
