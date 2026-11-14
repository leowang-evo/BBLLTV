package com.xx.blbl.network;

import com.xx.blbl.model.player.DashMediaModel;
import com.xx.blbl.model.player.PlayInfoModel;
import com.xx.blbl.model.player.VideoSegmentModel;
import com.xx.blbl.network.response.BaseResponse;

public enum VideoPlayResponse {
    VERIFICATION_REQUIRED,
    PLAYABLE,
    MISSING_STREAMS,
    FAILED;

    public static VideoPlayResponse classify(BaseResponse<PlayInfoModel> response) {
        if (response == null) return FAILED;
        return classify(response.getCode(), response.getData());
    }

    public static VideoPlayResponse classifyPgc(
            com.xx.blbl.network.response.Base2Response<PlayInfoModel> response) {
        return response == null ? FAILED : classify(response.getCode(), response.getResult());
    }

    private static VideoPlayResponse classify(int code, PlayInfoModel info) {
        if (info != null && info.requiresVerification()) return VERIFICATION_REQUIRED;
        if (code != 0) return FAILED;
        if (info == null) return MISSING_STREAMS;
        if (info.getDash() != null && info.getDash().getVideo() != null) {
            for (DashMediaModel video : info.getDash().getVideo()) {
                if (video != null && !video.playableURLs().isEmpty()) return PLAYABLE;
            }
        }
        if (info.getDurl() != null && !info.getDurl().isEmpty()) {
            for (VideoSegmentModel segment : info.getDurl()) {
                if (segment == null
                        || segment.getUrl() == null
                        || segment.getUrl().trim().isEmpty()) {
                    return MISSING_STREAMS;
                }
            }
            return PLAYABLE;
        }
        return MISSING_STREAMS;
    }
}
