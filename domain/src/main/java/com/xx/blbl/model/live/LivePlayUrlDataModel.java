package com.xx.blbl.model.live;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class LivePlayUrlDataModel implements Serializable {

    @SerializedName("accept_quality")
    private List<String> accept_quality;

    @SerializedName("current_qn")
    private int current_qn;

    @SerializedName("current_quality")
    private int current_quality;

    @SerializedName("durl")
    private List<LiveDUrlModel> durl;

    @SerializedName("quality_description")
    private List<LiveQualityModel> quality_description;

    public final List<String> getAccept_quality() {
        return this.accept_quality;
    }

    public final int getCurrent_qn() {
        return this.current_qn;
    }

    public final int getCurrent_quality() {
        return this.current_quality;
    }

    public final List<LiveDUrlModel> getDurl() {
        return this.durl;
    }

    public final List<LiveQualityModel> getQuality_description() {
        return this.quality_description;
    }

    public final void setAccept_quality(List<String> list) {
        this.accept_quality = list;
    }

    public final void setCurrent_qn(int i4) {
        this.current_qn = i4;
    }

    public final void setCurrent_quality(int i4) {
        this.current_quality = i4;
    }

    public final void setDurl(List<LiveDUrlModel> list) {
        this.durl = list;
    }

    public final void setQuality_description(List<LiveQualityModel> list) {
        this.quality_description = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LivePlayUrlDataModel(current_quality=");
        sb.append(this.current_quality);
        sb.append(", accept_quality=");
        sb.append(this.accept_quality);
        sb.append(", current_qn=");
        sb.append(this.current_qn);
        sb.append(", quality_description=");
        sb.append(this.quality_description);
        sb.append(", durl=");
        return DomainSupport.appendList(sb, this.durl, ')');
    }
}
