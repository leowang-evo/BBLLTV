package com.xx.blbl.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

/** One typed Retrofit boundary for endpoints that already return the application model. */
final class ResponseCallback<T> implements Callback<T> {
    private final NetResultCallback<T> target;

    ResponseCallback(NetResultCallback<T> target) {
        this.target = Objects.requireNonNull(target, "target");
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        target.onResponse(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable failure) {
        target.onFailure(failure);
    }
}
