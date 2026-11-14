package com.xx.blbl.ui.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.live.LiveRoomItem;
import com.xx.blbl.model.search.SearchItemModel;
import com.xx.blbl.model.series.BadgeInfoModel;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.model.user.UserInfoModel;
import com.xx.blbl.model.video.channel.ChannelVideoModel;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterL;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.ui.holder.LiveRoomCardViewHolder;
import com.xx.blbl.ui.holder.SeriesCardViewHolder;
import com.xx.blbl.ui.holder.UserCardViewHolder;
import com.xx.blbl.ui.holder.VideoCardViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.List;

public final class CatalogAdapter extends PagedListAdapter {
    public final /* synthetic */ int e;
    public final ItemClickCallback f10332f;
    public final ItemFocusCallback f10333p;

    public CatalogAdapter(int i4) {
        this.e = i4;
        switch (i4) {
            case 1:
                this.f10332f = new ItemRouterT(this, 29);
                this.f10333p = new ContentClickRouter(this, 1);
                break;
            case 2:
                this.f10332f = new CatalogClickRouter(this, 4);
                this.f10333p = null; // Original J2/y.r is an empty focus callback.
                break;
            case 3:
                this.f10332f = new CatalogClickRouter(this, 6);
                this.f10333p = new ContentClickRouter(this, 9);
                break;
            case 4:
                this.f10332f = new CatalogClickRouter(this, 8);
                this.f10333p = new ContentClickRouter(this, 11);
                break;
            case 5:
                this.f10332f = new CatalogClickRouter(this, 12);
                this.f10333p = new ContentClickRouter(this, 14);
                break;
            case 6:
                this.f10332f = new ContentClickRouter(this, 20);
                this.f10333p = new CatalogClickRouter(this, 18);
                break;
            default:
                this.f10332f = new ItemRouterL(this, 24);
                this.f10333p = new ItemRouterT(this, 21);
                break;
        }
    }

    @Override
    public final ViewHolder createItemHolder(ViewGroup viewGroup, int i4) {
        ItemFocusCallback bVar = this.f10333p;
        ItemClickCallback cVar = this.f10332f;
        switch (this.e) {
            case 0:
                View viewE =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_movie, viewGroup, false);
                int i7 = SeriesCardViewHolder.e;
                Intrinsics.checkNotNull(viewE);
                return UiSupport.seriesHolder(viewE, (ItemRouterL) cVar, (ItemRouterT) bVar);
            case 1:
                View viewE2 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_video, viewGroup, false);
                int i8 = VideoCardViewHolder.f14413y;
                Intrinsics.checkNotNull(viewE2);
                return UiSupport.videoHolder(viewE2, (ItemRouterT) cVar, (ContentClickRouter) bVar);
            case 2:
                View viewE3 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_video, viewGroup, false);
                int i9 = LiveRoomCardViewHolder.f14515p;
                Intrinsics.checkNotNull(viewE3);
                CatalogClickRouter onItemClick = (CatalogClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
                return new LiveRoomCardViewHolder(viewE3, onItemClick, bVar);
            case 3:
                View viewE4 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_movie, viewGroup, false);
                int i10 = SeriesCardViewHolder.e;
                Intrinsics.checkNotNull(viewE4);
                return UiSupport.seriesHolder(
                        viewE4, (CatalogClickRouter) cVar, (ContentClickRouter) bVar);
            case 4:
                View viewE5 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_user, viewGroup, false);
                int i11 = UserCardViewHolder.e;
                Intrinsics.checkNotNull(viewE5);
                CatalogClickRouter onItemClickListener = (CatalogClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
                ContentClickRouter onFocusListener = (ContentClickRouter) bVar;
                Intrinsics.checkNotNullParameter(onFocusListener, "onFocusListener");
                return new UserCardViewHolder(viewE5, onItemClickListener, onFocusListener);
            case 5:
                View viewE6 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_movie, viewGroup, false);
                int i12 = SeriesCardViewHolder.e;
                Intrinsics.checkNotNull(viewE6);
                return UiSupport.seriesHolder(
                        viewE6, (CatalogClickRouter) cVar, (ContentClickRouter) bVar);
            default:
                View viewE7 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_user, viewGroup, false);
                int i13 = UserCardViewHolder.e;
                Intrinsics.checkNotNull(viewE7);
                ContentClickRouter onItemClickListener2 = (ContentClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClickListener2, "onItemClickListener");
                CatalogClickRouter onFocusListener2 = (CatalogClickRouter) bVar;
                Intrinsics.checkNotNullParameter(onFocusListener2, "onFocusListener");
                return new UserCardViewHolder(viewE7, onItemClickListener2, onFocusListener2);
        }
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        List<BadgeInfoModel> badges;
        Drawable drawableI;
        UserInfoModel userInfoModel;
        int i7 = this.e;
        Intrinsics.checkNotNullParameter(holder, "holder");
        switch (i7) {
            case 0:
                if ((holder instanceof SeriesCardViewHolder) && itemAt(i4) != null) {
                    Object objC = itemAt(i4);
                    Intrinsics.checkNotNull(objC);
                    ((SeriesCardViewHolder) holder).bindSeries((SeriesModel) objC);
                    break;
                }
                break;
            case 1:
                if ((holder instanceof VideoCardViewHolder) && itemAt(i4) != null) {
                    VideoCardViewHolder jVar = (VideoCardViewHolder) holder;
                    Object objC2 = itemAt(i4);
                    Intrinsics.checkNotNull(objC2);
                    ChannelVideoModel channelVideoModel = (ChannelVideoModel) objC2;
                    jVar.f14420w.setText(channelVideoModel.getDuration());
                    jVar.d.setText(channelVideoModel.getAuthor_name());
                    jVar.f14416c.setVisibility(0);
                    jVar.e.setText(channelVideoModel.getView_count());
                    jVar.f14418p.setText(DisplayFormat.f(channelVideoModel.getDanmaku()));
                    jVar.f14415b.setText(channelVideoModel.getName());
                    Object obj = ImageLoader.f736a;
                    String cover = channelVideoModel.getCover();
                    AppCompatImageView imageView = jVar.f14414a;
                    Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
                    ImageLoader.loadThumbnail(cover, imageView);
                    break;
                }
                break;
            case 2:
                if ((holder instanceof LiveRoomCardViewHolder) && itemAt(i4) != null) {
                    LiveRoomCardViewHolder bVar = (LiveRoomCardViewHolder) holder;
                    Object objC3 = itemAt(i4);
                    Intrinsics.checkNotNull(objC3);
                    LiveRoomItem liveRoomItem = (LiveRoomItem) objC3;
                    Object obj2 = ImageLoader.f736a;
                    String cover2 = liveRoomItem.getCover();
                    AppCompatImageView imageView2 = bVar.f14516a;
                    Intrinsics.checkNotNullExpressionValue(imageView2, "imageView");
                    ImageLoader.loadThumbnail(cover2, imageView2);
                    bVar.f14517b.setText(liveRoomItem.getTitle());
                    boolean zIsEmpty = TextUtils.isEmpty(liveRoomItem.getUname());
                    AppCompatImageView appCompatImageView = bVar.f14518c;
                    AppCompatTextView appCompatTextView = bVar.d;
                    if (zIsEmpty) {
                        appCompatTextView.setVisibility(8);
                        appCompatImageView.setVisibility(8);
                    } else {
                        appCompatTextView.setVisibility(0);
                        appCompatImageView.setVisibility(0);
                        appCompatTextView.setText(liveRoomItem.getUname());
                    }
                    bVar.e.setText(DisplayFormat.f(liveRoomItem.getOnline()));
                    break;
                }
                break;
            case 3:
                if ((holder instanceof SeriesCardViewHolder) && itemAt(i4) != null) {
                    SeriesCardViewHolder gVar = (SeriesCardViewHolder) holder;
                    Object objC4 = itemAt(i4);
                    Intrinsics.checkNotNull(objC4);
                    SearchItemModel searchItemModel = (SearchItemModel) objC4;
                    Object obj3 = ImageLoader.f736a;
                    String cover3 = searchItemModel.getCover();
                    AppCompatImageView imageView3 = gVar.f14405a;
                    Intrinsics.checkNotNullExpressionValue(imageView3, "imageView");
                    ImageLoader.loadPoster(cover3, imageView3);
                    gVar.f14406b.setText(Html.fromHtml(searchItemModel.getTitle()));
                    gVar.f14407c.setText(searchItemModel.getDesc());
                    List<BadgeInfoModel> badges2 = searchItemModel.getBadges();
                    AppCompatTextView appCompatTextView2 = gVar.d;
                    if (badges2 == null
                            || ((badges = searchItemModel.getBadges()) != null
                                    && badges.isEmpty())) {
                        appCompatTextView2.setVisibility(8);
                        break;
                    } else {
                        List<BadgeInfoModel> badges3 = searchItemModel.getBadges();
                        if (badges3 != null && !badges3.isEmpty()) {
                            BadgeInfoModel badgeInfoModel = badges3.get(0);
                            appCompatTextView2.setVisibility(0);
                            appCompatTextView2.setText(badgeInfoModel.getText());
                            try {
                                if (!TextUtils.isEmpty(badgeInfoModel.getBg_color_night())
                                        && (drawableI =
                                                        androidx.appcompat.content.res
                                                                .AppCompatResources.getDrawable(
                                                                appCompatTextView2.getContext(),
                                                                R.drawable.badge_background))
                                                != null) {
                                    Drawable drawableE =
                                            androidx.core.graphics.drawable.DrawableCompat.wrap(
                                                    drawableI);
                                    Intrinsics.checkNotNullExpressionValue(drawableE, "wrap(...)");
                                    drawableE.setTint(
                                            Color.parseColor(badgeInfoModel.getBg_color_night()));
                                    appCompatTextView2.setBackgroundDrawable(drawableE);
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                    }
                }
                break;
            case 4:
                if ((holder instanceof UserCardViewHolder) && itemAt(i4) != null) {
                    UserCardViewHolder eVar = (UserCardViewHolder) holder;
                    Object objC5 = itemAt(i4);
                    Intrinsics.checkNotNull(objC5);
                    SearchItemModel searchItemModel2 = (SearchItemModel) objC5;
                    Object obj4 = ImageLoader.f736a;
                    ImageLoader.loadAvatar(searchItemModel2.getUpic(), eVar.f14884b);
                    eVar.f14885c.setText(searchItemModel2.getUname());
                    eVar.d.setText(searchItemModel2.getUsign());
                    break;
                }
                break;
            case 5:
                if ((holder instanceof SeriesCardViewHolder) && itemAt(i4) != null) {
                    Object objC6 = itemAt(i4);
                    Intrinsics.checkNotNull(objC6);
                    ((SeriesCardViewHolder) holder).bindLaneItem((LaneItemModel) objC6);
                    break;
                }
                break;
            default:
                if ((holder instanceof UserCardViewHolder)
                        && (userInfoModel = (UserInfoModel) itemAt(i4)) != null) {
                    UserCardViewHolder eVar2 = (UserCardViewHolder) holder;
                    Object obj5 = ImageLoader.f736a;
                    ImageLoader.loadAvatar(userInfoModel.getFace(), eVar2.f14884b);
                    eVar2.f14885c.setText(userInfoModel.getUname());
                    eVar2.d.setText(userInfoModel.getSign());
                    break;
                }
                break;
        }
    }
}
