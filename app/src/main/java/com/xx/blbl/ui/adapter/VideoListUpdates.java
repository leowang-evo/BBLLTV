package com.xx.blbl.ui.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.BangumiModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoStatModel;
import com.xx.blbl.platform.RecyclerFocus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Immutable render snapshots on a shared worker; only the latest update can commit. */
final class VideoListUpdates {
    private static final ExecutorService WORKER = Executors.newSingleThreadExecutor(task -> {
        Thread thread = new Thread(() -> {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            task.run();
        }, "BBLL-video-diff");
        return thread;
    });
    private final VideoGridAdapter adapter;
    private final Handler main = new Handler(Looper.getMainLooper());
    private RecyclerView recycler;
    private ViewTreeObserver observer;
    private List<Entry> displayed = new ArrayList<>();
    private List<Object> desired;
    private volatile int generation;
    private String focusedKey;
    private int focusedPosition;
    private long focusVersion;
    private boolean dispatching, resetPosition, pendingReset;
    private boolean navigationAtTop;

    private final ViewTreeObserver.OnGlobalFocusChangeListener focusListener = (old, next) -> {
        if (recycler == null) return;
        if ((inside(old) || inside(next)) && !dispatching && !recycler.isComputingLayout())
            focusVersion++;
        if (inside(next)) {
            RecyclerView.ViewHolder holder = recycler.findContainingViewHolder(next);
            if (holder != null) remember(next, holder.getBindingAdapterPosition());
        }
    };

    VideoListUpdates(VideoGridAdapter adapter) { this.adapter = adapter; }

    private boolean inside(View view) {
        if (view == recycler && view != null) return true;
        for (ViewParent parent = view == null ? null : view.getParent(); parent != null; parent = parent.getParent())
            if (parent == recycler) return true;
        return false;
    }

    void attach(RecyclerView view) {
        recycler = view;
        displayed = snapshots(adapter.items);
        view.setItemAnimator(null);
        observer = view.getViewTreeObserver();
        observer.addOnGlobalFocusChangeListener(focusListener);
    }

    void cancel() {
        generation++;
        if (observer != null && observer.isAlive()) observer.removeOnGlobalFocusChangeListener(focusListener);
        if (recycler != null && recycler.getViewTreeObserver().isAlive())
            recycler.getViewTreeObserver().removeOnGlobalFocusChangeListener(focusListener);
        observer = null;
        recycler = null;
        resetPosition = pendingReset = false;
        desired = null;
        displayed = new ArrayList<>();
        focusedKey = null;
        navigationAtTop = false;
    }

    void clearRememberedFocus() {
        focusedKey = null;
        focusedPosition = 0;
        focusVersion++;
        navigationAtTop = true;
    }

    void remember(View view, int position) {
        navigationAtTop = false;
        adapter.lastFocusedItem = view;
        if (position >= 0 && position < displayed.size()) {
            focusedKey = displayed.get(position).key;
            focusedPosition = position;
        }
    }

    void resetOnNextUpdate() { resetPosition = true; }

    List pendingItems() {
        return new ArrayList(desired == null ? adapter.items : desired);
    }

    void append(List values) {
        List next = pendingItems();
        next.addAll(new ArrayList(values));
        replace(next);
    }

    void replace(List values) {
        final List<Object> next = new ArrayList<>(values);
        final List<Entry> after = snapshots(next);
        final int token = ++generation;
        desired = next;
        pendingReset |= resetPosition;
        resetPosition = false;
        final boolean goToStart = pendingReset;
        final List<Entry> before = displayed;
        if (before.isEmpty()) {
            commit(token, next, after, DiffUtil.calculateDiff(diff(before, after)), goToStart);
            return;
        }
        WORKER.execute(() -> {
            if (generation != token) return;
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(diff(before, after));
            main.post(() -> { if (generation == token) commit(token, next, after, result, goToStart); });
        });
    }

    private void commit(int token, List<Object> next, List<Entry> after, DiffUtil.DiffResult result, boolean goToStart) {
        // Legacy adapters expose their backing list. Never dispatch a diff against a changed base.
        if (adapter.items.size() != displayed.size()) {
            displayed = snapshots(adapter.items);
            replace(next);
            return;
        }
        for (int i = 0; i < displayed.size(); i++) {
            if (adapter.items.get(i) != displayed.get(i).model) {
                displayed = snapshots(adapter.items);
                replace(next);
                return;
            }
        }
        RecyclerView view = recycler;
        boolean hadFocus = view != null && view.hasFocus();
        Parcelable anchor = capture(false);
        int oldFocusPosition = focusedPosition;
        String oldFocusKey = focusedKey;
        long expectedFocusVersion = focusVersion;
        pendingReset = false;
        dispatching = true;
        adapter.items.clear();
        adapter.items.addAll(next);
        displayed = after;
        result.dispatchUpdatesTo(adapter);
        dispatching = false;
        if (view == null) return;
        boolean start = goToStart || navigationAtTop;
        int destination = start ? 0 : positionOf(oldFocusKey, oldFocusPosition);
        if (destination >= 0 && (hadFocus || oldFocusKey != null)) {
            focusedKey = displayed.get(destination).key;
            focusedPosition = destination;
        } else if (destination < 0) {
            focusedKey = null;
            adapter.lastFocusedItem = null;
        }
        if (start && view.getLayoutManager() instanceof LinearLayoutManager)
            ((LinearLayoutManager) view.getLayoutManager()).scrollToPositionWithOffset(0, 0);
        else if (start) view.scrollToPosition(0);
        else restore(anchor);
        view.postOnAnimation(() -> {
            if (token != generation || recycler != view || focusVersion != expectedFocusVersion
                    || !hadFocus || !view.isShown() || destination < 0) return;
            focus(destination);
        });
    }

    void restoreFocus() {
        if (focusedKey == null) return;
        int position = positionOf(focusedKey, focusedPosition);
        if (recycler != null && recycler.isShown() && position >= 0) focus(position);
    }

    private void focus(int position) {
        RecyclerView.ViewHolder holder = recycler.findViewHolderForAdapterPosition(position);
        if (holder != null) holder.itemView.requestFocus();
        else RecyclerFocus.request(recycler, position);
    }

    Parcelable capture(boolean useLastFocused) {
        if (recycler == null || !(recycler.getLayoutManager() instanceof LinearLayoutManager)) return null;
        LinearLayoutManager layout = (LinearLayoutManager) recycler.getLayoutManager();
        int position = layout.findFirstVisibleItemPosition();
        View focused = recycler.findFocus();
        RecyclerView.ViewHolder holder = focused == null ? null : recycler.findContainingViewHolder(focused);
        if (holder != null) position = holder.getBindingAdapterPosition();
        else if (useLastFocused && focusedKey != null) {
            int previous = positionOf(focusedKey, focusedPosition);
            if (previous >= 0 && layout.findViewByPosition(previous) != null) position = previous;
        }
        if (position < 0 || position >= displayed.size()) return layout.onSaveInstanceState();
        View child = layout.findViewByPosition(position);
        Bundle state = new Bundle();
        state.putString("video_anchor", displayed.get(position).key);
        state.putInt("position", position);
        state.putInt("offset", child == null ? 0 : layout.getDecoratedTop(child) - recycler.getPaddingTop());
        return state;
    }

    void restore(Parcelable value) {
        if (value == null || recycler == null || !(recycler.getLayoutManager() instanceof LinearLayoutManager)) return;
        LinearLayoutManager layout = (LinearLayoutManager) recycler.getLayoutManager();
        if (!(value instanceof Bundle) || !((Bundle) value).containsKey("video_anchor")) {
            layout.onRestoreInstanceState(value);
            return;
        }
        Bundle state = (Bundle) value;
        int position = positionOf(state.getString("video_anchor"), state.getInt("position"));
        if (position >= 0) layout.scrollToPositionWithOffset(position, state.getInt("offset"));
    }

    private int positionOf(String key, int fallback) {
        for (int i = 0; i < displayed.size(); i++) if (displayed.get(i).key.equals(key)) return i;
        return displayed.isEmpty() ? -1 : Math.max(0, Math.min(fallback, displayed.size() - 1));
    }

    private static DiffUtil.Callback diff(List<Entry> old, List<Entry> next) {
        return new DiffUtil.Callback() {
            public int getOldListSize() { return old.size(); }
            public int getNewListSize() { return next.size(); }
            public boolean areItemsTheSame(int a, int b) { return old.get(a).key.equals(next.get(b).key); }
            public Object getChangePayload(int a, int b) {
                return java.util.Objects.equals(old.get(a).cover, next.get(b).cover) ? Boolean.TRUE : null;
            }
            public boolean areContentsTheSame(int a, int b) { return old.get(a).content.equals(next.get(b).content); }
        };
    }

    private static List<Entry> snapshots(List values) {
        List<Entry> result = new ArrayList<>();
        Map<String, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            VideoModel video = (VideoModel) values.get(i);
            String identity = video.getEpid() > 0 ? "ep:" + video.getEpid()
                    : video.getBvid() != null && !video.getBvid().isEmpty() ? "bv:" + video.getBvid()
                    : video.getAid() > 0 ? "av:" + video.getAid() : "row:" + i;
            Integer occurrence = occurrences.get(identity);
            int number = occurrence == null ? 0 : occurrence;
            occurrences.put(identity, number + 1);
            OwnerModel owner = video.getOwner();
            VideoStatModel stat = video.getStat();
            BangumiModel bangumi = video.getBangumi();
            List<Object> content = Arrays.asList(video.getTitle(), video.getPic(), video.getDuration(),
                    video.getPubdate(), owner == null ? null : owner.getName(),
                    stat == null ? null : stat.getView(), stat == null ? null : stat.getDanmaku(),
                    bangumi == null ? null : bangumi.getLong_title(),
                    bangumi == null ? null : bangumi.getCover());
            result.add(new Entry(identity + "#" + number, content, video, bangumi == null ? video.getPic() : bangumi.getCover()));
        }
        return result;
    }

    private static final class Entry {
        final String key;
        final List<Object> content;
        final Object model;
        final String cover;
        Entry(String key, List<Object> content, Object model, String cover) {
            this.key = key; this.content = content; this.model = model; this.cover = cover;
        }
    }
}
