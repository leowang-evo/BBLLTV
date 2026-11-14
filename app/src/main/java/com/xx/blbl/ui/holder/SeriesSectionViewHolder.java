package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.SeriesHeaderAdapter;

public final class SeriesSectionViewHolder extends ViewHolder {
    public static final /* synthetic */ int f15060c = 0;
    public final AppCompatTextView f15061a;
    public final SeriesHeaderAdapter f15062b;

    public SeriesSectionViewHolder(View view) {
        super(view);
        this.f15061a = (AppCompatTextView) view.findViewById(R.id.top_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        SeriesHeaderAdapter cVar = new SeriesHeaderAdapter();
        this.f15062b = cVar;
        view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        recyclerView.setAdapter(cVar);
    }
}
