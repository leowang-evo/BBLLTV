package com.xx.blbl.platform;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/** Only the latest request may focus a row after an asynchronous layout or attachment. */
public final class RecyclerFocus {
    private static final WeakHashMap<RecyclerView, WeakReference<Request>> pending =
            new WeakHashMap<>();

    private RecyclerFocus() {}

    public static void request(RecyclerView recycler, int position) {
        cancel(recycler);
        RecyclerView.Adapter<?> adapter = recycler.getAdapter();
        if (adapter == null || position < 0 || position >= adapter.getItemCount()) return;
        Request request = new Request(recycler, position);
        pending.put(recycler, new WeakReference<>(request));
        recycler.addOnLayoutChangeListener(request);
        recycler.addOnAttachStateChangeListener(request);
        recycler.scrollToPosition(position);
        recycler.post(request);
    }

    public static void cancel(RecyclerView recycler) {
        WeakReference<Request> previous = pending.remove(recycler);
        if (previous != null && previous.get() != null) previous.get().cancel();
    }

    private static final class Request
            implements Runnable, View.OnLayoutChangeListener, View.OnAttachStateChangeListener {
        private final RecyclerView recycler;
        private final int position;
        private boolean canceled;

        Request(RecyclerView recycler, int position) {
            this.recycler = recycler;
            this.position = position;
        }

        @Override
        public void run() {
            if (canceled || !recycler.isAttachedToWindow() || !recycler.isShown()) return;
            RecyclerView.ViewHolder holder = recycler.findViewHolderForAdapterPosition(position);
            if (holder == null) return;
            View target = holder.itemView.findViewById(R.id.click_view);
            if ((target == null ? holder.itemView : target).requestFocus()) cancel();
        }

        void cancel() {
            canceled = true;
            recycler.removeCallbacks(this);
            recycler.removeOnLayoutChangeListener(this);
            recycler.removeOnAttachStateChangeListener(this);
            WeakReference<Request> current = pending.get(recycler);
            if (current != null && current.get() == this) pending.remove(recycler);
        }

        @Override
        public void onLayoutChange(
                View view, int l, int t, int r, int b, int ol, int ot, int or, int ob) {
            run();
        }

        @Override
        public void onViewAttachedToWindow(View view) {
            recycler.post(this);
        }

        @Override
        public void onViewDetachedFromWindow(View view) {
            cancel();
        }
    }
}
