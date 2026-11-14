package com.xx.blbl.ui.user;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.HistoryListResponse;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class HistoryFragment extends PagedListFragment<HistoryVideoModel> {
    public VideoGridAdapter O0;
    public final Lazy<NetworkManager> f9647P0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f9648Q0 = LazyKt.lazy(AppController::state);
    public final int f9649R0 = 20;
    public long f9650S0;

    @Override
    public String cacheKey() {
        return "historyCacheList";
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && O0 != null) O0.replaceItems((List) value);
    }

    @Override
    public void reloadContent() {
        if (f9648Q0.getValue().signedIn) refresh();
        else if (hostActivity() != null) hostActivity().showLogin();
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new VideoGridAdapter(1);
    }

    @Override
    public void loadPage(int page) {
        if (!f9648Q0.getValue().signedIn) {
            showStatus(R.mipmap.empty, getString(R.string.need_sign_in));
            return;
        }
        if (page == 1) {
            f9650S0 = 0;
            loadCachedData();
        }
        this.loadingPage = true;
        f9647P0.getValue()
                .getHistory(
                        f9650S0,
                        f9649R0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<HistoryListResponse> response) -> {
                                    this.loadingPage = false;
                                    setLoading(false);
                                    HistoryListResponse data =
                                            response == null ? null : response.getData();
                                    ArrayList<HistoryVideoModel> items =
                                            data == null ? null : data.getList();
                                    if (items == null) return;
                                    ArrayList<HistoryVideoModel> videos = new ArrayList<>();
                                    for (HistoryVideoModel item : items)
                                        if (item.getCovers() == null) videos.add(item);
                                    if (!videos.isEmpty()) {
                                        showContent();
                                        if (page == 1) {
                                            setHasMore(true);
                                            O0.replaceItems(videos);
                                            cacheData(videos);
                                            scrollToTop();
                                        } else O0.appendItems(videos);
                                        if (items.size() < f9649R0) setHasMore(false);
                                        f9650S0 = items.get(items.size() - 1).getView_at();
                                    } else if (items.isEmpty()) setHasMore(false);
                                },
                                error -> {
                                    this.loadingPage = false;
                                    if (page == 1) showNetworkError();
                                    setLoading(false);
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (("signIn".equals(event)
                        || "updateUserInfo".equals(event)
                        || "signOut".equals(event)
                        || "clickTab4".equals(event)
                        || "clickTopTab0".equals(event)
                        || "keyMenuPress".equals(event))
                && !isHidden()) {
            refresh();
            return;
        }
        if (event == null || !event.startsWith("playUgc")) return;
        try {
            String[] values = event.split("\\|", -1);
            if (values.length == 4 && O0 != null)
                O0.f(
                        Long.parseLong(values[1]),
                        Long.parseLong(values[2]),
                        Long.parseLong(values[3]));
        } catch (NumberFormatException ignored) {
            /* An unrelated event must not break the list. */
        }
    }
}
