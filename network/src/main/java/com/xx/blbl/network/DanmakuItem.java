package com.xx.blbl.network;

/** XML danmaku data at the application/network boundary, independent of renderer SDK. */
public final class DanmakuItem {
    private final long id;
    private final long positionMillis;
    private final String content;
    private final int mode;
    private final int textSize;
    private final int color;

    public DanmakuItem(
            long id, long positionMillis, String content, int mode, int textSize, int color) {
        this.id = id;
        this.positionMillis = positionMillis;
        this.content = java.util.Objects.requireNonNull(content, "content");
        this.mode = mode;
        this.textSize = textSize;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public long getPositionMillis() {
        return positionMillis;
    }

    public String getContent() {
        return content;
    }

    public int getMode() {
        return mode;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getColor() {
        return color;
    }

    public int getStyle() {
        return 0;
    }

    public int getWeight() {
        return 1;
    }

    public int getRank() {
        return 0;
    }
}
