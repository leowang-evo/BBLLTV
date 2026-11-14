package com.xx.blbl.network;

public interface NetResultCallback<T> {
    void onFailure(Throwable th);

    void onResponse(T t7);
}
