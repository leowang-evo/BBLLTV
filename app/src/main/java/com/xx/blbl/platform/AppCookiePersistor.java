package com.xx.blbl.platform;

import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xx.blbl.AppController;

import okhttp3.Cookie;

import java.util.List;

/** Application alias delegating persistence to the official external library. */
public final class AppCookiePersistor extends SharedPrefsCookiePersistor {
    public AppCookiePersistor(AppController context) {
        super(context);
    }

    public List<Cookie> a() {
        return loadAll();
    }
}
