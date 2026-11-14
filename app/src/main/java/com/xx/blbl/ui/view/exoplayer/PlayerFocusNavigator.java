package com.xx.blbl.ui.view.exoplayer;

import android.view.KeyEvent;
import android.view.View;

import com.xx.blbl.R;

import java.util.ArrayList;
import java.util.List;

/** The remote's explicit path between the time bar and visible player actions. */
final class PlayerFocusNavigator {
    private static final int[] ACTIONS = {
        R.id.button_play, R.id.button_previous, R.id.button_next,
        R.id.button_rewind, R.id.button_fast_forward, R.id.button_dm_switch,
        R.id.exo_settings, R.id.button_choose_episode, R.id.button_more,
        R.id.button_up_info, R.id.button_subtitle, R.id.button_related,
        R.id.button_repeat, R.id.button_live_settings, R.id.button_restart, R.id.button_close
    };
    private final MyPlayerControlView controls;
    private View lastAction;

    PlayerFocusNavigator(MyPlayerControlView controls) {
        this.controls = controls;
    }

    private static boolean available(View view) {
        return view != null && view.isShown() && view.isEnabled() && view.isFocusable();
    }

    private List<View> actions() {
        List<View> result = new ArrayList<>();
        for (int id : ACTIONS) {
            View view = controls.findViewById(id);
            if (available(view)) result.add(view);
        }
        return result;
    }

    void enter() {
        if (available(lastAction)) {
            lastAction.requestFocus();
            return;
        }
        List<View> buttons = actions();
        if (!buttons.isEmpty()) {
            lastAction = buttons.get(0);
            lastAction.requestFocus();
        }
    }

    boolean navigate(KeyEvent event, boolean relatedShortcutEnabled) {
        int key = event.getKeyCode();
        if (key != KeyEvent.KEYCODE_DPAD_UP
                && key != KeyEvent.KEYCODE_DPAD_DOWN
                && key != KeyEvent.KEYCODE_DPAD_LEFT
                && key != KeyEvent.KEYCODE_DPAD_RIGHT) {
            return false;
        }
        View focused = controls.findFocus();
        View timeBar = controls.findViewById(R.id.exo_progress);
        List<View> buttons = actions();
        int index = buttons.indexOf(focused);
        if (focused == timeBar
                && (key == KeyEvent.KEYCODE_DPAD_LEFT || key == KeyEvent.KEYCODE_DPAD_RIGHT)) {
            return false;
        }
        if (event.getAction() != KeyEvent.ACTION_DOWN) return true;
        if (focused == timeBar) {
            if (key == KeyEvent.KEYCODE_DPAD_DOWN) enter();
            return true;
        }
        if (index < 0) {
            enter();
            return true;
        }
        lastAction = focused;
        if (key == KeyEvent.KEYCODE_DPAD_UP) {
            if (available(timeBar)) timeBar.requestFocus();
        } else if (key == KeyEvent.KEYCODE_DPAD_LEFT || key == KeyEvent.KEYCODE_DPAD_RIGHT) {
            int next = index + (key == KeyEvent.KEYCODE_DPAD_RIGHT ? 1 : -1);
            if (next >= 0 && next < buttons.size()) {
                lastAction = buttons.get(next);
                lastAction.requestFocus();
            }
        } else if (relatedShortcutEnabled) {
            View related = controls.findViewById(R.id.button_related);
            if (available(related)) related.performClick();
        }
        return true;
    }
}
