package com.xx.blbl.ui.holder;

import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.*;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.platform.VideoIdConverter;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.dialog.VideoActionPresenter;
import com.xx.blbl.ui.navigation.ScreenNavigator;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.List;

/** Video detail header and actions, using only published SDK APIs. */
public final class VideoHeaderViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    public static final int f585O = 0;
    public final AppCompatImageView f586G, f587H, d, f600x, f602z;
    public final LinearLayoutCompat f588I;
    public VideoModel f589J;
    public List f590K;
    public boolean f591L, M, f592N;
    public final View f593a;
    public final Lazy<NetworkManager> f594b = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f595c = LazyKt.lazy(AppController::state);
    public final AppCompatTextView e, f596f, f597p, f599w;
    public final FlexboxLayout f598v;
    public final AppCompatButton f601y;

    public VideoHeaderViewHolder(View view, ItemFocusCallback focus) {
        super(view);
        f593a = view;
        d = view.findViewById(R.id.image_cover);
        e = view.findViewById(R.id.text_title);
        f596f = view.findViewById(R.id.text_subtitle);
        f597p = view.findViewById(R.id.text_description);
        f598v = view.findViewById(R.id.view_flex_layout);
        f599w = view.findViewById(R.id.text_name);
        f600x = view.findViewById(R.id.image_avatar);
        f601y = view.findViewById(R.id.button_play);
        f602z = view.findViewById(R.id.button_like);
        f586G = view.findViewById(R.id.button_coin);
        f587H = view.findViewById(R.id.button_favorite);
        f588I = view.findViewById(R.id.button_uploader);
        View[] actions = {f601y, f602z, f586G, f587H, f588I};
        String[] tags = {"playVideo", "like", "coin", "favorite", "uploader"};
        for (int n = 0; n < actions.length; n++) {
            actions[n].setTag(tags[n]);
            actions[n].setOnClickListener(this);
            if (focus != null)
                actions[n].setOnFocusChangeListener(ItemEvents.focus(focus, this, false));
        }
        f586G.setOnLongClickListener(
                v -> {
                    VideoActionPresenter.coin(
                            v.getContext(), f589J, new VideoCollectionCallback(this, 2));
                    return true;
                });
        f587H.setOnLongClickListener(
                v -> {
                    VideoActionPresenter.folders(
                            v.getContext(), f589J, !M, new VideoCollectionCallback(this, 1));
                    return true;
                });
    }

    public NetworkManager network() {
        return f594b.getValue();
    }

    public void onClick(View view) {
        VideoModel video = f589J;
        if (video == null) return;
        if (view == f587H) {
            if (video.getAid() == 0) {
                if (video.getId() != 0) video.setAid(video.getId());
                else if (!TextUtils.isEmpty(video.getBvid()))
                    video.setAid(VideoIdConverter.toAid(video.getBvid()).longValue());
            }
            network()
                    .collection(
                            video.getAid(),
                            !M,
                            String.valueOf(f595c.getValue().defaultFavoriteId),
                            new VideoInteractionCallback(this, 2));
        } else if (view == f602z)
            network()
                    .like(
                            video.getAid(),
                            video.getBvid(),
                            f591L ? 2 : 1,
                            new VideoInteractionCallback(this, 3));
        else if (view == f586G) {
            if (!f592N)
                network()
                        .giveCoin(
                                video.getAid(),
                                video.getBvid(),
                                view.getContext()
                                        .getSharedPreferences("BLBL", 0)
                                        .getInt("giveCoinNumber", 2),
                                new VideoCollectionCallback(this, 2));
        } else if (view == f588I) {
            OwnerModel owner = video.getOwner();
            if (owner != null)
                ScreenNavigator.openUserSpace(
                        view.getContext(), owner.getMid(), owner.getName(), owner.getFace());
        } else if (view == f601y) {
            if (f590K != null) ScreenNavigator.openPlaylist(view.getContext(), video, f590K, false);
            else ScreenNavigator.openVideo(view.getContext(), video, false);
        }
    }
}
