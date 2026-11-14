package com.xx.blbl.platform;

import android.content.Context;
import android.content.SharedPreferences;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xx.blbl.network.NetworkEnvironment;

import okhttp3.CookieJar;

/** Android persistence around the upstream cookie library, with original preference names. */
public final class AndroidNetworkEnvironment implements NetworkEnvironment {
    private final SharedPreferences preferences;
    private final SharedPrefsCookiePersistor persistor;
    private final SetCookieCache cache;
    private final PersistentCookieJar jar;

    public AndroidNetworkEnvironment(Context context) {
        Context application = context.getApplicationContext();
        preferences = application.getSharedPreferences("BLBL", Context.MODE_PRIVATE);
        persistor = new SharedPrefsCookiePersistor(application);
        cache = new SetCookieCache();
        jar = new PersistentCookieJar(cache, persistor);
        String ua = preferences.getString("currentUA", "");
        if (ua == null || ua.isEmpty()) refreshUserAgent();
    }

    @Override
    public CookieStore cookieStore() {
        return persistor::loadAll;
    }

    @Override
    public CookieJar cookieJar() {
        return jar;
    }

    @Override
    public String userAgent() {
        return preferences.getString("currentUA", UserAgentPool.BASE);
    }

    @Override
    public String refreshUserAgent() {
        String ua = UserAgentPool.next();
        preferences.edit().putString("currentUA", ua).apply();
        return ua;
    }

    @Override
    public void reloadCookies() {
        cache.addAll(persistor.loadAll());
    }

    public void clearCookies() {
        jar.clear();
    }
}
