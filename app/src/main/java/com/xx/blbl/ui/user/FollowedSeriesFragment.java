package com.xx.blbl.ui.user;

import android.os.Bundle;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.MyFollowingResponseWrapper;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class FollowedSeriesFragment extends PagedListFragment<SeriesModel> {
    public CatalogAdapter O0;
    public final Lazy<NetworkManager> f9657P0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f9658Q0 = LazyKt.lazy(AppController::state);
    public final int f9659R0 = 20;
    public int f9660S0 = 1;

    @Override
    public String cacheKey() {
        return f9660S0 == 2 ? "followingSeriesCacheList" : "followingAnimationCacheList";
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && O0 != null) O0.replaceItems((List) value);
    }

    @Override
    public void reloadContent() {
        if (f9658Q0.getValue().signedIn) refresh();
        else if (hostActivity() != null) hostActivity().showLogin();
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new CatalogAdapter(0);
    }

    @Override
    public int spanCount() {
        return 6;
    }

    @Override
    public void loadPage(int page) {
        if (!f9658Q0.getValue().signedIn) {
            showStatus(R.mipmap.empty, getString(R.string.need_sign_in));
            return;
        }
        this.loadingPage = true;
        if (page == 1) loadCachedData();
        f9657P0.getValue()
                .getMyFollowingSeries(
                        f9660S0,
                        page,
                        f9659R0,
                        f9658Q0.getValue().userId,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<MyFollowingResponseWrapper> response) -> {
                                    this.loadingPage = false;
                                    setLoading(false);
                                    MyFollowingResponseWrapper data =
                                            response == null ? null : response.getData();
                                    List<SeriesModel> series = data == null ? null : data.getList();
                                    if (series == null) return;
                                    if (series.isEmpty()) {
                                        if (page == 1) showEmptyState();
                                        setHasMore(false);
                                        return;
                                    }
                                    showContent();
                                    if (page == 1) {
                                        O0.replaceItems(series);
                                        setHasMore(true);
                                        cacheData(new ArrayList<>(series));
                                        scrollToTop();
                                    } else O0.appendItems(series);
                                    if (series.size() < f9659R0) setHasMore(false);
                                },
                                error -> {
                                    this.loadingPage = false;
                                    if (page == 1) showNetworkError();
                                    setLoading(false);
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (!isHidden()
                && ("clickTab4".equals(event)
                        || "updateUserInfo".equals(event)
                        || "signIn".equals(event)
                        || "signOut".equals(event))) refresh();
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        if (getArguments() != null) f9660S0 = getArguments().getInt("type");
    }
}
