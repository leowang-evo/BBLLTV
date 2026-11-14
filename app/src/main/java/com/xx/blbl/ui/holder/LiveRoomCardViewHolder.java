package com.xx.blbl.ui.holder;

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

public final class LiveRoomCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14515p = 0;
    public final AppCompatImageView f14516a;
    public final AppCompatTextView f14517b;
    public final AppCompatImageView f14518c;
    public final AppCompatTextView d;
    public final AppCompatTextView e;
    public final View f14519f;

    public LiveRoomCardViewHolder(
            View view, ItemClickCallback onItemClick, ItemFocusCallback bVar) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.f14516a = (AppCompatImageView) view.findViewById(R.id.imageView);
        AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.textView);
        this.f14517b = appCompatTextView;
        this.f14518c = (AppCompatImageView) view.findViewById(R.id.image_avatar);
        this.d = (AppCompatTextView) view.findViewById(R.id.textView_owner);
        this.e = (AppCompatTextView) view.findViewById(R.id.text_play_count);
        AppCompatTextView appCompatTextView2 =
                (AppCompatTextView) view.findViewById(R.id.text_danmaku_count);
        AppCompatImageView appCompatImageView =
                (AppCompatImageView) view.findViewById(R.id.icon_danmaku);
        AppCompatTextView appCompatTextView3 =
                (AppCompatTextView) view.findViewById(R.id.text_duration);
        View viewFindViewById = view.findViewById(R.id.click_view);
        this.f14519f = viewFindViewById;
        viewFindViewById.setOnClickListener(ItemEvents.click(onItemClick, this));
        if (bVar != null) {
            viewFindViewById.setOnFocusChangeListener(new ItemFocusListener(5, bVar, this));
        }
        appCompatTextView2.setVisibility(8);
        appCompatImageView.setVisibility(8);
        appCompatTextView.setMaxLines(1);
        appCompatTextView.setMinLines(1);
        appCompatTextView3.setVisibility(8);
    }
}
