package com.xx.blbl.playback;

import android.content.Context;
import android.text.TextUtils;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.live.LiveQuality;
import com.xx.blbl.model.player.DashInfoModel;
import com.xx.blbl.model.player.DashMediaModel;
import com.xx.blbl.model.player.DolbyModel;
import com.xx.blbl.model.player.FlacInfoModel;
import com.xx.blbl.model.player.PlayInfoModel;
import com.xx.blbl.model.player.VideoCodecEnum;
import com.xx.blbl.model.player.VideoSegmentModel;
import com.xx.blbl.model.series.UgcEpisodeModel;
import com.xx.blbl.model.series.UgcSectionModel;
import com.xx.blbl.model.series.UgcSeriesModel;
import com.xx.blbl.model.subtitle.SubtitleInfoModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoPvModel;
import com.xx.blbl.model.video.quality.AudioQuality;
import com.xx.blbl.model.video.quality.FnvalGenerator;
import com.xx.blbl.model.video.quality.VideoQuality;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.VideoPlayResponse;
import com.xx.blbl.network.chat.WebSocketEcho;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.platform.VideoIdConverter;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.playback.PlaybackFragment;
import com.xx.blbl.ui.view.exoplayer.MyPlayerView;

import kotlin.jvm.internal.Intrinsics;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class PlaybackCoordinator {
    public int f12264G;
    public final ArrayList f12265H = new ArrayList();
    public ArrayList f12266I = new ArrayList();
    public ArrayList f12267J = new ArrayList();
    public VideoQuality f12268K;
    public AudioQuality f12269L;
    public VideoCodecEnum M;
    public LiveQuality f12270N;
    public float f12271O;
    public List f12272P;
    public List f12273Q;
    public final kotlin.Lazy<NetworkManager> f12274R;
    public final AppState settings;
    public final WeakReference fragmentReference;
    public int f12277U;
    public int f12278V;
    public int f12279W;
    public int f12280X;
    public boolean f12281Y;
    public boolean f12282Z;
    public Context context;
    public boolean f12284a0;
    public VideoModel video;
    public VideoCodecEnum f12286b0;
    public List<VideoPvModel> f12287c;
    public AudioQuality f12288c0;
    public UgcSeriesModel d;
    public long f12289d0;
    public List e;
    public int f12290e0;
    public List f12291f;
    public boolean f12292f0;
    public boolean f12293g0;
    public int f12294h0;
    public boolean f12295i0;
    public int f12296j0;
    public VideoQuality f12297k0;
    public boolean f12298l0;
    public boolean f12299m0;
    public boolean f12300n0;
    public boolean o0;
    public ArrayList videoSegments;
    public boolean f12302p0;
    public String f12303q0;
    public final long f12304r0;
    public float f12305s0;
    public boolean f12306t0;
    public String f12307u0;
    public List videoTracks;
    public long f12309v0;
    public ArrayList audioTracks;
    public WebSocketEcho liveChat;
    public List subtitleLines;
    public PlayInfoModel playInfo;
    public List liveStreams;
    public boolean f12315y0;
    public int f12316z;
    public final PlayerMetadataCallback liveDanmakuListener;

    public PlaybackCoordinator(PlaybackFragment uVar) {
        VideoQuality videoQuality = VideoQuality.Q1080P;
        this.f12268K = videoQuality;
        this.f12269L = AudioQuality.Q192K;
        this.M = VideoCodecEnum.HEVC;
        this.f12270N = LiveQuality.HighDefinition;
        this.f12271O = 1.0f;
        this.f12274R = kotlin.LazyKt.lazy(AppController::network);
        this.settings = AppController.state();
        this.fragmentReference = new WeakReference(uVar);
        this.f12277U = 1;
        this.f12282Z = true;
        this.f12286b0 = VideoCodecEnum.AVC;
        this.f12288c0 = this.f12269L;
        this.f12294h0 = 12;
        this.f12296j0 = 10;
        this.f12297k0 = videoQuality;
        this.f12298l0 = true;
        this.f12300n0 = true;
        this.f12303q0 = "";
        this.f12304r0 = System.currentTimeMillis();
        this.f12305s0 = 45.0f;
        this.f12307u0 = "";
        this.liveDanmakuListener = new PlayerMetadataCallback(this, 5);
    }

    public static final void a(PlaybackCoordinator eVar) {
        VideoModel videoModel = eVar.video;
        if (videoModel != null) {
            eVar.network()
                    .getInteractionVideoInfo(
                            Long.valueOf(videoModel.getAid()),
                            videoModel.getBvid(),
                            eVar.f12307u0,
                            eVar.f12309v0,
                            new InteractionCallback(eVar, videoModel));
        }
    }

    public final void onPlayInfo(PlayInfoModel playInfoModel) {
        FlacInfoModel flac;
        DashMediaModel audio;
        DolbyModel dolby;
        List<DashMediaModel> audio2;
        List<DashMediaModel> audio3;
        DashInfoModel dash = playInfoModel.getDash();
        this.videoTracks = dash != null ? dash.getVideo() : null;
        if (this.videoTracks != null) {
            ArrayList<DashMediaModel> selected = new ArrayList<>();
            for (Object row : this.videoTracks) {
                DashMediaModel track = (DashMediaModel) row;
                if (track != null && track.getId() == playInfoModel.getQuality())
                    selected.add(track);
            }
            if (!selected.isEmpty()) this.videoTracks = selected;
        }
        ArrayList arrayList = new ArrayList();
        DashInfoModel dash2 = playInfoModel.getDash();
        if (dash2 != null && (audio3 = dash2.getAudio()) != null) {
            arrayList.addAll(audio3);
        }
        DashInfoModel dash3 = playInfoModel.getDash();
        if (dash3 != null
                && (dolby = dash3.getDolby()) != null
                && (audio2 = dolby.getAudio()) != null) {
            arrayList.addAll(audio2);
        }
        DashInfoModel dash4 = playInfoModel.getDash();
        if (dash4 != null
                && (flac = dash4.getFlac()) != null
                && (audio = flac.getAudio()) != null) {
            arrayList.add(audio);
        }
        this.audioTracks = arrayList;
        ArrayList<VideoSegmentModel> durl = playInfoModel.getDurl();
        this.videoSegments = durl;
        WeakReference weakReference = this.fragmentReference;
        PlaybackFragment uVar = (PlaybackFragment) weakReference.get();
        if (uVar != null) {
            uVar.Y(playInfoModel);
        }
        if (this.f12278V == 0) {
            PlaybackFragment uVar2 = (PlaybackFragment) weakReference.get();
            if (uVar2 != null) {
                uVar2.m0(true);
            }
        } else {
            PlaybackFragment uVar3 = (PlaybackFragment) weakReference.get();
            if (uVar3 != null) {
                uVar3.m0(false);
            }
        }
        VideoModel videoModel = this.video;
        if (videoModel != null) {
            network()
                    .getVideoDetail(
                            Long.valueOf(videoModel.getAid()),
                            videoModel.getBvid(),
                            new PlaybackDetailsCallback(this, 4));
        }
        this.f12281Y = false;
        VideoModel videoModel2 = this.video;
        if (videoModel2 != null) {
            network()
                    .getPlayerInfoWbi(
                            Long.valueOf(videoModel2.getAid()),
                            videoModel2.getBvid(),
                            videoModel2.getCid(),
                            new PlaybackDetailsCallback(this, 3));
        }
        VideoModel videoModel3 = this.video;
        if (videoModel3 != null) {
            network()
                    .getWatchingNumber(
                            Long.valueOf(videoModel3.getAid()),
                            videoModel3.getBvid(),
                            videoModel3.getCid(),
                            new PlaybackDetailsCallback(this, 5));
        }
    }

    public final void loadPreferences() {
        MyPlayerView myPlayerView;
        android.content.SharedPreferences preferences =
                AppController.instance().getSharedPreferences("BLBL", 0);
        this.f12298l0 = preferences.getInt("dmSwitch", 1) == 1;
        AppState dVar = this.settings;
        this.f12282Z =
                dVar.continuationMode == ContinuationPlayEnum.Recommend.getOrder()
                        || dVar.continuationMode == ContinuationPlayEnum.CurrentList.getOrder()
                        || dVar.continuationMode == ContinuationPlayEnum.PlayEpisode.getOrder();
        this.f12284a0 = preferences.getBoolean("exitPlayerAfterPlay", false);
        this.f12300n0 = preferences.getBoolean("showDebug", false);
        this.f12299m0 = preferences.getInt("showSubtitle", 0) == 1;
        this.o0 = preferences.getBoolean("showFfRe", false);
        this.f12302p0 = preferences.getBoolean("showNextPrevious", false);
        this.f12271O = preferences.getFloat("defaultPlaySpeed", 1.0f);
        this.f12292f0 = preferences.getBoolean("showBottomProgressBar", false);
        this.f12293g0 = preferences.getBoolean("showDmSwitch", false);
        this.f12294h0 = preferences.getInt("dmFilterWeight", 12);
        this.f12295i0 = preferences.getBoolean("allowVipColorfulDm", false);
        this.f12296j0 = preferences.getInt("ffSeekSecond", 10);
        int iC = preferences.getInt("videoCodec", 1);
        for (VideoCodecEnum videoCodecEnum : VideoCodecEnum.values()) {
            if (videoCodecEnum.getCode() == iC) {
                this.f12286b0 = videoCodecEnum;
                this.M = videoCodecEnum;
            }
        }
        int i4 = preferences.getInt("defaultResolution", 80);
        for (VideoQuality videoQuality : VideoQuality.values()) {
            if (videoQuality.getCode() == i4) {
                this.f12268K = videoQuality;
            }
        }
        PlaybackFragment uVar = (PlaybackFragment) this.fragmentReference.get();
        if (uVar != null) {
            VideoCodecEnum videoCodec = this.f12286b0;
            Intrinsics.checkNotNullParameter(videoCodec, "videoCodec");
            if (uVar.q() && (myPlayerView = uVar.playerView) != null) {
                myPlayerView.selectVideoCodec(videoCodec);
            }
        }
        int i7 = preferences.getInt("defaultAudioTrack", 30280);
        for (AudioQuality audioQuality : AudioQuality.values()) {
            if (audioQuality.getCode() == i7) {
                this.f12269L = audioQuality;
            }
        }
        this.f12297k0 = this.f12268K;
        this.f12305s0 = (dVar.displayWidth / 1920.0f) * preferences.getInt("subtitleTextSize", 45);
    }

    public final String selectedAudioUrl() {
        ArrayList<DashMediaModel> arrayList = this.audioTracks;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        VideoCodecEnum videoCodecEnum = this.f12286b0;
        VideoCodecEnum videoCodecEnum2 = VideoCodecEnum.HEVCDolbyVision;
        WeakReference weakReference = this.fragmentReference;
        if (videoCodecEnum == videoCodecEnum2 || videoCodecEnum == VideoCodecEnum.HVC1) {
            for (DashMediaModel dashMediaModel : arrayList) {
                int id = dashMediaModel.getId();
                AudioQuality audioQuality = AudioQuality.DolbyAtoms;
                if (id == audioQuality.getCode() && !dashMediaModel.playableURLs().isEmpty()) {
                    PlaybackFragment uVar = (PlaybackFragment) weakReference.get();
                    if (uVar != null) {
                        uVar.d0(audioQuality);
                    }
                    this.f12269L = audioQuality;
                    return dashMediaModel.playableURLs().get(0);
                }
            }
        }
        for (DashMediaModel dashMediaModel2 : arrayList) {
            if (dashMediaModel2.getId() == this.f12288c0.getCode()
                    && !dashMediaModel2.playableURLs().isEmpty()) {
                PlaybackFragment uVar2 = (PlaybackFragment) weakReference.get();
                if (uVar2 != null) {
                    uVar2.d0(this.f12288c0);
                }
                return dashMediaModel2.playableURLs().get(0);
            }
        }
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object next = it.next();
        if (it.hasNext()) {
            int id2 = ((DashMediaModel) next).getId();
            do {
                Object next2 = it.next();
                int id3 = ((DashMediaModel) next2).getId();
                if (id2 < id3) {
                    next = next2;
                    id2 = id3;
                }
            } while (it.hasNext());
        }
        DashMediaModel dashMediaModel3 = (DashMediaModel) next;
        for (AudioQuality audioQuality2 : AudioQuality.values()) {
            if (audioQuality2.getCode() == dashMediaModel3.getId()) {
                this.f12288c0 = audioQuality2;
                this.f12269L = audioQuality2;
                PlaybackFragment uVar3 = (PlaybackFragment) weakReference.get();
                if (uVar3 != null) {
                    uVar3.d0(this.f12288c0);
                }
            }
        }
        return dashMediaModel3.playableURLs().get(0);
    }

    public final NetworkManager network() {
        return this.f12274R.getValue();
    }

    public final void loadCurrentMedia() {
        VideoModel videoModel = this.video;
        if (videoModel != null) {
            if (videoModel.getEpid() == 0 && videoModel.getSid() == 0) {
                requestVodPlayback();
                return;
            }
            VideoModel videoModel2 = this.video;
            if (videoModel2 != null) {
                NetworkManager networkManagerF = network();
                Long lValueOf = Long.valueOf(videoModel2.getAid());
                String bvid = videoModel2.getBvid();
                Long lValueOf2 = Long.valueOf(videoModel2.getEpid());
                Long lValueOf3 = Long.valueOf(videoModel2.getCid());
                int code = this.f12297k0.getCode();
                FnvalGenerator fnvalGenerator = FnvalGenerator.INSTANCE;
                networkManagerF.getVideoPlayPgcInfo(
                        lValueOf,
                        bvid,
                        lValueOf2,
                        lValueOf3,
                        code,
                        fnvalGenerator.getFnval(this.f12297k0),
                        fnvalGenerator.getFourk(this.f12297k0),
                        new PlaybackDetailsCallback(this, 1));
            }
        }
    }

    private long videoRequestGeneration;

    public final void requestVodPlayback() {
        PlaybackFragment player = (PlaybackFragment) this.fragmentReference.get();
        if (this.video == null || player == null || !player.q()) return;
        player.clearPlaybackVerification();
        requestVideoPlayInfo(++videoRequestGeneration, false);
    }

    private void requestVideoPlayInfo(long generation, boolean legacy) {
        VideoModel video = this.video;
        long aid = video.getAid();
        long cid = video.getCid();
        NetResultCallback<BaseResponse<PlayInfoModel>> callback =
                new NetResultCallback<BaseResponse<PlayInfoModel>>() {
                    private PlaybackFragment currentPlayer() {
                        PlaybackFragment player =
                                (PlaybackFragment) PlaybackCoordinator.this.fragmentReference.get();
                        if (generation != videoRequestGeneration
                                || PlaybackCoordinator.this.video == null
                                || PlaybackCoordinator.this.video.getAid() != aid
                                || PlaybackCoordinator.this.video.getCid() != cid
                                || player == null
                                || !player.q()) return null;
                        return player;
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        PlaybackFragment player = currentPlayer();
                        if (player != null)
                            player.showPlaybackRequestError(
                                    player.n(R.string.playback_request_failed));
                    }

                    @Override
                    public void onResponse(BaseResponse<PlayInfoModel> response) {
                        PlaybackFragment player = currentPlayer();
                        if (player == null) return;
                        VideoPlayResponse outcome = VideoPlayResponse.classify(response);
                        android.util.Log.d(
                                "BBLLPlayback", (legacy ? "legacy " : "primary ") + outcome);
                        switch (outcome) {
                            case VERIFICATION_REQUIRED:
                                player.showPlaybackVerification();
                                break;
                            case PLAYABLE:
                                onPlayInfo(response.getData());
                                break;
                            case MISSING_STREAMS:
                                if (!legacy) requestVideoPlayInfo(generation, true);
                                else
                                    player.showPlaybackRequestError(
                                            player.n(R.string.playback_no_stream));
                                break;
                            case FAILED:
                                String message = response == null ? null : response.getMessage();
                                player.showPlaybackRequestError(
                                        TextUtils.isEmpty(message)
                                                ? player.n(R.string.playback_request_failed)
                                                : message);
                                break;
                        }
                    }
                };
        int quality = f12297k0.getCode();
        FnvalGenerator generator = FnvalGenerator.INSTANCE;
        int fnval = generator.getFnval(f12297k0);
        int fourk = generator.getFourk(f12297k0);
        if (legacy)
            network().getVideoPlayInfo(aid, video.getBvid(), cid, quality, fnval, fourk, callback);
        else
            network()
                    .getVideoPlayInfoWbi(
                            aid, video.getBvid(), cid, quality, fnval, fourk, callback);
    }

    public final String selectedVideoUrl() {
        List<DashMediaModel> list = this.videoTracks;
        if (list == null || list.isEmpty()) {
            return "";
        }
        if (this.f12268K == VideoQuality.Dolby) {
            for (DashMediaModel dashMediaModel : list) {
                String codecs = dashMediaModel.getCodecs();
                VideoCodecEnum videoCodecEnum = VideoCodecEnum.HEVCDolbyVision;
                if (kotlin.text.StringsKt.startsWith(codecs, videoCodecEnum.getValue(), false)) {
                    if (dashMediaModel.playableURLs().isEmpty()) {
                        return "";
                    }
                    this.f12286b0 = videoCodecEnum;
                    if (dashMediaModel.playableURLs().size() > 0) {
                        String str = dashMediaModel.playableURLs().get(0);
                        Intrinsics.checkNotNull(str);
                        return str;
                    }
                    String str2 = dashMediaModel.playableURLs().get(0);
                    Intrinsics.checkNotNull(str2);
                    return str2;
                }
            }
            for (DashMediaModel dashMediaModel2 : list) {
                String codecs2 = dashMediaModel2.getCodecs();
                VideoCodecEnum videoCodecEnum2 = VideoCodecEnum.HVC1;
                if (kotlin.text.StringsKt.startsWith(codecs2, videoCodecEnum2.getValue(), false)) {
                    if (dashMediaModel2.playableURLs().isEmpty()) {
                        return "";
                    }
                    this.f12286b0 = videoCodecEnum2;
                    if (dashMediaModel2.playableURLs().size() > 0) {
                        String str3 = dashMediaModel2.playableURLs().get(0);
                        Intrinsics.checkNotNull(str3);
                        return str3;
                    }
                    String str4 = dashMediaModel2.playableURLs().get(0);
                    Intrinsics.checkNotNull(str4);
                    return str4;
                }
            }
        }
        for (DashMediaModel dashMediaModel3 : list) {
            if (kotlin.text.StringsKt.startsWith(
                    dashMediaModel3.getCodecs(), this.f12286b0.getValue(), false)) {
                if (dashMediaModel3.playableURLs().isEmpty()) {
                    return "";
                }
                if (dashMediaModel3.playableURLs().size() > 0) {
                    String str5 = dashMediaModel3.playableURLs().get(0);
                    Intrinsics.checkNotNull(str5);
                    return str5;
                }
                String str6 = dashMediaModel3.playableURLs().get(0);
                Intrinsics.checkNotNull(str6);
                return str6;
            }
        }
        for (DashMediaModel dashMediaModel4 : list) {
            if (kotlin.text.StringsKt.startsWith(
                    dashMediaModel4.getCodecs(), this.M.getValue(), false)) {
                if (dashMediaModel4.playableURLs().isEmpty()) {
                    return "";
                }
                if (dashMediaModel4.playableURLs().size() > 0) {
                    String str7 = dashMediaModel4.playableURLs().get(0);
                    Intrinsics.checkNotNull(str7);
                    return str7;
                }
                String str8 = dashMediaModel4.playableURLs().get(0);
                Intrinsics.checkNotNull(str8);
                return str8;
            }
        }
        if (((DashMediaModel) list.get(0)).playableURLs().isEmpty()) {
            return "";
        }
        if (((DashMediaModel) list.get(0)).playableURLs().size() > 0) {
            String str9 = ((DashMediaModel) list.get(0)).playableURLs().get(0);
            Intrinsics.checkNotNull(str9);
            return str9;
        }
        String str10 = ((DashMediaModel) list.get(0)).playableURLs().get(0);
        Intrinsics.checkNotNull(str10);
        return str10;
    }

    public final void loadSubtitle() {
        List list = this.f12291f;
        if (list != null) {
            int i4 = this.f12264G;
            if (i4 < 0
                    || i4 >= list.size()
                    || TextUtils.isEmpty(
                            ((SubtitleInfoModel) list.get(this.f12264G)).getSubtitle_url())) {
                this.subtitleLines = null;
            } else {
                network()
                        .getSubtitle(
                                ((SubtitleInfoModel) list.get(this.f12264G)).getSubtitle_url(),
                                new PlayerMetadataCallback(this, 3));
            }
        }
    }

    public final void loadVideoDetails() {
        loadVideoDetails(this.video == null ? null : this.video.getUgc_season());
    }

    private void loadVideoDetails(UgcSeriesModel collection) {
        this.d = collection;
        this.f12287c = this.video == null ? null : this.video.getPages();
        VideoModel videoModel;
        String bvid;
        VideoModel videoModel2;
        VideoModel videoModel3;
        int i4 = 0;
        this.f12278V = 0;
        VideoModel videoModel4 = this.video;
        if (videoModel4 != null) {
            String redirect_url = videoModel4.getRedirect_url();
            if (!TextUtils.isEmpty(redirect_url)) {
                try {
                    URL url = new URL(redirect_url);
                    String path = url.getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                    String str = path.substring(path.lastIndexOf('/') + 1);
                    if (kotlin.text.StringsKt.startsWith(str, "ss", false)
                            || kotlin.text.StringsKt.startsWith(str, "ep", false)) {
                        i4 = 1;
                    } else {
                        String host = url.getHost();
                        Intrinsics.checkNotNullExpressionValue(host, "getHost(...)");
                        if (kotlin.text.StringsKt.startsWith(host, "live", false)) {
                            i4 = 2;
                        }
                    }
                    this.f12278V = i4;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (videoModel4.getEpid() != 0 || videoModel4.getSid() != 0) {
                this.f12278V = 1;
            }
        }
        int i7 = this.f12278V;
        if (i7 == 1) {
            VideoModel videoModel5 = this.video;
            if (videoModel5 != null) {
                try {
                    if (videoModel5.getEpid() == 0 && videoModel5.getSid() == 0) {
                        n(videoModel5);
                    }
                    network()
                            .getVideoEpisodes(
                                    videoModel5.getSid() == 0
                                            ? null
                                            : Long.valueOf(videoModel5.getSid()),
                                    videoModel5.getEpid() == 0
                                            ? null
                                            : Long.valueOf(videoModel5.getEpid()),
                                    new PlaybackMetadataCallback(this, videoModel5, 1));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    VideoModel videoModel6 = this.video;
                    if (videoModel6 != null) {
                        network()
                                .getVideoPv(
                                        Long.valueOf(videoModel6.getAid()),
                                        videoModel6.getBvid(),
                                        new PlayerMetadataCallback(this, 4));
                    }
                }
            }
            VideoModel videoModel7 = this.video;
            android.util.Log.d(
                    "playPgc", String.valueOf(videoModel7 != null ? videoModel7.getTitle() : null));
        } else if (i7 != 2) {
            VideoModel videoModel8 = this.video;
            if (videoModel8 != null) {
                network()
                        .getVideoPv(
                                Long.valueOf(videoModel8.getAid()),
                                videoModel8.getBvid(),
                                new PlayerMetadataCallback(this, 4));
            }
            VideoModel videoModel9 = this.video;
            android.util.Log.d(
                    "playUgc", String.valueOf(videoModel9 != null ? videoModel9.getTitle() : null));
        } else {
            VideoModel videoModel10 = this.video;
            if (videoModel10 != null) {
                network()
                        .getLiveUrl(
                                videoModel10.getAid(),
                                this.f12270N.getCode(),
                                new PlayerMetadataCallback(this, 0));
            }
            VideoModel videoModel11 = this.video;
            android.util.Log.d(
                    "playLive",
                    String.valueOf(videoModel11 != null ? videoModel11.getTitle() : null));
        }
        this.subtitleLines = null;
        VideoModel videoModel12 = this.video;
        if (videoModel12 != null
                && (bvid = videoModel12.getBvid()) != null
                && (videoModel2 = this.video) != null
                && videoModel2.getAid() == 0
                && !TextUtils.isEmpty(bvid)
                && (videoModel3 = this.video) != null) {
            videoModel3.setAid(VideoIdConverter.toAid(bvid).longValue());
        }
        VideoModel videoModel13 = this.video;
        if (videoModel13 != null) {
            long aid = videoModel13.getAid();
            if (aid != 0) {
                VideoModel videoModel14 = this.video;
                if (!TextUtils.isEmpty(videoModel14 != null ? videoModel14.getBvid() : null)
                        || (videoModel = this.video) == null) {
                    return;
                }
                String strA = VideoIdConverter.toBvid(Long.valueOf(aid));
                Intrinsics.checkNotNullExpressionValue(strA, "av2bv(...)");
                videoModel.setBvid(strA);
            }
        }
    }

    public final void l() {
        PlaybackFragment uVar;
        if (!this.f12284a0 || (uVar = (PlaybackFragment) this.fragmentReference.get()) == null) {
            return;
        }
        uVar.f9697K0 = true;
        MainActivity mainActivityH = uVar.h();
        if (mainActivityH != null) {
            mainActivityH.onBackPressed();
        }
    }

    public List<VideoPvModel> videoParts() {
        return this.f12287c == null ? Collections.emptyList() : this.f12287c;
    }

    public List<UgcEpisodeModel> collectionEpisodes() {
        if (this.d == null) return Collections.emptyList();
        List<UgcSectionModel> sections = this.d.getSections();
        if (sections == null || sections.isEmpty()) return Collections.emptyList();
        List<UgcEpisodeModel> episodes = sections.get(0).getEpisodes();
        return episodes == null ? Collections.emptyList() : episodes;
    }

    public List<?> episodeChoices() {
        if (this.f12278V == 1) return this.e == null ? Collections.emptyList() : this.e;
        if (this.f12278V != 0) return Collections.emptyList();
        List<VideoPvModel> parts = videoParts();
        List<UgcEpisodeModel> collection = collectionEpisodes();
        return parts.size() > 1 || collection.isEmpty() ? parts : collection;
    }

    public int currentEpisodeIndex(List<?> entries) {
        if (this.video == null) return -1;
        long cid = this.video.getCid();
        long aid = this.video.getAid();
        String bvid = this.video.getBvid();
        for (int index = 0; index < entries.size(); index++) {
            Object entry = entries.get(index);
            if (entry instanceof VideoPvModel && ((VideoPvModel) entry).getCid() == cid)
                return index;
            if (entry instanceof EpisodeModel && ((EpisodeModel) entry).getCid() == cid)
                return index;
            if (entry instanceof UgcEpisodeModel) {
                UgcEpisodeModel episode = (UgcEpisodeModel) entry;
                // A collection entry represents a whole video, including all of its CIDs.
                if ((aid > 0 && episode.getAid() == aid)
                        || (!TextUtils.isEmpty(bvid) && bvid.equals(episode.getBvid())))
                    return index;
            }
        }
        return -1;
    }

    private Object adjacentEpisode(List<?> entries, int offset) {
        int current = currentEpisodeIndex(entries);
        int target = current + offset;
        return current >= 0 && target >= 0 && target < entries.size() ? entries.get(target) : null;
    }

    public Object adjacentEpisode(int offset) {
        if (this.f12278V == 1) return adjacentEpisode(episodeChoices(), offset);
        if (this.f12278V != 0) return null;
        Object part = adjacentEpisode(videoParts(), offset);
        return part != null ? part : adjacentEpisode(collectionEpisodes(), offset);
    }

    public void playEpisode(Object entry) {
        if (this.video == null || entry == null) return;
        this.f12289d0 = 0;
        if (entry instanceof UgcEpisodeModel) {
            UgcEpisodeModel episode = (UgcEpisodeModel) entry;
            this.video = episode.getArc() == null ? new VideoModel() : episode.getArc();
            this.video.setCid(episode.getCid());
            this.video.setBvid(episode.getBvid());
            this.video.setAid(episode.getAid());
            this.video.setTitle(episode.getTitle());
            loadVideoDetails(this.d);
            return;
        }
        if (entry instanceof EpisodeModel) {
            EpisodeModel episode = (EpisodeModel) entry;
            this.video.setCid(episode.getCid());
            this.video.setBvid(episode.getBvid());
            this.video.setAid(episode.getAid());
            this.video.setEpid(episode.getId());
        } else {
            this.video.setCid(((VideoPvModel) entry).getCid());
        }
        PlaybackFragment player = (PlaybackFragment) this.fragmentReference.get();
        if (player != null) player.updateEpisodeHeader();
        loadCurrentMedia();
    }

    public final void playNext(boolean manual) {
        int continuation = this.settings.continuationMode;
        if (!manual && continuation == ContinuationPlayEnum.Disable.getOrder()) {
            l();
            return;
        }
        if (this.f12306t0) return;
        Object nextEpisode = adjacentEpisode(1);
        if (nextEpisode != null) {
            playEpisode(nextEpisode);
            return;
        }
        if (this.f12278V == 0) {
            boolean queueAllowed = manual || continuation == ContinuationPlayEnum.CurrentList.getOrder();
            boolean recommendAllowed = manual || continuation == ContinuationPlayEnum.Recommend.getOrder();
            if (queueAllowed && this.f12273Q != null && !this.f12273Q.isEmpty() && this.f12282Z) {
                this.video = (VideoModel) this.f12273Q.get(0);
                loadVideoDetails();
                PlaybackFragment player = (PlaybackFragment) this.fragmentReference.get();
                if (player != null) player.l0(false);
                this.f12273Q = this.f12273Q.size() > 1
                        ? this.f12273Q.subList(1, this.f12273Q.size())
                        : null;
                return;
            }
            if (recommendAllowed && this.f12272P != null && !this.f12272P.isEmpty() && this.f12282Z) {
                this.video = (VideoModel) this.f12272P.get(0);
                loadVideoDetails();
                PlaybackFragment player = (PlaybackFragment) this.fragmentReference.get();
                if (player != null) player.l0(false);
                return;
            }
        }
        l();
    }

    public final void n(VideoModel videoModel) {
        VideoModel videoModel2;
        VideoModel videoModel3;
        try {
            String path = new URL(videoModel.getRedirect_url()).getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            List<String> listL = java.util.Arrays.asList(path.split("/", -1));
            String str = (String) listL.get(listL.size() - 1);
            if (kotlin.text.StringsKt.startsWith(str, "ss", false)
                    && (videoModel3 = this.video) != null) {
                videoModel3.setSid(Long.parseLong(str.replace("ss", "")));
            }
            if (kotlin.text.StringsKt.startsWith(str, "ep", false)
                    && (videoModel2 = this.video) != null) {
                videoModel2.setEpid(Long.parseLong(str.replace("ep", "")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
