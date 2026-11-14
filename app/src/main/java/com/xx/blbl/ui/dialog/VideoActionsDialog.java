package com.xx.blbl.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.*;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.*;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.VideoIdConverter;
import com.xx.blbl.state.AppState;

import kotlin.Lazy;
import kotlin.LazyKt;

/** Player video action dialog with public AppCompat and typed service callbacks. */
public final class VideoActionsDialog extends AppCompatDialog
        implements View.OnClickListener, View.OnLongClickListener {
    public static final int f12036P = 0;
    public final LinearLayoutCompat f12037G, f12038H, f12051z;
    public final AppCompatTextView f12039I;
    public final Lazy<NetworkManager> f12040J = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f12041K = LazyKt.lazy(AppController::state);
    public VideoModel f12042L;
    public boolean M, f12043N, f12044O;
    public final AppCompatImageView f12045f, f12046p, f12047v;
    public final AppCompatTextView f12048w, f12049x, f12050y;

    public VideoActionsDialog(Context context) {
        super(context, R.style.DialogTheme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.dialog_action, null);
        setContentView(root);
        f12045f = root.findViewById(R.id.icon_like);
        f12046p = root.findViewById(R.id.icon_coin);
        f12047v = root.findViewById(R.id.icon_collection);
        f12048w = root.findViewById(R.id.text_like);
        f12049x = root.findViewById(R.id.text_coin);
        f12050y = root.findViewById(R.id.text_collection);
        f12051z = root.findViewById(R.id.button_like);
        f12037G = root.findViewById(R.id.button_coin);
        f12038H = root.findViewById(R.id.button_collection);
        f12039I = root.findViewById(R.id.button_triple);
        for (View view : new View[] {f12051z, f12037G, f12038H, f12039I})
            view.setOnClickListener(this);
        f12037G.setOnLongClickListener(this);
        f12038H.setOnLongClickListener(this);
        setCanceledOnTouchOutside(true);
    }

    public void i(View view) {
        VideoActionPresenter.coin(view.getContext(), f12042L, new FavoriteActionCallback(this, 2));
    }

    public NetworkManager j() {
        return f12040J.getValue();
    }

    public void onClick(View view) {
        if (!f12041K.getValue().signedIn) {
            VideoActionPresenter.toast(getContext(), getContext().getString(R.string.need_sign_in));
            return;
        }
        VideoModel video = f12042L;
        if (video == null) return;
        if (view == f12051z)
            j().like(video.getAid(), video.getBvid(), M ? 2 : 1, new VideoActionCallback(this, 3));
        else if (view == f12037G) {
            if (!f12044O)
                j().giveCoin(
                                video.getAid(),
                                video.getBvid(),
                                getContext()
                                        .getSharedPreferences("BLBL", 0)
                                        .getInt("giveCoinNumber", 2),
                                new FavoriteActionCallback(this, 2));
        } else if (view == f12038H) {
            if (video.getAid() == 0 && !TextUtils.isEmpty(video.getBvid()))
                video.setAid(VideoIdConverter.toAid(video.getBvid()).longValue());
            j().collection(
                            video.getAid(),
                            !f12043N,
                            String.valueOf(f12041K.getValue().defaultFavoriteId),
                            new VideoActionCallback(this, 2));
        } else if (view == f12039I && !(f12044O && f12043N && M))
            j().tripleAction(video.getAid(), video.getBvid(), new FavoriteActionCallback(this, 3));
    }

    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)
                && (getCurrentFocus() == f12037G || getCurrentFocus() == f12038H))
            return onLongClick(getCurrentFocus());
        return super.onKeyLongPress(keyCode, event);
    }

    public boolean onLongClick(View view) {
        if (view == f12037G) {
            i(view);
            return true;
        }
        if (view == f12038H) {
            VideoActionPresenter.folders(
                    getContext(), f12042L, !f12043N, new FavoriteActionCallback(this, 1));
            return true;
        }
        return false;
    }
}
