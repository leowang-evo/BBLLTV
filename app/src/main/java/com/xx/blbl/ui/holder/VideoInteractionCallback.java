package com.xx.blbl.ui.holder;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import com.xx.blbl.R;
import com.xx.blbl.model.common.CheckGiveCoinModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.dialog.VideoActionPresenter;

public final class VideoInteractionCallback implements NetResultCallback {
    public final /* synthetic */ int f576a;
    public final /* synthetic */ VideoHeaderViewHolder f577b;

    public /* synthetic */ VideoInteractionCallback(VideoHeaderViewHolder jVar, int i4) {
        this.f576a = i4;
        this.f577b = jVar;
    }

    private final void a(Throwable th) {}

    private final void b(Throwable th) {}

    @Override
    public final void onFailure(Throwable th) {
        switch (this.f576a) {
            case 0:
            case 1:
                break;
            case 2:
                VideoHeaderViewHolder jVar = this.f577b;
                VideoActionPresenter.toast(
                        jVar.f587H.getContext(),
                        String.valueOf(th != null ? th.getMessage() : null));
                jVar.getClass();
                break;
            default:
                VideoHeaderViewHolder jVar2 = this.f577b;
                VideoActionPresenter.toast(
                        jVar2.f602z.getContext(),
                        String.valueOf(th != null ? th.getMessage() : null));
                jVar2.getClass();
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        CheckGiveCoinModel checkGiveCoinModel;
        CheckGiveCoinModel checkGiveCoinModel2;
        Integer num;
        switch (this.f576a) {
            case 0:
                BaseResponse baseResponse = (BaseResponse) obj;
                VideoHeaderViewHolder jVar = this.f577b;
                if ((baseResponse == null
                                || (checkGiveCoinModel2 =
                                                (CheckGiveCoinModel) baseResponse.getData())
                                        == null
                                || checkGiveCoinModel2.getMultiply() != 1)
                        && (baseResponse == null
                                || (checkGiveCoinModel =
                                                (CheckGiveCoinModel) baseResponse.getData())
                                        == null
                                || checkGiveCoinModel.getMultiply() != 2)) {
                    jVar.f592N = false;
                    AppCompatImageView appCompatImageView = jVar.f586G;
                    Context context = appCompatImageView.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            context, "getContext(...)");
                    appCompatImageView.setColorFilter(VideoActionPresenter.primary(context));
                } else {
                    jVar.f592N = true;
                    AppCompatImageView appCompatImageView2 = jVar.f586G;
                    appCompatImageView2.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView2.getContext(), R.color.pink));
                }
                break;
            case 1:
                BaseResponse baseResponse2 = (BaseResponse) obj;
                VideoHeaderViewHolder jVar2 = this.f577b;
                if (baseResponse2 == null
                        || (num = (Integer) baseResponse2.getData()) == null
                        || num.intValue() != 1) {
                    jVar2.f591L = false;
                    AppCompatImageView appCompatImageView3 = jVar2.f602z;
                    Context context2 = appCompatImageView3.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            context2, "getContext(...)");
                    appCompatImageView3.setColorFilter(VideoActionPresenter.primary(context2));
                } else {
                    jVar2.f591L = true;
                    jVar2.f602z.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    jVar2.f593a.getContext(), R.color.pink));
                }
                break;
            case 2:
                BaseResponse baseResponse3 = (BaseResponse) obj;
                VideoHeaderViewHolder jVar3 = this.f577b;
                AppCompatImageView appCompatImageView4 = jVar3.f587H;
                if (baseResponse3 == null || baseResponse3.getCode() != 0) {
                    VideoActionPresenter.toast(
                            appCompatImageView4.getContext(),
                            String.valueOf(
                                    baseResponse3 != null ? baseResponse3.getMessage() : null));
                } else if (!jVar3.M) {
                    jVar3.M = true;
                    appCompatImageView4.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView4.getContext(), R.color.pink));
                } else {
                    jVar3.M = false;
                    Context context3 = jVar3.f602z.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            context3, "getContext(...)");
                    appCompatImageView4.setColorFilter(VideoActionPresenter.primary(context3));
                }
                break;
            default:
                BaseResponse baseResponse4 = (BaseResponse) obj;
                VideoHeaderViewHolder jVar4 = this.f577b;
                AppCompatImageView appCompatImageView5 = jVar4.f602z;
                if (baseResponse4 == null || baseResponse4.getCode() != 0) {
                    VideoActionPresenter.toast(
                            appCompatImageView5.getContext(),
                            String.valueOf(
                                    baseResponse4 != null ? baseResponse4.getMessage() : null));
                } else if (!jVar4.f591L) {
                    jVar4.f591L = true;
                    appCompatImageView5.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView5.getContext(), R.color.pink));
                } else {
                    jVar4.f591L = false;
                    Context context4 = appCompatImageView5.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            context4, "getContext(...)");
                    appCompatImageView5.setColorFilter(VideoActionPresenter.primary(context4));
                }
                break;
        }
    }
}
