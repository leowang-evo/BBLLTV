package com.xx.blbl.network.response;

import com.xx.blbl.model.series.timeline.TimeLineADayModel;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public final class GetTimeLineWrapper implements Serializable {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message = "";

    @SerializedName("result")
    private List<TimeLineADayModel> result;

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final List<TimeLineADayModel> getResult() {
        return this.result;
    }

    public final void setCode(int i4) {
        this.code = i4;
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setResult(List<TimeLineADayModel> list) {
        this.result = list;
    }
}
