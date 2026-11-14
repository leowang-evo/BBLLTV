package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import kotlin.jvm.internal.Intrinsics;

public final class SearchCategoryViewHolder extends ViewHolder {
    public static final /* synthetic */ int d = 0;
    public final AppCompatImageView f14865a;
    public final AppCompatTextView f14866b;
    public final LinearLayoutCompat f14867c;

    public SearchCategoryViewHolder(View view, ItemClickCallback onItemClick) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        View viewFindViewById = this.itemView.findViewById(R.id.image_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14865a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = this.itemView.findViewById(R.id.text_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14866b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = this.itemView.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) viewFindViewById3;
        this.f14867c = linearLayoutCompat;
        linearLayoutCompat.setOnClickListener(ItemEvents.click(onItemClick, this));
        linearLayoutCompat.setOnFocusChangeListener(ItemEvents.selected(this.f14866b));
    }
}
