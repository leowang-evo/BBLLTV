package com.xx.blbl.ui.holder;

import com.xx.blbl.model.series.FollowSeriesResult;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.platform.UiSupport;

public final class SeriesFollowCallback implements NetResultCallback {
    public final /* synthetic */ int f15043a;
    public final /* synthetic */ SeriesDetailHeaderViewHolder f15044b;

    public /* synthetic */ SeriesFollowCallback(SeriesDetailHeaderViewHolder hVar, int i4) {
        this.f15043a = i4;
        this.f15044b = hVar;
    }

    @Override
    public final void onFailure(Throwable th) {
        switch (this.f15043a) {
            case 0:
                SeriesDetailHeaderViewHolder hVar = this.f15044b;
                hVar.f15046G = false;
                UiSupport.toast(
                        hVar.f15050a.getContext(),
                        String.valueOf(th != null ? th.getMessage() : null));
                break;
            default:
                SeriesDetailHeaderViewHolder hVar2 = this.f15044b;
                hVar2.f15046G = false;
                UiSupport.toast(
                        hVar2.f15050a.getContext(),
                        String.valueOf(th != null ? th.getMessage() : null));
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        FollowSeriesResult followSeriesResult;
        FollowSeriesResult followSeriesResult2;
        switch (this.f15043a) {
            case 0:
                Base2Response base2Response = (Base2Response) obj;
                SeriesDetailHeaderViewHolder hVar = this.f15044b;
                hVar.f15046G = false;
                if (base2Response != null
                        && (followSeriesResult = (FollowSeriesResult) base2Response.getResult())
                                != null) {
                    if (followSeriesResult.getStatus() == 0) {
                        hVar.f15059z = false;
                        SeriesDetailHeaderViewHolder.a(hVar);
                    }
                    UiSupport.toast(hVar.f15050a.getContext(), followSeriesResult.getToast());
                    break;
                }
                break;
            default:
                Base2Response base2Response2 = (Base2Response) obj;
                SeriesDetailHeaderViewHolder hVar2 = this.f15044b;
                hVar2.f15046G = false;
                if (base2Response2 != null
                        && (followSeriesResult2 = (FollowSeriesResult) base2Response2.getResult())
                                != null) {
                    if (followSeriesResult2.getStatus() != 0) {
                        hVar2.f15059z = true;
                        SeriesDetailHeaderViewHolder.a(hVar2);
                    }
                    UiSupport.toast(hVar2.f15050a.getContext(), followSeriesResult2.getToast());
                    break;
                }
                break;
        }
    }
}
