package com.xx.blbl.ui.view.exoplayer;

import android.view.View;

import com.xx.blbl.R;

import java.util.IdentityHashMap;
import java.util.Map;

/** App information-only seeking mode composed with Media3's published controller animations. */
public final class MyPlayerControlViewLayoutManager {
    private final MyPlayerControlView controls;
    private int savedTimeout;
    private boolean timeoutSuspended;
    private final Map<View, Integer> seekVisibility = new IdentityHashMap<>();

    public MyPlayerControlViewLayoutManager(MyPlayerControlView controls) {
        this.controls = controls;
    }

    public void hide() {
        controls.hide();
    }

    public void hideImmediately() {
        controls.hideImmediately();
    }

    public void show() {
        controls.show();
    }

    public boolean isFullyVisible() {
        return controls.isFullyVisible();
    }

    public boolean isAnimationEnabled() {
        return controls.isAnimationEnabled();
    }

    public void setAnimationEnabled(boolean enabled) {
        controls.setAnimationEnabled(enabled);
    }

    public boolean getShowButton(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    public void setShowButton(View view, boolean visible) {
        if (view != null) view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void removeHideCallbacks() {
        if (!timeoutSuspended) {
            savedTimeout = controls.getShowTimeoutMs();
            timeoutSuspended = true;
        }
        controls.setShowTimeoutMs(0);
    }

    public void resetHideCallbacks() {
        if (timeoutSuspended) {
            timeoutSuspended = false;
            controls.setShowTimeoutMs(savedTimeout);
        }
        if (controls.isVisible()) controls.show();
    }

    public void hideInfoOnlyLeftTimeBar() {
        removeHideCallbacks();
        for (int id : new int[] {R.id.view_title, R.id.view_bottom_controller}) {
            View view = controls.findViewById(id);
            if (view != null) {
                if (!seekVisibility.containsKey(view))
                    seekVisibility.put(view, view.getVisibility());
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void showInfoAfterEndFF() {
        for (Map.Entry<View, Integer> entry : seekVisibility.entrySet())
            entry.getKey().setVisibility(entry.getValue());
        seekVisibility.clear();
        resetHideCallbacks();
    }
}
