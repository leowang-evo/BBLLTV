package com.xx.blbl.ui.holder;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;

import kotlin.jvm.internal.Intrinsics;

public final class SettingsHeaderViewHolder extends ViewHolder {
    public static final /* synthetic */ int f14787c = 0;
    public final AppCompatTextView f14788a;
    public final AppCompatTextView f14789b;

    public SettingsHeaderViewHolder(View view) {
        super(view);
        View viewFindViewById = view.findViewById(R.id.textView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.f14788a = (AppCompatTextView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.subTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.f14789b = (AppCompatTextView) viewFindViewById2;
    }
}
