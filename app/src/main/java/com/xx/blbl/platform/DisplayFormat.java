package com.xx.blbl.platform;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** Recovered application count, publication-time and duration formatting. */
public final class DisplayFormat {
    private DisplayFormat() {}

    private static final ThreadLocal<DecimalFormat> COUNTS = new ThreadLocal<DecimalFormat>() {
        @Override protected DecimalFormat initialValue() {
            DecimalFormat format = new DecimalFormat("0.#");
            format.setRoundingMode(RoundingMode.FLOOR);
            return format;
        }
    };
    private static final ThreadLocal<Locale> COUNT_LOCALE = new ThreadLocal<>();

    public static String f(long count) {
        if (count < 0) return String.format("%d", count);
        Locale locale = Locale.getDefault();
        if (!locale.equals(COUNT_LOCALE.get())) {
            COUNTS.remove();
            COUNT_LOCALE.set(locale);
        }
        DecimalFormat format = COUNTS.get();
        if (count > 100000000L) return format.format(count / 1.0e8) + "亿";
        if (count > 10000L) return format.format(count / 10000.0) + "万";
        return format.format(count);
    }

    public static String g(long seconds) {
        return relative(seconds, System.currentTimeMillis());
    }

    public static String relative(long seconds, long nowMillis) {
        long elapsed = (nowMillis - seconds * 1000L) / 1000L;
        long days = elapsed / 86400L,
                hours = elapsed % 86400L / 3600L,
                minutes = elapsed % 3600L / 60L;
        if (days >= 1) {
            if (days <= 3) return days + "天前";
            Date published = new Date(seconds * 1000L);
            Date now = new Date(nowMillis);
            String pattern = published.getYear() == now.getYear() ? "MM-dd" : "yyyy-MM-dd";
            return new SimpleDateFormat(pattern, Locale.CHINA).format(published);
        }
        if (hours >= 1) return hours + "小时前";
        return minutes >= 1 ? minutes + "分前" : "刚刚";
    }

    public static String h(long seconds) {
        if (seconds <= 0) return "00:00";
        long hours = seconds / 3600L, minutes = seconds % 3600L / 60L, remainder = seconds % 60L;
        String suffix =
                (minutes < 10 ? "0" : "") + minutes + ":" + (remainder < 10 ? "0" : "") + remainder;
        return hours == 0 ? suffix : (hours < 10 ? "0" : "") + hours + ":" + suffix;
    }
}
