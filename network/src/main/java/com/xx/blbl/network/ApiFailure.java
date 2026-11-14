package com.xx.blbl.network;

import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.network.response.BaseBaseResponse;
import com.xx.blbl.network.response.BaseResponse;

import java.io.IOException;

/** A transport success can still carry an unsuccessful application response. */
public final class ApiFailure extends IOException {
    public final int code;

    private ApiFailure(int code, String message) {
        super(message);
        this.code = code;
    }

    public static ApiFailure fromResponse(Object response) {
        if (response == null) return new ApiFailure(Integer.MIN_VALUE, "Empty response");
        int code;
        String message;
        if (response instanceof BaseResponse) {
            code = ((BaseResponse<?>) response).getCode();
            message = ((BaseResponse<?>) response).getMessage();
        } else if (response instanceof Base2Response) {
            code = ((Base2Response<?>) response).getCode();
            message = ((Base2Response<?>) response).getMessage();
        } else if (response instanceof BaseBaseResponse) {
            code = ((BaseBaseResponse) response).getCode();
            message = ((BaseBaseResponse) response).getMessage();
        } else return null;
        return code == 0 ? null : new ApiFailure(code, message);
    }
}
