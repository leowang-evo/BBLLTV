package com.xx.blbl.ui.view.exoplayer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.ui.PlayerView;

import com.kuaishou.akdanmaku.DanmakuConfig;
import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.kuaishou.akdanmaku.data.DataSource;
import com.kuaishou.akdanmaku.ecs.component.filter.TypeFilter;
import com.kuaishou.akdanmaku.render.SimpleRenderer;
import com.kuaishou.akdanmaku.render.TypedDanmakuRenderer;
import com.kuaishou.akdanmaku.ui.DanmakuPlayer;
import com.kuaishou.akdanmaku.ui.DanmakuView;
import com.xx.blbl.R;
import com.xx.blbl.model.player.VideoCodecEnum;
import com.xx.blbl.model.subtitle.SubtitleInfoModel;
import com.xx.blbl.model.video.quality.AudioQuality;
import com.xx.blbl.model.video.quality.VideoQuality;
import com.xx.blbl.ui.view.youtubeTapView.youtube.DoubleTapListener;
import com.xx.blbl.ui.view.youtubeTapView.youtube.YouTubeOverlay;

import java.util.Collections;
import java.util.List;

/** App playback/settings/danmaku integration composed with published Media3 and AkDanmaku. */
public class MyPlayerView extends FrameLayout {
    public static final int SHOW_BUFFERING_ALWAYS = 2,
            SHOW_BUFFERING_NEVER = 0,
            SHOW_BUFFERING_WHEN_PLAYING = 1;

    public interface ControllerVisibilityListener {
        void onVisibilityChanged(int visibility);
    }

    private final PlayerView videoView;
    private final MyPlayerControlView controller;
    private final MyPlayerSettingView settingView;
    private final YouTubeOverlay tapOverlay;
    private final DanmakuView danmakuView;
    private final TypeFilter typeFilter = new TypeFilter();
    private final DanmakuConfig danmakuConfig = new DanmakuConfig();
    private DanmakuPlayer danmakuPlayer;
    private Player player;
    private boolean useController = true, controllerAutoShow, doubleTapEnabled = true;
    private boolean consumeBackKeyUp;
    private CharSequence customErrorMessage;
    private int showBuffering = SHOW_BUFFERING_WHEN_PLAYING;
    private int controllerShowTimeoutMs = MyPlayerControlView.DEFAULT_SHOW_TIMEOUT_MS;
    private View customVideoSurface;
    private ControllerVisibilityListener visibilityListener;
    private MyPlayerControlView.VisibilityListener legacyVisibilityListener;
    private final DoubleTapGestureListener gestureListener;
    private final GestureDetector gestureDetector;
    private final Player.Listener playerListener =
            new Player.Listener() {
                @Override
                public void onIsPlayingChanged(boolean playing) {
                    if (playing) startDm();
                    else pauseDm();
                    updateControllerVisibility();
                }

                @Override
                public void onPositionDiscontinuity(
                        Player.PositionInfo oldPosition,
                        Player.PositionInfo newPosition,
                        int reason) {
                    seekDmTo(newPosition.positionMs);
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters parameters) {
                    if (danmakuPlayer != null) danmakuPlayer.updatePlaySpeed(parameters.speed);
                    settingView.setCurrentSpeed(parameters.speed);
                }

                @Override
                public void onPlaybackStateChanged(int state) {
                    updateBuffering();
                    updateControllerVisibility();
                }
            };

    public MyPlayerView(Context context) {
        this(context, null);
    }

    public MyPlayerView(Context context, AttributeSet attributes) {
        this(context, attributes, 0);
    }

    public MyPlayerView(Context context, AttributeSet attributes, int defStyleAttr) {
        super(context, attributes, defStyleAttr);
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        setFocusable(true);
        setClickable(true);
        videoView = new PlayerView(context, attributes, defStyleAttr);
        videoView.setUseController(false);
        // This layer renders video. Remote focus belongs to this view and the app controls.
        videoView.setId(View.NO_ID);
        videoView.setFocusableInTouchMode(false);
        videoView.setFocusable(false);
        videoView.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        videoView.setShowBuffering(showBuffering);
        addView(videoView, matchParent());
        danmakuView = new DanmakuView(context);
        danmakuView.setId(R.id.dmk_view);
        addView(danmakuView, matchParent());
        controller = new MyPlayerControlView(context, attributes);
        controller.setId(R.id.exo_controller);
        addView(controller, matchParent());
        controller.hideImmediately();
        tapOverlay = new YouTubeOverlay(context, attributes);
        tapOverlay.setId(R.id.view_youtube_overlay);
        tapOverlay.setVisibility(GONE);
        addView(tapOverlay, matchParent());
        settingView = new MyPlayerSettingView(context, attributes);
        settingView.setOnCloseListener(
                () -> {
                    requestFocus();
                    controller.restoreActionFocus();
                });
        settingView.setId(R.id.setting_view);
        settingView.setVisibility(GONE);
        addView(settingView, matchParent());
        gestureListener = new DoubleTapGestureListener(this);
        gestureListener.setControls(tapOverlay);
        gestureDetector = new GestureDetector(context, gestureListener);
        tapOverlay.setPlayerView(this);
        tapOverlay.setDoubleTapListener(
                new DoubleTapListener() {
                    public void onAnimationStart() {
                        setUseController(false);
                        tapOverlay.setVisibility(VISIBLE);
                    }

                    public void onAnimationEnd() {
                        setUseController(true);
                        tapOverlay.setVisibility(GONE);
                    }

                    public Boolean shouldForward(Player player, View view, float x) {
                        if (player.getPlaybackState() == Player.STATE_IDLE) {
                            cancelInDoubleTapMode();
                            return null;
                        }
                        return PlayerUiLogic.seekDirection(
                                player.getPlaybackState(),
                                player.getCurrentPosition(),
                                player.getDuration(),
                                view.getWidth(),
                                x);
                    }

                    public void keepInDoubleTapMode() {
                        MyPlayerView.this.keepInDoubleTapMode();
                    }
                });
        controller.setOnMenuShowImpl(this::showHideSettingView);
        controller.setOnDmEnableChangeImpl(settingView::dmEnableClick);
        View subtitleButton = controller.findViewById(R.id.button_subtitle);
        if (subtitleButton != null)
            subtitleButton.setOnClickListener(
                    view -> {
                        settingView.showHide(true);
                        settingView.showSubtitles();
                    });
        controller.addVisibilityListener(
                (MyPlayerControlView.VisibilityListener)
                        visibility -> {
                            updateContentDescription();
                            if (visibilityListener != null)
                                visibilityListener.onVisibilityChanged(visibility);
                        });
        settingView.setOnPlayerSettingInnerChange(
                new OnPlayerSettingInnerChange() {
                    public void onAspectRatioChange(int ratio) {
                        setResizeMode(ratio);
                    }

                    public void onPlaybackSpeedChange(float speed) {
                        if (player != null) player.setPlaybackSpeed(speed);
                        if (danmakuPlayer != null) danmakuPlayer.updatePlaySpeed(speed);
                        settingView.setCurrentSpeed(speed);
                    }

                    public void onDmEnableChange(boolean enabled) {
                        danmakuConfig.setVisibility(enabled);
                        danmakuConfig.updateVisibility();
                        controller.dmEnableButtonChange(enabled);
                        persistInt("dmSwitch", enabled ? 1 : 0);
                        applyConfig();
                    }

                    public void onDmAllowBottom(boolean enabled) {
                        changeType(4, enabled);
                        getContext()
                                .getSharedPreferences("BLBL", 0)
                                .edit()
                                .putBoolean("dmAllowBottom", enabled)
                                .apply();
                    }

                    public void onDmAllowTop(boolean enabled) {
                        changeType(5, enabled);
                        getContext()
                                .getSharedPreferences("BLBL", 0)
                                .edit()
                                .putBoolean("dmAllowTop", enabled)
                                .apply();
                    }

                    public void onDmAlpha(float alpha) {
                        danmakuConfig.setAlpha(alpha);
                        danmakuConfig.updateRender();
                        getContext()
                                .getSharedPreferences("BLBL", 0)
                                .edit()
                                .putFloat("dmAlpha", alpha)
                                .apply();
                        applyConfig();
                    }

                    public void onDmScreenArea(int area) {
                        danmakuConfig.setScreenPart(PlayerUiLogic.danmakuArea(area));
                        danmakuConfig.updateLayout();
                        persistInt("dmScreenArea", area);
                        applyConfig();
                    }

                    public void onDmSpeed(int speed) {
                        long duration = PlayerUiLogic.danmakuDuration(speed);
                        danmakuConfig.setDurationMs(duration);
                        danmakuConfig.setRollingDurationMs(duration);
                        danmakuConfig.updateLayout();
                        persistInt("dmSpeed", speed);
                        applyConfig();
                    }

                    public void onDmTextSize(int size) {
                        danmakuConfig.setTextSizeScale(PlayerUiLogic.danmakuTextScale(size));
                        danmakuConfig.updateMeasure();
                        persistInt("dmTextSize", size);
                        applyConfig();
                    }
                });
        setupDmConfig();
        updateContentDescription();
    }

    private static FrameLayout.LayoutParams matchParent() {
        return new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    private void persistInt(String key, int value) {
        getContext().getSharedPreferences("BLBL", 0).edit().putInt(key, value).apply();
    }

    private void changeType(int mode, boolean enabled) {
        if (enabled) typeFilter.removeFilterItem(mode);
        else typeFilter.addFilterItem(mode);
        danmakuConfig.updateFilter();
        applyConfig();
    }

    private void setupDmConfig() {
        danmakuConfig.setDataFilter(Collections.singletonList(typeFilter));
        if (settingView.getDmAllowTop()) typeFilter.removeFilterItem(5);
        else typeFilter.addFilterItem(5);
        if (settingView.getDmAllowBottom()) typeFilter.removeFilterItem(4);
        else typeFilter.addFilterItem(4);
        long duration = PlayerUiLogic.danmakuDuration(settingView.getDmSpeedParam());
        danmakuConfig.setDurationMs(duration);
        danmakuConfig.setRollingDurationMs(duration);
        danmakuConfig.setTextSizeScale(
                PlayerUiLogic.danmakuTextScale(settingView.getDmTextScaleParam()));
        danmakuConfig.setAlpha(settingView.getDmAlpha());
        danmakuConfig.setScreenPart(PlayerUiLogic.danmakuArea(settingView.getScreenPartParam()));
        danmakuConfig.setVisibility(settingView.getDmEnable());
        controller.dmEnableButtonChange(settingView.getDmEnable());
        applyConfig();
    }

    private void applyConfig() {
        if (danmakuPlayer != null) danmakuPlayer.updateConfig(danmakuConfig);
    }

    public void initDmPlayer() {
        if (danmakuPlayer != null) danmakuPlayer.release();
        SimpleRenderer plain = new PremiumDanmakuRenderer();
        Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.icon_video_up);
        TypedDanmakuRenderer renderer = new TypedDanmakuRenderer(plain);
        if (icon != null)
            renderer.registerRenderer(
                    DanmakuItemData.DANMAKU_STYLE_ICON_UP, new UpIconRenderer(icon));
        danmakuPlayer = new DanmakuPlayer(renderer, new DataSource());
        danmakuPlayer.bindView(danmakuView);
        setupDmConfig();
    }

    public void addOneDm(DanmakuItemData data) {
        if (danmakuPlayer != null) danmakuPlayer.send(data);
    }

    public void updateDmData(List<DanmakuItemData> data) {
        if (danmakuPlayer != null) danmakuPlayer.updateData(data);
    }

    public void startDm() {
        if (danmakuPlayer != null) danmakuPlayer.start(danmakuConfig);
    }

    public void pauseDm() {
        if (danmakuPlayer != null) danmakuPlayer.pause();
    }

    public void seekDmTo(long position) {
        if (danmakuPlayer != null) danmakuPlayer.seekTo(Math.max(0, position));
    }

    public long getCurrentDmTimeMs() {
        return danmakuPlayer == null ? 0 : danmakuPlayer.getCurrentTimeMs();
    }

    public void setPlayer(Player player) {
        if (this.player != null) this.player.removeListener(playerListener);
        this.player = player;
        videoView.setPlayer(player);
        controller.setPlayer(player);
        tapOverlay.setPlayer(player);
        if (player != null) {
            player.addListener(playerListener);
            bindCustomSurface();
            updatePlaybackSpeedList();
        }
        updateBuffering();
        updateErrorMessage();
        updateControllerVisibility();
    }

    public Player getPlayer() {
        return player;
    }

    public void play() {
        if (player != null) player.play();
    }

    public void pause() {
        if (player != null) player.pause();
    }

    public void onPause() {
        videoView.onPause();
        pauseDm();
    }

    public void onResume() {
        videoView.onResume();
        if (player != null && player.isPlaying()) startDm();
    }

    public void destroy() {
        cancelInDoubleTapMode();
        tapOverlay.f9791L.m();
        tapOverlay.M.f9799G = true;
        if (tapOverlay.M.f9811z != null) tapOverlay.M.f9811z.cancel();
        controller.clearVideoSettingChangeListener();
        tapOverlay.setDoubleTapListener(null);
        tapOverlay.setPlayer(null);
        if (player != null) player.removeListener(playerListener);
        controller.setPlayer(null);
        videoView.setPlayer(null);
        player = null;
        if (danmakuPlayer != null) danmakuPlayer.release();
        danmakuPlayer = null;
        danmakuView.setDanmakuPlayer(null);
        removeAllViews();
    }

    private boolean isDpadKey(int code) {
        return code == KeyEvent.KEYCODE_DPAD_LEFT
                || code == KeyEvent.KEYCODE_DPAD_RIGHT
                || code == KeyEvent.KEYCODE_DPAD_UP
                || code == KeyEvent.KEYCODE_DPAD_DOWN
                || code == KeyEvent.KEYCODE_DPAD_CENTER
                || code == KeyEvent.KEYCODE_ENTER;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP
                && consumeBackKeyUp) {
            consumeBackKeyUp = false;
            return true;
        }
        if (player != null && player.isPlayingAd()) return super.dispatchKeyEvent(event);
        int key = event.getKeyCode();
        if (settingView.isShowing()) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && key == KeyEvent.KEYCODE_BACK) {
                consumeBackKeyUp = true;
                if (event.getRepeatCount() == 0) settingView.onBack();
                return true;
            }
            return settingView.dispatchKeyEvent(event);
        }
        if (key == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
            showHideSettingView(true);
            return true;
        }
        boolean seekKey = key == KeyEvent.KEYCODE_DPAD_LEFT || key == KeyEvent.KEYCODE_DPAD_RIGHT;
        if (gestureListener.isDoubleTapping()) {
            if (seekKey) {
                gestureListener.handleKeyDown(event);
                return true;
            }
            cancelInDoubleTapMode();
        }
        if (useController && isDpadKey(key)) {
            if (!controller.isVisible()) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && !gestureListener.handleKeyDown(event)) {
                    showController();
                    controller.focusButtonByKeyDown(event);
                }
                return true;
            }
            showController();
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && (key == KeyEvent.KEYCODE_DPAD_CENTER || key == KeyEvent.KEYCODE_ENTER)
                    && (!controller.hasFocus() || controller.findFocus() == controller)) {
                controller.focusButtonByKeyDown(event);
                return true;
            }
            if (controller.handleKeyDown(event)) return true;
            if (super.dispatchKeyEvent(event)) return true;
            if (event.getAction() == KeyEvent.ACTION_DOWN) controller.focusButtonByKeyDown(event);
            return true;
        }
        if (dispatchMediaKeyEvent(event)) return true;
        return super.dispatchKeyEvent(event);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        return useController && controller.dispatchMediaKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!doubleTapEnabled) return super.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean performClick() {
        if (useController) {
            if (controller.isVisible()) hideController();
            else showController();
        }
        super.performClick();
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        if (!useController || player == null) return false;
        showController();
        return true;
    }

    public void showController() {
        if (useController) {
            boolean indefinite =
                    player == null
                            || !player.getPlayWhenReady()
                            || player.getPlaybackState() == Player.STATE_IDLE
                            || player.getPlaybackState() == Player.STATE_ENDED;
            controller.setShowTimeoutMs(indefinite ? 0 : controllerShowTimeoutMs);
            controller.show();
        }
    }

    public void setControllerShowTimeoutMs(int milliseconds) {
        controllerShowTimeoutMs = milliseconds;
        if (controller.isFullyVisible()) showController();
    }

    public int getControllerShowTimeoutMs() {
        return controllerShowTimeoutMs;
    }

    public void hideController() {
        controller.hide();
    }

    public boolean isControllerFullyVisible() {
        return controller.isFullyVisible();
    }

    public void setUseController(boolean use) {
        useController = use;
        if (!use) hideController();
        updateContentDescription();
    }

    public boolean getUseController() {
        return useController;
    }

    public void setControllerAutoShow(boolean show) {
        controllerAutoShow = show;
        updateControllerVisibility();
    }

    public boolean getControllerAutoShow() {
        return controllerAutoShow;
    }

    public void setControllerVisibilityListener(MyPlayerControlView.VisibilityListener listener) {
        if (legacyVisibilityListener != null)
            controller.removeVisibilityListener(legacyVisibilityListener);
        legacyVisibilityListener = listener;
        visibilityListener = null;
        if (listener != null) controller.addVisibilityListener(listener);
    }

    public void setControllerVisibilityListener(ControllerVisibilityListener listener) {
        if (legacyVisibilityListener != null)
            controller.removeVisibilityListener(legacyVisibilityListener);
        legacyVisibilityListener = null;
        visibilityListener = listener;
    }

    public void updateControllerVisibility() {
        if (controller.isVisible()
                || (controllerAutoShow
                        && player != null
                        && (!player.getPlayWhenReady()
                                || player.getPlaybackState() == Player.STATE_ENDED)))
            showController();
    }

    public void updateContentDescription() {
        setContentDescription(
                useController
                        ? getResources()
                                .getString(controller.isFullyVisible() ? 0x7f1200a1 : 0x7f1200af)
                        : null);
    }

    public void updateBuffering() {
        videoView.setShowBuffering(showBuffering);
    }

    public void setShowBuffering(int mode) {
        showBuffering = mode;
        updateBuffering();
    }

    public void setCustomErrorMessage(CharSequence message) {
        customErrorMessage = message;
        updateErrorMessage();
    }

    public void updateErrorMessage() {
        videoView.setCustomErrorMessage(customErrorMessage);
    }

    public void updateAspectRatio() {
        videoView.requestLayout();
    }

    public void updateForCurrentTrackSelections(boolean reset) {
        videoView.setKeepContentOnPlayerReset(!reset);
        videoView.requestLayout();
    }

    public void updatePlaybackSpeedList() {
        if (player != null) settingView.setCurrentSpeed(player.getPlaybackParameters().speed);
    }

    public void setResizeMode(int ratio) {
        videoView.setResizeMode(ratio);
        settingView.setCurrentScreenRatio(ratio);
    }

    public int getResizeMode() {
        return videoView.getResizeMode();
    }

    public void changeAspectRatio(int ratio) {
        setResizeMode(ratio);
    }

    public View getVideoSurfaceView() {
        return customVideoSurface == null ? videoView.getVideoSurfaceView() : customVideoSurface;
    }

    public void setVideoSurfaceView(View surface) {
        customVideoSurface = surface;
        if (surface != null) {
            View old = videoView.getVideoSurfaceView();
            ViewGroup parent = old == null ? null : (ViewGroup) old.getParent();
            if (parent != null && surface.getParent() == null) {
                parent.removeView(old);
                parent.addView(surface, 0, new ViewGroup.LayoutParams(-1, -1));
            }
        }
        bindCustomSurface();
    }

    private void bindCustomSurface() {
        if (player != null && customVideoSurface != null) {
            if (customVideoSurface instanceof TextureView)
                player.setVideoTextureView((TextureView) customVideoSurface);
            else if (customVideoSurface instanceof SurfaceView)
                player.setVideoSurfaceView((SurfaceView) customVideoSurface);
        }
    }

    public boolean isDoubleTapEnabled() {
        return doubleTapEnabled;
    }

    public void setDoubleTapEnabled(boolean enabled) {
        doubleTapEnabled = enabled;
    }

    public long getDoubleTapDelay() {
        return gestureListener.getDoubleTapDelay();
    }

    public void setDoubleTapDelay(long delay) {
        gestureListener.setDoubleTapDelay(delay);
    }

    public void keepInDoubleTapMode() {
        gestureListener.keepInDoubleTapMode();
    }

    public void cancelInDoubleTapMode() {
        gestureListener.cancelInDoubleTapMode();
    }

    public void setSeekSecond(int seconds) {
        tapOverlay.setSeekSeconds(seconds);
    }

    public void setOnPlayerSettingChange(OnPlayerSettingChange listener) {
        settingView.setOnPlayerSettingChange(listener);
    }

    public void setOnVideoSettingChangeListener(OnVideoSettingChangeListener listener) {
        controller.setOnVideoSettingChangeListener(listener);
    }

    public void setPremiumMember(boolean premium) {
        settingView.setPremiumMember(premium);
    }

    public void selectAudio(AudioQuality quality) {
        settingView.setCurrentAudioQuality(quality);
    }

    public void selectQuality(VideoQuality quality) {
        settingView.setCurrentVideoQuality(quality);
        controller.setQualityLabel(quality == null ? "" : quality.getShowName());
    }

    public void selectSubtitle(int index) {
        settingView.setCurrentSubtitlePosition(index);
    }

    public void selectVideoCodec(VideoCodecEnum codec) {
        settingView.setCurrentVideoCodec(codec);
    }

    public void setAudiosSelect(List<? extends AudioQuality> qualities) {
        settingView.setAudioQualities(qualities);
    }

    public void setQualities(List<? extends VideoQuality> qualities) {
        settingView.setVideoQualities(qualities);
    }

    public void setSubtitles(List<SubtitleInfoModel> subtitles) {
        settingView.setSubtitles(subtitles);
    }

    public void setVideoCodec(List<? extends VideoCodecEnum> codecs) {
        settingView.setVideoCodecs(codecs);
    }

    public void setPlaySpeed(float speed) {
        settingView.setCurrentSpeed(speed);
    }

    public void setTitle(String title) {
        controller.setTitle(title);
    }

    public void setSubTitle(String title) {
        controller.setSubTitle(title);
    }

    public void setShowMultiWindowTimeBar(boolean show) {
        controller.setShowMultiWindowTimeBar(show);
    }

    public void setShowHideOwnerInfo(boolean show) {
        controller.setShowHideOwnerButton(show);
    }

    public void showHideActionButton(boolean show) {
        controller.showHideActionButton(show);
    }

    public void showHideDmSwitchButton(boolean show) {
        controller.showHideDmSwitchButton(show);
    }

    public void showHideEpisodeButton(boolean show) {
        controller.showHideEpisodeButton(show);
    }

    public void showHideFfRe(boolean show) {
        controller.showHideFfRe(show);
    }

    public void showHideLiveSettingButton(boolean show) {
        controller.showHideLiveSettingButton(show);
    }

    public void showHideNextPrevious(boolean show) {
        controller.showHideNextPrevious(show);
    }

    public void showHideRelatedButton(boolean show) {
        controller.showHideRelatedButton(show);
    }

    public void showHideRepeatButton(boolean show) {
        controller.showHideRepeatButton(show);
    }

    public void showHideSubtitleButton(boolean show) {
        controller.showHideSubtitleButton(show);
    }

    public void showSettingButton(boolean show) {
        controller.showSettingButton(show);
    }

    public boolean dismissSettingsOnBack() {
        if (!settingView.isShowing()) return false;
        settingView.onBack();
        return true;
    }

    public void showHideSettingView(boolean show) {
        settingView.showHide(show);
    }

    public MyPlayerControlView getAppController() {
        return controller;
    }

    public static final class DoubleTapGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        public static final Companion Companion = new Companion(null);
        private static final String TAG = ".DTGListener";
        private YouTubeOverlay controls;
        private long doubleTapDelay;
        private boolean isDoubleTapping;
        private final Handler mHandler;
        private final Runnable mRunnable;
        private final View rootView;

        public static final class Companion {
            private Companion() {}

            public /* synthetic */ Companion(kotlin.jvm.internal.DefaultConstructorMarker dVar) {
                this();
            }
        }

        public DoubleTapGestureListener(View rootView) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(rootView, "rootView");
            this.rootView = rootView;
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mRunnable = () -> this.isDoubleTapping = false;
            this.doubleTapDelay = 700L;
        }

        public static final void mRunnable$lambda$0(DoubleTapGestureListener this$0) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.isDoubleTapping = false;
        }

        public final void cancelInDoubleTapMode() {
            this.mHandler.removeCallbacks(this.mRunnable);
            this.isDoubleTapping = false;
        }

        public final void firstDoubleTap(float f7, float f8) {
            YouTubeOverlay youTubeOverlay;
            Player g3;
            View myPlayerView;
            DoubleTapListener anonymousClass4;
            if (this.isDoubleTapping) {
                return;
            }
            this.isDoubleTapping = true;
            keepInDoubleTapMode();
            YouTubeOverlay aVar = this.controls;
            if (aVar == null
                    || (g3 = (youTubeOverlay = (YouTubeOverlay) aVar).f9794P) == null
                    || (myPlayerView = youTubeOverlay.f9793O) == null
                    || (anonymousClass4 = youTubeOverlay.f9795Q) == null) {
                return;
            }
            kotlin.jvm.internal.Intrinsics.checkNotNull(myPlayerView);
            anonymousClass4.shouldForward(g3, myPlayerView, f7);
        }

        public final YouTubeOverlay getControls() {
            return this.controls;
        }

        public final long getDoubleTapDelay() {
            return this.doubleTapDelay;
        }

        public final boolean handleKeyDown(KeyEvent event) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(event, "event");
            if (event.getAction() != 0) {
                return false;
            }
            if (event.getKeyCode() == 22) {
                secondDoubleTap(
                        (this.rootView.getWidth() / 4.0f) * 3, this.rootView.getHeight() / 2.0f);
                return true;
            }
            if (event.getKeyCode() != 21) {
                return false;
            }
            secondDoubleTap(this.rootView.getWidth() / 4.0f, this.rootView.getHeight() / 2.0f);
            return true;
        }

        public final boolean isDoubleTapping() {
            return this.isDoubleTapping;
        }

        public final void keepInDoubleTapMode() {
            this.isDoubleTapping = true;
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, this.doubleTapDelay);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(e, "e");
            firstDoubleTap(e.getX(), e.getY());
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(e, "e");
            if (e.getActionMasked() != 1 || !this.isDoubleTapping) {
                return super.onDoubleTapEvent(e);
            }
            secondDoubleTap(e.getX(), e.getY());
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(e, "e");
            if (!this.isDoubleTapping) {
                return super.onDown(e);
            }
            if (this.controls == null) {
                return true;
            }
            e.getX();
            e.getY();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(e, "e");
            if (this.isDoubleTapping) {
                return true;
            }
            return this.rootView.performClick();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(e, "e");
            if (!this.isDoubleTapping) {
                return super.onSingleTapUp(e);
            }
            singleTapUp(e.getX(), e.getY());
            return true;
        }

        public final void secondDoubleTap(float f7, float f8) {
            YouTubeOverlay aVar = this.controls;
            if (aVar != null) {
                ((YouTubeOverlay) aVar).n(f7, f8);
            }
        }

        public final void setControls(YouTubeOverlay aVar) {
            this.controls = aVar;
        }

        public final void setDoubleTapDelay(long j7) {
            this.doubleTapDelay = j7;
        }

        public final void setDoubleTapping(boolean z7) {
            this.isDoubleTapping = z7;
        }

        public final void singleTapUp(float f7, float f8) {
            YouTubeOverlay aVar = this.controls;
            if (aVar != null) {
                ((YouTubeOverlay) aVar).n(f7, f8);
            }
        }
    }
}
