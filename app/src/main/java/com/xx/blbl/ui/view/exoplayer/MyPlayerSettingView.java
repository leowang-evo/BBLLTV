package com.xx.blbl.ui.view.exoplayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;
import com.xx.blbl.model.common.PlayerSettingModel;
import com.xx.blbl.model.dm.DmScreenArea;
import com.xx.blbl.model.player.VideoCodecEnum;
import com.xx.blbl.model.subtitle.SubtitleInfoModel;
import com.xx.blbl.model.video.quality.AudioQuality;
import com.xx.blbl.model.video.quality.VideoQuality;
import com.xx.blbl.platform.RecyclerFocus;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/** Player settings pages share one choice renderer and a remote-aware back stack. */
public final class MyPlayerSettingView extends LinearLayoutCompat {
    // Keep the declared API 21 minimum without requiring API 24 java.util.function types.
    private interface ChoiceLabel<T> {
        String apply(T value);
    }

    private interface IndexChoice {
        void accept(int index);
    }

    private interface BooleanChoice {
        void accept(boolean value);
    }

    interface SettingClickListener {
        void onClick(View view, int position);
    }

    private static final List<Float> PLAYBACK_SPEEDS =
            Arrays.asList(.25f, .5f, .75f, 1f, 1.25f, 1.5f, 2f);
    private static final List<Float> DM_ALPHA_VALUES =
            Arrays.asList(.1f, .2f, .3f, .4f, .5f, .6f, .7f, .8f, .9f, 1f);
    private static final List<DmScreenArea> DM_AREA_VALUES =
            Arrays.asList(
                    DmScreenArea.OneEighth,
                    DmScreenArea.OneSixth,
                    DmScreenArea.Quarter,
                    DmScreenArea.Half,
                    DmScreenArea.ThreeQuarter,
                    DmScreenArea.Full);
    private List<? extends AudioQuality> audioQualities;
    private AudioQuality currentAudioQuality = AudioQuality.Q192K;
    private int currentScreenRatio, currentSubtitlePosition;
    private float currentSpeed = 1f, dmAlpha = 1f;
    private VideoCodecEnum currentVideoCodec = VideoCodecEnum.HEVC;
    private VideoQuality currentVideoQuality;
    private boolean dmAllowBottom, dmAllowTop, dmEnable = true, premiumMember, showing;
    private int dmSpeedParam = 4, dmTextScaleParam = 40, screenPartParam = 1, menuLevel = 1;
    private final ArrayList<PlayerSettingModel> mainSettingList = new ArrayList<>();
    private final ArrayList<PlayerSettingModel> dmSettingList = new ArrayList<>();
    private List<SubtitleInfoModel> subtitles;
    private List<? extends VideoCodecEnum> videoCodecs;
    private List<? extends VideoQuality> videoQualities;
    private OnPlayerSettingChange onPlayerSettingChange;
    private OnPlayerSettingInnerChange onPlayerSettingInnerChange;
    private final SettingAdapter mainAdapter = new SettingAdapter();
    private final RecyclerView recyclerView;
    private final AppCompatImageView iconBack;
    private final Deque<MenuPage> parents = new ArrayDeque<>();
    private MenuPage page;
    private Runnable onClose;

    private static final class MenuPage {
        final List<PlayerSettingModel> rows;
        final SettingClickListener listener;
        final int level;
        int focusPosition = 1;

        MenuPage(List<PlayerSettingModel> rows, SettingClickListener listener, int level) {
            this.rows = rows;
            this.listener = listener;
            this.level = level;
        }
    }

    public MyPlayerSettingView(Context context) {
        this(context, null);
    }

    public MyPlayerSettingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPlayerSettingView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        setOrientation(HORIZONTAL);
        setGravity(android.view.Gravity.END);
        View back = LayoutInflater.from(context).inflate(R.layout.view_back, this, false);
        iconBack = back.findViewById(R.id.icon_back);
        back.setOnClickListener(view -> onBack());
        addView(back);
        recyclerView =
                (RecyclerView)
                        LayoutInflater.from(context)
                                .inflate(R.layout.view_recycler_view, this, false);
        recyclerView.setBackgroundResource(R.drawable.tv_panel_bg);
        recyclerView.setItemAnimator(null);
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.px20));
        back.setBackgroundResource(R.drawable.tv_player_button);
        iconBack.setColorFilter(android.graphics.Color.WHITE);
        recyclerView.setNextFocusLeftId(R.id.view_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mainAdapter);
        addView(
                recyclerView,
                new LayoutParams(
                        (int) getResources().getDimension(R.dimen.tv_panel_width),
                        ViewGroup.LayoutParams.MATCH_PARENT));
        mainSettingList.add(row(R.string.setting, "", 0, 1));
        mainSettingList.add(
                row(R.string.video_quality, "1080P", R.drawable.icon_video_play_count, 0));
        mainSettingList.add(row(R.string.playSpeed, "1.0x", R.drawable.icon_setting, 0));
        mainSettingList.add(row(R.string.subtitle, "不可用", R.drawable.icon_subtitles, 0));
        mainSettingList.add(row(R.string.video_codec, "HEVC", R.drawable.icon_setting, 0));
        mainSettingList.add(row(R.string.audioTrack, "192kbps", R.drawable.icon_setting, 0));
        mainSettingList.add(
                row(
                        R.string.screen_ratio,
                        getResources().getStringArray(R.array.screen_ratios)[0],
                        R.drawable.icon_screen_ratio,
                        0));
        mainSettingList.add(row(R.string.dm_setting, "", R.drawable.icon_dm_enable, 0));
        dmSettingList.add(row(R.string.dm_setting, "", 0, 1));
        for (int title :
                new int[] {
                    R.string.dm_switch,
                    R.string.dm_alpha,
                    R.string.dm_text_size,
                    R.string.dm_screen_area,
                    R.string.dm_speed,
                    R.string.dm_allow_top,
                    R.string.dm_allow_bottom
                }) dmSettingList.add(row(title, "", 0, 0));
        for (int index : new int[] {1, 6, 7}) dmSettingList.get(index).setType(3);
        SharedPreferences prefs = context.getSharedPreferences("BLBL", 0);
        setDmEnable(prefs.getInt("dmSwitch", 1) == 1);
        setDmAlpha(prefs.getFloat("dmAlpha", 1f));
        setDmTextScaleParam(prefs.getInt("dmTextSize", 40));
        setScreenPartParam(prefs.getInt("dmScreenArea", 3));
        setDmSpeedParam(prefs.getInt("dmSpeed", 4));
        setDmAllowTop(prefs.getBoolean("dmAllowTop", false));
        setDmAllowBottom(prefs.getBoolean("dmAllowBottom", false));
        render(mainPage());
    }

    private PlayerSettingModel row(int title, String value, int icon, int type) {
        PlayerSettingModel row = new PlayerSettingModel();
        row.setTitle(getContext().getString(title));
        row.setShowValue(value);
        row.setIcon(icon);
        row.setType(type);
        return row;
    }

    private MenuPage mainPage() {
        return new MenuPage(
                mainSettingList,
                (view, position) -> {
                    switch (position) {
                        case 1:
                            showVideoQualityMenu();
                            break;
                        case 2:
                            showPlaybackSpeed();
                            break;
                        case 3:
                            showSubtitles();
                            break;
                        case 4:
                            showVideoCodec();
                            break;
                        case 5:
                            showAudioQuality();
                            break;
                        case 6:
                            showScreenRatio();
                            break;
                        case 7:
                            showDmSetting();
                            break;
                    }
                },
                1);
    }

    private void render(MenuPage next) {
        page = next;
        menuLevel = next.level;
        iconBack.setImageResource(parents.isEmpty() ? R.drawable.icon_close : R.drawable.icon_back);
        mainAdapter.onItemClick =
                (view, position) -> {
                    next.focusPosition = position;
                    next.listener.onClick(view, position);
                };
        mainAdapter.setItems(next.rows);
        if (showing) RecyclerFocus.request(recyclerView, next.focusPosition);
    }

    private void open(MenuPage next) {
        if (page != null) {
            View focus = recyclerView.findFocus();
            RecyclerView.ViewHolder holder =
                    focus == null ? null : recyclerView.findContainingViewHolder(focus);
            if (holder != null && holder.getBindingAdapterPosition() >= 0)
                page.focusPosition = holder.getBindingAdapterPosition();
            parents.push(page);
        }
        render(next);
    }

    private <T> void choices(
            int title,
            List<? extends T> values,
            T selected,
            ChoiceLabel<T> label,
            IndexChoice choose) {
        if (values == null || values.isEmpty()) {
            Toast.makeText(getContext(), "当前视频没有可选项目", Toast.LENGTH_SHORT).show();
            return;
        }
        List<PlayerSettingModel> rows = new ArrayList<>();
        rows.add(row(title, "", 0, 1));
        if (title == R.string.video_quality
                && premiumMember
                && getContext().getSharedPreferences("BLBL", 0).getInt("defaultResolution", 80)
                        == 80)
            rows.get(0).setShowValue(getContext().getString(R.string.more_quality_tip));
        for (T value : values) {
            PlayerSettingModel item = new PlayerSettingModel();
            item.setTitle(label.apply(value));
            item.setType(2);
            item.setCheck(java.util.Objects.equals(value, selected));
            rows.add(item);
        }
        MenuPage next =
                new MenuPage(
                        rows,
                        (view, position) -> {
                            int index = position - 1;
                            if (index < 0 || index >= values.size()) return;
                            choose.accept(index);
                            onBack();
                        },
                        parents.size() + 2);
        int selectedIndex = values.indexOf(selected);
        if (selectedIndex >= 0) next.focusPosition = selectedIndex + 1;
        open(next);
    }

    public void showAudioQuality() {
        choices(
                R.string.audioTrack,
                audioQualities,
                currentAudioQuality,
                AudioQuality::getShowName,
                index -> {
                    setCurrentAudioQuality(audioQualities.get(index));
                    if (onPlayerSettingChange != null) onPlayerSettingChange.onAudioChange(index);
                });
    }

    public void showVideoCodec() {
        choices(
                R.string.video_codec,
                videoCodecs,
                currentVideoCodec,
                VideoCodecEnum::getShowName,
                index -> {
                    setCurrentVideoCodec(videoCodecs.get(index));
                    if (onPlayerSettingChange != null)
                        onPlayerSettingChange.onVideoCodecChange(index);
                });
    }

    public void showVideoQualityMenu() {
        choices(
                R.string.video_quality,
                videoQualities,
                currentVideoQuality,
                VideoQuality::getShowName,
                index -> {
                    setCurrentVideoQuality(videoQualities.get(index));
                    if (onPlayerSettingChange != null)
                        onPlayerSettingChange.onVideoQualityChange(index);
                });
    }

    public void showSubtitles() {
        SubtitleInfoModel selected =
                subtitles != null
                                && currentSubtitlePosition >= 0
                                && currentSubtitlePosition < subtitles.size()
                        ? subtitles.get(currentSubtitlePosition)
                        : null;
        choices(
                R.string.subtitle,
                subtitles,
                selected,
                SubtitleInfoModel::getLan_doc,
                index -> {
                    setCurrentSubtitlePosition(index);
                    if (onPlayerSettingChange != null)
                        onPlayerSettingChange.onSubtitleChange(index);
                });
    }

    public void showPlaybackSpeed() {
        choices(
                R.string.playSpeed,
                PLAYBACK_SPEEDS,
                currentSpeed,
                value -> value + "x",
                index -> {
                    float speed = PLAYBACK_SPEEDS.get(index);
                    setCurrentSpeed(speed);
                    if (onPlayerSettingInnerChange != null)
                        onPlayerSettingInnerChange.onPlaybackSpeedChange(speed);
                });
    }

    public void showScreenRatio() {
        List<String> ratios = Arrays.asList(getResources().getStringArray(R.array.screen_ratios));
        choices(
                R.string.screen_ratio,
                ratios,
                ratios.get(currentScreenRatio),
                value -> value,
                index -> {
                    setCurrentScreenRatio(index);
                    if (onPlayerSettingInnerChange != null)
                        onPlayerSettingInnerChange.onAspectRatioChange(index);
                });
    }

    private void toggle(int title, boolean current, BooleanChoice change) {
        change.accept(!current);
    }

    public void showDmSetting() {
        open(
                new MenuPage(
                        dmSettingList,
                        (view, position) -> {
                            switch (position) {
                                case 1:
                                    toggle(
                                            R.string.dm_switch,
                                            dmEnable,
                                            value -> {
                                                setDmEnable(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmEnableChange(
                                                            value);
                                            });
                                    break;
                                case 2:
                                    choices(
                                            R.string.dm_alpha,
                                            DM_ALPHA_VALUES,
                                            dmAlpha,
                                            String::valueOf,
                                            index -> {
                                                float value = DM_ALPHA_VALUES.get(index);
                                                setDmAlpha(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmAlpha(value);
                                            });
                                    break;
                                case 3:
                                    integerChoices(
                                            R.string.dm_text_size,
                                            30,
                                            55,
                                            dmTextScaleParam,
                                            value -> {
                                                setDmTextScaleParam(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmTextSize(value);
                                            });
                                    break;
                                case 4:
                                    DmScreenArea selected = null;
                                    for (DmScreenArea area : DM_AREA_VALUES)
                                        if (area.getArea() == screenPartParam) selected = area;
                                    choices(
                                            R.string.dm_screen_area,
                                            DM_AREA_VALUES,
                                            selected,
                                            DmScreenArea::getShowName,
                                            index -> {
                                                int value = DM_AREA_VALUES.get(index).getArea();
                                                setScreenPartParam(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmScreenArea(
                                                            value);
                                            });
                                    break;
                                case 5:
                                    integerChoices(
                                            R.string.dm_speed,
                                            1,
                                            9,
                                            dmSpeedParam,
                                            value -> {
                                                setDmSpeedParam(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmSpeed(value);
                                            });
                                    break;
                                case 6:
                                    toggle(
                                            R.string.dm_allow_top,
                                            dmAllowTop,
                                            value -> {
                                                setDmAllowTop(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmAllowTop(value);
                                            });
                                    break;
                                case 7:
                                    toggle(
                                            R.string.dm_allow_bottom,
                                            dmAllowBottom,
                                            value -> {
                                                setDmAllowBottom(value);
                                                if (onPlayerSettingInnerChange != null)
                                                    onPlayerSettingInnerChange.onDmAllowBottom(
                                                            value);
                                            });
                                    break;
                            }
                        },
                        2));
    }

    private void integerChoices(int title, int first, int last, int selected, IndexChoice change) {
        List<Integer> values = new ArrayList<>();
        for (int i = first; i <= last; i++) values.add(i);
        choices(
                title,
                values,
                selected,
                String::valueOf,
                index -> change.accept(values.get(index)));
    }

    public void dmEnableClick() {
        setDmEnable(!dmEnable);
        if (onPlayerSettingInnerChange != null)
            onPlayerSettingInnerChange.onDmEnableChange(dmEnable);
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public boolean isShowing() {
        return showing;
    }

    public void setOnCloseListener(Runnable listener) {
        onClose = listener;
    }

    public void onBack() {
        if (parents.isEmpty()) showHide(false);
        else render(parents.pop());
    }

    private float panelTravel() {
        return getResources().getDimension(R.dimen.tv_panel_width)
                + getResources().getDimension(R.dimen.px150);
    }

    public void showHide(boolean show) {
        animate().setListener(null);
        animate().cancel();
        boolean wasVisible = getVisibility() == VISIBLE;
        showing = show;
        if (show) {
            if (!wasVisible) {
                setTranslationX(panelTravel());
                setAlpha(0f);
            }
            setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
            setVisibility(VISIBLE);
            parents.clear();
            render(mainPage());
            animate().setDuration(180)
                    .translationX(0)
                    .alpha(1)
                    .setListener(
                            new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (showing && !MyPlayerSettingView.this.hasFocus())
                                        RecyclerFocus.request(recyclerView, page.focusPosition);
                                }
                            });
        } else {
            clearFocus();
            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
            animate().setDuration(180)
                    .translationX(panelTravel())
                    .alpha(0)
                    .setListener(
                            new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (!showing) {
                                        setVisibility(GONE);
                                        parents.clear();
                                        if (onClose != null) onClose.run();
                                    }
                                }
                            });
        }
    }

    public final List<AudioQuality> getAudioQualities() {
        return (List<AudioQuality>) (List<?>) this.audioQualities;
    }

    public final AudioQuality getCurrentAudioQuality() {
        return this.currentAudioQuality;
    }

    public final int getCurrentScreenRatio() {
        return this.currentScreenRatio;
    }

    public final float getCurrentSpeed() {
        return this.currentSpeed;
    }

    public final int getCurrentSubtitlePosition() {
        return this.currentSubtitlePosition;
    }

    public final VideoCodecEnum getCurrentVideoCodec() {
        return this.currentVideoCodec;
    }

    public final VideoQuality getCurrentVideoQuality() {
        return this.currentVideoQuality;
    }

    public final boolean getDmAllowBottom() {
        return this.dmAllowBottom;
    }

    public final boolean getDmAllowTop() {
        return this.dmAllowTop;
    }

    public final float getDmAlpha() {
        return this.dmAlpha;
    }

    public final boolean getDmEnable() {
        return this.dmEnable;
    }

    public final int getDmSpeedParam() {
        return this.dmSpeedParam;
    }

    public final int getDmTextScaleParam() {
        return this.dmTextScaleParam;
    }

    public final OnPlayerSettingChange getOnPlayerSettingChange() {
        return this.onPlayerSettingChange;
    }

    public final OnPlayerSettingInnerChange getOnPlayerSettingInnerChange() {
        return this.onPlayerSettingInnerChange;
    }

    public final int getScreenPartParam() {
        return this.screenPartParam;
    }

    public final List<SubtitleInfoModel> getSubtitles() {
        return this.subtitles;
    }

    public final List<VideoCodecEnum> getVideoCodecs() {
        return (List<VideoCodecEnum>) (List<?>) this.videoCodecs;
    }

    public final List<VideoQuality> getVideoQualities() {
        return (List<VideoQuality>) (List<?>) this.videoQualities;
    }

    public final void setAudioQualities(List<? extends AudioQuality> list) {
        this.audioQualities = list;
    }

    public final void setCurrentAudioQuality(AudioQuality value) {
        SettingAdapter gVar;
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(value, "value");
        this.currentAudioQuality = value;
        if (this.mainSettingList.size() > 5) {
            this.mainSettingList.get(5).setShowValue(value.getShowName());
            if (this.menuLevel != 1 || (gVar = this.mainAdapter) == null) {
                return;
            }
            gVar.updateValue(5, value.getShowName());
        }
    }

    public final void setCurrentScreenRatio(int i4) {
        SettingAdapter gVar;
        if (i4 < 0 || i4 >= getResources().getStringArray(R.array.screen_ratios).length) return;
        this.currentScreenRatio = i4;
        String[] stringArray = getResources().getStringArray(R.array.screen_ratios);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                stringArray, "getStringArray(...)");
        if (this.mainSettingList.size() > 6) {
            PlayerSettingModel playerSettingModel = this.mainSettingList.get(6);
            String str = stringArray[i4];
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(str, "get(...)");
            playerSettingModel.setShowValue(str);
            if (this.menuLevel != 1 || (gVar = this.mainAdapter) == null) {
                return;
            }
            String str2 = stringArray[i4];
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
            gVar.updateValue(6, str2);
        }
    }

    public final void setCurrentSpeed(float f7) {
        SettingAdapter gVar;
        this.currentSpeed = f7;
        if (this.mainSettingList.size() > 2) {
            PlayerSettingModel playerSettingModel = MyPlayerSettingView.this.mainSettingList.get(2);
            StringBuilder sb = new StringBuilder();
            sb.append(f7);
            sb.append('x');
            playerSettingModel.setShowValue(sb.toString());
            if (this.menuLevel != 1 || (gVar = this.mainAdapter) == null) {
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f7);
            sb2.append('x');
            gVar.updateValue(2, sb2.toString());
        }
    }

    public final void setCurrentSubtitlePosition(int index) {
        currentSubtitlePosition = index;
        if (mainSettingList.size() <= 3) return;
        String value = subtitles == null || subtitles.isEmpty() ? "不可用"
                : index >= 0 && index < subtitles.size() ? subtitles.get(index).getLan_doc() : "关闭";
        mainSettingList.get(3).setShowValue(value);
        if (menuLevel == 1) mainAdapter.updateValue(3, value);
    }

    public final void setCurrentVideoCodec(VideoCodecEnum value) {
        SettingAdapter gVar;
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(value, "value");
        this.currentVideoCodec = value;
        if (this.mainSettingList.size() > 4) {
            this.mainSettingList.get(4).setShowValue(value.getShowName());
            if (this.menuLevel != 1 || (gVar = this.mainAdapter) == null) {
                return;
            }
            gVar.updateValue(4, value.getShowName());
        }
    }

    public final void setCurrentVideoQuality(VideoQuality videoQuality) {
        SettingAdapter gVar;
        this.currentVideoQuality = videoQuality;
        if (this.mainSettingList.size() <= 1 || videoQuality == null) {
            return;
        }
        this.mainSettingList.get(1).setShowValue(videoQuality.getShowName());
        if (this.menuLevel != 1 || (gVar = this.mainAdapter) == null) {
            return;
        }
        gVar.updateValue(1, videoQuality.getShowName());
    }

    public final void setDmAllowBottom(boolean z7) {
        this.dmAllowBottom = z7;
        if (this.dmSettingList.size() > 7) {
            this.dmSettingList.get(7).setShowValue(z7 ? "开" : "关");
            if (page != null && page.rows == dmSettingList)
                mainAdapter.updateValue(7, z7 ? "开" : "关");
        }
    }

    public final void setDmAllowTop(boolean z7) {
        this.dmAllowTop = z7;
        if (this.dmSettingList.size() > 6) {
            this.dmSettingList.get(6).setShowValue(z7 ? "开" : "关");
            if (page != null && page.rows == dmSettingList)
                mainAdapter.updateValue(6, z7 ? "开" : "关");
        }
    }

    public final void setDmAlpha(float f7) {
        this.dmAlpha = f7;
        if (this.dmSettingList.size() > 2) {
            this.dmSettingList.get(2).setShowValue(String.valueOf(f7));
        }
    }

    public final void setDmEnable(boolean z7) {
        this.dmEnable = z7;
        if (this.dmSettingList.size() > 1) {
            this.dmSettingList.get(1).setShowValue(z7 ? "开" : "关");
            if (page != null && page.rows == dmSettingList)
                mainAdapter.updateValue(1, z7 ? "开" : "关");
        }
    }

    public final void setDmSpeedParam(int i4) {
        this.dmSpeedParam = i4;
        if (this.dmSettingList.size() > 5) {
            this.dmSettingList.get(5).setShowValue(String.valueOf(i4));
        }
    }

    public final void setDmTextScaleParam(int i4) {
        this.dmTextScaleParam = i4;
        if (this.dmSettingList.size() > 3) {
            this.dmSettingList.get(3).setShowValue(String.valueOf(i4));
        }
    }

    public final void setOnPlayerSettingChange(OnPlayerSettingChange onPlayerSettingChange) {
        this.onPlayerSettingChange = onPlayerSettingChange;
    }

    public final void setOnPlayerSettingInnerChange(
            OnPlayerSettingInnerChange onPlayerSettingInnerChange) {
        this.onPlayerSettingInnerChange = onPlayerSettingInnerChange;
    }

    public final void setScreenPartParam(int i4) {
        this.screenPartParam = i4;
        if (this.dmSettingList.size() > 4) {
            for (DmScreenArea dmScreenArea : DmScreenArea.values()) {
                if (dmScreenArea.getArea() == i4) {
                    this.dmSettingList.get(4).setShowValue(dmScreenArea.getShowName());
                }
            }
        }
    }

    public final void setSubtitles(List<SubtitleInfoModel> list) {
        this.subtitles = list;
        setCurrentSubtitlePosition(currentSubtitlePosition);
    }

    public final void setVideoCodecs(List<? extends VideoCodecEnum> list) {
        this.videoCodecs = list;
    }

    public final void setVideoQualities(List<? extends VideoQuality> list) {
        this.videoQualities = list;
    }
}
