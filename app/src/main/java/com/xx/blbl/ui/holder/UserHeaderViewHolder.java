package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.ui.adapter.ItemFocusCallback;

import kotlin.Lazy;
import kotlin.LazyKt;

/** User-space header row and follow action on public UI/network APIs. */
public final class UserHeaderViewHolder extends RecyclerView.ViewHolder {
    public static final int f353x = 0;
    public final View f354a;
    public final Lazy<NetworkManager> f355b = LazyKt.lazy(AppController::network);
    public final AppCompatImageView f356c, d;
    public final AppCompatTextView e, f357f;
    public final AppCompatButton f358p;
    public int f359v = -1;
    public String f360w = "";

    public UserHeaderViewHolder(View view, ItemFocusCallback focus) {
        super(view);
        f354a = view;
        f356c = view.findViewById(R.id.image_top);
        d = view.findViewById(R.id.image_avatar);
        e = view.findViewById(R.id.text_title);
        f357f = view.findViewById(R.id.text_subtitle);
        f358p = view.findViewById(R.id.button_follow);
        f358p.setTag("follow");
        f358p.setOnClickListener(
                button -> {
                    if (f359v != -1 && f360w != null && !f360w.isEmpty())
                        f355b.getValue()
                                .userRelationModify(
                                        f360w,
                                        f359v != 1 ? 1 : 2,
                                        new UserHeaderCallback<>(this, 1));
                });
        if (focus != null)
            f358p.setOnFocusChangeListener(
                    (button, hasFocus) ->
                            focus.onFocusChange(button, getBindingAdapterPosition(), hasFocus));
    }

    public static void showRelationship(UserHeaderViewHolder owner, Integer relation) {
        if (relation != null && (relation == 2 || relation == 6)) {
            owner.f358p.setText(relation == 6 ? R.string.follow_as_friend : R.string.followed);
            owner.f358p.setBackgroundResource(R.drawable.button_common_2);
            owner.f359v = 1;
        } else {
            owner.f358p.setText(R.string.follow);
            owner.f358p.setBackgroundResource(R.drawable.button_common);
            owner.f359v = 0;
        }
    }
}
