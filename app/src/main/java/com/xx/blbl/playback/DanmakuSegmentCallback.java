package com.xx.blbl.playback;

import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.xx.blbl.model.proto.Dm;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.ui.playback.PlaybackFragment;
import com.xx.blbl.ui.view.exoplayer.MyPlayerView;

import kotlin.jvm.internal.Intrinsics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class DanmakuSegmentCallback implements NetResultCallback {
    public final /* synthetic */ PlaybackCoordinator f12262a;
    public final /* synthetic */ VideoModel f12263b;

    private final long requestCid;
    private final int requestSegment;

    public DanmakuSegmentCallback(PlaybackCoordinator eVar, VideoModel videoModel) {
        this.f12262a = eVar;
        this.f12263b = videoModel;
        this.requestCid = videoModel.getCid();
        this.requestSegment = eVar.f12277U;
    }

    @Override
    public final void onFailure(Throwable th) {
        long cid = requestCid;
        PlaybackCoordinator eVar = this.f12262a;
        PlaybackFragment owner = (PlaybackFragment) eVar.fragmentReference.get();
        if (owner == null
                || !owner.isPlaybackActive()
                || eVar.video == null
                || eVar.video.getCid() != cid) return;
        eVar.network().getDm(cid, new PlaybackDetailsCallback(eVar, 0));
        eVar.f12315y0 = true;
    }

    @Override
    public final void onResponse(Object obj) {
        List<Dm.DanmakuElem> elemsList;
        PlaybackFragment uVar;
        MyPlayerView myPlayerView;
        Dm.DmSegMobileReply dmSegMobileReply = (Dm.DmSegMobileReply) obj;
        if (dmSegMobileReply == null || (elemsList = dmSegMobileReply.getElemsList()) == null) {
            return;
        }
        PlaybackCoordinator eVar = this.f12262a;
        if (eVar.video == null || eVar.video.getCid() != requestCid) return;
        WeakReference weakReference = eVar.fragmentReference;
        PlaybackFragment uVar2 = (PlaybackFragment) weakReference.get();
        if (uVar2 != null) {
            int i7 = eVar.f12294h0;
            boolean z7 = eVar.f12295i0;
            ArrayList arrayList = new ArrayList();
            for (Dm.DanmakuElem danmakuElem : elemsList) {
                if (1 > i7 || i7 >= 11 || danmakuElem.getWeight() >= i7) {
                    long id = danmakuElem.getId();
                    long progress = danmakuElem.getProgress();
                    String content = danmakuElem.getContent();
                    Intrinsics.checkNotNullExpressionValue(content, "getContent(...)");
                    int mode = danmakuElem.getMode();
                    int i8 = 4;
                    if (mode != 4) {
                        i8 = 5;
                        if (mode != 5) {
                            i8 = 1;
                        }
                    }
                    arrayList.add(
                            new DanmakuItemData(
                                    id,
                                    progress,
                                    content,
                                    i8,
                                    danmakuElem.getFontsize(),
                                    danmakuElem.getColor(),
                                    0,
                                    1,
                                    (danmakuElem.getColorful() == Dm.DmColorfulType.VipGradualColor
                                                    && z7)
                                            ? 1
                                            : 0,
                                    null,
                                    0));
                }
            }
            uVar2.e0(arrayList, requestSegment == 1);
        }
    }
}
