package com.xx.blbl.model.series;

import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class PgcMediaInfo implements Serializable {

    @SerializedName("areas")
    private List<Integer> areas;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("horizontal_picture")
    private String horizontal_picture = "";

    @SerializedName("media_id")
    private long media_id;

    public final List<Integer> getAreas() {
        return this.areas;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getHorizontal_picture() {
        return this.horizontal_picture;
    }

    public final long getMedia_id() {
        return this.media_id;
    }

    public final void setAreas(List<Integer> list) {
        this.areas = list;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setHorizontal_picture(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.horizontal_picture = str;
    }

    public final void setMedia_id(long j7) {
        this.media_id = j7;
    }
}
