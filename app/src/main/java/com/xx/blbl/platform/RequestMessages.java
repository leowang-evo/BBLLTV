package com.xx.blbl.platform;

import android.content.Context;

import com.xx.blbl.R;
import com.xx.blbl.network.ApiFailure;

public final class RequestMessages {
    private RequestMessages() {}

    public static String error(Context context, Throwable failure) {
        if (failure instanceof ApiFailure) {
            int code = ((ApiFailure) failure).code;
            if (code == -351 || code == -352 || code == -412)
                return context.getString(R.string.request_limited_message, code);
            if (code != Integer.MIN_VALUE) {
                String message = failure.getMessage();
                return (message == null || message.trim().isEmpty()
                                ? context.getString(R.string.net_error)
                                : message)
                        + "（"
                        + code
                        + "）";
            }
        }
        return context.getString(R.string.net_error);
    }
}
