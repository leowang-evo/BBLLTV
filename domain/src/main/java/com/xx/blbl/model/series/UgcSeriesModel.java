package com.xx.blbl.model.series;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class UgcSeriesModel implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("sections")
    private List<UgcSectionModel> sections;

    @SerializedName("cover")
    private String cover = "";

    @SerializedName("intro")
    private String intro = "";

    @SerializedName("title")
    private String title = "";

    public final String getCover() {
        return this.cover;
    }

    public final long getId() {
        return this.id;
    }

    public final String getIntro() {
        return this.intro;
    }

    public final List<UgcSectionModel> getSections() {
        return this.sections;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setCover(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cover = str;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setIntro(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.intro = str;
    }

    public final void setSections(List<UgcSectionModel> list) {
        this.sections = list;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UgcSeriesModel(cover='");
        sb.append(this.cover);
        sb.append("', id=");
        sb.append(this.id);
        sb.append(", intro='");
        sb.append(this.intro);
        sb.append("', title='");
        sb.append(this.title);
        sb.append("', sections=");
        return DomainSupport.appendList(sb, this.sections, ')');
    }
}
