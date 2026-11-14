package com.xx.blbl.platform;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;

/** Typed application item listeners, independent of R8's mixed SDK classes. */
public final class ItemEvents {
    private ItemEvents() {}

    public static View.OnClickListener click(
            ItemClickCallback listener, RecyclerView.ViewHolder holder) {
        return view -> listener.onClick(view, holder.getBindingAdapterPosition());
    }

    public static View.OnFocusChangeListener focus(
            ItemFocusCallback listener, RecyclerView.ViewHolder holder, boolean onlyGained) {
        return (view, focused) -> {
            if (!onlyGained || focused)
                listener.onFocusChange(view, holder.getBindingAdapterPosition(), focused);
        };
    }

    public static View.OnFocusChangeListener selected(View text) {
        return (view, focused) -> text.setSelected(focused);
    }
}
