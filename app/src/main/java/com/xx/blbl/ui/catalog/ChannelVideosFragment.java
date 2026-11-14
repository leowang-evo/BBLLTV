package com.xx.blbl.ui.catalog;

import android.os.Bundle;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.model.video.channel.ChannelVideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.GetVideoByChannelWrapper;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.List;

public final class ChannelVideosFragment extends PagedListFragment<ChannelVideoModel> {
    public CatalogAdapter O0;
    public final Lazy<NetworkManager> f9508P0 = LazyKt.lazy(AppController::network);
    public final int f9509Q0 = 30;
    public String f9510R0 = "", T0 = "";
    public long f9511S0;

    @Override
    public void screenViewCreated(View view) {
        setScreenTitle(T0);
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new CatalogAdapter(1);
    }

    @Override
    public void loadPage(int page) {
        if (page == 1) f9510R0 = "";
        this.loadingPage = true;
        setLoading(true);
        f9508P0.getValue()
                .getVideosByNewChannel(
                        f9511S0,
                        f9510R0,
                        f9509Q0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<GetVideoByChannelWrapper> response) -> {
                                    this.loadingPage = false;
                                    setLoading(false);
                                    GetVideoByChannelWrapper data =
                                            response == null ? null : response.getData();
                                    if (data == null) return;
                                    if (data.getOffset() != null) f9510R0 = data.getOffset();
                                    setHasMore(data.getHas_more());
                                    List<ChannelVideoModel> videos = data.getList();
                                    if (videos == null || videos.isEmpty()) return;
                                    showContent();
                                    if (page == 1) {
                                        O0.replaceItems(videos);
                                        scrollToTop();
                                    } else O0.appendItems(videos);
                                },
                                error -> {
                                    this.loadingPage = false;
                                    if (page == 1) showNetworkError();
                                    setLoading(false);
                                }));
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        if (getArguments() != null) {
            T0 = getArguments().getString("title", "");
            f9511S0 = getArguments().getLong("tagId");
        }
    }
}
