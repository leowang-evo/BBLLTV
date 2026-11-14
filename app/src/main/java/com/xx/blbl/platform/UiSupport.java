package com.xx.blbl.platform;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.holder.EpisodeChoiceViewHolder;
import com.xx.blbl.ui.holder.SeriesCardViewHolder;
import com.xx.blbl.ui.holder.VideoCardViewHolder;

/** Application operations separated from R8's shared helper methods. */
public final class UiSupport {
    private UiSupport() {}

    public static View inflate(
            ViewGroup parent, String parameter, int layout, ViewGroup root, boolean attach) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(parent, parameter);
        return LayoutInflater.from(parent.getContext()).inflate(layout, root, attach);
    }

    public static StringBuilder k(String value) {
        return new StringBuilder(value);
    }

    public static String n(String left, String right) {
        return left + right;
    }

    public static String g(int value, String prefix) {
        return prefix + value;
    }

    public static VideoCardViewHolder videoHolder(
            View view, ItemClickCallback click, ItemFocusCallback focus) {
        return new VideoCardViewHolder(view, click, false, focus);
    }

    public static SeriesCardViewHolder seriesHolder(
            View view, ItemClickCallback click, ItemFocusCallback focus) {
        return new SeriesCardViewHolder(view, click, focus);
    }

    public static EpisodeChoiceViewHolder episodeHolder(View view, ItemClickCallback click) {
        return new EpisodeChoiceViewHolder(view, click);
    }

    public static int textColor(android.content.Context context) {
        android.util.TypedValue value = new android.util.TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorPrimary, value, true);
        return value.data;
    }

    public static void toast(android.content.Context context, String message) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}
