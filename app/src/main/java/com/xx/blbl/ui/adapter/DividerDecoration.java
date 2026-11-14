package com.xx.blbl.ui.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.State;

import com.xx.blbl.R;

import kotlin.jvm.internal.Intrinsics;

public final class DividerDecoration extends ItemDecoration {
    public final Drawable f12997a;

    public DividerDecoration(Context context) {
        Intrinsics.checkNotNull(context);
        this.f12997a = context.getDrawable(R.drawable.divider);
    }

    @Override
    public final void onDraw(Canvas c8, RecyclerView recyclerView, State state) {
        Intrinsics.checkNotNullParameter(c8, "c");
        Intrinsics.checkNotNullParameter(state, "state");
        Drawable drawable = this.f12997a;
        if (drawable != null) {
            int dimension =
                    (int) recyclerView.getContext().getResources().getDimension(R.dimen.px50);
            int width = recyclerView.getWidth() - dimension;
            int childCount = recyclerView.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = recyclerView.getChildAt(i4);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                Intrinsics.checkNotNull(
                        layoutParams,
                        "null cannot be cast to non-null type"
                                + " androidx.recyclerview.widget.RecyclerView.LayoutParams");
                int bottom =
                        childAt.getBottom()
                                + ((ViewGroup.MarginLayoutParams) ((LayoutParams) layoutParams))
                                        .bottomMargin;
                drawable.setBounds(
                        dimension, bottom, width, drawable.getIntrinsicHeight() + bottom);
                drawable.draw(c8);
            }
        }
    }
}
