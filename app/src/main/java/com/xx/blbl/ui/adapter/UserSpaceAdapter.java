package com.xx.blbl.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.AppServiceProvider;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.UserHeaderCallback;
import com.xx.blbl.ui.holder.UserHeaderViewHolder;
import com.xx.blbl.ui.holder.VideoCardViewHolder;

import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

public final class UserSpaceAdapter extends Adapter {
    public View f11503f;
    public ItemFocusCallback f11504p;
    public final ArrayList f11500a = new ArrayList();
    public final Object f11501b =
            kotlin.LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new AppServiceProvider(this, 11));
    public String f11502c = "";
    public String d = "";
    public String e = "";
    public final ContentClickRouter f11505v = new ContentClickRouter(this, 21);
    public final CatalogClickRouter f11506w = new CatalogClickRouter(this, 19);

    @Override
    public final int getItemCount() {
        return this.f11500a.size() + 1;
    }

    @Override
    public final int getItemViewType(int i4) {
        return i4 == 0 ? 0 : 1;
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        int i7;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if ((holder instanceof VideoCardViewHolder) && (i7 = i4 - 1) >= 0) {
            ArrayList arrayList = this.f11500a;
            if (i7 < arrayList.size()) {
                Object obj = arrayList.get(i7);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                ((VideoCardViewHolder) holder).bind((VideoModel) obj);
                return;
            }
        }
        if (holder instanceof UserHeaderViewHolder) {
            UserHeaderViewHolder eVar = (UserHeaderViewHolder) holder;
            String mid = this.f11502c;
            String name = this.d;
            String avatar = this.e;
            Intrinsics.checkNotNullParameter(mid, "mid");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(avatar, "avatar");
            eVar.e.setText(name);
            if (TextUtils.isEmpty(mid)) {
                return;
            }
            if (!TextUtils.isEmpty(avatar)) {
                Object obj2 = ImageLoader.f736a;
                ImageLoader.loadAvatar(avatar, eVar.d);
            }
            eVar.f360w = mid;
            kotlin.Lazy r7 = (kotlin.Lazy) eVar.f355b;
            ((NetworkManager) r7.getValue())
                    .checkUserRelation(
                            mid,
                            new com.xx.blbl.network.NetResultCallback<
                                    com.xx.blbl.network.response.BaseResponse<
                                            com.xx.blbl.model.user.CheckRelationModel>>() {
                                public void onFailure(Throwable error) {}

                                public void onResponse(
                                        com.xx.blbl.network.response.BaseResponse<
                                                        com.xx.blbl.model.user.CheckRelationModel>
                                                response) {
                                    com.xx.blbl.model.user.CheckRelationModel data =
                                            response == null ? null : response.getData();
                                    UserHeaderViewHolder.showRelationship(
                                            eVar,
                                            data == null
                                                    ? null
                                                    : Integer.valueOf(data.getAttribute()));
                                }
                            });
            ((NetworkManager) r7.getValue()).getUserSpace(mid, new UserHeaderCallback(eVar, 0));
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int i4) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CatalogClickRouter onFocusListener = this.f11506w;
        if (i4 != 0) {
            View viewInflate =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cell_video, parent, false);
            int i7 = VideoCardViewHolder.f14413y;
            Intrinsics.checkNotNull(viewInflate);
            return UiSupport.videoHolder(viewInflate, this.f11505v, onFocusListener);
        }
        View viewInflate2 =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cell_user_space_top, parent, false);
        int i8 = UserHeaderViewHolder.f353x;
        Intrinsics.checkNotNull(viewInflate2);
        Intrinsics.checkNotNullParameter(onFocusListener, "onFocusListener");
        return new UserHeaderViewHolder(viewInflate2, onFocusListener);
    }
}
