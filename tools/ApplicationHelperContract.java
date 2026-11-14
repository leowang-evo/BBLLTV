import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.xx.blbl.network.DanmakuItem;

import java.util.*;

/**
 * Executes application policy against real published dependencies, without Android initialization.
 */
public final class ApplicationHelperContract {
    private static int assertions;

    private static void check(boolean value, String label) {
        if (!value) throw new AssertionError(label);
        assertions++;
    }

    public static void main(String[] args) {
        check(
                com.xx.blbl.platform.VideoIdConverter.toBvid(170001L).equals("BV17x411w7KC"),
                "known AV170001");
        check(
                com.xx.blbl.platform.VideoIdConverter.toAid("BV17x411w7KC") == 170001L,
                "known BV inverse");
        Random random = new Random(0xBB11);
        for (int n = 0; n < 256; n++) {
            long av = random.nextLong() & ((1L << 51) - 1);
            check(
                    com.xx.blbl.platform.VideoIdConverter.toAid(
                                    com.xx.blbl.platform.VideoIdConverter.toBvid(av))
                            == av,
                    "BVID roundtrip " + av);
        }
        String plain = "https://cn-sha-normal.example/stream";
        String p2p = "https://a.mcdn.bilivideo.cn/stream";
        String peer = "https://a.szbdyd.com/stream";
        check(
                com.xx.blbl.platform.CdnComparator.f741a.compare(plain, p2p) < 0,
                "normal before mcdn");
        check(
                com.xx.blbl.platform.CdnComparator.f741a.compare(peer, plain) > 0,
                "szbdyd after normal");
        check(
                com.xx.blbl.platform.CdnComparator.f741a.compare(peer, p2p) == 0,
                "peer order stable");
        List<String> urls = new ArrayList<>(Arrays.asList(p2p, plain, peer));
        urls.sort(com.xx.blbl.platform.CdnComparator.f741a);
        check(urls.equals(Arrays.asList(plain, p2p, peer)), "stable CDN sort");
        DanmakuItem item = new DanmakuItem(31, 2500, "弹幕", 5, 36, 0x336699);
        DanmakuItemData data =
                com.xx.blbl.playback.DanmakuBridge.convert(Collections.singletonList(item)).get(0);
        check(data.getDanmakuId() == 31 && data.getPosition() == 2500, "DM identity/time");
        check(
                data.getContent().equals("弹幕")
                        && data.getMode() == 5
                        && data.getTextSize() == 36
                        && data.getTextColor() == 0x336699,
                "DM content/style fields");
        check(
                data.getScore() == 0
                        && data.getDanmakuStyle() == DanmakuItemData.DANMAKU_STYLE_NONE
                        && data.getRank() == 0,
                "original XML DM renderer defaults");
        System.out.println("Application helper assertions passed: " + assertions);
    }
}
