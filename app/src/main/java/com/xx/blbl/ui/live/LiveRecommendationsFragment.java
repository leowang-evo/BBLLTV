package com.xx.blbl.ui.live;

import android.view.View;

import androidx.recyclerview.widget.*;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.network.*;
import com.xx.blbl.network.response.*;
import com.xx.blbl.ui.adapter.LiveLaneAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;

import kotlin.Lazy;
import kotlin.LazyKt;

/** Live recommendation lanes driven by the published network API. */
public final class LiveRecommendationsFragment extends BaseScreenFragment {
    public RecyclerView f12100G0;
    public LiveLaneAdapter f12101H0;
    public final Lazy<NetworkManager> f12102I0 = LazyKt.lazy(AppController::network);

    @Override
    public int layoutResource() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void bindScreen(View view) {
        f12100G0 = view.findViewById(R.id.recyclerView);
        f12100G0.setLayoutManager(new LinearLayoutManager(view.getContext()));
        f12101H0 = new LiveLaneAdapter();
        f12100G0.setAdapter(f12101H0);
        refresh();
    }

    @Override
    public void onMessageEvent(String event) {
        if (!isScreenHidden()
                && ("clickTab3".equals(event)
                        || "clickLiveTopTab0".equals(event)
                        || "keyMenuPress".equals(event))) refresh();
    }

    private void refresh() {
        setLoading(true);
        f12102I0.getValue()
                .getLiveList(
                        new NetResultCallback<BaseResponse<LiveListWrapper>>() {
                            public void onFailure(Throwable error) {
                                setLoading(false);
                                showNetworkError();
                            }

                            public void onResponse(BaseResponse<LiveListWrapper> result) {
                                setLoading(false);
                                if (result != null
                                        && result.getData() != null
                                        && f12101H0 != null) {
                                    showContent();
                                    f12101H0.f10848a = result.getData();
                                    f12101H0.notifyDataSetChanged();
                                } else showEmptyState();
                            }
                        });
    }
}
