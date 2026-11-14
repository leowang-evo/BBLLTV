package com.xx.blbl.model;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CategoryModel implements Serializable {
    private int id;
    private String name;

    public CategoryModel() {
        this.name = "";
    }

    public CategoryModel(int i4, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = i4;
        this.name = name;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final void setId(int i4) {
        this.id = i4;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }
}
