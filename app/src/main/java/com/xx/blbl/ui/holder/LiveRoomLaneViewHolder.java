package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

import java.util.List;

public final class LiveRoomLaneViewHolder extends ViewHolder {
    public static final /* synthetic */ int d = 0;
    public final AppCompatTextView f14520a;
    public final RecyclerView f14521b;
    public final CatalogAdapter f14522c;

    public LiveRoomLaneViewHolder(View view) {
        super(view);
        this.f14520a = (AppCompatTextView) view.findViewById(R.id.top_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.f14521b = recyclerView;
        CatalogAdapter aVar = new CatalogAdapter(2);
        aVar.setHasLoadMoreRow(false);
        this.f14522c = aVar;
        view.getContext();
        recyclerView.setLayoutManager(new WrapContentGirdLayoutManager(view.getContext(), 4));
        recyclerView.setAdapter(aVar);
    }

    public final void bind(String str, List list) {
        RecyclerView recyclerView = this.f14521b;
        AppCompatTextView appCompatTextView = this.f14520a;
        if (list == null || list.isEmpty()) {
            appCompatTextView.setVisibility(8);
            recyclerView.setVisibility(8);
        } else {
            appCompatTextView.setVisibility(0);
            recyclerView.setVisibility(0);
            appCompatTextView.setText(str);
            this.f14522c.replaceItems(list);
        }
    }
}
