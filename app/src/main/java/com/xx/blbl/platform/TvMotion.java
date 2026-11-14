package com.xx.blbl.platform;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
/** Only transforms the focused view; never changes grid measurements. */
public final class TvMotion {
 private TvMotion() {}
 public static void focus(View view, boolean focused) {
  view.animate().cancel();
  view.animate().scaleX(focused ? 1.025f : 1f).scaleY(focused ? 1.025f : 1f)
   .setDuration(140).setInterpolator(new DecelerateInterpolator()).start();
 }
}
