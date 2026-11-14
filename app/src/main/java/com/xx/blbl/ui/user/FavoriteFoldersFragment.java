package com.xx.blbl.ui.user;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.favorite.FavoriteFolderModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.FavoriteFoldersWrapper;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.LaneItemAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class FavoriteFoldersFragment extends BaseScreenFragment {
    public RecyclerView f9642G0;
    public LaneItemAdapter f9643H0;
    public final Lazy<NetworkManager> f9644I0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> J0 = LazyKt.lazy(AppController::state);

    @Override
    public String cacheKey() {
        return "collectionCacheList";
    }

    @Override
    public int layoutResource() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void renderCachedData(Object value) {
        if (value instanceof List && f9643H0 != null) {
            f9643H0.f10345b.clear();
            f9643H0.f10345b.addAll((List) value);
            f9643H0.notifyDataSetChanged();
        }
    }

    @Override
    public void reloadContent() {
        j0();
    }

    @Override
    public void bindScreen(View view) {
        f9642G0 = view.findViewById(R.id.recyclerView);
        f9642G0.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        f9643H0 = new LaneItemAdapter(1);
        f9642G0.setAdapter(f9643H0);
        j0();
    }

    public void j0() {
        if (!J0.getValue().signedIn) {
            showStatus(R.mipmap.empty, getString(R.string.need_sign_in));
            return;
        }
        setLoading(true);
        loadCachedData();
        f9644I0.getValue()
                .getFavoriteFolders(
                        J0.getValue().userId,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<FavoriteFoldersWrapper> response) -> {
                                    setLoading(false);
                                    FavoriteFoldersWrapper data =
                                            response == null ? null : response.getData();
                                    List<FavoriteFolderModel> folders =
                                            data == null ? null : data.getList();
                                    if (folders == null) return;
                                    for (FavoriteFolderModel folder : folders) {
                                        String cover =
                                                AppController.instance()
                                                        .getSharedPreferences("BLBL", 0)
                                                        .getString("fav" + folder.getId(), "");
                                        if (!TextUtils.isEmpty(cover)) folder.setImageUrl(cover);
                                    }
                                    showContent();
                                    renderCachedData(folders);
                                    cacheData(new ArrayList<>(folders));
                                },
                                error -> {
                                    setLoading(false);
                                    showNetworkError();
                                }));
    }

    @Override
    public void onMessageEvent(String event) {
        if (!isHidden()
                && ("signIn".equals(event)
                        || "updateUserInfo".equals(event)
                        || "signOut".equals(event)
                        || "clickTab4".equals(event)
                        || "clickTopTab0".equals(event)
                        || "keyMenuPress".equals(event))) j0();
    }

    @Override
    public void releaseScreenView() {
        if (f9642G0 != null) f9642G0.setAdapter(null);
        f9642G0 = null;
        f9643H0 = null;
        super.releaseScreenView();
    }
}
