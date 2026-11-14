package com.xx.blbl.ui.navigation;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.xx.blbl.AppController;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.VideoModelMapper;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** Application routes and bundle contracts, independent of SDK-internal DI. */
public final class ScreenNavigator {
    public static final Lazy<AppState> f738a = LazyKt.lazy(AppController::state);

    private ScreenNavigator() {}

    private static MainActivity activity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof MainActivity) return (MainActivity) context;
            Context base = ((ContextWrapper) context).getBaseContext();
            if (base == context) break;
            context = base;
        }
        return context instanceof MainActivity ? (MainActivity) context : null;
    }

    private static void route(MainActivity activity, String className, Bundle args, String tag) {
        Fragment fragment =
                activity.getSupportFragmentManager()
                        .getFragmentFactory()
                        .instantiate(activity.getClassLoader(), className);
        fragment.setArguments(args);
        activity.showScreen(fragment, tag);
    }

    public static void openPlaylist(
            Context context, VideoModel model, List list, boolean showDetails) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(list, "list");
        MainActivity main = activity(context);
        if (main == null) return;
        Bundle args = new Bundle();
        args.putSerializable("model", model);
        args.putSerializable("list", (Serializable) list);
        boolean details = f738a.getValue().showVideoDetails && showDetails;
        route(
                main,
                details
                        ? "com.xx.blbl.ui.catalog.VideoDetailFragment"
                        : "com.xx.blbl.ui.playback.PlaybackFragment",
                args,
                details ? "videoDetail" : "videoPlayer");
    }

    public static void openVideo(Context context, VideoModel model, boolean showDetails) {
        Intrinsics.checkNotNullParameter(model, "model");
        MainActivity main = activity(context);
        if (main == null) return;
        Bundle args = new Bundle();
        args.putSerializable("model", model);
        boolean details = f738a.getValue().showVideoDetails && showDetails;
        route(
                main,
                details
                        ? "com.xx.blbl.ui.catalog.VideoDetailFragment"
                        : "com.xx.blbl.ui.playback.PlaybackFragment",
                args,
                details ? "videoDetail" : "videoPlayer");
    }

    public static void openRelationships(int type, Context context, String userId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        MainActivity main = activity(context);
        if (main == null) return;
        Bundle args = new Bundle();
        args.putString("userId", userId);
        args.putInt("type", type);
        route(main, "com.xx.blbl.ui.user.RelationshipListFragment", args, "followingFollowerUser");
    }

    private static boolean openHistorySeries(MainActivity main, HistoryVideoModel model) {
        if (TextUtils.isEmpty(model.getUri())) return false;
        try {
            URL url = new URL(model.getUri());
            String path = url.getPath();
            String last = path.substring(path.lastIndexOf('/') + 1);
            if (!last.startsWith("ss")) return false;
            Bundle args = new Bundle();
            args.putLong("seasonId", Long.parseLong(last.replace("ss", "")));
            args.putSerializable("title", model.getTitle());
            route(main, "com.xx.blbl.ui.catalog.SeriesDetailFragment", args, "seriesDetail");
            return true;
        } catch (Exception failure) {
            failure.printStackTrace();
            return false;
        }
    }

    private static boolean liveHistory(HistoryVideoModel model) {
        if (TextUtils.isEmpty(model.getUri())) return false;
        try {
            return new URL(model.getUri()).getHost().startsWith("live");
        } catch (Exception failure) {
            return false;
        }
    }

    public static void openHistoryVideo(Context context, HistoryVideoModel model) {
        MainActivity main = activity(context);
        if (main == null || openHistorySeries(main, model)) return;
        boolean details = f738a.getValue().showVideoDetails && !liveHistory(model);
        Bundle args = new Bundle();
        args.putSerializable("model", VideoModelMapper.fromHistory(model));
        if (!details) args.putLong("progress", model.getProgress() * 1000L);
        route(
                main,
                details
                        ? "com.xx.blbl.ui.catalog.VideoDetailFragment"
                        : "com.xx.blbl.ui.playback.PlaybackFragment",
                args,
                details ? "videoDetail" : "videoPlayer");
    }

    public static void openHistoryPlaylist(
            Context context, HistoryVideoModel model, ArrayList history) {
        MainActivity main = activity(context);
        if (main == null || openHistorySeries(main, model)) return;
        boolean details = f738a.getValue().showVideoDetails && !liveHistory(model);
        Bundle args = new Bundle();
        args.putSerializable("model", VideoModelMapper.fromHistory(model));
        args.putSerializable("list", VideoModelMapper.fromHistoryList(history));
        route(
                main,
                details
                        ? "com.xx.blbl.ui.catalog.VideoDetailFragment"
                        : "com.xx.blbl.ui.playback.PlaybackFragment",
                args,
                details ? "videoDetail" : "videoPlayer");
    }

    public static void openSeries(Context context, Long seasonId, Long episodeId, String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        MainActivity main = activity(context);
        if (main == null) return;
        Bundle args = new Bundle();
        args.putLong("seasonId", seasonId.longValue());
        if (episodeId != null) args.putLong("episodeId", episodeId.longValue());
        args.putSerializable("title", title);
        route(main, "com.xx.blbl.ui.catalog.SeriesDetailFragment", args, "seriesDetail");
    }

    public static void openUserSpace(Context context, String mid, String name, String avatar) {
        Intrinsics.checkNotNullParameter(mid, "mid");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        MainActivity main = activity(context);
        if (main == null) return;
        Bundle args = new Bundle();
        args.putString("mid", mid);
        args.putString("name", name);
        args.putString("avatar", avatar);
        route(main, "com.xx.blbl.ui.user.UserSpaceFragment", args, "userSpace");
    }
}
