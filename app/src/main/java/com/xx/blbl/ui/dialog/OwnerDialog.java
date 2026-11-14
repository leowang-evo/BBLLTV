package com.xx.blbl.ui.dialog;

import android.content.Context;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.*;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.*;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.*;
import com.xx.blbl.network.response.BaseBaseResponse;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.ui.adapter.DynamicFeedAdapter;

import kotlin.Lazy;
import kotlin.LazyKt;

/** Uploader's paginated videos and follow action, composed with public AndroidX APIs. */
public final class OwnerDialog extends AppCompatDialog {
    public final AppCompatTextView f12054G;
    public final Lazy<NetworkManager> f12055H = LazyKt.lazy(AppController::network);
    public OwnerModel f12056I;
    public VideoModel f12057J;
    public int f12058K = 1;
    public final boolean f12059L = true;
    public int M;
    public final int f12060N = 12;
    public boolean f12061O;
    public int f12062P = -1;
    public final RecyclerView f12063f;
    public final GridLayoutManager f12064p;
    public final AppCompatImageView f12065v, f12069z;
    public final AppCompatTextView f12066w;
    public final DynamicFeedAdapter f12067x;
    public final ProgressBar f12068y;

    public OwnerDialog(Context context) {
        super(context, R.style.DialogTheme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.dialog_owner_detail, null);
        setContentView(root);
        f12063f = root.findViewById(R.id.recyclerView);
        f12065v = root.findViewById(R.id.image_avatar);
        f12066w = root.findViewById(R.id.text_name);
        f12068y = root.findViewById(R.id.progressBar);
        f12069z = root.findViewById(R.id.icon_follow);
        f12054G = root.findViewById(R.id.text_follow);
        root.findViewById(R.id.button_follow).setOnClickListener(v -> follow());
        f12067x = new DynamicFeedAdapter(1);
        f12063f.setAdapter(f12067x);
        f12064p = new GridLayoutManager(context, 3);
        f12063f.setLayoutManager(f12064p);
        f12063f.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    public void onScrolled(RecyclerView v, int dx, int dy) {
                        if (dy > 0
                                && !f12061O
                                && f12064p.findLastVisibleItemPosition()
                                        >= f12067x.getItemCount() - f12060N) j();
                    }
                });
        setCanceledOnTouchOutside(true);
    }

    public void bindOwner(OwnerModel owner, VideoModel current) {
        f12056I = owner;
        f12057J = current;
        f12066w.setText(owner.getName());
        ImageLoader.loadAvatar(owner.getFace(), f12065v);
        f12058K = 1;
        j();
        f12055H.getValue().checkUserRelation(owner.getMid(), new OwnerVideosCallback(this, 0));
    }

    public static void i(OwnerDialog dialog, Integer relation) {
        boolean follows = relation != null && (relation == 2 || relation == 6);
        dialog.f12054G.setText(
                relation != null && relation == 6
                        ? R.string.follow_as_friend
                        : follows ? R.string.followed : R.string.follow);
        int color =
                ContextCompat.getColor(
                        dialog.getContext(), follows ? R.color.grey : R.color.colorAccent);
        dialog.f12054G.setTextColor(color);
        dialog.f12069z.setImageResource(follows ? R.drawable.icon_check : R.drawable.icon_plus);
        dialog.f12069z.setColorFilter(color);
        dialog.f12062P = follows ? 1 : 0;
    }

    private void follow() {
        if (f12056I == null || f12062P < 0) return;
        f12055H.getValue()
                .userRelationModify(
                        f12056I.getMid(),
                        f12062P == 1 ? 2 : 1,
                        new NetResultCallback<BaseBaseResponse>() {
                            public void onFailure(Throwable t) {
                                Toast.makeText(
                                                getContext(),
                                                t == null ? "" : String.valueOf(t.getMessage()),
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }

                            public void onResponse(BaseBaseResponse r) {
                                if (r != null && r.getCode() == 0)
                                    i(OwnerDialog.this, f12062P == 1 ? 0 : 2);
                                else if (r != null)
                                    Toast.makeText(getContext(), r.getMessage(), Toast.LENGTH_SHORT)
                                            .show();
                            }
                        });
    }

    public void j() {
        if (f12061O || f12056I == null) return;
        f12061O = true;
        if (f12058K == 1) f12068y.setVisibility(View.VISIBLE);
        f12055H.getValue()
                .getUserDynamic(f12056I.getMid(), f12058K, 20, new OwnerVideosCallback(this, 1));
    }
}
