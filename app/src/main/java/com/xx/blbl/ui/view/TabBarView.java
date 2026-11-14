package com.xx.blbl.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.xx.blbl.R;

import java.util.ArrayList;
import java.util.List;

/** Six recovered navigation tabs, with explicit navigation/account responsibilities. */
public final class TabBarView extends FrameLayout implements View.OnClickListener {
    public interface OnTabClick {
        void onTabSelected(int position);
    }

    public interface ActionListener {
        void onSettings();

        /** Signed-in hosts show account actions; signed-out hosts show login. */
        void onAccount(boolean signedIn);
    }

    private final List<AppCompatImageView> tabs = new ArrayList<>();
    private final AppCompatImageView settings, avatar;
    private final TypedValue selectedColor = new TypedValue(), normalColor = new TypedValue();
    private OnTabClick onTabClick;
    private ActionListener actionListener;
    private boolean signedIn;
    private String avatarUrl = "", userAgent = "Mozilla/5.0";

    public TabBarView(Context context) {
        this(context, null);
    }

    public TabBarView(Context context, AttributeSet attributes) {
        super(context, attributes);
        View.inflate(context, R.layout.view_tab_bar, this);
        context.getTheme().resolveAttribute(R.attr.tabBarSelected, selectedColor, true);
        context.getTheme().resolveAttribute(R.attr.tabBarNormal, normalColor, true);
        for (int id :
                new int[] {
                    R.id.button_home,
                    R.id.button_category,
                    R.id.button_dynamic,
                    R.id.button_live,
                    R.id.button_me,
                    R.id.button_search
                }) {
            AppCompatImageView button = findViewById(id);
            tabs.add(button);
            button.setOnClickListener(this);
        }
        settings = findViewById(R.id.button_setting);
        settings.setOnClickListener(this);
        avatar = findViewById(R.id.image_avatar);
        avatar.setOnClickListener(this);
        a(0);
        c();
    }

    /**
     * Original selection entry point; positions 0..5 correspond to
     * home/category/dynamic/live/me/search.
     */
    public void a(int position) {
        for (int index = 0; index < tabs.size(); index++) b(tabs.get(index), index == position);
    }

    public void b(View view, boolean selected) {
        if (view instanceof AppCompatImageView) {
            ((AppCompatImageView) view)
                    .setColorFilter((selected ? selectedColor : normalColor).data);
            view.setSelected(selected);
        }
    }

    /** Refresh the latest host-supplied account/avatar snapshot. */
    public void c() {
        if (!signedIn) {
            com.xx.blbl.platform.ImageLoader.loadDefaultAvatar(avatar);
        } else if (!TextUtils.isEmpty(avatarUrl)) {
            String url = avatarUrl.startsWith("http") ? avatarUrl : "https:" + avatarUrl;
            if (!url.startsWith("https")) url = url.replace("http", "https");
            url += "@96w_96h_1c_1s.webp";
            GlideUrl image =
                    new GlideUrl(
                            url,
                            new LazyHeaders.Builder()
                                    .addHeader("Referer", "https://www.bilibili.com/")
                                    .addHeader("User-Agent", userAgent)
                                    .build());
            Glide.with(this)
                    .load(image)
                    .placeholder(R.mipmap.default_avatar)
                    .circleCrop()
                    .into(avatar);
        }
    }

    public void setAccount(boolean signedIn, String avatarUrl, String userAgent) {
        this.signedIn = signedIn;
        this.avatarUrl = avatarUrl == null ? "" : avatarUrl;
        if (userAgent != null) this.userAgent = userAgent;
        c();
    }

    @Override
    public void onClick(View view) {
        int position = tabs.indexOf(view);
        if (position >= 0) {
            a(position);
            if (onTabClick != null) onTabClick.onTabSelected(position);
        } else if (view == settings && actionListener != null) actionListener.onSettings();
        else if (view == avatar && actionListener != null) actionListener.onAccount(signedIn);
    }

    public OnTabClick getOnTabClick() {
        return onTabClick;
    }

    public void setOnTabClick(OnTabClick listener) {
        onTabClick = listener;
    }

    public void setActionListener(ActionListener listener) {
        actionListener = listener;
    }
}
