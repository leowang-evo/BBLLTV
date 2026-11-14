package com.xx.blbl.ui.screen;

import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

/** Application string events, replacing the obfuscated event-bus entry points. */
public final class ScreenEvents {
    public interface Listener {
        void onMessageEvent(String event);
    }

    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static final CopyOnWriteArrayList<WeakReference<Listener>> LISTENERS =
            new CopyOnWriteArrayList<>();

    private ScreenEvents() {}

    public static void register(Listener listener) {
        for (WeakReference<Listener> reference : LISTENERS) {
            Listener old = reference.get();
            if (old == listener) return;
            if (old == null) LISTENERS.remove(reference);
        }
        LISTENERS.add(new WeakReference<>(listener));
    }

    public static void unregister(Listener listener) {
        for (WeakReference<Listener> reference : LISTENERS) {
            Listener old = reference.get();
            if (old == null || old == listener) LISTENERS.remove(reference);
        }
    }

    public static void post(String event) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MAIN.post(() -> post(event));
            return;
        }
        for (WeakReference<Listener> reference : LISTENERS) {
            Listener listener = reference.get();
            if (listener == null) LISTENERS.remove(reference);
            else listener.onMessageEvent(event);
        }
    }
}
