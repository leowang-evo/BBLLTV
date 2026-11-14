package com.xx.blbl.platform;

import com.xx.blbl.AppController;

/** Application instance accessor retained for recovered application call sites. */
public final class ApplicationAccessor {
    public AppController application() {
        return AppController.instance();
    }
}
