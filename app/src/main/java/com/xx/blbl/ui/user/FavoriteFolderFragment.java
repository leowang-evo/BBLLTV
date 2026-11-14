package com.xx.blbl.ui.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.FavoriteFolderDetailWrapper;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.HistoryVideoAdapter;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.List;

public final class FavoriteFolderFragment extends PagedListFragment<HistoryVideoModel> {
    public HistoryVideoAdapter O0;
    public final Lazy<NetworkManager> f9636P0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f9637Q0 = LazyKt.lazy(AppController::state);
    public final int f9638R0 = 20;
    public long f9639S0;
    public String T0 = "";

    @Override
    public void reloadContent() {
        if (f9637Q0.getValue().signedIn) refresh();
        else if (hostActivity() != null) hostActivity().showLogin();
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public void bindScreen(View view) {
        super.bindScreen(view);
        setScreenTitle(T0);
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new HistoryVideoAdapter();
    }

    @Override
    public void loadPage(int page) {
        if (!f9637Q0.getValue().signedIn) {
            showStatus(R.mipmap.empty, getString(R.string.need_sign_in));
            return;
        }
        this.loadingPage = true;
        f9636P0.getValue()
                .getFavoriteFolderDetail(
                        f9639S0,
                        page,
                        f9638R0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<FavoriteFolderDetailWrapper> response) -> {
                                    this.loadingPage = false;
                                    setLoading(false);
                                    FavoriteFolderDetailWrapper data =
                                            response == null ? null : response.getData();
                                    List<HistoryVideoModel> videos =
                                            data == null ? null : data.getMedias();
                                    if (videos == null || videos.isEmpty()) {
                                        if (page == 1) showEmptyState();
                                        setHasMore(false);
                                        return;
                                    }
                                    showContent();
                                    if (page == 1) {
                                        setHasMore(true);
                                        O0.replaceItems(videos);
                                        if (this.recyclerView != null)
                                            this.recyclerView.requestFocus();
                                        String cover = "";
                                        for (HistoryVideoModel video : videos)
                                            if (!TextUtils.isEmpty(video.getCover())) {
                                                cover = video.getCover();
                                                break;
                                            }
                                        AppController.instance()
                                                .getSharedPreferences("BLBL", 0)
                                                .edit()
                                                .putString("fav" + f9639S0, cover)
                                                .apply();
                                    } else O0.appendItems(videos);
                                    if (videos.size() < f9638R0) setHasMore(false);
                                },
                                error -> {
                                    this.loadingPage = false;
                                    setLoading(false);
                                    if (page == 1) showNetworkError();
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (!isHidden()
                && ("signIn".equals(event)
                        || "updateUserInfo".equals(event)
                        || "signOut".equals(event)
                        || "clickTab4".equals(event))) refresh();
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        if (getArguments() != null) {
            f9639S0 = getArguments().getLong("id");
            T0 = String.valueOf(getArguments().getString("title"));
        }
    }
}
