package com.xx.blbl.ui.view.exoplayer;

public interface OnVideoSettingChangeListener {
    void onAspectRatioChange(int i4);

    void onAudioChange(int i4);

    void onButtonClick(int i4);

    void onLiveQualityChange(int i4);

    void onMenuDown();

    void onNext();

    void onPlaySpeedChange(float f7);

    void onPrevious();

    void onResolutionChange(int i4);

    void onSubtitleChange(int i4);

    void onVideoCodecChange(int i4);
}
