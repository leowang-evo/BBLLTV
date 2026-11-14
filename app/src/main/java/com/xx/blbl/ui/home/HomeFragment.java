package com.xx.blbl.ui.home;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.CategoryModel;
import com.xx.blbl.ui.navigation.TabPagerAdapter;
import com.xx.blbl.ui.screen.TabScreenFragment;

import java.util.ArrayList;

public final class HomeFragment extends TabScreenFragment
        implements com.xx.blbl.ui.MainActivity.BackHandler {
    public TabLayout f9661G0;
    public ViewPager f9662H0;
    public TabPagerAdapter f9663I0;
    public final int J0 = -1;

    @Override
    public final int layoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public final void bindScreen(View view) {
        bindResume(view);
        this.f9661G0 = (TabLayout) view.findViewById(R.id.tabLayout);
        this.f9662H0 = (ViewPager) view.findViewById(R.id.viewPager);
        FragmentManager qI = childFragments();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                qI, "getChildFragmentManager(...)");
        TabPagerAdapter aVar = new TabPagerAdapter(qI, 1);
        aVar.pages = new ArrayList();
        this.f9663I0 = aVar;
        ViewPager viewPager = this.f9662H0;
        if (viewPager != null) {
            viewPager.setAdapter(aVar);
        }
        ViewPager viewPager2 = this.f9662H0;
        int i4 = 1;
        if (viewPager2 != null) {
            viewPager2.setOffscreenPageLimit(1);
        }
        TabLayout tabLayout = this.f9661G0;
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(this.f9662H0);
        }
        ArrayList arrayList = new ArrayList();
        String strN = stringResource(R.string.recommend);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN, "getString(...)");
        arrayList.add(new CategoryModel(0, strN));
        String strN2 = stringResource(R.string.hot);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN2, "getString(...)");
        arrayList.add(new CategoryModel(1, strN2));
        String strN3 = stringResource(R.string.animation);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN3, "getString(...)");
        arrayList.add(new CategoryModel(2, strN3));
        String strN4 = stringResource(R.string.film_and_television);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN4, "getString(...)");
        arrayList.add(new CategoryModel(3, strN4));
        TabPagerAdapter aVar2 = this.f9663I0;
        if (aVar2 != null) {
            ArrayList arrayList2 = aVar2.pages;
            arrayList2.clear();
            arrayList2.addAll(arrayList);
            aVar2.refreshPages();
        }
        try {
            if (AppController.f9455a
                            .application()
                            .getSharedPreferences("BLBL", 0)
                            .getInt("defaultStartPage", 1)
                    == 0) {
                i4 = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i7 = this.J0;
        if (i7 != -1) {
            i4 = i7;
        }
        ViewPager viewPager3 = this.f9662H0;
        if (viewPager3 != null) {
            viewPager3.setCurrentItem(i4);
        }
        TabLayout tabLayout2 = this.f9661G0;
        if (tabLayout2 != null) {
            tabLayout2.addOnTabSelectedListener(new HomeTabListener(0));
            // TabLayout's rounded state background owns focus; suppress the platform's
            // additional rectangular focus overlay on each internal tab view.
            for (int index = 0; index < tabLayout2.getTabCount(); index++) {
                View tab = tabLayout2.getTabAt(index).view;
                if (android.os.Build.VERSION.SDK_INT >= 26)
                    tab.setDefaultFocusHighlightEnabled(false);
            }
        }
    }

    private View resumeCard;
    private boolean resumeLaunched;
    private void bindResume(View view) {
        resumeCard = view.findViewById(R.id.tv_resume);
        resumeCard.setOnClickListener(target -> {
            com.xx.blbl.model.video.VideoModel video = com.xx.blbl.platform.ContinueWatching.load();
            if (video != null) {
                resumeLaunched = true;
                com.xx.blbl.ui.navigation.ScreenNavigator.openVideo(target.getContext(), video, false);
            }
        });
        resumeCard.setOnFocusChangeListener(com.xx.blbl.platform.TvMotion::focus);
        refreshResume();
    }

    private void refreshResume() {
        if (resumeCard == null) return;
        com.xx.blbl.model.video.VideoModel video = com.xx.blbl.platform.ContinueWatching.load();
        resumeCard.setVisibility(video == null ? View.GONE : View.VISIBLE);
        if (video != null) {
            ((android.widget.TextView) resumeCard.findViewById(R.id.tv_resume_title)).setText(video.getTitle());
            String position = com.xx.blbl.platform.DisplayFormat.h(video.getProgress() / 1000);
            ((android.widget.TextView) resumeCard.findViewById(R.id.tv_resume_label)).setText("继续观看 · " + position);
            resumeCard.setContentDescription("继续观看：" + video.getTitle() + "，已播放 " + position);
        }
    }

    private final java.util.Map<androidx.fragment.app.Fragment, android.os.Parcelable> savedGridScroll =
            new java.util.IdentityHashMap<>();

    @Override public boolean handleBack() {
        if (f9661G0 == null) return false;
        TabLayout.Tab tab = f9661G0.getTabAt(f9661G0.getSelectedTabPosition());
        if (tab == null || tab.view.hasFocus()) return false;
        // Leave the content area before exit confirmation. Moving focus first also
        // invalidates asynchronous card-focus restoration while the grid returns up.
        tab.view.requestFocusFromTouch();
        savedGridScroll.clear();
        for (androidx.fragment.app.Fragment child : childFragments().getFragments())
            if (child.isResumed() && child.getView() != null && child.getView().isShown()
                    && child instanceof com.xx.blbl.ui.screen.PagedListFragment)
                ((com.xx.blbl.ui.screen.PagedListFragment) child).returnToTopNavigation();
        return true;
    }

    @Override public void screenHiddenChanged(boolean hidden) {
        if (hidden) {
            savedGridScroll.clear();
            for (androidx.fragment.app.Fragment child : childFragments().getFragments()) {
                if (child.isResumed() && child instanceof com.xx.blbl.ui.screen.PagedListFragment) {
                    com.xx.blbl.ui.screen.PagedListFragment list =
                            (com.xx.blbl.ui.screen.PagedListFragment) child;
                    if (list.recyclerView != null && list.listLayout != null) {
                        list.recyclerView.stopScroll();
                        savedGridScroll.put(child, list.listAdapter == null ? list.listLayout.onSaveInstanceState()
                                : list.listAdapter.captureViewportState(list.listLayout));
                    }
                }
            }
            return;
        }
        refreshResume();
        if (f9662H0 != null) f9662H0.post(() -> {
            if (isHidden() || f9662H0 == null) return;
            boolean returnToResume = resumeLaunched && resumeCard != null
                    && resumeCard.getVisibility() == View.VISIBLE;
            for (androidx.fragment.app.Fragment child : childFragments().getFragments()) {
                if (child.isResumed() && child instanceof com.xx.blbl.ui.screen.PagedListFragment) {
                    com.xx.blbl.ui.screen.PagedListFragment list =
                            (com.xx.blbl.ui.screen.PagedListFragment) child;
                    if (!returnToResume && list.listAdapter != null) list.listAdapter.restoreFocus();
                    android.os.Parcelable scroll = savedGridScroll.remove(child);
                    if (scroll != null && list.recyclerView != null && list.listLayout != null) {
                        // Hiding the side rail resizes the hidden grid. Restore its anchor after
                        // the rail and focused card have been restored, using the original offset.
                        list.recyclerView.stopScroll();
                        if (list.listAdapter != null) list.listAdapter.restoreViewportState(scroll, list.listLayout);
                        else list.listLayout.onRestoreInstanceState(scroll);
                    }
                }
            }
            if (returnToResume) resumeCard.requestFocus();
            resumeLaunched = false;
        });
    }
    @Override public void screenResumed() { refreshResume(); }

    @Override
    public final void releaseScreenView() {
        TabPagerAdapter aVar = this.f9663I0;
        if (aVar != null) {
            aVar.pages.clear();
            aVar.refreshPages();
        }
        ViewPager viewPager = this.f9662H0;
        if (viewPager != null) {
            viewPager.setAdapter(null);
        }
        resumeCard = null;
        resumeLaunched = false;
        savedGridScroll.clear();
        super.releaseScreenView();
        this.f9661G0 = null;
        this.f9663I0 = null;
        this.f9662H0 = null;
    }
}
