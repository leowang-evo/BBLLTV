package com.xx.blbl.recovery;

import android.content.Context;
import android.view.View;

import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.DynamicFeedAdapter;
import com.xx.blbl.ui.adapter.EpisodeListAdapter;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.PlayerOptionAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.navigation.ScreenNavigator;

import java.util.ArrayList;

/** Application-only item routes extracted from the former R8-merged AppCompat class. */
public final class ItemRouterL implements ItemClickCallback {
    public final int f4389a;
    public final Object f4390b;

    public ItemRouterL(Object target, int variant) {
        f4390b = target;
        f4389a = variant;
    }

    @Override
    public void onClick(View view, int position) {
        Context context = view == null ? null : view.getContext();
        switch (f4389a) {
            case 24:
                {
                    SeriesModel model = (SeriesModel) ((CatalogAdapter) f4390b).itemAt(position);
                    if (model != null)
                        ScreenNavigator.openSeries(
                                context, model.getSeason_id(), null, model.getTitle());
                    return;
                }
            case 25:
                {
                    VideoGridAdapter adapter = (VideoGridAdapter) f4390b;
                    VideoModel model = (VideoModel) adapter.itemAt(position);
                    if (model == null) return;
                    if (((AppState) ((kotlin.Lazy) adapter.f10334f).getValue()).continuationMode
                                    == ContinuationPlayEnum.CurrentList.getOrder()
                            && position + 1 < adapter.items.size()) {
                        ScreenNavigator.openPlaylist(
                                context,
                                model,
                                new ArrayList(
                                        adapter.items.subList(position + 1, adapter.items.size())),
                                true);
                        return;
                    }
                    ScreenNavigator.openVideo(context, model, true);
                    return;
                }
            case 26:
                {
                    EpisodeListAdapter adapter = (EpisodeListAdapter) f4390b;
                    if (adapter.e != null) adapter.e.onClick(view, position);
                    int old = adapter.f10339c;
                    adapter.f10339c = position;
                    adapter.notifyItemChanged(old);
                    adapter.notifyItemChanged(position);
                    return;
                }
            case 27:
                {
                    DynamicFeedAdapter adapter = (DynamicFeedAdapter) f4390b;
                    int old = adapter.f10343c;
                    adapter.f10343c = position;
                    adapter.notifyItemChanged(old);
                    adapter.notifyItemChanged(position);
                    if (adapter.d != null) adapter.d.onClick(view, position);
                    return;
                }
            case 28:
                {
                    VideoGridAdapter adapter = (VideoGridAdapter) f4390b;
                    HistoryVideoModel model = (HistoryVideoModel) adapter.itemAt(position);
                    if (model == null) return;
                    if (((AppState) ((kotlin.Lazy) adapter.f10334f).getValue()).continuationMode
                                    == ContinuationPlayEnum.CurrentList.getOrder()
                            && position + 1 < adapter.items.size()) {
                        ScreenNavigator.openHistoryPlaylist(
                                context,
                                model,
                                new ArrayList(
                                        adapter.items.subList(position + 1, adapter.items.size())));
                        return;
                    }
                    ScreenNavigator.openHistoryVideo(context, model);
                    return;
                }
            case 29:
                {
                    ItemClickCallback listener = ((PlayerOptionAdapter) f4390b).f10348b;
                    if (listener != null) listener.onClick(view, position);
                    return;
                }
            default:
                throw new IllegalStateException("Not an application item route: " + f4389a);
        }
    }
}
