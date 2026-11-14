package com.xx.blbl.ui.user;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.CategoryModel;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.dialog.AccountDialog;
import com.xx.blbl.ui.home.HomeTabListener;
import com.xx.blbl.ui.navigation.TabPagerAdapter;
import com.xx.blbl.ui.screen.TabScreenFragment;

import kotlin.LazyThreadSafetyMode;

import java.util.ArrayList;

public final class MyLibraryFragment extends TabScreenFragment implements View.OnClickListener {
    public AppCompatImageView f9667G0;
    public final Object f9668H0 =
            kotlin.LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, AppController::state);
    public TabLayout f9669I0;
    public ViewPager J0;
    public TabPagerAdapter f9670K0;

    @Override
    public final void screenStarted() {
        super.screenStarted();
    }

    @Override
    public final void screenStopped() {
        super.screenStopped();
    }

    @Override
    public final int layoutResource() {
        return R.layout.fragment_me;
    }

    @Override
    public final void bindScreen(View view) {
        AppCompatImageView appCompatImageView =
                (AppCompatImageView) view.findViewById(R.id.image_avatar);
        this.f9667G0 = appCompatImageView;
        if (appCompatImageView != null) {
            appCompatImageView.setOnClickListener(this);
        }
        this.f9669I0 = (TabLayout) view.findViewById(R.id.tabLayout);
        this.J0 = (ViewPager) view.findViewById(R.id.viewPager);
        FragmentManager qI = childFragments();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                qI, "getChildFragmentManager(...)");
        TabPagerAdapter aVar = new TabPagerAdapter(qI, 3);
        aVar.pages = new ArrayList();
        this.f9670K0 = aVar;
        ViewPager viewPager = this.J0;
        if (viewPager != null) {
            viewPager.setAdapter(aVar);
        }
        ViewPager viewPager2 = this.J0;
        if (viewPager2 != null) {
            viewPager2.setOffscreenPageLimit(1);
        }
        TabLayout tabLayout = this.f9669I0;
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(this.J0);
        }
        j0();
        ArrayList arrayList = new ArrayList();
        String strN = stringResource(R.string.history);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN, "getString(...)");
        int i4 = 0;
        arrayList.add(new CategoryModel(0, strN));
        String strN2 = stringResource(R.string.collection);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN2, "getString(...)");
        arrayList.add(new CategoryModel(1, strN2));
        String strN3 = stringResource(R.string.following_animation);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN3, "getString(...)");
        arrayList.add(new CategoryModel(2, strN3));
        String strN4 = stringResource(R.string.following_series);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN4, "getString(...)");
        arrayList.add(new CategoryModel(3, strN4));
        String strN5 = stringResource(R.string.later_watch);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN5, "getString(...)");
        arrayList.add(new CategoryModel(4, strN5));
        TabPagerAdapter aVar2 = this.f9670K0;
        if (aVar2 != null) {
            ArrayList arrayList2 = aVar2.pages;
            arrayList2.clear();
            arrayList2.addAll(arrayList);
            aVar2.refreshPages();
        }
        try {
            int i7 =
                    AppController.f9455a
                                    .application()
                                    .getSharedPreferences("BLBL", 0)
                                    .getInt("defaultStartPage", 1)
                            - 4;
            if (i7 >= 0) {
                i4 = i7;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewPager viewPager3 = this.J0;
        if (viewPager3 != null) {
            viewPager3.setCurrentItem(i4);
        }
        TabLayout tabLayout2 = this.f9669I0;
        if (tabLayout2 != null) {
            tabLayout2.addOnTabSelectedListener(new HomeTabListener(2));
        }
    }

    public final void j0() {
        if (this.f9667G0 == null) {
            return;
        }
        kotlin.Lazy r7 = (kotlin.Lazy) this.f9668H0;
        if (!((AppState) r7.getValue()).signedIn) {
            Object obj = ImageLoader.f736a;
            AppCompatImageView appCompatImageView = this.f9667G0;
            kotlin.jvm.internal.Intrinsics.checkNotNull(appCompatImageView);
            ImageLoader.loadDefaultAvatar(appCompatImageView);
            return;
        }
        String str = ((AppState) r7.getValue()).avatar;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Object obj2 = ImageLoader.f736a;
        AppCompatImageView appCompatImageView2 = this.f9667G0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(appCompatImageView2);
        ImageLoader.loadAvatar(str, appCompatImageView2, true);
    }

    @Override
    public final void onClick(View view) {
        if (view != null && view.equals(this.f9667G0)) {
            if (((AppState) ((kotlin.Lazy) this.f9668H0).getValue()).signedIn) {
                Context context = view.getContext();
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                        context, "getContext(...)");
                new AccountDialog(context).show();
            } else {
                MainActivity mainActivity = this.activityHost;
                if (mainActivity != null) {
                    mainActivity.showLogin();
                }
            }
        }
    }

    public final void onMessageEvent(String str) {
        if (kotlin.jvm.internal.Intrinsics.areEqual(str, "signIn")
                || kotlin.jvm.internal.Intrinsics.areEqual(str, "updateUserInfo")) {
            j0();
        }
    }

    @Override
    public final void releaseScreenView() {
        TabPagerAdapter aVar = this.f9670K0;
        if (aVar != null) {
            aVar.pages.clear();
            aVar.refreshPages();
        }
        ViewPager viewPager = this.J0;
        if (viewPager != null) {
            viewPager.setAdapter(null);
        }
        super.releaseScreenView();
        this.f9669I0 = null;
        this.f9670K0 = null;
        this.J0 = null;
        this.f9667G0 = null;
    }
}
