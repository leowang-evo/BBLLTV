package com.xx.blbl.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.xx.blbl.R;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.ImageLoader;

/** Video metadata dialog on upstream AppCompat. */
public final class VideoInfoDialog extends AppCompatDialog {
    public final int f12024f;
    public View f12025p;
    public AppCompatTextView f12026v;
    public Object f12027w;

    public VideoInfoDialog(Context context) {
        super(context, R.style.DialogTheme);
        f12024f = 2;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.dialog_video_info, null);
        setContentView(root);
        f12025p = root.findViewById(R.id.imageView);
        f12026v = root.findViewById(R.id.text_title);
        f12027w = root.findViewById(R.id.text_description);
        root.findViewById(R.id.button_close).setOnClickListener(view -> dismiss());
        setCanceledOnTouchOutside(true);
    }

    public VideoInfoDialog(Context context, int theme, int mode) {
        super(context, theme);
        f12024f = mode;
    }

    public void i(VideoModel model) {
        ImageLoader.loadThumbnail(model.getPic(), (AppCompatImageView) f12025p);
        f12026v.setText(model.getTitle());
        ((AppCompatTextView) f12027w).setText(model.getDesc());
    }
}
