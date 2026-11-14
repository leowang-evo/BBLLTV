package com.xx.blbl.ui.holder;

import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ItemFocusListener;

import kotlin.jvm.internal.Intrinsics;

public final class SeriesFilterViewHolder extends ViewHolder {
    public static final /* synthetic */ int e = 0;
    public final AppCompatImageView f15024a;
    public final AppCompatTextView f15025b;
    public final TypedValue f15026c;
    public final TypedValue d;

    public SeriesFilterViewHolder(
            View view,
            ItemClickCallback onItemClickListener,
            ItemFocusCallback onItemFocusListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        Intrinsics.checkNotNullParameter(onItemFocusListener, "onItemFocusListener");
        View viewFindViewById = view.findViewById(R.id.image_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f15024a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_filter);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f15025b = (AppCompatTextView) viewFindViewById2;
        TypedValue typedValue = new TypedValue();
        this.f15026c = typedValue;
        TypedValue typedValue2 = new TypedValue();
        this.d = typedValue2;
        View viewFindViewById3 = view.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        viewFindViewById3.setOnClickListener(ItemEvents.click(onItemClickListener, this));
        viewFindViewById3.setOnFocusChangeListener(
                new ItemFocusListener(7, onItemFocusListener, this));
        view.getContext().getTheme().resolveAttribute(android.R.attr.colorAccent, typedValue, true);
        view.getContext()
                .getTheme()
                .resolveAttribute(android.R.attr.textColorPrimary, typedValue2, true);
    }
}
