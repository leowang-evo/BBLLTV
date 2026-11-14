package com.xx.blbl.ui.catalog;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.media3.common.PlaybackException;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xx.blbl.R;
import com.xx.blbl.model.CategoryModel;
import com.xx.blbl.ui.navigation.TabPagerAdapter;
import com.xx.blbl.ui.screen.TabScreenFragment;

import java.util.ArrayList;

public final class CategoryFragment extends TabScreenFragment {
    public TabLayout f9596G0;
    public ViewPager f9597H0;
    public TabPagerAdapter f9598I0;

    @Override
    public final int layoutResource() {
        return R.layout.fragment_category;
    }

    @Override
    public final void bindScreen(View view) {
        this.f9596G0 = (TabLayout) view.findViewById(R.id.tabLayout);
        this.f9597H0 = (ViewPager) view.findViewById(R.id.viewPager);
        FragmentManager qI = childFragments();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                qI, "getChildFragmentManager(...)");
        TabPagerAdapter aVar = new TabPagerAdapter(qI, 0);
        aVar.pages = new ArrayList();
        this.f9598I0 = aVar;
        ViewPager viewPager = this.f9597H0;
        if (viewPager != null) {
            viewPager.setAdapter(aVar);
        }
        TabLayout tabLayout = this.f9596G0;
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(this.f9597H0);
        }
        ViewPager viewPager2 = this.f9597H0;
        if (viewPager2 != null) {
            viewPager2.setOffscreenPageLimit(1);
        }
        ArrayList arrayList = new ArrayList();
        String strN = stringResource(R.string.allWeb);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN, "getString(...)");
        arrayList.add(new CategoryModel(0, strN));
        String strN2 = stringResource(R.string.cartoon);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN2, "getString(...)");
        arrayList.add(new CategoryModel(1005, strN2));
        String strN3 = stringResource(R.string.music);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN3, "getString(...)");
        arrayList.add(new CategoryModel(PlaybackException.ERROR_CODE_TIMEOUT, strN3));
        String strN4 = stringResource(R.string.dance);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN4, "getString(...)");
        arrayList.add(new CategoryModel(PlaybackException.ERROR_CODE_FAILED_RUNTIME_CHECK, strN4));
        String strN5 = stringResource(R.string.game);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN5, "getString(...)");
        arrayList.add(new CategoryModel(1008, strN5));
        String strN6 = stringResource(R.string.knowledge);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN6, "getString(...)");
        arrayList.add(new CategoryModel(1010, strN6));
        String strN7 = stringResource(R.string.science);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN7, "getString(...)");
        arrayList.add(new CategoryModel(1012, strN7));
        String strN8 = stringResource(R.string.sport);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN8, "getString(...)");
        arrayList.add(new CategoryModel(1018, strN8));
        String strN9 = stringResource(R.string.auto);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN9, "getString(...)");
        arrayList.add(new CategoryModel(1013, strN9));
        String strN10 = stringResource(R.string.lifestyle);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN10, "getString(...)");
        arrayList.add(new CategoryModel(160, strN10));
        String strN11 = stringResource(R.string.food);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN11, "getString(...)");
        arrayList.add(new CategoryModel(1020, strN11));
        String strN12 = stringResource(R.string.pet);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN12, "getString(...)");
        arrayList.add(new CategoryModel(1024, strN12));
        String strN13 = stringResource(R.string.kichiku);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN13, "getString(...)");
        arrayList.add(new CategoryModel(1007, strN13));
        String strN14 = stringResource(R.string.fashion);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN14, "getString(...)");
        arrayList.add(new CategoryModel(1014, strN14));
        String strN15 = stringResource(R.string.entainment);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN15, "getString(...)");
        arrayList.add(new CategoryModel(5, strN15));
        String strN16 = stringResource(R.string.film_and_television);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN16, "getString(...)");
        arrayList.add(new CategoryModel(1001, strN16));
        String strN17 = stringResource(R.string.documentary);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN17, "getString(...)");
        arrayList.add(new CategoryModel(177, strN17));
        String strN18 = stringResource(R.string.movie);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN18, "getString(...)");
        arrayList.add(new CategoryModel(23, strN18));
        String strN19 = stringResource(R.string.series);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN19, "getString(...)");
        arrayList.add(new CategoryModel(11, strN19));
        TabPagerAdapter aVar2 = this.f9598I0;
        if (aVar2 != null) {
            ArrayList arrayList2 = aVar2.pages;
            arrayList2.clear();
            arrayList2.addAll(arrayList);
            aVar2.refreshPages();
        }
    }

    @Override
    public final void releaseScreenView() {
        TabPagerAdapter aVar = this.f9598I0;
        if (aVar != null) {
            aVar.pages.clear();
            aVar.refreshPages();
        }
        ViewPager viewPager = this.f9597H0;
        if (viewPager != null) {
            viewPager.removeAllViews();
        }
        ViewPager viewPager2 = this.f9597H0;
        if (viewPager2 != null) {
            viewPager2.setAdapter(null);
        }
        super.releaseScreenView();
        this.f9596G0 = null;
        this.f9598I0 = null;
        this.f9597H0 = null;
    }
}
