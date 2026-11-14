package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.LaneItemAdapter;

public final class SeriesExtrasLaneViewHolder extends ViewHolder {
    public static final /* synthetic */ int f15032c = 0;
    public final AppCompatTextView f15033a;
    public final LaneItemAdapter f15034b;

    public SeriesExtrasLaneViewHolder(View view) {
        super(view);
        this.f15033a = (AppCompatTextView) view.findViewById(R.id.top_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LaneItemAdapter fVar = new LaneItemAdapter(3);
        this.f15034b = fVar;
        view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        recyclerView.setAdapter(fVar);
    }
}
