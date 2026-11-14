package com.xx.blbl.ui.catalog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.series.AllSeriesFilterModel;
import com.xx.blbl.model.series.AllSeriesFilterOption;
import com.xx.blbl.model.series.SeriesTypeEnum;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.SeriesFilterAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenResult;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.List;

public final class SeriesBrowserFragment extends BaseScreenFragment
        implements MainActivity.BackHandler {
    public RecyclerView f9493G0;
    public WrapContentGirdLayoutManager f9494H0;
    public CatalogAdapter f9495I0;
    public RecyclerView J0;
    public FrameLayout f9496K0;
    public SeriesFilterAdapter f9497L0;
    public AppCompatTextView M0;
    public View f9498N0;
    public AppCompatImageView O0;
    public AppCompatTextView f9499P0;
    public boolean f9502S0;
    public int f9505W0;
    public boolean f9507Y0;
    public int f9500Q0 = 1;
    public int f9501R0 = -215;
    public final Lazy<NetworkManager> T0 = LazyKt.lazy(AppController::network);
    public int f9503U0 = 1;
    public boolean f9504V0 = true;
    public final int f9506X0 = 12;
    private int resultGeneration;
    private boolean initialResultsPending;
    private View entryFocusAnchor;
    private int returningPosition = -1;

    public static final void j0(SeriesBrowserFragment cVar) {
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
            appCompatImageView.setImageResource(R.mipmap.empty);
            AppCompatTextView appCompatTextView = cVar.f9499P0;
            if (appCompatTextView != null) {
                appCompatTextView.setText(cVar.stringResource(R.string.empty_data));
            } else {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textInfo");
                throw null;
            }
        }
    }

    @Override
    public final void screenHiddenChanged(boolean z7) {
        if (z7) {
            if (f9493G0 != null && f9495I0 != null && f9495I0.lastFocusedItem != null) {
                RecyclerView.ViewHolder holder =
                        f9493G0.findContainingViewHolder(f9495I0.lastFocusedItem);
                returningPosition = holder == null ? -1 : holder.getBindingAdapterPosition();
            }
            return;
        }
        if (f9495I0 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
            throw null;
        }
        final RecyclerView list = f9493G0;
        final int position = returningPosition;
        // MainActivity restores the page's generic focus during pop. Restore the
        // poster afterwards so that generic request cannot replace it with the header.
        if (list != null) list.post(() -> {
            if (!isVisible() || f9493G0 != list || f9495I0 == null) return;
            RecyclerView.ViewHolder holder = list.findViewHolderForAdapterPosition(position);
            if (holder != null) holder.itemView.requestFocusFromTouch();
            else if (position >= 0)
                com.xx.blbl.platform.RecyclerFocus.request(list, position);
        });
    }

    @Override
    public final int layoutResource() {
        return R.layout.fragment_all_series;
    }

    @Override
    public final void bindScreen(View view) {
        initialResultsPending = true;
        returningPosition = -1;
        f9503U0 = 1;
        f9504V0 = true;
        f9507Y0 = true;
        SeriesBrowserFragment cVar;
        FrameLayout frameLayout;
        SeriesFilterAdapter aVar;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        View viewFindViewById = view.findViewById(R.id.button_back_1);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById, "findViewById(...)");
        ((AppCompatImageView) viewFindViewById)
                .setOnClickListener(
                        cell -> {
                            if (hostActivity() != null) hostActivity().onBackPressed();
                        });
        entryFocusAnchor = viewFindViewById;
        viewFindViewById.post(() -> {
            if (initialResultsPending && isVisible() && entryFocusAnchor != null)
                entryFocusAnchor.requestFocusFromTouch();
            if (J0 != null) J0.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        });
        View viewFindViewById2 = view.findViewById(R.id.view_info);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById2, "findViewById(...)");
        this.f9498N0 = viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.image_info);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById3, "findViewById(...)");
        this.O0 = (AppCompatImageView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.text_info);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById4, "findViewById(...)");
        this.f9499P0 = (AppCompatTextView) viewFindViewById4;
        View viewFindViewById5 = view.findViewById(R.id.text_top_title);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById5, "findViewById(...)");
        this.M0 = (AppCompatTextView) viewFindViewById5;
        View viewFindViewById6 = view.findViewById(R.id.viewFilter);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById6, "findViewById(...)");
        this.f9496K0 = (FrameLayout) viewFindViewById6;
        View viewFindViewById7 = view.findViewById(R.id.recyclerView);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById7, "findViewById(...)");
        this.f9493G0 = (RecyclerView) viewFindViewById7;
        // Until the first response, keep the old page's focus rectangle from
        // choosing a lower poster when the new grid receives its first layout.
        f9493G0.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        f9493G0.setFocusable(false);
        f9493G0.setItemAnimator(null);
        view.getContext();
        WrapContentGirdLayoutManager wrapContentGirdLayoutManager =
                new WrapContentGirdLayoutManager(requireContext(), 6);
        this.f9494H0 = wrapContentGirdLayoutManager;
        RecyclerView recyclerView3 = this.f9493G0;
        if (recyclerView3 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerView");
            throw null;
        }
        recyclerView3.setLayoutManager(wrapContentGirdLayoutManager);
        CatalogAdapter aVar2 = new CatalogAdapter(5);
        aVar2.setHasLoadMoreRow(false);
        this.f9495I0 = aVar2;
        RecyclerView recyclerView4 = this.f9493G0;
        if (recyclerView4 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerView");
            throw null;
        }
        recyclerView4.setAdapter(aVar2);
        View viewFindViewById8 = view.findViewById(R.id.recyclerViewFilter);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById8, "findViewById(...)");
        RecyclerView recyclerView5 = (RecyclerView) viewFindViewById8;
        this.J0 = recyclerView5;
        recyclerView5.setFocusable(false);
        recyclerView5.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        view.getContext();
        recyclerView5.setLayoutManager(new LinearLayoutManager(requireContext()));
        SeriesFilterAdapter aVar3 = new SeriesFilterAdapter();
        this.f9497L0 = aVar3;
        RecyclerView recyclerView6 = this.J0;
        if (recyclerView6 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerViewFilter");
            throw null;
        }
        recyclerView6.setAdapter(aVar3);
        Context context = view.getContext();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        SeriesFilterFactory cVar2 = new SeriesFilterFactory(context);
        int i4 = this.f9500Q0;
        if (i4 == SeriesTypeEnum.Anime.getType()) {
            AllSeriesFilterModel allSeriesFilterModelG = cVar2.g(this.f9500Q0);
            AllSeriesFilterModel allSeriesFilterModelM = cVar2.m();
            AllSeriesFilterModel allSeriesFilterModel = new AllSeriesFilterModel();
            String string = context.getString(R.string.filter_spoken);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            allSeriesFilterModel.setTitle(string);
            allSeriesFilterModel.setIconResourceId(R.drawable.ic_voice);
            allSeriesFilterModel.setKey("spoken_language_type");
            AllSeriesFilterOption allSeriesFilterOption = new AllSeriesFilterOption();
            allSeriesFilterOption.setTitle("全部配音");
            allSeriesFilterOption.setValue("-1");
            allSeriesFilterModel.setOptions(
                    java.util.Arrays.asList(
                            allSeriesFilterOption,
                            SeriesFilterFactory.option("原声", "1"),
                            SeriesFilterFactory.option("中文配音", "2")));
            AllSeriesFilterModel allSeriesFilterModelF = cVar2.f(this.f9500Q0);
            AllSeriesFilterModel allSeriesFilterModelK = cVar2.k();
            AllSeriesFilterModel allSeriesFilterModelI = cVar2.i();
            AllSeriesFilterModel allSeriesFilterModel2 = new AllSeriesFilterModel();
            String string2 = context.getString(R.string.filter_season);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            allSeriesFilterModel2.setTitle(string2);
            allSeriesFilterModel2.setIconResourceId(R.drawable.ic_flower);
            allSeriesFilterModel2.setKey("season_month");
            AllSeriesFilterOption allSeriesFilterOption2 = new AllSeriesFilterOption();
            allSeriesFilterOption2.setTitle("全部季度");
            allSeriesFilterOption2.setValue("-1");
            allSeriesFilterModel2.setOptions(
                    java.util.Arrays.asList(
                            allSeriesFilterOption2,
                            SeriesFilterFactory.option("一月", "1"),
                            SeriesFilterFactory.option("四月", "4"),
                            SeriesFilterFactory.option("七月", "7"),
                            SeriesFilterFactory.option("十月", "10")));
            List listD =
                    java.util.Arrays.asList(
                            allSeriesFilterModelG,
                            allSeriesFilterModelM,
                            allSeriesFilterModel,
                            allSeriesFilterModelF,
                            allSeriesFilterModelK,
                            allSeriesFilterModelI,
                            allSeriesFilterModel2,
                            cVar2.n(),
                            cVar2.l(this.f9500Q0));
            SeriesFilterAdapter aVar4 = this.f9497L0;
            if (aVar4 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "adapterFilter");
                throw null;
            }
            aVar4.a(listD);
            AppCompatTextView appCompatTextView = this.M0;
            if (appCompatTextView == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textTitle");
                throw null;
            }
            appCompatTextView.setText(SeriesTypeEnum.Anime.getShowName());
        } else if (i4 == SeriesTypeEnum.ChinaAnime.getType()) {
            List listD2 =
                    java.util.Arrays.asList(
                            cVar2.g(this.f9500Q0),
                            cVar2.m(),
                            cVar2.k(),
                            cVar2.i(),
                            cVar2.n(),
                            cVar2.l(this.f9500Q0));
            SeriesFilterAdapter aVar5 = this.f9497L0;
            if (aVar5 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "adapterFilter");
                throw null;
            }
            aVar5.a(listD2);
            AppCompatTextView appCompatTextView2 = this.M0;
            if (appCompatTextView2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textTitle");
                throw null;
            }
            appCompatTextView2.setText(SeriesTypeEnum.ChinaAnime.getShowName());
        } else if (i4 == SeriesTypeEnum.Movie.getType()) {
            List listD6 =
                    java.util.Arrays.asList(
                            cVar2.g(this.f9500Q0),
                            cVar2.f(this.f9500Q0),
                            cVar2.l(this.f9500Q0),
                            cVar2.j(),
                            cVar2.i());
            SeriesFilterAdapter aVar9 = this.f9497L0;
            if (aVar9 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "adapterFilter");
                throw null;
            }
            aVar9.a(listD6);
            AppCompatTextView appCompatTextView6 = this.M0;
            if (appCompatTextView6 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textTitle");
                throw null;
            }
            appCompatTextView6.setText(SeriesTypeEnum.Movie.getShowName());
        } else if (i4 == SeriesTypeEnum.Drama.getType()) {
            List listD3 =
                    java.util.Arrays.asList(
                            cVar2.g(this.f9500Q0),
                            cVar2.f(this.f9500Q0),
                            cVar2.l(this.f9500Q0),
                            cVar2.j(),
                            cVar2.i());
            SeriesFilterAdapter aVar6 = this.f9497L0;
            if (aVar6 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "adapterFilter");
                throw null;
            }
            aVar6.a(listD3);
            AppCompatTextView appCompatTextView3 = this.M0;
            if (appCompatTextView3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textTitle");
                throw null;
            }
            appCompatTextView3.setText(SeriesTypeEnum.Drama.getShowName());
        } else if (i4 == SeriesTypeEnum.Documentary.getType()) {
            AllSeriesFilterModel allSeriesFilterModelG2 = cVar2.g(this.f9500Q0);
            AllSeriesFilterModel allSeriesFilterModelL = cVar2.l(this.f9500Q0);
            AllSeriesFilterModel allSeriesFilterModel3 = new AllSeriesFilterModel();
            String string3 = context.getString(R.string.filter_publish);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            allSeriesFilterModel3.setTitle(string3);
            allSeriesFilterModel3.setIconResourceId(R.drawable.ic_publish);
            allSeriesFilterModel3.setKey("producer_id");
            AllSeriesFilterOption allSeriesFilterOption3 = new AllSeriesFilterOption();
            allSeriesFilterOption3.setTitle("全部出品");
            allSeriesFilterOption3.setValue("-1");
            allSeriesFilterModel3.setOptions(
                    java.util.Arrays.asList(
                            allSeriesFilterOption3,
                            SeriesFilterFactory.option("央视", "4"),
                            SeriesFilterFactory.option("BBC", "1"),
                            SeriesFilterFactory.option("探索频道", "7"),
                            SeriesFilterFactory.option("国家地理", "14"),
                            SeriesFilterFactory.option("NHK", "2"),
                            SeriesFilterFactory.option("历史频道", "6"),
                            SeriesFilterFactory.option("卫视", "8"),
                            SeriesFilterFactory.option("自制", "9"),
                            SeriesFilterFactory.option("ITV", "5"),
                            SeriesFilterFactory.option("SKY", "3"),
                            SeriesFilterFactory.option("ZDF", "10"),
                            SeriesFilterFactory.option("合作机构", "11"),
                            SeriesFilterFactory.option("国内其他", "12"),
                            SeriesFilterFactory.option("国外其他", "13")));
            List listD4 =
                    java.util.Arrays.asList(
                            allSeriesFilterModelG2,
                            allSeriesFilterModelL,
                            allSeriesFilterModel3,
                            cVar2.j(),
                            cVar2.i());
            SeriesFilterAdapter aVar7 = this.f9497L0;
            if (aVar7 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "adapterFilter");
                throw null;
            }
            aVar7.a(listD4);
            AppCompatTextView appCompatTextView4 = this.M0;
            if (appCompatTextView4 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textTitle");
                throw null;
            }
            appCompatTextView4.setText(SeriesTypeEnum.Documentary.getShowName());
        } else if (i4 == SeriesTypeEnum.Variety.getType()) {
            List listD5 =
                    java.util.Arrays.asList(
                            cVar2.g(this.f9500Q0), cVar2.i(), cVar2.l(this.f9500Q0));
            SeriesFilterAdapter aVar8 = this.f9497L0;
            if (aVar8 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "adapterFilter");
                throw null;
            }
            aVar8.a(listD5);
            AppCompatTextView appCompatTextView5 = this.M0;
            if (appCompatTextView5 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "textTitle");
                throw null;
            }
            appCompatTextView5.setText(SeriesTypeEnum.Variety.getShowName());
        }
        cVar = this;
        cVar.f9501R0 = -((int) resources().getDimension(R.dimen.px215));
        frameLayout = cVar.f9496K0;
        if (frameLayout == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewFilter");
            throw null;
        }
        ViewGroup.LayoutParams layoutParams2 = frameLayout.getLayoutParams();
        kotlin.jvm.internal.Intrinsics.checkNotNull(
                layoutParams2,
                "null cannot be cast to non-null type"
                        + " androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ((ConstraintLayout.LayoutParams) layoutParams2).setMarginEnd(cVar.f9501R0);
        aVar = cVar.f9497L0;
        if (aVar == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "adapterFilter");
            throw null;
        }
        aVar.f11054c =
                (ItemFocusCallback)
                        (cell, position, focused) -> {
                            if (focused) l0(true);
                        };
        aVar.f11053b = new SeriesBrowserCallback(cVar);
        recyclerView = cVar.f9493G0;
        if (recyclerView == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerView");
            throw null;
        }
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView list, int dx, int dy) {
                        if (f9495I0 == null || f9495I0.items.isEmpty()) return;
                        f9505W0 = f9494H0.getItemCount();
                        if (!f9507Y0
                                && f9504V0
                                && f9505W0 <= f9494H0.findLastVisibleItemPosition() + f9506X0) {
                            f9503U0++;
                            k0();
                        }
                    }
                });
        recyclerView2 = cVar.f9493G0;
        if (recyclerView2 != null) {
            recyclerView2.post(this::k0);
        } else {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerView");
            throw null;
        }
    }

    public final void k0() {
        this.f9507Y0 = true;
        NetworkManager networkManager = T0.getValue();
        int i4 = this.f9503U0;
        int i7 = this.f9500Q0;
        SeriesFilterAdapter aVar = this.f9497L0;
        if (aVar != null) {
            if (i4 == 1) resultGeneration++;
            final int generation = resultGeneration;
            SeriesBrowserCallback result = new SeriesBrowserCallback(this, i4, generation);
            com.xx.blbl.network.NetResultCallback<
                    com.xx.blbl.network.response.BaseResponse<
                            com.xx.blbl.network.response.GetAllSeriesWrapper>> bound =
                    ScreenResult.bind(this, result::onResponse, result::onFailure);
            networkManager.getAllSeries(
                    i4,
                    i7,
                    aVar.f11052a,
                    new com.xx.blbl.network.NetResultCallback<
                            com.xx.blbl.network.response.BaseResponse<
                                    com.xx.blbl.network.response.GetAllSeriesWrapper>>() {
                        public void onResponse(com.xx.blbl.network.response.BaseResponse<
                                com.xx.blbl.network.response.GetAllSeriesWrapper> value) {
                            runOnMain(() -> {
                                if (isCurrentResult(generation)) bound.onResponse(value);
                            });
                        }
                        public void onFailure(Throwable error) {
                            runOnMain(() -> {
                                if (isCurrentResult(generation)) bound.onFailure(error);
                            });
                        }
                    });
        } else {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "adapterFilter");
            throw null;
        }
    }

    boolean isCurrentResult(int generation) { return generation == resultGeneration; }
    int currentResultGeneration() { return resultGeneration; }

    void replaceFirstResults(List list) {
        boolean focusFirst = initialResultsPending && !f9502S0
                && (entryFocusAnchor != null && entryFocusAnchor.hasFocus()
                    || f9493G0.hasFocus()
                    || screenView != null && !screenView.hasFocus());
        initialResultsPending = false;
        returningPosition = -1;
        com.xx.blbl.platform.RecyclerFocus.cancel(f9493G0);
        f9495I0.replaceItems(list);
        f9494H0.scrollToPositionWithOffset(0, 0);
        f9493G0.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        if (focusFirst) com.xx.blbl.platform.RecyclerFocus.request(f9493G0, 0);
    }

    public final void l0(boolean z7) {
        if (z7) {
            if (this.f9502S0) {
                return;
            }
            this.f9502S0 = true;
            FrameLayout frameLayout = this.f9496K0;
            if (frameLayout == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewFilter");
                throw null;
            }
            ObjectAnimator objectAnimatorOfFloat =
                    ObjectAnimator.ofFloat(frameLayout, "translationX", this.f9501R0);
            objectAnimatorOfFloat.setDuration(500L);
            objectAnimatorOfFloat.start();
            return;
        }
        if (this.f9502S0) {
            this.f9502S0 = false;
            FrameLayout frameLayout2 = this.f9496K0;
            if (frameLayout2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewFilter");
                throw null;
            }
            ObjectAnimator objectAnimatorOfFloat2 =
                    ObjectAnimator.ofFloat(frameLayout2, "translationX", 0.0f);
            objectAnimatorOfFloat2.setDuration(500L);
            objectAnimatorOfFloat2.start();
        }
    }

    @Override
    public final void initializeScreen(Bundle bundle) {
        super.initializeScreen(bundle);
        Bundle bundle2 = getArguments();
        if (bundle2 != null) {
            this.f9500Q0 = bundle2.getInt("seasonType");
        }
    }

    @Override
    public boolean handleBack() {
        if (!f9502S0) return false;
        l0(false);
        if (f9495I0 != null) f9495I0.restoreFocus();
        return true;
    }

    @Override
    public void releaseScreenView() {
        resultGeneration++;
        initialResultsPending = false;
        entryFocusAnchor = null;
        if (f9493G0 != null) com.xx.blbl.platform.RecyclerFocus.cancel(f9493G0);
        if (f9493G0 != null) f9493G0.setAdapter(null);
        if (J0 != null) J0.setAdapter(null);
        f9493G0 = null;
        f9494H0 = null;
        f9495I0 = null;
        J0 = null;
        f9496K0 = null;
        f9497L0 = null;
        M0 = null;
        f9498N0 = null;
        O0 = null;
        f9499P0 = null;
        super.releaseScreenView();
    }
}
