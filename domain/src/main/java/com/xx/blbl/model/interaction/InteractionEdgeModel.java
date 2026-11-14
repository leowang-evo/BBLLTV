package com.xx.blbl.model.interaction;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionEdgeModel implements Serializable {

    @SerializedName("dimension")
    private InteractionEdgeDimensionModel dimension;

    @SerializedName("questions")
    private List<InteractionEdgeQuestionModel> questions;

    @SerializedName("skin")
    private InteractionEdgeSkinModel skin;

    public final InteractionEdgeDimensionModel getDimension() {
        return this.dimension;
    }

    public final List<InteractionEdgeQuestionModel> getQuestions() {
        return this.questions;
    }

    public final InteractionEdgeSkinModel getSkin() {
        return this.skin;
    }

    public final void setDimension(InteractionEdgeDimensionModel interactionEdgeDimensionModel) {
        this.dimension = interactionEdgeDimensionModel;
    }

    public final void setQuestions(List<InteractionEdgeQuestionModel> list) {
        this.questions = list;
    }

    public final void setSkin(InteractionEdgeSkinModel interactionEdgeSkinModel) {
        this.skin = interactionEdgeSkinModel;
    }

    public String toString() {
        return "InteractionEdgeModel(dimension=" + this.dimension + ", questions=" + this.questions + ", skin=" + this.skin + ')';
    }
}
