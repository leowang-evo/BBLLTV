package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.SeriesEpisodesAdapter;
import com.xx.blbl.ui.adapter.SharedClickListener;

public final class SeriesEpisodesLaneViewHolder extends ViewHolder {
    public static final /* synthetic */ int e = 0;
    public final AppCompatTextView f15035a;
    public final View f15036b;
    public final AppCompatTextView f15037c;
    public final SeriesEpisodesAdapter d;

    public SeriesEpisodesLaneViewHolder(View view) {
        super(view);
        this.f15035a = (AppCompatTextView) view.findViewById(R.id.top_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        View viewFindViewById = view.findViewById(R.id.button_order);
        this.f15036b = viewFindViewById;
        this.f15037c = (AppCompatTextView) view.findViewById(R.id.text_order);
        SeriesEpisodesAdapter bVar = new SeriesEpisodesAdapter();
        this.d = bVar;
        view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        recyclerView.setAdapter(bVar);
        viewFindViewById.setVisibility(0);
        viewFindViewById.setOnClickListener(new SharedClickListener(this, 17));
    }
}
