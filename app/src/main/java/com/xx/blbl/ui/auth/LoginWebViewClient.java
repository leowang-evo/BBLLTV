package com.xx.blbl.ui.auth;

import android.net.Uri;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xx.blbl.AppController;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.screen.ScreenEvents;

import okhttp3.Cookie;

import java.util.ArrayList;
import java.util.List;

/** Transfer Bilibili WebView login cookies through the public upstream persistence API. */
public final class LoginWebViewClient extends WebViewClient {
    public final BrowserLoginFragment f9714a;
    private boolean completed;

    public LoginWebViewClient(BrowserLoginFragment owner) {
        f9714a = owner;
    }

    @Override
    public void onPageFinished(WebView web, String url) {
        if (!completed && url != null) {
            String host = Uri.parse(url).getHost();
            if (host != null && (host.equals("bilibili.com") || host.endsWith(".bilibili.com"))) {
                String header = CookieManager.getInstance().getCookie(url);
                if (header != null) transfer(header);
            }
        }
        super.onPageFinished(web, url);
    }

    private void transfer(String header) {
        List<Cookie> cookies = new ArrayList<>();
        boolean signedIn = false;
        for (String part : header.split(";")) {
            int separator = part.indexOf('=');
            if (separator <= 0) continue;
            String name = part.substring(0, separator).trim();
            String value = part.substring(separator + 1).trim();
            if (name.equals("bili_jct")) signedIn = true;
            cookies.add(
                    new Cookie.Builder()
                            .name(name)
                            .value(value)
                            .domain("bilibili.com")
                            .path("/")
                            .httpOnly()
                            .expiresAt(253402300799999L)
                            .build());
        }
        new SharedPrefsCookiePersistor(AppController.instance()).saveAll(cookies);
        if (signedIn) {
            completed = true;
            f9714a.f9715G0.getValue().loadCookie();
            ScreenEvents.post("signIn");
            MainActivity activity = f9714a.hostActivity();
            if (activity != null) activity.onBackPressed();
        }
    }
}
