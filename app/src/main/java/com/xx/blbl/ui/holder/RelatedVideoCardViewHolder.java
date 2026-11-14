package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.BangumiModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoStatModel;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.ItemClickCallback;

import java.util.Arrays;

public final class RelatedVideoCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14422v = 0;
    public final AppCompatImageView f14423a;
    public final AppCompatTextView f14424b;
    public final AppCompatImageView f14425c;
    public final AppCompatTextView d;
    public final AppCompatTextView e;
    public final AppCompatTextView f14426f;
    public final AppCompatTextView f14427p;

    public RelatedVideoCardViewHolder(View view, boolean z7, ItemClickCallback cVar) {
        super(view);
        this.f14423a = (AppCompatImageView) view.findViewById(R.id.imageView);
        AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.textView);
        this.f14424b = appCompatTextView;
        AppCompatImageView appCompatImageView =
                (AppCompatImageView) view.findViewById(R.id.image_avatar);
        this.f14425c = appCompatImageView;
        AppCompatTextView appCompatTextView2 =
                (AppCompatTextView) view.findViewById(R.id.textView_owner);
        this.d = appCompatTextView2;
        this.e = (AppCompatTextView) view.findViewById(R.id.text_play_count);
        this.f14426f = (AppCompatTextView) view.findViewById(R.id.text_danmaku_count);
        this.f14427p = (AppCompatTextView) view.findViewById(R.id.text_duration);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.click_view);
        constraintLayout.setOnClickListener(ItemEvents.click(cVar, this));
        AppState dVar = AppController.state();
        constraintLayout.getLayoutParams().width = dVar.displayWidth / 5;
        if (z7) {
            appCompatTextView.setTextColor(
                    androidx.core.content.ContextCompat.getColor(
                            view.getContext(), R.color.textColor));
            appCompatTextView2.setTextColor(
                    androidx.core.content.ContextCompat.getColor(
                            view.getContext(), R.color.subTextColor));
            appCompatImageView.setColorFilter(
                    androidx.core.content.ContextCompat.getColor(
                            view.getContext(), R.color.subTextColor));
        }
    }

    public final void bind(VideoModel videoModel) {
        kotlin.Unit jVar;
        this.f14427p.setText(DisplayFormat.h(videoModel.getDuration()));
        OwnerModel owner = videoModel.getOwner();
        kotlin.Unit jVar2 = kotlin.Unit.INSTANCE;
        AppCompatImageView appCompatImageView = this.f14425c;
        AppCompatTextView appCompatTextView = this.d;
        if (owner != null) {
            appCompatTextView.setText(
                    String.format(
                            "%s · %s",
                            Arrays.copyOf(
                                    new Object[] {
                                        owner.getName(), DisplayFormat.g(videoModel.getPubdate())
                                    },
                                    2)));
            appCompatImageView.setVisibility(0);
            jVar = jVar2;
        } else {
            jVar = null;
        }
        if (jVar == null) {
            appCompatImageView.setVisibility(8);
            appCompatTextView.setText(DisplayFormat.g(videoModel.getPubdate()));
        }
        VideoStatModel stat = videoModel.getStat();
        if (stat != null) {
            this.e.setText(DisplayFormat.f(stat.getView()));
            this.f14426f.setText(DisplayFormat.f(stat.getDanmaku()));
        }
        BangumiModel bangumi = videoModel.getBangumi();
        AppCompatImageView imageView = this.f14423a;
        AppCompatTextView appCompatTextView2 = this.f14424b;
        if (bangumi != null) {
            appCompatTextView2.setText(bangumi.getLong_title());
            Object obj = ImageLoader.f736a;
            String cover = bangumi.getCover();
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            ImageLoader.loadThumbnail(cover, imageView);
        } else {
            jVar2 = null;
        }
        if (jVar2 == null) {
            Object obj2 = ImageLoader.f736a;
            String pic = videoModel.getPic();
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            ImageLoader.loadThumbnail(pic, imageView);
            appCompatTextView2.setText(videoModel.getTitle());
        }
    }
}
