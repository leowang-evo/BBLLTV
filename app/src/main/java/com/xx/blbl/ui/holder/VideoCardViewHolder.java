package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.BangumiModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoStatModel;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.ItemEvents;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ItemFocusListener;

import java.util.Arrays;

public final class VideoCardViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14413y = 0;
    public final AppCompatImageView f14414a;
    public final AppCompatTextView f14415b;
    public final AppCompatImageView f14416c;
    public final AppCompatTextView d;
    public final AppCompatTextView e;
    public final AppCompatImageView f14417f;
    public final AppCompatTextView f14418p;
    public final AppCompatImageView f14419v;
    public final AppCompatTextView f14420w;
    public final ConstraintLayout f14421x;

    public VideoCardViewHolder(
            View view, ItemClickCallback cVar, boolean z7, ItemFocusCallback bVar) {
        super(view);
        this.f14414a = (AppCompatImageView) view.findViewById(R.id.imageView);
        AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.textView);
        this.f14415b = appCompatTextView;
        this.f14416c = (AppCompatImageView) view.findViewById(R.id.image_avatar);
        this.d = (AppCompatTextView) view.findViewById(R.id.textView_owner);
        this.e = (AppCompatTextView) view.findViewById(R.id.text_play_count);
        this.f14417f = (AppCompatImageView) view.findViewById(R.id.icon_play_count);
        this.f14418p = (AppCompatTextView) view.findViewById(R.id.text_danmaku_count);
        this.f14419v = (AppCompatImageView) view.findViewById(R.id.icon_danmaku);
        this.f14420w = (AppCompatTextView) view.findViewById(R.id.text_duration);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.click_view);
        this.f14421x = constraintLayout;
        constraintLayout.setOnClickListener(ItemEvents.click(cVar, this));
        if (z7) {
            appCompatTextView.setTextSize(
                    0, appCompatTextView.getContext().getResources().getDimension(R.dimen.px30));
            appCompatTextView.setMaxLines(1);
            appCompatTextView.setMinLines(1);
        }
        constraintLayout.setOnFocusChangeListener((target, focused) -> {
            com.xx.blbl.platform.TvMotion.focus(target, focused);
            if (focused && bVar != null)
                bVar.onFocusChange(target, getBindingAdapterPosition(), true);
        });
    }

    public final void bindText(VideoModel video) {
        f14420w.setText(DisplayFormat.h(video.getDuration()));
        OwnerModel owner = video.getOwner();
        f14416c.setVisibility(owner == null ? View.GONE : View.VISIBLE);
        String published = DisplayFormat.g(video.getPubdate());
        d.setText(owner == null ? published : owner.getName() + " · " + published);
        VideoStatModel stat = video.getStat();
        e.setText(stat == null ? "—" : DisplayFormat.f(stat.getView()));
        f14418p.setText(stat == null ? "—" : DisplayFormat.f(stat.getDanmaku()));
        BangumiModel bangumi = video.getBangumi();
        f14415b.setText(bangumi == null ? video.getTitle() : bangumi.getLong_title());
    }

    public final void bind(VideoModel video) {
        bindText(video);
        BangumiModel bangumi = video.getBangumi();
        ImageLoader.loadThumbnail(bangumi == null ? video.getPic() : bangumi.getCover(), f14414a);
    }
}
