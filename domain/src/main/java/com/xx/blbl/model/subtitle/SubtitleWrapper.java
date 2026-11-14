package com.xx.blbl.model.subtitle;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleWrapper implements Serializable {

    @SerializedName("subtitles")
    private List<SubtitleInfoModel> subtitles;

    public final List<SubtitleInfoModel> getSubtitles() {
        return this.subtitles;
    }

    public final void setSubtitles(List<SubtitleInfoModel> list) {
        this.subtitles = list;
    }

    public String toString() {
        return DomainSupport.appendList(new StringBuilder("SubtitleWrapper(subtitles="), this.subtitles, ')');
    }
}
