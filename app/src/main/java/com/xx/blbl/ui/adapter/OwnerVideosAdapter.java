package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.series.UgcEpisodeModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.RelatedVideoCardViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

public final class OwnerVideosAdapter extends Adapter {
    public ItemClickCallback f11905b;
    public boolean d;
    public final ArrayList f11904a = new ArrayList();
    public boolean f11906c = true;
    public final ContentClickRouter e = new ContentClickRouter(this, 23);

    @Override
    public final int getItemCount() {
        return this.f11904a.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof RelatedVideoCardViewHolder) {
            RelatedVideoCardViewHolder kVar = (RelatedVideoCardViewHolder) holder;
            Object obj = this.f11904a.get(i4);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            VideoModel arc = ((UgcEpisodeModel) obj).getArc();
            if (arc != null) {
                kVar.bind(arc);
            }
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE = UiSupport.inflate(viewGroup, "parent", R.layout.cell_video, viewGroup, false);
        int i7 = RelatedVideoCardViewHolder.f14422v;
        Intrinsics.checkNotNull(viewE);
        boolean z7 = this.f11906c;
        ContentClickRouter onItemClick = this.e;
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        return new RelatedVideoCardViewHolder(viewE, z7, onItemClick);
    }
}
