package com.xx.blbl.model.player;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class FlacInfoModel implements Serializable {

    @SerializedName("audio")
    private DashMediaModel audio;

    @SerializedName("display")
    private boolean display = true;

    public final DashMediaModel getAudio() {
        return this.audio;
    }

    public final boolean getDisplay() {
        return this.display;
    }

    public final void setAudio(DashMediaModel dashMediaModel) {
        this.audio = dashMediaModel;
    }

    public final void setDisplay(boolean z7) {
        this.display = z7;
    }

    public String toString() {
        return "FlacInfoModel(display=" + this.display + ", audio=" + this.audio + ')';
    }
}
