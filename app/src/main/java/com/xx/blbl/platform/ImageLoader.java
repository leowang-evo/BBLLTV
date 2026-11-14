package com.xx.blbl.platform;

import android.widget.ImageView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.graphics.Outline;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.state.AppState;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/** Application image policy on the upstream Glide public API. */
public final class ImageLoader {
    public static final Lazy<AppState> f736a = LazyKt.lazy(AppController::state);
    public static final Lazy<NetworkManager> f737b = LazyKt.lazy(AppController::network);

    private ImageLoader() {}

    private static final ViewOutlineProvider COVER_OUTLINE = new ViewOutlineProvider() {
        @Override public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(),
                    view.getResources().getDimension(R.dimen.px15));
        }
    };

    private static void prepareCover(ImageView view) {
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setOutlineProvider(COVER_OUTLINE);
        view.setClipToOutline(true);
    }

    private static String https(String value) {
        String normalized = value.startsWith("http") ? value : "https:" + value;
        if (!normalized.startsWith("https")) normalized = value.replace("http", "https");
        return normalized;
    }

    private static GlideUrl request(String url, boolean poster) {
        LazyHeaders headers =
                new LazyHeaders.Builder()
                        .addHeader(poster ? "referer" : "Referer", "https://www.bilibili.com/")
                        .addHeader(
                                poster ? "user-agent" : "User-Agent",
                                f737b.getValue().getCurrentUA())
                        .build();
        return new GlideUrl(url, headers);
    }

    public static GlideUrl avatarRequest(String url, boolean small) {
        Intrinsics.checkNotNullParameter(url, "url");
        return request(
                https(url) + (small ? "@96w_96h_1c_1s.webp" : "@240w_240h_1c_1s.webp"), false);
    }

    public static GlideUrl thumbnailRequest(String url, boolean large) {
        Intrinsics.checkNotNullParameter(url, "url");
        String normalized = https(url);
        int quality = f736a.getValue().imageQuality;
        if (large) {
            if (quality == 0) normalized += "@480w_270h_1c.webp";
            else if (quality == 1 || (quality != 2 && quality != 3))
                normalized += "@672w_378h.webp";
        } else {
            if (quality == 0) normalized += "@240w_135h_1c.webp";
            else if (quality == 1 || (quality != 2 && quality != 3))
                normalized += "@480w_270h_1c.webp";
            else if (quality == 2) normalized += "@672w_378h.webp";
        }
        return request(normalized, false);
    }

    public static void loadAvatar(String url, ImageView imageView) {
        loadAvatar(url, imageView, false);
    }

    public static void loadAvatar(String url, ImageView imageView, boolean small) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Glide.with(imageView)
                .load(avatarRequest(url, small))
                .placeholder(R.mipmap.default_avatar)
                .circleCrop()
                .into(imageView);
    }

    public static void loadThumbnail(String url, ImageView imageView) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        prepareCover(imageView);
        Glide.with(imageView)
                .load(thumbnailRequest(url, false))
                .placeholder(R.mipmap.default_video)
                .dontTransform()
                .into(imageView);
    }

    public static void loadDefaultAvatar(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (imageView.getTag(R.id.glide_custom_view_target_tag) != null)
            Glide.with(imageView.getContext().getApplicationContext()).clear(imageView);
        imageView.setOutlineProvider(AVATAR_OUTLINE);
        imageView.setClipToOutline(true);
        imageView.setImageResource(R.mipmap.default_avatar);
    }

    private static final ViewOutlineProvider AVATAR_OUTLINE = new ViewOutlineProvider() {
        @Override public void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, view.getWidth(), view.getHeight());
        }
    };

    public static void loadPoster(String url, ImageView imageView) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        prepareCover(imageView);
        String normalized = https(url);
        int quality = f736a.getValue().imageQuality;
        if (quality == 0) normalized += "@160w_213h_1c.webp";
        else if (quality == 1) normalized += "@320w_426h_1c.webp";
        else if (quality == 2) normalized += "@466w_622h.webp";
        else if (quality != 3) normalized += "@480w_640h_1c.webp";
        Glide.with(imageView)
                .load(request(normalized, true))
                .placeholder(R.mipmap.default_video)
                .dontTransform()
                .into(imageView);
    }
}
