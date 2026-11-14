package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;

import kotlin.jvm.internal.Intrinsics;

public final class LabelActionViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14410c = 0;
    public final AppCompatTextView f14411a;
    public final AppCompatTextView f14412b;

    public LabelActionViewHolder(
            View view,
            ItemClickCallback onItemClickListener,
            final ItemRouterT onItemLongClickListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        Intrinsics.checkNotNullParameter(onItemLongClickListener, "onItemLongClickListener");
        View viewFindViewById = view.findViewById(R.id.text_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14411a = (AppCompatTextView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_sub);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14412b = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.click_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        viewFindViewById3.setOnClickListener(ItemEvents.click(onItemClickListener, this));
        viewFindViewById3.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public final boolean onLongClick(View view2) {
                        ItemRouterT onItemLongClickListener2 = onItemLongClickListener;
                        Intrinsics.checkNotNullParameter(
                                onItemLongClickListener2, "$onItemLongClickListener");
                        LabelActionViewHolder this$0 = LabelActionViewHolder.this;
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        this$0.getBindingAdapterPosition();
                        ((ScreenContentAdapter) onItemLongClickListener2.f4630b).getClass();
                        return false;
                    }
                });
    }
}
