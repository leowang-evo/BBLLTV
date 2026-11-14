package com.xx.blbl.ui.adapter;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.search.SearchItemModel;
import com.xx.blbl.model.video.HistoryModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.AppServiceProvider;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterL;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.ui.holder.VideoCardViewHolder;
import com.xx.blbl.ui.holder.VideoProgressCardViewHolder;

import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class VideoGridAdapter extends PagedListAdapter {
    private final VideoListUpdates updates = new VideoListUpdates(this);
    public final /* synthetic */ int e;
    public final Object f10334f;
    public final ItemClickCallback f10335p;
    public final ItemFocusCallback f10336v;

    public VideoGridAdapter(int i4) {
        this.e = i4;
        switch (i4) {
            case 1:
                this.f10334f =
                        kotlin.LazyKt.lazy(
                                LazyThreadSafetyMode.SYNCHRONIZED, new AppServiceProvider(this, 7));
                this.f10335p = new ItemRouterL(this, 28);
                this.f10336v = new ItemRouterT(this, 25);
                break;
            case 2:
                this.f10334f =
                        kotlin.LazyKt.lazy(
                                LazyThreadSafetyMode.SYNCHRONIZED,
                                new AppServiceProvider(this, 10));
                this.f10335p = new CatalogClickRouter(this, 9);
                this.f10336v = new ContentClickRouter(this, 12);
                break;
            default:
                this.f10334f =
                        kotlin.LazyKt.lazy(
                                LazyThreadSafetyMode.SYNCHRONIZED, new AppServiceProvider(this, 6));
                this.f10335p = new ItemRouterL(this, 25);
                this.f10336v = new ItemRouterT(this, 22);
                break;
        }
    }

    @Override
    public final ViewHolder createItemHolder(ViewGroup viewGroup, int i4) {
        ItemFocusCallback bVar = this.f10336v;
        ItemClickCallback cVar = this.f10335p;
        switch (this.e) {
            case 0:
                View viewE =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_video, viewGroup, false);
                int i7 = VideoCardViewHolder.f14413y;
                Intrinsics.checkNotNull(viewE);
                return UiSupport.videoHolder(viewE, (ItemRouterL) cVar, (ItemRouterT) bVar);
            case 1:
                View viewE2 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_video, viewGroup, false);
                int i8 = VideoProgressCardViewHolder.f14397f;
                Intrinsics.checkNotNull(viewE2);
                ItemRouterL onItemClick = (ItemRouterL) cVar;
                Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
                return new VideoProgressCardViewHolder(viewE2, onItemClick, (ItemRouterT) bVar);
            default:
                View viewE3 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_video, viewGroup, false);
                int i9 = VideoCardViewHolder.f14413y;
                Intrinsics.checkNotNull(viewE3);
                return UiSupport.videoHolder(
                        viewE3, (CatalogClickRouter) cVar, (ContentClickRouter) bVar);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        View cover = holder.itemView.findViewById(R.id.imageView);
        if (cover instanceof android.widget.ImageView)
            com.bumptech.glide.Glide.with(holder.itemView.getContext().getApplicationContext())
                    .clear((android.widget.ImageView) cover);
        holder.itemView.animate().cancel();
        holder.itemView.setScaleX(1f);
        holder.itemView.setScaleY(1f);
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, java.util.List<Object> payloads) {
        if (e == 0 && !payloads.isEmpty() && holder instanceof VideoCardViewHolder) {
            ((VideoCardViewHolder) holder).bindText((VideoModel) items.get(position));
        } else super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void replaceItems(List data) {
        if (e == 0) { updates.replace(data); return; }
        switch (this.e) {
            case 2:
                Intrinsics.checkNotNullParameter(data, "data");
                Iterator it = data.iterator();
                while (it.hasNext()) {
                    SearchItemModel searchItemModel = (SearchItemModel) it.next();
                    if (searchItemModel.getRoomid() != 0) {
                        searchItemModel.setArcurl(
                                "https://live.bilibili.com/" + searchItemModel.getRoomid());
                        searchItemModel.setAid(searchItemModel.getRoomid());
                        searchItemModel.setAuthor(searchItemModel.getUname());
                    }
                }
                super.replaceItems(data);
                break;
            default:
                super.replaceItems(data);
                break;
        }
    }

    @Override public void appendItems(List values) {
        if (e == 0) updates.append(values); else super.appendItems(values);
    }
    @Override public void onAttachedToRecyclerView(androidx.recyclerview.widget.RecyclerView view) {
        super.onAttachedToRecyclerView(view);
        if (e == 0) updates.attach(view);
    }
    @Override public void onDetachedFromRecyclerView(androidx.recyclerview.widget.RecyclerView view) {
        if (e == 0) updates.cancel();
        super.onDetachedFromRecyclerView(view);
    }
    @Override public void rememberFocusedItem(View view, int position) {
        if (e == 0) updates.remember(view, position); else super.rememberFocusedItem(view, position);
    }
    @Override public void restoreFocus() { if (e == 0) updates.restoreFocus(); else super.restoreFocus(); }
    @Override public void clearRememberedFocus() {
        super.clearRememberedFocus();
        if (e == 0) updates.clearRememberedFocus();
    }
    @Override public void resetPositionOnNextUpdate() { if (e == 0) updates.resetOnNextUpdate(); }
    @Override public void cancelPendingUpdates() { if (e == 0) updates.cancel(); }
    @Override public android.os.Parcelable captureViewportState(androidx.recyclerview.widget.LinearLayoutManager layout) {
        return e == 0 ? updates.capture(true) : super.captureViewportState(layout);
    }
    @Override public void restoreViewportState(android.os.Parcelable state, androidx.recyclerview.widget.LinearLayoutManager layout) {
        if (e == 0) updates.restore(state); else super.restoreViewportState(state, layout);
    }

    public void f(long j7, long j8, long j9) {
        ArrayList<HistoryVideoModel> arrayList = this.items;
        int i4 = 0;
        for (HistoryVideoModel historyVideoModel : arrayList) {
            int i7 = i4 + 1;
            HistoryModel history = historyVideoModel.getHistory();
            if (history != null && history.getOid() == j7) {
                HistoryModel history2 = historyVideoModel.getHistory();
                if (history2 != null) {
                    history2.setCid(j8);
                }
                historyVideoModel.setProgress(j9 / ((long) 1000));
                arrayList.remove(i4);
                arrayList.add(0, historyVideoModel);
                notifyItemMoved(i4, 0);
                notifyItemChanged(0);
                return;
            }
            i4 = i7;
        }
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        String str;
        int i7 = this.e;
        Intrinsics.checkNotNullParameter(holder, "holder");
        switch (i7) {
            case 0:
                if ((holder instanceof VideoCardViewHolder) && itemAt(i4) != null) {
                    Object objC = itemAt(i4);
                    Intrinsics.checkNotNull(objC);
                    ((VideoCardViewHolder) holder).bind((VideoModel) objC);
                    break;
                }
                break;
            case 1:
                if ((holder instanceof VideoProgressCardViewHolder) && itemAt(i4) != null) {
                    VideoProgressCardViewHolder eVar = (VideoProgressCardViewHolder) holder;
                    Object objC2 = itemAt(i4);
                    Intrinsics.checkNotNull(objC2);
                    HistoryVideoModel historyVideoModel = (HistoryVideoModel) objC2;
                    eVar.f14399b.setText(historyVideoModel.getTitle());
                    int duration = (int) historyVideoModel.getDuration();
                    ProgressBar progressBar = eVar.f14400c;
                    progressBar.setMax(duration);
                    progressBar.setProgress((int) historyVideoModel.getProgress());
                    HistoryModel history = historyVideoModel.getHistory();
                    boolean zA =
                            Intrinsics.areEqual(
                                    history != null ? history.getBusiness() : null, "live");
                    AppCompatTextView appCompatTextView = eVar.e;
                    if (zA) {
                        appCompatTextView.setText(historyVideoModel.getBadge());
                    } else {
                        appCompatTextView.setText(
                                String.format(
                                        "%s/%s",
                                        Arrays.copyOf(
                                                new Object[] {
                                                    DisplayFormat.h(
                                                            historyVideoModel.getProgress()),
                                                    DisplayFormat.h(historyVideoModel.getDuration())
                                                },
                                                2)));
                    }
                    long j7 = 1000;
                    Date date = new Date(historyVideoModel.getView_at() * j7);
                    Date date2 = new Date();
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long time = ((date2.getTime() - date.getTime()) / j7) / ((long) 86400);
                    if (time == 0) {
                        str =
                                String.format(
                                        "今天 %s",
                                        Arrays.copyOf(
                                                new Object[] {
                                                    new SimpleDateFormat("HH:mm", Locale.CHINA)
                                                            .format(date)
                                                },
                                                1));
                    } else if (time == 1) {
                        str =
                                String.format(
                                        "昨天 %s",
                                        Arrays.copyOf(
                                                new Object[] {
                                                    new SimpleDateFormat("HH:mm", Locale.CHINA)
                                                            .format(date)
                                                },
                                                1));
                    } else if (time == 2) {
                        str =
                                String.format(
                                        "前天 %s",
                                        Arrays.copyOf(
                                                new Object[] {
                                                    new SimpleDateFormat("HH:mm", Locale.CHINA)
                                                            .format(date)
                                                },
                                                1));
                    } else if (time > 365) {
                        str = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    } else {
                        str = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(date);
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    }
                    eVar.d.setText(str);
                    Object obj = ImageLoader.f736a;
                    String cover = historyVideoModel.getCover();
                    AppCompatImageView imageView = eVar.f14398a;
                    Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
                    ImageLoader.loadThumbnail(cover, imageView);
                    break;
                }
                break;
            default:
                if ((holder instanceof VideoCardViewHolder) && itemAt(i4) != null) {
                    VideoCardViewHolder jVar = (VideoCardViewHolder) holder;
                    Object objC3 = itemAt(i4);
                    Intrinsics.checkNotNull(objC3);
                    SearchItemModel searchItemModel = (SearchItemModel) objC3;
                    jVar.f14420w.setText(searchItemModel.getDuration());
                    boolean zIsEmpty = TextUtils.isEmpty(searchItemModel.getAuthor());
                    AppCompatTextView appCompatTextView2 = jVar.d;
                    if (!zIsEmpty) {
                        appCompatTextView2.setText(
                                String.format(
                                        "%s · %s",
                                        Arrays.copyOf(
                                                new Object[] {
                                                    searchItemModel.getAuthor(),
                                                    DisplayFormat.g(searchItemModel.getPubdate())
                                                },
                                                2)));
                    } else if (!TextUtils.isEmpty(searchItemModel.getUname())) {
                        appCompatTextView2.setText(searchItemModel.getUname());
                    }
                    long play = searchItemModel.getPlay();
                    AppCompatImageView appCompatImageView = jVar.f14417f;
                    AppCompatTextView appCompatTextView3 = jVar.e;
                    if (play != 0) {
                        appCompatTextView3.setText(DisplayFormat.f(searchItemModel.getPlay()));
                        appCompatTextView3.setVisibility(0);
                        appCompatImageView.setVisibility(0);
                    } else if (searchItemModel.getOnline() != 0) {
                        appCompatTextView3.setVisibility(0);
                        appCompatImageView.setVisibility(0);
                        appCompatTextView3.setText(DisplayFormat.f(searchItemModel.getOnline()));
                    } else {
                        appCompatTextView3.setVisibility(8);
                        appCompatImageView.setVisibility(8);
                    }
                    long danmaku = searchItemModel.getDanmaku();
                    AppCompatImageView appCompatImageView2 = jVar.f14419v;
                    AppCompatTextView appCompatTextView4 = jVar.f14418p;
                    if (danmaku == 0) {
                        appCompatTextView4.setVisibility(8);
                        appCompatImageView2.setVisibility(8);
                    } else {
                        appCompatTextView4.setVisibility(0);
                        appCompatImageView2.setVisibility(0);
                        appCompatTextView4.setText(DisplayFormat.f(searchItemModel.getDanmaku()));
                    }
                    jVar.f14415b.setText(Html.fromHtml(searchItemModel.getTitle()));
                    boolean zIsEmpty2 = TextUtils.isEmpty(searchItemModel.getPic());
                    AppCompatImageView imageView2 = jVar.f14414a;
                    if (!zIsEmpty2) {
                        Object obj2 = ImageLoader.f736a;
                        String pic = searchItemModel.getPic();
                        Intrinsics.checkNotNullExpressionValue(imageView2, "imageView");
                        ImageLoader.loadThumbnail(pic, imageView2);
                    } else if (!TextUtils.isEmpty(searchItemModel.getCover())) {
                        Object obj3 = ImageLoader.f736a;
                        String cover2 = searchItemModel.getCover();
                        Intrinsics.checkNotNullExpressionValue(imageView2, "imageView");
                        ImageLoader.loadThumbnail(cover2, imageView2);
                    }
                    break;
                }
                break;
        }
    }
}
