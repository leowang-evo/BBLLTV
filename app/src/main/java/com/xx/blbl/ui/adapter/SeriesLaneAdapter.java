package com.xx.blbl.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.lane.LaneHeaderModel;
import com.xx.blbl.model.lane.LaneInfoModel;
import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.series.SeriesTypeEnum;
import com.xx.blbl.model.series.timeline.SeriesTimeLineModel;
import com.xx.blbl.model.series.timeline.TimeLineADayModel;
import com.xx.blbl.ui.holder.SeriesLaneViewHolder;
import com.xx.blbl.ui.holder.TimelineLaneViewHolder;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SeriesLaneAdapter extends PagedListAdapter {
    @Override
    public final ViewHolder createItemHolder(ViewGroup parent, int i4) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i4 == 1) {
            View viewInflate =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cell_lane_series_time_line, parent, false);
            int i7 = TimelineLaneViewHolder.f15064f;
            Intrinsics.checkNotNull(viewInflate);
            return new TimelineLaneViewHolder(viewInflate);
        }
        View viewInflate2 =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cell_lane_scrollable, parent, false);
        int i8 = SeriesLaneViewHolder.f14401f;
        Intrinsics.checkNotNull(viewInflate2);
        return new SeriesLaneViewHolder(viewInflate2);
    }

    @Override
    public final int getItemViewType(int i4) {
        LaneInfoModel laneInfoModel = (LaneInfoModel) itemAt(i4);
        if ((laneInfoModel != null ? laneInfoModel.getTimelineDay() : null) != null) {
            return 1;
        }
        return super.getItemViewType(i4);
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if ((holder instanceof SeriesLaneViewHolder) && itemAt(i4) != null) {
            SeriesLaneViewHolder fVar = (SeriesLaneViewHolder) holder;
            Object objC = itemAt(i4);
            Intrinsics.checkNotNull(objC);
            LaneInfoModel laneInfoModel = (LaneInfoModel) objC;
            fVar.f14403b.setText(laneInfoModel.getTitle());
            List<LaneItemModel> items = laneInfoModel.getItems();
            if (items != null) {
                Intrinsics.areEqual(laneInfoModel.getStyle(), "follow");
                LaneItemAdapter fVar2 = fVar.d;
                fVar2.getClass();
                ArrayList arrayList = fVar2.f10345b;
                arrayList.clear();
                arrayList.addAll(items);
                fVar2.notifyDataSetChanged();
            }
            List<LaneHeaderModel> headers = laneInfoModel.getHeaders();
            View view = fVar.f14404c;
            if (headers != null) {
                List<LaneHeaderModel> headers2 = laneInfoModel.getHeaders();
                Intrinsics.checkNotNull(headers2);
                if (headers2.isEmpty()) {
                    view.setVisibility(8);
                } else {
                    try {
                        List<LaneHeaderModel> headers3 = laneInfoModel.getHeaders();
                        Intrinsics.checkNotNull(headers3);
                        LaneHeaderModel laneHeaderModel =
                                (LaneHeaderModel) CollectionsKt.first(headers3);
                        if (laneHeaderModel.getUrl().contains("index_type")) {
                            view.setVisibility(0);
                            Matcher matcher =
                                    Pattern.compile("(?<=index_type=).*?(?=&|$)")
                                            .matcher(laneHeaderModel.getUrl());
                            Intrinsics.checkNotNullExpressionValue(matcher, "matcher(...)");
                            while (matcher.find()) {
                                String strGroup = matcher.group();
                                Intrinsics.checkNotNullExpressionValue(strGroup, "group(...)");
                                fVar.e = Integer.parseInt(strGroup);
                            }
                            if (fVar.e == SeriesTypeEnum.Anime.getType()) {
                                Matcher matcher2 =
                                        Pattern.compile("(?<=area=).*?(?=&|$)")
                                                .matcher(laneHeaderModel.getUrl());
                                Intrinsics.checkNotNullExpressionValue(matcher2, "matcher(...)");
                                while (matcher2.find()) {
                                    if (Intrinsics.areEqual(matcher2.group(), "1,6,7")) {
                                        fVar.e = SeriesTypeEnum.ChinaAnime.getType();
                                    }
                                }
                            }
                        } else {
                            view.setVisibility(8);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        view.setVisibility(8);
                    }
                }
            } else {
                view.setVisibility(8);
            }
        }
        if (!(holder instanceof TimelineLaneViewHolder) || itemAt(i4) == null) {
            return;
        }
        Object objC2 = itemAt(i4);
        Intrinsics.checkNotNull(objC2);
        if (((LaneInfoModel) objC2).getTimelineDay() != null) {
            TimelineLaneViewHolder kVar = (TimelineLaneViewHolder) holder;
            Object objC3 = itemAt(i4);
            Intrinsics.checkNotNull(objC3);
            List<TimeLineADayModel> timelineDay = ((LaneInfoModel) objC3).getTimelineDay();
            Intrinsics.checkNotNull(timelineDay);
            kVar.e = timelineDay;
            List<SeriesTimeLineModel> episodes = timelineDay.get(0).getEpisodes();
            if (episodes != null) {
                kVar.d.a(episodes);
            }
        }
    }
}
