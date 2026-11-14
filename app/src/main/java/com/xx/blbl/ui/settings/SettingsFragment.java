package com.xx.blbl.ui.settings;

import android.view.View;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.SettingModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.CacheMaintenance;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.adapter.DividerDecoration;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenResult;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

public final class SettingsFragment extends BaseScreenFragment implements View.OnClickListener {
    public LinearLayoutCompat f9543I0;
    public LinearLayoutCompat J0;
    public LinearLayoutCompat f9544K0;
    public LinearLayoutCompat f9545L0;
    public LinearLayoutCompat M0;
    public RecyclerView f9546N0;
    public LinearLayoutCompat O0;
    public ScreenContentAdapter f9547P0;
    public int f9548Q0;
    public List f9549R0;
    public List f9550S0;
    public List T0;
    public final PreferenceRowHandler f9554X0;
    public final PlaybackPreferenceHandler f9555Y0;
    public final AppState f9541G0 = AppController.state();
    public final Lazy<NetworkManager> f9542H0 = LazyKt.lazy(AppController::network);
    public final ArrayList f9551U0 = new ArrayList();
    public final PreferenceRowHandler f9552V0 = new PreferenceRowHandler(this, 0);
    public final PlaybackPreferenceHandler f9553W0 = new PlaybackPreferenceHandler(this, 2);

    public SettingsFragment() {
        int i4 = 1;
        this.f9554X0 = new PreferenceRowHandler(this, i4);
        this.f9555Y0 = new PlaybackPreferenceHandler(this, i4);
    }

    public static final void j0(SettingsFragment kVar, int i4, int i7, String text) {
        if (i4 == 0) {
            List list = kVar.f9549R0;
            if (list == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "dataSourceCommon");
                throw null;
            }
            ((SettingModel) list.get(i7)).setInfo(text);
        } else if (i4 == 1) {
            List list2 = kVar.f9550S0;
            if (list2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "dataSourcePlayer");
                throw null;
            }
            ((SettingModel) list2.get(i7)).setInfo(text);
        } else if (i4 == 2) {
            List list3 = kVar.T0;
            if (list3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "dataSourceDm");
                throw null;
            }
            ((SettingModel) list3.get(i7)).setInfo(text);
        } else if (i4 == 4) {
            ((SettingModel) kVar.f9551U0.get(i7)).setInfo(text);
        }
        if (kVar.f9548Q0 == i4) {
            ScreenContentAdapter jVar = kVar.f9547P0;
            if (jVar == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(text, "text");
            if (i7 >= 0) {
                ArrayList arrayList = (ArrayList) jVar.f10356b;
                if (i7 < arrayList.size()) {
                    ((SettingModel) arrayList.get(i7)).setInfo(text);
                    jVar.notifyItemChanged(i7);
                }
            }
        }
    }

    @Override
    public final int layoutResource() {
        return R.layout.fragment_settings;
    }

    @Override
    public final void bindScreen(View view) {
        String strN = stringResource(R.string.setting);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN, "getString(...)");
        setScreenTitle(strN);
        View viewFindViewById = view.findViewById(R.id.recyclerViewSetting);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById, "findViewById(...)");
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        this.f9546N0 = recyclerView;
        view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ScreenContentAdapter jVar = new ScreenContentAdapter(0);
        this.f9547P0 = jVar;
        RecyclerView recyclerView2 = this.f9546N0;
        if (recyclerView2 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerViewSettings");
            throw null;
        }
        recyclerView2.setAdapter(jVar);
        RecyclerView recyclerView3 = this.f9546N0;
        if (recyclerView3 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "recyclerViewSettings");
            throw null;
        }
        recyclerView3.setItemAnimator(null);
        String strN2 = stringResource(R.string.clear_cache);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN2, "getString(...)");
        SettingModel settingModel = new SettingModel(strN2, "0.0kb");
        String strN3 = stringResource(R.string.cache_limit);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN3, "getString(...)");
        SettingModel settingModel2 = new SettingModel(strN3, "不限制");
        String strN4 = stringResource(R.string.check_version);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN4, "getString(...)");
        SettingModel settingModel3 = new SettingModel(strN4, "1.5.2");
        String strN5 = stringResource(R.string.default_start_page);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN5, "getString(...)");
        SettingModel settingModel4 = new SettingModel(strN5, "热门");
        String strN6 = stringResource(R.string.image_quality);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN6, "getString(...)");
        SettingModel settingModel5 = new SettingModel(strN6, "中尺寸");
        String strN7 = stringResource(R.string.theme);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN7, "getString(...)");
        SettingModel settingModel6 = new SettingModel(strN7, "黑色");
        String strN8 = stringResource(R.string.fullscreen_app);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN8, "getString(...)");
        this.f9549R0 =
                java.util.Arrays.asList(
                        settingModel,
                        settingModel2,
                        settingModel3,
                        settingModel4,
                        settingModel5,
                        settingModel6,
                        new SettingModel(strN8, "开"));
        String strN9 = stringResource(R.string.default_video_quality);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN9, "getString(...)");
        SettingModel settingModel7 = new SettingModel(strN9, "1080P");
        String strN10 = stringResource(R.string.default_audio_track);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN10, "getString(...)");
        SettingModel settingModel8 = new SettingModel(strN10, "192kbps");
        String strN11 = stringResource(R.string.default_play_speed);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN11, "getString(...)");
        SettingModel settingModel9 = new SettingModel(strN11, "1.0");
        String strN12 = stringResource(R.string.after_play);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN12, "getString(...)");
        SettingModel settingModel10 = new SettingModel(strN12, "开");
        String strN13 = stringResource(R.string.play_finish_exit_player);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN13, "getString(...)");
        SettingModel settingModel11 = new SettingModel(strN13, "开");
        String strN14 = stringResource(R.string.show_re_ff);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN14, "getString(...)");
        SettingModel settingModel12 = new SettingModel(strN14, "关");
        String strN15 = stringResource(R.string.video_codec);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN15, "getString(...)");
        SettingModel settingModel13 = new SettingModel(strN15, "AVC");
        String strN16 = stringResource(R.string.show_subtitle_default);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN16, "getString(...)");
        SettingModel settingModel14 = new SettingModel(strN16, "关");
        String strN17 = stringResource(R.string.subtitle_text_size);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN17, "getString(...)");
        SettingModel settingModel15 = new SettingModel(strN17, "40");
        String strN18 = stringResource(R.string.show_debug);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN18, "getString(...)");
        SettingModel settingModel16 = new SettingModel(strN18, "关");
        String strN19 = stringResource(R.string.show_video_detail);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN19, "getString(...)");
        SettingModel settingModel17 = new SettingModel(strN19, "关");
        String strN20 = stringResource(R.string.show_bottom_progress_bar);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN20, "getString(...)");
        SettingModel settingModel18 = new SettingModel(strN20, "关");
        String strN21 = stringResource(R.string.simple_key_press);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN21, "getString(...)");
        SettingModel settingModel19 = new SettingModel(strN21, "关");
        String strN22 = stringResource(R.string.give_coin_number);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN22, "getString(...)");
        SettingModel settingModel20 = new SettingModel(strN22, "2");
        String strN23 = stringResource(R.string.show_next_previous);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN23, "getString(...)");
        SettingModel settingModel21 = new SettingModel(strN23, "关");
        String strN24 = stringResource(R.string.show_dm_switch);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN24, "getString(...)");
        SettingModel settingModel22 = new SettingModel(strN24, "关");
        String strN25 = stringResource(R.string.ff_seek_second);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN25, "getString(...)");
        this.f9550S0 =
                java.util.Arrays.asList(
                        settingModel7,
                        settingModel8,
                        settingModel9,
                        settingModel10,
                        settingModel11,
                        settingModel12,
                        settingModel13,
                        settingModel14,
                        settingModel15,
                        settingModel16,
                        settingModel17,
                        settingModel18,
                        settingModel19,
                        settingModel20,
                        settingModel21,
                        settingModel22,
                        new SettingModel(strN25, "10s"));
        String strN26 = stringResource(R.string.dm_switch);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN26, "getString(...)");
        SettingModel settingModel23 = new SettingModel(strN26, "开");
        String strN27 = stringResource(R.string.dm_alpha);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN27, "getString(...)");
        SettingModel settingModel24 = new SettingModel(strN27, "0.8");
        String strN28 = stringResource(R.string.dm_text_size);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN28, "getString(...)");
        SettingModel settingModel25 = new SettingModel(strN28, "中号");
        String strN29 = stringResource(R.string.dm_screen_area);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN29, "getString(...)");
        SettingModel settingModel26 = new SettingModel(strN29, "1/2");
        String strN30 = stringResource(R.string.dm_speed);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN30, "getString(...)");
        SettingModel settingModel27 = new SettingModel(strN30, "4");
        String strN31 = stringResource(R.string.dm_allow_top);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN31, "getString(...)");
        SettingModel settingModel28 = new SettingModel(strN31, "关");
        String strN32 = stringResource(R.string.dm_allow_bottom);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN32, "getString(...)");
        SettingModel settingModel29 = new SettingModel(strN32, "关");
        String strN33 = stringResource(R.string.dm_filter_weight);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN33, "getString(...)");
        SettingModel settingModel30 = new SettingModel(strN33, "关");
        String strN34 = stringResource(R.string.allow_vip_colorful_dm);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strN34, "getString(...)");
        this.T0 =
                java.util.Arrays.asList(
                        settingModel23,
                        settingModel24,
                        settingModel25,
                        settingModel26,
                        settingModel27,
                        settingModel28,
                        settingModel29,
                        settingModel30,
                        new SettingModel(strN34, "关"));
        ScreenContentAdapter jVar2 = this.f9547P0;
        if (jVar2 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
            throw null;
        }
        List list = this.f9549R0;
        if (list == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "dataSourceCommon");
            throw null;
        }
        jVar2.b(list);
        ScreenContentAdapter jVar3 = this.f9547P0;
        if (jVar3 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
            throw null;
        }
        jVar3.f10357c = this.f9552V0;
        View viewFindViewById2 = view.findViewById(R.id.container_about);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById2, "findViewById(...)");
        this.O0 = (LinearLayoutCompat) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.button_setting_common);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById3, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) viewFindViewById3;
        this.f9543I0 = linearLayoutCompat;
        linearLayoutCompat.setOnClickListener(this);
        LinearLayoutCompat linearLayoutCompat2 = this.f9543I0;
        if (linearLayoutCompat2 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "buttonCategoryCommon");
            throw null;
        }
        linearLayoutCompat2.setSelected(true);
        View viewFindViewById4 = view.findViewById(R.id.button_setting_play);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById4, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat3 = (LinearLayoutCompat) viewFindViewById4;
        this.J0 = linearLayoutCompat3;
        linearLayoutCompat3.setOnClickListener(this);
        View viewFindViewById5 = view.findViewById(R.id.button_setting_dm);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById5, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat4 = (LinearLayoutCompat) viewFindViewById5;
        this.f9544K0 = linearLayoutCompat4;
        linearLayoutCompat4.setOnClickListener(this);
        View viewFindViewById6 = view.findViewById(R.id.button_setting_about);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById6, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat5 = (LinearLayoutCompat) viewFindViewById6;
        this.f9545L0 = linearLayoutCompat5;
        linearLayoutCompat5.setOnClickListener(this);
        View viewFindViewById7 = view.findViewById(R.id.button_setting_device);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                viewFindViewById7, "findViewById(...)");
        LinearLayoutCompat linearLayoutCompat6 = (LinearLayoutCompat) viewFindViewById7;
        this.M0 = linearLayoutCompat6;
        linearLayoutCompat6.setOnClickListener(this);
        try {
            SettingsSnapshot.populate(this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void onClick(View view) {
        LinearLayoutCompat linearLayoutCompat = this.f9543I0;
        if (linearLayoutCompat == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "buttonCategoryCommon");
            throw null;
        }
        if (java.util.Objects.equals(view, linearLayoutCompat)) {
            RecyclerView recyclerView = this.f9546N0;
            if (recyclerView == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "recyclerViewSettings");
                throw null;
            }
            recyclerView.setVisibility(0);
            LinearLayoutCompat linearLayoutCompat2 = this.O0;
            if (linearLayoutCompat2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewAbout");
                throw null;
            }
            linearLayoutCompat2.setVisibility(8);
            this.f9548Q0 = 0;
            ScreenContentAdapter jVar = this.f9547P0;
            if (jVar == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            List list = this.f9549R0;
            if (list == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "dataSourceCommon");
                throw null;
            }
            jVar.b(list);
            ScreenContentAdapter jVar2 = this.f9547P0;
            if (jVar2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            jVar2.f10357c = this.f9552V0;
            LinearLayoutCompat linearLayoutCompat3 = this.f9543I0;
            if (linearLayoutCompat3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryCommon");
                throw null;
            }
            linearLayoutCompat3.setSelected(true);
            LinearLayoutCompat linearLayoutCompat4 = this.J0;
            if (linearLayoutCompat4 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryPlayer");
                throw null;
            }
            linearLayoutCompat4.setSelected(false);
            LinearLayoutCompat linearLayoutCompat5 = this.f9544K0;
            if (linearLayoutCompat5 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDm");
                throw null;
            }
            linearLayoutCompat5.setSelected(false);
            LinearLayoutCompat linearLayoutCompat6 = this.f9545L0;
            if (linearLayoutCompat6 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryAbout");
                throw null;
            }
            linearLayoutCompat6.setSelected(false);
            LinearLayoutCompat linearLayoutCompat7 = this.M0;
            if (linearLayoutCompat7 != null) {
                linearLayoutCompat7.setSelected(false);
                return;
            } else {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDeviceInfo");
                throw null;
            }
        }
        LinearLayoutCompat linearLayoutCompat8 = this.J0;
        if (linearLayoutCompat8 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "buttonCategoryPlayer");
            throw null;
        }
        if (java.util.Objects.equals(view, linearLayoutCompat8)) {
            RecyclerView recyclerView2 = this.f9546N0;
            if (recyclerView2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "recyclerViewSettings");
                throw null;
            }
            recyclerView2.setVisibility(0);
            LinearLayoutCompat linearLayoutCompat9 = this.O0;
            if (linearLayoutCompat9 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewAbout");
                throw null;
            }
            linearLayoutCompat9.setVisibility(8);
            this.f9548Q0 = 1;
            ScreenContentAdapter jVar3 = this.f9547P0;
            if (jVar3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            List list2 = this.f9550S0;
            if (list2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "dataSourcePlayer");
                throw null;
            }
            jVar3.b(list2);
            ScreenContentAdapter jVar4 = this.f9547P0;
            if (jVar4 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            jVar4.f10357c = this.f9553W0;
            LinearLayoutCompat linearLayoutCompat10 = this.f9543I0;
            if (linearLayoutCompat10 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryCommon");
                throw null;
            }
            linearLayoutCompat10.setSelected(false);
            LinearLayoutCompat linearLayoutCompat11 = this.J0;
            if (linearLayoutCompat11 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryPlayer");
                throw null;
            }
            linearLayoutCompat11.setSelected(true);
            LinearLayoutCompat linearLayoutCompat12 = this.f9544K0;
            if (linearLayoutCompat12 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDm");
                throw null;
            }
            linearLayoutCompat12.setSelected(false);
            LinearLayoutCompat linearLayoutCompat13 = this.f9545L0;
            if (linearLayoutCompat13 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryAbout");
                throw null;
            }
            linearLayoutCompat13.setSelected(false);
            LinearLayoutCompat linearLayoutCompat14 = this.M0;
            if (linearLayoutCompat14 != null) {
                linearLayoutCompat14.setSelected(false);
                return;
            } else {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDeviceInfo");
                throw null;
            }
        }
        LinearLayoutCompat linearLayoutCompat15 = this.f9544K0;
        if (linearLayoutCompat15 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "buttonCategoryDm");
            throw null;
        }
        if (java.util.Objects.equals(view, linearLayoutCompat15)) {
            RecyclerView recyclerView3 = this.f9546N0;
            if (recyclerView3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "recyclerViewSettings");
                throw null;
            }
            recyclerView3.setVisibility(0);
            LinearLayoutCompat linearLayoutCompat16 = this.O0;
            if (linearLayoutCompat16 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewAbout");
                throw null;
            }
            linearLayoutCompat16.setVisibility(8);
            this.f9548Q0 = 2;
            ScreenContentAdapter jVar5 = this.f9547P0;
            if (jVar5 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            List list3 = this.T0;
            if (list3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "dataSourceDm");
                throw null;
            }
            jVar5.b(list3);
            ScreenContentAdapter jVar6 = this.f9547P0;
            if (jVar6 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            jVar6.f10357c = this.f9554X0;
            LinearLayoutCompat linearLayoutCompat17 = this.f9543I0;
            if (linearLayoutCompat17 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryCommon");
                throw null;
            }
            linearLayoutCompat17.setSelected(false);
            LinearLayoutCompat linearLayoutCompat18 = this.J0;
            if (linearLayoutCompat18 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryPlayer");
                throw null;
            }
            linearLayoutCompat18.setSelected(false);
            LinearLayoutCompat linearLayoutCompat19 = this.f9544K0;
            if (linearLayoutCompat19 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDm");
                throw null;
            }
            linearLayoutCompat19.setSelected(true);
            LinearLayoutCompat linearLayoutCompat20 = this.f9545L0;
            if (linearLayoutCompat20 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryAbout");
                throw null;
            }
            linearLayoutCompat20.setSelected(false);
            LinearLayoutCompat linearLayoutCompat21 = this.M0;
            if (linearLayoutCompat21 != null) {
                linearLayoutCompat21.setSelected(false);
                return;
            } else {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDeviceInfo");
                throw null;
            }
        }
        LinearLayoutCompat linearLayoutCompat22 = this.f9545L0;
        if (linearLayoutCompat22 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "buttonCategoryAbout");
            throw null;
        }
        if (java.util.Objects.equals(view, linearLayoutCompat22)) {
            RecyclerView recyclerView4 = this.f9546N0;
            if (recyclerView4 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "recyclerViewSettings");
                throw null;
            }
            recyclerView4.setVisibility(8);
            LinearLayoutCompat linearLayoutCompat23 = this.O0;
            if (linearLayoutCompat23 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewAbout");
                throw null;
            }
            linearLayoutCompat23.setVisibility(0);
            LinearLayoutCompat linearLayoutCompat24 = this.f9543I0;
            if (linearLayoutCompat24 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryCommon");
                throw null;
            }
            linearLayoutCompat24.setSelected(false);
            LinearLayoutCompat linearLayoutCompat25 = this.J0;
            if (linearLayoutCompat25 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryPlayer");
                throw null;
            }
            linearLayoutCompat25.setSelected(false);
            LinearLayoutCompat linearLayoutCompat26 = this.f9544K0;
            if (linearLayoutCompat26 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDm");
                throw null;
            }
            linearLayoutCompat26.setSelected(false);
            LinearLayoutCompat linearLayoutCompat27 = this.f9545L0;
            if (linearLayoutCompat27 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryAbout");
                throw null;
            }
            linearLayoutCompat27.setSelected(true);
            LinearLayoutCompat linearLayoutCompat28 = this.M0;
            if (linearLayoutCompat28 != null) {
                linearLayoutCompat28.setSelected(false);
                return;
            } else {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDeviceInfo");
                throw null;
            }
        }
        LinearLayoutCompat linearLayoutCompat29 = this.M0;
        if (linearLayoutCompat29 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                    "buttonCategoryDeviceInfo");
            throw null;
        }
        if (java.util.Objects.equals(view, linearLayoutCompat29)) {
            RecyclerView recyclerView5 = this.f9546N0;
            if (recyclerView5 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "recyclerViewSettings");
                throw null;
            }
            recyclerView5.setVisibility(0);
            LinearLayoutCompat linearLayoutCompat30 = this.O0;
            if (linearLayoutCompat30 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "viewAbout");
                throw null;
            }
            linearLayoutCompat30.setVisibility(8);
            ScreenContentAdapter jVar7 = this.f9547P0;
            if (jVar7 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            jVar7.b(this.f9551U0);
            ScreenContentAdapter jVar8 = this.f9547P0;
            if (jVar8 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            jVar8.f10357c = this.f9555Y0;
            LinearLayoutCompat linearLayoutCompat31 = this.f9543I0;
            if (linearLayoutCompat31 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryCommon");
                throw null;
            }
            linearLayoutCompat31.setSelected(false);
            LinearLayoutCompat linearLayoutCompat32 = this.J0;
            if (linearLayoutCompat32 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryPlayer");
                throw null;
            }
            linearLayoutCompat32.setSelected(false);
            LinearLayoutCompat linearLayoutCompat33 = this.f9544K0;
            if (linearLayoutCompat33 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDm");
                throw null;
            }
            linearLayoutCompat33.setSelected(false);
            LinearLayoutCompat linearLayoutCompat34 = this.f9545L0;
            if (linearLayoutCompat34 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryAbout");
                throw null;
            }
            linearLayoutCompat34.setSelected(false);
            LinearLayoutCompat linearLayoutCompat35 = this.M0;
            if (linearLayoutCompat35 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                        "buttonCategoryDeviceInfo");
                throw null;
            }
            linearLayoutCompat35.setSelected(true);
            f9548Q0 = 4;
            PlaybackPreferenceHandler result = new PlaybackPreferenceHandler(this, 0);
            f9542H0.getValue()
                    .getZoneInfo(ScreenResult.bind(this, result::onResponse, result::onFailure));
        }
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    public void clearCache() {
        View requesting = this.screenView;
        CacheMaintenance.clear(
                requireContext(),
                () -> {
                    if (requesting != null && requesting == this.screenView)
                        SettingsSnapshot.refreshCache(this);
                });
    }

    public void checkVersion() {
        new TvSettingsDialog.Builder(requireContext())
                .setTitle(R.string.check_version)
                .setMessage("当前版本：" + SettingsSnapshot.version() + "\n此独立构建尚未配置更新源。")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void releaseScreenView() {
        if (f9546N0 != null) f9546N0.setAdapter(null);
        f9543I0 = null;
        J0 = null;
        f9544K0 = null;
        f9545L0 = null;
        M0 = null;
        O0 = null;
        f9546N0 = null;
        f9547P0 = null;
        super.releaseScreenView();
    }
}
