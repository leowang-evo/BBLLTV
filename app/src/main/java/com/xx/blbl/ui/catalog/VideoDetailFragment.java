package com.xx.blbl.ui.catalog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.detail.VideoDetailModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.platform.VideoIdConverter;
import com.xx.blbl.ui.adapter.ItemFocusCallback;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.io.Serializable;
import java.util.List;

public final class VideoDetailFragment extends BaseScreenFragment {
    public ScreenContentAdapter f9526H0;
    public RecyclerView f9527I0;
    public long f9528K0;
    public long f9529L0;
    public VideoModel f9530N0;
    public List O0;
    public final Lazy<NetworkManager> f9525G0 = LazyKt.lazy(AppController::network);
    public String J0 = "";
    public String M0 = "";

    @Override
    public final void screenStarted() {
        super.screenStarted();
    }

    @Override
    public final void screenStopped() {
        super.screenStopped();
    }

    @Override
    public final int layoutResource() {
        return R.layout.fragment_series_detail;
    }

    @Override
    public final void bindScreen(View view) {
        AppCompatImageView appCompatImageView =
                (AppCompatImageView) view.findViewById(R.id.button_back_1);
        if (appCompatImageView != null) {
            appCompatImageView.setOnClickListener(
                    cell -> {
                        if (hostActivity() != null) hostActivity().onBackPressed();
                    });
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.f9527I0 = recyclerView;
        if (recyclerView != null) {
            view.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
        ScreenContentAdapter jVar = new ScreenContentAdapter(3);
        this.f9526H0 = jVar;
        RecyclerView recyclerView2 = this.f9527I0;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(jVar);
        }
        ScreenContentAdapter jVar2 = this.f9526H0;
        if (jVar2 != null) {
            jVar2.f10357c = this.f9530N0;
        }
        if (jVar2 != null) {
            jVar2.d = this.O0;
        }
        if (jVar2 != null) {
            jVar2.e =
                    (ItemFocusCallback)
                            (cell, position, focused) -> {
                                if (focused && f9527I0 != null) f9527I0.smoothScrollToPosition(0);
                            };
        }
        setLoading(true);
        f9525G0.getValue()
                .getVideoDetail(
                        f9528K0,
                        J0,
                        ScreenResult.bind(
                                this,
                                (BaseResponse<VideoDetailModel> response) -> {
                                    VideoDetailModel detail =
                                            response == null ? null : response.getData();
                                    if (detail != null) {
                                        if (detail.getView() != null
                                                && detail.getView().getOwner() != null
                                                && f9530N0 != null)
                                            f9530N0.setOwner(detail.getView().getOwner());
                                        f9526H0.f10357c = f9530N0;
                                        f9526H0.f10356b = detail;
                                        f9526H0.notifyDataSetChanged();
                                        showContent();
                                    }
                                    setLoading(false);
                                },
                                error -> {
                                    setLoading(false);
                                    showError(error == null ? null : error.getMessage());
                                }));
    }

    @Override
    public final void onMessageEvent(String event) {
        if (event == null || !event.startsWith("playUgc")) return;
        try {
            String[] values = event.split("\\|", -1);
            if (values.length != 4
                    || f9530N0 == null
                    || Long.parseLong(values[1]) != f9530N0.getAid()) return;
            f9530N0.setCid(Long.parseLong(values[2]));
            f9530N0.setProgress(Long.parseLong(values[3]));
            if (f9526H0 != null) {
                f9526H0.f10357c = f9530N0;
                f9526H0.notifyItemChanged(0);
                f9526H0.notifyItemChanged(1);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    @Override
    public final void initializeScreen(Bundle bundle) {
        super.initializeScreen(bundle);
        Bundle bundle2 = getArguments();
        if (bundle2 != null) {
            Serializable serializable = bundle2.getSerializable("model");
            if (serializable != null && (serializable instanceof VideoModel)) {
                VideoModel videoModel = (VideoModel) serializable;
                this.f9530N0 = videoModel;
                this.J0 = videoModel.getBvid();
                this.f9528K0 = videoModel.getAid();
                this.f9529L0 = videoModel.getCid();
                this.M0 = videoModel.getTitle();
            }
            String string = bundle2.getString("bvid");
            if (string != null && !TextUtils.isEmpty(string)) {
                this.J0 = string;
            }
            String string2 = bundle2.getString("title");
            if (string2 != null && !TextUtils.isEmpty(string2)) {
                this.M0 = string2;
            }
            if (bundle2.getLong("avid") != 0) {
                this.f9528K0 = bundle2.getLong("avid");
            }
            if (bundle2.getLong("cid") != 0) {
                this.f9529L0 = bundle2.getLong("cid");
            }
            if (this.f9528K0 == 0 && !TextUtils.isEmpty(this.J0)) {
                this.f9528K0 = VideoIdConverter.toAid(this.J0).longValue();
            }
            if (this.f9528K0 != 0 && TextUtils.isEmpty(this.J0)) {
                String strA = VideoIdConverter.toBvid(Long.valueOf(this.f9528K0));
                java.util.Objects.requireNonNull(strA, "av2bv");
                this.J0 = strA;
            }
            if (this.f9530N0 == null) {
                VideoModel videoModel2 = new VideoModel();
                this.f9530N0 = videoModel2;
                videoModel2.setBvid(this.J0);
                VideoModel videoModel3 = this.f9530N0;
                java.util.Objects.requireNonNull(videoModel3);
                videoModel3.setAid(this.f9528K0);
                VideoModel videoModel4 = this.f9530N0;
                java.util.Objects.requireNonNull(videoModel4);
                videoModel4.setCid(this.f9529L0);
                VideoModel videoModel5 = this.f9530N0;
                java.util.Objects.requireNonNull(videoModel5);
                videoModel5.setTitle(this.M0);
            }
            Serializable serializable2 = bundle2.getSerializable("list");
            List list = serializable2 instanceof List ? (List) serializable2 : null;
            if (list != null) {
                this.O0 = list;
            }
        }
    }

    @Override
    public void releaseScreenView() {
        if (f9527I0 != null) f9527I0.setAdapter(null);
        f9527I0 = null;
        f9526H0 = null;
        super.releaseScreenView();
    }
}
