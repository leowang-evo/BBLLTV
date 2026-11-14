package com.xx.blbl.model.interaction;

import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionModel implements Serializable {

    @SerializedName("edges")
    private InteractionEdgeModel edges;

    @SerializedName("hidden_vars")
    private List<InteractionVariableModel> hidden_vars;

    @SerializedName("preload")
    private InteractionPreloadVideoWrapper preload;

    @SerializedName("story_list")
    private List<InteractionStoryModel> story_list;

    @SerializedName("title")
    private String title = "";

    @SerializedName("edge_id")
    private String edge_id = "";

    public final String getEdge_id() {
        return this.edge_id;
    }

    public final InteractionEdgeModel getEdges() {
        return this.edges;
    }

    public final List<InteractionVariableModel> getHidden_vars() {
        return this.hidden_vars;
    }

    public final InteractionPreloadVideoWrapper getPreload() {
        return this.preload;
    }

    public final List<InteractionStoryModel> getStory_list() {
        return this.story_list;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setEdge_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.edge_id = str;
    }

    public final void setEdges(InteractionEdgeModel interactionEdgeModel) {
        this.edges = interactionEdgeModel;
    }

    public final void setHidden_vars(List<InteractionVariableModel> list) {
        this.hidden_vars = list;
    }

    public final void setPreload(InteractionPreloadVideoWrapper interactionPreloadVideoWrapper) {
        this.preload = interactionPreloadVideoWrapper;
    }

    public final void setStory_list(List<InteractionStoryModel> list) {
        this.story_list = list;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        return "InteractionModel(title='" + this.title + "', edge_id='" + this.edge_id + "', hidden_vars=" + this.hidden_vars + ", preload=" + this.preload + ", story_list=" + this.story_list + ", edges=" + this.edges + ')';
    }
}
