package com.xx.blbl.ui.user;

import android.os.Bundle;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.UserInfoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.GetFollowUserWrapper;
import com.xx.blbl.ui.adapter.CatalogAdapter;
import com.xx.blbl.ui.adapter.PagedListAdapter;
import com.xx.blbl.ui.screen.PagedListFragment;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.List;

/** Followers/following list, preserving original endpoint and fifty-item paging rules. */
public final class RelationshipListFragment extends PagedListFragment<UserInfoModel> {
    public final Lazy<NetworkManager> O0 = LazyKt.lazy(AppController::network);
    public String f9711P0 = "0";
    public int f9712Q0;
    public CatalogAdapter f9713R0;

    public static void q0(RelationshipListFragment owner, int page, GetFollowUserWrapper data) {
        List<UserInfoModel> list = data == null ? null : data.getList();
        if (list == null) return;
        if (page == 1) {
            if (owner.f9713R0 != null) owner.f9713R0.replaceItems(list);
            owner.setHasMore(list.size() >= 50);
        } else if (list.isEmpty()) owner.setHasMore(false);
        else if (owner.f9713R0 != null) owner.f9713R0.appendItems(list);
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public void bindScreen(View view) {
        super.bindScreen(view);
        setScreenTitle(getString(f9712Q0 == 1 ? R.string.my_follower : R.string.my_following));
        setHasMore(false);
    }

    @Override
    public PagedListAdapter createAdapter() {
        f9713R0 = new CatalogAdapter(6);
        return f9713R0;
    }

    @Override
    public int spanCount() {
        return 8;
    }

    @Override
    public void loadPage(int page) {
        if (page == 1) setLoading(true);
        if (f9712Q0 == 1)
            O0.getValue().getFollower(f9711P0, page, new RelationshipListCallback(this, page, 0));
        else O0.getValue().getFollowing(f9711P0, page, new RelationshipListCallback(this, page, 1));
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String user = arguments.getString("userId");
            if (user != null) f9711P0 = user;
            f9712Q0 = arguments.getInt("type");
        }
    }
}
