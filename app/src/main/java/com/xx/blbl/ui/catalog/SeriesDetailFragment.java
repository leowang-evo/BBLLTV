package com.xx.blbl.ui.catalog;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.series.EpisodeProgressModel;
import com.xx.blbl.model.series.EpisodesDetailModel;
import com.xx.blbl.model.series.SeriesUserState;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

public final class SeriesDetailFragment extends BaseScreenFragment {
    public final Lazy<NetworkManager> f9512G0 = LazyKt.lazy(AppController::network);
    public ScreenContentAdapter f9513H0;
    public RecyclerView f9514I0;
    public Long J0, f9515K0;

    @Override
    public int layoutResource() {
        return R.layout.fragment_series_detail;
    }

    @Override
    public void bindScreen(View view) {
        View back = view.findViewById(R.id.button_back_1);
        if (back != null)
            back.setOnClickListener(
                    cell -> {
                        if (hostActivity() != null) hostActivity().onBackPressed();
                    });
        f9514I0 = view.findViewById(R.id.recyclerView);
        f9514I0.setLayoutManager(new LinearLayoutManager(requireContext()));
        f9513H0 = new ScreenContentAdapter(2);
        f9514I0.setAdapter(f9513H0);
        f9513H0.f10357c =
                (ItemFocusCallback)
                        (cell, position, focused) -> {
                            if (focused && f9514I0 != null) f9514I0.smoothScrollToPosition(0);
                        };
        load();
    }

    private void load() {
        setLoading(true);
        f9512G0.getValue()
                .getVideoEpisodes(
                        J0,
                        f9515K0,
                        ScreenResult.bind(
                                this,
                                (Base2Response<EpisodesDetailModel> response) -> {
                                    setLoading(false);
                                    EpisodesDetailModel detail =
                                            response == null ? null : response.getResult();
                                    if (detail != null) {
                                        showContent();
                                        f9513H0.f10356b = detail;
                                        f9513H0.notifyDataSetChanged();
                                    }
                                },
                                error -> {
                                    setLoading(false);
                                    showError(error == null ? null : error.getMessage());
                                }));
    }

    @Override
    public void reloadContent() {
        load();
    }

    @Override
    public void onMessageEvent(String event) {
        if (event == null || !event.startsWith("playEpisode")) return;
        try {
            String[] values = event.split("\\|", -1);
            if (values.length != 4 || f9513H0 == null) return;
            EpisodeProgressModel progress = new EpisodeProgressModel();
            progress.setLast_ep_id(Long.parseLong(values[1]));
            progress.setLast_time(Long.parseLong(values[2]) / 1000);
            progress.setLast_ep_index(values[3]);
            EpisodesDetailModel detail = (EpisodesDetailModel) f9513H0.f10356b;
            SeriesUserState status = detail == null ? null : detail.getUser_status();
            if (status != null) status.setProgress(progress);
            f9513H0.notifyItemChanged(0);
        } catch (NumberFormatException ignored) {
        }
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        if (getArguments() != null) {
            J0 = getArguments().getLong("seasonId");
            f9515K0 = getArguments().getLong("episodeId");
        }
    }

    @Override
    public void releaseScreenView() {
        if (f9514I0 != null) f9514I0.setAdapter(null);
        f9514I0 = null;
        f9513H0 = null;
        super.releaseScreenView();
    }
}
