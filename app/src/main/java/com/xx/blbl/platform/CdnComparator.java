package com.xx.blbl.platform;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

import java.util.Comparator;

/** Application CDN ordering: ordinary hosts precede the original P2P hosts. */
public final class CdnComparator extends Lambda<Integer>
        implements Function2<String, String, Integer>, Comparator<String> {
    public static final CdnComparator f741a = new CdnComparator(2);

    public CdnComparator(int arity) {
        super(arity);
    }

    private static boolean peer(String url) {
        return url.contains("szbdyd.com") || url.contains("mcdn.bilivideo.");
    }

    @Override
    public int compare(String first, String second) {
        Intrinsics.checkNotNull(first);
        Intrinsics.checkNotNull(second);
        boolean a = peer(first), b = peer(second);
        return a == b ? 0 : a ? 1 : -1;
    }

    @Override
    public Integer invoke(String first, String second) {
        return compare(first, second);
    }
}
