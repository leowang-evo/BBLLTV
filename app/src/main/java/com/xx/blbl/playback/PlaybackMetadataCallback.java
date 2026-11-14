package com.xx.blbl.playback;

import android.content.Context;
import android.text.TextUtils;

import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.live.ChatHostUrlModel;
import com.xx.blbl.model.series.EpisodesDetailModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.chat.WebSocketEcho;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.ChatRoomWrapper;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.dialog.VideoActionPresenter;
import com.xx.blbl.ui.playback.PlaybackFragment;

import kotlin.jvm.internal.Intrinsics;

import java.util.List;

public final class PlaybackMetadataCallback implements NetResultCallback {
    public final /* synthetic */ int f12255a;
    public final /* synthetic */ PlaybackCoordinator f12256b;
    public final /* synthetic */ VideoModel f12257c;
    private final long liveGeneration, liveRoom;

    public /* synthetic */ PlaybackMetadataCallback(
            PlaybackCoordinator eVar, VideoModel videoModel, int i4) {
        this.f12255a = i4;
        this.f12256b = eVar;
        this.f12257c = videoModel;
        PlaybackFragment owner = (PlaybackFragment) eVar.fragmentReference.get();
        this.liveGeneration = owner == null ? -1 : owner.liveChatGeneration();
        this.liveRoom = videoModel == null ? 0 : videoModel.getAid();
    }

    private final void a(Throwable th) {}

    @Override
    public final void onFailure(Throwable th) {
        PlaybackFragment active = (PlaybackFragment) this.f12256b.fragmentReference.get();
        if (active == null || !active.q()) return;
        switch (this.f12255a) {
            case 0:
                break;
            default:
                PlaybackCoordinator eVar = this.f12256b;
                Context context = eVar.context;
                if (context instanceof MainActivity) {
                    Intrinsics.checkNotNull(
                            context,
                            "null cannot be cast to non-null type com.xx.blbl.ui.MainActivity");
                    if (!((MainActivity) context).isFinishing()) {
                        VideoActionPresenter.toast(eVar.context, String.valueOf(th));
                    }
                }
                eVar.loadCurrentMedia();
                break;
        }
    }

    @Override
    public final void onResponse(Object obj) {
        PlaybackFragment active = (PlaybackFragment) this.f12256b.fragmentReference.get();
        if (active == null || !active.q()) return;
        ChatRoomWrapper chatRoomWrapper;
        EpisodesDetailModel episodesDetailModel;
        EpisodesDetailModel episodesDetailModel2;
        List<EpisodeModel> episodes;
        EpisodesDetailModel episodesDetailModel3;
        String title;
        VideoModel videoModel;
        EpisodesDetailModel episodesDetailModel4;
        switch (this.f12255a) {
            case 0:
                PlaybackFragment owner = (PlaybackFragment) this.f12256b.fragmentReference.get();
                if (owner == null || !owner.acceptsLiveChat(liveGeneration, liveRoom)) return;
                BaseResponse baseResponse = (BaseResponse) obj;
                if (baseResponse != null
                        && (chatRoomWrapper = (ChatRoomWrapper) baseResponse.getData()) != null) {
                    String token = chatRoomWrapper.getToken();
                    List<ChatHostUrlModel> host_list = chatRoomWrapper.getHost_list();
                    String str = "";
                    if (host_list != null && !host_list.isEmpty()) {
                        ChatHostUrlModel chatHostUrlModel = host_list.get(0);
                        str =
                                "wss://"
                                        + chatHostUrlModel.getHost()
                                        + ':'
                                        + chatHostUrlModel.getWss_port()
                                        + "/sub";
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(token)) {
                        PlaybackCoordinator eVar = this.f12256b;
                        WebSocketEcho webSocketEcho = eVar.liveChat;
                        if (webSocketEcho != null) {
                            webSocketEcho.release();
                            eVar.liveChat = null;
                        }
                        WebSocketEcho webSocketEchoBuild =
                                WebSocketEcho.build(
                                        str,
                                        token,
                                        Long.valueOf(liveRoom),
                                        Long.valueOf(eVar.settings.userId));
                        eVar.liveChat = webSocketEchoBuild;
                        if (webSocketEchoBuild != null) {
                            webSocketEchoBuild.setOnDmListener(
                                    dm ->
                                            eVar.liveDanmakuListener.onDm(
                                                    dm, liveGeneration, liveRoom));
                        }
                        break;
                    }
                }
                break;
            default:
                Base2Response base2Response = (Base2Response) obj;
                PlaybackCoordinator eVar2 = this.f12256b;
                if (base2Response == null || base2Response.getCode() != 0) {
                    Context context = eVar2.context;
                    if (context instanceof MainActivity) {
                        Intrinsics.checkNotNull(
                                context,
                                "null cannot be cast to non-null type com.xx.blbl.ui.MainActivity");
                        if (!((MainActivity) context).isFinishing()) {
                            VideoActionPresenter.toast(
                                    eVar2.context,
                                    String.valueOf(
                                            base2Response != null
                                                    ? base2Response.getMessage()
                                                    : null));
                        }
                    }
                }
                if (base2Response != null
                        && (episodesDetailModel4 = (EpisodesDetailModel) base2Response.getResult())
                                != null) {
                    long season_id = episodesDetailModel4.getSeason_id();
                    VideoModel videoModel2 = eVar2.video;
                    if (videoModel2 != null) {
                        videoModel2.setSid(season_id);
                    }
                }
                if (base2Response != null
                        && (episodesDetailModel3 = (EpisodesDetailModel) base2Response.getResult())
                                != null
                        && (title = episodesDetailModel3.getTitle()) != null
                        && (videoModel = eVar2.video) != null) {
                    videoModel.setTitle(title);
                }
                if (base2Response != null
                        && (episodesDetailModel2 = (EpisodesDetailModel) base2Response.getResult())
                                != null
                        && (episodes = episodesDetailModel2.getEpisodes()) != null
                        && !episodes.isEmpty()) {
                    eVar2.e = episodes;
                    EpisodeModel episodeModel = episodes.get(episodes.size() - 1);
                    VideoModel videoModel3 = this.f12257c;
                    if (TextUtils.isEmpty(videoModel3.getBvid()) && videoModel3.getAid() == 0) {
                        int i4 = eVar2.f12290e0;
                        if (i4 > 0 && i4 < episodes.size()) {
                            episodeModel = episodes.get(eVar2.f12290e0);
                        }
                        VideoModel videoModel4 = eVar2.video;
                        if (videoModel4 != null) {
                            videoModel4.setBvid(episodeModel.getBvid());
                        }
                        VideoModel videoModel5 = eVar2.video;
                        if (videoModel5 != null) {
                            videoModel5.setAid(episodeModel.getAid());
                        }
                        VideoModel videoModel6 = eVar2.video;
                        if (videoModel6 != null) {
                            videoModel6.setCid(episodeModel.getCid());
                        }
                    }
                }
                if (base2Response != null
                        && (episodesDetailModel = (EpisodesDetailModel) base2Response.getResult())
                                != null) {
                    eVar2.f12279W = episodesDetailModel.getType();
                }
                PlaybackFragment uVar = (PlaybackFragment) eVar2.fragmentReference.get();
                if (uVar != null) {
                    uVar.o0();
                }
                eVar2.loadCurrentMedia();
                break;
        }
    }
}
