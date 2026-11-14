package com.xx.blbl.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/** Original spelling retained for XML and source compatibility. */
public final class WrapContentGirdLayoutManager extends GridLayoutManager {
    public WrapContentGirdLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public WrapContentGirdLayoutManager(
            Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public WrapContentGirdLayoutManager(
            Context context, AttributeSet attributes, int defStyleAttr, int defStyleRes) {
        super(context, attributes, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException failure) {
            failure.printStackTrace();
            Log.e("LayoutManager", String.valueOf(failure.getMessage()));
        }
    }
}
