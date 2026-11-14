package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ItemFocusListener;
import com.xx.blbl.ui.adapter.SharedClickListener;

import kotlin.jvm.internal.Intrinsics;

public final class UserCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int e = 0;
    public final ItemClickCallback f14883a;
    public final AppCompatImageView f14884b;
    public final AppCompatTextView f14885c;
    public final AppCompatTextView d;

    public UserCardViewHolder(
            View view, ItemClickCallback onItemClickListener, ItemFocusCallback onFocusListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        Intrinsics.checkNotNullParameter(onFocusListener, "onFocusListener");
        this.f14883a = onItemClickListener;
        View viewFindViewById = view.findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14884b = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.textView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14885c = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.text_sub);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.d = (AppCompatTextView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        viewFindViewById4.setOnClickListener(new SharedClickListener(this, 16));
        viewFindViewById4.setOnFocusChangeListener(new ItemFocusListener(6, onFocusListener, this));
    }
}
