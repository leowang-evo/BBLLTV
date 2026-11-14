package com.xx.blbl.ui.holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.series.BadgeInfoModel;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ItemFocusListener;

public final class SeriesCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int e = 0;
    public final AppCompatImageView f14405a;
    public final AppCompatTextView f14406b;
    public final AppCompatTextView f14407c;
    public final AppCompatTextView d;

    public SeriesCardViewHolder(View view, ItemClickCallback cVar, ItemFocusCallback bVar) {
        super(view);
        this.f14405a = (AppCompatImageView) view.findViewById(R.id.imageView);
        this.f14406b = (AppCompatTextView) view.findViewById(R.id.textView);
        this.f14407c = (AppCompatTextView) view.findViewById(R.id.text_sub);
        this.d = (AppCompatTextView) view.findViewById(R.id.text_badge);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.click_view);
        constraintLayout.setOnClickListener(ItemEvents.click(cVar, this));
        constraintLayout.setOnFocusChangeListener(ItemEvents.selected(this.f14406b));
        if (bVar != null) {
            constraintLayout.setOnFocusChangeListener(new ItemFocusListener(3, bVar, this));
        }
    }

    public final void bindLaneItem(LaneItemModel laneItemModel) {
        Drawable drawableI;
        Object obj = ImageLoader.f736a;
        String cover = laneItemModel.getCover();
        AppCompatImageView imageView = this.f14405a;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
        ImageLoader.loadPoster(cover, imageView);
        this.f14406b.setText(laneItemModel.getTitle());
        boolean zIsEmpty = TextUtils.isEmpty(laneItemModel.getDesc());
        AppCompatTextView appCompatTextView = this.f14407c;
        if (!zIsEmpty) {
            appCompatTextView.setText(laneItemModel.getDesc());
            appCompatTextView.setVisibility(0);
        } else if (TextUtils.isEmpty(laneItemModel.getSubTitle())) {
            appCompatTextView.setVisibility(8);
        } else {
            appCompatTextView.setText(laneItemModel.getSubTitle());
            appCompatTextView.setVisibility(0);
        }
        BadgeInfoModel badge_info = laneItemModel.getBadge_info();
        AppCompatTextView appCompatTextView2 = this.d;
        if (badge_info == null) {
            appCompatTextView2.setVisibility(8);
            return;
        }
        appCompatTextView2.setVisibility(0);
        BadgeInfoModel badge_info2 = laneItemModel.getBadge_info();
        kotlin.jvm.internal.Intrinsics.checkNotNull(badge_info2);
        appCompatTextView2.setText(badge_info2.getText());
        try {
            BadgeInfoModel badge_info3 = laneItemModel.getBadge_info();
            if (TextUtils.isEmpty(badge_info3 != null ? badge_info3.getBg_color_night() : null)
                    || (drawableI =
                                    androidx.appcompat.content.res.AppCompatResources.getDrawable(
                                            appCompatTextView2.getContext(),
                                            R.drawable.badge_background))
                            == null) {
                return;
            }
            Drawable drawableE = androidx.core.graphics.drawable.DrawableCompat.wrap(drawableI);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(drawableE, "wrap(...)");
            BadgeInfoModel badge_info4 = laneItemModel.getBadge_info();
            drawableE.setTint(
                    Color.parseColor(badge_info4 != null ? badge_info4.getBg_color_night() : null));
            appCompatTextView2.setBackgroundDrawable(drawableE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void bindSeries(SeriesModel seriesModel) {
        Drawable drawableI;
        Object obj = ImageLoader.f736a;
        String cover = seriesModel.getCover();
        AppCompatImageView imageView = this.f14405a;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
        ImageLoader.loadPoster(cover, imageView);
        boolean zIsEmpty = TextUtils.isEmpty(seriesModel.getTitle());
        AppCompatTextView appCompatTextView = this.f14406b;
        if (!zIsEmpty) {
            appCompatTextView.setVisibility(0);
            appCompatTextView.setText(seriesModel.getTitle());
        } else if (TextUtils.isEmpty(seriesModel.getSeason_title())) {
            appCompatTextView.setVisibility(8);
        } else {
            appCompatTextView.setVisibility(0);
            appCompatTextView.setText(seriesModel.getSeason_title());
        }
        this.f14407c.setText(seriesModel.getProgress());
        BadgeInfoModel badge_info = seriesModel.getBadge_info();
        AppCompatTextView appCompatTextView2 = this.d;
        if (badge_info == null) {
            appCompatTextView2.setVisibility(8);
            return;
        }
        BadgeInfoModel badge_info2 = seriesModel.getBadge_info();
        kotlin.jvm.internal.Intrinsics.checkNotNull(badge_info2);
        if (TextUtils.isEmpty(badge_info2.getText())) {
            appCompatTextView2.setVisibility(8);
            return;
        }
        appCompatTextView2.setVisibility(0);
        BadgeInfoModel badge_info3 = seriesModel.getBadge_info();
        kotlin.jvm.internal.Intrinsics.checkNotNull(badge_info3);
        appCompatTextView2.setText(badge_info3.getText());
        try {
            BadgeInfoModel badge_info4 = seriesModel.getBadge_info();
            if (TextUtils.isEmpty(badge_info4 != null ? badge_info4.getBg_color_night() : null)
                    || (drawableI =
                                    androidx.appcompat.content.res.AppCompatResources.getDrawable(
                                            appCompatTextView2.getContext(),
                                            R.drawable.badge_background))
                            == null) {
                return;
            }
            Drawable drawableE = androidx.core.graphics.drawable.DrawableCompat.wrap(drawableI);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(drawableE, "wrap(...)");
            BadgeInfoModel badge_info5 = seriesModel.getBadge_info();
            drawableE.setTint(
                    Color.parseColor(badge_info5 != null ? badge_info5.getBg_color_night() : null));
            appCompatTextView2.setBackgroundDrawable(drawableE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
