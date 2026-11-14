package com.xx.blbl.ui.settings;

import android.content.DialogInterface;
import android.view.View;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.SettingModel;
import com.xx.blbl.model.common.ZoneModel;
import com.xx.blbl.model.player.VideoCodecEnum;
import com.xx.blbl.model.video.quality.AudioQuality;
import com.xx.blbl.model.video.quality.VideoQuality;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;

public final class PlaybackPreferenceHandler implements NetResultCallback, ItemClickCallback {
    public final /* synthetic */ int f9531a;
    public final /* synthetic */ SettingsFragment f9532b;

    public /* synthetic */ PlaybackPreferenceHandler(SettingsFragment kVar, int i4) {
        this.f9531a = i4;
        this.f9532b = kVar;
    }

    @Override
    public void onClick(View view, int i4) {
        switch (this.f9531a) {
            case 1:
                if (view == null) {
                    return;
                }

                if (i4 == 5) {
                    SettingsFragment kVar = this.f9532b;
                    kotlin.Lazy<NetworkManager> r7 = kVar.f9542H0;
                    ((NetworkManager) r7.getValue()).refreshUA();
                    ((SettingModel) kVar.f9551U0.get(5))
                            .setInfo(((NetworkManager) r7.getValue()).getCurrentUA());
                    ScreenContentAdapter jVar = kVar.f9547P0;
                    if (jVar != null) {
                        jVar.notifyItemChanged(5);
                        return;
                    } else {
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(
                                "adapter");
                        throw null;
                    }
                }
                return;
            default:
                if (view == null) {
                    return;
                }
                final SettingsFragment kVar2 = this.f9532b;
                switch (i4) {
                    case 0:
                        String[] strArr = {
                            VideoQuality.Q720P.getShowName(),
                            VideoQuality.Q1080P.getShowName(),
                            VideoQuality.Q1080PLUS.getShowName(),
                            VideoQuality.Q1080P60.getShowName(),
                            VideoQuality.Q4K.getShowName(),
                            VideoQuality.HDR.getShowName(),
                            VideoQuality.Dolby.getShowName(),
                            VideoQuality.Q8K.getShowName()
                        };
                        TvSettingsDialog.Builder kVar3 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar3.setTitle(R.string.default_video_quality);
                        kVar3.setItems(strArr, new PreferenceChoiceHandler(kVar2, strArr, 14));
                        kVar3.show();
                        return;
                    case 1:
                        final String[] strArr2 = {
                            AudioQuality.Q192K.getShowName(),
                            AudioQuality.Q132K.getShowName(),
                            AudioQuality.Q64K.getShowName(),
                            AudioQuality.DolbyAtoms.getShowName(),
                            AudioQuality.Lossless.getShowName()
                        };
                        TvSettingsDialog.Builder kVar4 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar4.setTitle(R.string.default_audio_track);
                        final int i7 = 1;
                        kVar4.setItems(
                                strArr2,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public final void onClick(
                                            DialogInterface dialogInterface, int i8) {
                                        switch (i7) {
                                            case 0:
                                                SettingsFragment kVar5 = kVar2;
                                                String[] strArr3 = strArr2;
                                                kVar5.f9541G0.continuationMode = i8;
                                                AppController.f9455a
                                                        .application()
                                                        .getSharedPreferences("BLBL", 0)
                                                        .edit()
                                                        .putInt("continuationPlayType", i8)
                                                        .apply();
                                                SettingsFragment.j0(kVar5, 1, 3, strArr3[i8]);
                                                dialogInterface.dismiss();
                                                break;
                                            default:
                                                SettingsFragment kVar6 = kVar2;
                                                String[] strArr4 = strArr2;
                                                AudioQuality audioQuality = AudioQuality.Q192K;
                                                int code = audioQuality.getCode();
                                                if (i8 == 0) {
                                                    code = audioQuality.getCode();
                                                } else if (i8 == 1) {
                                                    code = AudioQuality.Q132K.getCode();
                                                } else if (i8 == 2) {
                                                    code = AudioQuality.Q64K.getCode();
                                                } else if (i8 == 3) {
                                                    code = AudioQuality.DolbyAtoms.getCode();
                                                } else if (i8 == 4) {
                                                    code = AudioQuality.Lossless.getCode();
                                                }
                                                AppController.f9455a
                                                        .application()
                                                        .getSharedPreferences("BLBL", 0)
                                                        .edit()
                                                        .putInt("defaultAudioTrack", code)
                                                        .apply();
                                                SettingsFragment.j0(kVar6, 1, 1, strArr4[i8]);
                                                dialogInterface.dismiss();
                                                break;
                                        }
                                    }
                                });
                        kVar4.show();
                        return;
                    case 2:
                        String[] strArr3 = {"0.25", "0.5", "0.75", "1.0", "1.25", "1.5", "2.0"};
                        TvSettingsDialog.Builder kVar5 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar5.setTitle(R.string.default_play_speed);
                        kVar5.setItems(strArr3, new PreferenceChoiceHandler(strArr3, kVar2, 21));
                        kVar5.show();
                        return;
                    case 3:
                        final String[] strArr4 = {
                            ContinuationPlayEnum.Disable.getShowName(),
                            ContinuationPlayEnum.Recommend.getShowName(),
                            ContinuationPlayEnum.CurrentList.getShowName(),
                            ContinuationPlayEnum.PlayEpisode.getShowName()
                        };
                        TvSettingsDialog.Builder kVar6 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar6.setTitle(R.string.after_play);
                        final int i8 = 0;
                        kVar6.setItems(
                                strArr4,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public final void onClick(
                                            DialogInterface dialogInterface, int i9) {
                                        switch (i8) {
                                            case 0:
                                                SettingsFragment kVar7 = kVar2;
                                                String[] strArr5 = strArr4;
                                                kVar7.f9541G0.continuationMode = i9;
                                                AppController.f9455a
                                                        .application()
                                                        .getSharedPreferences("BLBL", 0)
                                                        .edit()
                                                        .putInt("continuationPlayType", i9)
                                                        .apply();
                                                SettingsFragment.j0(kVar7, 1, 3, strArr5[i9]);
                                                dialogInterface.dismiss();
                                                break;
                                            default:
                                                SettingsFragment kVar8 = kVar2;
                                                String[] strArr6 = strArr4;
                                                AudioQuality audioQuality = AudioQuality.Q192K;
                                                int code = audioQuality.getCode();
                                                if (i9 == 0) {
                                                    code = audioQuality.getCode();
                                                } else if (i9 == 1) {
                                                    code = AudioQuality.Q132K.getCode();
                                                } else if (i9 == 2) {
                                                    code = AudioQuality.Q64K.getCode();
                                                } else if (i9 == 3) {
                                                    code = AudioQuality.DolbyAtoms.getCode();
                                                } else if (i9 == 4) {
                                                    code = AudioQuality.Lossless.getCode();
                                                }
                                                AppController.f9455a
                                                        .application()
                                                        .getSharedPreferences("BLBL", 0)
                                                        .edit()
                                                        .putInt("defaultAudioTrack", code)
                                                        .apply();
                                                SettingsFragment.j0(kVar8, 1, 1, strArr6[i9]);
                                                dialogInterface.dismiss();
                                                break;
                                        }
                                    }
                                });
                        kVar6.show();
                        return;
                    case 4:
                        String[] strArr5 = {"开", "关"};
                        TvSettingsDialog.Builder kVar7 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar7.setTitle(R.string.play_finish_exit_player);
                        kVar7.setItems(strArr5, new PreferenceChoiceHandler(kVar2, strArr5, 24));
                        kVar7.show();
                        return;
                    case 5:
                        String[] strArr6 = {"开", "关"};
                        TvSettingsDialog.Builder kVar8 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar8.setTitle(R.string.show_re_ff);
                        kVar8.setItems(strArr6, new PreferenceChoiceHandler(kVar2, strArr6, 15));
                        kVar8.show();
                        return;
                    case 6:
                        String[] strArr7 = {
                            VideoCodecEnum.AVC.getShowName(),
                            VideoCodecEnum.HEVC.getShowName(),
                            VideoCodecEnum.AV1.getShowName()
                        };
                        TvSettingsDialog.Builder kVar9 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar9.setTitle(R.string.video_codec);
                        kVar9.setItems(strArr7, new PreferenceChoiceHandler(kVar2, strArr7, 16));
                        kVar9.show();
                        return;
                    case 7:
                        String[] strArr8 = {"开", "关"};
                        TvSettingsDialog.Builder kVar10 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar10.setTitle(R.string.show_subtitle_default);
                        kVar10.setItems(strArr8, new PreferenceChoiceHandler(kVar2, strArr8, 17));
                        kVar10.show();
                        return;
                    case 8:
                        String[] strArr9 = {
                            "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
                            "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
                            "59", "60"
                        };
                        TvSettingsDialog.Builder kVar11 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar11.setTitle(R.string.subtitle_text_size);
                        kVar11.setItems(strArr9, new PreferenceChoiceHandler(strArr9, kVar2, 18));
                        kVar11.show();
                        return;
                    case 9:
                        String[] strArr10 = {"开", "关"};
                        TvSettingsDialog.Builder kVar12 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar12.setTitle(R.string.show_debug);
                        kVar12.setItems(strArr10, new PreferenceChoiceHandler(kVar2, strArr10, 19));
                        kVar12.show();
                        return;
                    case 10:
                        String[] strArr11 = {"开", "关"};
                        TvSettingsDialog.Builder kVar13 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar13.setTitle(R.string.show_video_detail);
                        kVar13.setItems(strArr11, new PreferenceChoiceHandler(kVar2, strArr11, 20));
                        kVar13.show();
                        return;
                    case 11:
                        String[] strArr12 = {"开", "关"};
                        TvSettingsDialog.Builder kVar14 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar14.setTitle(R.string.show_bottom_progress_bar);
                        kVar14.setItems(strArr12, new PreferenceChoiceHandler(kVar2, strArr12, 22));
                        kVar14.show();
                        return;
                    case 12:
                        String[] strArr13 = {"开", "关"};
                        TvSettingsDialog.Builder kVar15 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar15.setTitle(R.string.simple_key_press_tip);
                        kVar15.setItems(strArr13, new PreferenceChoiceHandler(kVar2, strArr13, 23));
                        kVar15.show();
                        return;
                    case 13:
                        String[] strArr14 = {"1", "2"};
                        TvSettingsDialog.Builder kVar16 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar16.setTitle(R.string.give_coin_number_tip);
                        kVar16.setItems(strArr14, new PreferenceChoiceHandler(kVar2, strArr14, 25));
                        kVar16.show();
                        return;
                    case 14:
                        String[] strArr15 = {"开", "关"};
                        TvSettingsDialog.Builder kVar17 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar17.setTitle(R.string.show_next_previous);
                        kVar17.setItems(strArr15, new PreferenceChoiceHandler(kVar2, strArr15, 26));
                        kVar17.show();
                        return;
                    case 15:
                        String[] strArr16 = {"开", "关"};
                        TvSettingsDialog.Builder kVar18 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar18.setTitle(R.string.show_dm_switch);
                        kVar18.setItems(strArr16, new PreferenceChoiceHandler(kVar2, strArr16, 27));
                        kVar18.show();
                        return;
                    case 16:
                        String[] strArr17 = {
                            "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60"
                        };
                        TvSettingsDialog.Builder kVar19 =
                                new TvSettingsDialog.Builder(view.getContext(), view);
                        kVar19.setTitle(R.string.ff_duration);
                        kVar19.setItems(strArr17, new PreferenceChoiceHandler(strArr17, kVar2, 28));
                        kVar19.show();
                        return;
                    default:
                        return;
                }
        }
    }

    @Override
    public void onFailure(Throwable th) {}

    @Override
    public void onResponse(Object obj) {
        ZoneModel zoneModel;
        BaseResponse baseResponse = (BaseResponse) obj;
        if (baseResponse == null || (zoneModel = (ZoneModel) baseResponse.getData()) == null) {
            return;
        }
        SettingsFragment kVar = this.f9532b;
        if (kVar.f9551U0.size() > 4) {
            ((SettingModel) kVar.f9551U0.get(4)).setInfo(zoneModel.toString());
            ScreenContentAdapter jVar = kVar.f9547P0;
            if (jVar == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("adapter");
                throw null;
            }
            jVar.notifyItemChanged(4);
        }
    }
}
