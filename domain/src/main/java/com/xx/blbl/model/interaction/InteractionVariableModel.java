package com.xx.blbl.model.interaction;

import com.xx.blbl.recovery.DomainSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionVariableModel implements Serializable {

    @SerializedName("is_show")
    private int is_show;

    @SerializedName("value")
    private float value;

    @SerializedName("id")
    private String id = "";

    @SerializedName("id_v2")
    private String id_v2 = "";

    @SerializedName("type")
    private int type = 1;

    @SerializedName("name")
    private String name = "";

    public final String getId() {
        return this.id;
    }

    public final String getId_v2() {
        return this.id_v2;
    }

    public final String getName() {
        return this.name;
    }

    public final int getType() {
        return this.type;
    }

    public final float getValue() {
        return this.value;
    }

    public final int is_show() {
        return this.is_show;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final void setId_v2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id_v2 = str;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setType(int i4) {
        this.type = i4;
    }

    public final void setValue(float f7) {
        this.value = f7;
    }

    public final void set_show(int i4) {
        this.is_show = i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InteractionVariableModel(value=");
        sb.append(this.value);
        sb.append(", id='");
        sb.append(this.id);
        sb.append("', id_v2='");
        sb.append(this.id_v2);
        sb.append("', type=");
        sb.append(this.type);
        sb.append(", is_show=");
        sb.append(this.is_show);
        sb.append(", name='");
        return DomainSupport.appendString(sb, this.name, "')");
    }
}
