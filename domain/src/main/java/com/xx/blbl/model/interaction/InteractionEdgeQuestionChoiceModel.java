package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionEdgeQuestionChoiceModel implements Serializable {

    @SerializedName("cid")
    private long cid;

    @SerializedName("id")
    private long id;

    @SerializedName("is_default")
    private int is_default;

    @SerializedName("is_hidden")
    private int is_hidden;

    @SerializedName("text_align")
    private int text_align;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    @SerializedName("x")
    private int f9458x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    @SerializedName("y")
    private int f9459y;

    @SerializedName("platform_action")
    private String platform_action = "";

    @SerializedName("native_action")
    private String native_action = "";

    @SerializedName("condition")
    private String condition = "";

    @SerializedName("option")
    private String option = "";

    public final long getCid() {
        return this.cid;
    }

    public final String getCondition() {
        return this.condition;
    }

    public final long getId() {
        return this.id;
    }

    public final String getNative_action() {
        return this.native_action;
    }

    public final String getOption() {
        return this.option;
    }

    public final String getPlatform_action() {
        return this.platform_action;
    }

    public final int getText_align() {
        return this.text_align;
    }

    public final int getX() {
        return this.f9458x;
    }

    public final int getY() {
        return this.f9459y;
    }

    public final int is_default() {
        return this.is_default;
    }

    public final int is_hidden() {
        return this.is_hidden;
    }

    public final void setCid(long j7) {
        this.cid = j7;
    }

    public final void setCondition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.condition = str;
    }

    public final void setId(long j7) {
        this.id = j7;
    }

    public final void setNative_action(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.native_action = str;
    }

    public final void setOption(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.option = str;
    }

    public final void setPlatform_action(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.platform_action = str;
    }

    public final void setText_align(int i4) {
        this.text_align = i4;
    }

    public final void setX(int i4) {
        this.f9458x = i4;
    }

    public final void setY(int i4) {
        this.f9459y = i4;
    }

    public final void set_default(int i4) {
        this.is_default = i4;
    }

    public final void set_hidden(int i4) {
        this.is_hidden = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InteractionEdgeQuestionChoiceModel(id='");
        sb.append(this.id);
        sb.append("', platform_action='");
        sb.append(this.platform_action);
        sb.append("', native_action='");
        sb.append(this.native_action);
        sb.append("', condition='");
        sb.append(this.condition);
        sb.append("', cid=");
        sb.append(this.cid);
        sb.append(", x=");
        sb.append(this.f9458x);
        sb.append(", y=");
        sb.append(this.f9459y);
        sb.append(", text_align=");
        sb.append(this.text_align);
        sb.append(", option='");
        sb.append(this.option);
        sb.append("', is_default=");
        sb.append(this.is_default);
        sb.append(", is_hidden=");
        return DomainSupport.appendInt(sb, this.is_hidden, ')');
    }
}
