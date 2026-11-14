package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionEdgeQuestionModel implements Serializable {

    @SerializedName("choices")
    private List<InteractionEdgeQuestionChoiceModel> choices;

    @SerializedName("duration")
    private long duration;

    @SerializedName("pause_video")
    private int pause_video;

    @SerializedName("start_time_r")
    private long start_time_r;

    @SerializedName("type")
    private int type;

    @SerializedName("id")
    private String id = "";

    @SerializedName("title")
    private String title = "";

    public final List<InteractionEdgeQuestionChoiceModel> getChoices() {
        return this.choices;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getId() {
        return this.id;
    }

    public final int getPause_video() {
        return this.pause_video;
    }

    public final long getStart_time_r() {
        return this.start_time_r;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getType() {
        return this.type;
    }

    public final void setChoices(List<InteractionEdgeQuestionChoiceModel> list) {
        this.choices = list;
    }

    public final void setDuration(long j7) {
        this.duration = j7;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final void setPause_video(int i4) {
        this.pause_video = i4;
    }

    public final void setStart_time_r(long j7) {
        this.start_time_r = j7;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InteractionEdgeQuestionModel(id='");
        sb.append(this.id);
        sb.append("', choices=");
        sb.append(this.choices);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", start_time_r=");
        sb.append(this.start_time_r);
        sb.append(", duration=");
        sb.append(this.duration);
        sb.append(", pause_video=");
        sb.append(this.pause_video);
        sb.append(", title='");
        return DomainSupport.appendString(sb, this.title, "')");
    }
}
