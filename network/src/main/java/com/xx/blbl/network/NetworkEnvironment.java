package com.xx.blbl.network;

import okhttp3.Cookie;
import okhttp3.CookieJar;

import java.util.List;
import java.util.concurrent.Executor;

/** Platform integration supplied by the Android application or a JVM test. */
public interface NetworkEnvironment {
    interface CookieStore {
        List<Cookie> loadAll();
    }

    CookieStore cookieStore();

    CookieJar cookieJar();

    String userAgent();

    String refreshUserAgent();

    void reloadCookies();

    default String baseUrl() {
        return "https://api.bilibili.com/";
    }

    /** Null selects Retrofit's platform default (Android main thread on TV). */
    default Executor callbackExecutor() {
        return null;
    }

    static void installDefault(NetworkEnvironment environment) {
        Holder.environment = java.util.Objects.requireNonNull(environment, "environment");
    }

    static NetworkEnvironment defaultEnvironment() {
        NetworkEnvironment environment = Holder.environment;
        if (environment == null)
            throw new IllegalStateException(
                    "Application must initialize NetworkEnvironment before creating its network"
                            + " service");
        return environment;
    }

    final class Holder {
        private Holder() {}

        private static volatile NetworkEnvironment environment;
    }
}
