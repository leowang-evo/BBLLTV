package com.xx.blbl.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.common.PlayerSettingModel;
import com.xx.blbl.recovery.ItemRouterL;
import com.xx.blbl.ui.holder.SettingsHeaderViewHolder;
import com.xx.blbl.ui.holder.SettingsOptionViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

public final class PlayerOptionAdapter extends Adapter {
    public ItemClickCallback f10348b;
    public final ArrayList f10347a = new ArrayList();
    public final ItemRouterL f10349c = new ItemRouterL(this, 29);

    public final void a(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = this.f10347a;
        int size = arrayList.size();
        arrayList.clear();
        if (size > 0) {
            notifyItemRangeRemoved(0, size);
        }
        arrayList.addAll(list);
        notifyItemRangeChanged(0, arrayList.size());
    }

    public final void b(int i4, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ArrayList arrayList = this.f10347a;
        if (i4 < arrayList.size()) {
            ((PlayerSettingModel) arrayList.get(i4)).setShowValue(value);
            notifyItemChanged(i4);
        }
    }

    @Override
    public final int getItemCount() {
        return this.f10347a.size();
    }

    @Override
    public final int getItemViewType(int i4) {
        return ((PlayerSettingModel) this.f10347a.get(i4)).getType();
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        boolean z7 = holder instanceof SettingsHeaderViewHolder;
        ArrayList arrayList = this.f10347a;
        if (z7) {
            SettingsHeaderViewHolder aVar = (SettingsHeaderViewHolder) holder;
            String title = ((PlayerSettingModel) arrayList.get(i4)).getTitle();
            String showValue = ((PlayerSettingModel) arrayList.get(i4)).getShowValue();
            Intrinsics.checkNotNullParameter(title, "title");
            aVar.f14788a.setText(title);
            boolean zIsEmpty = TextUtils.isEmpty(showValue);
            AppCompatTextView appCompatTextView = aVar.f14789b;
            if (zIsEmpty) {
                appCompatTextView.setVisibility(8);
                return;
            } else {
                appCompatTextView.setVisibility(0);
                appCompatTextView.setText(showValue);
                return;
            }
        }
        if (holder instanceof SettingsOptionViewHolder) {
            SettingsOptionViewHolder bVar = (SettingsOptionViewHolder) holder;
            Object obj = arrayList.get(i4);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            PlayerSettingModel playerSettingModel = (PlayerSettingModel) obj;
            int icon = playerSettingModel.getIcon();
            AppCompatImageView appCompatImageView = bVar.f14791a;
            if (icon == 0) {
                appCompatImageView.setVisibility(8);
            } else {
                appCompatImageView.setVisibility(0);
                com.bumptech.glide.Glide.with(appCompatImageView.getContext())
                        .load(Integer.valueOf(playerSettingModel.getIcon()))
                        .into(appCompatImageView);
            }
            bVar.f14792b.setText(playerSettingModel.getTitle());
            boolean zIsEmpty2 = TextUtils.isEmpty(playerSettingModel.getShowValue());
            AppCompatTextView appCompatTextView2 = bVar.f14793c;
            if (zIsEmpty2) {
                appCompatTextView2.setVisibility(8);
            } else {
                appCompatTextView2.setVisibility(0);
                appCompatTextView2.setText(playerSettingModel.getShowValue());
            }
            int type = playerSettingModel.getType();
            AppCompatImageView appCompatImageView2 = bVar.d;
            AppCompatImageView appCompatImageView3 = bVar.e;
            if (type == 2) {
                appCompatImageView3.setVisibility(playerSettingModel.getCheck() ? 0 : 8);
                appCompatImageView2.setVisibility(8);
            } else {
                appCompatImageView3.setVisibility(8);
                appCompatImageView2.setVisibility(0);
            }
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int i4) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i4 == 1) {
            View viewInflate =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cell_player_setting_head, parent, false);
            int i7 = SettingsHeaderViewHolder.f14787c;
            Intrinsics.checkNotNull(viewInflate);
            return new SettingsHeaderViewHolder(viewInflate);
        }
        View viewInflate2 =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cell_player_setting, parent, false);
        int i8 = SettingsOptionViewHolder.f14790f;
        Intrinsics.checkNotNull(viewInflate2);
        ItemRouterL onItemClickListener = this.f10349c;
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        return new SettingsOptionViewHolder(viewInflate2, onItemClickListener);
    }
}
