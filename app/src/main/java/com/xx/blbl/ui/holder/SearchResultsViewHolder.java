package com.xx.blbl.ui.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.search.SearchItemModel;
import com.xx.blbl.model.search.SearchType;
import com.xx.blbl.network.ApiFailure;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.SearchResponseWrapper;
import com.xx.blbl.platform.RequestMessages;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.adapter.VideoGridAdapter;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

import java.util.List;

/** Search categories share paging, stale-response guards, and a visible retry state. */
public final class SearchResultsViewHolder extends RecyclerView.ViewHolder {
    public static final int f14869J = 0;
    private final RecyclerView recyclerView;
    private final TextView status;
    private WrapContentGirdLayoutManager layoutManager;
    private PagedListAdapter adapter;
    private SearchType searchType;
    private String keyword, order;
    private int pageNumber, generation;
    private boolean loading, hasMore;

    public SearchResultsViewHolder(View root) {
        super(root);
        recyclerView = root.findViewById(R.id.recyclerView);
        status = root.findViewById(R.id.search_result_status);
        status.setOnClickListener(
                view -> {
                    pageNumber = 1;
                    hasMore = true;
                    loadPage();
                });
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView view, int dx, int dy) {
                        if (loading
                                || !hasMore
                                || layoutManager == null
                                || adapter == null
                                || adapter.items.isEmpty()) return;
                        if (layoutManager.findLastVisibleItemPosition() + 12 < adapter.items.size())
                            return;
                        pageNumber++;
                        loadPage();
                    }
                });
    }

    public void bindSeries(SearchType type, String term, List items) {
        configure(type, term, "totalrank", new CatalogAdapter(3), 6, items);
    }

    public void bindVideos(SearchType type, List items, String term, String sorting) {
        configure(type, term, sorting, new VideoGridAdapter(2), 4, items);
    }

    public void bindUsers(String term) {
        configure(SearchType.User, term, "totalrank", new CatalogAdapter(4), 6, null);
    }

    private void configure(
            SearchType type,
            String term,
            String sorting,
            PagedListAdapter list,
            int columns,
            List initialItems) {
        generation++;
        searchType = type;
        keyword = term;
        order = sorting;
        pageNumber = 1;
        hasMore = true;
        adapter = list;
        adapter.setHasLoadMoreRow(false);
        layoutManager = new WrapContentGirdLayoutManager(itemView.getContext(), columns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        if (initialItems != null) adapter.replaceItems(initialItems);
        status.setVisibility(View.GONE);
        loadPage();
    }

    private void showStatus(String message, boolean retry) {
        status.setText(
                retry
                        ? itemView.getContext().getString(R.string.search_result_retry, message)
                        : message);
        status.setEnabled(retry);
        status.setFocusable(retry);
        status.setVisibility(View.VISIBLE);
    }

    private void failed(Throwable error) {
        loading = false;
        hasMore = false;
        adapter.setHasLoadMoreRow(false);
        String message = RequestMessages.error(itemView.getContext(), error);
        if (adapter.items.isEmpty()) showStatus(message, true);
        else
            android.widget.Toast.makeText(
                            itemView.getContext(), message, android.widget.Toast.LENGTH_SHORT)
                    .show();
    }

    public void loadPage() {
        if (adapter == null || searchType == null) return;
        loading = true;
        int request = ++generation;
        int requestedPage = pageNumber;
        if (adapter.items.isEmpty())
            showStatus(itemView.getContext().getString(R.string.search_result_loading), false);
        AppController.network()
                .searchByTypeWbi(
                        searchType,
                        keyword,
                        requestedPage,
                        order,
                        new NetResultCallback<BaseResponse<SearchResponseWrapper>>() {
                            @Override
                            public void onFailure(Throwable error) {
                                if (request == generation) failed(error);
                            }

                            @Override
                            public void onResponse(BaseResponse<SearchResponseWrapper> response) {
                                if (request != generation) return;
                                ApiFailure error = ApiFailure.fromResponse(response);
                                if (error != null) {
                                    failed(error);
                                    return;
                                }
                                loading = false;
                                List<SearchItemModel> items =
                                        response.getData() == null
                                                ? null
                                                : response.getData().getResult();
                                hasMore =
                                        items != null && response.getData().getNumPages() > 0
                                                ? requestedPage < response.getData().getNumPages()
                                                : items != null && items.size() >= 42;
                                adapter.setHasLoadMoreRow(hasMore);
                                if (items == null || items.isEmpty()) {
                                    if (requestedPage == 1) {
                                        adapter.replaceItems(java.util.Collections.emptyList());
                                        showStatus(
                                                itemView.getContext()
                                                        .getString(R.string.search_empty),
                                                true);
                                    }
                                    return;
                                }
                                status.setVisibility(View.GONE);
                                if (requestedPage == 1) adapter.replaceItems(items);
                                else adapter.appendItems(items);
                            }
                        });
    }
}
