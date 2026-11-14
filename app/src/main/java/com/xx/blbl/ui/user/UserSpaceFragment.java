package com.xx.blbl.ui.user;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.UserDynamicResponse;
import com.xx.blbl.ui.adapter.UserSpaceAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenResult;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.List;

public final class UserSpaceFragment extends BaseScreenFragment {
    public final Lazy<NetworkManager> f9516G0 = LazyKt.lazy(AppController::network);
    public RecyclerView f9517H0;
    public UserSpaceAdapter f9518I0;
    public WrapContentGirdLayoutManager J0;
    public String f9519K0 = "", f9520L0 = "", M0 = "";
    public int f9521N0 = 1, f9522P0;
    public boolean O0, f9524R0;
    public final int f9523Q0 = 12;

    @Override
    public int layoutResource() {
        return R.layout.fragment_series_detail;
    }

    @Override
    public void screenHiddenChanged(boolean hidden) {
        if (!hidden && f9518I0 != null && f9518I0.f11503f != null) f9518I0.f11503f.requestFocus();
    }

    @Override
    public void bindScreen(View view) {
        View back = view.findViewById(R.id.button_back_1);
        if (back != null)
            back.setOnClickListener(
                    cell -> {
                        if (hostActivity() != null) hostActivity().onBackPressed();
                    });
        f9517H0 = view.findViewById(R.id.recyclerView);
        J0 = new WrapContentGirdLayoutManager(requireContext(), 4);
        J0.setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return position == 0 ? 4 : 1;
                    }
                });
        f9517H0.setLayoutManager(J0);
        f9518I0 = new UserSpaceAdapter();
        f9517H0.setAdapter(f9518I0);
        f9518I0.f11502c = f9519K0;
        f9518I0.d = f9520L0;
        f9518I0.e = M0;
        f9518I0.f11504p =
                (cell, position, focused) -> {
                    if (focused && f9517H0 != null) f9517H0.smoothScrollToPosition(0);
                };
        f9517H0.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView list, int dx, int dy) {
                        f9522P0 = J0.getItemCount();
                        if (!f9524R0
                                && O0
                                && f9522P0 <= J0.findLastVisibleItemPosition() + f9523Q0) {
                            f9521N0++;
                            j0();
                        }
                    }
                });
        f9517H0.post(this::j0);
    }

    public void j0() {
        f9524R0 = true;
        if (f9521N0 == 1) setLoading(true);
        f9516G0.getValue()
                .getUserDynamic(
                        f9519K0,
                        f9521N0,
                        20,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<UserDynamicResponse> response) -> {
                                    UserDynamicResponse data =
                                            response == null ? null : response.getData();
                                    List<VideoModel> videos =
                                            data == null ? null : data.getArchives();
                                    if (videos != null) {
                                        if (videos.isEmpty()) O0 = false;
                                        else if (f9521N0 == 1) {
                                            O0 = true;
                                            f9518I0.f11503f = null;
                                            f9518I0.f11500a.clear();
                                            f9518I0.f11500a.addAll(videos);
                                            f9518I0.notifyDataSetChanged();
                                        } else {
                                            int start = f9518I0.f11500a.size() + 1;
                                            f9518I0.f11500a.addAll(videos);
                                            f9518I0.notifyItemRangeInserted(start, videos.size());
                                        }
                                    }
                                    f9524R0 = false;
                                    setLoading(false);
                                },
                                error -> {
                                    f9524R0 = false;
                                    setLoading(false);
                                    if (getContext() != null)
                                        android.widget.Toast.makeText(
                                                        requireContext(),
                                                        String.valueOf(
                                                                error == null
                                                                        ? null
                                                                        : error.getMessage()),
                                                        0)
                                                .show();
                                }));
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        Bundle args = getArguments();
        if (args != null) {
            f9519K0 = args.getString("mid", "");
            f9520L0 = args.getString("name", "");
            M0 = args.getString("avatar", "");
        }
    }

    @Override
    public void releaseScreenView() {
        if (f9517H0 != null) f9517H0.setAdapter(null);
        f9517H0 = null;
        f9518I0 = null;
        J0 = null;
        super.releaseScreenView();
    }
}
