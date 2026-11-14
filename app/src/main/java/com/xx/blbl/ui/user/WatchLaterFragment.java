package com.xx.blbl.ui.user;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.LaterWatchWrapper;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class WatchLaterFragment extends PagedListFragment<VideoModel> {
    public VideoGridAdapter O0;
    public final Lazy<NetworkManager> f9653P0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f9654Q0 = LazyKt.lazy(AppController::state);

    @Override
    public String cacheKey() {
        return "watchLaterCacheList";
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && O0 != null) O0.replaceItems((List) value);
    }

    @Override
    public void reloadContent() {
        if (f9654Q0.getValue().signedIn) refresh();
        else if (hostActivity() != null) hostActivity().showLogin();
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new VideoGridAdapter(0);
    }

    @Override
    public void loadPage(int page) {
        if (!f9654Q0.getValue().signedIn) {
            showStatus(R.mipmap.empty, getString(R.string.need_sign_in));
            return;
        }
        this.loadingPage = true;
        setHasMore(false);
        loadCachedData();
        f9653P0.getValue()
                .getLaterWatch(
                        ScreenResult.bind(
                                this,
                                (BaseResponse<LaterWatchWrapper> response) -> {
                                    this.loadingPage = false;
                                    setLoading(false);
                                    LaterWatchWrapper data =
                                            response == null ? null : response.getData();
                                    List<VideoModel> videos = data == null ? null : data.getList();
                                    if (videos == null || videos.isEmpty()) {
                                        showEmptyState();
                                        return;
                                    }
                                    showContent();
                                    O0.replaceItems(videos);
                                    cacheData(new ArrayList<>(videos));
                                    scrollToTop();
                                },
                                error -> {
                                    this.loadingPage = false;
                                    if (page == 1) showNetworkError();
                                    setLoading(false);
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (isHidden()) return;
        if ("signIn".equals(event)
                || "updateUserInfo".equals(event)
                || "signOut".equals(event)
                || "clickTab4".equals(event)
                || "clickTopTab4".equals(event)) refresh();
        else if ("backPressed".equals(event)) scrollToTop();
    }
}
