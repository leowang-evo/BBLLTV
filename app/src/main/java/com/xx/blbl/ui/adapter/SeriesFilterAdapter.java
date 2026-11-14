package com.xx.blbl.ui.adapter;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.series.AllSeriesFilterModel;
import com.xx.blbl.model.series.AllSeriesFilterOption;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.SeriesFilterViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

public final class SeriesFilterAdapter extends Adapter {
    public ItemClickCallback f11053b;
    public ItemFocusCallback f11054c;
    public final ArrayList f11052a = new ArrayList();
    public final CatalogClickRouter d = new CatalogClickRouter(this, 11);
    public final ContentClickRouter e = new ContentClickRouter(this, 13);

    public final void a(List data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = this.f11052a;
        int size = arrayList.size();
        arrayList.clear();
        notifyItemRangeRemoved(0, size);
        arrayList.addAll(data);
        notifyItemRangeChanged(0, data.size());
    }

    @Override
    public final int getItemCount() {
        return this.f11052a.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder e0Var, int i4) {
        SeriesFilterViewHolder holder = (SeriesFilterViewHolder) e0Var;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Object obj = this.f11052a.get(i4);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        AllSeriesFilterModel allSeriesFilterModel = (AllSeriesFilterModel) obj;
        int iconResourceId = allSeriesFilterModel.getIconResourceId();
        AppCompatImageView appCompatImageView = holder.f15024a;
        appCompatImageView.setImageResource(iconResourceId);
        List<AllSeriesFilterOption> options = allSeriesFilterModel.getOptions();
        AppCompatTextView appCompatTextView = holder.f15025b;
        if (options != null && allSeriesFilterModel.getCurrentSelect() > 0) {
            int currentSelect = allSeriesFilterModel.getCurrentSelect();
            List<AllSeriesFilterOption> options2 = allSeriesFilterModel.getOptions();
            Intrinsics.checkNotNull(options2);
            if (currentSelect < options2.size()) {
                List<AllSeriesFilterOption> options3 = allSeriesFilterModel.getOptions();
                Intrinsics.checkNotNull(options3);
                appCompatTextView.setText(
                        options3.get(allSeriesFilterModel.getCurrentSelect()).getTitle());
                TypedValue typedValue = holder.f15026c;
                appCompatImageView.setColorFilter(typedValue.data);
                appCompatTextView.setTextColor(typedValue.data);
                return;
            }
        }
        if (allSeriesFilterModel.getOptions() == null
                || allSeriesFilterModel.getCurrentSelect() < 0) {
            appCompatTextView.setText(allSeriesFilterModel.getTitle());
        } else {
            List<AllSeriesFilterOption> options4 = allSeriesFilterModel.getOptions();
            Intrinsics.checkNotNull(options4);
            if (options4.isEmpty()) {
                appCompatTextView.setText(allSeriesFilterModel.getTitle());
            } else {
                List<AllSeriesFilterOption> options5 = allSeriesFilterModel.getOptions();
                Intrinsics.checkNotNull(options5);
                appCompatTextView.setText(options5.get(0).getTitle());
            }
        }
        TypedValue typedValue2 = holder.d;
        appCompatTextView.setTextColor(typedValue2.data);
        appCompatImageView.setColorFilter(typedValue2.data);
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE =
                UiSupport.inflate(
                        viewGroup, "parent", R.layout.cell_series_filter, viewGroup, false);
        int i7 = SeriesFilterViewHolder.e;
        Intrinsics.checkNotNull(viewE);
        CatalogClickRouter onItemClickListener = this.d;
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        ContentClickRouter onItemFocusListener = this.e;
        Intrinsics.checkNotNullParameter(onItemFocusListener, "onItemFocusListener");
        return new SeriesFilterViewHolder(viewE, onItemClickListener, onItemFocusListener);
    }
}
