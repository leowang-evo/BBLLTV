package com.xx.blbl.ui.dialog;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.xx.blbl.R;
import com.xx.blbl.model.common.CheckFavoriteModel;
import com.xx.blbl.model.common.TripleActionResultModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;

public final class FavoriteActionCallback implements NetResultCallback {
    public final /* synthetic */ int f12034a;
    public final /* synthetic */ VideoActionsDialog f12035b;

    public /* synthetic */ FavoriteActionCallback(VideoActionsDialog fVar, int i4) {
        this.f12034a = i4;
        this.f12035b = fVar;
    }

    private final void a(Throwable th) {}

    @Override
    public final void onFailure(Throwable th) {
        switch (this.f12034a) {
            case 0:
                break;
            case 1:
                VideoActionsDialog fVar = this.f12035b;
                VideoActionPresenter.toast(
                        fVar.getContext(), String.valueOf(th != null ? th.getMessage() : null));
                fVar.getClass();
                break;
            case 2:
                VideoActionsDialog fVar2 = this.f12035b;
                VideoActionPresenter.toast(
                        fVar2.getContext(), String.valueOf(th != null ? th.getMessage() : null));
                fVar2.getClass();
                break;
            default:
                VideoActionsDialog fVar3 = this.f12035b;
                VideoActionPresenter.toast(
                        fVar3.getContext(), String.valueOf(th != null ? th.getMessage() : null));
                fVar3.getClass();
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        CheckFavoriteModel checkFavoriteModel;
        VideoActionsDialog fVar = this.f12035b;
        switch (this.f12034a) {
            case 0:
                BaseResponse baseResponse = (BaseResponse) obj;
                if (baseResponse == null
                        || (checkFavoriteModel = (CheckFavoriteModel) baseResponse.getData())
                                == null
                        || !checkFavoriteModel.getFavoured()) {
                    fVar.f12043N = false;
                    AppCompatImageView appCompatImageView = fVar.f12047v;
                    if (appCompatImageView == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "iconCollection");
                        throw null;
                    }
                    appCompatImageView.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    fVar.getContext(), R.color.textColor));
                    AppCompatTextView appCompatTextView = fVar.f12050y;
                    if (appCompatTextView != null) {
                        appCompatTextView.setText(fVar.getContext().getString(R.string.collection));
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "textCollection");
                        throw null;
                    }
                }
                fVar.f12043N = true;
                AppCompatImageView appCompatImageView2 = fVar.f12047v;
                if (appCompatImageView2 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconCollection");
                    throw null;
                }
                appCompatImageView2.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView2 = fVar.f12050y;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setText(fVar.getContext().getString(R.string.collection_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textCollection");
                    throw null;
                }
            case 1:
                BaseResponse baseResponse2 = (BaseResponse) obj;
                int i4 = VideoActionsDialog.f12036P;
                fVar.getClass();
                if (baseResponse2 == null || baseResponse2.getCode() != 0) {
                    VideoActionPresenter.toast(
                            fVar.getContext(),
                            String.valueOf(
                                    baseResponse2 != null ? baseResponse2.getMessage() : null));
                    return;
                }
                if (fVar.f12043N) {
                    fVar.f12043N = false;
                    AppCompatImageView appCompatImageView3 = fVar.f12047v;
                    if (appCompatImageView3 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "iconCollection");
                        throw null;
                    }
                    appCompatImageView3.setColorFilter(
                            androidx.core.content.ContextCompat.getColor(
                                    fVar.getContext(), R.color.textColor));
                    AppCompatTextView appCompatTextView3 = fVar.f12050y;
                    if (appCompatTextView3 != null) {
                        appCompatTextView3.setText(
                                fVar.getContext().getString(R.string.collection));
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "textCollection");
                        throw null;
                    }
                }
                fVar.f12043N = true;
                AppCompatImageView appCompatImageView4 = fVar.f12047v;
                if (appCompatImageView4 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconCollection");
                    throw null;
                }
                appCompatImageView4.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView4 = fVar.f12050y;
                if (appCompatTextView4 != null) {
                    appCompatTextView4.setText(fVar.getContext().getString(R.string.collection_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textCollection");
                    throw null;
                }
            case 2:
                BaseResponse baseResponse3 = (BaseResponse) obj;
                fVar.getClass();
                if (baseResponse3 == null || baseResponse3.getCode() != 0) {
                    VideoActionPresenter.toast(
                            fVar.getContext(),
                            String.valueOf(
                                    baseResponse3 != null ? baseResponse3.getMessage() : null));
                    return;
                }
                fVar.f12044O = true;
                AppCompatImageView appCompatImageView5 = fVar.f12046p;
                if (appCompatImageView5 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconCoin");
                    throw null;
                }
                appCompatImageView5.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView5 = fVar.f12049x;
                if (appCompatTextView5 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textCoin");
                    throw null;
                }
                appCompatTextView5.setText(fVar.getContext().getString(R.string.give_coin_));
                fVar.M = true;
                AppCompatImageView appCompatImageView6 = fVar.f12045f;
                if (appCompatImageView6 == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "iconLike");
                    throw null;
                }
                appCompatImageView6.setColorFilter(
                        androidx.core.content.ContextCompat.getColor(
                                fVar.getContext(), R.color.pink));
                AppCompatTextView appCompatTextView6 = fVar.f12048w;
                if (appCompatTextView6 != null) {
                    appCompatTextView6.setText(fVar.getContext().getString(R.string.liked_));
                    return;
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textLike");
                    throw null;
                }
            default:
                BaseResponse baseResponse4 = (BaseResponse) obj;
                fVar.getClass();
                if (baseResponse4 == null || baseResponse4.getCode() != 0) {
                    VideoActionPresenter.toast(
                            fVar.getContext(),
                            String.valueOf(
                                    baseResponse4 != null ? baseResponse4.getMessage() : null));
                    return;
                }
                TripleActionResultModel tripleActionResultModel =
                        (TripleActionResultModel) baseResponse4.getData();
                if (tripleActionResultModel != null) {
                    if (tripleActionResultModel.getLike()) {
                        fVar.M = true;
                        AppCompatImageView appCompatImageView7 = fVar.f12045f;
                        if (appCompatImageView7 == null) {
                            kotlin.jvm.internal.Intrinsics
                                    .throwUninitializedPropertyAccessException("iconLike");
                            throw null;
                        }
                        appCompatImageView7.setColorFilter(
                                androidx.core.content.ContextCompat.getColor(
                                        fVar.getContext(), R.color.pink));
                        AppCompatTextView appCompatTextView7 = fVar.f12048w;
                        if (appCompatTextView7 == null) {
                            kotlin.jvm.internal.Intrinsics
                                    .throwUninitializedPropertyAccessException("textLike");
                            throw null;
                        }
                        appCompatTextView7.setText(fVar.getContext().getString(R.string.liked_));
                    }
                    if (tripleActionResultModel.getCoin()) {
                        fVar.f12044O = true;
                        AppCompatImageView appCompatImageView8 = fVar.f12046p;
                        if (appCompatImageView8 == null) {
                            kotlin.jvm.internal.Intrinsics
                                    .throwUninitializedPropertyAccessException("iconCoin");
                            throw null;
                        }
                        appCompatImageView8.setColorFilter(
                                androidx.core.content.ContextCompat.getColor(
                                        fVar.getContext(), R.color.pink));
                        AppCompatTextView appCompatTextView8 = fVar.f12049x;
                        if (appCompatTextView8 == null) {
                            kotlin.jvm.internal.Intrinsics
                                    .throwUninitializedPropertyAccessException("textCoin");
                            throw null;
                        }
                        appCompatTextView8.setText(
                                fVar.getContext().getString(R.string.give_coin_));
                    }
                    if (tripleActionResultModel.getFav()) {
                        fVar.f12043N = true;
                        AppCompatImageView appCompatImageView9 = fVar.f12047v;
                        if (appCompatImageView9 == null) {
                            kotlin.jvm.internal.Intrinsics
                                    .throwUninitializedPropertyAccessException("iconCollection");
                            throw null;
                        }
                        appCompatImageView9.setColorFilter(
                                androidx.core.content.ContextCompat.getColor(
                                        fVar.getContext(), R.color.pink));
                        AppCompatTextView appCompatTextView9 = fVar.f12050y;
                        if (appCompatTextView9 != null) {
                            appCompatTextView9.setText(
                                    fVar.getContext().getString(R.string.collection_));
                            return;
                        } else {
                            kotlin.jvm.internal.Intrinsics
                                    .throwUninitializedPropertyAccessException("textCollection");
                            throw null;
                        }
                    }
                    return;
                }
                return;
        }
    }
}
