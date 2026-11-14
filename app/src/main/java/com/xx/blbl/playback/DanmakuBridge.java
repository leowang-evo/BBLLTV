package com.xx.blbl.playback;

import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.xx.blbl.network.DanmakuItem;

import java.util.*;

/** Translate independent network records to the published renderer's public model. */
public final class DanmakuBridge {
    private DanmakuBridge() {}

    public static List<DanmakuItemData> convert(List<DanmakuItem> source) {
        List<DanmakuItemData> result = new ArrayList<>();
        for (DanmakuItem item : source)
            result.add(
                    new DanmakuItemData(
                            item.getId(),
                            item.getPositionMillis(),
                            item.getContent(),
                            item.getMode(),
                            item.getTextSize(),
                            item.getColor(),
                            0,
                            DanmakuItemData.DANMAKU_STYLE_NONE,
                            item.getRank(),
                            null,
                            0));
        return result;
    }
}
