package com.xx.blbl.ui.screen;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

/** Original paginated list application behavior using public RecyclerView APIs. */
public abstract class PagedListFragment<MODEL> extends BaseScreenFragment {
    public RecyclerView recyclerView;
    public LinearLayoutManager listLayout;
    public PagedListAdapter listAdapter;
    public int visibleItemCount;
    public boolean loadingPage;
    public int pageNumber = 1;
    public boolean hasMorePages = true;
    public final int prefetchDistance = 12;
    private int contentRequestGeneration;
    private boolean refreshPending;
    private boolean deferHomeLoad() {
        return this instanceof com.xx.blbl.ui.home.RecommendedVideosFragment
                || this instanceof com.xx.blbl.ui.home.PopularVideosFragment
                || this instanceof com.xx.blbl.ui.home.SeriesLanesFragment;
    }

    @Override public void screenResumed() {
        super.screenResumed();
        if (refreshPending) refresh();
    }
    protected final int beginPageRequest(int page) {
        if (page == 1) contentRequestGeneration++;
        return contentRequestGeneration;
    }
    protected final boolean isCurrentPageRequest(int token) { return token == contentRequestGeneration; }
    public final void refreshFromTop() {
        if (listAdapter != null) listAdapter.resetPositionOnNextUpdate();
        refresh();
    }

    @Override
    public void screenHiddenChanged(boolean hidden) {
        if (!hidden && this.listAdapter != null) this.listAdapter.restoreFocus();
    }

    @Override
    public final int layoutResource() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void reloadContent() {
        refresh();
    }

    @Override
    public void bindScreen(View view) {
        this.recyclerView = view.findViewById(R.id.recyclerView);
        this.listAdapter = createAdapter();
        if (this.recyclerView != null) this.recyclerView.setAdapter(this.listAdapter);
        this.listLayout = createLayoutManager();
        if (this.listLayout instanceof GridLayoutManager) {
            GridLayoutManager grid = (GridLayoutManager) this.listLayout;
            grid.setSpanSizeLookup(
                    new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return PagedListFragment.this.listAdapter != null
                                            && position
                                                    == PagedListFragment.this.listAdapter.items
                                                            .size()
                                    ? grid.getSpanCount()
                                    : 1;
                        }
                    });
        }
        if (this.recyclerView != null) {
            this.recyclerView.setLayoutManager(this.listLayout);
            if (this instanceof com.xx.blbl.ui.home.RecommendedVideosFragment
                    || this instanceof com.xx.blbl.ui.home.PopularVideosFragment) {
                this.recyclerView.setItemAnimator(null);
                this.recyclerView.setItemViewCacheSize(8);
                this.recyclerView.setHasFixedSize(true);
            }
            this.recyclerView.addOnScrollListener(
                    new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recycler, int dx, int dy) {
                            LinearLayoutManager layout = PagedListFragment.this.listLayout;
                            if (layout == null) return;
                            PagedListFragment.this.visibleItemCount = layout.getItemCount();
                            int lastVisible = layout.findLastVisibleItemPosition();
                            if (PagedListFragment.this.loadingPage
                                    || PagedListFragment.this.visibleItemCount
                                            > lastVisible + PagedListFragment.this.prefetchDistance
                                    || !PagedListFragment.this.hasMorePages) return;
                            loadPage(++PagedListFragment.this.pageNumber);
                        }
                    });
        }
        refresh();
    }

    public abstract PagedListAdapter createAdapter();

    public int spanCount() {
        return 4;
    }

    public LinearLayoutManager createLayoutManager() {
        return new WrapContentGirdLayoutManager(requireContext(), spanCount());
    }

    public final void refresh() {
        if (deferHomeLoad() && !isResumed()) {
            refreshPending = true;
            return;
        }
        refreshPending = false;
        this.pageNumber = 1;
        loadPage(1);
    }

    public abstract void loadPage(int page);

    public final void scrollToTop() {
        if (this.recyclerView != null) this.recyclerView.scrollToPosition(0);
    }

    public final void returnToTopNavigation() {
        if (recyclerView == null || listLayout == null) return;
        recyclerView.stopScroll();
        com.xx.blbl.platform.RecyclerFocus.cancel(recyclerView);
        if (listAdapter != null) listAdapter.clearRememberedFocus();
        listLayout.scrollToPositionWithOffset(0, 0);
    }

    public final void setHasMore(boolean hasMore) {
        if (this.listAdapter != null) this.listAdapter.setHasLoadMoreRow(hasMore);
        this.hasMorePages = hasMore;
    }

    @Override
    public void releaseScreenView() {
        contentRequestGeneration++;
        refreshPending = false;
        super.releaseScreenView();
        if (this.listAdapter != null) {
            this.listAdapter.cancelPendingUpdates();
            this.listAdapter.lastFocusedItem = null;
            int count = this.listAdapter.items.size();
            this.listAdapter.items.clear();
            this.listAdapter.notifyItemRangeRemoved(0, count);
        }
        this.listAdapter = null;
        this.listLayout = null;
        this.recyclerView = null;
    }
}
