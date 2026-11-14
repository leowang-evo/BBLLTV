package com.xx.blbl.recovery;

import android.view.View;

import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.channel.ChannelVideoModel;
import com.xx.blbl.platform.VideoIdConverter;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.EpisodeListAdapter;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.LaneItemAdapter;
import com.xx.blbl.ui.adapter.RelatedVideosAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.navigation.ScreenNavigator;

/** Application-only click/focus dispatch extracted from the former R8-merged Core class. */
public final class ItemRouterT implements ItemClickCallback, ItemFocusCallback {
    public final int f4629a;
    public Object f4630b;

    public ItemRouterT(Object target, int variant) {
        f4630b = target;
        f4629a = variant;
    }

    @Override
    public void onClick(View view, int position) {
        switch (f4629a) {
            case 23:
            case 24:
                {
                    EpisodeListAdapter adapter = (EpisodeListAdapter) f4630b;
                    if (adapter.e != null) adapter.e.onClick(view, position);
                    int old = adapter.f10339c;
                    adapter.f10339c = position;
                    adapter.notifyItemChanged(old);
                    adapter.notifyItemChanged(position);
                    return;
                }
            case 26:
                {
                    LaneItemModel model =
                            (LaneItemModel) ((LaneItemAdapter) f4630b).f10345b.get(position);
                    ScreenNavigator.openSeries(
                            view == null ? null : view.getContext(),
                            model.getSeason_id(),
                            null,
                            model.getTitle());
                    return;
                }
            case 27:
                {
                    ItemClickCallback listener = ((RelatedVideosAdapter) f4630b).f10351b;
                    if (listener != null) listener.onClick(view, position);
                    return;
                }
            case 29:
                {
                    ChannelVideoModel channel =
                            (ChannelVideoModel) ((CatalogAdapter) f4630b).itemAt(position);
                    if (channel == null) return;
                    VideoModel model = new VideoModel();
                    model.setBvid(channel.getBvid());
                    model.setAid(VideoIdConverter.toAid(channel.getBvid()));
                    model.setPic(channel.getCover());
                    model.setTitle(channel.getName());
                    OwnerModel owner = new OwnerModel();
                    owner.setMid(channel.getAuthor_id());
                    owner.setName(channel.getAuthor_name());
                    model.setOwner(owner);
                    ScreenNavigator.openVideo(view == null ? null : view.getContext(), model, true);
                    return;
                }
            default:
                throw new IllegalStateException("Not an application click route: " + f4629a);
        }
    }

    @Override
    public void onFocusChange(View view, int position, boolean focused) {
        if (f4629a == 21) ((CatalogAdapter) f4630b).lastFocusedItem = view;
        else if (f4629a == 22 || f4629a == 25) ((VideoGridAdapter) f4630b).rememberFocusedItem(view, position);
        else if (f4630b instanceof ItemFocusCallback)
            ((ItemFocusCallback) f4630b).onFocusChange(view, position, focused);
        else throw new IllegalStateException("Not an application focus route: " + f4629a);
    }
}
