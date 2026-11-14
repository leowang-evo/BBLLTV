package com.xx.blbl.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.SignOutModel;
import com.xx.blbl.model.user.UserStatModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.navigation.ScreenNavigator;
import com.xx.blbl.ui.screen.ScreenEvents;

import kotlin.Lazy;
import kotlin.LazyKt;

/** Account/profile dialog composed with upstream AppCompat and typed network API. */
public final class AccountDialog extends AppCompatDialog {
    public final AppCompatTextView f12072G, f12073H, f12074I;
    public final Lazy<NetworkManager> f12075J = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f12076K = LazyKt.lazy(AppController::state);
    public final AppCompatImageView f12077f;
    public final AppCompatTextView f12078p, f12079v, f12080w, f12081x, f12082y, f12083z;

    public AccountDialog(Context context) {
        super(context, R.style.DialogTheme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_info, null);
        f12077f = root.findViewById(R.id.image_avatar);
        f12078p = root.findViewById(R.id.text_name);
        f12079v = root.findViewById(R.id.text_vip);
        f12080w = root.findViewById(R.id.text_level);
        f12081x = root.findViewById(R.id.text_coin);
        f12082y = root.findViewById(R.id.text_following);
        f12083z = root.findViewById(R.id.text_follower);
        f12072G = root.findViewById(R.id.text_dynamic);
        f12073H = root.findViewById(R.id.button_sign_out);
        f12074I = root.findViewById(R.id.button_view_space);
        f12073H.setOnClickListener(view -> signOut());
        f12074I.setOnClickListener(
                view -> {
                    dismiss();
                    ScreenNavigator.openUserSpace(
                            context, String.valueOf(i().userId), i().nickname, i().avatar);
                });
        f12082y.setOnClickListener(
                view -> {
                    dismiss();
                    ScreenNavigator.openRelationships(0, context, String.valueOf(i().userId));
                });
        f12083z.setOnClickListener(
                view -> {
                    dismiss();
                    ScreenNavigator.openRelationships(1, context, String.valueOf(i().userId));
                });
        setContentView(root);
        setCanceledOnTouchOutside(true);
        ImageLoader.loadAvatar(i().avatar, f12077f);
        f12078p.setText(i().nickname);
        f12079v.setText(i().vipLabel);
        f12080w.setText(getContext().getString(R.string.level_, i().userLevel));
        f12081x.setText(getContext().getString(R.string.coin_number_, i().coinBalance));
        f12075J.getValue()
                .getUserStat(
                        new NetResultCallback<BaseResponse<UserStatModel>>() {
                            public void onFailure(Throwable error) {}

                            public void onResponse(BaseResponse<UserStatModel> value) {
                                UserStatModel stats = value == null ? null : value.getData();
                                if (stats == null) return;
                                f12082y.setText(
                                        getContext()
                                                .getString(
                                                        R.string.following_count_,
                                                        stats.getFollowing()));
                                f12083z.setText(
                                        getContext()
                                                .getString(
                                                        R.string.follower_count_,
                                                        stats.getFollower()));
                                f12072G.setText(
                                        getContext()
                                                .getString(
                                                        R.string.dynamic_count_,
                                                        stats.getDynamic_count()));
                            }
                        });
    }

    public AppState i() {
        return f12076K.getValue();
    }

    private void signOut() {
        f12075J.getValue()
                .signOut(
                        new NetResultCallback<BaseResponse<SignOutModel>>() {
                            public void onFailure(Throwable error) {}

                            public void onResponse(BaseResponse<SignOutModel> value) {
                                if (value != null && value.getCode() == 0) {
                                    i().clearAccount();
                                    ScreenEvents.post("signOut");
                                    ScreenEvents.post("updateUserInfo");
                                    dismiss();
                                }
                            }
                        });
    }
}
