package com.xx.blbl.platform;

import com.xx.blbl.AppController;

import kotlin.jvm.functions.Function0;

/** Application service selectors; upstream DI internals are not reproduced. */
public final class AppServiceProvider implements Function0<Object> {
    public final int f352a;

    public AppServiceProvider(Object unusedOwner, int selector) {
        f352a = selector;
    }

    @Override
    public Object invoke() {
        switch (f352a) {
            case 0:
            case 1:
            case 4:
            case 12:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
                return AppController.network();
            default:
                return AppController.state();
        }
    }
}
