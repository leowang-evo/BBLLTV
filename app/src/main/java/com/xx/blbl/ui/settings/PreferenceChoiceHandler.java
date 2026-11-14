package com.xx.blbl.ui.settings;

import android.content.DialogInterface;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.dm.DmScreenArea;
import com.xx.blbl.model.video.quality.VideoQuality;
import com.xx.blbl.ui.MainActivity;

public final /* synthetic */ class PreferenceChoiceHandler
        implements DialogInterface.OnClickListener {
    public final /* synthetic */ int f9533a;
    public final /* synthetic */ SettingsFragment f9534b;
    public final /* synthetic */ String[] f9535c;

    public /* synthetic */ PreferenceChoiceHandler(SettingsFragment kVar, String[] strArr, int i4) {
        this.f9533a = i4;
        this.f9534b = kVar;
        this.f9535c = strArr;
    }

    public /* synthetic */ PreferenceChoiceHandler(String[] strArr, SettingsFragment kVar, int i4) {
        this.f9533a = i4;
        this.f9535c = strArr;
        this.f9534b = kVar;
    }

    @Override
    public final void onClick(DialogInterface dialogInterface, int i4) {
        int i7;
        int code;
        MainActivity mainActivityH;
        switch (this.f9533a) {
            case 0:
                SettingsFragment kVar = this.f9534b;
                String[] strArr = this.f9535c;
                if (i4 == 1) {
                    i7 = 10;
                } else if (i4 == 2) {
                    i7 = 30;
                } else if (i4 != 3) {
                    i7 = i4 != 4 ? 0 : 100;
                } else {
                    i7 = 50;
                }
                String str = strArr[i4];
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                SettingsFragment.j0(kVar, 0, 1, str);
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("cacheLimit", i7)
                        .apply();
                dialogInterface.dismiss();
                break;
            case 1:
                SettingsFragment kVar2 = this.f9534b;
                String[] strArr2 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("defaultStartPage", i4)
                        .apply();
                SettingsFragment.j0(kVar2, 0, 3, strArr2[i4]);
                dialogInterface.dismiss();
                break;
            case 2:
                SettingsFragment kVar3 = this.f9534b;
                String[] strArr3 = this.f9535c;
                kVar3.f9541G0.imageQuality = i4;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("imageQualityLevel", i4)
                        .apply();
                String str2 = strArr3[i4];
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                SettingsFragment.j0(kVar3, 0, 4, str2);
                dialogInterface.dismiss();
                break;
            case 3:
                SettingsFragment themePage = this.f9534b;
                MainActivity themeActivity = themePage.hostActivity();
                android.content.SharedPreferences preferences =
                        AppController.instance().getSharedPreferences("BLBL", 0);
                boolean themeChanged = preferences.getInt("theme", 1) != i4;
                preferences.edit().putInt("theme", i4).apply();
                SettingsFragment.j0(themePage, 0, 5, this.f9535c[i4]);
                dialogInterface.dismiss();
                if (themeActivity != null) {
                    themeActivity.onBackPressed();
                    themeActivity.selectTab(0);
                    if (themeChanged) themeActivity.recreate();
                }
                break;
            case 4:
                SettingsFragment kVar5 = this.f9534b;
                String[] strArr5 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("fullscreenEnable", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar5, 0, 6, strArr5[i4]);
                dialogInterface.dismiss();
                break;
            case 5:
                SettingsFragment kVar6 = this.f9534b;
                String[] strArr6 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("dmSwitch", i4 == 0 ? 1 : 0)
                        .apply();
                SettingsFragment.j0(kVar6, 2, 0, strArr6[i4]);
                dialogInterface.dismiss();
                break;
            case 6:
                String[] strArr7 = this.f9535c;
                SettingsFragment kVar7 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putFloat("dmAlpha", Float.parseFloat(strArr7[i4]))
                        .apply();
                SettingsFragment.j0(kVar7, 2, 1, strArr7[i4]);
                dialogInterface.dismiss();
                break;
            case 7:
                String[] strArr8 = this.f9535c;
                SettingsFragment kVar8 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("dmTextSize", Integer.parseInt(strArr8[i4]))
                        .apply();
                SettingsFragment.j0(kVar8, 2, 2, strArr8[i4]);
                dialogInterface.dismiss();
                break;
            case 8:
                SettingsFragment kVar9 = this.f9534b;
                String[] strArr9 = this.f9535c;
                DmScreenArea dmScreenArea = DmScreenArea.Half;
                int area = dmScreenArea.getArea();
                if (i4 == 0) {
                    area = DmScreenArea.OneEighth.getArea();
                } else if (i4 == 1) {
                    area = DmScreenArea.OneSixth.getArea();
                } else if (i4 == 2) {
                    area = DmScreenArea.Quarter.getArea();
                } else if (i4 == 3) {
                    area = dmScreenArea.getArea();
                } else if (i4 == 4) {
                    area = DmScreenArea.ThreeQuarter.getArea();
                } else if (i4 == 5) {
                    area = DmScreenArea.Full.getArea();
                }
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("dmScreenArea", area)
                        .apply();
                SettingsFragment.j0(kVar9, 2, 3, strArr9[i4]);
                dialogInterface.dismiss();
                break;
            case 9:
                String[] strArr10 = this.f9535c;
                SettingsFragment kVar10 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("dmSpeed", Integer.parseInt(strArr10[i4]))
                        .apply();
                SettingsFragment.j0(kVar10, 2, 4, strArr10[i4]);
                dialogInterface.dismiss();
                break;
            case 10:
                SettingsFragment kVar11 = this.f9534b;
                String[] strArr11 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("dmAllowTop", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar11, 2, 5, strArr11[i4]);
                dialogInterface.dismiss();
                break;
            case 11:
                SettingsFragment kVar12 = this.f9534b;
                String[] strArr12 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("dmAllowBottom", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar12, 2, 6, strArr12[i4]);
                dialogInterface.dismiss();
                break;
            case 12:
                String[] strArr13 = this.f9535c;
                SettingsFragment kVar13 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("dmFilterWeight", i4 < 10 ? Integer.parseInt(strArr13[i4]) : 12)
                        .apply();
                SettingsFragment.j0(kVar13, 2, 7, strArr13[i4]);
                dialogInterface.dismiss();
                break;
            case 13:
                SettingsFragment kVar14 = this.f9534b;
                String[] strArr14 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("allowVipColorfulDm", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar14, 2, 8, strArr14[i4]);
                dialogInterface.dismiss();
                break;
            case 14:
                SettingsFragment kVar15 = this.f9534b;
                String[] strArr15 = this.f9535c;
                switch (i4) {
                    case 0:
                        code = VideoQuality.Q720P.getCode();
                        break;
                    case 1:
                        code = VideoQuality.Q1080P.getCode();
                        break;
                    case 2:
                        code = VideoQuality.Q1080PLUS.getCode();
                        break;
                    case 3:
                        code = VideoQuality.Q1080P60.getCode();
                        break;
                    case 4:
                        code = VideoQuality.Q4K.getCode();
                        break;
                    case 5:
                        code = VideoQuality.HDR.getCode();
                        break;
                    case 6:
                        code = VideoQuality.Dolby.getCode();
                        break;
                    case 7:
                        code = VideoQuality.Q8K.getCode();
                        break;
                    default:
                        code = VideoQuality.Q1080P.getCode();
                        break;
                }
                if (code > 64
                        && !kVar15.f9541G0.premiumMember
                        && kVar15.hostActivity() != null
                        && (mainActivityH = kVar15.hostActivity()) != null
                        && !mainActivityH.isFinishing()) {
                    android.widget.Toast.makeText(
                                    kVar15.requireHostActivity(),
                                    kVar15.stringResource(R.string.change_resolution_need_vip),
                                    android.widget.Toast.LENGTH_SHORT)
                            .show();
                }
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("defaultResolution", code)
                        .apply();
                SettingsFragment.j0(kVar15, 1, 0, strArr15[i4]);
                dialogInterface.dismiss();
                break;
            case 15:
                SettingsFragment kVar16 = this.f9534b;
                String[] strArr16 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("showFfRe", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar16, 1, 5, strArr16[i4]);
                dialogInterface.dismiss();
                break;
            case 16:
                SettingsFragment kVar17 = this.f9534b;
                String[] strArr17 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("videoCodec", i4)
                        .apply();
                SettingsFragment.j0(kVar17, 1, 6, strArr17[i4]);
                dialogInterface.dismiss();
                break;
            case 17:
                SettingsFragment kVar18 = this.f9534b;
                String[] strArr18 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("showSubtitle", i4 == 0 ? 1 : 0)
                        .apply();
                SettingsFragment.j0(kVar18, 1, 7, strArr18[i4]);
                dialogInterface.dismiss();
                break;
            case 18:
                String[] strArr19 = this.f9535c;
                SettingsFragment kVar19 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("subtitleTextSize", Integer.parseInt(strArr19[i4]))
                        .apply();
                SettingsFragment.j0(kVar19, 1, 8, strArr19[i4]);
                dialogInterface.dismiss();
                break;
            case 19:
                SettingsFragment kVar20 = this.f9534b;
                String[] strArr20 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("showDebug", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar20, 1, 9, strArr20[i4]);
                dialogInterface.dismiss();
                break;
            case 20:
                SettingsFragment kVar21 = this.f9534b;
                String[] strArr21 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("showVideoDetail", i4 == 0)
                        .apply();
                kVar21.f9541G0.showVideoDetails = i4 == 0;
                SettingsFragment.j0(kVar21, 1, 10, strArr21[i4]);
                dialogInterface.dismiss();
                break;
            case 21:
                String[] strArr22 = this.f9535c;
                SettingsFragment kVar22 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putFloat("defaultPlaySpeed", Float.parseFloat(strArr22[i4]))
                        .apply();
                SettingsFragment.j0(kVar22, 1, 2, strArr22[i4]);
                dialogInterface.dismiss();
                break;
            case 22:
                SettingsFragment kVar23 = this.f9534b;
                String[] strArr23 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("showBottomProgressBar", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar23, 1, 11, strArr23[i4]);
                dialogInterface.dismiss();
                break;
            case 23:
                SettingsFragment kVar24 = this.f9534b;
                String[] strArr24 = this.f9535c;
                boolean z7 = i4 == 0;
                kVar24.f9541G0.simpleRemoteKeys = z7;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("simpleKeyPress", z7)
                        .apply();
                SettingsFragment.j0(kVar24, 1, 12, strArr24[i4]);
                dialogInterface.dismiss();
                break;
            case 24:
                SettingsFragment kVar25 = this.f9534b;
                String[] strArr25 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("exitPlayerAfterPlay", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar25, 1, 4, strArr25[i4]);
                dialogInterface.dismiss();
                break;
            case 25:
                SettingsFragment kVar26 = this.f9534b;
                String[] strArr26 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("giveCoinNumber", i4 + 1)
                        .apply();
                SettingsFragment.j0(kVar26, 1, 13, strArr26[i4]);
                dialogInterface.dismiss();
                break;
            case 26:
                SettingsFragment kVar27 = this.f9534b;
                String[] strArr27 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("showNextPrevious", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar27, 1, 14, strArr27[i4]);
                dialogInterface.dismiss();
                break;
            case 27:
                SettingsFragment kVar28 = this.f9534b;
                String[] strArr28 = this.f9535c;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putBoolean("showDmSwitch", i4 == 0)
                        .apply();
                SettingsFragment.j0(kVar28, 1, 15, strArr28[i4]);
                dialogInterface.dismiss();
                break;
            default:
                String[] strArr29 = this.f9535c;
                SettingsFragment kVar29 = this.f9534b;
                AppController.f9455a
                        .application()
                        .getSharedPreferences("BLBL", 0)
                        .edit()
                        .putInt("ffSeekSecond", Integer.parseInt(strArr29[i4]))
                        .apply();
                SettingsFragment.j0(kVar29, 1, 16, strArr29[i4] + 's');
                dialogInterface.dismiss();
                break;
        }
    }
}
