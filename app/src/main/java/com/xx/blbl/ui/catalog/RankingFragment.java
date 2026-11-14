package com.xx.blbl.ui.catalog;

import android.os.Bundle;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.model.CategoryModel;
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

public final class RankingFragment extends PagedListFragment<VideoModel> {
    public VideoGridAdapter O0;
    public final Lazy<NetworkManager> f9599P0 = LazyKt.lazy(AppController::network);
    public CategoryModel f9600Q0;

    @Override
    public String cacheKey() {
        return f9600Q0 == null ? super.cacheKey() : "categoryCacheList" + f9600Q0.getId();
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && O0 != null && O0.items.isEmpty()) O0.replaceItems((List) value);
    }

    @Override
    public void bindScreen(View view) {
        super.bindScreen(view);
        setHasMore(false);
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new VideoGridAdapter(0);
    }

    @Override
    public void loadPage(int page) {
        if (f9600Q0 == null) return;
        final int request = beginPageRequest(page);
        if (page == 1) loadCachedData();
        this.loadingPage = true;
        setLoading(true);
        f9599P0.getValue()
                .getRanking(
                        f9600Q0.getId(),
                        ScreenResult.bind(
                                this,
                                (BaseResponse<ListDataModel<VideoModel>> response) -> {
                                    if (!isCurrentPageRequest(request)) return;
                                    this.loadingPage = false;
                                    setLoading(false);
                                    ListDataModel<VideoModel> data =
                                            response == null ? null : response.getData();
                                    ArrayList<VideoModel> items =
                                            data == null ? null : data.getList();
                                    if (items != null && !items.isEmpty()) {
                                        showContent();
                                        O0.replaceItems(items);
                                        cacheData(items);

                                    } else showEmptyState();
                                },
                                error -> {
                                    if (!isCurrentPageRequest(request)) return;
                                    this.loadingPage = false;
                                    if (page == 1) showNetworkError();
                                    setLoading(false);
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (isHidden()) return;
        if ("clickTab1".equals(event)) refreshFromTop();
        else if ("backPressed".equals(event)) scrollToTop();
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        if (getArguments() != null)
            f9600Q0 = (CategoryModel) getArguments().getSerializable("model");
    }

    @Override
    public void releaseScreenView() {
        super.releaseScreenView();
        O0 = null;
    }
}
