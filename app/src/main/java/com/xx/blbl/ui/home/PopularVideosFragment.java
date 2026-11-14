package com.xx.blbl.ui.home;

import android.widget.Toast;

import com.xx.blbl.AppController;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.ListDataModel;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class PopularVideosFragment extends PagedListFragment<VideoModel> {
    public VideoGridAdapter O0;
    public final Lazy<NetworkManager> f9603P0 = LazyKt.lazy(AppController::network);
    public final int f9604Q0 = 24;

    private boolean receivedNetworkContent;

    @Override
    public String cacheKey() {
        return "hotCacheList";
    }

    @Override
    public void renderCachedData(Object value) {
        // A slow disk read must never replace newer network data or reset focus.
        if (value instanceof List && O0 != null && O0.items.isEmpty() && !receivedNetworkContent)
            O0.replaceItems((List) value);
    }

    @Override public void bindScreen(android.view.View view) {
        receivedNetworkContent = false;
        super.bindScreen(view);
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
        f9603P0.getValue()
                .getHotVideos(
                        page,
                        f9604Q0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<ListDataModel<VideoModel>> response) -> {
                                    if (!isCurrentPageRequest(request)) return;
                                    setLoading(false);
                                    this.loadingPage = false;
                                    ListDataModel<VideoModel> data =
                                            response == null ? null : response.getData();
                                    ArrayList<VideoModel> items =
                                            data == null ? null : data.getList();
                                    if (items == null) return;
                                    if (page == 1) receivedNetworkContent = true;
                                    if (items.isEmpty()) {
                                        if (page == 1) {
                                            O0.replaceItems(java.util.Collections.emptyList());
                                            setHasMore(false);
                                            showEmptyState();
                                        }
                                        else setHasMore(false);
                                        return;
                                    }
                                    showContent();
                                    if (page == 1) {
                                        O0.replaceItems(items);
                                        setHasMore(true);
                                        cacheData(items);

                                    } else O0.appendItems(items);
                                    if (items.size() < f9604Q0) setHasMore(false);
                                },
                                error -> {
                                    if (!isCurrentPageRequest(request)) return;
                                    this.loadingPage = false;
                                    if (page == 1 && O0.items.isEmpty()) showNetworkError();
                                    setLoading(false);
                                    if (getContext() != null)
                                        Toast.makeText(
                                                        requireContext(),
                                                        String.valueOf(
                                                                error == null
                                                                        ? null
                                                                        : error.getMessage()),
                                                        Toast.LENGTH_SHORT)
                                                .show();
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (isHidden()) return;
        if ("clickTab0".equals(event)
                || "clickTopTab1".equals(event)
                || "keyMenuPress".equals(event)) refreshFromTop();
        else if ("backPressed".equals(event)) scrollToTop();
    }
}
