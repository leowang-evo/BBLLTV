package com.xx.blbl.ui.auth;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.user.ScanQrModel;
import com.xx.blbl.model.user.SignInResultModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.screen.ScreenEvents;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.EnumMap;
import java.util.Map;

/** QR login and the original 1500 ms status polling, with Android view lifecycle cancellation. */
public final class QrLoginFragment extends BaseScreenFragment {
    public AppCompatImageView f9721H0;
    public AppCompatTextView f9722I0;
    public final Lazy<NetworkManager> f9720G0 = LazyKt.lazy(AppController::network);
    public String J0 = "";
    public final long f9723K0 = 1500;
    private ProgressBar qrProgress;
    private int requestGeneration;
    private final Runnable poll = () -> checkResult(false);

    public static void j0(QrLoginFragment page) {
        page.schedulePoll();
    }

    @Override
    public int layoutResource() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public void bindScreen(View view) {
        setScreenTitle(getString(R.string.sign_in));
        f9721H0 = view.findViewById(R.id.imageView);
        f9722I0 = view.findViewById(R.id.text_result);
        qrProgress = view.findViewById(R.id.progressBar);
        view.findViewById(R.id.button_check_result).setOnClickListener(button -> checkResult(true));
        view.findViewById(R.id.button_other_way_login)
                .setOnClickListener(
                        button -> {
                            MainActivity activity = hostActivity();
                            if (activity != null) {
                                activity.onBackPressed();
                                activity.showScreen(new BrowserLoginFragment(), "otherWayLogin");
                            }
                        });
        requestQr();
    }

    @Override
    public void reloadContent() {
        requestQr();
    }

    private void result(String message) {
        if (f9722I0 != null) f9722I0.setText(message);
    }

    private void schedulePoll() {
        mainHandler.removeCallbacks(poll);
        if (isAttachedToHost() && this.screenView != null && !TextUtils.isEmpty(J0))
            mainHandler.postDelayed(poll, f9723K0);
    }

    private void requestQr() {
        mainHandler.removeCallbacks(poll);
        final int generation = ++requestGeneration;
        J0 = "";
        if (qrProgress != null) qrProgress.setVisibility(View.VISIBLE);
        f9720G0.getValue()
                .getQRLoginInfo(
                        new NetResultCallback<BaseResponse<ScanQrModel>>() {
                            @Override
                            public void onFailure(Throwable failure) {
                                runOnMain(
                                        () -> {
                                            if (generation != requestGeneration
                                                    || !isAttachedToHost()) return;
                                            result(failure == null ? null : failure.getMessage());
                                            if (qrProgress != null)
                                                qrProgress.setVisibility(View.GONE);
                                        });
                            }

                            @Override
                            public void onResponse(BaseResponse<ScanQrModel> response) {
                                runOnMain(
                                        () -> {
                                            if (generation != requestGeneration
                                                    || !isAttachedToHost()) return;
                                            ScanQrModel data =
                                                    response == null ? null : response.getData();
                                            if (data == null) {
                                                result(
                                                        response == null
                                                                ? null
                                                                : response.getMessage());
                                                return;
                                            }
                                            J0 = data.getQrcode_key();
                                            if (!TextUtils.isEmpty(data.getUrl()))
                                                renderQr(data.getUrl(), generation);
                                            schedulePoll();
                                        });
                            }
                        });
    }

    private void renderQr(String url, int generation) {
        runIo(
                () -> {
                    try {
                        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
                        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                        BitMatrix bits =
                                new QRCodeWriter()
                                        .encode(url, BarcodeFormat.QR_CODE, 500, 500, hints);
                        int[] pixels = new int[250000];
                        for (int y = 0; y < 500; y++)
                            for (int x = 0; x < 500; x++)
                                pixels[y * 500 + x] = bits.get(x, y) ? Color.BLACK : Color.WHITE;
                        Bitmap bitmap =
                                Bitmap.createBitmap(pixels, 500, 500, Bitmap.Config.ARGB_8888);
                        runOnMain(
                                () -> {
                                    if (generation != requestGeneration || f9721H0 == null) return;
                                    f9721H0.setImageBitmap(bitmap);
                                    if (qrProgress != null) qrProgress.setVisibility(View.GONE);
                                });
                    } catch (com.google.zxing.WriterException failure) {
                        runOnMain(
                                () -> {
                                    if (generation == requestGeneration)
                                        result(failure.getMessage());
                                });
                    }
                });
    }

    private void checkResult(boolean manual) {
        if (!isAttachedToHost() || this.screenView == null || TextUtils.isEmpty(J0)) return;
        final int generation = requestGeneration;
        f9720G0.getValue()
                .checkSignInResult(
                        J0,
                        new NetResultCallback<BaseResponse<SignInResultModel>>() {
                            @Override
                            public void onFailure(Throwable failure) {
                                runOnMain(
                                        () -> {
                                            if (generation != requestGeneration
                                                    || !isAttachedToHost()) return;
                                            result(failure == null ? null : failure.getMessage());
                                            schedulePoll();
                                        });
                            }

                            @Override
                            public void onResponse(BaseResponse<SignInResultModel> response) {
                                runOnMain(
                                        () -> {
                                            if (generation != requestGeneration
                                                    || !isAttachedToHost()) return;
                                            SignInResultModel data =
                                                    response == null ? null : response.getData();
                                            if (data != null && data.getCode() == 0) {
                                                mainHandler.removeCallbacks(poll);
                                                ScreenEvents.post("signIn");
                                                MainActivity activity = hostActivity();
                                                if (activity != null) activity.onBackPressed();
                                            } else if (data != null && data.getCode() == 86038)
                                                requestQr();
                                            else if (!manual) schedulePoll();
                                            if (manual)
                                                result(data == null ? null : data.getMessage());
                                        });
                            }
                        });
    }

    @Override
    public void releaseScreenView() {
        requestGeneration++;
        mainHandler.removeCallbacks(poll);
        f9721H0 = null;
        f9722I0 = null;
        qrProgress = null;
        super.releaseScreenView();
    }
}
