package com.xx.blbl.ui.dialog;

import android.content.Context;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.ui.adapter.EpisodeListAdapter;

/** Application episode dialog whose owner supplies content and item actions. */
public final class VideoActionClickListener extends AppCompatDialog {
    public RecyclerView f12028f;
    public EpisodeListAdapter f12029p;
    public AppCompatTextView f12030v;
    public VideoModel f12031w;

    public VideoActionClickListener(Context context, int theme) {
        super(context, theme);
    }
}
