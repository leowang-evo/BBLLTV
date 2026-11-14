package com.xx.blbl.ui.home;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.UserInfoModel;
import com.xx.blbl.model.user.following.BaseUserModel;
import com.xx.blbl.model.user.following.FollowingProfileModel;
import com.xx.blbl.model.user.following.FollowingProfileModelWrapper;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.*;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.DynamicFeedAdapter;
import com.xx.blbl.ui.adapter.DynamicVideoFeedAdapter;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.screen.ScreenResult;
import com.xx.blbl.ui.screen.TabScreenFragment;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

/** Dynamic subscriptions and per-author archive pagination. */
public final class DynamicFragment extends TabScreenFragment {
    public RecyclerView f9616G0, f9617H0;
    public DynamicFeedAdapter f9618I0;
    public DynamicVideoFeedAdapter J0;
    public WrapContentGirdLayoutManager f9619K0;
    public LinearLayoutManager f9620L0;
    public final Lazy<NetworkManager> M0 = LazyKt.lazy(AppController::network);
    public final AppState f9621N0 = AppController.state();
    public int O0 = 1;
    public long f9622P0;
    public boolean f9623Q0;
    public int f9624R0;
    public final int f9625S0 = 12;
    public boolean T0, f9626U0;
    public final ArrayList<String> f9627V0 = new ArrayList<>();
    public int f9628W0;
    public final int f9629X0 = 12;
    public boolean f9630Y0, f9631Z0;
    public int f9632a1;
    private int generation;
    private int videoRequest;

    private <T> NetResultCallback<T> callback(
            ScreenResult.Action<T> success, ScreenResult.Action<Throwable> failure) {
        int request = generation;
        return new NetResultCallback<T>() {
            public void onResponse(T value) {
                runOnMain(
                        () -> {
                            if (request == generation
                                    && DynamicFragment.this.screenView != null
                                    && isAttachedToHost()) success.accept(value);
                        });
            }

            public void onFailure(Throwable error) {
                runOnMain(
                        () -> {
                            if (request == generation
                                    && DynamicFragment.this.screenView != null
                                    && isAttachedToHost()) failure.accept(error);
                        });
            }
        };
    }

    private void failed(Throwable error, boolean pageError) {
        T0 = false;
        setLoading(false);
        if (getContext() != null)
            Toast.makeText(
                            requireContext(),
                            String.valueOf(error == null ? null : error.getMessage()),
                            Toast.LENGTH_SHORT)
                    .show();
        if (pageError) showNetworkError();
    }

    public static void j0(DynamicFragment page) {
        page.loadVideos();
    }

    private void loadVideos() {
        final int currentVideoRequest = ++videoRequest;
        T0 = true;
        if (O0 == 1) setLoading(true);
        if (f9618I0 == null || f9618I0.f10343c == 0) {
            if (O0 == 1) f9622P0 = 0;
            M0.getValue()
                    .getAllDynamic(
                            O0,
                            f9622P0 == 0 ? null : f9622P0,
                            callback(
                                    (BaseResponse<AllDynamicResponse> response) -> {
                                        if (currentVideoRequest != videoRequest) return;
                                        AllDynamicResponse data =
                                                response == null ? null : response.getData();
                                        if (data != null) {
                                            applyVideos(data.getItems());
                                            f9622P0 = data.getOffset();
                                        }
                                        T0 = false;
                                        setLoading(false);
                                    },
                                    error -> {
                                        if (currentVideoRequest == videoRequest)
                                            failed(error, false);
                                    }));
        } else {
            String uid = f9618I0.a();
            if (TextUtils.isEmpty(uid)) {
                T0 = false;
                setLoading(false);
                return;
            }
            M0.getValue()
                    .getUserDynamic(
                            uid,
                            O0,
                            20,
                            callback(
                                    (BaseResponse<UserDynamicResponse> response) -> {
                                        if (currentVideoRequest != videoRequest) return;
                                        UserDynamicResponse data =
                                                response == null ? null : response.getData();
                                        if (data != null) applyVideos(data.getArchives());
                                        T0 = false;
                                        setLoading(false);
                                    },
                                    error -> {
                                        if (currentVideoRequest == videoRequest)
                                            failed(error, false);
                                    }));
        }
    }

    private void applyVideos(List<VideoModel> videos) {
        if (videos == null || J0 == null) return;
        if (O0 == 1) {
            J0.replaceItems(videos);
            f9623Q0 = true;
            if (f9619K0 != null) f9619K0.scrollToPosition(0);
        } else J0.appendItems(videos);
        if (videos.isEmpty()) f9623Q0 = false;
    }

    @Override
    public String cacheKey() {
        return "dynamicAllCacheList";
    }

    @Override
    public int layoutResource() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && J0 != null) J0.replaceItems((List) value);
    }

    @Override
    public void reloadContent() {
        if (f9621N0.signedIn) k0();
        else if (hostActivity() != null) hostActivity().showLogin();
    }

    @Override
    public void screenHiddenChanged(boolean hidden) {
        if (!hidden && J0 != null && J0.lastFocusedItem != null) J0.lastFocusedItem.requestFocus();
    }

    @Override
    public void bindScreen(View view) {
        f9616G0 = view.findViewById(R.id.recyclerViewLeft);
        f9617H0 = view.findViewById(R.id.recyclerViewRight);
        f9618I0 = new DynamicFeedAdapter(0);
        J0 = new DynamicVideoFeedAdapter();
        f9620L0 = new LinearLayoutManager(requireContext());
        f9619K0 = new WrapContentGirdLayoutManager(requireContext(), 3);
        f9616G0.setAdapter(f9618I0);
        f9616G0.setLayoutManager(f9620L0);
        f9617H0.setAdapter(J0);
        f9617H0.setLayoutManager(f9619K0);
        f9618I0.d =
                (ItemClickCallback)
                        (cell, position) -> {
                            O0 = 1;
                            f9622P0 = 0;
                            loadVideos();
                        };
        f9616G0.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView list, int dx, int dy) {
                        f9628W0 = f9620L0.getItemCount();
                        if (!f9630Y0
                                && f9631Z0
                                && f9628W0 <= f9620L0.findLastVisibleItemPosition() + f9629X0)
                            loadMoreAuthors();
                    }
                });
        f9617H0.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView list, int dx, int dy) {
                        f9624R0 = f9619K0.getItemCount();
                        if (!T0
                                && f9623Q0
                                && f9624R0 <= f9619K0.findLastVisibleItemPosition() + f9625S0) {
                            O0++;
                            loadVideos();
                        }
                    }
                });
        k0();
    }

    public void k0() {
        generation++;
        videoRequest++;
        T0 = false;
        f9630Y0 = false;
        f9631Z0 = false;
        if (!f9621N0.signedIn) {
            showStatus(R.mipmap.empty, getString(R.string.need_sign_in));
            return;
        }
        showContent();
        O0 = 1;
        f9622P0 = 0;
        setLoading(true);
        int request = generation;
        if (!f9626U0)
            runIo(
                    () -> {
                        Object cached = f9621N0.cache.a("dynamicUserCacheList");
                        runOnMain(
                                () -> {
                                    if (request == generation
                                            && cached instanceof List
                                            && f9618I0 != null) {
                                        f9626U0 = true;
                                        f9618I0.b((List) cached);
                                    }
                                });
                    });
        M0.getValue()
                .getFollowing(
                        callback(
                                (BaseResponse<FollowingResponse> response) -> {
                                    FollowingResponse data =
                                            response == null ? null : response.getData();
                                    List<FollowingProfileModelWrapper> authors =
                                            data == null ? null : data.getItems();
                                    if (authors == null) {
                                        f9632a1 = 0;
                                        f9631Z0 = true;
                                        loadMoreAuthors();
                                    } else {
                                        f9618I0.b(authors);
                                        f9627V0.clear();
                                        for (FollowingProfileModelWrapper author : authors) {
                                            FollowingProfileModel profile =
                                                    author.getUser_profile();
                                            if (profile != null
                                                    && profile.getInfo() != null
                                                    && profile.getInfo().getUid() != null)
                                                f9627V0.add(profile.getInfo().getUid());
                                        }
                                        f9632a1 = 0;
                                        f9631Z0 = true;
                                        O0 = 1;
                                        f9622P0 = 0;
                                        loadVideos();
                                        ArrayList<FollowingProfileModelWrapper> snapshot =
                                                new ArrayList<>(authors);
                                        runIo(
                                                () ->
                                                        f9621N0.cache.b(
                                                                "dynamicUserCacheList", snapshot));
                                    }
                                    setLoading(false);
                                },
                                error -> failed(error, true)));
    }

    private void loadMoreAuthors() {
        f9630Y0 = true;
        f9632a1++;
        M0.getValue()
                .getFollowing(
                        String.valueOf(f9621N0.userId),
                        f9632a1,
                        callback(
                                (BaseResponse<GetFollowUserWrapper> response) -> {
                                    f9630Y0 = false;
                                    GetFollowUserWrapper data =
                                            response == null ? null : response.getData();
                                    List<UserInfoModel> authors =
                                            data == null ? null : data.getList();
                                    ArrayList<FollowingProfileModelWrapper> extra =
                                            new ArrayList<>();
                                    if (authors != null) {
                                        if (authors.isEmpty()) f9631Z0 = false;
                                        for (UserInfoModel author : authors)
                                            if (!f9627V0.contains(author.getMid())) {
                                                BaseUserModel info = new BaseUserModel();
                                                info.setUid(author.getMid());
                                                info.setUname(author.getUname());
                                                info.setFace(author.getFace());
                                                FollowingProfileModel profile =
                                                        new FollowingProfileModel();
                                                profile.setInfo(info);
                                                FollowingProfileModelWrapper wrapper =
                                                        new FollowingProfileModelWrapper();
                                                wrapper.setUser_profile(profile);
                                                extra.add(wrapper);
                                            }
                                    }
                                    if (!extra.isEmpty() && f9618I0 != null) {
                                        int start = f9618I0.f10342b.size();
                                        f9618I0.f10342b.addAll(extra);
                                        f9618I0.notifyItemRangeInserted(start, extra.size());
                                    }
                                    if (f9632a1 == 1) loadVideos();
                                },
                                error -> {
                                    f9630Y0 = false;
                                    f9632a1--;
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (isHidden()) return;
        if ("signIn".equals(event)
                || "updateUserInfo".equals(event)
                || "signOut".equals(event)
                || "clickTab2".equals(event)
                || "keyMenuPress".equals(event)) k0();
        else if ("backPressed".equals(event) && f9617H0 != null) f9617H0.scrollToPosition(0);
    }

    @Override
    public void releaseScreenView() {
        generation++;
        if (J0 != null) {
            J0.lastFocusedItem = null;
            J0.items.clear();
        }
        f9618I0 = null;
        J0 = null;
        f9616G0 = null;
        f9617H0 = null;
        f9619K0 = null;
        f9620L0 = null;
        super.releaseScreenView();
    }
}
