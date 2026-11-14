package com.xx.blbl.ui.view.youtubeTapView.youtube;

import android.view.View;

import androidx.media3.common.Player;

public interface DoubleTapListener {
    void onAnimationEnd();

    void onAnimationStart();

    Boolean shouldForward(Player player, View playerView, float x);

    default void keepInDoubleTapMode() {}
}
