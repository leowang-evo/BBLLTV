package com.xx.blbl.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xx.blbl.R;
import com.xx.blbl.platform.RecyclerFocus;

/** TV-sized settings choices with a current-value mark and cancellable D-pad navigation. */
public final class TvSettingsDialog extends AppCompatDialog {
    private final Builder config;
    private RecyclerView choices;
    private View firstFocus;
    private int selected;
    private boolean initialFocusPending;

    private TvSettingsDialog(Builder config) {
        super(config.context, R.style.DialogTheme);
        this.config = config;
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    private TextView text(CharSequence value, int size, int color) {
        TextView view = new TextView(getContext());
        view.setText(value);
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        view.setTextColor(color);
        view.setGravity(Gravity.CENTER_VERTICAL);
        return view;
    }

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        Window window = getWindow();
        if (window == null) return;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setDimAmount(.62f);
        LinearLayout panel = new LinearLayout(getContext());
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(30, 24, 30, 24);
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.rgb(24, 32, 47));
        background.setCornerRadius(22);
        background.setStroke(1, Color.rgb(63, 76, 98));
        panel.setBackground(background);
        TextView title = text(config.title, 34, Color.rgb(242, 244, 250));
        panel.addView(title, new LinearLayout.LayoutParams(-1, 58));
        if (config.items != null) {
            panel.addView(text("方向键选择 · 确认保存 · 返回取消", 22, Color.rgb(160, 174, 196)),
                    new LinearLayout.LayoutParams(-1, 42));
            choices = new RecyclerView(getContext());
            choices.setFocusable(false);
            choices.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
            choices.setLayoutManager(new LinearLayoutManager(getContext()));
            choices.setItemAnimator(null);
            choices.setClipToPadding(false);
            selected = 0;
            for (int i = 0; i < config.items.length; i++)
                if (sameChoice(config.items[i], config.current)) { selected = i; break; }
            choices.setAdapter(new Choices());
            int available = getContext().getResources().getDisplayMetrics().heightPixels - 240;
            panel.addView(choices, new LinearLayout.LayoutParams(-1,
                    Math.min(Math.min(540, available), config.items.length * 82)));
        } else if (config.message != null) {
            TextView message = text(config.message, 26, Color.rgb(184, 195, 213));
            message.setPadding(0, 12, 0, 24);
            panel.addView(message, new LinearLayout.LayoutParams(-1, -2));
        }
        TextView close = text(config.positiveText == null ? "返回" : config.positiveText,
                26, Color.rgb(222, 213, 250));
        close.setGravity(Gravity.CENTER);
        close.setFocusable(true);
        close.setClickable(true);
        close.setBackgroundResource(R.drawable.tv_setting_row);
        close.setOnClickListener(v -> {
            if (config.positive != null) config.positive.onClick(this, DialogInterface.BUTTON_POSITIVE);
            dismiss();
        });
        LinearLayout.LayoutParams button = new LinearLayout.LayoutParams(180, 68);
        button.gravity = Gravity.END;
        button.topMargin = 16;
        panel.addView(close, button);
        firstFocus = close;
        setContentView(panel);
        int width = Math.min(880, getContext().getResources().getDisplayMetrics().widthPixels - 96);
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
        setOnDismissListener(dialog -> {
            View anchor = config.anchor;
            if (anchor != null && anchor.isAttachedToWindow() && anchor.isShown())
                anchor.post(anchor::requestFocusFromTouch);
        });
    }

    private static boolean sameChoice(String option, String current) {
        if (option.equalsIgnoreCase(current)) return true;
        if ((option.contains("不限") || option.contains("无限"))
                && (current.contains("不限") || current.contains("无限"))) return true;
        try {
            String a = option.replaceAll("(?i)(m(?:b)?|s)$", "").trim();
            String b = current.replaceAll("(?i)(m(?:b)?|s)$", "").trim();
            return new java.math.BigDecimal(a).compareTo(new java.math.BigDecimal(b)) == 0;
        } catch (NumberFormatException ignored) { return false; }
    }

    @Override public void show() {
        initialFocusPending = true;
        super.show();
    }

    @Override public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus || !initialFocusPending || getWindow() == null) return;
        getWindow().getDecorView().post(() -> {
            if (!isShowing() || !initialFocusPending) return;
            initialFocusPending = false;
            if (choices != null) {
                choices.requestFocusFromTouch();
                RecyclerFocus.request(choices, selected);
            } else if (firstFocus != null) firstFocus.requestFocusFromTouch();
        });
    }

    private final class Choices extends RecyclerView.Adapter<Choice> {
        @Override public Choice onCreateViewHolder(ViewGroup parent, int type) {
            LinearLayout row = new LinearLayout(getContext());
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(24, 0, 24, 0);
            row.setFocusable(true);
            row.setClickable(true);
            row.setBackgroundResource(R.drawable.tv_setting_row);
            row.setLayoutParams(new RecyclerView.LayoutParams(-1, 82));
            TextView label = text("", 28, Color.rgb(242, 244, 250));
            label.setId(R.id.text_view_primary);
            row.addView(label, new LinearLayout.LayoutParams(0, -1, 1));
            TextView mark = text("", 26, Color.rgb(189, 164, 250));
            mark.setGravity(Gravity.CENTER);
            row.addView(mark, new LinearLayout.LayoutParams(46, -1));
            return new Choice(row, label, mark);
        }
        @Override public void onBindViewHolder(Choice holder, int position) {
            holder.label.setText(config.items[position]);
            holder.mark.setText(position == selected ? "●" : "○");
            holder.itemView.setSelected(position == selected);
        }
        @Override public int getItemCount() { return config.items.length; }
    }

    private final class Choice extends RecyclerView.ViewHolder {
        final TextView label, mark;
        Choice(View row, TextView label, TextView mark) {
            super(row);
            this.label = label;
            this.mark = mark;
            row.setOnClickListener(v -> {
                int index = getBindingAdapterPosition();
                if (index == RecyclerView.NO_POSITION) return;
                if (config.selection != null) config.selection.onClick(TvSettingsDialog.this, index);
                dismiss();
            });
        }
    }

    public static final class Builder {
        final Context context;
        View anchor;
        CharSequence title = "", message, positiveText;
        String current = "";
        String[] items;
        DialogInterface.OnClickListener selection, positive;
        public Builder(Context context) { this.context = context; }
        public Builder(Context context, View anchor) {
            this(context);
            this.anchor = anchor;
            TextView value = anchor.findViewById(R.id.text_sub);
            if (value != null) current = value.getText().toString();
        }
        public Builder setTitle(int id) { title = context.getText(id); return this; }
        public Builder setTitle(CharSequence value) { title = value; return this; }
        public Builder setMessage(CharSequence value) { message = value; return this; }
        public Builder setItems(String[] values, DialogInterface.OnClickListener listener) {
            items = values.clone(); selection = listener; return this;
        }
        public Builder setPositiveButton(int id, DialogInterface.OnClickListener listener) {
            positiveText = context.getText(id); positive = listener; return this;
        }
        public TvSettingsDialog show() {
            TvSettingsDialog dialog = new TvSettingsDialog(this); dialog.show(); return dialog;
        }
    }
}
