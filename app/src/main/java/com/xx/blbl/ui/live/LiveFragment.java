package com.xx.blbl.ui.live;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.live.LiveAreaCategoryParent;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.home.HomeTabListener;
import com.xx.blbl.ui.navigation.TabPagerAdapter;
import com.xx.blbl.ui.screen.TabScreenFragment;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

/** Live top-level areas, including the original synthetic recommendation first page. */
public final class LiveFragment extends TabScreenFragment {
    public final Lazy<NetworkManager> f9664G0 = LazyKt.lazy(AppController::network);
    public TabLayout f9665H0;
    public ViewPager f9666I0;
    public TabPagerAdapter J0;

    @Override
    public int layoutResource() {
        return R.layout.fragment_category;
    }

    @Override
    public void bindScreen(View view) {
        f9665H0 = view.findViewById(R.id.tabLayout);
        f9666I0 = view.findViewById(R.id.viewPager);
        J0 = new TabPagerAdapter(getChildFragmentManager(), 2);
        J0.pages = new ArrayList<>();
        if (f9666I0 != null) {
            f9666I0.setAdapter(J0);
            f9666I0.setOffscreenPageLimit(1);
        }
        if (f9665H0 != null) {
            f9665H0.setupWithViewPager(f9666I0);
            f9665H0.addOnTabSelectedListener(new HomeTabListener(1));
        }
        reloadContent();
    }

    @Override
    public void reloadContent() {
        ArrayList<LiveAreaCategoryParent> categories = new ArrayList<>();
        LiveAreaCategoryParent recommendation = new LiveAreaCategoryParent();
        recommendation.setName(getString(R.string.recommend));
        categories.add(recommendation);
        setLoading(true);
        f9664G0.getValue()
                .getLiveArea(
                        new NetResultCallback<BaseResponse<List<LiveAreaCategoryParent>>>() {
                            @Override
                            public void onFailure(Throwable failure) {
                                runOnMain(
                                        () -> {
                                            if (isAttachedToHost()) {
                                                setLoading(false);
                                                showError(
                                                        failure == null
                                                                ? null
                                                                : failure.getMessage());
                                            }
                                        });
                            }

                            @Override
                            public void onResponse(
                                    BaseResponse<List<LiveAreaCategoryParent>> response) {
                                runOnMain(
                                        () -> {
                                            if (!isAttachedToHost() || J0 == null) return;
                                            setLoading(false);
                                            if (response != null && response.getData() != null)
                                                categories.addAll(response.getData());
                                            J0.pages.clear();
                                            J0.pages.addAll(categories);
                                            J0.refreshPages();
                                        });
                            }
                        });
    }

    @Override
    public void releaseScreenView() {
        if (f9666I0 != null) f9666I0.setAdapter(null);
        f9665H0 = null;
        f9666I0 = null;
        J0 = null;
        super.releaseScreenView();
    }
}
