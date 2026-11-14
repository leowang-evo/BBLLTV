package com.xx.blbl.ui.view.youtubeTapView.youtube.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.xx.blbl.R;

import java.util.Arrays;

public final class SecondsView extends ConstraintLayout {
    boolean animationStopped;
    public static final /* synthetic */ int f9812c0 = 0;
    public final LinearLayout f9813K;
    public final TextView f9814L;
    public ImageView M;
    public ImageView f9815N;
    public ImageView f9816O;
    public ValueAnimator f9817P;
    public ValueAnimator f9818Q;
    public ValueAnimator f9819R;
    public ValueAnimator f9820S;
    public ValueAnimator f9821T;
    public long f9822U;
    public String f9823V;
    public int f9824W;
    public boolean f9825a0;
    public int f9826b0;

    public SecondsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        final int i4 = 4;
        final int i7 = 3;
        final int i8 = 0;
        final int i9 = 2;
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(context, "context");
        final int i10 = 1;
        LayoutInflater.from(context).inflate(R.layout.yt_seconds_view, (ViewGroup) this, true);
        View viewFindViewById = findViewById(R.id.triangle_container);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById, "findViewById(...)");
        this.f9813K = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tv_seconds);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById2, "findViewById(...)");
        this.f9814L = (TextView) viewFindViewById2;
        this.M = (ImageView) findViewById(R.id.icon_1);
        this.f9815N = (ImageView) findViewById(R.id.icon_2);
        this.f9816O = (ImageView) findViewById(R.id.icon_3);
        long j7 = 5;
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(this.f9822U / j7);
        kotlin.jvm.internal.Intrinsics.checkNotNull(duration);
        duration.addListener(new TapAnimationListener(this, 7));
        duration.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    public final /* synthetic */ SecondsView f13996b;

                    {
                        this.f13996b = SecondsView.this;
                    }

                    @Override
                    public final void onAnimationUpdate(ValueAnimator it) {
                        ImageView imageView;
                        SecondsView this$0 = this.f13996b;
                        switch (i8) {
                            case 0:
                                int i11 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView2 = this$0.M;
                                if (imageView2 != null) {
                                    Object animatedValue = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView2.setAlpha(((Float) animatedValue).floatValue());
                                    break;
                                }
                                break;
                            case 1:
                                int i12 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView3 = this$0.f9815N;
                                if (imageView3 != null) {
                                    Object animatedValue2 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue2,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView3.setAlpha(((Float) animatedValue2).floatValue());
                                    break;
                                }
                                break;
                            case 2:
                                int i13 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView4 = this$0.M;
                                if (imageView4 != null && (imageView = this$0.f9816O) != null) {
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView);
                                    imageView4.setAlpha(1.0f - imageView.getAlpha());
                                    ImageView imageView5 = this$0.f9816O;
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView5);
                                    Object animatedValue3 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue3,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView5.setAlpha(((Float) animatedValue3).floatValue());
                                    break;
                                }
                                break;
                            case 3:
                                int i14 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView6 = this$0.f9815N;
                                if (imageView6 != null) {
                                    Object animatedValue4 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue4,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView6.setAlpha(
                                            1.0f - ((Float) animatedValue4).floatValue());
                                    break;
                                }
                                break;
                            default:
                                int i15 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView7 = this$0.f9816O;
                                if (imageView7 != null) {
                                    Object animatedValue5 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue5,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView7.setAlpha(
                                            1.0f - ((Float) animatedValue5).floatValue());
                                    break;
                                }
                                break;
                        }
                    }
                });
        duration.addListener(new TapAnimationListener(this, 6));
        this.f9817P = duration;
        ValueAnimator duration2 = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(this.f9822U / j7);
        kotlin.jvm.internal.Intrinsics.checkNotNull(duration2);
        duration2.addListener(new TapAnimationListener(this, 9));
        duration2.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    public final /* synthetic */ SecondsView f13996b;

                    {
                        this.f13996b = SecondsView.this;
                    }

                    @Override
                    public final void onAnimationUpdate(ValueAnimator it) {
                        ImageView imageView;
                        SecondsView this$0 = this.f13996b;
                        switch (i10) {
                            case 0:
                                int i11 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView2 = this$0.M;
                                if (imageView2 != null) {
                                    Object animatedValue = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView2.setAlpha(((Float) animatedValue).floatValue());
                                    break;
                                }
                                break;
                            case 1:
                                int i12 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView3 = this$0.f9815N;
                                if (imageView3 != null) {
                                    Object animatedValue2 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue2,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView3.setAlpha(((Float) animatedValue2).floatValue());
                                    break;
                                }
                                break;
                            case 2:
                                int i13 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView4 = this$0.M;
                                if (imageView4 != null && (imageView = this$0.f9816O) != null) {
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView);
                                    imageView4.setAlpha(1.0f - imageView.getAlpha());
                                    ImageView imageView5 = this$0.f9816O;
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView5);
                                    Object animatedValue3 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue3,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView5.setAlpha(((Float) animatedValue3).floatValue());
                                    break;
                                }
                                break;
                            case 3:
                                int i14 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView6 = this$0.f9815N;
                                if (imageView6 != null) {
                                    Object animatedValue4 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue4,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView6.setAlpha(
                                            1.0f - ((Float) animatedValue4).floatValue());
                                    break;
                                }
                                break;
                            default:
                                int i15 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView7 = this$0.f9816O;
                                if (imageView7 != null) {
                                    Object animatedValue5 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue5,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView7.setAlpha(
                                            1.0f - ((Float) animatedValue5).floatValue());
                                    break;
                                }
                                break;
                        }
                    }
                });
        duration2.addListener(new TapAnimationListener(this, 8));
        this.f9818Q = duration2;
        ValueAnimator duration3 = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(this.f9822U / j7);
        kotlin.jvm.internal.Intrinsics.checkNotNull(duration3);
        duration3.addListener(new TapAnimationListener(this, 1));
        duration3.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    public final /* synthetic */ SecondsView f13996b;

                    {
                        this.f13996b = SecondsView.this;
                    }

                    @Override
                    public final void onAnimationUpdate(ValueAnimator it) {
                        ImageView imageView;
                        SecondsView this$0 = this.f13996b;
                        switch (i9) {
                            case 0:
                                int i11 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView2 = this$0.M;
                                if (imageView2 != null) {
                                    Object animatedValue = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView2.setAlpha(((Float) animatedValue).floatValue());
                                    break;
                                }
                                break;
                            case 1:
                                int i12 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView3 = this$0.f9815N;
                                if (imageView3 != null) {
                                    Object animatedValue2 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue2,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView3.setAlpha(((Float) animatedValue2).floatValue());
                                    break;
                                }
                                break;
                            case 2:
                                int i13 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView4 = this$0.M;
                                if (imageView4 != null && (imageView = this$0.f9816O) != null) {
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView);
                                    imageView4.setAlpha(1.0f - imageView.getAlpha());
                                    ImageView imageView5 = this$0.f9816O;
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView5);
                                    Object animatedValue3 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue3,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView5.setAlpha(((Float) animatedValue3).floatValue());
                                    break;
                                }
                                break;
                            case 3:
                                int i14 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView6 = this$0.f9815N;
                                if (imageView6 != null) {
                                    Object animatedValue4 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue4,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView6.setAlpha(
                                            1.0f - ((Float) animatedValue4).floatValue());
                                    break;
                                }
                                break;
                            default:
                                int i15 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView7 = this$0.f9816O;
                                if (imageView7 != null) {
                                    Object animatedValue5 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue5,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView7.setAlpha(
                                            1.0f - ((Float) animatedValue5).floatValue());
                                    break;
                                }
                                break;
                        }
                    }
                });
        duration3.addListener(new TapAnimationListener(this, 0));
        this.f9819R = duration3;
        ValueAnimator duration4 = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(this.f9822U / j7);
        kotlin.jvm.internal.Intrinsics.checkNotNull(duration4);
        duration4.addListener(new TapAnimationListener(this, 3));
        duration4.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    public final /* synthetic */ SecondsView f13996b;

                    {
                        this.f13996b = SecondsView.this;
                    }

                    @Override
                    public final void onAnimationUpdate(ValueAnimator it) {
                        ImageView imageView;
                        SecondsView this$0 = this.f13996b;
                        switch (i7) {
                            case 0:
                                int i11 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView2 = this$0.M;
                                if (imageView2 != null) {
                                    Object animatedValue = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView2.setAlpha(((Float) animatedValue).floatValue());
                                    break;
                                }
                                break;
                            case 1:
                                int i12 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView3 = this$0.f9815N;
                                if (imageView3 != null) {
                                    Object animatedValue2 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue2,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView3.setAlpha(((Float) animatedValue2).floatValue());
                                    break;
                                }
                                break;
                            case 2:
                                int i13 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView4 = this$0.M;
                                if (imageView4 != null && (imageView = this$0.f9816O) != null) {
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView);
                                    imageView4.setAlpha(1.0f - imageView.getAlpha());
                                    ImageView imageView5 = this$0.f9816O;
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView5);
                                    Object animatedValue3 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue3,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView5.setAlpha(((Float) animatedValue3).floatValue());
                                    break;
                                }
                                break;
                            case 3:
                                int i14 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView6 = this$0.f9815N;
                                if (imageView6 != null) {
                                    Object animatedValue4 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue4,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView6.setAlpha(
                                            1.0f - ((Float) animatedValue4).floatValue());
                                    break;
                                }
                                break;
                            default:
                                int i15 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView7 = this$0.f9816O;
                                if (imageView7 != null) {
                                    Object animatedValue5 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue5,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView7.setAlpha(
                                            1.0f - ((Float) animatedValue5).floatValue());
                                    break;
                                }
                                break;
                        }
                    }
                });
        duration4.addListener(new TapAnimationListener(this, 2));
        this.f9820S = duration4;
        ValueAnimator duration5 = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(this.f9822U / j7);
        kotlin.jvm.internal.Intrinsics.checkNotNull(duration5);
        duration5.addListener(new TapAnimationListener(this, 5));
        duration5.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    public final /* synthetic */ SecondsView f13996b;

                    {
                        this.f13996b = SecondsView.this;
                    }

                    @Override
                    public final void onAnimationUpdate(ValueAnimator it) {
                        ImageView imageView;
                        SecondsView this$0 = this.f13996b;
                        switch (i4) {
                            case 0:
                                int i11 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView2 = this$0.M;
                                if (imageView2 != null) {
                                    Object animatedValue = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView2.setAlpha(((Float) animatedValue).floatValue());
                                    break;
                                }
                                break;
                            case 1:
                                int i12 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView3 = this$0.f9815N;
                                if (imageView3 != null) {
                                    Object animatedValue2 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue2,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView3.setAlpha(((Float) animatedValue2).floatValue());
                                    break;
                                }
                                break;
                            case 2:
                                int i13 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView4 = this$0.M;
                                if (imageView4 != null && (imageView = this$0.f9816O) != null) {
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView);
                                    imageView4.setAlpha(1.0f - imageView.getAlpha());
                                    ImageView imageView5 = this$0.f9816O;
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(imageView5);
                                    Object animatedValue3 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue3,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView5.setAlpha(((Float) animatedValue3).floatValue());
                                    break;
                                }
                                break;
                            case 3:
                                int i14 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView6 = this$0.f9815N;
                                if (imageView6 != null) {
                                    Object animatedValue4 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue4,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView6.setAlpha(
                                            1.0f - ((Float) animatedValue4).floatValue());
                                    break;
                                }
                                break;
                            default:
                                int i15 = SecondsView.f9812c0;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(it, "it");
                                ImageView imageView7 = this$0.f9816O;
                                if (imageView7 != null) {
                                    Object animatedValue5 = it.getAnimatedValue();
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(
                                            animatedValue5,
                                            "null cannot be cast to non-null type kotlin.Float");
                                    imageView7.setAlpha(
                                            1.0f - ((Float) animatedValue5).floatValue());
                                    break;
                                }
                                break;
                        }
                    }
                });
        duration5.addListener(new TapAnimationListener(this, 4));
        this.f9821T = duration5;
        this.f9822U = 750L;
        this.f9823V = "";
        this.f9825a0 = true;
        this.f9826b0 = R.drawable.ic_play_triangle;
    }

    public final String getCurrentProgressStr() {
        return this.f9823V;
    }

    public final long getCycleDuration() {
        return this.f9822U;
    }

    public final int getIcon() {
        return this.f9826b0;
    }

    public final int getSeconds() {
        return this.f9824W;
    }

    public final TextView getTextView() {
        return this.f9814L;
    }

    public final void m() {
        this.animationStopped = true;
        ValueAnimator valueAnimator = this.f9817P;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.f9818Q;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator valueAnimator3 = this.f9819R;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        ValueAnimator valueAnimator4 = this.f9820S;
        if (valueAnimator4 != null) {
            valueAnimator4.cancel();
        }
        ValueAnimator valueAnimator5 = this.f9821T;
        if (valueAnimator5 != null) {
            valueAnimator5.cancel();
        }
        ImageView imageView = this.M;
        if (imageView != null) {
            imageView.setAlpha(0.0f);
        }
        ImageView imageView2 = this.f9815N;
        if (imageView2 != null) {
            imageView2.setAlpha(0.0f);
        }
        ImageView imageView3 = this.f9816O;
        if (imageView3 == null) {
            return;
        }
        imageView3.setAlpha(0.0f);
    }

    public final void setCurrentProgressStr(String str) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f9823V = str;
    }

    public final void setCycleDuration(long j7) {
        ValueAnimator valueAnimator = this.f9817P;
        if (valueAnimator != null) {
            valueAnimator.setDuration(j7 / ((long) 5));
        }
        ValueAnimator valueAnimator2 = this.f9818Q;
        if (valueAnimator2 != null) {
            valueAnimator2.setDuration(j7 / ((long) 5));
        }
        ValueAnimator valueAnimator3 = this.f9819R;
        if (valueAnimator3 != null) {
            valueAnimator3.setDuration(j7 / ((long) 5));
        }
        ValueAnimator valueAnimator4 = this.f9820S;
        if (valueAnimator4 != null) {
            valueAnimator4.setDuration(j7 / ((long) 5));
        }
        ValueAnimator valueAnimator5 = this.f9821T;
        if (valueAnimator5 != null) {
            valueAnimator5.setDuration(j7 / ((long) 5));
        }
        this.f9822U = j7;
    }

    public final void setForward(boolean z7) {
        this.f9813K.setRotation(z7 ? 0.0f : 180.0f);
        this.f9825a0 = z7;
    }

    public final void setIcon(int i4) {
        if (i4 > 0) {
            ImageView imageView = this.M;
            if (imageView != null) {
                imageView.setImageResource(i4);
            }
            ImageView imageView2 = this.f9815N;
            if (imageView2 != null) {
                imageView2.setImageResource(i4);
            }
            ImageView imageView3 = this.f9816O;
            if (imageView3 != null) {
                imageView3.setImageResource(i4);
            }
        }
        this.f9826b0 = i4;
    }

    public final void setSeconds(int i4) {
        boolean zIsEmpty = TextUtils.isEmpty(this.f9823V);
        TextView textView = this.f9814L;
        if (zIsEmpty) {
            textView.setText(
                    getContext()
                            .getResources()
                            .getQuantityString(
                                    R.plurals.quick_seek_x_second, i4, Integer.valueOf(i4)));
        } else {
            textView.setText(
                    String.format(
                            "%s(%s)",
                            Arrays.copyOf(
                                    new Object[] {
                                        getContext()
                                                .getResources()
                                                .getQuantityString(
                                                        R.plurals.quick_seek_x_second,
                                                        i4,
                                                        Integer.valueOf(i4)),
                                        this.f9823V
                                    },
                                    2)));
        }
        this.f9824W = i4;
    }
}
