package com.xx.blbl.ui.home;

import com.google.android.material.tabs.TabLayout;
import com.xx.blbl.ui.screen.ScreenEvents;

/** Original tab-reselection application events on the published Material tab listener. */
public final class HomeTabListener implements TabLayout.OnTabSelectedListener {
    public final int f9633a;

    public HomeTabListener(int mode) {
        f9633a = mode;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {}

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        ScreenEvents.post(
                (f9633a == 1 ? "clickLiveTopTab" : "clickTopTab")
                        + (tab == null ? null : tab.getPosition()));
    }
}
