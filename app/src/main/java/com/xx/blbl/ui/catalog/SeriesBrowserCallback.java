package com.xx.blbl.ui.catalog;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;
import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.series.AllSeriesFilterModel;
import com.xx.blbl.model.series.AllSeriesFilterOption;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.GetAllSeriesWrapper;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.SeriesFilterAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SeriesBrowserCallback implements NetResultCallback, ItemClickCallback {
    public final /* synthetic */ SeriesBrowserFragment f9490a;
    private final int page, generation;

    public /* synthetic */ SeriesBrowserCallback(SeriesBrowserFragment cVar) {
        this(cVar, cVar.f9503U0, cVar.currentResultGeneration());
    }

    SeriesBrowserCallback(SeriesBrowserFragment cVar, int page, int generation) {
        this.f9490a = cVar;
        this.page = page;
        this.generation = generation;
    }

    @Override
    public void onClick(View view, int i4) {
        Context contextJ;
        SeriesBrowserFragment cVar = this.f9490a;
        if (!cVar.f9502S0) {
            cVar.l0(true);
        }
        SeriesFilterAdapter aVar = cVar.f9497L0;
        AllSeriesFilterModel allSeriesFilterModel = null;
        if (aVar == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "adapterFilter");
            throw null;
        }
        if (i4 >= 0) {
            ArrayList arrayList = aVar.f11052a;
            if (i4 < arrayList.size()) {
                allSeriesFilterModel = (AllSeriesFilterModel) arrayList.get(i4);
            }
        }
        if (allSeriesFilterModel == null
                || allSeriesFilterModel.getOptions() == null
                || (contextJ = cVar.contextOrNull()) == null) {
            return;
        }
        ArrayList<CharSequence> arrayList2 = new ArrayList<>();
        List<AllSeriesFilterOption> options = allSeriesFilterModel.getOptions();
        java.util.Objects.requireNonNull(options);
        Iterator<AllSeriesFilterOption> it = options.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().getTitle());
        }
        final AllSeriesFilterModel selectedFilter = allSeriesFilterModel;
        new AlertDialog.Builder(contextJ)
                .setTitle(selectedFilter.getTitle())
                .setSingleChoiceItems(
                        arrayList2.toArray(new CharSequence[0]),
                        selectedFilter.getCurrentSelect(),
                        (dialog, selected) -> {
                            selectedFilter.setCurrentSelect(selected);
                            cVar.f9497L0.notifyItemChanged(i4);
                            dialog.dismiss();
                            cVar.f9503U0 = 1;
                            cVar.k0();
                        })
                .show();
    }

    @Override
    public void onFailure(Throwable th) {
        SeriesBrowserFragment cVar = this.f9490a;
        if (!cVar.isCurrentResult(generation)) return;
        cVar.f9507Y0 = false;
        if (page == 1) {
            String strValueOf = String.valueOf(th != null ? th.getMessage() : null);
            if (cVar.isAttachedToHost()) {
                View view = cVar.f9498N0;
                if (view == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "viewInfo");
                    throw null;
                }
                view.setVisibility(0);
                AppCompatImageView appCompatImageView = cVar.O0;
                if (appCompatImageView == null) {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "imageInfo");
                    throw null;
                }
                appCompatImageView.setImageResource(R.mipmap.net_error);
                AppCompatTextView appCompatTextView = cVar.f9499P0;
                if (appCompatTextView != null) {
                    appCompatTextView.setText(strValueOf);
                } else {
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                            "textInfo");
                    throw null;
                }
            }
        }
    }

    @Override
    public void onResponse(Object obj) {
        GetAllSeriesWrapper getAllSeriesWrapper;
        List<LaneItemModel> list;
        BaseResponse baseResponse = (BaseResponse) obj;
        SeriesBrowserFragment cVar = this.f9490a;
        if (!cVar.isCurrentResult(generation)) return;
        View view = cVar.f9498N0;
        boolean hasData = false;
        if (view == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewInfo");
            throw null;
        }
        view.setVisibility(8);
        cVar.f9507Y0 = false;
        if (baseResponse != null
                && (getAllSeriesWrapper = (GetAllSeriesWrapper) baseResponse.getData()) != null
                && (list = getAllSeriesWrapper.getList()) != null) {
            if (!list.isEmpty()) {
                if (page == 1) {
                    CatalogAdapter aVar = cVar.f9495I0;
                    if (aVar == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "adapter");
                        throw null;
                    }
                    cVar.replaceFirstResults(list);
                    CatalogAdapter aVar2 = cVar.f9495I0;
                    if (aVar2 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "adapter");
                        throw null;
                    }
                    aVar2.setHasLoadMoreRow(true);
                    cVar.f9504V0 = true;
                    RecyclerView recyclerView = cVar.f9493G0;
                    if (recyclerView == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "recyclerView");
                        throw null;
                    }
                } else {
                    CatalogAdapter aVar3 = cVar.f9495I0;
                    if (aVar3 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "adapter");
                        throw null;
                    }
                    aVar3.appendItems(list);
                }
                if (list.size() < 24) {
                    CatalogAdapter aVar4 = cVar.f9495I0;
                    if (aVar4 == null) {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "adapter");
                        throw null;
                    }
                    aVar4.setHasLoadMoreRow(false);
                    cVar.f9504V0 = false;
                }
            } else if (page == 1) {
                cVar.replaceFirstResults(java.util.Collections.emptyList());
                SeriesBrowserFragment.j0(cVar);
            }
            hasData = true;
        }
        if (!hasData && page == 1) {
            SeriesBrowserFragment.j0(cVar);
        }
    }
}
