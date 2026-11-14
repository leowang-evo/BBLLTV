package com.xx.blbl.playback;

import android.view.View;

import com.xx.blbl.model.interaction.InteractionModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.playback.PlaybackFragment;

/** The application-only interaction branch formerly merged into a MediaCodec adapter. */
final class InteractionCallback implements NetResultCallback<BaseResponse<InteractionModel>> {
    private final PlaybackCoordinator state;
    private final VideoModel video;

    InteractionCallback(PlaybackCoordinator state, VideoModel video) {
        this.state = state;
        this.video = video;
    }

    public void onFailure(Throwable error) {}

    public void onResponse(BaseResponse<InteractionModel> value) {
        if (value == null || value.getData() == null) return;
        state.f12306t0 = true;
        PlaybackFragment fragment = (PlaybackFragment) state.fragmentReference.get();
        if (fragment != null && fragment.q() && fragment.f9693F0 != null) {
            fragment.f9693F0.setVisibility(View.VISIBLE);
            fragment.f9693F0.setCurrentCid(video.getCid());
            fragment.f9693F0.setModel(value.getData());
        }
    }
}
