package com.xx.blbl.platform;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.xx.blbl.AppController;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Recovered cache-limit behavior using each upstream component's public clear API. */
public final class CacheMaintenance {
    private static final ExecutorService IO =
            Executors.newSingleThreadExecutor(
                    runnable -> {
                        Thread thread = new Thread(runnable, "bbll-cache-maintenance");
                        thread.setDaemon(true);
                        return thread;
                    });

    private CacheMaintenance() {}

    public static long size(Context context) {
        File web = new File(context.getApplicationInfo().dataDir, "app_webview");
        return size(context.getCacheDir()) + size(web);
    }

    private static long size(File file) {
        if (file.isFile()) return file.length();
        File[] children = file.listFiles();
        long total = 0;
        if (children != null) for (File child : children) total += size(child);
        return total;
    }

    public static void enforceLimit(Context context) {
        Context application = context.getApplicationContext();
        IO.execute(
                () -> {
                    int limit = application.getSharedPreferences("BLBL", 0).getInt("cacheLimit", 0);
                    if (limit > 0 && size(application) > limit * 1048576L)
                        clearNow(application, null);
                });
    }

    public static void clear(Context context, Runnable completed) {
        Context application = context.getApplicationContext();
        IO.execute(() -> clearNow(application, completed));
    }

    private static void clearNow(Context application, Runnable completed) {
        AppController.state().cache.clear();
        Glide.get(application).clearDiskCache();
        new Handler(Looper.getMainLooper())
                .post(
                        () -> {
                            // Clear cached web resources through WebView; keep cookies/account
                            // data.
                            WebView view = null;
                            try {
                                view = new WebView(application);
                                view.clearCache(true);
                            } catch (RuntimeException failure) {
                                android.util.Log.w(
                                        "BBLL", "Web cache provider unavailable", failure);
                            } finally {
                                if (view != null) view.destroy();
                                if (completed != null) completed.run();
                            }
                        });
    }
}
