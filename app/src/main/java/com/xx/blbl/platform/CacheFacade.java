package com.xx.blbl.platform;

import org.afinal.simplecache.ACache;

import java.io.File;
import java.io.Serializable;

/** Application cache boundary; all storage/expiration remains in upstream ACache. */
public final class CacheFacade {
    private final File directory;
    private volatile ACache cache;

    public CacheFacade(File directory) {
        this.directory = directory;
    }

    private ACache cache() {
        ACache value = cache;
        if (value == null) synchronized (this) {
            value = cache;
            if (value == null) cache = value = ACache.get(directory);
        }
        return value;
    }

    public Object a(String key) {
        return cache().getAsObject(key);
    }

    public void b(String key, Serializable value) {
        cache().put(key, value);
    }

    public void clear() {
        cache().clear();
    }

    public File directory() {
        return directory;
    }

    public long size() {
        return size(directory);
    }

    private static long size(File file) {
        if (file.isFile()) return file.length();
        File[] files = file.listFiles();
        long total = 0;
        if (files != null) for (File child : files) total += size(child);
        return total;
    }
}
