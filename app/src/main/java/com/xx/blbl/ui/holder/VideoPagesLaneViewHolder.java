package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.*;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;

public final class VideoPagesLaneViewHolder extends RecyclerView.ViewHolder {
    public static final int f603b = 0;
    public final ScreenContentAdapter f604a;

    public VideoPagesLaneViewHolder(View view) {
        super(view);
        f604a = new ScreenContentAdapter(4);
        RecyclerView list = view.findViewById(R.id.recyclerView);
        list.setLayoutManager(
                new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        list.setAdapter(f604a);
        ((AppCompatTextView) view.findViewById(R.id.top_title)).setText(R.string.video_page);
    }
}
