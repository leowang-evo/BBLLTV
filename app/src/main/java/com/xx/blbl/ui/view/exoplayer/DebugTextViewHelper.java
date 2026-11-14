package com.xx.blbl.ui.view.exoplayer;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.media3.exoplayer.ExoPlayer;

/** App URL annotations composed with the published Media3 decoder/state diagnostics. */
public class DebugTextViewHelper {
    private String videoUrl, audioUrl;
    private final Upstream helper;

    private final class Upstream extends androidx.media3.exoplayer.util.DebugTextViewHelper {
        Upstream(ExoPlayer player, TextView text) {
            super(player, text);
        }

        @Override
        protected String getDebugString() {
            return DebugTextViewHelper.this.getDebugString();
        }

        String state() {
            return super.getPlayerStateString();
        }

        String video() {
            return super.getVideoString();
        }

        String audio() {
            return super.getAudioString();
        }

        void refresh() {
            updateAndPost();
        }
    }

    public DebugTextViewHelper(ExoPlayer player, TextView text) {
        helper = new Upstream(player, text);
    }

    public String getDebugString() {
        return getUrlString() + getPlayerStateString() + getVideoString() + getAudioString();
    }

    public String getPlayerStateString() {
        return helper.state();
    }

    public String getVideoString() {
        return helper.video();
    }

    public String getAudioString() {
        return helper.audio();
    }

    public String getUrlString() {
        String text = TextUtils.isEmpty(videoUrl) ? "" : "VideoHost: " + videoUrl;
        if (!TextUtils.isEmpty(audioUrl)) text += "\nAudioHost: " + audioUrl;
        return text + "\n";
    }

    public void setAudioUrl(String url) {
        audioUrl = url;
    }

    public void setVideoUrl(String url) {
        videoUrl = url;
    }

    public final void start() {
        helper.start();
    }

    public final void stop() {
        helper.stop();
    }

    public final void updateAndPost() {
        helper.refresh();
    }
}
