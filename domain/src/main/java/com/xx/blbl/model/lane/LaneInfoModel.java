package com.xx.blbl.model.lane;

import com.xx.blbl.recovery.DomainSupport;
import com.xx.blbl.model.series.timeline.TimeLineADayModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LaneInfoModel implements Serializable {

    @SerializedName("headers")
    private List<LaneHeaderModel> headers;

    @SerializedName("items")
    private List<LaneItemModel> items;

    @SerializedName("module_id")
    private long module_id;

    @SerializedName("size")
    private int size;
    private List<TimeLineADayModel> timelineDay;

    @SerializedName("title")
    private String title = "";

    @SerializedName("style")
    private String style = "";

    public final List<LaneHeaderModel> getHeaders() {
        return this.headers;
    }

    public final List<LaneItemModel> getItems() {
        return this.items;
    }

    public final long getModule_id() {
        return this.module_id;
    }

    public final int getSize() {
        return this.size;
    }

    public final String getStyle() {
        return this.style;
    }

    public final List<TimeLineADayModel> getTimelineDay() {
        return this.timelineDay;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setHeaders(List<LaneHeaderModel> list) {
        this.headers = list;
    }

    public final void setItems(List<LaneItemModel> list) {
        this.items = list;
    }

    public final void setModule_id(long j7) {
        this.module_id = j7;
    }

    public final void setSize(int i4) {
        this.size = i4;
    }

    public final void setStyle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.style = str;
    }

    public final void setTimelineDay(List<TimeLineADayModel> list) {
        this.timelineDay = list;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LaneInfoModel(title='");
        sb.append(this.title);
        sb.append("', style='");
        sb.append(this.style);
        sb.append("', size=");
        sb.append(this.size);
        sb.append(", module_id=");
        sb.append(this.module_id);
        sb.append(", items=");
        sb.append(this.items);
        sb.append(", headers=");
        return DomainSupport.appendList(sb, this.headers, ')');
    }
}
