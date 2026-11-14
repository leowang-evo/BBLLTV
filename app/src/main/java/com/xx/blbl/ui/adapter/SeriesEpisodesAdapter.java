package com.xx.blbl.ui.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.series.BadgeInfoModel;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.SeriesEpisodeCardViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

public final class SeriesEpisodesAdapter extends Adapter {
    public boolean f11056b;
    public final ArrayList f11055a = new ArrayList();
    public final ContentClickRouter f11057c = new ContentClickRouter(this, 15);

    @Override
    public final int getItemCount() {
        return this.f11055a.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder e0Var, int i4) {
        Drawable drawableI;
        SeriesEpisodeCardViewHolder holder = (SeriesEpisodeCardViewHolder) e0Var;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Object obj = this.f11055a.get(i4);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        EpisodeModel episodeModel = (EpisodeModel) obj;
        Object obj2 = ImageLoader.f736a;
        ImageLoader.loadThumbnail(episodeModel.getCover(), holder.f15028a);
        holder.f15029b.setText(episodeModel.getTitle());
        BadgeInfoModel badge_info = episodeModel.getBadge_info();
        AppCompatTextView appCompatTextView = holder.d;
        if (badge_info == null || TextUtils.isEmpty(episodeModel.getBadge())) {
            appCompatTextView.setVisibility(8);
        } else {
            appCompatTextView.setVisibility(0);
            appCompatTextView.setText(episodeModel.getBadge());
            try {
                BadgeInfoModel badge_info2 = episodeModel.getBadge_info();
                if (!TextUtils.isEmpty(badge_info2 != null ? badge_info2.getBg_color_night() : null)
                        && (drawableI =
                                        androidx.appcompat.content.res.AppCompatResources
                                                .getDrawable(
                                                        appCompatTextView.getContext(),
                                                        R.drawable.badge_background))
                                != null) {
                    Drawable drawableE =
                            androidx.core.graphics.drawable.DrawableCompat.wrap(drawableI);
                    Intrinsics.checkNotNullExpressionValue(drawableE, "wrap(...)");
                    BadgeInfoModel badge_info3 = episodeModel.getBadge_info();
                    drawableE.setTint(
                            Color.parseColor(
                                    badge_info3 != null ? badge_info3.getBg_color_night() : null));
                    appCompatTextView.setBackgroundDrawable(drawableE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        boolean zIsEmpty = TextUtils.isEmpty(episodeModel.getLong_title());
        AppCompatTextView appCompatTextView2 = holder.f15030c;
        if (zIsEmpty) {
            appCompatTextView2.setVisibility(8);
        } else {
            appCompatTextView2.setVisibility(0);
            appCompatTextView2.setText(episodeModel.getLong_title());
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE =
                UiSupport.inflate(viewGroup, "parent", R.layout.cell_episode, viewGroup, false);
        int i7 = SeriesEpisodeCardViewHolder.f15027p;
        Intrinsics.checkNotNull(viewE);
        ContentClickRouter onItemClickListener = this.f11057c;
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        return new SeriesEpisodeCardViewHolder(viewE, onItemClickListener);
    }
}
