package com.xx.blbl.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;

import java.util.ArrayList;
import java.util.List;

/** Application list adapter with a focus target and optional load-more row. */
public abstract class PagedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int LOAD_MORE_TYPE = 123456;
    public final ArrayList items = new ArrayList();
    private boolean hasLoadMoreRow = true;
    public View lastFocusedItem;

    public void appendItems(List list) {
        if (list == null) throw new NullPointerException("list");
        int start = this.items.size();
        List snapshot = new ArrayList(list);
        this.items.addAll(snapshot);
        if (!snapshot.isEmpty()) notifyItemRangeInserted(start, snapshot.size());
    }

    public void restoreFocus() {
        if (this.lastFocusedItem != null) this.lastFocusedItem.requestFocus();
    }

    public void rememberFocusedItem(View view, int position) { lastFocusedItem = view; }
    public void clearRememberedFocus() { lastFocusedItem = null; }
    public void cancelPendingUpdates() {}
    public void resetPositionOnNextUpdate() {}
    public android.os.Parcelable captureViewportState(androidx.recyclerview.widget.LinearLayoutManager layout) {
        return layout.onSaveInstanceState();
    }
    public void restoreViewportState(android.os.Parcelable state, androidx.recyclerview.widget.LinearLayoutManager layout) {
        layout.onRestoreInstanceState(state);
    }

    public final Object itemAt(int position) {
        return position >= 0 && position < this.items.size() ? this.items.get(position) : null;
    }

    public abstract RecyclerView.ViewHolder createItemHolder(ViewGroup parent, int viewType);

    public void replaceItems(List data) {
        if (data == null) throw new NullPointerException("data");
        List snapshot = new ArrayList(data);
        this.lastFocusedItem = null;
        int previous = this.items.size();
        this.items.clear();
        if (previous != 0) notifyItemRangeRemoved(0, previous);
        this.items.addAll(snapshot);
        if (!snapshot.isEmpty()) notifyItemRangeInserted(0, snapshot.size());
    }

    public final void setHasLoadMoreRow(boolean hasMore) {
        if (hasLoadMoreRow == hasMore) return;
        hasLoadMoreRow = hasMore;
        int position = this.items.size();
        if (hasMore) notifyItemInserted(position);
        else notifyItemRemoved(position);
    }

    @Override
    public final int getItemCount() {
        return this.items.size() + (hasLoadMoreRow ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return position == this.items.size() && hasLoadMoreRow
                ? this.LOAD_MORE_TYPE
                : super.getItemViewType(position);
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != this.LOAD_MORE_TYPE) return createItemHolder(parent, viewType);
        return new LoadingViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_load_more, parent, false));
    }
}
