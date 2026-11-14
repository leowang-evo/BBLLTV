package com.xx.blbl.ui.view.exoplayer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.kuaishou.akdanmaku.DanmakuConfig;
import com.kuaishou.akdanmaku.data.DanmakuItem;
import com.kuaishou.akdanmaku.ui.DanmakuDisplayer;
import com.kuaishou.akdanmaku.utils.Size;

/** The app's uploader-icon danmaku style, recovered from q5/a. */
final class UpIconRenderer extends PremiumDanmakuRenderer {
    private final Drawable icon;
    private final Paint background = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF bounds = new RectF();

    UpIconRenderer(Drawable icon) {
        this.icon = icon;
        background.setColor(Color.argb(102, 0, 0, 0));
    }

    @Override
    public void draw(
            DanmakuItem item, Canvas canvas, DanmakuDisplayer displayer, DanmakuConfig config) {
        bounds.set(0, 0, canvas.getWidth(), canvas.getHeight());
        float radius = canvas.getHeight() * .5f;
        canvas.drawRoundRect(bounds, radius, radius, background);
        int width =
                (int)
                        ((canvas.getHeight() - 24)
                                * (icon.getIntrinsicWidth() / (float) icon.getIntrinsicHeight()));
        icon.setBounds(26, 12, width + 26, canvas.getHeight() - 12);
        icon.draw(canvas);
        int save = canvas.save();
        canvas.translate(width + 34, 12);
        try {
            super.draw(item, canvas, displayer, config);
        } finally {
            canvas.restoreToCount(save);
        }
    }

    @Override
    public Size measure(DanmakuItem item, DanmakuDisplayer displayer, DanmakuConfig config) {
        Size size = super.measure(item, displayer, config);
        float aspect = icon.getIntrinsicWidth() / (float) icon.getIntrinsicHeight();
        return new Size(
                size.getWidth() + 52 + (int) (size.getHeight() * aspect) + 8,
                size.getHeight() + 24);
    }
}
