package com.xx.blbl.ui.auth;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.screen.BaseScreenFragment;

import kotlin.Lazy;
import kotlin.LazyKt;

/** Alternative web login, keeping the original login endpoint and desktop user agent. */
public final class BrowserLoginFragment extends BaseScreenFragment
        implements MainActivity.BackHandler {
    public FrameLayout f9716H0;
    public WebView f9717I0;
    public final Lazy<NetworkManager> f9715G0 = LazyKt.lazy(AppController::network);
    public final LoginWebViewClient J0 = new LoginWebViewClient(this);

    @Override
    public int layoutResource() {
        return R.layout.fragment_web;
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public void bindScreen(View view) {
        setScreenTitle(getString(R.string.sign_in_other_way));
        f9716H0 = view.findViewById(R.id.web_container);
        f9717I0 = new WebView(view.getContext());
        if (f9716H0 != null) f9716H0.addView(f9717I0, new FrameLayout.LayoutParams(-1, -1));
        f9717I0.clearCache(true);
        WebSettings settings = f9717I0.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setUserAgentString(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)"
                        + " Chrome/91.0.4472.124 Safari/537.36");
        f9717I0.setWebViewClient(J0);
        f9717I0.loadUrl("https://passport.bilibili.com/login");
    }

    @Override
    public boolean handleBack() {
        if (f9717I0 != null && f9717I0.canGoBack()) {
            f9717I0.goBack();
            return true;
        }
        return false;
    }

    @Override
    public void releaseScreenView() {
        if (f9717I0 != null) {
            f9717I0.stopLoading();
            if (f9716H0 != null) f9716H0.removeView(f9717I0);
            f9717I0.destroy();
        }
        f9717I0 = null;
        f9716H0 = null;
        super.releaseScreenView();
    }
}
