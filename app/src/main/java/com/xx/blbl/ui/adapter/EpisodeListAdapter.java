package com.xx.blbl.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.series.UgcEpisodeModel;
import com.xx.blbl.model.video.VideoPvModel;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterL;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.ui.holder.EpisodeChoiceViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.Arrays;

public final class EpisodeListAdapter extends Adapter {
    public static final int SERIES = 0, PARTS = 1, COLLECTION = 2;
    public final /* synthetic */ int f10337a;
    public final ArrayList f10338b;
    public int f10339c;
    public LinearLayoutCompat d;
    public ItemClickCallback e;
    public final ItemClickCallback f10340f;

    public EpisodeListAdapter(int i4) {
        this.f10337a = i4;
        switch (i4) {
            case 1:
                this.f10338b = new ArrayList();
                this.f10340f = new ItemRouterL(this, 26);
                break;
            case 2:
                this.f10338b = new ArrayList();
                this.f10340f = new ItemRouterT(this, 24);
                break;
            default:
                this.f10338b = new ArrayList();
                this.f10340f = new ItemRouterT(this, 23);
                break;
        }
    }

    @Override
    public final int getItemCount() {
        switch (this.f10337a) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f10338b.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder e0Var, int i4) {
        boolean z7;
        ArrayList arrayList = this.f10338b;
        switch (this.f10337a) {
            case 0:
                EpisodeChoiceViewHolder holder = (EpisodeChoiceViewHolder) e0Var;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Object obj = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                EpisodeModel episodeModel = (EpisodeModel) obj;
                boolean z8 = this.f10339c == i4;
                holder.f14382a.setVisibility(z8 ? 0 : 8);
                boolean zIsEmpty = TextUtils.isEmpty(episodeModel.getBadge());
                AppCompatTextView appCompatTextView = holder.f14383b;
                if (zIsEmpty) {
                    appCompatTextView.setText(
                            String.format(
                                    "%d、%s",
                                    Arrays.copyOf(
                                            new Object[] {
                                                Integer.valueOf(
                                                        holder.getBindingAdapterPosition() + 1),
                                                episodeModel.getLong_title()
                                            },
                                            2)));
                } else {
                    appCompatTextView.setText(
                            String.format(
                                    "%d、(%s)%s",
                                    Arrays.copyOf(
                                            new Object[] {
                                                Integer.valueOf(
                                                        holder.getBindingAdapterPosition() + 1),
                                                episodeModel.getBadge(),
                                                episodeModel.getLong_title()
                                            },
                                            3)));
                }
                LinearLayoutCompat linearLayoutCompat = holder.f14384c;
                linearLayoutCompat.setSelected(z8);
                if (this.f10339c == i4) {
                    this.d = linearLayoutCompat;
                }
                break;
            case 1:
                EpisodeChoiceViewHolder holder2 = (EpisodeChoiceViewHolder) e0Var;
                Intrinsics.checkNotNullParameter(holder2, "holder");
                Object obj2 = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                VideoPvModel videoPvModel = (VideoPvModel) obj2;
                z7 = this.f10339c == i4;
                holder2.f14382a.setVisibility(z7 ? 0 : 8);
                holder2.f14383b.setText(videoPvModel.getPart());
                LinearLayoutCompat linearLayoutCompat2 = holder2.f14384c;
                linearLayoutCompat2.setSelected(z7);
                if (this.f10339c == i4) {
                    this.d = linearLayoutCompat2;
                }
                break;
            default:
                EpisodeChoiceViewHolder holder3 = (EpisodeChoiceViewHolder) e0Var;
                Intrinsics.checkNotNullParameter(holder3, "holder");
                Object obj3 = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                UgcEpisodeModel ugcEpisodeModel = (UgcEpisodeModel) obj3;
                z7 = this.f10339c == i4;
                holder3.f14382a.setVisibility(z7 ? 0 : 8);
                holder3.f14383b.setText(ugcEpisodeModel.getTitle());
                LinearLayoutCompat linearLayoutCompat3 = holder3.f14384c;
                linearLayoutCompat3.setSelected(z7);
                if (this.f10339c == i4) {
                    this.d = linearLayoutCompat3;
                }
                break;
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        ItemClickCallback cVar = this.f10340f;
        switch (this.f10337a) {
            case 0:
                View viewE =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_episode_list, viewGroup, false);
                int i7 = EpisodeChoiceViewHolder.d;
                Intrinsics.checkNotNull(viewE);
                return UiSupport.episodeHolder(viewE, (ItemRouterT) cVar);
            case 1:
                View viewE2 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_episode_list, viewGroup, false);
                int i8 = EpisodeChoiceViewHolder.d;
                Intrinsics.checkNotNull(viewE2);
                return UiSupport.episodeHolder(viewE2, (ItemRouterL) cVar);
            default:
                View viewE3 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_episode_list, viewGroup, false);
                int i9 = EpisodeChoiceViewHolder.d;
                Intrinsics.checkNotNull(viewE3);
                return UiSupport.episodeHolder(viewE3, (ItemRouterT) cVar);
        }
    }
}
