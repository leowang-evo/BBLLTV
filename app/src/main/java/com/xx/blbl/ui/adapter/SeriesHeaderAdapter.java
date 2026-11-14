package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.SeriesCardViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

public final class SeriesHeaderAdapter extends Adapter {
    public long f11059b;
    public final ArrayList f11058a = new ArrayList();
    public final ContentClickRouter f11060c = new ContentClickRouter(this, 17);

    @Override
    public final int getItemCount() {
        return this.f11058a.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder e0Var, int i4) {
        SeriesCardViewHolder holder = (SeriesCardViewHolder) e0Var;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Object obj = this.f11058a.get(i4);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        holder.bindSeries((SeriesModel) obj);
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE = UiSupport.inflate(viewGroup, "parent", R.layout.cell_series, viewGroup, false);
        int i7 = SeriesCardViewHolder.e;
        Intrinsics.checkNotNull(viewE);
        ContentClickRouter onItemClick = this.f11060c;
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        return new SeriesCardViewHolder(viewE, onItemClick, null);
    }
}
