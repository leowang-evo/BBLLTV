package com.xx.blbl.network.response;

import com.xx.blbl.model.interaction.InteractionInfo;
import com.xx.blbl.model.subtitle.SubtitleWrapper;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerInfoDataWrapper implements Serializable {

    @SerializedName("interaction")
    private InteractionInfo interaction;

    @SerializedName("subtitle")
    private SubtitleWrapper subtitle;

    public final InteractionInfo getInteraction() {
        return this.interaction;
    }

    public final SubtitleWrapper getSubtitle() {
        return this.subtitle;
    }

    public final void setInteraction(InteractionInfo interactionInfo) {
        this.interaction = interactionInfo;
    }

    public final void setSubtitle(SubtitleWrapper subtitleWrapper) {
        this.subtitle = subtitleWrapper;
    }

    public String toString() {
        return "PlayerInfoDataWrapper(subtitle=" + this.subtitle + ", interaction=" + this.interaction + ')';
    }
}
