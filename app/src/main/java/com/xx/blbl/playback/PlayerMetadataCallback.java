package com.xx.blbl.playback;

import android.content.Context;

import androidx.media3.exoplayer.ExoPlayer;

import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.xx.blbl.R;
import com.xx.blbl.model.dm.DmModel;
import com.xx.blbl.model.interaction.InteractionInfo;
import com.xx.blbl.model.live.LiveDUrlModel;
import com.xx.blbl.model.live.LivePlayUrlDataModel;
import com.xx.blbl.model.subtitle.SubtitleInfoModel;
import com.xx.blbl.model.subtitle.SubtitleModel;
import com.xx.blbl.model.subtitle.SubtitleWrapper;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoPvModel;
import com.xx.blbl.network.ApiFailure;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.chat.WebSocketEcho;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.PlayerInfoDataWrapper;
import com.xx.blbl.network.response.SubtitleResponse;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.dialog.VideoActionPresenter;
import com.xx.blbl.ui.playback.PlaybackFragment;
import com.xx.blbl.ui.view.exoplayer.MyPlayerView;
import com.xx.blbl.platform.RequestMessages;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class PlayerMetadataCallback implements NetResultCallback, WebSocketEcho.DmListener {
    public final /* synthetic */ int f12260a;
    public final /* synthetic */ PlaybackCoordinator f12261b;
    private final VideoModel requestedVideo;
    private final long requestedCid;

    public /* synthetic */ PlayerMetadataCallback(PlaybackCoordinator eVar, int i4) {
        this.f12260a = i4;
        this.f12261b = eVar;
        this.requestedVideo = eVar.video;
        this.requestedCid = eVar.video == null ? 0 : eVar.video.getCid();
    }

    private boolean currentPageRequest() {
        return f12260a != 4 || (requestedVideo != null
                && f12261b.video == requestedVideo && requestedVideo.getCid() == requestedCid);
    }

    private final void a(Throwable th) {}

    private final void b(Throwable th) {}

    public void onDm(DmModel dmModel) {
        PlaybackCoordinator state = this.f12261b;
        PlaybackFragment fragment = (PlaybackFragment) state.fragmentReference.get();
        if (fragment == null) return;
        onDm(
                dmModel,
                fragment.liveChatGeneration(),
                state.video == null ? 0 : state.video.getAid());
    }

    public void onDm(DmModel dmModel, long generation, long room) {
        PlaybackCoordinator state = this.f12261b;
        PlaybackFragment fragment = (PlaybackFragment) state.fragmentReference.get();
        if (fragment == null) return;
        // WebSocket transport callbacks run off the Android main thread.
        fragment.playbackHandler.post(
                () -> {
                    if (!state.f12298l0
                            || !fragment.acceptsLiveChat(generation, room)
                            || fragment.playerView == null) return;
                    MyPlayerView player = fragment.playerView;
                    dmModel.setProgress((int) (player.getCurrentDmTimeMs() + 500L));
                    int mode = dmModel.getMode() == 4 ? 4 : dmModel.getMode() == 5 ? 5 : 1;
                    player.addOneDm(
                            new DanmakuItemData(
                                    System.currentTimeMillis(),
                                    dmModel.getProgress(),
                                    dmModel.getContent(),
                                    mode,
                                    25,
                                    dmModel.getColor(),
                                    0,
                                    DanmakuItemData.DANMAKU_STYLE_NONE,
                                    0,
                                    null,
                                    0));
                });
    }

    @Override
    public void onFailure(Throwable th) {
        PlaybackFragment active = (PlaybackFragment) this.f12261b.fragmentReference.get();
        if (active == null || !active.q() || !currentPageRequest()) return;
        if (f12260a == 4 && requestedCid <= 0) {
            active.showPlaybackRequestError(RequestMessages.error(active.requireContext(), th));
            return;
        }
        switch (this.f12260a) {
            case 0:
                PlaybackFragment uVar = (PlaybackFragment) this.f12261b.fragmentReference.get();
                if (uVar != null) {
                    uVar.j0(String.valueOf(th != null ? th.getMessage() : null));
                }
                break;
            case 2:
            case 3:
                break;
            default:
                PlaybackCoordinator eVar2 = this.f12261b;
                Context context2 = eVar2.context;
                if (context2 != null) {
                    VideoActionPresenter.toast(
                            context2, String.valueOf(th != null ? th.getMessage() : null));
                }
                eVar2.loadCurrentMedia();
                break;
        }
    }

    @Override
    public void onResponse(Object obj) {
        PlaybackFragment active = (PlaybackFragment) this.f12261b.fragmentReference.get();
        if (active == null || !active.q() || !currentPageRequest()) return;
        Context context;
        LivePlayUrlDataModel livePlayUrlDataModel;
        List<LiveDUrlModel> durl;
        PlaybackFragment uVar;
        PlayerInfoDataWrapper playerInfoDataWrapper;
        InteractionInfo interaction;
        ExoPlayer f7;
        PlayerInfoDataWrapper playerInfoDataWrapper2;
        SubtitleWrapper subtitle;
        List<SubtitleInfoModel> subtitles;
        MyPlayerView myPlayerView;
        List<SubtitleModel> body;
        List list;
        switch (this.f12260a) {
            case 0:
                BaseResponse baseResponse = (BaseResponse) obj;
                PlaybackCoordinator eVar = this.f12261b;
                if (baseResponse != null
                        && (livePlayUrlDataModel = (LivePlayUrlDataModel) baseResponse.getData())
                                != null
                        && (durl = livePlayUrlDataModel.getDurl()) != null) {
                    eVar.liveStreams = durl;
                    PlaybackFragment uVar2 = (PlaybackFragment) eVar.fragmentReference.get();
                    if (uVar2 != null) {
                        uVar2.Z();
                    }
                }
                if (baseResponse == null || baseResponse.getCode() != 0) {
                    if (!eVar.settings.premiumMember && (context = eVar.context) != null) {
                        VideoActionPresenter.toast(
                                context, context.getString(R.string.play_fail_vip));
                    }
                    PlaybackFragment uVar3 = (PlaybackFragment) eVar.fragmentReference.get();
                    if (uVar3 != null) {
                        uVar3.j0(
                                String.valueOf(
                                        baseResponse != null ? baseResponse.getMessage() : null));
                    }
                }
                break;
            case 2:
                BaseResponse baseResponse3 = (BaseResponse) obj;
                PlaybackCoordinator eVar3 = this.f12261b;
                if (baseResponse3 == null
                        || (playerInfoDataWrapper2 =
                                        (PlayerInfoDataWrapper) baseResponse3.getData())
                                == null
                        || (subtitle = playerInfoDataWrapper2.getSubtitle()) == null
                        || (subtitles = subtitle.getSubtitles()) == null) {
                    uVar = (PlaybackFragment) eVar3.fragmentReference.get();
                    if (uVar != null) {
                        uVar.n0(false);
                    }
                } else {
                    boolean zIsEmpty = subtitles.isEmpty();
                    Unit jVar = Unit.INSTANCE;
                    if (zIsEmpty) {
                        PlaybackFragment uVar5 = (PlaybackFragment) eVar3.fragmentReference.get();
                        if (uVar5 != null) {
                            uVar5.n0(false);
                        } else {
                            jVar = null;
                        }
                    } else {
                        eVar3.f12291f = subtitles;
                        eVar3.f12264G = eVar3.f12299m0 ? 0 : subtitles.size();
                        WeakReference weakReference = eVar3.fragmentReference;
                        PlaybackFragment uVar6 = (PlaybackFragment) weakReference.get();
                        if (uVar6 != null) {
                            uVar6.i0((ArrayList) subtitles);
                        }
                        PlaybackFragment uVar7 = (PlaybackFragment) weakReference.get();
                        if (uVar7 != null) {
                            int i4 = eVar3.f12264G;
                            if (uVar7.q() && (myPlayerView = uVar7.playerView) != null) {
                                myPlayerView.selectSubtitle(i4);
                            }
                        }
                        eVar3.loadSubtitle();
                    }
                    if (jVar == null) {
                        uVar = (PlaybackFragment) eVar3.fragmentReference.get();
                        if (uVar != null) {
                            uVar.n0(false);
                        }
                    }
                }
                if (baseResponse3 != null
                        && (playerInfoDataWrapper = (PlayerInfoDataWrapper) baseResponse3.getData())
                                != null
                        && (interaction = playerInfoDataWrapper.getInteraction()) != null
                        && !Intrinsics.areEqual(interaction.getGraph_version(), "")) {
                    eVar3.f12306t0 = true;
                    String graph_version = interaction.getGraph_version();
                    Intrinsics.checkNotNullParameter(graph_version, "<set-?>");
                    eVar3.f12307u0 = graph_version;
                    PlaybackFragment uVar8 = (PlaybackFragment) eVar3.fragmentReference.get();
                    if (uVar8 != null && (f7 = uVar8.player) != null) {
                        f7.seekTo(0L);
                    }
                    PlaybackCoordinator.a(eVar3);
                    break;
                }
                break;
            case 3:
                SubtitleResponse subtitleResponse = (SubtitleResponse) obj;
                if (subtitleResponse != null && (body = subtitleResponse.getBody()) != null) {
                    this.f12261b.subtitleLines = body;
                    break;
                }
                break;
            default:
                BaseResponse baseResponse4 = (BaseResponse) obj;
                PlaybackCoordinator eVar4 = this.f12261b;
                ApiFailure failure = ApiFailure.fromResponse(baseResponse4);
                if (failure != null && eVar4.video != null && eVar4.video.getCid() <= 0) {
                    active.showPlaybackRequestError(
                            RequestMessages.error(active.requireContext(), failure));
                    return;
                }
                if (baseResponse4 == null || baseResponse4.getCode() != 0) {
                    Context context3 = eVar4.context;
                    if (context3 instanceof MainActivity) {
                        Intrinsics.checkNotNull(
                                context3,
                                "null cannot be cast to non-null type com.xx.blbl.ui.MainActivity");
                        if (!((MainActivity) context3).isFinishing()) {
                            VideoActionPresenter.toast(
                                    eVar4.context,
                                    String.valueOf(
                                            baseResponse4 != null
                                                    ? baseResponse4.getMessage()
                                                    : null));
                        }
                    }
                }
                if (baseResponse4 != null && (list = (List) baseResponse4.getData()) != null) {
                    eVar4.f12287c = list;
                    // Search and dynamic feeds omit cid. Resolve it from the page list before
                    // requesting a stream, while preserving a selected/history page's cid.
                    if (eVar4.video != null && eVar4.video.getCid() <= 0)
                        for (Object entry : list) {
                            long cid = ((VideoPvModel) entry).getCid();
                            if (cid > 0) {
                                eVar4.video.setCid(cid);
                                break;
                            }
                        }
                    PlaybackFragment uVar9 = (PlaybackFragment) eVar4.fragmentReference.get();
                    if (uVar9 != null) {
                        uVar9.p0();
                    }
                }
                if (eVar4.video == null || eVar4.video.getCid() <= 0) {
                    active.showPlaybackRequestError(active.n(R.string.playback_no_stream));
                    return;
                }
                eVar4.loadCurrentMedia();
                break;
        }
    }
}
