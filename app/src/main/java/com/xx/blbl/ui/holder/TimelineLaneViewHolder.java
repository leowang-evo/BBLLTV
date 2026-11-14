package com.xx.blbl.ui.holder;

import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.series.timeline.SeriesTimeLineModel;
import com.xx.blbl.model.series.timeline.TimeLineADayModel;
import com.xx.blbl.ui.adapter.LaneItemAdapter;

import java.util.List;

public final class TimelineLaneViewHolder extends ViewHolder {
    public static final /* synthetic */ int f15064f = 0;
    public final View f15065a;
    public final RecyclerView f15066b;
    public final AppCompatImageView f15067c;
    public final LaneItemAdapter d;
    public List e;

    public TimelineLaneViewHolder(View view) {
        super(view);
        this.f15065a = view;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.f15066b = recyclerView;
        this.f15067c = (AppCompatImageView) view.findViewById(R.id.image_empty);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        LaneItemAdapter fVar = new LaneItemAdapter(4);
        this.d = fVar;
        recyclerView.getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 6));
        recyclerView.setAdapter(fVar);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public final void onCheckedChanged(RadioGroup radioGroup2, int i4) {
                        TimelineLaneViewHolder this$0 = TimelineLaneViewHolder.this;
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(this$0, "this$0");
                        int[] days = {
                            R.id.button_radio_0,
                            R.id.button_radio_1,
                            R.id.button_radio_2,
                            R.id.button_radio_3,
                            R.id.button_radio_4,
                            R.id.button_radio_5,
                            R.id.button_radio_6,
                            R.id.button_radio_7
                        };
                        for (int day = 0; day < days.length; day++)
                            if (i4 == days[day]) {
                                this$0.showDay(day);
                                break;
                            }
                    }
                });
    }

    public final void showDay(int i4) {
        List<SeriesTimeLineModel> episodes;
        List<SeriesTimeLineModel> episodes2;
        List<SeriesTimeLineModel> episodes3;
        List list = this.e;
        if (list != null) {
            int size = list.size();
            int i7 = 7;
            for (int i8 = 0; i8 < size; i8++) {
                if (((TimeLineADayModel) list.get(i8)).is_today() == 1) {
                    i7 = i8;
                }
            }
            LaneItemAdapter fVar = this.d;
            RecyclerView recyclerView = this.f15066b;
            AppCompatImageView appCompatImageView = this.f15067c;
            if (i4 == 0) {
                if (((TimeLineADayModel) list.get(0)).getEpisodes() == null
                        || ((episodes3 = ((TimeLineADayModel) list.get(0)).getEpisodes()) != null
                                && episodes3.isEmpty())) {
                    appCompatImageView.setVisibility(0);
                    recyclerView.setVisibility(8);
                } else {
                    appCompatImageView.setVisibility(8);
                    recyclerView.setVisibility(0);
                    List<SeriesTimeLineModel> episodes4 =
                            ((TimeLineADayModel) list.get(0)).getEpisodes();
                    kotlin.jvm.internal.Intrinsics.checkNotNull(episodes4);
                    fVar.a(episodes4);
                }
            }
            if (((TimeLineADayModel) list.get(i7)).getDay_of_week() > i4) {
                while (i7 > 0) {
                    if (((TimeLineADayModel) list.get(i7)).getDay_of_week() == i4) {
                        if (((TimeLineADayModel) list.get(i7)).getEpisodes() == null
                                || ((episodes2 = ((TimeLineADayModel) list.get(i7)).getEpisodes())
                                                != null
                                        && episodes2.isEmpty())) {
                            appCompatImageView.setVisibility(0);
                            recyclerView.setVisibility(8);
                            return;
                        }
                        appCompatImageView.setVisibility(8);
                        recyclerView.setVisibility(0);
                        List<SeriesTimeLineModel> episodes5 =
                                ((TimeLineADayModel) list.get(i7)).getEpisodes();
                        kotlin.jvm.internal.Intrinsics.checkNotNull(episodes5);
                        fVar.a(episodes5);
                        return;
                    }
                    i7--;
                }
                return;
            }
            int size2 = list.size();
            while (i7 < size2) {
                if (((TimeLineADayModel) list.get(i7)).getDay_of_week() == i4) {
                    if (((TimeLineADayModel) list.get(i7)).getEpisodes() == null
                            || ((episodes = ((TimeLineADayModel) list.get(i7)).getEpisodes())
                                            != null
                                    && episodes.isEmpty())) {
                        appCompatImageView.setVisibility(0);
                        recyclerView.setVisibility(8);
                        return;
                    }
                    appCompatImageView.setVisibility(8);
                    recyclerView.setVisibility(0);
                    List<SeriesTimeLineModel> episodes6 =
                            ((TimeLineADayModel) list.get(i7)).getEpisodes();
                    kotlin.jvm.internal.Intrinsics.checkNotNull(episodes6);
                    fVar.a(episodes6);
                    return;
                }
                i7++;
            }
        }
    }
}
