package com.xx.blbl.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.RecommendListDataModel;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class RecommendedVideosFragment extends PagedListFragment<VideoModel> {
    public VideoGridAdapter O0;
    public final Lazy<NetworkManager> f9609P0 = LazyKt.lazy(AppController::network);
    public final AppState f9610Q0 = AppController.state();
    public final int f9611R0 = 24;

    @Override
    public void saveScreenState(Bundle state) {}

    private boolean receivedNetworkContent;

    @Override
    public String cacheKey() {
        return "recommendCacheList";
    }

    @Override
    public void renderCachedData(Object value) {
        // A slow disk read must never replace newer network data or reset focus.
        if (value instanceof List && O0 != null && O0.items.isEmpty() && !receivedNetworkContent)
            O0.replaceItems((List) value);
    }

    @Override
    public void bindScreen(View view) {
        receivedNetworkContent = false;
        super.bindScreen(view);
        setHasMore(f9610Q0.signedIn);
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new VideoGridAdapter(0);
    }

    @Override
    public void loadPage(int page) {
        final int request = beginPageRequest(page);
        if (page == 1) loadCachedData();
        this.loadingPage = true;
        f9609P0.getValue()
                .getRecommendVideos(
                        page,
                        f9611R0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<RecommendListDataModel<VideoModel>> response) -> {
                                    if (!isCurrentPageRequest(request)) return;
                                    setLoading(false);
                                    this.loadingPage = false;
                                    RecommendListDataModel<VideoModel> data =
                                            response == null ? null : response.getData();
                                    ArrayList<VideoModel> items =
                                            data == null ? null : data.getItem();
                                    if (items == null) return;
                                    if (page == 1) receivedNetworkContent = true;
                                    ArrayList<VideoModel> valid = new ArrayList<>();
                                    for (VideoModel video : items)
                                        if (!TextUtils.isEmpty(video.getTitle())
                                                && !TextUtils.isEmpty(video.getBvid()))
                                            valid.add(video);
                                    if (valid.isEmpty()) {
                                        if (page == 1) {
                                            O0.replaceItems(java.util.Collections.emptyList());
                                            setHasMore(false);
                                            showEmptyState();
                                        }
                                        return;
                                    }
                                    showContent();
                                    if (page == 1) {
                                        O0.replaceItems(valid);
                                        setHasMore(true);
                                        cacheData(items);

                                    } else O0.appendItems(valid);
                                    if (items.size() < f9611R0) setHasMore(false);
                                },
                                error -> {
                                    if (!isCurrentPageRequest(request)) return;
                                    this.loadingPage = false;
                                    if (page == 1 && O0.items.isEmpty()) showNetworkError();
                                    setLoading(false);
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (isHidden()) return;
        if ("updateUserInfo".equals(event)) {
            setHasMore(f9610Q0.signedIn);
            if (O0 == null || O0.items.isEmpty()) refresh();
        } else if ("signIn".equals(event)
                || "clickTab0".equals(event)
                || "clickTopTab0".equals(event)
                || "keyMenuPress".equals(event)) {
            setHasMore(f9610Q0.signedIn);
            refreshFromTop();
        } else if ("backPressed".equals(event)) scrollToTop();
    }
}
