package com.xx.blbl.ui.playback;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.*;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.media3.common.*;
import androidx.media3.datasource.*;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.*;
import androidx.recyclerview.widget.*;

import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.interaction.InteractionEdgeQuestionChoiceModel;
import com.xx.blbl.model.live.*;
import com.xx.blbl.model.player.*;
import com.xx.blbl.model.series.*;
import com.xx.blbl.model.subtitle.*;
import com.xx.blbl.model.subtitle.SubtitleModel;
import com.xx.blbl.model.video.*;
import com.xx.blbl.model.video.quality.*;
import com.xx.blbl.network.*;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.RecyclerFocus;
import com.xx.blbl.playback.DanmakuSegmentCallback;
import com.xx.blbl.playback.PlaybackCoordinator;
import com.xx.blbl.playback.PlaybackMetadataCallback;
import com.xx.blbl.playback.PlayerMetadataCallback;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.EpisodeListAdapter;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.RelatedVideosAdapter;
import com.xx.blbl.ui.dialog.FavoriteActionCallback;
import com.xx.blbl.ui.dialog.OwnerDialog;
import com.xx.blbl.ui.dialog.VideoActionCallback;
import com.xx.blbl.ui.dialog.VideoActionsDialog;
import com.xx.blbl.ui.dialog.VideoInfoDialog;
import com.xx.blbl.ui.screen.ScreenEvents;
import com.xx.blbl.ui.view.exoplayer.*;
import com.xx.blbl.ui.view.exoplayer.DebugTextViewHelper;
import com.xx.blbl.ui.view.exoplayer.MyPlayerView;
import com.xx.blbl.ui.view.exoplayer.OnPlayerSettingChange;
import com.xx.blbl.ui.view.exoplayer.OnVideoSettingChangeListener;
import com.xx.blbl.ui.view.interaction.InteractionVideoHandleView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/** Application playback orchestration around published Media3 and Danmaku APIs. */
public final class PlaybackFragment extends Fragment
        implements MainActivity.BackHandler, ScreenEvents.Listener {
    public AppCompatTextView f9689A0, f9692D0, E0;
    public AppCompatImageView f9690B0;
    public LinearLayoutCompat f9691C0, f9707z0;
    public InteractionVideoHandleView f9693F0;
    public DebugTextViewHelper f9696I0;
    public boolean f9697K0;
    public MyPlayerView playerView;
    public ExoPlayer player;
    public TextClock f9701t0;
    public ProgressBar f9702u0;
    public RecyclerView f9704w0;
    public ConstraintLayout f9705x0;
    public RelatedVideosAdapter f9706y0;
    public final PlaybackCoordinator coordinator = new PlaybackCoordinator(this);
    public final AppState appState = AppController.state();
    public final Handler playbackHandler = new Handler(Looper.getMainLooper());
    public final Runnable progressTicker = this::tick;
    private boolean destroyed, terminalHandled, resumeWhenShown, started, pendingPlayback;
    private boolean playbackEverStarted, manualAudioSelection;
    private long liveChatGeneration;
    private List<DanmakuItemData> pendingDmData;
    private boolean pendingDmReset;
    private boolean verificationRequired;
    private AlertDialog verificationDialog;
    public final Player.Listener playerListener =
            new Player.Listener() {
                @Override
                public void onIsPlayingChanged(boolean playing) {
                    if (PlaybackFragment.this.playerView == null) return;
                    PlaybackFragment.this.playerView.setKeepScreenOn(playing);
                    if (playing && canPlay()) {
                        flushPendingDm();
                        PlaybackFragment.this.playerView.startDm();
                        PlaybackFragment.this.playerView.seekDmTo(
                                PlaybackFragment.this.player.getCurrentPosition());
                    } else PlaybackFragment.this.playerView.pauseDm();
                    if (f9701t0 != null) f9701t0.setVisibility(playing ? View.GONE : View.VISIBLE);
                    c0(playing ? 3 : 2);
                }

                @Override
                public void onPlaybackStateChanged(int state) {
                    if (state == Player.STATE_READY) terminalHandled = false;
                    if (state == Player.STATE_ENDED && !terminalHandled) {
                        terminalHandled = true;
                        if (f9705x0 == null || f9705x0.getVisibility() != View.VISIBLE)
                            PlaybackFragment.this.coordinator.playNext(false);
                        if (PlaybackFragment.this.coordinator.f12306t0 && f9693F0 != null)
                            f9693F0.d();
                    }
                }

                @Override
                public void onPlayerError(PlaybackException error) {
                    j0(error.getMessage());
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters parameters) {
                    PlaybackFragment.this.coordinator.f12271O = parameters.speed;
                }

                @Override
                public void onVideoSizeChanged(VideoSize size) {
                    if (f9693F0 != null) f9693F0.setVideoViewport(size.width, size.height);
                }
            };

    public MainActivity h() {
        return getActivity() instanceof MainActivity ? (MainActivity) getActivity() : null;
    }

    public Context j() {
        return getContext();
    }

    public android.content.res.Resources m() {
        return getResources();
    }

    public String n(int resource) {
        return getString(resource);
    }

    public boolean q() {
        return isAdded() && !destroyed;
    }

    public boolean t() {
        return isVisible();
    }

    public void Q(Bundle arguments) {
        setArguments(arguments);
    }

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        x(saved);
    }

    public void x(Bundle saved) {
        Bundle arguments = getArguments();
        PlaybackCoordinator state = this.coordinator;
        if (arguments != null) {
            Serializable value = arguments.getSerializable("model");
            if (value instanceof VideoModel) state.video = (VideoModel) value;
            state.f12289d0 = arguments.getLong("progress", 0);
            state.f12290e0 = arguments.getInt("startEpisode", 0);
            Serializable list = arguments.getSerializable("list");
            if (list instanceof List) state.f12273Q = (List) list;
            if (state.video != null && state.video.getProgress() != 0)
                state.f12289d0 = state.video.getProgress();
        }
        state.context = getContext();
        state.f12268K = this.appState.signedIn ? VideoQuality.Q1080P : VideoQuality.Q720P;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
        return y(inflater, parent, saved);
    }

    public View y(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
        return inflater.inflate(R.layout.fragment_video_player, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        I(view);
    }

    public void I(View view) {
        destroyed = false;
        this.playerView = view.findViewById(R.id.playerView);
        f9705x0 = view.findViewById(R.id.view_related);
        f9704w0 = view.findViewById(R.id.recyclerViewRelated);
        f9706y0 = new RelatedVideosAdapter();
        if (f9704w0 != null) {
            f9704w0.setLayoutManager(
                    new GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false));
            f9704w0.setAdapter(f9706y0);
        }
        f9707z0 = view.findViewById(R.id.view_next);
        f9689A0 = view.findViewById(R.id.text_next);
        f9690B0 = view.findViewById(R.id.imageView_next);
        f9691C0 = view.findViewById(R.id.view_debug);
        f9692D0 = view.findViewById(R.id.text_debug);
        E0 = view.findViewById(R.id.text_subtitle);
        f9701t0 = view.findViewById(R.id.text_clock);
        f9702u0 = view.findViewById(R.id.bottom_progress_bar);
        View closeRelated = view.findViewById(R.id.button_close_related);
        if (closeRelated != null) closeRelated.setOnClickListener(v -> l0(false));
        this.coordinator.loadPreferences();
        this.playerView.setPremiumMember(this.appState.premiumMember);
        this.playerView.getAppController().setDisableRelatedKey(this.appState.simpleRemoteKeys);
        this.playerView.setOnPlayerSettingChange(
                new OnPlayerSettingChange() {
                    public void onAudioChange(int index) {
                        S(PlaybackFragment.this, index);
                        closeSettings();
                    }

                    public void onSubtitleChange(int index) {
                        PlaybackFragment.this.coordinator.f12264G = index;
                        PlaybackFragment.this.coordinator.loadSubtitle();
                        closeSettings();
                    }

                    public void onVideoCodecChange(int index) {
                        U(PlaybackFragment.this, index);
                        closeSettings();
                    }

                    public void onVideoQualityChange(int index) {
                        T(PlaybackFragment.this, index);
                        closeSettings();
                    }
                });
        this.playerView.setOnVideoSettingChangeListener(new VideoActions());
        this.playerView.setControllerVisibilityListener(
                (MyPlayerView.ControllerVisibilityListener)
                        visibility -> {
                            if (E0 != null
                                    && E0.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                                FrameLayout.LayoutParams params =
                                        (FrameLayout.LayoutParams) E0.getLayoutParams();
                                params.bottomMargin =
                                        Math.round(
                                                m().getDimension(
                                                                visibility == View.VISIBLE
                                                                        ? R.dimen.px300
                                                                        : R.dimen.px60));
                                E0.setLayoutParams(params);
                            }
                            if (f9702u0 != null)
                                f9702u0.setVisibility(
                                        this.coordinator.f12292f0 && visibility != View.VISIBLE
                                                ? View.VISIBLE
                                                : View.GONE);
                            if (f9701t0 != null) f9701t0.setVisibility(visibility);
                        });
        f9706y0.f10351b =
                (clicked, position) -> {
                    List<VideoModel> related = this.coordinator.f12272P;
                    if (related != null && position >= 0 && position < related.size()) {
                        a0();
                        this.coordinator.video = related.get(position);
                        this.coordinator.loadVideoDetails();
                        l0(false);
                    }
                };
        f9693F0 = view.findViewById(R.id.interaction_view);
        f9693F0.setCallback(
                new InteractionVideoHandleView.Callback() {
                    public void onPauseVideo() {
                        if (PlaybackFragment.this.player != null)
                            PlaybackFragment.this.player.pause();
                    }

                    public void onChoice(InteractionEdgeQuestionChoiceModel choice) {
                        if (PlaybackFragment.this.coordinator.video != null
                                && choice.getCid() != 0) {
                            PlaybackFragment.this.coordinator.video.setCid(choice.getCid());
                            PlaybackFragment.this.coordinator.f12309v0 = choice.getId();
                            PlaybackFragment.this.coordinator.f12289d0 = 0;
                            PlaybackFragment.this.coordinator.requestVodPlayback();
                        }
                    }
                });
        this.playerView.showHideFfRe(this.coordinator.o0);
        this.playerView.showHideNextPrevious(this.coordinator.f12302p0);
        this.playerView.showHideDmSwitchButton(this.coordinator.f12293g0);
        this.playerView.setSeekSecond(this.coordinator.f12296j0);
        f9691C0.setVisibility(this.coordinator.f12300n0 ? View.VISIBLE : View.GONE);
        E0.setTextSize(0, this.coordinator.f12305s0);
        f9702u0.setVisibility(this.coordinator.f12292f0 ? View.VISIBLE : View.GONE);
        this.coordinator.loadVideoDetails();
    }

    private void closeSettings() {
        if (this.playerView != null) this.playerView.showHideSettingView(false);
    }

    private void toast(String message) {
        if (getContext() != null) Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public DefaultDataSource.Factory X() {
        boolean live = this.coordinator.f12278V == 2;
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", live ? "https://live.bilibili.com/" : "https://www.bilibili.com/");
        VideoModel video = this.coordinator.video;
        String referer = live ? "https://live.bilibili.com/" : "https://www.bilibili.com/";
        if (video != null) {
            if (live && !TextUtils.isEmpty(video.getRedirect_url()))
                referer = video.getRedirect_url();
            else if (!live && !TextUtils.isEmpty(video.getBvid()))
                referer += "video/" + video.getBvid();
        }
        headers.put("Referer", referer);
        DefaultHttpDataSource.Factory http =
                new DefaultHttpDataSource.Factory()
                        .setUserAgent(AppController.network().getCurrentUA())
                        .setAllowCrossProtocolRedirects(true)
                        .setDefaultRequestProperties(headers);
        return new DefaultDataSource.Factory(requireContext(), http);
    }

    private void ensurePlayer() {
        if (this.player != null) return;
        this.player =
                new ExoPlayer.Builder(requireContext())
                        .setMediaSourceFactory(new DefaultMediaSourceFactory(X()))
                        .build();
        this.player.addListener(this.playerListener);
        this.playerView.setPlayer(this.player);
    }

    private MediaSource source(String url) {
        return new DefaultMediaSourceFactory(X()).createMediaSource(MediaItem.fromUri(url));
    }

    private void start(MediaSource source, long position) {
        if (!canPlay()) {
            pendingPlayback = true;
            return;
        }
        ensurePlayer();
        terminalHandled = false;
        this.playerView.setCustomErrorMessage(null);
        this.player.setMediaSource(source);
        this.player.prepare();
        if (position > 0) this.player.seekTo(position);
        this.player.setPlaybackSpeed(this.coordinator.f12271O);
        this.player.play();
        this.playerView.setKeepScreenOn(true);
        this.playerView.requestFocus();
        if (!playbackEverStarted && position > 1) {
            toast(n(R.string.tv_resumed_position));
        }
        playbackEverStarted = true;
    }

    private void changeTracks() {
        String video = this.coordinator.selectedVideoUrl();
        if (TextUtils.isEmpty(video)) return;
        long position =
                this.player == null ? this.coordinator.f12289d0 : this.player.getCurrentPosition();
        String audio = this.coordinator.selectedAudioUrl();
        start(
                TextUtils.isEmpty(audio)
                        ? source(video)
                        : new MergingMediaSource(source(video), source(audio)),
                position);
    }

    public static void S(PlaybackFragment fragment, int index) {
        if (index < 0 || index >= fragment.coordinator.f12266I.size()) return;
        fragment.manualAudioSelection = true;
        fragment.coordinator.f12288c0 = (AudioQuality) fragment.coordinator.f12266I.get(index);
        fragment.coordinator.f12269L = fragment.coordinator.f12288c0;
        fragment.changeTracks();
    }

    public static void T(PlaybackFragment fragment, int index) {
        PlaybackCoordinator state = fragment.coordinator;
        if (index < 0 || index >= state.f12265H.size()) return;
        state.f12316z = index;
        state.f12268K = (VideoQuality) state.f12265H.get(index);
        state.f12297k0 = state.f12268K;
        if (fragment.player != null) state.f12289d0 = fragment.player.getCurrentPosition();
        if (state.f12268K.getCode() > 64 && !fragment.appState.premiumMember)
            fragment.toast(fragment.n(R.string.change_resolution_need_vip));
        state.loadCurrentMedia();
    }

    public static void U(PlaybackFragment fragment, int index) {
        if (index < 0 || index >= fragment.coordinator.f12267J.size()) return;
        fragment.coordinator.f12286b0 = (VideoCodecEnum) fragment.coordinator.f12267J.get(index);
        fragment.changeTracks();
    }

    public void Y(PlayInfoModel info) {
        if (info == null) return;
        PlaybackCoordinator state = this.coordinator;
        state.playInfo = info;
        if (!canPlay()) {
            pendingPlayback = true;
            return;
        }
        pendingPlayback = false;
        this.playerView.showHideLiveSettingButton(false);
        this.playerView.showSettingButton(true);
        ArrayList<VideoQuality> qualities = new ArrayList<>();
        if (info.getAccept_quality() != null)
            for (Integer value : info.getAccept_quality())
                for (VideoQuality quality : VideoQuality.values())
                    if (value != null && value == quality.getCode() && quality.getCode() > 32)
                        qualities.add(quality);
        if (qualities.size() > state.f12265H.size()) {
            state.f12265H.clear();
            state.f12265H.addAll(qualities);
        }
        if (!state.f12265H.isEmpty()) this.playerView.setQualities(state.f12265H);
        ArrayList<AudioQuality> audios = new ArrayList<>();
        DashInfoModel dash = info.getDash();
        if (dash != null && dash.getAudio() != null)
            for (DashMediaModel audio : dash.getAudio())
                for (AudioQuality quality : AudioQuality.values())
                    if (audio.getId() == quality.getCode() && !audios.contains(quality))
                        audios.add(quality);
        if (dash != null && dash.getDolby() != null && dash.getDolby().getAudio() != null)
            audios.add(AudioQuality.DolbyAtoms);
        if (dash != null && dash.getFlac() != null && dash.getFlac().getAudio() != null)
            audios.add(AudioQuality.Lossless);
        state.f12266I = audios;
        this.playerView.setAudiosSelect(audios);
        if ((!manualAudioSelection || !audios.contains(state.f12288c0))
                && audios.contains(state.f12269L)) state.f12288c0 = state.f12269L;
        ArrayList<VideoCodecEnum> codecs = new ArrayList<>();
        if (dash != null && dash.getVideo() != null)
            for (DashMediaModel video : dash.getVideo())
                for (VideoCodecEnum codec : VideoCodecEnum.values())
                    if (video.getCodecs().startsWith(codec.getValue()) && !codecs.contains(codec))
                        codecs.add(codec);
        state.f12267J = codecs;
        this.playerView.setVideoCodec(codecs);
        String videoUrl = state.selectedVideoUrl(), audioUrl = null;
        if (!TextUtils.isEmpty(videoUrl)) {
            audioUrl = state.selectedAudioUrl();
            MediaSource video = source(videoUrl);
            start(
                    TextUtils.isEmpty(audioUrl)
                            ? video
                            : new MergingMediaSource(video, source(audioUrl)),
                    state.f12306t0 ? 0 : state.f12289d0);
        } else if (state.videoSegments != null && !state.videoSegments.isEmpty()) {
            ArrayList<MediaSource> segments = new ArrayList<>();
            for (Object item : state.videoSegments)
                segments.add(source(((VideoSegmentModel) item).getUrl()));
            start(
                    new ConcatenatingMediaSource(segments.toArray(new MediaSource[0])),
                    state.f12289d0);
            this.playerView.setShowMultiWindowTimeBar(true);
        } else {
            j0("No playable stream returned");
            return;
        }
        if (state.f12289d0 > 0) state.f12289d0 = 1;
        this.playerView.setPlaySpeed(state.f12271O);
        this.playerView.selectQuality(state.f12268K);
        d0(state.f12269L);
        this.playerView.selectVideoCodec(state.f12286b0);
        VideoModel video = state.video;
        this.playerView.setShowHideOwnerInfo(
                video != null
                        && video.getOwner() != null
                        && !TextUtils.isEmpty(video.getOwner().getMid()));
        state.f12277U = 1;
        state.f12315y0 = false;
        if (video != null)
            state.network()
                    .getVideoCommentWbi(
                            video.getCid(),
                            video.getAid(),
                            1,
                            new DanmakuSegmentCallback(state, video));
        this.playbackHandler.removeCallbacks(this.progressTicker);
        this.playbackHandler.postDelayed(this.progressTicker, 100);
        if (state.f12300n0) {
            if (f9696I0 != null) f9696I0.stop();
            f9696I0 = new DebugTextViewHelper(this.player, f9692D0);
            if (!TextUtils.isEmpty(videoUrl)) f9696I0.setVideoUrl(Uri.parse(videoUrl).getHost());
            if (!TextUtils.isEmpty(audioUrl)) f9696I0.setAudioUrl(Uri.parse(audioUrl).getHost());
            f9696I0.start();
        }
    }

    public void Z() {
        if (!canPlay()) {
            pendingPlayback = true;
            return;
        }
        pendingPlayback = false;
        MyPlayerView view = this.playerView;
        view.setShowHideOwnerInfo(false);
        view.showSettingButton(false);
        view.showHideEpisodeButton(false);
        view.showHideActionButton(false);
        view.showHideSubtitleButton(false);
        view.showHideFfRe(false);
        view.showHideNextPrevious(false);
        view.showHideRelatedButton(false);
        view.showHideRepeatButton(false);
        view.showHideLiveSettingButton(true);
        VideoModel video = this.coordinator.video;
        if (video != null) {
            h0(video.getTitle());
            g0(
                    video.getOwner() == null ? null : video.getOwner().getName(),
                    n(R.string.living),
                    null,
                    null);
        }
        this.coordinator.f12292f0 = false;
        f9702u0.setVisibility(View.GONE);
        List<LiveDUrlModel> streams = this.coordinator.liveStreams;
        if (streams != null && !streams.isEmpty()) start(source(streams.get(0).getUrl()), 0);
        view.initDmPlayer();
        if (this.player != null && this.player.isPlaying()) view.startDm();
        f9701t0.setVisibility(View.GONE);
        requestLiveChat();
    }

    public void a0() {
        PlaybackCoordinator state = this.coordinator;
        VideoModel video = state.video;
        if (video != null && this.player != null) {
            long progress = this.player.getCurrentPosition() + 1;
            if (state.f12278V != 2)
                com.xx.blbl.platform.ContinueWatching.save(video, progress, this.player.getDuration());
            if (state.f12278V == 1
                    && state.e != null
                    && state.f12280X >= 0
                    && state.f12280X < state.e.size())
                ScreenEvents.post(
                        "playEpisode|"
                                + video.getEpid()
                                + "|"
                                + progress
                                + "|"
                                + ((EpisodeModel) state.e.get(state.f12280X)).getTitle());
            else if (state.f12278V == 0)
                ScreenEvents.post(
                        "playUgc|" + video.getAid() + "|" + video.getCid() + "|" + progress);
        }
        c0(2);
    }

    @Override
    public boolean handleBack() {
        return b0();
    }

    public boolean b0() {
        if (f9697K0) {
            a0();
            return false;
        }
        if (this.playerView != null && this.playerView.dismissSettingsOnBack()) return true;
        if (f9705x0 != null && f9705x0.getVisibility() == View.VISIBLE) {
            l0(false);
            return true;
        }
        if (this.playerView != null && this.playerView.isControllerFullyVisible()) {
            this.playerView.hideController();
            return true;
        }
        a0();
        return false;
    }

    public void c0(int action) {
        if (this.player == null || this.coordinator.f12278V == 2 || this.coordinator.video == null)
            return;
        PlaybackCoordinator state = this.coordinator;
        state.network()
                .playHeartbeat(
                        state.video,
                        state.settings.userId,
                        this.player.getCurrentPosition() / 1000L,
                        action,
                        state.f12304r0,
                        state.f12279W,
                        new NetResultCallback<BaseResponse<String>>() {
                            public void onFailure(Throwable error) {
                                android.util.Log.d("BBLL", "Heartbeat failed", error);
                            }

                            public void onResponse(BaseResponse<String> value) {}
                        });
    }

    public void d0(AudioQuality quality) {
        if (q() && this.playerView != null) this.playerView.selectAudio(quality);
    }

    public void e0(List data) {
        e0(data, false);
    }

    public void e0(List data, boolean reset) {
        if (!q() || this.playerView == null) return;
        pendingDmData = new ArrayList<DanmakuItemData>(data);
        pendingDmReset |= reset;
        flushPendingDm();
    }

    private void flushPendingDm() {
        if (!canPlay() || pendingDmData == null) return;
        if (pendingDmReset) this.playerView.initDmPlayer();
        this.playerView.updateDmData(pendingDmData);
        pendingDmData = null;
        pendingDmReset = false;
        if (this.player != null) this.playerView.seekDmTo(this.player.getCurrentPosition());
        if (this.player != null && this.player.isPlaying()) this.playerView.startDm();
        else this.playerView.pauseDm();
    }

    public void f0(String title, String cover) {
        if (!q()) return;
        f9689A0.setText(title);
        f9690B0.setVisibility(TextUtils.isEmpty(cover) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(cover)) ImageLoader.loadThumbnail(cover, f9690B0);
    }

    public void g0(String owner, String suffix, Long views, Long published) {
        if (!q() || this.playerView == null) return;
        StringBuilder text = new StringBuilder();
        if (!TextUtils.isEmpty(owner)) text.append(owner).append(" · ");
        if (views != null && views > 0)
            text.append(getString(R.string.view_, DisplayFormat.f(views))).append(" · ");
        if (published != null && published > 0) {
            Date date = new Date(published * 1000);
            String format =
                    date.getYear() == new Date().getYear() ? "MM月dd日 HH:mm" : "yyyy年MM月dd日 HH:mm";
            text.append(
                    getString(
                            R.string.publish_at,
                            new SimpleDateFormat(format, Locale.CHINA).format(date)));
        }
        if (suffix != null) text.append(suffix);
        this.playerView.setSubTitle(text.toString());
    }

    public void h0(String title) {
        if (q() && this.playerView != null) this.playerView.setTitle(title);
    }

    public void i0(ArrayList subtitles) {
        if (!q() || this.playerView == null) return;
        ArrayList<SubtitleInfoModel> rows = new ArrayList<>(subtitles);
        SubtitleInfoModel off = new SubtitleInfoModel();
        off.setId("-1");
        off.setLan_doc(n(R.string.off));
        off.setLan(n(R.string.off));
        rows.add(off);
        this.playerView.setSubtitles(rows);
        n0(!subtitles.isEmpty());
    }

    public void j0(String error) {
        if (q() && this.playerView != null) this.playerView.setCustomErrorMessage(error);
    }

    public void showPlaybackRequestError(String message) {
        if (f9702u0 != null) f9702u0.setVisibility(View.GONE);
        j0(message);
    }

    public void showPlaybackVerification() {
        verificationRequired = true;
        if (this.player != null) this.player.pause();
        showPlaybackRequestError(n(R.string.playback_verification_message));
        presentPlaybackVerification();
    }

    public void clearPlaybackVerification() {
        if (!verificationRequired) return;
        verificationRequired = false;
        dismissPlaybackVerification();
        j0(null);
    }

    private void dismissPlaybackVerification() {
        AlertDialog dialog = verificationDialog;
        verificationDialog = null;
        if (dialog != null) dialog.dismiss();
    }

    private void presentPlaybackVerification() {
        if (!verificationRequired || verificationDialog != null || !canPlay() || !isResumed())
            return;
        Uri officialPage = officialVideoPage();
        AlertDialog.Builder builder =
                new AlertDialog.Builder(requireContext())
                        .setTitle(R.string.playback_verification_title)
                        .setMessage(R.string.playback_verification_message)
                        .setNegativeButton(
                                R.string.playback_back_to_list,
                                (dialog, which) -> exitVerification())
                        .setNeutralButton(
                                R.string.playback_try_again,
                                (dialog, which) -> {
                                    clearPlaybackVerification();
                                    this.coordinator.loadCurrentMedia();
                                })
                        .setOnCancelListener(dialog -> exitVerification());
        if (officialPage != null) {
            builder.setPositiveButton(
                    R.string.playback_open_bilibili,
                    (dialog, which) -> {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, officialPage));
                        } catch (ActivityNotFoundException error) {
                            toast(n(R.string.playback_no_browser));
                            this.playbackHandler.post(this::presentPlaybackVerification);
                        }
                    });
        }
        AlertDialog dialog = builder.create();
        verificationDialog = dialog;
        dialog.setOnDismissListener(
                ignored -> {
                    if (verificationDialog == dialog) verificationDialog = null;
                });
        dialog.show();
    }

    private void exitVerification() {
        verificationRequired = false;
        f9697K0 = true;
        MainActivity activity = h();
        if (activity != null) activity.onBackPressed();
    }

    private Uri officialVideoPage() {
        VideoModel video = this.coordinator.video;
        if (video == null) return null;
        String id = video.getBvid();
        if (id == null || !id.matches("BV[A-Za-z0-9]{10}")) {
            if (video.getAid() <= 0) return null;
            id = "av" + video.getAid();
        }
        Uri.Builder url =
                new Uri.Builder()
                        .scheme("https")
                        .authority("www.bilibili.com")
                        .appendPath("video")
                        .appendPath(id);
        if (video.getPages() != null) {
            for (VideoPvModel page : video.getPages()) {
                if (page != null && page.getCid() == video.getCid() && page.getPage() > 1) {
                    url.appendQueryParameter("p", String.valueOf(page.getPage()));
                    break;
                }
            }
        }
        return url.build();
    }

    public void k0(boolean show) {
        if (!q() || f9707z0 == null) return;
        if (f9707z0.getVisibility() != (show ? View.VISIBLE : View.GONE)) {
            f9707z0.setVisibility(show ? View.VISIBLE : View.GONE);
            f9707z0.startAnimation(
                    AnimationUtils.loadAnimation(
                            requireContext(),
                            show ? R.anim.slide_in_to_right : R.anim.slide_out_to_right));
        }
        if (show)
            this.playbackHandler.postDelayed(
                    () -> {
                        if (q()) k0(false);
                    },
                    5000);
    }

    public void l0(boolean show) {
        if (!q()
                || f9705x0 == null
                || (show
                        && (this.coordinator.f12272P == null
                                || this.coordinator.f12272P.isEmpty()))) return;
        f9705x0.setVisibility(show ? View.VISIBLE : View.GONE);
        f9705x0.startAnimation(
                AnimationUtils.loadAnimation(
                        requireContext(), show ? R.anim.slide_up : R.anim.slide_down));
        if (show) RecyclerFocus.request(f9704w0, 0);
        else if (this.playerView != null)
            this.playerView.getAppController().requestPlayPauseFocus();
    }

    public void m0(boolean show) {
        if (q() && this.playerView != null) this.playerView.showHideRelatedButton(show);
    }

    public void n0(boolean show) {
        if (q() && this.playerView != null) this.playerView.showHideSubtitleButton(show);
    }

    public void q0() {
        if (!q()) return;
        VideoModel video = this.coordinator.video;
        if (video == null) return;
        String suffix =
                TextUtils.isEmpty(this.coordinator.f12303q0)
                        ? null
                        : " · " + getString(R.string.watching_, this.coordinator.f12303q0);
        g0(
                video.getOwner() == null ? null : video.getOwner().getName(),
                suffix,
                video.getStat() == null ? null : video.getStat().getView(),
                video.getPubdate() != 0 ? video.getPubdate() : video.getPub_time());
    }

    public void updateEpisodeHeader() {
        if (!q()) return;
        PlaybackCoordinator state = this.coordinator;
        VideoModel video = state.video;
        if (video == null) return;
        h0(video.getTitle());
        q0();
        List<?> entries = state.episodeChoices();
        int current = state.currentEpisodeIndex(entries);
        state.f12280X = current;
        if (current < 0) return;
        Object entry = entries.get(current);
        String title;
        if (entry instanceof EpisodeModel) {
            EpisodeModel episode = (EpisodeModel) entry;
            title = TextUtils.isEmpty(episode.getLong_title())
                    ? episode.getTitle()
                    : episode.getLong_title();
        } else if (entry instanceof UgcEpisodeModel) {
            title = ((UgcEpisodeModel) entry).getTitle();
        } else {
            title = ((VideoPvModel) entry).getPart();
        }
        if (!TextUtils.isEmpty(title) && !title.equals(video.getTitle()))
            h0(title + " ｜ " + video.getTitle());
    }

    private void tick() {
        if (!canPlay()) return;
        if (this.player != null && this.player.isPlaying()) {
            long position = this.player.getCurrentPosition(), duration = this.player.getDuration();
            if (this.coordinator.f12292f0 && duration > 0) {
                f9702u0.setMax((int) Math.min(Integer.MAX_VALUE, duration));
                f9702u0.setProgress((int) Math.min(Integer.MAX_VALUE, position));
            }
            VideoModel video = this.coordinator.video;
            if (this.coordinator.f12278V != 2
                    && !this.coordinator.f12315y0
                    && (position + 1000) / 360000 >= this.coordinator.f12277U
                    && video != null) {
                this.coordinator.f12277U++;
                this.coordinator
                        .network()
                        .getVideoCommentWbi(
                                video.getCid(),
                                video.getAid(),
                                this.coordinator.f12277U,
                                new DanmakuSegmentCallback(this.coordinator, video));
            }
            if (duration > 0
                    && duration - position < 12000
                    && !this.coordinator.f12281Y
                    && !this.coordinator.f12306t0
                    && this.appState.continuationMode != ContinuationPlayEnum.Disable.getOrder())
                previewNext();
            SubtitleModel active = null;
            if (this.coordinator.subtitleLines != null)
                for (Object row : this.coordinator.subtitleLines) {
                    SubtitleModel subtitle = (SubtitleModel) row;
                    if (subtitle.getFrom() * 1000 < position
                            && subtitle.getTo() * 1000 >= position) {
                        active = subtitle;
                        break;
                    }
                }
            E0.setVisibility(active == null ? View.GONE : View.VISIBLE);
            if (active != null) E0.setText(active.getContent());
            if (this.coordinator.f12306t0 && f9693F0 != null) f9693F0.f(position);
        }
        this.playbackHandler.postDelayed(this.progressTicker, 500);
    }

    private void previewNext() {
        PlaybackCoordinator state = this.coordinator;
        Object next = state.adjacentEpisode(1);
        if (next == null && state.f12278V == 0 && state.f12282Z) {
            List<VideoModel> list = null;
            if (this.appState.continuationMode == ContinuationPlayEnum.CurrentList.getOrder())
                list = state.f12273Q;
            else if (this.appState.continuationMode == ContinuationPlayEnum.Recommend.getOrder())
                list = state.f12272P;
            if (list != null && !list.isEmpty()) next = list.get(0);
        }
        if (next instanceof VideoModel)
            f0(((VideoModel) next).getTitle(), ((VideoModel) next).getPic());
        else if (next instanceof EpisodeModel) f0(((EpisodeModel) next).getLong_title(), null);
        else if (next instanceof UgcEpisodeModel)
            f0(
                    ((UgcEpisodeModel) next).getTitle(),
                    ((UgcEpisodeModel) next).getArc() == null
                            ? null
                            : ((UgcEpisodeModel) next).getArc().getPic());
        else if (next instanceof VideoPvModel) f0(((VideoPvModel) next).getPart(), null);
        if (next != null) k0(true);
        state.f12281Y = true;
    }

    private void resumeAfterDialog(android.app.Dialog dialog, boolean resume) {
        dialog.setOnDismissListener(
                ignored -> {
                    if (this.player != null && resume) {
                        if (!isHidden()) this.player.play();
                        else resumeWhenShown = true;
                    }
                });
    }

    public static void V(PlaybackFragment fragment) {
        fragment.chooseEpisode();
    }

    public static void W(PlaybackFragment fragment) {
        VideoModel video = fragment.coordinator.video;
        if (!fragment.q() || video == null || video.getOwner() == null) return;
        boolean resume = fragment.player != null && fragment.player.getPlayWhenReady();
        if (fragment.player != null) fragment.player.pause();
        OwnerDialog dialog = new OwnerDialog(fragment.requireContext());
        dialog.bindOwner(video.getOwner(), video);
        dialog.f12067x.d =
                (ItemClickCallback)
                        (view, position) -> {
                            Object selected = dialog.f12067x.f10342b.get(position);
                            if (selected instanceof VideoModel) {
                                fragment.a0();
                                fragment.coordinator.video = (VideoModel) selected;
                                fragment.coordinator.loadVideoDetails();
                                dialog.dismiss();
                            }
                        };
        fragment.resumeAfterDialog(dialog, resume);
        dialog.show();
    }

    private void chooseEpisode() {
        PlaybackCoordinator state = this.coordinator;
        if (!q() || state.video == null) return;
        List<?> entries = state.episodeChoices();
        if (entries.isEmpty()) return;
        boolean resume = this.player != null && this.player.getPlayWhenReady();
        if (this.player != null) this.player.pause();
        AppCompatDialog dialog = new AppCompatDialog(requireContext(), R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View content =
                LayoutInflater.from(dialog.getContext())
                        .inflate(R.layout.dialog_choose_episode, null);
        dialog.setContentView(content);
        RecyclerView recycler = content.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        List<?> alternate = entries.get(0) instanceof VideoPvModel
                ? state.collectionEpisodes()
                : Collections.emptyList();
        resumeAfterDialog(dialog, resume);
        dialog.show();
        bindEpisodeChoices(dialog, content, entries, alternate);
    }

    private void bindEpisodeChoices(
            AppCompatDialog dialog, View content, List<?> entries, List<?> alternate) {
        PlaybackCoordinator state = this.coordinator;
        Object first = entries.get(0);
        int mode = EpisodeListAdapter.PARTS;
        if (first instanceof EpisodeModel) mode = EpisodeListAdapter.SERIES;
        else if (first instanceof UgcEpisodeModel) mode = EpisodeListAdapter.COLLECTION;

        TextView title = content.findViewById(R.id.top_title);
        title.setText(mode == EpisodeListAdapter.COLLECTION
                ? state.d.getTitle()
                : state.video.getTitle());
        TextView toggle = content.findViewById(R.id.button_more_info);
        toggle.setVisibility(alternate.isEmpty() ? View.GONE : View.VISIBLE);
        toggle.setText(mode == EpisodeListAdapter.COLLECTION
                ? R.string.choose_video_part
                : R.string.choose_collection_video);
        toggle.setOnClickListener(v -> bindEpisodeChoices(dialog, content, alternate, entries));

        RecyclerView recycler = content.findViewById(R.id.recyclerView);
        EpisodeListAdapter adapter = new EpisodeListAdapter(mode);
        adapter.f10338b.addAll(entries);
        int current = state.currentEpisodeIndex(entries);
        adapter.f10339c = current;
        adapter.e = (clicked, position) -> {
            if (position < 0 || position >= entries.size()) return;
            state.playEpisode(entries.get(position));
            dialog.dismiss();
        };
        recycler.setAdapter(adapter);
        RecyclerFocus.request(recycler, Math.max(0, current));
    }

    private final class VideoActions implements OnVideoSettingChangeListener {
        public void onAspectRatioChange(int ratio) {
            PlaybackFragment.this.playerView.setResizeMode(ratio);
        }

        public void onAudioChange(int index) {
            S(PlaybackFragment.this, index);
        }

        public void onButtonClick(int action) {
            if (action == 0) V(PlaybackFragment.this);
            else if (action == 1) W(PlaybackFragment.this);
            else if (action == 3) l0(true);
            else if (action == 4) {
                f9697K0 = true;
                if (h() != null) h().onBackPressed();
            } else if (action == 5 && PlaybackFragment.this.player != null
                    && PlaybackFragment.this.coordinator.f12278V != 2) {
                PlaybackFragment.this.coordinator.f12289d0 = 0;
                PlaybackFragment.this.player.seekTo(0);
                PlaybackFragment.this.player.play();
                PlaybackFragment.this.playerView.showController();
            } else if (action == -1 && PlaybackFragment.this.coordinator.video != null) {
                VideoInfoDialog dialog = new VideoInfoDialog(requireContext());
                dialog.i(PlaybackFragment.this.coordinator.video);
                dialog.show();
            } else if (action == 2 && PlaybackFragment.this.coordinator.video != null) {
                boolean resume =
                        PlaybackFragment.this.player != null
                                && PlaybackFragment.this.player.getPlayWhenReady();
                if (PlaybackFragment.this.player != null) PlaybackFragment.this.player.pause();
                VideoActionsDialog dialog = new VideoActionsDialog(requireContext());
                VideoModel video = PlaybackFragment.this.coordinator.video;
                dialog.f12042L = video;
                dialog.j()
                        .hasLike(
                                video.getAid(),
                                video.getBvid(),
                                new VideoActionCallback(dialog, 1));
                dialog.j()
                        .hasCollection(
                                video.getAid(),
                                video.getBvid(),
                                new FavoriteActionCallback(dialog, 0));
                dialog.j()
                        .hasGiveCoin(
                                video.getAid(),
                                video.getBvid(),
                                new VideoActionCallback(dialog, 0));
                resumeAfterDialog(dialog, resume);
                dialog.show();
            }
        }

        public void onLiveQualityChange(int index) {
            LiveQuality quality =
                    index == 0
                            ? LiveQuality.Fluent
                            : index == 1
                                    ? LiveQuality.StandardDefinition
                                    : LiveQuality.HighDefinition;
            if (quality == PlaybackFragment.this.coordinator.f12270N
                    || PlaybackFragment.this.coordinator.video == null) return;
            PlaybackFragment.this.coordinator.f12270N = quality;
            PlaybackFragment.this
                    .coordinator
                    .network()
                    .getLiveUrl(
                            PlaybackFragment.this.coordinator.video.getAid(),
                            quality.getCode(),
                            new PlayerMetadataCallback(PlaybackFragment.this.coordinator, 0));
        }

        public void onMenuDown() {
            if (PlaybackFragment.this.coordinator.f12278V == 1
                    || PlaybackFragment.this.coordinator.d != null) V(PlaybackFragment.this);
            else if (PlaybackFragment.this.coordinator.f12278V == 0) W(PlaybackFragment.this);
        }

        public void onNext() {
            PlaybackFragment.this.coordinator.playNext(true);
        }

        public void onPlaySpeedChange(float speed) {
            PlaybackFragment.this.coordinator.f12271O = speed;
        }

        public void onPrevious() {
            previous();
        }

        public void onResolutionChange(int index) {
            T(PlaybackFragment.this, index);
        }

        public void onSubtitleChange(int index) {
            PlaybackFragment.this.coordinator.f12264G = index;
            PlaybackFragment.this.coordinator.loadSubtitle();
        }

        public void onVideoCodecChange(int index) {
            U(PlaybackFragment.this, index);
        }
    }

    private void previous() {
        this.coordinator.playEpisode(this.coordinator.adjacentEpisode(-1));
    }

    public boolean isPlaybackActive() {
        return canPlay();
    }

    public long liveChatGeneration() {
        return liveChatGeneration;
    }

    public boolean acceptsLiveChat(long generation, long room) {
        return generation == liveChatGeneration
                && canPlay()
                && this.coordinator.f12278V == 2
                && this.coordinator.video != null
                && this.coordinator.video.getAid() == room;
    }

    private void closeLiveChat() {
        liveChatGeneration++;
        if (this.coordinator.liveChat != null) {
            this.coordinator.liveChat.release();
            this.coordinator.liveChat = null;
        }
    }

    private void requestLiveChat() {
        if (!canPlay() || this.coordinator.f12278V != 2 || this.coordinator.video == null) return;
        closeLiveChat();
        VideoModel video = this.coordinator.video;
        this.coordinator
                .network()
                .getLiveChatRoomUrl(
                        video.getAid(), new PlaybackMetadataCallback(this.coordinator, video, 0));
    }

    private boolean canPlay() {
        return q() && started && !isHidden() && this.playerView != null;
    }

    private void restoreVisibleSource() {
        if (!canPlay() || verificationRequired) return;
        resumeWhenShown = false;
        if (this.coordinator.f12278V == 2 && this.coordinator.liveStreams != null) Z();
        else if (this.coordinator.playInfo != null && (this.player == null || pendingPlayback))
            Y(this.coordinator.playInfo);
    }

    @Override
    public void onStart() {
        super.onStart();
        started = true;
        G();
    }

    public void G() {
        ScreenEvents.register(this);
        if (canPlay()) {
            this.playerView.setPremiumMember(this.appState.premiumMember);
            this.playerView.getAppController().setDisableRelatedKey(this.appState.simpleRemoteKeys);
            restoreVisibleSource();
            flushPendingDm();
            this.playerView.onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presentPlaybackVerification();
    }

    @Override
    public void onStop() {
        started = false;
        dismissPlaybackVerification();
        H();
        super.onStop();
    }

    public void H() {
        ScreenEvents.unregister(this);
        closeLiveChat();
        this.playbackHandler.removeCallbacks(this.progressTicker);
        a0();
        if (this.player != null) this.coordinator.f12289d0 = this.player.getCurrentPosition();
        if (this.playerView != null) this.playerView.onPause();
        releasePlayer();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) closeLiveChat();
        if (this.player != null) {
            if (hidden) {
                resumeWhenShown = this.player.getPlayWhenReady();
                this.player.pause();
                this.playbackHandler.removeCallbacks(this.progressTicker);
            } else if (canPlay()) {
                if (pendingPlayback) restoreVisibleSource();
                else if (resumeWhenShown) {
                    resumeWhenShown = false;
                    this.player.play();
                }
                if (this.coordinator.f12278V == 2 && this.coordinator.liveChat == null)
                    requestLiveChat();
                this.playerView
                        .getAppController()
                        .setDisableRelatedKey(this.appState.simpleRemoteKeys);
                flushPendingDm();
                this.playbackHandler.removeCallbacks(this.progressTicker);
                this.playbackHandler.postDelayed(this.progressTicker, 100);
            }
        } else if (!hidden) restoreVisibleSource();
    }

    @Override
    public void onMessageEvent(String event) {
        if ("keyMenuPress".equals(event) && isVisible() && this.playerView != null)
            this.playerView.showHideSettingView(true);
        if (("updateUserInfo".equals(event) || "signOut".equals(event)) && this.playerView != null)
            this.playerView.setPremiumMember(this.appState.premiumMember);
    }

    private void releasePlayer() {
        if (f9696I0 != null) {
            f9696I0.stop();
            f9696I0 = null;
        }
        if (this.player != null) {
            this.player.removeListener(this.playerListener);
            if (this.playerView != null) this.playerView.setPlayer(null);
            this.player.release();
            this.player = null;
        }
    }

    @Override
    public void onDestroyView() {
        z();
        super.onDestroyView();
    }

    public void z() {
        verificationRequired = false;
        dismissPlaybackVerification();
        pendingDmData = null;
        pendingDmReset = false;
        a0();
        destroyed = true;
        ScreenEvents.unregister(this);
        this.playbackHandler.removeCallbacksAndMessages(null);
        closeLiveChat();
        releasePlayer();
        if (this.playerView != null) this.playerView.destroy();
        this.playerView = null;
        f9704w0 = null;
        f9706y0 = null;
        f9705x0 = null;
        f9707z0 = null;
        E0 = null;
    }
}
