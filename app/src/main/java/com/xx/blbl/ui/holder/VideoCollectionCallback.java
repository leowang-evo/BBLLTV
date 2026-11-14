package com.xx.blbl.ui.holder;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import com.xx.blbl.R;
import com.xx.blbl.model.common.CheckFavoriteModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.dialog.VideoActionPresenter;

public final class VideoCollectionCallback implements NetResultCallback {
    public final /* synthetic */ int f578a;
    public final /* synthetic */ VideoHeaderViewHolder f579b;

    public /* synthetic */ VideoCollectionCallback(VideoHeaderViewHolder jVar, int i4) {
        this.f578a = i4;
        this.f579b = jVar;
    }

    private final void a(Throwable th) {}

    @Override
    public final void onFailure(Throwable th) {
        switch (this.f578a) {
            case 0:
                break;
            case 1:
                VideoHeaderViewHolder jVar = this.f579b;
                VideoActionPresenter.toast(
                        jVar.f587H.getContext(),
                        String.valueOf(th != null ? th.getMessage() : null));
                jVar.getClass();
                break;
            default:
                VideoHeaderViewHolder jVar2 = this.f579b;
                VideoActionPresenter.toast(
                        jVar2.f586G.getContext(),
                        String.valueOf(th != null ? th.getMessage() : null));
                jVar2.getClass();
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        CheckFavoriteModel checkFavoriteModel;
        VideoHeaderViewHolder jVar = this.f579b;
        switch (this.f578a) {
            case 0:
                BaseResponse baseResponse = (BaseResponse) obj;
                if (baseResponse == null
                        || (checkFavoriteModel = (CheckFavoriteModel) baseResponse.getData())
                                == null
                        || !checkFavoriteModel.getFavoured()) {
                    jVar.M = false;
                    AppCompatImageView appCompatImageView = jVar.f587H;
                    Context context = appCompatImageView.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            context, "getContext(...)");
                    appCompatImageView.setColorFilter(VideoActionPresenter.primary(context));
                } else {
                    jVar.M = true;
                    AppCompatImageView appCompatImageView2 = jVar.f587H;
                    appCompatImageView2.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView2.getContext(), R.color.pink));
                }
                break;
            case 1:
                BaseResponse baseResponse2 = (BaseResponse) obj;
                int i4 = VideoHeaderViewHolder.f585O;
                jVar.getClass();
                AppCompatImageView appCompatImageView3 = jVar.f587H;
                if (baseResponse2 == null || baseResponse2.getCode() != 0) {
                    VideoActionPresenter.toast(
                            appCompatImageView3.getContext(),
                            String.valueOf(
                                    baseResponse2 != null ? baseResponse2.getMessage() : null));
                } else if (!jVar.M) {
                    jVar.M = true;
                    appCompatImageView3.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView3.getContext(), R.color.pink));
                } else {
                    jVar.M = false;
                    Context context2 = appCompatImageView3.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            context2, "getContext(...)");
                    appCompatImageView3.setColorFilter(VideoActionPresenter.primary(context2));
                }
                break;
            default:
                BaseResponse baseResponse3 = (BaseResponse) obj;
                jVar.getClass();
                AppCompatImageView appCompatImageView4 = jVar.f586G;
                if (baseResponse3 != null && baseResponse3.getCode() == 0) {
                    jVar.f592N = true;
                    appCompatImageView4.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView4.getContext(), R.color.pink));
                    jVar.f591L = true;
                    AppCompatImageView appCompatImageView5 = jVar.f602z;
                    appCompatImageView5.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    appCompatImageView5.getContext(), R.color.pink));
                } else {
                    VideoActionPresenter.toast(
                            appCompatImageView4.getContext(),
                            String.valueOf(
                                    baseResponse3 != null ? baseResponse3.getMessage() : null));
                }
                break;
        }
    }
}
