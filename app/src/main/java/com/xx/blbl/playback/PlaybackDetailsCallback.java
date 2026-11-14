package com.xx.blbl.playback;

import android.content.Context;
import android.text.TextUtils;

import androidx.media3.exoplayer.ExoPlayer;

import com.xx.blbl.R;
import com.xx.blbl.model.interaction.InteractionInfo;
import com.xx.blbl.model.player.PlayInfoModel;
import com.xx.blbl.model.player.WatchingTotalNumberModel;
import com.xx.blbl.model.series.UgcSectionModel;
import com.xx.blbl.model.series.UgcSeriesModel;
import com.xx.blbl.model.subtitle.SubtitleInfoModel;
import com.xx.blbl.model.subtitle.SubtitleWrapper;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.detail.VideoDetailModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.PlayerInfoDataWrapper;
import com.xx.blbl.ui.adapter.RelatedVideosAdapter;
import com.xx.blbl.ui.dialog.VideoActionPresenter;
import com.xx.blbl.ui.playback.PlaybackFragment;
import com.xx.blbl.ui.view.exoplayer.MyPlayerView;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class PlaybackDetailsCallback implements NetResultCallback {
    public final /* synthetic */ int f12258a;
    public final /* synthetic */ PlaybackCoordinator f12259b;

    private final long requestCid;

    public /* synthetic */ PlaybackDetailsCallback(PlaybackCoordinator eVar, int i4) {
        this.f12258a = i4;
        this.f12259b = eVar;
        this.requestCid = eVar.video == null ? 0 : eVar.video.getCid();
    }

    private final void a(Throwable th) {}

    private final void b(Throwable th) {}

    private final void c(Throwable th) {}

    @Override
    public final void onFailure(Throwable th) {
        PlaybackFragment active = (PlaybackFragment) this.f12259b.fragmentReference.get();
        if (active == null || !active.q()) return;
        switch (this.f12258a) {
            case 1:
                PlaybackCoordinator eVar = this.f12259b;
                PlaybackFragment uVar = (PlaybackFragment) eVar.fragmentReference.get();
                if (uVar != null) {
                    uVar.j0(String.valueOf(th != null ? th.getMessage() : null));
                }
                Context context = eVar.context;
                if (context != null) {
                    VideoActionPresenter.toast(
                            context, String.valueOf(th != null ? th.getMessage() : null));
                }
                break;
            case 4:
                PlaybackFragment uVar3 = (PlaybackFragment) this.f12259b.fragmentReference.get();
                if (uVar3 != null) {
                    uVar3.m0(false);
                }
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        PlaybackFragment active = (PlaybackFragment) this.f12259b.fragmentReference.get();
        if (active == null || !active.q()) return;
        MyPlayerView myPlayerView;
        Context context;
        PlayInfoModel playInfoModel;
        PlaybackFragment uVar;
        VideoModel videoModel;
        PlayerInfoDataWrapper playerInfoDataWrapper;
        InteractionInfo interaction;
        ExoPlayer f7;
        PlayerInfoDataWrapper playerInfoDataWrapper2;
        SubtitleWrapper subtitle;
        List<SubtitleInfoModel> subtitles;
        MyPlayerView myPlayerView2;
        VideoDetailModel videoDetailModel;
        boolean z7;
        MyPlayerView myPlayerView3;
        OwnerModel owner;
        List<UgcSectionModel> sections;
        List list;
        RelatedVideosAdapter hVar;
        WatchingTotalNumberModel watchingTotalNumberModel;
        String total;
        switch (this.f12258a) {
            case 0:
                List list2 = (List) obj;
                if (list2 != null) {
                    PlaybackCoordinator eVar = this.f12259b;
                    if (eVar.video == null || eVar.video.getCid() != requestCid) return;
                    PlaybackFragment uVar3 = (PlaybackFragment) eVar.fragmentReference.get();
                    if (uVar3 != null) {
                        uVar3.e0(DanmakuBridge.convert(list2), true);
                    }
                }
                break;
            case 1:
                Base2Response<PlayInfoModel> response = (Base2Response<PlayInfoModel>) obj;
                PlaybackCoordinator state = this.f12259b;
                PlaybackFragment player = (PlaybackFragment) state.fragmentReference.get();
                if (player == null
                        || !player.q()
                        || state.video == null
                        || state.video.getCid() != requestCid) return;
                com.xx.blbl.network.VideoPlayResponse outcome =
                        com.xx.blbl.network.VideoPlayResponse.classifyPgc(response);
                if (outcome == com.xx.blbl.network.VideoPlayResponse.PLAYABLE)
                    state.onPlayInfo(response.getResult());
                else if (outcome == com.xx.blbl.network.VideoPlayResponse.VERIFICATION_REQUIRED)
                    player.showPlaybackVerification();
                else if (outcome == com.xx.blbl.network.VideoPlayResponse.MISSING_STREAMS)
                    player.showPlaybackRequestError(player.n(R.string.playback_no_stream));
                else
                    player.showPlaybackRequestError(
                            com.xx.blbl.platform.RequestMessages.error(
                                    player.requireContext(),
                                    com.xx.blbl.network.ApiFailure.fromResponse(response)));
                break;
            case 3:
                BaseResponse baseResponse2 = (BaseResponse) obj;
                PlaybackCoordinator eVar4 = this.f12259b;
                if (baseResponse2 == null
                        || (playerInfoDataWrapper2 =
                                        (PlayerInfoDataWrapper) baseResponse2.getData())
                                == null
                        || (subtitle = playerInfoDataWrapper2.getSubtitle()) == null
                        || (subtitles = subtitle.getSubtitles()) == null) {
                    uVar = (PlaybackFragment) eVar4.fragmentReference.get();
                    if (uVar != null) {
                        uVar.n0(false);
                    }
                } else {
                    boolean zIsEmpty = subtitles.isEmpty();
                    Unit jVar2 = Unit.INSTANCE;
                    if (zIsEmpty) {
                        PlaybackFragment uVar5 = (PlaybackFragment) eVar4.fragmentReference.get();
                        if (uVar5 != null) {
                            uVar5.n0(false);
                        } else {
                            jVar2 = null;
                        }
                    } else {
                        eVar4.f12291f = subtitles;
                        eVar4.f12264G = eVar4.f12299m0 ? 0 : subtitles.size();
                        WeakReference weakReference = eVar4.fragmentReference;
                        PlaybackFragment uVar6 = (PlaybackFragment) weakReference.get();
                        if (uVar6 != null) {
                            uVar6.i0((ArrayList) subtitles);
                        }
                        PlaybackFragment uVar7 = (PlaybackFragment) weakReference.get();
                        if (uVar7 != null) {
                            int i4 = eVar4.f12264G;
                            if (uVar7.q() && (myPlayerView2 = uVar7.playerView) != null) {
                                myPlayerView2.selectSubtitle(i4);
                            }
                        }
                        eVar4.loadSubtitle();
                    }
                    if (jVar2 == null) {
                        uVar = (PlaybackFragment) eVar4.fragmentReference.get();
                        if (uVar != null) {
                            uVar.n0(false);
                        }
                    }
                }
                if (baseResponse2 != null
                        && (playerInfoDataWrapper = (PlayerInfoDataWrapper) baseResponse2.getData())
                                != null
                        && (interaction = playerInfoDataWrapper.getInteraction()) != null
                        && !Intrinsics.areEqual(interaction.getGraph_version(), "")) {
                    eVar4.f12306t0 = true;
                    String graph_version = interaction.getGraph_version();
                    Intrinsics.checkNotNullParameter(graph_version, "<set-?>");
                    eVar4.f12307u0 = graph_version;
                    PlaybackFragment uVar8 = (PlaybackFragment) eVar4.fragmentReference.get();
                    if (uVar8 != null && (f7 = uVar8.player) != null) {
                        f7.seekTo(0L);
                    }
                    PlaybackCoordinator.a(eVar4);
                }
                if ((baseResponse2 == null || baseResponse2.getCode() != 0)
                        && (videoModel = eVar4.video) != null) {
                    eVar4.network()
                            .getPlayerInfo(
                                    Long.valueOf(videoModel.getAid()),
                                    videoModel.getBvid(),
                                    videoModel.getCid(),
                                    new PlayerMetadataCallback(eVar4, 2));
                }
                break;
            case 4:
                BaseResponse baseResponse3 = (BaseResponse) obj;
                PlaybackCoordinator eVar5 = this.f12259b;
                if (baseResponse3 != null
                        && (videoDetailModel = (VideoDetailModel) baseResponse3.getData())
                                != null) {
                    List<VideoModel> related = videoDetailModel.getRelated();
                    if (related != null) {
                        if (related.isEmpty()) {
                            PlaybackFragment uVar9 =
                                    (PlaybackFragment) eVar5.fragmentReference.get();
                            if (uVar9 != null) {
                                uVar9.m0(false);
                            }
                        } else {
                            eVar5.f12272P = related;
                            WeakReference weakReference2 = eVar5.fragmentReference;
                            PlaybackFragment uVar10 = (PlaybackFragment) weakReference2.get();
                            if (uVar10 != null
                                    && uVar10.q()
                                    && (list = uVar10.coordinator.f12272P) != null
                                    && (hVar = uVar10.f9706y0) != null) {
                                ArrayList arrayList = hVar.f10350a;
                                arrayList.clear();
                                arrayList.addAll(list);
                                hVar.notifyDataSetChanged();
                            }
                            PlaybackFragment uVar11 = (PlaybackFragment) weakReference2.get();
                            if (uVar11 != null) {
                                uVar11.m0(true);
                            }
                        }
                    }
                    VideoModel view = videoDetailModel.getView();
                    Unit jVar3 = null;
                    String mid = null;
                    jVar3 = null;
                    if (view != null) {
                        UgcSeriesModel ugc_season = view.getUgc_season();
                        if (ugc_season != null
                                && (sections = ugc_season.getSections()) != null
                                && !sections.isEmpty()) {
                            eVar5.d = ugc_season;
                        }
                        VideoModel videoModel2 = eVar5.video;
                        if (videoModel2 != null) {
                            videoModel2.setOwner(view.getOwner());
                        }
                        VideoModel videoModel3 = eVar5.video;
                        if (videoModel3 != null) {
                            videoModel3.setStat(view.getStat());
                        }
                        VideoModel videoModel4 = eVar5.video;
                        if (videoModel4 != null) {
                            videoModel4.setPubdate(view.getPubdate());
                        }
                        VideoModel videoModel5 = eVar5.video;
                        if (videoModel5 != null) {
                            videoModel5.setDesc(view.getDesc());
                        }
                        int i7 = eVar5.f12278V;
                        WeakReference weakReference3 = eVar5.fragmentReference;
                        if (i7 == 0) {
                            PlaybackFragment uVar12 = (PlaybackFragment) weakReference3.get();
                            if (uVar12 != null) {
                                uVar12.p0();
                            }
                        } else {
                            PlaybackFragment uVar13 = (PlaybackFragment) weakReference3.get();
                            if (uVar13 != null) {
                                uVar13.o0();
                            }
                        }
                        PlaybackFragment uVar14 = (PlaybackFragment) weakReference3.get();
                        if (uVar14 != null) {
                            z7 = false;
                            VideoModel videoModel6 = eVar5.video;
                            if ((videoModel6 != null ? videoModel6.getOwner() : null) != null) {
                                VideoModel videoModel7 = eVar5.video;
                                if (videoModel7 != null
                                        && (owner = videoModel7.getOwner()) != null) {
                                    mid = owner.getMid();
                                }
                                z7 = TextUtils.isEmpty(mid) ? false : true;
                            }
                            if (uVar14.q() && (myPlayerView3 = uVar14.playerView) != null) {
                                myPlayerView3.setShowHideOwnerInfo(z7);
                            }
                            jVar3 = Unit.INSTANCE;
                        }
                    }
                    if (jVar3 != null) {
                        return;
                    }
                }
                PlaybackFragment uVar15 = (PlaybackFragment) eVar5.fragmentReference.get();
                if (uVar15 != null) {
                    uVar15.m0(false);
                }
                break;
            default:
                BaseResponse baseResponse4 = (BaseResponse) obj;
                if (baseResponse4 != null
                        && (watchingTotalNumberModel =
                                        (WatchingTotalNumberModel) baseResponse4.getData())
                                != null
                        && (total = watchingTotalNumberModel.getTotal()) != null) {
                    PlaybackCoordinator eVar6 = this.f12259b;
                    eVar6.getClass();
                    eVar6.f12303q0 = total;
                    PlaybackFragment uVar16 = (PlaybackFragment) eVar6.fragmentReference.get();
                    if (uVar16 != null) {
                        uVar16.q0();
                    }
                    break;
                }
                break;
        }
    }
}
