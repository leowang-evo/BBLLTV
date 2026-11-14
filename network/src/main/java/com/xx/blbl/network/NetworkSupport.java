package com.xx.blbl.network;

import okhttp3.FormBody;

import java.util.Arrays;
import java.util.List;

/** Small application operations separated from shared R8 helper classes. */
final class NetworkSupport {
    private NetworkSupport() {}

    static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    static String concat(String prefix, Object value) {
        return prefix + value;
    }

    static String formEncode(
            String value,
            String ignoredEncodeSet,
            int ignoredStart,
            int ignoredEnd,
            int defaultMask) {
        // Call sites in the recovered source use R8's default arguments (mask 91).
        if (defaultMask != 91 || ignoredStart != 0 || ignoredEnd != 0)
            throw new IllegalArgumentException("Unsupported migrated canonicalization call");
        return new FormBody.Builder().add("value", value).build().encodedValue(0);
    }

    static List<String> split(String value, String[] delimiters) {
        if (delimiters.length != 1) throw new IllegalArgumentException("Expected one delimiter");
        return Arrays.asList(value.split(java.util.regex.Pattern.quote(delimiters[0]), -1));
    }

    static int indexOf(String value, String needle, int start, int mask) {
        return value.indexOf(needle, (mask & 2) != 0 ? 0 : start);
    }
}
