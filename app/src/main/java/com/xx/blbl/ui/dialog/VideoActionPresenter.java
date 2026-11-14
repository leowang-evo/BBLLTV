package com.xx.blbl.ui.dialog;

import android.content.Context;
import android.util.TypedValue;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.favorite.FavoriteFolderModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.*;
import com.xx.blbl.network.response.*;
import com.xx.blbl.platform.VideoIdConverter;

import java.util.*;

/** Shared application dialog actions. Framework behavior is supplied by AppCompat. */
public final class VideoActionPresenter {
    private VideoActionPresenter() {}

    public static int primary(Context context) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorPrimary, value, true);
        return value.resourceId != 0
                ? androidx.core.content.ContextCompat.getColor(context, value.resourceId)
                : value.data;
    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void coin(Context context, VideoModel video, NetResultCallback callback) {
        if (video == null) return;
        int[] amount = {2};
        new AlertDialog.Builder(context)
                .setTitle(R.string.give_coin)
                .setSingleChoiceItems(
                        context.getResources().getStringArray(R.array.give_coin_number),
                        1,
                        (dialog, which) -> amount[0] = which + 1)
                .setPositiveButton(
                        R.string.confirm,
                        (dialog, which) ->
                                AppController.network()
                                        .giveCoin(
                                                video.getAid(),
                                                video.getBvid(),
                                                amount[0],
                                                callback))
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public static void folders(
            Context context, VideoModel video, boolean add, NetResultCallback callback) {
        if (video == null) return;
        AppController.network()
                .getFavoriteFolders(
                        AppController.state().userId,
                        new NetResultCallback<BaseResponse<FavoriteFoldersWrapper>>() {
                            public void onFailure(Throwable error) {
                                toast(
                                        context,
                                        error == null ? "" : String.valueOf(error.getMessage()));
                            }

                            public void onResponse(BaseResponse<FavoriteFoldersWrapper> result) {
                                List<FavoriteFolderModel> folders =
                                        result == null || result.getData() == null
                                                ? null
                                                : result.getData().getList();
                                if (folders == null || folders.isEmpty()) return;
                                CharSequence[] names = new CharSequence[folders.size()];
                                boolean[] selected = new boolean[folders.size()];
                                selected[0] = true;
                                for (int n = 0; n < names.length; n++)
                                    names[n] = folders.get(n).getTitle();
                                new AlertDialog.Builder(context)
                                        .setTitle(R.string.collection)
                                        .setMultiChoiceItems(
                                                names,
                                                selected,
                                                (dialog, index, checked) ->
                                                        selected[index] = checked)
                                        .setPositiveButton(
                                                R.string.confirm,
                                                (dialog, index) -> {
                                                    StringBuilder ids = new StringBuilder();
                                                    for (int n = 0; n < selected.length; n++)
                                                        if (selected[n]) {
                                                            if (ids.length() > 0) ids.append(',');
                                                            ids.append(folders.get(n).getId());
                                                        }
                                                    if (ids.length() > 0) {
                                                        long aid = video.getAid();
                                                        if (aid == 0
                                                                && video.getBvid() != null
                                                                && !video.getBvid().isEmpty())
                                                            aid =
                                                                    VideoIdConverter.toAid(
                                                                                    video.getBvid())
                                                                            .longValue();
                                                        AppController.network()
                                                                .collection(
                                                                        aid,
                                                                        add,
                                                                        ids.toString(),
                                                                        callback);
                                                    }
                                                })
                                        .setNegativeButton(R.string.cancel, null)
                                        .show();
                            }
                        });
    }
}
