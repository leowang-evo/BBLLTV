package com.xx.blbl.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/** Sign raw query values using the WBI canonical query format. */
public final class WbiGenerator {
    private static final int[] MIXIN_KEY_ENC_TAB = {
        46, 47, 18, 2, 53, 8, 23, 32, 15, 50, 10, 31, 58, 3, 45, 35,
        27, 43, 5, 49, 33, 9, 42, 19, 29, 28, 14, 39, 12, 38, 41, 13,
        37, 48, 7, 16, 24, 55, 40, 61, 26, 17, 0, 1, 60, 51, 30, 4,
        22, 25, 54, 21, 56, 59, 6, 63, 57, 62, 11, 36, 20, 34, 44, 52
    };

    interface ClockMillis {
        long now();
    }

    private final ClockMillis clockMillis;

    public WbiGenerator() {
        this(System::currentTimeMillis);
    }

    WbiGenerator(ClockMillis clockMillis) {
        this.clockMillis = Objects.requireNonNull(clockMillis, "clockMillis");
    }

    private String filterStr(String value) {
        return value.replace("!", "")
                .replace("'", "")
                .replace("(", "")
                .replace(")", "")
                .replace("*", "");
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("%7E", "~");
        } catch (UnsupportedEncodingException impossible) {
            throw new AssertionError(impossible);
        }
    }

    private String getMixinKey(String key) {
        StringBuilder mixed = new StringBuilder(MIXIN_KEY_ENC_TAB.length);
        // The original visits all 64 positions before taking 32 characters.
        for (int position : MIXIN_KEY_ENC_TAB) mixed.append(key.charAt(position));
        return mixed.substring(0, 32);
    }

    public Map<String, String> generatorWbi(
            HashMap<String, String> params, String imgKey, String subKey) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(params, "params");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(imgKey, "imgKey");
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(subKey, "subKey");
        if (imgKey.isEmpty() || subKey.isEmpty() || params.isEmpty()) return params;
        String mixinKey = getMixinKey(imgKey.concat(subKey));
        // Preserve the original integer-seconds division and saturating Int conversion.
        long seconds = clockMillis.now() / 1000L;
        int timestamp =
                seconds > Integer.MAX_VALUE
                        ? Integer.MAX_VALUE
                        : seconds < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) seconds;
        params.remove("w_rid");
        params.put("wts", String.valueOf(timestamp));
        TreeMap<String, String> sorted = new TreeMap<>(params);
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            String filtered = filterStr(entry.getValue());
            params.put(entry.getKey(), filtered);
            if (query.length() > 0) query.append('&');
            query.append(encode(entry.getKey())).append('=').append(encode(filtered));
        }
        String signature = MD5Util.md5(query.append(mixinKey).toString());
        params.put("wts", String.valueOf(timestamp));
        params.put("w_rid", signature);
        return params;
    }
}
