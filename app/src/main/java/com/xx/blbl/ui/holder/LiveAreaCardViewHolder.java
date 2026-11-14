package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import kotlin.jvm.internal.Intrinsics;

public final class LiveAreaCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14512c = 0;
    public final AppCompatImageView f14513a;
    public final AppCompatTextView f14514b;

    public LiveAreaCardViewHolder(View view, ItemClickCallback onItemClickListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        View viewFindViewById = view.findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14513a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.textView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14514b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.text_sub);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        view.findViewById(R.id.click_view)
                .setOnClickListener(ItemEvents.click(onItemClickListener, this));
        ((AppCompatTextView) viewFindViewById3).setVisibility(8);
    }
}
