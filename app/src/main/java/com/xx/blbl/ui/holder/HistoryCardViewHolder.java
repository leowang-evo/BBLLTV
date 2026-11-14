package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;

public final class HistoryCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14385v = 0;
    public final AppCompatImageView f14386a;
    public final AppCompatTextView f14387b;
    public final AppCompatTextView f14388c;
    public final AppCompatTextView d;
    public final AppCompatTextView e;
    public final AppCompatImageView f14389f;
    public final AppCompatImageView f14390p;

    public HistoryCardViewHolder(View view, ItemClickCallback cVar) {
        super(view);
        this.f14386a = (AppCompatImageView) view.findViewById(R.id.imageView);
        this.f14387b = (AppCompatTextView) view.findViewById(R.id.textView);
        AppCompatImageView appCompatImageView =
                (AppCompatImageView) view.findViewById(R.id.image_avatar);
        this.f14388c = (AppCompatTextView) view.findViewById(R.id.textView_owner);
        AppCompatTextView appCompatTextView =
                (AppCompatTextView) view.findViewById(R.id.text_play_count);
        this.d = appCompatTextView;
        AppCompatTextView appCompatTextView2 =
                (AppCompatTextView) view.findViewById(R.id.text_danmaku_count);
        this.e = appCompatTextView2;
        AppCompatTextView appCompatTextView3 =
                (AppCompatTextView) view.findViewById(R.id.text_duration);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.click_view);
        AppCompatImageView appCompatImageView2 =
                (AppCompatImageView) view.findViewById(R.id.icon_play_count);
        this.f14389f = appCompatImageView2;
        AppCompatImageView appCompatImageView3 =
                (AppCompatImageView) view.findViewById(R.id.icon_danmaku);
        this.f14390p = appCompatImageView3;
        constraintLayout.setOnClickListener(ItemEvents.click(cVar, this));
        appCompatImageView.setVisibility(8);
        appCompatImageView2.setVisibility(8);
        appCompatImageView3.setVisibility(8);
        appCompatTextView.setVisibility(8);
        appCompatTextView2.setVisibility(8);
        appCompatTextView3.setVisibility(8);
    }
}
