package com.xx.blbl.platform;

import android.content.Context;
import android.content.SharedPreferences;
import com.xx.blbl.AppController;
import com.xx.blbl.model.video.VideoModel;
import org.json.JSONObject;

/** A single resumable item per account; independent of network history availability. */
public final class ContinueWatching {
    private ContinueWatching() {}
    private static SharedPreferences prefs() {
        return AppController.instance().getSharedPreferences("continue_watching", Context.MODE_PRIVATE);
    }
    private static String key() { return "video_" + AppController.state().userId; }
    private static boolean valid(VideoModel video) {
        return video != null && video.getAid() > 0 && video.getCid() > 0
                && (video.getBvid().matches("BV[0-9A-Za-z]{10}")
                    || (video.getSid() > 0 && video.getEpid() > 0));
    }
    public static void save(VideoModel video, long positionMs, long durationMs) {
        if (!valid(video) || positionMs < 3000) return;
        if (durationMs > 0 && positionMs >= durationMs - 5000) {
            VideoModel previous = load();
            if (previous != null && previous.getAid() == video.getAid()
                    && previous.getCid() == video.getCid()) prefs().edit().remove(key()).apply();
            return;
        }
        try {
            JSONObject value = new JSONObject();
            value.put("aid", video.getAid()).put("cid", video.getCid())
                    .put("sid", video.getSid()).put("epid", video.getEpid())
                    .put("bvid", video.getBvid()).put("title", video.getTitle())
                    .put("pic", video.getPic()).put("duration", video.getDuration())
                    .put("progress", positionMs);
            prefs().edit().putString(key(), value.toString()).apply();
        } catch (Exception ignored) { /* Playback must remain independent of local history. */ }
    }
    public static VideoModel load() {
        try {
            JSONObject value = new JSONObject(prefs().getString(key(), ""));
            VideoModel video = new VideoModel();
            video.setAid(value.getLong("aid")); video.setCid(value.getLong("cid"));
            video.setSid(value.optLong("sid")); video.setEpid(value.optLong("epid"));
            video.setBvid(value.getString("bvid")); video.setTitle(value.getString("title"));
            video.setPic(value.optString("pic")); video.setDuration(value.optLong("duration"));
            video.setProgress(value.getLong("progress"));
            return valid(video) && video.getProgress() >= 3000 ? video : null;
        } catch (Exception ignored) { return null; }
    }
}
