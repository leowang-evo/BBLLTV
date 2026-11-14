package com.xx.blbl.ui.holder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.LaneItemAdapter;
import com.xx.blbl.ui.catalog.SeriesBrowserFragment;
import com.xx.blbl.ui.navigation.ScreenNavigator;

public final class SeriesLaneViewHolder extends ViewHolder implements View.OnClickListener {
    public static final /* synthetic */ int f14401f = 0;
    public final View f14402a;
    public final AppCompatTextView f14403b;
    public final View f14404c;
    public final LaneItemAdapter d;
    public int e;

    public SeriesLaneViewHolder(View view) {
        super(view);
        this.f14402a = view;
        AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.top_title);
        this.f14403b = appCompatTextView;
        View viewFindViewById = view.findViewById(R.id.button_more);
        this.f14404c = viewFindViewById;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LaneItemAdapter fVar = new LaneItemAdapter(0);
        this.d = fVar;
        this.e = -1;
        recyclerView.getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 6));
        recyclerView.setAdapter(fVar);
        appCompatTextView.setOnClickListener(this);
        viewFindViewById.setOnClickListener(this);
    }

    @Override
    public final void onClick(View view) {
        if (this.e == -1) {
            return;
        }
        Object obj = ScreenNavigator.f738a;
        Context context = this.f14402a.getContext();
        int i4 = this.e;
        if (context instanceof MainActivity) {
            SeriesBrowserFragment cVar = new SeriesBrowserFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("seasonType", i4);
            cVar.setArguments(bundle);
            ((MainActivity) context).showScreen(cVar, "allSeries");
        }
    }
}
