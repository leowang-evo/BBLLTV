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

public final class EpisodeChoiceViewHolder extends ViewHolder {
    public static final /* synthetic */ int d = 0;
    public final AppCompatImageView f14382a;
    public final AppCompatTextView f14383b;
    public final LinearLayoutCompat f14384c;

    public EpisodeChoiceViewHolder(View view, ItemClickCallback cVar) {
        super(view);
        View viewFindViewById = view.findViewById(R.id.icon_playing);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14382a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14383b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) viewFindViewById3;
        this.f14384c = linearLayoutCompat;
        linearLayoutCompat.setOnClickListener(ItemEvents.click(cVar, this));
        linearLayoutCompat.setOnFocusChangeListener(ItemEvents.selected(this.f14383b));
    }
}
