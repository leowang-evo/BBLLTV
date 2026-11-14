package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.ui.holder.UserCardViewHolder;

/** Application focus routing, independent of the original fused SDK listener classes. */
public final class ItemFocusListener implements View.OnFocusChangeListener {
    public final int f347a;
    public final Object f348b, f349c;

    public ItemFocusListener(int mode, Object callback, Object owner) {
        f347a = mode;
        f348b = callback;
        f349c = owner;
    }

    public void onFocusChange(View view, boolean focused) {
        if (f347a == 1) {
            TextView text = (TextView) f349c;
            android.view.ViewPropertyAnimator animation = text.animate();
            if (focused && f348b instanceof OvershootInterpolator)
                animation.setInterpolator((OvershootInterpolator) f348b);
            animation
                    .scaleX(focused ? 1.1f : 1f)
                    .scaleY(focused ? 1.1f : 1f)
                    .setDuration(600)
                    .start();
            return;
        }
        if ((f347a == 2 || f347a == 3 || f347a == 4) && !focused) return;
        ((ItemFocusCallback) f348b)
                .onFocusChange(
                        view,
                        ((RecyclerView.ViewHolder) f349c).getBindingAdapterPosition(),
                        focused);
        if (f347a == 6) ((UserCardViewHolder) f349c).f14885c.setSelected(focused);
    }
}
