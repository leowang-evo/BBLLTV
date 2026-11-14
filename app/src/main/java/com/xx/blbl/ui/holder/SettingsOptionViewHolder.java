package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import kotlin.jvm.internal.Intrinsics;

public final class SettingsOptionViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14790f = 0;
    public final AppCompatImageView f14791a;
    public final AppCompatTextView f14792b;
    public final AppCompatTextView f14793c;
    public final AppCompatImageView d;
    public final AppCompatImageView e;

    public SettingsOptionViewHolder(View view, ItemClickCallback onItemClickListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        View viewFindViewById = view.findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14791a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_view_primary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14792b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.text_view_sub);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.f14793c = (AppCompatTextView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.icon_more);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.d = (AppCompatImageView) viewFindViewById4;
        View viewFindViewById5 = view.findViewById(R.id.icon_check);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.e = (AppCompatImageView) viewFindViewById5;
        view.findViewById(R.id.click_view)
                .setOnClickListener(ItemEvents.click(onItemClickListener, this));
    }
}
