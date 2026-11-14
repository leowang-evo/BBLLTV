package com.xx.blbl.ui.view.exoplayer;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;
import com.xx.blbl.model.common.PlayerSettingModel;

import java.util.ArrayList;
import java.util.List;

/** Application-owned settings rows recovered from e5/g and x5/a,b. */
final class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.Holder> {
    MyPlayerSettingView.SettingClickListener onItemClick;
    private final List<PlayerSettingModel> items = new ArrayList<>();
    private RecyclerView recycler;

    @Override
    public void onAttachedToRecyclerView(RecyclerView view) {
        super.onAttachedToRecyclerView(view);
        recycler = view;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView view) {
        if (recycler == view) recycler = null;
        super.onDetachedFromRecyclerView(view);
    }

    void setItems(List<PlayerSettingModel> values) {
        items.clear();
        items.addAll(values);
        notifyDataSetChanged();
    }

    void updateValue(int position, String value) {
        if (position >= 0 && position < items.size()) {
            items.get(position).setShowValue(value);
            notifyItemChanged(position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int type) {
        return new Holder(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                type == 1
                                        ? R.layout.cell_player_setting_head
                                        : R.layout.cell_player_setting,
                                parent,
                                false),
                type == 1);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PlayerSettingModel item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.value.setText(item.getShowValue());
        holder.value.setVisibility(
                TextUtils.isEmpty(item.getShowValue()) ? View.GONE : View.VISIBLE);
        if (holder.header) return;
        holder.icon.setVisibility(item.getIcon() == 0 ? View.GONE : View.VISIBLE);
        if (item.getIcon() != 0) holder.icon.setImageResource(item.getIcon());
        holder.arrow.setVisibility(item.getType() == 0 ? View.VISIBLE : View.GONE);
        holder.toggle.setVisibility(item.getType() == 3 ? View.VISIBLE : View.GONE);
        if (item.getType() == 3) holder.toggle.setImageResource(
                "开".equals(item.getShowValue()) ? R.drawable.tv_switch_on : R.drawable.tv_switch_off);
        holder.check.setVisibility(
                item.getType() == 2 && item.getCheck() ? View.VISIBLE : View.GONE);
    }

    final class Holder extends RecyclerView.ViewHolder {
        final boolean header;
        final TextView title, value;
        final ImageView icon, arrow, check, toggle;

        Holder(View view, boolean header) {
            super(view);
            this.header = header;
            title = view.findViewById(header ? R.id.textView : R.id.text_view_primary);
            value = view.findViewById(header ? R.id.subTextView : R.id.text_view_sub);
            icon = header ? null : view.findViewById(R.id.imageView);
            arrow = header ? null : view.findViewById(R.id.icon_more);
            check = header ? null : view.findViewById(R.id.icon_check);
            toggle = header ? null : view.findViewById(R.id.tv_setting_toggle);
            if (!header)
                view.findViewById(R.id.click_view)
                        .setOnClickListener(
                                clicked -> {
                                    int position = getBindingAdapterPosition();
                                    if (position != RecyclerView.NO_POSITION
                                            && SettingAdapter.this.onItemClick != null)
                                        SettingAdapter.this.onItemClick.onClick(clicked, position);
                                });
        }
    }
}
