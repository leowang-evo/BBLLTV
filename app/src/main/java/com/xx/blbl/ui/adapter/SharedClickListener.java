package com.xx.blbl.ui.adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.xx.blbl.R;
import com.xx.blbl.model.video.TagModel;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.holder.SeriesEpisodesLaneViewHolder;
import com.xx.blbl.ui.holder.UserCardViewHolder;

import java.util.Collections;

/** Only application click branches; Material/AndroidX internals stay in their libraries. */
public final class SharedClickListener implements View.OnClickListener {
    public final int f345a;
    public final Object f346b;

    public SharedClickListener(Object owner, int mode) {
        if (mode != 1 && mode != 16 && mode != 17)
            throw new IllegalArgumentException("Unsupported application click branch: " + mode);
        f345a = mode;
        f346b = owner;
    }

    public void onClick(View view) {
        if (f345a == 16) {
            UserCardViewHolder holder = (UserCardViewHolder) f346b;
            holder.f14883a.onClick(view, holder.getBindingAdapterPosition());
            return;
        }
        if (f345a == 17) {
            SeriesEpisodesLaneViewHolder holder = (SeriesEpisodesLaneViewHolder) f346b;
            holder.d.f11056b = !holder.d.f11056b;
            Collections.reverse(holder.d.f11055a);
            holder.d.notifyDataSetChanged();
            holder.f15037c.setText(
                    holder.d.f11056b ? R.string.positive_sequence : R.string.negative_sequence);
            return;
        }
        TagModel tag = (TagModel) f346b;
        Context context = view.getContext();
        while (context instanceof ContextWrapper && !(context instanceof MainActivity))
            context = ((ContextWrapper) context).getBaseContext();
        if (!(context instanceof MainActivity)) return;
        MainActivity activity = (MainActivity) context;
        boolean channel = "new_channel".equals(tag.getTag_type());
        Bundle args = new Bundle();
        if (channel) {
            args.putString("title", tag.getTag_name());
            args.putLong("tagId", tag.getTag_id());
        } else args.putString("keyword", tag.getTag_name());
        Fragment fragment =
                activity.getSupportFragmentManager()
                        .getFragmentFactory()
                        .instantiate(
                                activity.getClassLoader(),
                                channel
                                        ? "com.xx.blbl.ui.catalog.ChannelVideosFragment"
                                        : "com.xx.blbl.ui.search.ClassicSearchFragment");
        fragment.setArguments(args);
        activity.showScreen(fragment, channel ? "channelDetail" : "search");
    }
}
