package com.xx.blbl.platform;

import android.app.ActivityManager;
import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.module.GlideModule;

/** Bounded image memory, initialized lazily by Glide's public module loader. */
@SuppressWarnings("deprecation")
public final class ImageCachePolicy implements GlideModule {
    @Override public void applyOptions(Context context, GlideBuilder builder) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        boolean low = manager != null && manager.isLowRamDevice();
        int memory = manager == null ? 128 : manager.getMemoryClass();
        long budget = Math.max(4, Math.min(low ? 8 : 12, memory / 12)) * 1048576L;
        builder.setMemoryCache(new LruResourceCache(budget));
        builder.setBitmapPool(new LruBitmapPool(low ? 2 * 1048576L : 4 * 1048576L));
    }
    @Override public void registerComponents(Context context, Glide glide, Registry registry) {}
}
