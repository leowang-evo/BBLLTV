package com.xx.blbl;

import android.app.Application;

import com.xx.blbl.network.NetworkEnvironment;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.AndroidNetworkEnvironment;
import com.xx.blbl.platform.ApplicationAccessor;
import com.xx.blbl.state.AppState;

/** Application composition using ordinary Android and published networking APIs. */
public final class AppController extends Application {
    public static final ApplicationAccessor f9455a = new ApplicationAccessor();
    private static volatile AppController instance;
    private AndroidNetworkEnvironment networkEnvironment;
    private NetworkManager network;
    private AppState state;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        networkEnvironment = new AndroidNetworkEnvironment(this);
        NetworkEnvironment.installDefault(networkEnvironment);
        state = new AppState();
        network = new NetworkManager(networkEnvironment);
    }

    public static AppController instance() {
        AppController application = instance;
        if (application == null) throw new IllegalStateException("Application has not started");
        return application;
    }

    public static NetworkManager network() {
        return instance().network;
    }

    public static AppState state() {
        return instance().state;
    }

    public static void clearCookies() {
        instance().networkEnvironment.clearCookies();
    }
}
