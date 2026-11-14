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

public final class DynamicAuthorViewHolder extends ViewHolder {
    public static final /* synthetic */ int e = 0;
    public final AppCompatImageView f14394a;
    public final AppCompatTextView f14395b;
    public final LinearLayoutCompat f14396c;
    public final View d;

    public DynamicAuthorViewHolder(View view, ItemClickCallback cVar) {
        super(view);
        View viewFindViewById = view.findViewById(R.id.image_avatar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14394a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_name);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14395b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) viewFindViewById3;
        this.f14396c = linearLayoutCompat;
        View viewFindViewById4 = view.findViewById(R.id.indicator);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.d = viewFindViewById4;
        linearLayoutCompat.setOnClickListener(ItemEvents.click(cVar, this));
        linearLayoutCompat.setOnFocusChangeListener(ItemEvents.selected(this.f14395b));
    }
}
