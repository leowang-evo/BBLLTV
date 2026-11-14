package com.xx.blbl.ui.live;

import android.os.Bundle;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.model.live.LiveRoomItem;
import com.xx.blbl.network.*;
import com.xx.blbl.network.response.*;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.*;

/** Live area list retaining public application routes and pagination. */
public final class LiveAreaFragment extends PagedListFragment<LiveRoomItem> {
    public CatalogAdapter O0;
    public final Lazy<NetworkManager> f12093P0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f12094Q0 = LazyKt.lazy(AppController::state);
    public long f12095R0, f12096S0;
    public String T0 = "";

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        Bundle args = getArguments();
        if (args != null) {
            f12095R0 = args.getLong("areaId");
            f12096S0 = args.getLong("areaParentId");
            T0 = String.valueOf(args.getString("title"));
        }
    }

    @Override
    public void reloadContent() {
        refresh();
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public void bindScreen(View view) {
        super.bindScreen(view);
        setScreenTitle(T0);
        setHasMore(false);
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new CatalogAdapter(2);
    }

    @Override
    public void loadPage(int page) {
        final int request = beginPageRequest(page);
        final View requestingView = screenView;
        this.loadingPage = true;
        f12093P0.getValue()
                .getLiveCategoryDetailList(
                        f12095R0,
                        f12096S0,
                        page,
                        new NetResultCallback<BaseResponse<LiveCategoryDetailListWrapper>>() {
                            public void onFailure(Throwable error) {
                                runOnMain(() -> failed(error));
                            }

                            private boolean current() {
                                return isCurrentPageRequest(request) && screenView == requestingView
                                        && requestingView != null && O0 != null && isAttachedToHost();
                            }

                            private void failed(Throwable error) {
                                if (!current()) return;
                                LiveAreaFragment.this.loadingPage = false;
                                setLoading(false);
                                String message = com.xx.blbl.platform.RequestMessages.error(
                                        requireContext(), error);
                                if (page == 1) showError(message);
                                else {
                                    setHasMore(false);
                                    android.widget.Toast.makeText(requireContext(), message,
                                            android.widget.Toast.LENGTH_SHORT).show();
                                }
                            }

                            public void onResponse(
                                    BaseResponse<LiveCategoryDetailListWrapper> response) {
                                runOnMain(() -> apply(response));
                            }

                            private void apply(BaseResponse<LiveCategoryDetailListWrapper> response) {
                                if (!current()) return;
                                ApiFailure error = ApiFailure.fromResponse(response);
                                if (error != null) { failed(error); return; }
                                LiveAreaFragment.this.loadingPage = false;
                                setLoading(false);
                                LiveCategoryDetailListWrapper data =
                                        response == null ? null : response.getData();
                                List<LiveRoomItem> list = data == null ? null : data.getList();
                                if (list == null || list.isEmpty()) {
                                    setHasMore(false);
                                    if (page == 1) {
                                        O0.replaceItems(Collections.emptyList());
                                        showEmptyState();
                                    }
                                    return;
                                }
                                showContent();
                                setHasMore(data.getCount() > 0
                                        ? (long) page * 20 < data.getCount() : list.size() >= 20);
                                if (O0 != null) {
                                    if (page == 1) {
                                        O0.replaceItems(list);
                                        if (LiveAreaFragment.this.recyclerView != null)
                                            LiveAreaFragment.this.recyclerView.requestFocus();
                                    } else O0.appendItems(list);
                                }
                            }
                        });
    }

    @Override public void releaseScreenView() {
        O0 = null;
        super.releaseScreenView();
    }
}
