package com.xx.blbl.ui.settings;

import android.view.View;

import com.xx.blbl.R;
import com.xx.blbl.model.StartPageEnum;
import com.xx.blbl.model.dm.DmScreenArea;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.ItemClickCallback;

public final class PreferenceRowHandler implements ItemClickCallback {
    public final /* synthetic */ int f9536a;
    public final /* synthetic */ SettingsFragment f9537b;

    public /* synthetic */ PreferenceRowHandler(SettingsFragment kVar, int i4) {
        this.f9536a = i4;
        this.f9537b = kVar;
    }

    @Override
    public final void onClick(View view, int i4) {
        switch (this.f9536a) {
            case 0:
                if (view != null) {
                    SettingsFragment kVar = this.f9537b;
                    switch (i4) {
                        case 0:
                            MainActivity mainActivity = kVar.activityHost;
                            if (mainActivity != null) {
                                kVar.clearCache();
                            }
                            break;
                        case 1:
                            String[] stringArray =
                                    view.getContext()
                                            .getResources()
                                            .getStringArray(R.array.cache_limits);
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                                    stringArray, "getStringArray(...)");
                            TvSettingsDialog.Builder kVar2 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar2.setTitle(R.string.cache_limit);
                            kVar2.setItems(
                                    stringArray, new PreferenceChoiceHandler(kVar, stringArray, 0));
                            kVar2.show();
                            break;
                        case 2:
                            kVar.checkVersion();
                            break;
                        case 3:
                            String[] strArr = {
                                StartPageEnum.Recommend.getShowName(),
                                StartPageEnum.Hot.getShowName(),
                                StartPageEnum.Category.getShowName(),
                                StartPageEnum.Dynamic.getShowName(),
                                StartPageEnum.History.getShowName(),
                                StartPageEnum.Collection.getShowName(),
                                StartPageEnum.FollowingAnimation.getShowName(),
                                StartPageEnum.FollowingSeries.getShowName(),
                                StartPageEnum.LaterWatch.getShowName()
                            };
                            TvSettingsDialog.Builder kVar3 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar3.setTitle(R.string.default_start_page);
                            kVar3.setItems(strArr, new PreferenceChoiceHandler(kVar, strArr, 1));
                            kVar3.show();
                            break;
                        case 4:
                            String[] stringArray2 =
                                    view.getContext()
                                            .getResources()
                                            .getStringArray(R.array.image_qualities);
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                                    stringArray2, "getStringArray(...)");
                            TvSettingsDialog.Builder kVar4 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar4.setTitle(R.string.image_quality);
                            kVar4.setItems(
                                    stringArray2,
                                    new PreferenceChoiceHandler(kVar, stringArray2, 2));
                            kVar4.show();
                            break;
                        case 5:
                            String[] stringArray3 =
                                    view.getContext().getResources().getStringArray(R.array.themes);
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(
                                    stringArray3, "getStringArray(...)");
                            TvSettingsDialog.Builder kVar5 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar5.setTitle(R.string.theme);
                            kVar5.setItems(
                                    stringArray3,
                                    new PreferenceChoiceHandler(kVar, stringArray3, 3));
                            kVar5.show();
                            break;
                        case 6:
                            String[] strArr2 = {"开", "关"};
                            TvSettingsDialog.Builder kVar6 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar6.setTitle(R.string.fullscreen_app_tip);
                            kVar6.setItems(strArr2, new PreferenceChoiceHandler(kVar, strArr2, 4));
                            kVar6.show();
                            break;
                    }
                }
                break;
            default:
                if (view != null) {
                    SettingsFragment kVar7 = this.f9537b;
                    switch (i4) {
                        case 0:
                            String[] strArr3 = {"开", "关"};
                            TvSettingsDialog.Builder kVar8 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar8.setTitle(R.string.dm_switch);
                            kVar8.setItems(strArr3, new PreferenceChoiceHandler(kVar7, strArr3, 5));
                            kVar8.show();
                            break;
                        case 1:
                            String[] strArr4 = {
                                "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0"
                            };
                            TvSettingsDialog.Builder kVar9 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar9.setTitle(R.string.dm_alpha);
                            kVar9.setItems(strArr4, new PreferenceChoiceHandler(strArr4, kVar7, 6));
                            kVar9.show();
                            break;
                        case 2:
                            String[] strArr5 = {
                                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                                "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
                                "52", "53", "54", "55"
                            };
                            TvSettingsDialog.Builder kVar10 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar10.setTitle(R.string.dm_text_size);
                            kVar10.setItems(
                                    strArr5, new PreferenceChoiceHandler(strArr5, kVar7, 7));
                            kVar10.show();
                            break;
                        case 3:
                            String[] strArr6 = {
                                DmScreenArea.OneEighth.getShowName(),
                                DmScreenArea.OneSixth.getShowName(),
                                DmScreenArea.Quarter.getShowName(),
                                DmScreenArea.Half.getShowName(),
                                DmScreenArea.ThreeQuarter.getShowName(),
                                DmScreenArea.Full.getShowName()
                            };
                            TvSettingsDialog.Builder kVar11 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar11.setTitle(R.string.dm_screen_area);
                            kVar11.setItems(
                                    strArr6, new PreferenceChoiceHandler(kVar7, strArr6, 8));
                            kVar11.show();
                            break;
                        case 4:
                            String[] strArr7 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
                            TvSettingsDialog.Builder kVar12 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar12.setTitle(R.string.dm_speed);
                            kVar12.setItems(
                                    strArr7, new PreferenceChoiceHandler(strArr7, kVar7, 9));
                            kVar12.show();
                            break;
                        case 5:
                            String[] strArr8 = {"开", "关"};
                            TvSettingsDialog.Builder kVar13 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar13.setTitle(R.string.dm_allow_top);
                            kVar13.setItems(
                                    strArr8, new PreferenceChoiceHandler(kVar7, strArr8, 10));
                            kVar13.show();
                            break;
                        case 6:
                            String[] strArr9 = {"开", "关"};
                            TvSettingsDialog.Builder kVar14 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar14.setTitle(R.string.dm_allow_bottom);
                            kVar14.setItems(
                                    strArr9, new PreferenceChoiceHandler(kVar7, strArr9, 11));
                            kVar14.show();
                            break;
                        case 7:
                            String[] strArr10 = {
                                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "关"
                            };
                            TvSettingsDialog.Builder kVar15 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar15.setTitle(R.string.dm_filter_weight_tip);
                            kVar15.setItems(
                                    strArr10, new PreferenceChoiceHandler(strArr10, kVar7, 12));
                            kVar15.show();
                            break;
                        case 8:
                            String[] strArr11 = {"开", "关"};
                            TvSettingsDialog.Builder kVar16 =
                                    new TvSettingsDialog.Builder(
                                            view.getContext(), view);
                            kVar16.setTitle(R.string.allow_vip_colorful_dm);
                            kVar16.setItems(
                                    strArr11, new PreferenceChoiceHandler(kVar7, strArr11, 13));
                            kVar16.show();
                            break;
                    }
                }
                break;
        }
    }
}
