package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.live.LiveRoomItem;
import com.xx.blbl.model.live.LiveRoomModule;
import com.xx.blbl.model.live.LiveRoomWrapper;
import com.xx.blbl.network.response.LiveListWrapper;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.LiveRoomLaneViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.List;

public final class LiveLaneAdapter extends Adapter {
    public LiveListWrapper f10848a;

    public LiveLaneAdapter() {
        super();
    }

    @Override
    public final int getItemCount() {
        LiveListWrapper liveListWrapper = this.f10848a;
        if (liveListWrapper == null) {
            return 0;
        }
        List<LiveRoomItem> recommend_room_list = liveListWrapper.getRecommend_room_list();
        int i4 = recommend_room_list != null ? !recommend_room_list.isEmpty() ? 1 : 0 : 0;
        List<LiveRoomWrapper> room_list = liveListWrapper.getRoom_list();
        return room_list != null ? room_list.size() + i4 : i4;
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        List<LiveRoomWrapper> room_list;
        int i7;
        LiveListWrapper liveListWrapper;
        List<LiveRoomItem> recommend_room_list;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof LiveRoomLaneViewHolder) {
            if (i4 == 0
                    && (liveListWrapper = this.f10848a) != null
                    && (recommend_room_list = liveListWrapper.getRecommend_room_list()) != null
                    && (!recommend_room_list.isEmpty())) {
                String string = holder.itemView.getContext().getString(R.string.hot_live);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                LiveListWrapper liveListWrapper2 = this.f10848a;
                Intrinsics.checkNotNull(liveListWrapper2);
                List<LiveRoomItem> recommend_room_list2 = liveListWrapper2.getRecommend_room_list();
                Intrinsics.checkNotNull(recommend_room_list2);
                ((LiveRoomLaneViewHolder) holder).bind(string, recommend_room_list2);
            }
            LiveListWrapper liveListWrapper3 = this.f10848a;
            if (liveListWrapper3 == null
                    || (room_list = liveListWrapper3.getRoom_list()) == null
                    || (i7 = i4 - 1) < 0
                    || i7 >= room_list.size()) {
                return;
            }
            LiveRoomLaneViewHolder cVar = (LiveRoomLaneViewHolder) holder;
            LiveRoomModule module_info = room_list.get(i7).getModule_info();
            cVar.bind(
                    String.valueOf(module_info != null ? module_info.getTitle() : null),
                    room_list.get(i7).getList());
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        View viewE =
                UiSupport.inflate(
                        viewGroup, "parent", R.layout.cell_lane_scrollable, viewGroup, false);
        int i7 = LiveRoomLaneViewHolder.d;
        Intrinsics.checkNotNull(viewE);
        return new LiveRoomLaneViewHolder(viewE);
    }
}
