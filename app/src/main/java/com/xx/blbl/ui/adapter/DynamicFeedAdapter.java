package com.xx.blbl.ui.adapter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.following.BaseUserModel;
import com.xx.blbl.model.user.following.FollowingProfileModel;
import com.xx.blbl.model.user.following.FollowingProfileModelWrapper;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterL;
import com.xx.blbl.ui.holder.DynamicAuthorViewHolder;
import com.xx.blbl.ui.holder.VideoCardViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

public final class DynamicFeedAdapter extends Adapter {
    public final /* synthetic */ int f10341a;
    public final ArrayList f10342b;
    public int f10343c;
    public ItemClickCallback d;
    public final ItemClickCallback e;

    public DynamicFeedAdapter(int i4) {
        this.f10341a = i4;
        switch (i4) {
            case 1:
                this.f10342b = new ArrayList();
                this.e = new ContentClickRouter(this, 3);
                break;
            default:
                ArrayList arrayList = new ArrayList();
                this.f10342b = arrayList;
                FollowingProfileModelWrapper followingProfileModelWrapper =
                        new FollowingProfileModelWrapper();
                followingProfileModelWrapper.setDefaultItem(1);
                arrayList.add(followingProfileModelWrapper);
                this.e = new ItemRouterL(this, 27);
                break;
        }
    }

    public String a() {
        int i4;
        FollowingProfileModel user_profile;
        BaseUserModel info;
        String uid;
        ArrayList arrayList = this.f10342b;
        return (arrayList.isEmpty()
                        || this.f10343c >= arrayList.size()
                        || (i4 = this.f10343c) <= 0
                        || (user_profile =
                                        ((FollowingProfileModelWrapper) arrayList.get(i4))
                                                .getUser_profile())
                                == null
                        || (info = user_profile.getInfo()) == null
                        || (uid = info.getUid()) == null)
                ? ""
                : uid;
    }

    public void b(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = this.f10342b;
        int size = arrayList.size();
        arrayList.clear();
        notifyItemRangeRemoved(0, size);
        FollowingProfileModelWrapper followingProfileModelWrapper =
                new FollowingProfileModelWrapper();
        followingProfileModelWrapper.setDefaultItem(1);
        arrayList.add(followingProfileModelWrapper);
        arrayList.addAll(list);
        notifyItemRangeChanged(0, arrayList.size());
    }

    @Override
    public final int getItemCount() {
        switch (this.f10341a) {
            case 0:
                break;
        }
        return this.f10342b.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        BaseUserModel info;
        ArrayList arrayList = this.f10342b;
        switch (this.f10341a) {
            case 0:
                DynamicAuthorViewHolder holder2 = (DynamicAuthorViewHolder) holder;
                Intrinsics.checkNotNullParameter(holder2, "holder");
                Object obj = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                FollowingProfileModelWrapper followingProfileModelWrapper =
                        (FollowingProfileModelWrapper) obj;
                boolean z7 = this.f10343c == i4;
                int defaultItem = followingProfileModelWrapper.getDefaultItem();
                AppCompatImageView appCompatImageView = holder2.f14394a;
                AppCompatTextView appCompatTextView = holder2.f14395b;
                if (defaultItem == 1) {
                    appCompatTextView.setText(
                            appCompatTextView.getContext().getString(R.string.all_dynamic));
                    Glide.with(AppController.instance())
                            .load(Integer.valueOf(R.mipmap.icon_dynamic))
                            .into(appCompatImageView);
                } else {
                    FollowingProfileModel user_profile =
                            followingProfileModelWrapper.getUser_profile();
                    if (user_profile != null && (info = user_profile.getInfo()) != null) {
                        appCompatTextView.setText(info.getUname());
                        Object obj2 = ImageLoader.f736a;
                        ImageLoader.loadAvatar(info.getFace(), appCompatImageView);
                    }
                }
                holder2.d.setVisibility(z7 ? 0 : 4);
                holder2.f14396c.setSelected(z7);
                break;
            default:
                Intrinsics.checkNotNullParameter(holder, "holder");
                if (holder instanceof VideoCardViewHolder) {
                    Object obj3 = arrayList.get(i4);
                    Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                    ((VideoCardViewHolder) holder).bind((VideoModel) obj3);
                }
                break;
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        ItemClickCallback cVar = this.e;
        switch (this.f10341a) {
            case 0:
                View viewE =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_following, viewGroup, false);
                int i7 = DynamicAuthorViewHolder.e;
                Intrinsics.checkNotNull(viewE);
                ItemRouterL onItemClick = (ItemRouterL) cVar;
                Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
                return new DynamicAuthorViewHolder(viewE, onItemClick);
            default:
                View viewE2 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_video, viewGroup, false);
                int i8 = VideoCardViewHolder.f14413y;
                Intrinsics.checkNotNull(viewE2);
                ContentClickRouter onItemClick2 = (ContentClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClick2, "onItemClick");
                return new VideoCardViewHolder(viewE2, onItemClick2, true, null);
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        switch (this.f10341a) {
            case 1:
                Intrinsics.checkNotNullParameter(holder, "holder");
                super.onViewAttachedToWindow(holder);
                new Handler(Looper.getMainLooper())
                        .postDelayed(
                                () -> {
                                    if (holder.getBindingAdapterPosition() == this.f10343c
                                            && holder instanceof VideoCardViewHolder)
                                        ((VideoCardViewHolder) holder).f14421x.requestFocus();
                                },
                                200L);
                break;
            default:
                super.onViewAttachedToWindow(holder);
                break;
        }
    }
}
