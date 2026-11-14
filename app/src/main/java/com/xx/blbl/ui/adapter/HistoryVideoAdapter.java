package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.favorite.FolderStatModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.platform.AppServiceProvider;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.HistoryCardViewHolder;

import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

import java.util.Arrays;

public final class HistoryVideoAdapter extends PagedListAdapter {
    public final Object e =
            kotlin.LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new AppServiceProvider(this, 9));
    public final ContentClickRouter f10649f = new ContentClickRouter(this, 5);

    @Override
    public final ViewHolder createItemHolder(ViewGroup viewGroup, int i4) {
        View viewE = UiSupport.inflate(viewGroup, "parent", R.layout.cell_video, viewGroup, false);
        int i7 = HistoryCardViewHolder.f14385v;
        Intrinsics.checkNotNull(viewE);
        ContentClickRouter onItemClick = this.f10649f;
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        return new HistoryCardViewHolder(viewE, onItemClick);
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        Unit jVar;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (!(holder instanceof HistoryCardViewHolder) || itemAt(i4) == null) {
            return;
        }
        HistoryCardViewHolder bVar = (HistoryCardViewHolder) holder;
        Object objC = itemAt(i4);
        Intrinsics.checkNotNull(objC);
        HistoryVideoModel historyVideoModel = (HistoryVideoModel) objC;
        bVar.f14387b.setText(historyVideoModel.getTitle());
        bVar.f14388c.setText(
                String.format(
                        "收藏于：%s",
                        Arrays.copyOf(
                                new Object[] {DisplayFormat.g(historyVideoModel.getFav_time())},
                                1)));
        Object obj = ImageLoader.f736a;
        String cover = historyVideoModel.getCover();
        AppCompatImageView imageView = bVar.f14386a;
        Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
        ImageLoader.loadThumbnail(cover, imageView);
        FolderStatModel cnt_info = historyVideoModel.getCnt_info();
        AppCompatImageView appCompatImageView = bVar.f14390p;
        AppCompatImageView appCompatImageView2 = bVar.f14389f;
        AppCompatTextView appCompatTextView = bVar.e;
        AppCompatTextView appCompatTextView2 = bVar.d;
        if (cnt_info != null) {
            appCompatImageView2.setVisibility(0);
            appCompatImageView.setVisibility(0);
            appCompatTextView2.setVisibility(0);
            appCompatTextView.setVisibility(0);
            appCompatTextView2.setText(DisplayFormat.f(cnt_info.getPlay()));
            appCompatTextView.setText(DisplayFormat.f(cnt_info.getDanmaku()));
            jVar = Unit.INSTANCE;
        } else {
            jVar = null;
        }
        if (jVar == null) {
            appCompatImageView2.setVisibility(8);
            appCompatImageView.setVisibility(8);
            appCompatTextView2.setVisibility(8);
            appCompatTextView.setVisibility(8);
        }
    }
}
