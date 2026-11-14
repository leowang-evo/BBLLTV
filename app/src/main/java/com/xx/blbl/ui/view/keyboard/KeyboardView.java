package com.xx.blbl.ui.view.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.xx.blbl.R;

/** Recovered alphabet/T9 remote keyboard, with explicit host input callbacks. */
public final class KeyboardView extends LinearLayout
        implements ViewTreeObserver.OnGlobalLayoutListener {
    public interface KeySelectListener {
        void onCharacter(String character);

        void onDelete();

        void onClear();

        /** The host submits its current input, trimmed with KeyboardInput.trimKey. */
        void onSearch();
    }

    private static final String[] T9_LABELS = {
        "\n0/1", "2\nABC", "3\nDEF", "4\nGHI", "5\nJKL", "6\nMNO", "7\nPQRS", "8\nTUV", "9\nWXYZ"
    };
    private final OvershootInterpolator focusInterpolator = new OvershootInterpolator();
    private KeySelectListener listener;
    private LinearLayout grid;
    private View header, footer, expandedKey;
    private TextView alphabetButton, t9Button;
    private boolean initialized, squareKey, squareApplied, t9Mode, dispatchDelete = true;
    private int initialHeight;

    public KeyboardView(Context context) {
        this(context, null);
    }

    public KeyboardView(Context context, AttributeSet attributes) {
        super(context, attributes);
        setOrientation(VERTICAL);
        setClipChildren(false);
        t9Mode = context.getSharedPreferences("BLBL", 0).getBoolean("isT9Keyboard", true);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if (initialized) return;
        initialized = true;
        initialHeight = getHeight();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        header = inflater.inflate(R.layout.layout_keybord_header, this, false);
        footer = inflater.inflate(R.layout.layout_keybord_footer, this, false);
        addView(header);
        grid = new LinearLayout(getContext());
        grid.setOrientation(VERTICAL);
        grid.setClipChildren(false);
        grid.setElevation(20f);
        addView(grid, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
        addView(footer);
        alphabetButton = findViewById(R.id.button_qwe);
        t9Button = findViewById(R.id.button_t9);
        findViewById(R.id.button_back)
                .setOnClickListener(
                        view -> {
                            if (listener != null) listener.onDelete();
                        });
        findViewById(R.id.button_clear)
                .setOnClickListener(
                        view -> {
                            if (listener != null) listener.onClear();
                        });
        findViewById(R.id.button_search)
                .setOnClickListener(
                        view -> {
                            if (listener != null) listener.onSearch();
                        });
        alphabetButton.setOnClickListener(view -> setMode(false, true));
        t9Button.setOnClickListener(view -> setMode(true, true));
        setMode(t9Mode, false);
        if (squareKey) {
            ViewTreeObserver.OnGlobalLayoutListener sizeListener =
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            if (header.getHeight() == 0 || footer.getHeight() == 0 || squareApplied)
                                return;
                            int width = initialHeight - header.getHeight() - footer.getHeight();
                            ViewGroup.LayoutParams params = getLayoutParams();
                            if (params != null) {
                                params.width = width;
                                params.height = LayoutParams.MATCH_PARENT;
                                setLayoutParams(params);
                            }
                            squareApplied = true;
                            header.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            footer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    };
            header.getViewTreeObserver().addOnGlobalLayoutListener(sizeListener);
            footer.getViewTreeObserver().addOnGlobalLayoutListener(sizeListener);
        }
    }

    private void animateFocus(TextView key) {
        key.setOnFocusChangeListener(
                (view, focused) -> {
                    if (focused) key.animate().setInterpolator(focusInterpolator);
                    key.animate()
                            .scaleX(focused ? 1.1f : 1f)
                            .scaleY(focused ? 1.1f : 1f)
                            .setDuration(600L)
                            .start();
                });
    }

    private void setMode(boolean t9, boolean persist) {
        t9Mode = t9;
        expandedKey = null;
        if (grid == null) return;
        grid.removeAllViews();
        int rows = t9 ? 3 : 6, columns = t9 ? 3 : 6;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int row = 0; row < rows; row++) {
            LinearLayout line = new LinearLayout(getContext());
            line.setOrientation(HORIZONTAL);
            line.setClipChildren(false);
            LinearLayout.LayoutParams rowParams =
                    new LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
            rowParams.setMargins(0, t9 ? 15 : 10, 0, 0);
            grid.addView(line, rowParams);
            for (int column = 0; column < columns; column++) {
                final int index = row * columns + column;
                View keyRoot =
                        inflater.inflate(
                                t9 ? R.layout.layout_key : R.layout.layout_key_qwe, line, false);
                TextView key = keyRoot.findViewById(t9 ? R.id.button_key : R.id.button_key_qwe);
                key.setText(t9 ? T9_LABELS[index] : KeyboardInput.alphabetKey(index));
                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
                params.setMargins(t9 ? 10 : 5, 0, t9 ? 10 : 5, 0);
                line.addView(keyRoot, params);
                animateFocus(key);
                if (t9) {
                    keyRoot.findViewById(R.id.view_select).setVisibility(GONE);
                    key.setOnClickListener(view -> expandT9(keyRoot, index));
                } else
                    key.setOnClickListener(
                            view -> {
                                commit(key.getText().toString());
                                key.requestFocus();
                            });
            }
        }
        alphabetButton.setTextColor(
                ContextCompat.getColor(getContext(), t9 ? R.color.white : R.color.colorAccent));
        t9Button.setTextColor(
                ContextCompat.getColor(getContext(), t9 ? R.color.colorAccent : R.color.white));
        if (persist)
            getContext()
                    .getSharedPreferences("BLBL", 0)
                    .edit()
                    .putBoolean("isT9Keyboard", t9)
                    .apply();
    }

    private void expandT9(View root, int index) {
        expandedKey = root;
        TextView key = root.findViewById(R.id.button_key);
        View choices = root.findViewById(R.id.view_select);
        key.setVisibility(GONE);
        choices.setVisibility(VISIBLE);
        String[] labels = KeyboardInput.t9Keys(index);
        int[] ids = {
            R.id.button_center,
            R.id.button_top,
            R.id.button_left,
            R.id.button_right,
            R.id.button_bottom
        };
        for (int position = 0; position < ids.length; position++) {
            TextView option = choices.findViewById(ids[position]);
            String label = labels[position];
            option.setVisibility(label == null ? INVISIBLE : VISIBLE);
            if (label == null) continue;
            option.setText(label);
            if (position == 0) {
                option.setOnClickListener(
                        view -> {
                            commit(label);
                            collapseT9(root);
                        });
                option.requestFocus();
            } else
                option.setOnFocusChangeListener(
                        (view, focused) -> {
                            if (focused) {
                                commit(label);
                                collapseT9(root);
                            }
                        });
        }
    }

    private void commit(String text) {
        if (listener != null) listener.onCharacter(KeyboardInput.trimKey(text));
    }

    private void collapseT9(View root) {
        root.findViewById(R.id.view_select).setVisibility(GONE);
        View key = root.findViewById(R.id.button_key);
        key.setVisibility(VISIBLE);
        key.requestFocus();
        expandedKey = null;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (dispatchDelete
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_DEL
                && listener != null) listener.onDelete();
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && t9Mode
                && expandedKey != null
                && expandedKey.findViewById(R.id.view_select).getVisibility() == VISIBLE) {
            collapseT9(expandedKey);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void setDispatchKeyDel(boolean dispatch) {
        dispatchDelete = dispatch;
    }

    public void setKeySelectListener(KeySelectListener listener) {
        this.listener = listener;
    }

    public void setSquareKey(boolean square) {
        squareKey = square;
    }

    public boolean isT9Mode() {
        return t9Mode;
    }

    /** Original helper entry points retained. */
    public void a() {
        setMode(false, false);
    }

    public void b() {
        setMode(true, false);
    }
}
