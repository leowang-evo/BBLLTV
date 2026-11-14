package com.xx.blbl.recovery;

import java.util.Comparator;
import java.util.List;

/** Application operations separated from R8's shared SDK helper containers. */
public final class DomainSupport {
    private DomainSupport() {}
    public static String appendInt(StringBuilder b, int value, char suffix) {
        return b.append(value).append(suffix).toString();
    }
    public static String appendString(StringBuilder b, String value, String suffix) {
        return b.append(value).append(suffix).toString();
    }
    public static String appendList(StringBuilder b, List<?> value, char suffix) {
        return b.append(value).append(suffix).toString();
    }
    private static boolean deprioritized(String url) {
        kotlin.jvm.internal.Intrinsics.checkNotNull(url);
        return url.contains("szbdyd.com") || url.contains("mcdn.bilivideo.");
    }
    public static final Comparator<String> CDN_ORDER = (left, right) ->
        Boolean.compare(deprioritized(left), deprioritized(right));
}
