package com.xx.blbl.ui.holder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.series.EpisodesDetailModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.AppServiceProvider;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.navigation.ScreenNavigator;
import com.xx.blbl.ui.playback.PlaybackFragment;

import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

public final class SeriesDetailHeaderViewHolder extends ViewHolder {
    public static final /* synthetic */ int f15045K = 0;
    public boolean f15046G;
    public EpisodesDetailModel f15047H;
    public EpisodeModel f15048I;
    public long f15049J;
    public final View f15050a;
    public final Object f15051b;
    public final Object f15052c;
    public final AppCompatImageView d;
    public final AppCompatTextView e;
    public final AppCompatTextView f15053f;
    public final AppCompatTextView f15054p;
    public final AppCompatTextView f15055v;
    public final AppCompatImageView f15056w;
    public final AppCompatButton f15057x;
    public final AppCompatButton f15058y;
    public boolean f15059z;

    public SeriesDetailHeaderViewHolder(
            View view,
            ItemClickCallback onItemClickListener,
            final ItemFocusCallback onFocusListener) {
        super(view);
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        Intrinsics.checkNotNullParameter(onFocusListener, "onFocusListener");
        this.f15050a = view;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        this.f15051b = kotlin.LazyKt.lazy(lazyThreadSafetyMode, new AppServiceProvider(this, 20));
        this.f15052c = kotlin.LazyKt.lazy(lazyThreadSafetyMode, new AppServiceProvider(this, 21));
        View viewFindViewById = view.findViewById(R.id.image_cover);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.d = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.text_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.e = (AppCompatTextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.text_subtitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.f15053f = (AppCompatTextView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.text_description);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.f15054p = (AppCompatTextView) viewFindViewById4;
        View viewFindViewById5 = view.findViewById(R.id.text_watch_info);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.f15055v = (AppCompatTextView) viewFindViewById5;
        View viewFindViewById6 = view.findViewById(R.id.image_top);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.f15056w = (AppCompatImageView) viewFindViewById6;
        View viewFindViewById7 = view.findViewById(R.id.button_continue_watch);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
        AppCompatButton appCompatButton = (AppCompatButton) viewFindViewById7;
        this.f15057x = appCompatButton;
        View viewFindViewById8 = view.findViewById(R.id.button_follow_series);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
        AppCompatButton appCompatButton2 = (AppCompatButton) viewFindViewById8;
        this.f15058y = appCompatButton2;
        appCompatButton.setTag("continueWatch");
        final int i4 = 0;
        appCompatButton.setOnClickListener(
                new View.OnClickListener() {
                    public final /* synthetic */ SeriesDetailHeaderViewHolder f15039b;

                    {
                        this.f15039b = SeriesDetailHeaderViewHolder.this;
                    }

                    @Override
                    public final void onClick(View view2) {
                        int i7 = 1;
                        int i8 = 0;
                        SeriesDetailHeaderViewHolder this$0 = this.f15039b;
                        switch (i4) {
                            case 0:
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                EpisodeModel episodeModel = this$0.f15048I;
                                if (episodeModel != null) {
                                    Object obj = ScreenNavigator.f738a;
                                    Context context = view2.getContext();
                                    VideoModel videoModel = new VideoModel();
                                    videoModel.setTitle(episodeModel.getLong_title());
                                    videoModel.setPic(episodeModel.getCover());
                                    videoModel.setAid(episodeModel.getAid());
                                    videoModel.setBvid(episodeModel.getBvid());
                                    videoModel.setCid(episodeModel.getCid());
                                    videoModel.setPubdate(episodeModel.getPub_time());
                                    videoModel.setRedirect_url(episodeModel.getShare_url());
                                    long j7 = this$0.f15049J * ((long) 1000);
                                    if (context instanceof MainActivity) {
                                        PlaybackFragment uVar = new PlaybackFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("model", videoModel);
                                        bundle.putLong("progress", j7);
                                        uVar.setArguments(bundle);
                                        ((MainActivity) context).showScreen(uVar, "videoPlayer");
                                    }
                                }
                                break;
                            default:
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                if (!this$0.f15046G) {
                                    if (!((AppState) ((kotlin.Lazy) this$0.f15052c).getValue())
                                            .signedIn) {
                                        View view3 = this$0.f15050a;
                                        UiSupport.toast(
                                                view3.getContext(),
                                                view3.getContext()
                                                        .getString(R.string.need_sign_in));
                                    } else {
                                        boolean z7 = this$0.f15059z;
                                        kotlin.Lazy r7 = (kotlin.Lazy) this$0.f15051b;
                                        if (!z7) {
                                            EpisodesDetailModel episodesDetailModel =
                                                    this$0.f15047H;
                                            if (episodesDetailModel != null) {
                                                this$0.f15046G = true;
                                                ((NetworkManager) r7.getValue())
                                                        .followSeries(
                                                                episodesDetailModel.getSeason_id(),
                                                                new SeriesFollowCallback(
                                                                        this$0, i7));
                                            }
                                        } else {
                                            EpisodesDetailModel episodesDetailModel2 =
                                                    this$0.f15047H;
                                            if (episodesDetailModel2 != null) {
                                                this$0.f15046G = true;
                                                ((NetworkManager) r7.getValue())
                                                        .cancelFollowSeries(
                                                                episodesDetailModel2.getSeason_id(),
                                                                new SeriesFollowCallback(
                                                                        this$0, i8));
                                            }
                                        }
                                    }
                                    break;
                                }
                                break;
                        }
                    }
                });
        appCompatButton.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public final void onFocusChange(View view2, boolean z7) {
                        switch (i4) {
                            case 0:
                                ItemFocusCallback onFocusListener2 = onFocusListener;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        onFocusListener2, "$onFocusListener");
                                SeriesDetailHeaderViewHolder this$0 =
                                        SeriesDetailHeaderViewHolder.this;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                onFocusListener2.onFocusChange(
                                        view2, this$0.getBindingAdapterPosition(), z7);
                                break;
                            default:
                                ItemFocusCallback onFocusListener3 = onFocusListener;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        onFocusListener3, "$onFocusListener");
                                SeriesDetailHeaderViewHolder this$1 =
                                        SeriesDetailHeaderViewHolder.this;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$1, "this$0");
                                onFocusListener3.onFocusChange(
                                        view2, this$1.getBindingAdapterPosition(), z7);
                                break;
                        }
                    }
                });
        appCompatButton2.setTag("followSeries");
        final int i7 = 1;
        appCompatButton2.setOnClickListener(
                new View.OnClickListener() {
                    public final /* synthetic */ SeriesDetailHeaderViewHolder f15039b;

                    {
                        this.f15039b = SeriesDetailHeaderViewHolder.this;
                    }

                    @Override
                    public final void onClick(View view2) {
                        int i8 = 1;
                        int i9 = 0;
                        SeriesDetailHeaderViewHolder this$0 = this.f15039b;
                        switch (i7) {
                            case 0:
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                EpisodeModel episodeModel = this$0.f15048I;
                                if (episodeModel != null) {
                                    Object obj = ScreenNavigator.f738a;
                                    Context context = view2.getContext();
                                    VideoModel videoModel = new VideoModel();
                                    videoModel.setTitle(episodeModel.getLong_title());
                                    videoModel.setPic(episodeModel.getCover());
                                    videoModel.setAid(episodeModel.getAid());
                                    videoModel.setBvid(episodeModel.getBvid());
                                    videoModel.setCid(episodeModel.getCid());
                                    videoModel.setPubdate(episodeModel.getPub_time());
                                    videoModel.setRedirect_url(episodeModel.getShare_url());
                                    long j7 = this$0.f15049J * ((long) 1000);
                                    if (context instanceof MainActivity) {
                                        PlaybackFragment uVar = new PlaybackFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("model", videoModel);
                                        bundle.putLong("progress", j7);
                                        uVar.setArguments(bundle);
                                        ((MainActivity) context).showScreen(uVar, "videoPlayer");
                                    }
                                }
                                break;
                            default:
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                if (!this$0.f15046G) {
                                    if (!((AppState) ((kotlin.Lazy) this$0.f15052c).getValue())
                                            .signedIn) {
                                        View view3 = this$0.f15050a;
                                        UiSupport.toast(
                                                view3.getContext(),
                                                view3.getContext()
                                                        .getString(R.string.need_sign_in));
                                    } else {
                                        boolean z7 = this$0.f15059z;
                                        kotlin.Lazy r7 = (kotlin.Lazy) this$0.f15051b;
                                        if (!z7) {
                                            EpisodesDetailModel episodesDetailModel =
                                                    this$0.f15047H;
                                            if (episodesDetailModel != null) {
                                                this$0.f15046G = true;
                                                ((NetworkManager) r7.getValue())
                                                        .followSeries(
                                                                episodesDetailModel.getSeason_id(),
                                                                new SeriesFollowCallback(
                                                                        this$0, i8));
                                            }
                                        } else {
                                            EpisodesDetailModel episodesDetailModel2 =
                                                    this$0.f15047H;
                                            if (episodesDetailModel2 != null) {
                                                this$0.f15046G = true;
                                                ((NetworkManager) r7.getValue())
                                                        .cancelFollowSeries(
                                                                episodesDetailModel2.getSeason_id(),
                                                                new SeriesFollowCallback(
                                                                        this$0, i9));
                                            }
                                        }
                                    }
                                    break;
                                }
                                break;
                        }
                    }
                });
        appCompatButton2.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public final void onFocusChange(View view2, boolean z7) {
                        switch (i7) {
                            case 0:
                                ItemFocusCallback onFocusListener2 = onFocusListener;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        onFocusListener2, "$onFocusListener");
                                SeriesDetailHeaderViewHolder this$0 =
                                        SeriesDetailHeaderViewHolder.this;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$0, "this$0");
                                onFocusListener2.onFocusChange(
                                        view2, this$0.getBindingAdapterPosition(), z7);
                                break;
                            default:
                                ItemFocusCallback onFocusListener3 = onFocusListener;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        onFocusListener3, "$onFocusListener");
                                SeriesDetailHeaderViewHolder this$1 =
                                        SeriesDetailHeaderViewHolder.this;
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(
                                        this$1, "this$0");
                                onFocusListener3.onFocusChange(
                                        view2, this$1.getBindingAdapterPosition(), z7);
                                break;
                        }
                    }
                });
    }

    public static final void a(SeriesDetailHeaderViewHolder hVar) {
        EpisodesDetailModel episodesDetailModel = hVar.f15047H;
        boolean z7 = false;
        if (episodesDetailModel != null && episodesDetailModel.getType() == 1) {
            z7 = true;
        }
        boolean z8 = hVar.f15059z;
        AppCompatButton appCompatButton = hVar.f15058y;
        if (z8) {
            appCompatButton.setBackgroundResource(R.drawable.button_common_2);
            appCompatButton.setText(
                    appCompatButton
                            .getContext()
                            .getString(
                                    z7 ? R.string.followed_animation : R.string.followed_series));
        } else {
            appCompatButton.setBackgroundResource(R.drawable.button_common);
            appCompatButton.setText(
                    appCompatButton
                            .getContext()
                            .getString(z7 ? R.string.follow_animation : R.string.follow_series));
        }
    }
}
