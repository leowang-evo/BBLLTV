package com.xx.blbl.model.player;

import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class SupportFormatModel implements Serializable {

    @SerializedName("codecs")
    private ArrayList<String> codecs;

    @SerializedName("quality")
    private int quality;

    @SerializedName("format")
    private String format = "";

    @SerializedName("new_description")
    private String new_description = "";

    @SerializedName("display_desc")
    private String display_desc = "";

    public final ArrayList<String> getCodecs() {
        return this.codecs;
    }

    public final String getDisplay_desc() {
        return this.display_desc;
    }

    public final String getFormat() {
        return this.format;
    }

    public final String getNew_description() {
        return this.new_description;
    }

    public final int getQuality() {
        return this.quality;
    }

    public final void setCodecs(ArrayList<String> arrayList) {
        this.codecs = arrayList;
    }

    public final void setDisplay_desc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.display_desc = str;
    }

    public final void setFormat(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.format = str;
    }

    public final void setNew_description(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.new_description = str;
    }

    public final void setQuality(int i4) {
        this.quality = i4;
    }

    public String toString() {
        return "SupportFormatModel(quality=" + this.quality + ", format='" + this.format + "', new_description='" + this.new_description + "', display_desc='" + this.display_desc + "', codecs=" + this.codecs + ')';
    }
}
