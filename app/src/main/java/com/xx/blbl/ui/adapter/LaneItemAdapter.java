package com.xx.blbl.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.favorite.FavoriteFolderModel;
import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.live.LiveAreaCategory;
import com.xx.blbl.model.series.timeline.SeriesTimeLineModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.ui.holder.FavoriteFolderCardViewHolder;
import com.xx.blbl.ui.holder.LiveAreaCardViewHolder;
import com.xx.blbl.ui.holder.SeriesCardViewHolder;
import com.xx.blbl.ui.holder.SeriesEpisodeCardViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LaneItemAdapter extends Adapter {
    public final /* synthetic */ int f10344a;
    public final ArrayList f10345b;
    public final ItemClickCallback f10346c;

    public LaneItemAdapter(int i4) {
        this.f10344a = i4;
        switch (i4) {
            case 1:
                this.f10345b = new ArrayList();
                this.f10346c = new CatalogClickRouter(this, 3);
                break;
            case 2:
                this.f10345b = new ArrayList();
                this.f10346c = new ContentClickRouter(this, 6);
                break;
            case 3:
                this.f10345b = new ArrayList();
                this.f10346c = new CatalogClickRouter(this, 13);
                break;
            case 4:
                this.f10345b = new ArrayList();
                this.f10346c = new CatalogClickRouter(this, 15);
                break;
            default:
                this.f10345b = new ArrayList();
                this.f10346c = new ItemRouterT(this, 26);
                break;
        }
    }

    public void a(List list) {
        ArrayList arrayList = this.f10345b;
        int size = arrayList.size();
        arrayList.clear();
        notifyItemRangeRemoved(0, size);
        arrayList.addAll(list);
        notifyItemRangeChanged(0, list.size());
    }

    @Override
    public final int getItemCount() {
        switch (this.f10344a) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return this.f10345b.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder e0Var, int i4) {
        String str;
        ArrayList arrayList = this.f10345b;
        switch (this.f10344a) {
            case 0:
                SeriesCardViewHolder holder = (SeriesCardViewHolder) e0Var;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(holder, "holder");
                Object obj = arrayList.get(i4);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                holder.bindLaneItem((LaneItemModel) obj);
                break;
            case 1:
                FavoriteFolderCardViewHolder holder2 = (FavoriteFolderCardViewHolder) e0Var;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(holder2, "holder");
                Object obj2 = arrayList.get(i4);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                FavoriteFolderModel favoriteFolderModel = (FavoriteFolderModel) obj2;
                holder2.f14392b.setText(favoriteFolderModel.getTitle());
                holder2.f14393c.setText(
                        String.format(
                                "%d个内容",
                                Arrays.copyOf(
                                        new Object[] {
                                            Integer.valueOf(favoriteFolderModel.getMedia_count())
                                        },
                                        1)));
                if (!TextUtils.isEmpty(favoriteFolderModel.getImageUrl())) {
                    Object obj3 = ImageLoader.f736a;
                    String imageUrl = favoriteFolderModel.getImageUrl();
                    AppCompatImageView imageView = holder2.f14391a;
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                            imageView, "imageView");
                    ImageLoader.loadThumbnail(imageUrl, imageView);
                } else if (favoriteFolderModel.getMedia_count() > 0) {
                    ((NetworkManager) ((kotlin.Lazy) holder2.d).getValue())
                            .getFavoriteDetail(
                                    favoriteFolderModel.getId(),
                                    new com.xx.blbl.network.NetResultCallback<
                                            com.xx.blbl.network.response.BaseResponse<
                                                    com.xx.blbl.model.favorite
                                                            .FolderDetailModel>>() {
                                        public void onFailure(Throwable error) {}

                                        public void onResponse(
                                                com.xx.blbl.network.response.BaseResponse<
                                                                com.xx.blbl.model.favorite
                                                                        .FolderDetailModel>
                                                        response) {
                                            com.xx.blbl.model.favorite.FolderDetailModel data =
                                                    response == null ? null : response.getData();
                                            if (data == null) return;
                                            AppController.instance()
                                                    .getSharedPreferences("BLBL", 0)
                                                    .edit()
                                                    .putString(
                                                            "fav" + data.getId(), data.getCover())
                                                    .apply();
                                            ImageLoader.loadThumbnail(
                                                    data.getCover(), holder2.f14391a);
                                        }
                                    });
                }
                break;
            case 2:
                LiveAreaCardViewHolder holder3 = (LiveAreaCardViewHolder) e0Var;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(holder3, "holder");
                Object obj4 = arrayList.get(i4);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
                LiveAreaCategory liveAreaCategory = (LiveAreaCategory) obj4;
                Object obj5 = ImageLoader.f736a;
                String url = liveAreaCategory.getPic();
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(url, "url");
                AppCompatImageView imageView2 = holder3.f14513a;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(imageView2, "imageView");
                Glide.with(AppController.instance()).asDrawable().load(url).into(imageView2);
                boolean zIsEmpty = TextUtils.isEmpty(liveAreaCategory.getName());
                AppCompatTextView appCompatTextView = holder3.f14514b;
                if (!zIsEmpty) {
                    appCompatTextView.setText(liveAreaCategory.getName());
                }
                if (!TextUtils.isEmpty(liveAreaCategory.getTitle())) {
                    appCompatTextView.setText(liveAreaCategory.getTitle());
                }
                break;
            case 3:
                SeriesEpisodeCardViewHolder holder4 = (SeriesEpisodeCardViewHolder) e0Var;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(holder4, "holder");
                Object obj6 = arrayList.get(i4);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(obj6, "get(...)");
                VideoModel videoModel = (VideoModel) obj6;
                Object obj7 = ImageLoader.f736a;
                ImageLoader.loadThumbnail(videoModel.getCover(), holder4.f15028a);
                holder4.f15031f.setVisibility(8);
                holder4.f15029b.setVisibility(8);
                holder4.d.setVisibility(8);
                boolean zIsEmpty2 = TextUtils.isEmpty(videoModel.getTitle());
                AppCompatTextView appCompatTextView2 = holder4.f15030c;
                if (!zIsEmpty2) {
                    appCompatTextView2.setVisibility(0);
                    appCompatTextView2.setText(videoModel.getTitle());
                } else {
                    appCompatTextView2.setVisibility(8);
                }
                break;
            default:
                SeriesCardViewHolder holder5 = (SeriesCardViewHolder) e0Var;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(holder5, "holder");
                Object obj8 = arrayList.get(i4);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(obj8, "get(...)");
                SeriesTimeLineModel seriesTimeLineModel = (SeriesTimeLineModel) obj8;
                Object obj9 = ImageLoader.f736a;
                String cover = seriesTimeLineModel.getCover();
                AppCompatImageView imageView3 = holder5.f14405a;
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(imageView3, "imageView");
                ImageLoader.loadPoster(cover, imageView3);
                holder5.f14406b.setText(seriesTimeLineModel.getTitle());
                boolean zIsEmpty3 = TextUtils.isEmpty(seriesTimeLineModel.getDelay_reason());
                AppCompatTextView appCompatTextView3 = holder5.f14407c;
                if (zIsEmpty3) {
                    appCompatTextView3.setText(
                            appCompatTextView3
                                    .getContext()
                                    .getString(
                                            R.string.pub_to_, seriesTimeLineModel.getPub_index()));
                } else {
                    appCompatTextView3.setText(seriesTimeLineModel.getDelay_reason());
                }
                StringBuilder sb = new StringBuilder();
                switch (seriesTimeLineModel.getDayOfWeek()) {
                    case 1:
                        str = "周一";
                        break;
                    case 2:
                        str = "周二";
                        break;
                    case 3:
                        str = "周三";
                        break;
                    case 4:
                        str = "周四";
                        break;
                    case 5:
                        str = "周五";
                        break;
                    case 6:
                        str = "周六";
                        break;
                    case 7:
                        str = "周日";
                        break;
                    default:
                        str = "";
                        break;
                }
                sb.append(str);
                sb.append(' ');
                sb.append(seriesTimeLineModel.getPub_time());
                holder5.d.setText(sb.toString());
                break;
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        ItemClickCallback cVar = this.f10346c;
        switch (this.f10344a) {
            case 0:
                View viewE =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_movie, viewGroup, false);
                int i7 = SeriesCardViewHolder.e;
                kotlin.jvm.internal.Intrinsics.checkNotNull(viewE);
                ItemRouterT onItemClick = (ItemRouterT) cVar;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
                return new SeriesCardViewHolder(viewE, onItemClick, null);
            case 1:
                View viewE2 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_folder, viewGroup, false);
                int i8 = FavoriteFolderCardViewHolder.e;
                kotlin.jvm.internal.Intrinsics.checkNotNull(viewE2);
                CatalogClickRouter onItemClick2 = (CatalogClickRouter) cVar;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(onItemClick2, "onItemClick");
                return new FavoriteFolderCardViewHolder(viewE2, onItemClick2);
            case 2:
                View viewE3 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_user, viewGroup, false);
                int i9 = LiveAreaCardViewHolder.f14512c;
                kotlin.jvm.internal.Intrinsics.checkNotNull(viewE3);
                ContentClickRouter onItemClickListener = (ContentClickRouter) cVar;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                        onItemClickListener, "onItemClickListener");
                return new LiveAreaCardViewHolder(viewE3, onItemClickListener);
            case 3:
                View viewE4 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_episode, viewGroup, false);
                int i10 = SeriesEpisodeCardViewHolder.f15027p;
                kotlin.jvm.internal.Intrinsics.checkNotNull(viewE4);
                CatalogClickRouter onItemClickListener2 = (CatalogClickRouter) cVar;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                        onItemClickListener2, "onItemClickListener");
                return new SeriesEpisodeCardViewHolder(viewE4, onItemClickListener2);
            default:
                View viewE5 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_movie, viewGroup, false);
                int i11 = SeriesCardViewHolder.e;
                kotlin.jvm.internal.Intrinsics.checkNotNull(viewE5);
                CatalogClickRouter onItemClick3 = (CatalogClickRouter) cVar;
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(onItemClick3, "onItemClick");
                return new SeriesCardViewHolder(viewE5, onItemClick3, null);
        }
    }
}
