package com.xx.blbl.ui.screen;

import android.view.View;

import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.platform.RequestMessages;

/** Dispatch a network result only while the requesting Fragment view still exists. */
public final class ScreenResult {
    private ScreenResult() {}

    public interface Action<T> {
        void accept(T value);
    }

    public static <T> NetResultCallback<T> bind(
            BaseScreenFragment page, Action<T> success, Action<Throwable> failure) {
        View requestingView = page.screenView;
        return new NetResultCallback<T>() {
            public void onResponse(T value) {
                page.runOnMain(
                        () -> {
                            if (page.isAdded()
                                    && page.screenView == requestingView
                                    && requestingView != null) {
                                com.xx.blbl.network.ApiFailure error =
                                        com.xx.blbl.network.ApiFailure.fromResponse(value);
                                if (error == null) success.accept(value);
                                else {
                                    failure.accept(error);
                                    page.showError(
                                            RequestMessages.error(page.requireContext(), error));
                                }
                            }
                        });
            }

            public void onFailure(Throwable error) {
                page.runOnMain(
                        () -> {
                            if (page.isAdded()
                                    && page.screenView == requestingView
                                    && requestingView != null) failure.accept(error);
                        });
            }
        };
    }
}
