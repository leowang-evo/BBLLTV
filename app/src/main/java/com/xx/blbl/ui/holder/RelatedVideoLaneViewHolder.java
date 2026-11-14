package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.*;

import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.ui.adapter.RelatedVideosAdapter;
import com.xx.blbl.ui.navigation.ScreenNavigator;

public final class RelatedVideoLaneViewHolder extends RecyclerView.ViewHolder {
    public static final int f605b = 0;
    public final RelatedVideosAdapter f606a;

    public RelatedVideoLaneViewHolder(View view) {
        super(view);
        f606a = new RelatedVideosAdapter();
        RecyclerView list = view.findViewById(R.id.recyclerView);
        list.setLayoutManager(
                new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        list.setAdapter(f606a);
        f606a.f10352c = false;
        ((AppCompatTextView) view.findViewById(R.id.top_title)).setText(R.string.related_video);
        f606a.f10351b =
                (v, index) -> {
                    if (index >= 0 && index < f606a.f10350a.size())
                        ScreenNavigator.openVideo(
                                v.getContext(), (VideoModel) f606a.f10350a.get(index), true);
                };
    }
}
