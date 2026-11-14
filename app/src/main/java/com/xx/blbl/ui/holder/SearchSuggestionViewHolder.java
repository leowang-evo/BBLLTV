package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import kotlin.jvm.internal.Intrinsics;

public final class SearchSuggestionViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14862c = 0;
    public final AppCompatTextView f14863a;
    public final LinearLayoutCompat f14864b;

    public SearchSuggestionViewHolder(View view, ItemClickCallback onItemClick) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        View viewFindViewById = this.itemView.findViewById(R.id.text_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14863a = (AppCompatTextView) viewFindViewById;
        View viewFindViewById2 = this.itemView.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) viewFindViewById2;
        this.f14864b = linearLayoutCompat;
        linearLayoutCompat.setOnClickListener(ItemEvents.click(onItemClick, this));
        linearLayoutCompat.setOnFocusChangeListener(ItemEvents.selected(this.f14863a));
    }
}
