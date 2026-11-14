package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.ui.holder.RelatedVideoCardViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

public final class RelatedVideosAdapter extends Adapter {
    public ItemClickCallback f10351b;
    public final ArrayList f10350a = new ArrayList();
    public boolean f10352c = true;
    public final ItemRouterT d = new ItemRouterT(this, 27);

    @Override
    public final int getItemCount() {
        return this.f10350a.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof RelatedVideoCardViewHolder) {
            Object obj = this.f10350a.get(i4);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            ((RelatedVideoCardViewHolder) holder).bind((VideoModel) obj);
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE = UiSupport.inflate(viewGroup, "parent", R.layout.cell_video, viewGroup, false);
        int i7 = RelatedVideoCardViewHolder.f14422v;
        Intrinsics.checkNotNull(viewE);
        boolean z7 = this.f10352c;
        ItemRouterT onItemClick = this.d;
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        return new RelatedVideoCardViewHolder(viewE, z7, onItemClick);
    }
}
