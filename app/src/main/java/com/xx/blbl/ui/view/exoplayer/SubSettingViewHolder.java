package com.xx.blbl.ui.view.exoplayer;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SubSettingViewHolder extends RecyclerView.ViewHolder {
    public final View checkView;
    public final TextView textView;

    @SuppressLint({"UnsafeOptInUsageError"})
    public SubSettingViewHolder(View view) {
        super(view);
        if (Build.VERSION.SDK_INT < 26) {
            view.setFocusable(true);
        }
        this.textView = (TextView) view.findViewById(0x7f0a0138);
        this.checkView = view.findViewById(0x7f0a0111);
    }
}
