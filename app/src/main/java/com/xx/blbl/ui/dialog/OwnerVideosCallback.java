package com.xx.blbl.ui.dialog;

import android.view.View;
import android.widget.Toast;

import com.xx.blbl.model.user.CheckRelationModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.*;

import java.util.*;

/** App-owned relation/dynamic callbacks retained under their original aliases. */
public final class OwnerVideosCallback implements NetResultCallback {
    public final int f12052a;
    public final OwnerDialog f12053b;

    public OwnerVideosCallback(OwnerDialog dialog, int mode) {
        f12053b = dialog;
        f12052a = mode;
    }

    public void onFailure(Throwable t) {
        if (f12052a == 0) return;
        Toast.makeText(
                        f12053b.getContext(),
                        t == null ? "" : String.valueOf(t.getMessage()),
                        Toast.LENGTH_SHORT)
                .show();
        finish();
    }

    public void onResponse(Object value) {
        BaseResponse<?> r = (BaseResponse<?>) value;
        if (f12052a == 0) {
            CheckRelationModel data = r == null ? null : (CheckRelationModel) r.getData();
            OwnerDialog.i(f12053b, data == null ? null : data.getAttribute());
            return;
        }
        UserDynamicResponse data = r == null ? null : (UserDynamicResponse) r.getData();
        List<VideoModel> list = data == null ? null : data.getArchives();
        OwnerDialog d = f12053b;
        if (list != null) {
            if (d.f12058K == 1) {
                int old = d.f12067x.f10342b.size();
                d.f12067x.f10342b.clear();
                d.f12067x.notifyItemRangeRemoved(0, old);
                d.f12067x.f10342b.addAll(list);
                d.f12067x.notifyItemRangeInserted(0, list.size());
                int selection = list.size() - 1;
                for (int n = 0; n < list.size(); n++) {
                    VideoModel v = list.get(n);
                    if (d.f12057J != null
                            && (d.f12057J.getAid() == v.getAid()
                                    || Objects.equals(d.f12057J.getBvid(), v.getBvid())))
                        selection = n;
                }
                if (selection >= 0) {
                    final int target = selection;
                    d.f12063f.post(
                            () -> {
                                d.f12064p.scrollToPosition(target);
                                d.f12063f.post(
                                        () -> {
                                            androidx.recyclerview.widget.RecyclerView.ViewHolder
                                                    holder =
                                                            d.f12063f
                                                                    .findViewHolderForAdapterPosition(
                                                                            target);
                                            if (holder != null) holder.itemView.requestFocus();
                                        });
                            });
                }
            } else {
                int start = d.f12067x.f10342b.size();
                d.f12067x.f10342b.addAll(list);
                d.f12067x.notifyItemRangeInserted(start, list.size());
            }
            d.f12058K++;
        }
        finish();
    }

    private void finish() {
        f12053b.f12061O = false;
        f12053b.f12068y.setVisibility(View.GONE);
    }
}
