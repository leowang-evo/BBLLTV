package com.xx.blbl.ui.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.*;

import com.xx.blbl.R;
import com.xx.blbl.model.series.UgcEpisodeModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.VideoIdConverter;
import com.xx.blbl.ui.adapter.OwnerVideosAdapter;
import com.xx.blbl.ui.navigation.ScreenNavigator;

import java.util.*;

public final class UgcEpisodesLaneViewHolder extends RecyclerView.ViewHolder {
    public static final int f607p = 0;
    public final View f608a, d;
    public final AppCompatTextView f609b, e;
    public final OwnerVideosAdapter f610c;
    public VideoModel f611f;

    public UgcEpisodesLaneViewHolder(View view) {
        super(view);
        f608a = view;
        f609b = view.findViewById(R.id.top_title);
        d = view.findViewById(R.id.button_order);
        e = view.findViewById(R.id.text_order);
        f610c = new OwnerVideosAdapter();
        f610c.f11906c = false;
        RecyclerView list = view.findViewById(R.id.recyclerView);
        list.setLayoutManager(
                new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        list.setAdapter(f610c);
        d.setVisibility(View.VISIBLE);
        d.setOnClickListener(
                v -> {
                    f610c.d = !f610c.d;
                    Collections.reverse(f610c.f11904a);
                    f610c.notifyDataSetChanged();
                    e.setText(f610c.d ? R.string.positive_sequence : R.string.negative_sequence);
                });
        f610c.f11905b = (v, index) -> select(v, index);
    }

    private void select(View view, int index) {
        if (index < 0 || index >= f610c.f11904a.size()) return;
        UgcEpisodeModel episode = (UgcEpisodeModel) f610c.f11904a.get(index);
        if (f611f != null && Objects.equals(f611f.getBvid(), episode.getBvid())) {
            Toast.makeText(view.getContext(), R.string.series_already_open, Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        VideoModel arc = episode.getArc();
        if (arc == null) return;
        arc.setCid(episode.getCid());
        if (arc.getAid() == 0 && arc.getId() != 0) arc.setAid(arc.getId());
        if (arc.getAid() == 0 && !TextUtils.isEmpty(arc.getBvid()))
            arc.setAid(VideoIdConverter.toAid(arc.getBvid()).longValue());
        if (arc.getAid() != 0 && TextUtils.isEmpty(arc.getBvid()))
            arc.setBvid(VideoIdConverter.toBvid(arc.getAid()));
        ScreenNavigator.openVideo(view.getContext(), arc, true);
    }
}
