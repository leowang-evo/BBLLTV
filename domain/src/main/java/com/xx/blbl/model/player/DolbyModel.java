package com.xx.blbl.model.player;

import com.xx.blbl.recovery.DomainSupport;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class DolbyModel {

    @SerializedName("audio")
    private List<DashMediaModel> audio;

    @SerializedName("type")
    private int type;

    public final List<DashMediaModel> getAudio() {
        return this.audio;
    }

    public final int getType() {
        return this.type;
    }

    public final void setAudio(List<DashMediaModel> list) {
        this.audio = list;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DolbyModel(type=");
        sb.append(this.type);
        sb.append(", audio=");
        return DomainSupport.appendList(sb, this.audio, ')');
    }
}
