package com.xx.blbl.ui.view.exoplayer;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.ui.DefaultTimeBar;
import androidx.media3.ui.PlayerControlView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;
import com.xx.blbl.model.live.LiveQuality;
import com.xx.blbl.platform.RecyclerFocus;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Published Media3 controls plus the app's remote navigation, actions and live-quality choices. */
public class MyPlayerControlView extends PlayerControlView {
    public interface VisibilityListener extends PlayerControlView.VisibilityListener {}

    public interface ProgressUpdateListener extends PlayerControlView.ProgressUpdateListener {}

    private final List<VisibilityListener> visibilityListeners = new ArrayList<>();
    private final ImageView playPauseButton, dmSwitchButton, repeatButton;
    private final TextView textTitle, textSubTitle;
    private final DefaultTimeBar timeBar;
    private final LiveQualitySelectionAdapter liveQualityAdapter =
            new LiveQualitySelectionAdapter(this);
    private final MyPlayerControlViewLayoutManager appLayoutManager;
    private OnVideoSettingChangeListener videoSettings;
    private OnDmEnableChangeImpl dmEnableChange;
    private OnMenuShowImpl menuShow;
    private PopupWindow settingsWindow;
    private Runnable pendingKeyFocus;
    private boolean showMultiWindowTimeBar, disableRelatedKey;
    private final PlayerFocusNavigator focusNavigator = new PlayerFocusNavigator(this);
    private final Player.Listener appListener =
            new Player.Listener() {
                @Override
                public void onEvents(Player player, Player.Events events) {
                    updatePlayPauseButton();
                    updateNavigation();
                    updateSummary();
                    updateLoadingIndicator();
                }
            };

    public MyPlayerControlView(Context context) {
        this(context, null);
    }

    public MyPlayerControlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPlayerControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, attrs);
    }

    public MyPlayerControlView(
            Context context, AttributeSet attrs, int defStyleAttr, AttributeSet playbackAttrs) {
        this(new ControlAttributes(context, playbackAttrs), attrs, defStyleAttr);
    }

    private static final class ControlAttributes {
        final Context context;
        final AttributeSet playback;
        final XmlResourceParser parser;

        ControlAttributes(Context context, AttributeSet attrs) {
            this.context = new ContextThemeWrapper(context, R.style.RecoveryPlayerControl);
            if (attrs != null) {
                playback = attrs;
                parser = null;
                return;
            }
            parser = context.getResources().getXml(R.layout.my_player_control_view);
            try {
                while (parser.next() != XmlPullParser.START_TAG) {}
                playback = Xml.asAttributeSet(parser);
            } catch (Exception malformed) {
                parser.close();
                throw new IllegalStateException("Cannot read player control attributes", malformed);
            }
        }

        void close() {
            if (parser != null) parser.close();
        }
    }

    private MyPlayerControlView(
            ControlAttributes attributes, AttributeSet attrs, int defStyleAttr) {
        // Media3 only reads the layout attribute when playback attributes are non-null.
        super(attributes.context, attrs, defStyleAttr, attributes.playback);
        attributes.close();
        playPauseButton = findViewById(R.id.button_play);
        dmSwitchButton = findViewById(R.id.button_dm_switch);
        repeatButton = findViewById(R.id.button_repeat);
        textTitle = findViewById(R.id.text_title);
        textSubTitle = findViewById(R.id.text_sub_title);
        timeBar = findViewById(R.id.exo_progress);
        appLayoutManager = new MyPlayerControlViewLayoutManager(this);
        bind(R.id.button_play, view -> dispatchPlayPause(getPlayer()));
        bind(
                R.id.button_previous,
                view -> {
                    if (videoSettings != null) videoSettings.onPrevious();
                });
        bind(
                R.id.button_next,
                view -> {
                    if (videoSettings != null) videoSettings.onNext();
                });
        bind(
                R.id.button_rewind,
                view -> {
                    if (getPlayer() != null) getPlayer().seekBack();
                });
        bind(
                R.id.button_fast_forward,
                view -> {
                    if (getPlayer() != null) getPlayer().seekForward();
                });
        bind(
                R.id.button_dm_switch,
                view -> {
                    if (dmEnableChange != null) dmEnableChange.onEnable();
                });
        bind(
                R.id.exo_settings,
                view -> {
                    if (menuShow != null) menuShow.onShowHide(true);
                });
        bindAction(R.id.button_choose_episode, 0);
        bindAction(R.id.button_up_info, 1);
        bindAction(R.id.button_more, 2);
        bindAction(R.id.button_related, 3);
        bindAction(R.id.button_close, 4);
        bindAction(R.id.button_restart, 5);
        bindAction(R.id.text_title, -1);
        bind(
                R.id.button_repeat,
                view -> {
                    Player player = getPlayer();
                    if (player == null) return;
                    player.setRepeatMode(
                            player.getRepeatMode() == Player.REPEAT_MODE_ONE
                                    ? Player.REPEAT_MODE_OFF
                                    : Player.REPEAT_MODE_ONE);
                    repeatButton.setImageResource(
                            player.getRepeatMode() == Player.REPEAT_MODE_ONE
                                    ? R.drawable.icon_loop_one_play
                                    : R.drawable.icon_order_all_play);
                });
        bind(
                R.id.button_live_settings,
                view -> {
                    appLayoutManager.removeHideCallbacks();
                    displaySettingsWindow(liveQualityAdapter);
                });
        bindFocusHint(findViewById(R.id.button_subtitle));
    }

    private String qualityLabel = "";
    public void setQualityLabel(String label) {
        qualityLabel = label == null ? "" : label;
        updateSummary();
    }
    private void updateSummary() {
        android.widget.TextView summary = findViewById(R.id.tv_player_summary);
        if (summary == null) return;
        float speed = getPlayer() == null ? 1f : getPlayer().getPlaybackParameters().speed;
        summary.setText((qualityLabel.isEmpty() ? "" : qualityLabel + " · ")
                + String.format(java.util.Locale.ROOT, "%.2f", speed).replaceAll("0$", "") + "×");
    }

    private void bind(int id, View.OnClickListener listener) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(listener);
            bindFocusHint(view);
        }
    }

    private void bindFocusHint(View view) {
        if (view == null) return;
        View.OnFocusChangeListener previous = view.getOnFocusChangeListener();
        view.setOnFocusChangeListener((target, focused) -> {
            if (previous != null) previous.onFocusChange(target, focused);
            if (target.getId() != R.id.text_title)
                com.xx.blbl.platform.TvMotion.focus(target, focused);
            android.widget.TextView caption = findViewById(R.id.tv_player_action_text);
            if (focused && caption != null && target.getContentDescription() != null)
                caption.setText(target.getContentDescription());
        });
    }

    private void bindAction(int id, int action) {
        bind(
                id,
                view -> {
                    if (videoSettings != null) videoSettings.onButtonClick(action);
                });
    }

    private void visible(int id, boolean visible) {
        View view = findViewById(id);
        if (view != null) view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPlayer(Player player) {
        Player previous = getPlayer();
        if (previous != null) previous.removeListener(appListener);
        super.setPlayer(player);
        if (player != null) player.addListener(appListener);
        updateAll();
    }

    public void dispatchPlayPause(Player player) {
        if (player == null) return;
        if (player.getPlayWhenReady()) player.pause();
        else {
            if (player.getPlaybackState() == Player.STATE_IDLE) player.prepare();
            if (player.getPlaybackState() == Player.STATE_ENDED) player.seekToDefaultPosition();
            player.play();
        }
        updatePlayPauseButton();
    }

    public void seekToTimeBarPosition(Player player, long position) {
        if (player == null) return;
        if (showMultiWindowTimeBar) {
            Timeline timeline = player.getCurrentTimeline();
            Timeline.Window window = new Timeline.Window();
            for (int index = 0; index < timeline.getWindowCount(); index++) {
                long duration = timeline.getWindow(index, window).getDurationMs();
                if (duration == C.TIME_UNSET
                        || position < duration
                        || index == timeline.getWindowCount() - 1) {
                    player.seekTo(index, position);
                    return;
                }
                position -= duration;
            }
        }
        player.seekTo(position);
    }

    public void updatePlayPauseButton() {
        if (playPauseButton == null) return;
        Player player = getPlayer();
        boolean playing =
                player != null
                        && player.getPlayWhenReady()
                        && player.getPlaybackState() != Player.STATE_IDLE
                        && player.getPlaybackState() != Player.STATE_ENDED;
        playPauseButton.setImageResource(playing ? R.drawable.icon_pause : R.drawable.icon_play);
        playPauseButton.setContentDescription(
                getContext().getString(playing ? R.string.pause : R.string.play));
        if (playPauseButton.hasFocus()) {
            android.widget.TextView caption = findViewById(R.id.tv_player_action_text);
            if (caption != null) caption.setText(playPauseButton.getContentDescription());
        }
        if (repeatButton != null && player != null)
            repeatButton.setImageResource(
                    player.getRepeatMode() == Player.REPEAT_MODE_ONE
                            ? R.drawable.icon_loop_one_play
                            : R.drawable.icon_order_all_play);
    }

    public void updateNavigation() {
        Player player = getPlayer();
        if (timeBar != null)
            timeBar.setEnabled(
                    player != null
                            && player.isCommandAvailable(
                                    Player.COMMAND_SEEK_IN_CURRENT_MEDIA_ITEM));
    }

    public void updateProgress() {
        Player player = getPlayer();
        if (player == null) return;
        long durationMs = player.getContentDuration(), offsetMs = 0;
        Timeline timeline = player.getCurrentTimeline();
        if (showMultiWindowTimeBar
                && timeline.getWindowCount() <= MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR) {
            Timeline.Window window = new Timeline.Window();
            long combined = 0, offset = 0;
            boolean known = !timeline.isEmpty();
            for (int index = 0; index < timeline.getWindowCount(); index++) {
                long length = timeline.getWindow(index, window).getDurationMs();
                if (length == C.TIME_UNSET) {
                    known = false;
                    break;
                }
                combined += length;
                if (index < player.getCurrentMediaItemIndex()) offset += length;
            }
            if (known) {
                durationMs = combined;
                offsetMs = offset;
            }
        }
        TextView position = findViewById(R.id.exo_position),
                duration = findViewById(R.id.exo_duration);
        if (position != null)
            position.setText(
                    PlayerUiLogic.formatSeconds((player.getContentPosition() + offsetMs) / 1000));
        if (duration != null) duration.setText(PlayerUiLogic.formatSeconds(durationMs / 1000));
        if (timeBar != null) {
            timeBar.setDuration(Math.max(0, durationMs));
            timeBar.setPosition(player.getContentPosition() + offsetMs);
            timeBar.setBufferedPosition(player.getContentBufferedPosition() + offsetMs);
        }
    }

    public void updateTimeline() {
        updateProgress();
    }

    public void updatePlaybackSpeedList() {
        /* Speed rows are fully managed by MyPlayerSettingView. */
    }

    private void updateLoadingIndicator() {
        View indicator = findViewById(R.id.loading_progress_bar);
        Player player = getPlayer();
        if (indicator != null) indicator.setVisibility(
                player != null && player.getPlaybackState() == Player.STATE_BUFFERING
                        ? View.VISIBLE : View.GONE);
    }

    public void updateAll() {
        updateLoadingIndicator();
        updatePlayPauseButton();
        updateNavigation();
        updateProgress();
    }

    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter) {
        if (settingsWindow != null) settingsWindow.dismiss();
        RecyclerView menu = new RecyclerView(getContext());
        menu.setLayoutManager(new LinearLayoutManager(getContext()));
        menu.setAdapter(adapter);
        menu.setBackgroundColor(0xff202020);
        settingsWindow =
                new PopupWindow(
                        menu,
                        (int) getResources().getDimension(R.dimen.px650),
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        true);
        settingsWindow.setOnDismissListener(appLayoutManager::resetHideCallbacks);
        settingsWindow.showAsDropDown(findViewById(R.id.button_live_settings));
        RecyclerFocus.request(menu, 0);
    }

    public void addVisibilityListener(VisibilityListener listener) {
        visibilityListeners.add(listener);
        super.addVisibilityListener(listener);
    }

    public void removeVisibilityListener(VisibilityListener listener) {
        visibilityListeners.remove(listener);
        super.removeVisibilityListener(listener);
    }

    public void notifyOnVisibilityChange() {
        for (VisibilityListener listener : new ArrayList<>(visibilityListeners))
            listener.onVisibilityChange(getVisibility());
    }

    public void setProgressUpdateListener(ProgressUpdateListener listener) {
        super.setProgressUpdateListener(listener);
    }

    public void restoreActionFocus() {
        focusNavigator.enter();
    }

    public void requestPlayPauseFocus() {
        if (playPauseButton != null) playPauseButton.requestFocus();
    }

    public void clearVideoSettingChangeListener() {
        if (pendingKeyFocus != null) removeCallbacks(pendingKeyFocus);
        videoSettings = null;
        dmEnableChange = null;
        menuShow = null;
        if (settingsWindow != null) settingsWindow.dismiss();
    }

    public void dmEnableButtonChange(boolean enabled) {
        dmSwitchButton.setImageResource(
                enabled ? R.drawable.icon_dm_enable : R.drawable.icon_dm_disable);
    }

    private void focusForKey(int key) {
        if ((key == KeyEvent.KEYCODE_DPAD_LEFT || key == KeyEvent.KEYCODE_DPAD_RIGHT)
                && timeBar != null) timeBar.requestFocus();
        else if (key == KeyEvent.KEYCODE_DPAD_CENTER || key == KeyEvent.KEYCODE_ENTER)
            requestPlayPauseFocus();
        else focusNavigator.enter();
    }

    public void focusButtonByKeyDown(KeyEvent event) {
        if (event.getAction() != KeyEvent.ACTION_DOWN) return;
        int key = event.getKeyCode();
        if (pendingKeyFocus != null) removeCallbacks(pendingKeyFocus);
        focusForKey(key);
        if ((key == KeyEvent.KEYCODE_DPAD_CENTER || key == KeyEvent.KEYCODE_ENTER)
                && playPauseButton != null) playPauseButton.performClick();
        deferKeyFocus(key);
    }

    private void deferKeyFocus(int key) {
        if (pendingKeyFocus != null) removeCallbacks(pendingKeyFocus);
        // Media3 also has a progress-only state whose action row appears after a show animation.
        // Retry only while focus is still on the player, without replaying the button action.
        pendingKeyFocus =
                () -> {
                    if (isAttachedToWindow()
                            && isVisible()
                            && getParent() instanceof View
                            && ((View) getParent()).isFocused()) focusForKey(key);
                };
        postOnAnimation(pendingKeyFocus);
        postDelayed(pendingKeyFocus, 300);
    }

    public boolean handleKeyDown(KeyEvent event) {
        View live = findViewById(R.id.button_live_settings);
        boolean handled =
                focusNavigator.navigate(
                        event,
                        !disableRelatedKey
                                && (live == null || live.getVisibility() != View.VISIBLE));
        if (handled
                && event.getAction() == KeyEvent.ACTION_DOWN
                && (!hasFocus() || findFocus() == this)) deferKeyFocus(event.getKeyCode());
        return handled;
    }

    public void setDisableRelatedKey(boolean disabled) {
        disableRelatedKey = disabled;
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public OnDmEnableChangeImpl getOnDmEnableChangeImpl() {
        return dmEnableChange;
    }

    public void setOnDmEnableChangeImpl(OnDmEnableChangeImpl listener) {
        dmEnableChange = listener;
    }

    public OnMenuShowImpl getOnMenuShowImpl() {
        return menuShow;
    }

    public void setOnMenuShowImpl(OnMenuShowImpl listener) {
        menuShow = listener;
    }

    public void setOnVideoSettingChangeListener(OnVideoSettingChangeListener listener) {
        videoSettings = listener;
    }

    @Override
    public void setShowMultiWindowTimeBar(boolean show) {
        showMultiWindowTimeBar = show;
        super.setShowMultiWindowTimeBar(show);
    }

    public void setSubTitle(String text) {
        if (textSubTitle != null) textSubTitle.setText(text);
    }

    public void setTitle(String text) {
        if (textTitle != null) textTitle.setText(text);
    }

    public void setShowHideOwnerButton(boolean show) {
        visible(R.id.button_up_info, show);
    }

    public void showHideActionButton(boolean show) {
        visible(R.id.button_more, show);
    }

    public void showHideDmSwitchButton(boolean show) {
        visible(R.id.button_dm_switch, show);
    }

    public void showHideEpisodeButton(boolean show) {
        visible(R.id.button_choose_episode, show);
    }

    public void showHideFfRe(boolean show) {
        visible(R.id.button_rewind, show);
        visible(R.id.button_fast_forward, show);
    }

    public void showHideLiveSettingButton(boolean show) {
        visible(R.id.button_live_settings, show);
        visible(R.id.button_restart, !show);
    }

    public void showHideNextPrevious(boolean show) {
        visible(R.id.button_previous, show);
        visible(R.id.button_next, show);
    }

    public void showHideRelatedButton(boolean show) {
        visible(R.id.button_related, show);
    }

    public void showHideRepeatButton(boolean show) {
        visible(R.id.button_repeat, show);
    }

    public void showHideSubtitleButton(boolean show) {
        visible(R.id.button_subtitle, show);
    }

    public void showSettingButton(boolean show) {
        visible(R.id.exo_settings, show);
    }

    public MyPlayerControlViewLayoutManager getAppLayoutManager() {
        return appLayoutManager;
    }

    public static final class LiveQualitySelectionAdapter
            extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final MyPlayerControlView owner;
        private final List<LiveQuality> qualities =
                Arrays.asList(
                        LiveQuality.Fluent,
                        LiveQuality.StandardDefinition,
                        LiveQuality.HighDefinition);
        private int checkPosition = 2;

        public LiveQualitySelectionAdapter(MyPlayerControlView owner) {
            this.owner = owner;
        }

        @Override
        public int getItemCount() {
            return qualities.size();
        }

        @Override
        public SubSettingViewHolder onCreateViewHolder(ViewGroup parent, int type) {
            return new SubSettingViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(0x7f0d0050, parent, false));
        }

        @Override
        public void onBindViewHolder(SubSettingViewHolder holder, int position) {
            holder.textView.setText(qualities.get(position).getShowName());
            holder.checkView.setVisibility(
                    position == checkPosition ? View.VISIBLE : View.INVISIBLE);
            holder.itemView.setOnClickListener(
                    view -> {
                        int selected = holder.getBindingAdapterPosition();
                        if (selected == RecyclerView.NO_POSITION) return;
                        checkPosition = selected;
                        notifyDataSetChanged();
                        if (owner.videoSettings != null)
                            owner.videoSettings.onLiveQualityChange(selected);
                    });
        }

        public void select(LiveQuality quality) {
            int index = qualities.indexOf(quality);
            if (index >= 0) checkPosition = index;
            notifyDataSetChanged();
        }
    }
}
