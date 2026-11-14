package com.xx.blbl.ui.view.youtubeTapView.youtube.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;

public final class TapAnimationListener implements Animator.AnimatorListener {
    public final /* synthetic */ int f13997a;
    public final /* synthetic */ View f13998b;

    public TapAnimationListener(View view, int i4) {
        this.f13997a = i4;
        this.f13998b = view;
    }

    @Override
    public final void onAnimationCancel(Animator animation) {
        switch (this.f13997a) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                break;
            default:
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(animation, "animation");
                break;
        }
    }

    @Override
    public final void onAnimationEnd(Animator animation) {
        if (this.f13998b instanceof SecondsView && ((SecondsView) this.f13998b).animationStopped)
            return;
        switch (this.f13997a) {
            case 0:
                ValueAnimator valueAnimator = ((SecondsView) this.f13998b).f9820S;
                if (valueAnimator != null) {
                    valueAnimator.start();
                }
                break;
            case 1:
                break;
            case 2:
                ValueAnimator valueAnimator2 = ((SecondsView) this.f13998b).f9821T;
                if (valueAnimator2 != null) {
                    valueAnimator2.start();
                }
                break;
            case 3:
                break;
            case 4:
                ValueAnimator valueAnimator3 = ((SecondsView) this.f13998b).f9817P;
                if (valueAnimator3 != null) {
                    valueAnimator3.start();
                }
                break;
            case 5:
                break;
            case 6:
                ValueAnimator valueAnimator4 = ((SecondsView) this.f13998b).f9818Q;
                if (valueAnimator4 != null) {
                    valueAnimator4.start();
                }
                break;
            case 7:
                break;
            case 8:
                ValueAnimator valueAnimator5 = ((SecondsView) this.f13998b).f9819R;
                if (valueAnimator5 != null) {
                    valueAnimator5.start();
                }
                break;
            case 9:
                break;
            default:
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(animation, "animation");
                CircleClipTapView circleClipTapView = (CircleClipTapView) this.f13998b;
                if (!circleClipTapView.f9799G) {
                    circleClipTapView.getPerformAtEnd().invoke();
                }
                break;
        }
    }

    @Override
    public final void onAnimationRepeat(Animator animation) {
        switch (this.f13997a) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                break;
            default:
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(animation, "animation");
                break;
        }
    }

    @Override
    public final void onAnimationStart(Animator animation) {
        if (this.f13998b instanceof SecondsView)
            ((SecondsView) this.f13998b).animationStopped = false;
        switch (this.f13997a) {
            case 0:
                break;
            case 1:
                SecondsView secondsView = (SecondsView) this.f13998b;
                ImageView imageView = secondsView.M;
                if (imageView != null) {
                    imageView.setAlpha(1.0f);
                }
                ImageView imageView2 = secondsView.f9815N;
                if (imageView2 != null) {
                    imageView2.setAlpha(1.0f);
                }
                ImageView imageView3 = secondsView.f9816O;
                if (imageView3 != null) {
                    imageView3.setAlpha(0.0f);
                    break;
                }
                break;
            case 2:
                break;
            case 3:
                SecondsView secondsView2 = (SecondsView) this.f13998b;
                ImageView imageView4 = secondsView2.M;
                if (imageView4 != null) {
                    imageView4.setAlpha(0.0f);
                }
                ImageView imageView5 = secondsView2.f9815N;
                if (imageView5 != null) {
                    imageView5.setAlpha(1.0f);
                }
                ImageView imageView6 = secondsView2.f9816O;
                if (imageView6 != null) {
                    imageView6.setAlpha(1.0f);
                    break;
                }
                break;
            case 4:
                break;
            case 5:
                SecondsView secondsView3 = (SecondsView) this.f13998b;
                ImageView imageView7 = secondsView3.M;
                if (imageView7 != null) {
                    imageView7.setAlpha(0.0f);
                }
                ImageView imageView8 = secondsView3.f9815N;
                if (imageView8 != null) {
                    imageView8.setAlpha(0.0f);
                }
                ImageView imageView9 = secondsView3.f9816O;
                if (imageView9 != null) {
                    imageView9.setAlpha(1.0f);
                    break;
                }
                break;
            case 6:
                break;
            case 7:
                SecondsView secondsView4 = (SecondsView) this.f13998b;
                ImageView imageView10 = secondsView4.M;
                if (imageView10 != null) {
                    imageView10.setAlpha(0.0f);
                }
                ImageView imageView11 = secondsView4.f9815N;
                if (imageView11 != null) {
                    imageView11.setAlpha(0.0f);
                }
                ImageView imageView12 = secondsView4.f9816O;
                if (imageView12 != null) {
                    imageView12.setAlpha(0.0f);
                    break;
                }
                break;
            case 8:
                break;
            case 9:
                SecondsView secondsView5 = (SecondsView) this.f13998b;
                ImageView imageView13 = secondsView5.M;
                if (imageView13 != null) {
                    imageView13.setAlpha(1.0f);
                }
                ImageView imageView14 = secondsView5.f9815N;
                if (imageView14 != null) {
                    imageView14.setAlpha(0.0f);
                }
                ImageView imageView15 = secondsView5.f9816O;
                if (imageView15 != null) {
                    imageView15.setAlpha(0.0f);
                    break;
                }
                break;
            default:
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(animation, "animation");
                ((CircleClipTapView) this.f13998b).setVisibility(0);
                break;
        }
    }
}
