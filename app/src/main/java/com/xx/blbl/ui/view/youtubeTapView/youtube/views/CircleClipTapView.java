package com.xx.blbl.ui.view.youtubeTapView.youtube.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.xx.blbl.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public final class CircleClipTapView extends View {
    public static final /* synthetic */ int f9798J = 0;
    public boolean f9799G;
    public Function0<Unit> f9800H;
    public float f9801I;
    public final Paint f9802a;
    public final Paint f9803b;
    public int f9804c;
    public int d;
    public final Path e;
    public boolean f9805f;
    public float f9806p;
    public float f9807v;
    public float f9808w;
    public final int f9809x;
    public final int f9810y;
    public ValueAnimator f9811z;

    public CircleClipTapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(attrs, "attrs");
        Paint paint = new Paint();
        this.f9802a = paint;
        Paint paint2 = new Paint();
        this.f9803b = paint2;
        this.e = new Path();
        this.f9805f = true;
        if (context == null) {
            throw new IllegalArgumentException("Context is null.");
        }
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setAntiAlias(true);
        paint.setColor(
                androidx.core.content.ContextCompat.getColor(
                        context, R.color.dtpv_yt_background_circle_color));
        paint2.setStyle(style);
        paint2.setAntiAlias(true);
        paint2.setColor(
                androidx.core.content.ContextCompat.getColor(
                        context, R.color.dtpv_yt_tap_circle_color));
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f9804c = displayMetrics.widthPixels;
        this.d = displayMetrics.heightPixels;
        float f7 = displayMetrics.density;
        this.f9809x = (int) (30.0f * f7);
        this.f9810y = (int) (f7 * 400.0f);
        b();
        this.f9811z = getCircleAnimator();
        this.f9800H = () -> Unit.INSTANCE;
        this.f9801I = 80.0f;
    }

    private final ValueAnimator getCircleAnimator() {
        if (this.f9811z == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(getAnimationDuration());
            valueAnimatorOfFloat.addUpdateListener(
                    animation -> {
                        float value = ((Float) animation.getAnimatedValue()).floatValue();
                        this.f9808w = ((this.f9810y - this.f9809x) * value) + this.f9809x;
                        invalidate();
                    });
            valueAnimatorOfFloat.addListener(new TapAnimationListener(this, 10));
            this.f9811z = valueAnimatorOfFloat;
        }
        ValueAnimator valueAnimator = this.f9811z;
        kotlin.jvm.internal.Intrinsics.checkNotNull(valueAnimator);
        return valueAnimator;
    }

    public final void a(Function0<Unit> aVar) {
        this.f9799G = true;
        getCircleAnimator().end();
        aVar.invoke();
        this.f9799G = false;
        getCircleAnimator().start();
    }

    public final void b() {
        float f7 = this.f9804c * 0.5f;
        Path path = this.e;
        path.reset();
        boolean z7 = this.f9805f;
        float f8 = z7 ? 0.0f : this.f9804c;
        int i4 = z7 ? 1 : -1;
        path.moveTo(f8, 0.0f);
        float f9 = i4;
        path.lineTo(((f7 - this.f9801I) * f9) + f8, 0.0f);
        float f10 = this.f9801I;
        int i7 = this.d;
        path.quadTo(((f7 + f10) * f9) + f8, i7 / 2, ((f7 - f10) * f9) + f8, i7);
        path.lineTo(f8, this.d);
        path.close();
        invalidate();
    }

    public final void c(float f7, float f8) {
        this.f9806p = f7;
        this.f9807v = f8;
        boolean z7 = f7 <= ((float) (getResources().getDisplayMetrics().widthPixels / 2));
        if (this.f9805f != z7) {
            this.f9805f = z7;
            b();
        }
    }

    public final long getAnimationDuration() {
        ValueAnimator valueAnimator = this.f9811z;
        if (valueAnimator != null) {
            return valueAnimator.getDuration();
        }
        return 650L;
    }

    public final float getArcSize() {
        return this.f9801I;
    }

    public final int getCircleBackgroundColor() {
        return this.f9802a.getColor();
    }

    public final int getCircleColor() {
        return this.f9803b.getColor();
    }

    public final Function0<Unit> getPerformAtEnd() {
        return this.f9800H;
    }

    @Override
    public final void onDraw(Canvas canvas) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Path path = this.e;
        canvas.clipPath(path);
        canvas.drawPath(path, this.f9802a);
        canvas.drawCircle(this.f9806p, this.f9807v, this.f9808w, this.f9803b);
    }

    @Override
    public final void onSizeChanged(int i4, int i7, int i8, int i9) {
        super.onSizeChanged(i4, i7, i8, i9);
        this.f9804c = i4;
        this.d = i7;
        b();
    }

    public final void setAnimationDuration(long j7) {
        getCircleAnimator().setDuration(j7);
    }

    public final void setArcSize(float f7) {
        this.f9801I = f7;
        b();
    }

    public final void setCircleBackgroundColor(int i4) {
        this.f9802a.setColor(i4);
    }

    public final void setCircleColor(int i4) {
        this.f9803b.setColor(i4);
    }

    public final void setPerformAtEnd(Function0<Unit> aVar) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(aVar, "<set-?>");
        this.f9800H = aVar;
    }
}
