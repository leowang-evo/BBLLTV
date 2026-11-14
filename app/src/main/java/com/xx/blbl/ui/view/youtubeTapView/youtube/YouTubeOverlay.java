package com.xx.blbl.ui.view.youtubeTapView.youtube;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.media3.common.Player;

import com.xx.blbl.R;
import com.xx.blbl.ui.view.exoplayer.PlayerUiLogic;
import com.xx.blbl.ui.view.youtubeTapView.youtube.views.CircleClipTapView;
import com.xx.blbl.ui.view.youtubeTapView.youtube.views.SecondsView;

public final class YouTubeOverlay extends ConstraintLayout {
    public final ConstraintLayout f9790K;
    public final SecondsView f9791L;
    public final CircleClipTapView M;
    public final ProgressBar f9792N;
    public View f9793O;
    public Player f9794P;
    public DoubleTapListener f9795Q;
    public int f9796R;
    public int f9797S;

    public YouTubeOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(context, "context");
        LayoutInflater.from(context).inflate(R.layout.yt_overlay, (ViewGroup) this, true);
        View viewFindViewById = findViewById(R.id.root_constraint_layout);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById, "findViewById(...)");
        this.f9790K = (ConstraintLayout) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.seconds_view);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById2, "findViewById(...)");
        SecondsView secondsView = (SecondsView) viewFindViewById2;
        this.f9791L = secondsView;
        View viewFindViewById3 = findViewById(R.id.circle_clip_tap_view);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById3, "findViewById(...)");
        CircleClipTapView circleClipTapView = (CircleClipTapView) viewFindViewById3;
        this.M = circleClipTapView;
        View viewFindViewById4 = findViewById(R.id.progress_bar);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById4, "findViewById(...)");
        this.f9792N = (ProgressBar) viewFindViewById4;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes =
                    getContext()
                            .obtainStyledAttributes(
                                    attributeSet,
                                    new int[] {
                                        R.attr.yt_animationDuration,
                                        R.attr.yt_arcSize,
                                        R.attr.yt_backgroundCircleColor,
                                        R.attr.yt_icon,
                                        R.attr.yt_iconAnimationDuration,
                                        R.attr.yt_playerView,
                                        R.attr.yt_seekSeconds,
                                        R.attr.yt_tapCircleColor,
                                        R.attr.yt_textAppearance
                                    },
                                    0,
                                    0);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                    typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
            setAnimationDuration(typedArrayObtainStyledAttributes.getInt(0, 650));
            this.f9796R = typedArrayObtainStyledAttributes.getInt(6, 10);
            setIconAnimationDuration(typedArrayObtainStyledAttributes.getInt(4, 750));
            setArcSize$app_appRelease(
                    typedArrayObtainStyledAttributes.getDimensionPixelSize(
                            1, getContext().getResources().getDimensionPixelSize(R.dimen.px100)));
            setTapCircleColor(
                    typedArrayObtainStyledAttributes.getColor(
                            7,
                            androidx.core.content.ContextCompat.getColor(
                                    getContext(), R.color.dtpv_yt_tap_circle_color)));
            setCircleBackgroundColor(
                    typedArrayObtainStyledAttributes.getColor(
                            2,
                            androidx.core.content.ContextCompat.getColor(
                                    getContext(), R.color.dtpv_yt_background_circle_color)));
            setTextAppearance(
                    typedArrayObtainStyledAttributes.getResourceId(
                            8, R.style.YTOSecondsTextAppearance));
            setIcon(typedArrayObtainStyledAttributes.getResourceId(3, R.drawable.ic_play_triangle));
            typedArrayObtainStyledAttributes.recycle();
        } else {
            setArcSize$app_appRelease(
                    getContext().getResources().getDimensionPixelSize(R.dimen.px100));
            setTapCircleColor(
                    androidx.core.content.ContextCompat.getColor(
                            getContext(), R.color.dtpv_yt_tap_circle_color));
            setCircleBackgroundColor(
                    androidx.core.content.ContextCompat.getColor(
                            getContext(), R.color.dtpv_yt_background_circle_color));
            setAnimationDuration(650L);
            setIconAnimationDuration(750L);
            this.f9796R = 10;
            setTextAppearance(R.style.YTOSecondsTextAppearance);
        }
        secondsView.setForward(true);
        m(true);
        circleClipTapView.setPerformAtEnd(
                () -> {
                    if (f9795Q != null) f9795Q.onAnimationEnd();
                    f9791L.setVisibility(INVISIBLE);
                    f9791L.setSeconds(0);
                    f9791L.m();
                    return kotlin.Unit.INSTANCE;
                });
    }

    public final void setAnimationDuration(long j7) {
        this.M.setAnimationDuration(j7);
    }

    public final void setCircleBackgroundColor(int i4) {
        this.M.setCircleBackgroundColor(i4);
    }

    public final void setIcon(int i4) {
        SecondsView secondsView = this.f9791L;
        secondsView.m();
        secondsView.setIcon(i4);
    }

    public final void setIconAnimationDuration(long j7) {
        this.f9791L.setCycleDuration(j7);
    }

    public final void setTapCircleColor(int i4) {
        this.M.setCircleColor(i4);
    }

    public final void setTextAppearance(int i4) {
        androidx.core.widget.TextViewCompat.setTextAppearance(this.f9791L.getTextView(), i4);
        this.f9797S = i4;
    }

    public final long getAnimationDuration() {
        return this.M.getAnimationDuration();
    }

    public final float getArcSize() {
        return this.M.getArcSize();
    }

    public final int getCircleBackgroundColor() {
        return this.M.getCircleBackgroundColor();
    }

    public final int getIcon() {
        return this.f9791L.getIcon();
    }

    public final long getIconAnimationDuration() {
        return this.f9791L.getCycleDuration();
    }

    public final TextView getSecondsTextView() {
        return this.f9791L.getTextView();
    }

    public final int getSeekSeconds() {
        return this.f9796R;
    }

    public final int getTapCircleColor() {
        return this.M.getCircleColor();
    }

    public final int getTextAppearance() {
        return this.f9797S;
    }

    public final void m(boolean z7) {
        ConstraintSet oVar = new ConstraintSet();
        ConstraintLayout constraintLayout = this.f9790K;
        oVar.clone(constraintLayout);
        SecondsView secondsView = this.f9791L;
        if (z7) {
            oVar.clear(secondsView.getId(), 6);
            oVar.connect(secondsView.getId(), 7, ConstraintSet.PARENT_ID, 7);
        } else {
            oVar.clear(secondsView.getId(), 7);
            oVar.connect(secondsView.getId(), 6, ConstraintSet.PARENT_ID, 6);
        }
        secondsView.m();
        ValueAnimator valueAnimator = secondsView.f9817P;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
        oVar.applyTo(constraintLayout);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public final void n(float f7, float f8) {
        View myPlayerView;
        Boolean boolShouldForward;
        Player g3 = this.f9794P;
        if (g3 == null || (myPlayerView = this.f9793O) == null) {
            return;
        }
        DoubleTapListener anonymousClass4 = this.f9795Q;
        if (anonymousClass4 != null) {
            kotlin.jvm.internal.Intrinsics.checkNotNull(myPlayerView);
            boolShouldForward = anonymousClass4.shouldForward(g3, myPlayerView, f7);
        } else {
            boolShouldForward = null;
        }
        int visibility = getVisibility();
        SecondsView secondsView = this.f9791L;
        if (visibility != 0) {
            if (boolShouldForward == null) {
                return;
            }
            DoubleTapListener anonymousClass5 = this.f9795Q;
            if (anonymousClass5 != null) {
                anonymousClass5.onAnimationStart();
            }
            secondsView.setVisibility(0);
            secondsView.m();
            ValueAnimator valueAnimator = secondsView.f9817P;
            if (valueAnimator != null) {
                valueAnimator.start();
            }
        }
        boolean zA = kotlin.jvm.internal.Intrinsics.areEqual(boolShouldForward, Boolean.FALSE);
        CircleClipTapView circleClipTapView = this.M;
        if (zA) {
            if (secondsView.f9825a0) {
                m(false);
                secondsView.setForward(false);
                secondsView.setSeconds(0);
            }
            circleClipTapView.a(
                    () -> {
                        M.c(f7, f8);
                        return kotlin.Unit.INSTANCE;
                    });
            Player g7 = this.f9794P;
            if (g7 != null) {
                secondsView.setCurrentProgressStr(
                        PlayerUiLogic.formatSeconds(
                                (g7.getCurrentPosition() / ((long) 1000)) + ((long) this.f9796R)));
            }
            secondsView.setSeconds(secondsView.getSeconds() + this.f9796R);
            Player g8 = this.f9794P;
            o(
                    g8 != null
                            ? Long.valueOf(g8.getCurrentPosition() - ((long) (this.f9796R * 1000)))
                            : null);
        } else if (kotlin.jvm.internal.Intrinsics.areEqual(boolShouldForward, Boolean.TRUE)) {
            if (!secondsView.f9825a0) {
                m(true);
                secondsView.setForward(true);
                secondsView.setSeconds(0);
            }
            circleClipTapView.a(
                    () -> {
                        M.c(f7, f8);
                        return kotlin.Unit.INSTANCE;
                    });
            Player g9 = this.f9794P;
            if (g9 != null) {
                secondsView.setCurrentProgressStr(
                        PlayerUiLogic.formatSeconds(
                                (g9.getCurrentPosition() / ((long) 1000)) + ((long) this.f9796R)));
            }
            secondsView.setSeconds(secondsView.getSeconds() + this.f9796R);
            Player g10 = this.f9794P;
            o(
                    g10 != null
                            ? Long.valueOf(g10.getCurrentPosition() + ((long) (this.f9796R * 1000)))
                            : null);
        }
        Player g11 = this.f9794P;
        kotlin.jvm.internal.Intrinsics.checkNotNull(g11);
        int iL = (int) g11.getDuration();
        ProgressBar progressBar = this.f9792N;
        progressBar.setMax(iL);
        Player g12 = this.f9794P;
        kotlin.jvm.internal.Intrinsics.checkNotNull(g12);
        progressBar.setProgress((int) g12.getCurrentPosition());
    }

    public final void o(Long l7) {
        if (l7 == null) {
            return;
        }
        if (l7.longValue() <= 0) {
            Player g3 = this.f9794P;
            if (g3 != null) {
                g3.seekTo(0L);
                return;
            }
            return;
        }
        Player g7 = this.f9794P;
        if (g7 != null) {
            long jL = g7.getDuration();
            if (jL >= 0 && l7.longValue() >= jL) {
                Player g8 = this.f9794P;
                if (g8 != null) {
                    g8.seekTo(jL);
                    return;
                }
                return;
            }
        }
        View myPlayerView = this.f9793O;
        if (myPlayerView != null) {
            if (this.f9795Q != null) this.f9795Q.keepInDoubleTapMode();
        }
        Player g9 = this.f9794P;
        if (g9 != null) {
            g9.seekTo(l7.longValue());
        }
    }

    public final void setArcSize$app_appRelease(float f7) {
        this.M.setArcSize(f7);
    }

    public void setPlayer(Player player) {
        this.f9794P = player;
    }

    public void setPlayerView(View playerView) {
        this.f9793O = playerView;
    }

    public void setDoubleTapListener(DoubleTapListener listener) {
        this.f9795Q = listener;
    }

    public void setSeekSeconds(int seconds) {
        this.f9796R = seconds;
    }
}
