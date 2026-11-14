package com.xx.blbl.ui.view.exoplayer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextPaint;

import com.kuaishou.akdanmaku.DanmakuConfig;
import com.kuaishou.akdanmaku.data.DanmakuItem;
import com.kuaishou.akdanmaku.data.DanmakuItemData;
import com.kuaishou.akdanmaku.render.SimpleRenderer;
import com.kuaishou.akdanmaku.ui.DanmakuDisplayer;

/** BBLL's rank-one pink/blue outline, layered onto the published renderer API. */
class PremiumDanmakuRenderer extends SimpleRenderer {
    private final TextPaint fill = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private final TextPaint outline = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private final Paint border = new Paint(Paint.ANTI_ALIAS_FLAG);

    PremiumDanmakuRenderer() {
        fill.setStyle(Paint.Style.FILL);
        outline.setStyle(Paint.Style.FILL_AND_STROKE);
        outline.setStrokeWidth(3f);
        border.setColor(Color.WHITE);
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(6f);
    }

    @Override
    public void draw(
            DanmakuItem item, Canvas canvas, DanmakuDisplayer displayer, DanmakuConfig config) {
        DanmakuItemData data = item.getData();
        if (data.getRank() != 1) {
            super.draw(item, canvas, displayer, config);
            return;
        }
        float size = Math.max(12f, Math.min(25f, data.getTextSize()));
        fill.setTextSize(size * (displayer.getDensity() - .6f) * config.getTextSizeScale());
        fill.setColor(Color.BLACK | data.getTextColor());
        fill.setTypeface(config.getBold() ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        outline.setTextSize(fill.getTextSize());
        outline.setTypeface(fill.getTypeface());
        outline.setColor(fill.getColor() == Color.rgb(34, 34, 34) ? Color.WHITE : Color.BLACK);
        Paint.FontMetrics metrics = fill.getFontMetrics();
        float midpoint = (metrics.leading + metrics.descent - metrics.ascent) / 2f;
        outline.setShader(
                new LinearGradient(
                        0,
                        midpoint,
                        fill.measureText(data.getContent()),
                        midpoint,
                        Color.rgb(241, 83, 159),
                        Color.rgb(52, 140, 205),
                        Shader.TileMode.CLAMP));
        float baseline = 3f - fill.ascent();
        canvas.drawText(data.getContent(), 3f, baseline, outline);
        canvas.drawText(data.getContent(), 3f, baseline, fill);
        if (data.getDanmakuStyle() == DanmakuItemData.DANMAKU_STYLE_SELF_SEND)
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), border);
    }
}
