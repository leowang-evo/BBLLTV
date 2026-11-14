package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.AppServiceProvider;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.VideoCardViewHolder;

import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

public final class DynamicVideoFeedAdapter extends Adapter {
    public View lastFocusedItem;
    public final ArrayList items = new ArrayList();
    public final Object appState =
            kotlin.LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new AppServiceProvider(this, 8));
    public final int d = 5;
    public final CatalogClickRouter clickListener = new CatalogClickRouter(this, 0);
    public final ContentClickRouter focusListener = new ContentClickRouter(this, 2);

    public final void appendItems(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        int start = this.items.size();
        java.util.HashSet<Long> knownIds = new java.util.HashSet<>();
        for (Object item : this.items) knownIds.add(((VideoModel) item).getAid());
        for (Object item : list) {
            VideoModel video = (VideoModel) item;
            if (video.getAid() == 0 || knownIds.add(video.getAid())) this.items.add(video);
        }
        int inserted = this.items.size() - start;
        if (inserted > 0) notifyItemRangeInserted(start, inserted);
    }

    public final void replaceItems(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List snapshot = new ArrayList(list);
        this.lastFocusedItem = null;
        int removed = this.items.size();
        this.items.clear();
        if (removed > 0) notifyItemRangeRemoved(0, removed);
        this.items.addAll(snapshot);
        if (!snapshot.isEmpty()) notifyItemRangeInserted(0, snapshot.size());
    }

    @Override
    public final int getItemCount() {
        return this.items.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof VideoCardViewHolder) {
            Object obj = this.items.get(i4);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            ((VideoCardViewHolder) holder).bind((VideoModel) obj);
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE = UiSupport.inflate(viewGroup, "parent", R.layout.cell_video, viewGroup, false);
        int i7 = VideoCardViewHolder.f14413y;
        Intrinsics.checkNotNull(viewE);
        return UiSupport.videoHolder(viewE, this.clickListener, this.focusListener);
    }
}
