package com.xx.blbl.ui.holder;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ItemFocusListener;

public final class VideoProgressCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14397f = 0;
    public final AppCompatImageView f14398a;
    public final AppCompatTextView f14399b;
    public final ProgressBar f14400c;
    public final AppCompatTextView d;
    public final AppCompatTextView e;

    public VideoProgressCardViewHolder(View view, ItemClickCallback cVar, ItemFocusCallback bVar) {
        super(view);
        this.f14398a = (AppCompatImageView) view.findViewById(R.id.imageView);
        this.f14399b = (AppCompatTextView) view.findViewById(R.id.textView);
        AppCompatImageView appCompatImageView =
                (AppCompatImageView) view.findViewById(R.id.image_avatar);
        AppCompatImageView appCompatImageView2 =
                (AppCompatImageView) view.findViewById(R.id.icon_play_count);
        AppCompatImageView appCompatImageView3 =
                (AppCompatImageView) view.findViewById(R.id.icon_danmaku);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.f14400c = progressBar;
        this.d = (AppCompatTextView) view.findViewById(R.id.textView_owner);
        AppCompatTextView appCompatTextView =
                (AppCompatTextView) view.findViewById(R.id.text_play_count);
        AppCompatTextView appCompatTextView2 =
                (AppCompatTextView) view.findViewById(R.id.text_danmaku_count);
        this.e = (AppCompatTextView) view.findViewById(R.id.text_duration);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.click_view);
        constraintLayout.setOnClickListener(ItemEvents.click(cVar, this));
        appCompatImageView.setVisibility(8);
        appCompatImageView2.setVisibility(8);
        appCompatTextView.setVisibility(8);
        appCompatImageView3.setVisibility(8);
        appCompatTextView2.setVisibility(8);
        progressBar.setVisibility(0);
        if (bVar != null) {
            constraintLayout.setOnFocusChangeListener(new ItemFocusListener(2, bVar, this));
        }
    }
}
