package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.AppServiceProvider;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import kotlin.LazyThreadSafetyMode;

public final class FavoriteFolderCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int e = 0;
    public final AppCompatImageView f14391a;
    public final AppCompatTextView f14392b;
    public final AppCompatTextView f14393c;
    public final Object d;

    public FavoriteFolderCardViewHolder(View view, ItemClickCallback cVar) {
        super(view);
        this.f14391a = (AppCompatImageView) view.findViewById(R.id.imageView);
        this.f14392b = (AppCompatTextView) view.findViewById(R.id.textView);
        this.f14393c = (AppCompatTextView) view.findViewById(R.id.text_sub);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.click_view);
        this.d =
                kotlin.LazyKt.lazy(
                        LazyThreadSafetyMode.SYNCHRONIZED, new AppServiceProvider(this, 18));
        constraintLayout.setOnClickListener(ItemEvents.click(cVar, this));
    }
}
