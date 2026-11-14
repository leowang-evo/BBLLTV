package com.xx.blbl.ui.home;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xx.blbl.AppController;
import com.xx.blbl.model.lane.LaneInfoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.GetLaneWrapper;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.adapter.SeriesLaneAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class SeriesLanesFragment extends PagedListFragment<LaneInfoModel> {
    public SeriesLaneAdapter O0;
    public final Lazy<NetworkManager> f9605P0 = LazyKt.lazy(AppController::network);
    public final int f9606Q0 = 20;
    public long f9607R0;
    public int f9608S0 = 1;

    @Override
    public String cacheKey() {
        return "laneCacheList" + f9608S0;
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && O0 != null) O0.replaceItems((List) value);
    }

    @Override
    public PagedListAdapter createAdapter() {
        return O0 = new SeriesLaneAdapter();
    }

    @Override
    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(requireContext());
    }

    @Override
    public void loadPage(int page) {
        if (page == 1) {
            f9607R0 = 0;
            loadCachedData();
        }
        this.loadingPage = true;
        f9605P0.getValue()
                .getLane(
                        f9608S0,
                        page == 1 ? 0 : 1,
                        f9607R0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<GetLaneWrapper> response) -> {
                                    setLoading(false);
                                    this.loadingPage = false;
                                    if (response != null && response.getData() != null)
                                        f9607R0 = response.getData().getNext_cursor();
                                    if (f9608S0 == 1) loadTimeline();
                                    GetLaneWrapper data =
                                            response == null ? null : response.getData();
                                    List<LaneInfoModel> modules =
                                            data == null ? null : data.getModules();
                                    if (modules == null || modules.isEmpty()) {
                                        setHasMore(false);
                                        return;
                                    }
                                    ArrayList<LaneInfoModel> valid = new ArrayList<>();
                                    for (LaneInfoModel module : modules)
                                        if (module.getItems() != null
                                                && !module.getItems().isEmpty()) valid.add(module);
                                    showContent();
                                    if (page == 1) {
                                        setHasMore(true);
                                        O0.replaceItems(valid);
                                        cacheData(valid);
                                    } else O0.appendItems(valid);
                                    if (modules.size() < f9606Q0) setHasMore(false);
                                },
                                error -> {
                                    this.loadingPage = false;
                                    if (page == 1) showNetworkError();
                                    setLoading(false);
                                }));
    }

    private void loadTimeline() {
        f9605P0.getValue()
                .getSeriesTimeLine(
                        ScreenResult.bind(
                                this,
                                response -> {
                                    java.util.List<
                                                    com.xx.blbl.model.series.timeline
                                                            .TimeLineADayModel>
                                            days = response == null ? null : response.getResult();
                                    if (days == null || days.size() < 10 || O0 == null) return;
                                    int today = 6;
                                    for (int index = 0; index < days.size(); index++) {
                                        com.xx.blbl.model.series.timeline.TimeLineADayModel day =
                                                days.get(index);
                                        if (day.getEpisodes() != null)
                                            for (com.xx.blbl.model.series.timeline
                                                            .SeriesTimeLineModel
                                                    episode : day.getEpisodes())
                                                episode.setDayOfWeek(day.getDay_of_week());
                                        if (day.is_today() == 1) today = index;
                                    }
                                    ArrayList<com.xx.blbl.model.series.timeline.SeriesTimeLineModel>
                                            recent = new ArrayList<>();
                                    for (int index = today;
                                            index >= Math.max(0, today - 3);
                                            index--)
                                        if (days.get(index).getEpisodes() != null)
                                            recent.addAll(days.get(index).getEpisodes());
                                    com.xx.blbl.model.series.timeline.TimeLineADayModel aggregate =
                                            new com.xx.blbl.model.series.timeline
                                                    .TimeLineADayModel();
                                    aggregate.setEpisodes(recent);
                                    ArrayList<com.xx.blbl.model.series.timeline.TimeLineADayModel>
                                            timeline = new ArrayList<>();
                                    timeline.add(aggregate);
                                    timeline.addAll(days);
                                    LaneInfoModel lane = new LaneInfoModel();
                                    lane.setTimelineDay(timeline);
                                    if (O0.items.size() >= 1) {
                                        O0.items.add(1, lane);
                                        O0.notifyItemInserted(1);
                                    }
                                },
                                error -> {}));
    }

    @Override
    public void onMessageEvent(String event) {
        if (isHidden()) return;
        if ("clickTab0".equals(event)
                || ("clickTopTab2".equals(event) && f9608S0 == 1)
                || ("clickTopTab3".equals(event) && f9608S0 == 2)) refresh();
        else if ("backPressed".equals(event)) scrollToTop();
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        if (getArguments() != null) f9608S0 = getArguments().getInt("type");
    }
}
