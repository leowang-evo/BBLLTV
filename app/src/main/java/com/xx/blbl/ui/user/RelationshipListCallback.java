package com.xx.blbl.ui.user;

import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.GetFollowUserWrapper;

/** Typed version of the original follower/following response callback. */
public final class RelationshipListCallback
        implements NetResultCallback<BaseResponse<GetFollowUserWrapper>> {
    public final int f9708a;
    public final RelationshipListFragment f9709b;
    public final int f9710c;

    public RelationshipListCallback(RelationshipListFragment owner, int page, int mode) {
        f9708a = mode;
        f9709b = owner;
        f9710c = page;
    }

    @Override
    public void onFailure(Throwable failure) {
        f9709b.runOnMain(
                () -> {
                    if (!f9709b.isAttachedToHost()) return;
                    if (f9710c == 1)
                        f9709b.showError(failure == null ? null : failure.getMessage());
                    f9709b.setLoading(false);
                });
    }

    @Override
    public void onResponse(BaseResponse<GetFollowUserWrapper> response) {
        f9709b.runOnMain(
                () -> {
                    if (!f9709b.isAttachedToHost()) return;
                    f9709b.setLoading(false);
                    f9709b.showContent();
                    RelationshipListFragment.q0(
                            f9709b, f9710c, response == null ? null : response.getData());
                });
    }
}
