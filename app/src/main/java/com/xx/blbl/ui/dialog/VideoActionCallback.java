package com.xx.blbl.ui.dialog;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.xx.blbl.R;
import com.xx.blbl.model.common.CheckGiveCoinModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.MainActivity;

public final class VideoActionCallback implements NetResultCallback {
    public final /* synthetic */ int f12032a;
    public final /* synthetic */ VideoActionsDialog f12033b;

    public /* synthetic */ VideoActionCallback(VideoActionsDialog fVar, int i4) {
        this.f12032a = i4;
        this.f12033b = fVar;
    }

    private final void a(Throwable th) {}

    private final void b(Throwable th) {}

    @Override
    public final void onFailure(Throwable th) {
        switch (this.f12032a) {
            case 0:
            case 1:
                break;
            case 2:
                VideoActionsDialog fVar = this.f12033b;
                VideoActionPresenter.toast(
                        fVar.getContext(), String.valueOf(th != null ? th.getMessage() : null));
                fVar.getClass();
                break;
            default:
                VideoActionsDialog fVar2 = this.f12033b;
                if (fVar2.getContext() instanceof MainActivity) {
                    Context context = fVar2.getContext();
                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                            context,
                            "null cannot be cast to non-null type com.xx.blbl.ui.MainActivity");
                    if (!((MainActivity) context).isFinishing()) {
                        VideoActionPresenter.toast(
                                fVar2.getContext(),
                                String.valueOf(th != null ? th.getMessage() : null));
                    }
                }
                fVar2.getClass();
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        CheckGiveCoinModel checkGiveCoinModel;
        CheckGiveCoinModel checkGiveCoinModel2;
        Integer num;
        switch (this.f12032a) {
            case 0:
                BaseResponse baseResponse = (BaseResponse) obj;
                VideoActionsDialog fVar = this.f12033b;
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
                    fVar.f12044O = false;
                    AppCompatImageView appCompatImageView = fVar.f12046p;
                    if (appCompatImageView == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "iconCoin");
                        throw null;
                    }
                    appCompatImageView.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    fVar.getContext(), R.color.textColor));
                    AppCompatTextView appCompatTextView = fVar.f12049x;
                    if (appCompatTextView != null) {
                        appCompatTextView.setText(fVar.getContext().getString(R.string.give_coin));
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "textCoin");
                        throw null;
                    }
                }
                fVar.f12044O = true;
                AppCompatImageView appCompatImageView2 = fVar.f12046p;
                if (appCompatImageView2 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconCoin");
                    throw null;
                }
                appCompatImageView2.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView2 = fVar.f12049x;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setText(fVar.getContext().getString(R.string.give_coin_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textCoin");
                    throw null;
                }
            case 1:
                BaseResponse baseResponse2 = (BaseResponse) obj;
                VideoActionsDialog fVar2 = this.f12033b;
                if (baseResponse2 == null
                        || (num = (Integer) baseResponse2.getData()) == null
                        || num.intValue() != 1) {
                    fVar2.M = false;
                    AppCompatImageView appCompatImageView3 = fVar2.f12045f;
                    if (appCompatImageView3 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "iconLike");
                        throw null;
                    }
                    appCompatImageView3.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    fVar2.getContext(), R.color.textColor));
                    AppCompatTextView appCompatTextView3 = fVar2.f12048w;
                    if (appCompatTextView3 != null) {
                        appCompatTextView3.setText(fVar2.getContext().getString(R.string.like));
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "textLike");
                        throw null;
                    }
                }
                fVar2.M = true;
                AppCompatImageView appCompatImageView4 = fVar2.f12045f;
                if (appCompatImageView4 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconLike");
                    throw null;
                }
                appCompatImageView4.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar2.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView4 = fVar2.f12048w;
                if (appCompatTextView4 != null) {
                    appCompatTextView4.setText(fVar2.getContext().getString(R.string.liked_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textLike");
                    throw null;
                }
            case 2:
                BaseResponse baseResponse3 = (BaseResponse) obj;
                VideoActionsDialog fVar3 = this.f12033b;
                if (baseResponse3 == null || baseResponse3.getCode() != 0) {
                    VideoActionPresenter.toast(
                            fVar3.getContext(),
                            String.valueOf(
                                    baseResponse3 != null ? baseResponse3.getMessage() : null));
                    return;
                }
                if (fVar3.f12043N) {
                    fVar3.f12043N = false;
                    AppCompatImageView appCompatImageView5 = fVar3.f12047v;
                    if (appCompatImageView5 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "iconCollection");
                        throw null;
                    }
                    appCompatImageView5.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    fVar3.getContext(), R.color.textColor));
                    AppCompatTextView appCompatTextView5 = fVar3.f12050y;
                    if (appCompatTextView5 != null) {
                        appCompatTextView5.setText(
                                fVar3.getContext().getString(R.string.collection));
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "textCollection");
                        throw null;
                    }
                }
                fVar3.f12043N = true;
                AppCompatImageView appCompatImageView6 = fVar3.f12047v;
                if (appCompatImageView6 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconCollection");
                    throw null;
                }
                appCompatImageView6.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar3.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView6 = fVar3.f12050y;
                if (appCompatTextView6 != null) {
                    appCompatTextView6.setText(fVar3.getContext().getString(R.string.collection_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textCollection");
                    throw null;
                }
            default:
                BaseResponse baseResponse4 = (BaseResponse) obj;
                VideoActionsDialog fVar4 = this.f12033b;
                if (baseResponse4 == null || baseResponse4.getCode() != 0) {
                    if (fVar4.getContext() instanceof MainActivity) {
                        Context context = fVar4.getContext();
                        kotlin.jvm.internal.Intrinsics.checkNotNull(
                                context,
                                "null cannot be cast to non-null type com.xx.blbl.ui.MainActivity");
                        if (((MainActivity) context).isFinishing()) {
                            return;
                        }
                        VideoActionPresenter.toast(
                                fVar4.getContext(),
                                String.valueOf(
                                        baseResponse4 != null ? baseResponse4.getMessage() : null));
                        return;
                    }
                    return;
                }
                if (fVar4.M) {
                    fVar4.M = false;
                    AppCompatImageView appCompatImageView7 = fVar4.f12045f;
                    if (appCompatImageView7 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "iconLike");
                        throw null;
                    }
                    appCompatImageView7.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    fVar4.getContext(), R.color.textColor));
                    AppCompatTextView appCompatTextView7 = fVar4.f12048w;
                    if (appCompatTextView7 != null) {
                        appCompatTextView7.setText(fVar4.getContext().getString(R.string.like));
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "textLike");
                        throw null;
                    }
                }
                fVar4.M = true;
                AppCompatImageView appCompatImageView8 = fVar4.f12045f;
                if (appCompatImageView8 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconLike");
                    throw null;
                }
                appCompatImageView8.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar4.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView8 = fVar4.f12048w;
                if (appCompatTextView8 != null) {
                    appCompatTextView8.setText(fVar4.getContext().getString(R.string.liked_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textLike");
                    throw null;
                }
        }
    }
}
