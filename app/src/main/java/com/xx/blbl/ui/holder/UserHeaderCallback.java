package com.xx.blbl.ui.holder;

import android.text.TextUtils;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xx.blbl.model.user.UserSpaceInfo;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseBaseResponse;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.platform.ImageLoader;

/** User header response/follow callbacks, preserving original application modes. */
public final class UserHeaderCallback<T> implements NetResultCallback<T> {
    public final int f350a;
    public final UserHeaderViewHolder f351b;

    public UserHeaderCallback(UserHeaderViewHolder owner, int mode) {
        f350a = mode;
        f351b = owner;
    }

    private void toast(String message) {
        Toast.makeText(f351b.f354a.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable error) {
        if (f350a != 0) toast(String.valueOf(error == null ? null : error.getMessage()));
    }

    @Override
    public void onResponse(T value) {
        if (f350a == 0) {
            BaseResponse response = (BaseResponse) value;
            UserSpaceInfo info = response == null ? null : (UserSpaceInfo) response.getData();
            if (info == null) return;
            if (!TextUtils.isEmpty(info.getFace())) ImageLoader.loadAvatar(info.getFace(), f351b.d);
            if (!TextUtils.isEmpty(info.getTop_photo()))
                Glide.with(f351b.f356c).load(info.getTop_photo()).into(f351b.f356c);
            if (!TextUtils.isEmpty(info.getName())) f351b.e.setText(info.getName());
            if (!TextUtils.isEmpty(info.getSign())) f351b.f357f.setText(info.getSign());
        } else {
            BaseBaseResponse response = (BaseBaseResponse) value;
            if (response != null && response.getCode() == 0) {
                f351b.f359v = f351b.f359v == 1 ? 0 : 1;
                UserHeaderViewHolder.showRelationship(f351b, f351b.f359v == 1 ? 2 : 0);
            } else toast(String.valueOf(response == null ? null : response.getMessage()));
        }
    }
}
