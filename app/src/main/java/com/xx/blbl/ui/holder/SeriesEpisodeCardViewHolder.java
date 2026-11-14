package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import kotlin.jvm.internal.Intrinsics;

public final class SeriesEpisodeCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f15027p = 0;
    public final AppCompatImageView f15028a;
    public final AppCompatTextView f15029b;
    public final AppCompatTextView f15030c;
    public final AppCompatTextView d;
    public final AppCompatImageView e;
    public final View f15031f;

    public SeriesEpisodeCardViewHolder(View view, ItemClickCallback onItemClickListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        View viewFindViewById = view.findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f15028a = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_position);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f15029b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.text_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.f15030c = (AppCompatTextView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.text_badge);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.d = (AppCompatTextView) viewFindViewById4;
        View viewFindViewById5 = view.findViewById(R.id.icon_play);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.e = (AppCompatImageView) viewFindViewById5;
        View viewFindViewById6 = view.findViewById(R.id.view_cover);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.f15031f = viewFindViewById6;
        AppState dVar = AppController.state();
        View viewFindViewById7 = view.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
        viewFindViewById7.getLayoutParams().width = dVar.displayWidth / 5;
        viewFindViewById7.setOnClickListener(ItemEvents.click(onItemClickListener, this));
    }
}
