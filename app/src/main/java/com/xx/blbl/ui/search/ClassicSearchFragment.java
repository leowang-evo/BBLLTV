package com.xx.blbl.ui.search;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.search.HotWordModel;
import com.xx.blbl.model.search.SearchAllResponseData;
import com.xx.blbl.model.search.SearchCategoryItem;
import com.xx.blbl.model.search.SearchVideoOrder;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;
import com.xx.blbl.ui.adapter.SearchSuggestionsAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

/** Text-input search screen with the original result/history navigation states. */
public final class ClassicSearchFragment extends BaseScreenFragment
        implements View.OnClickListener, MainActivity.BackHandler {
    public RecyclerView f9560G0, f9561H0;
    public AppCompatImageView f9562I0, f9563K0;
    public AppCompatEditText J0;
    public LinearLayoutCompat f9564L0, f9568R0;
    public AppCompatTextView M0, f9569S0;
    public FlexboxLayout f9565N0;
    public ConstraintLayout O0;
    public TabLayout f9566P0;
    public ViewPager2 f9567Q0;
    public SearchSuggestionsAdapter T0, f9570U0;
    public ScreenContentAdapter f9571V0;
    public final Lazy<NetworkManager> f9572W0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f9573X0 = LazyKt.lazy(AppController::state);
    public String f9575Z0 = "";
    public final ArrayList<String> f9576a1 = new ArrayList<>();
    public int f9577b1;
    private TabLayoutMediator mediator;
    private final SearchPageSupport searches =
            new SearchPageSupport(
                    this,
                    f9576a1,
                    new SearchPageSupport.Ui() {
                        @Override
                        public void hotWords(List<HotWordModel> words) {
                            if (T0 != null) T0.a(words);
                        }

                        @Override
                        public void suggestions(List<HotWordModel> words) {
                            if (f9570U0 != null) f9570U0.a(words);
                            if (f9561H0 != null && !words.isEmpty())
                                f9561H0.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void historyChanged() {
                            renderHistory();
                        }

                        @Override
                        public void results(String keyword, SearchAllResponseData data) {
                            f9577b1 = 0;
                            if (contextOrNull() != null && f9571V0 != null)
                                f9571V0.a(contextOrNull(), keyword, data);
                            if (f9564L0 != null) f9564L0.setVisibility(View.GONE);
                            if (O0 != null) O0.setVisibility(View.VISIBLE);
                            if (f9561H0 != null) f9561H0.setVisibility(View.GONE);
                            hideKeyboard();
                        }
                    });

    @Override
    public int layoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void bindScreen(View view) {
        f9560G0 = view.findViewById(R.id.recyclerViewHotSearch);
        f9561H0 = view.findViewById(R.id.recyclerViewSuggest);
        f9564L0 = view.findViewById(R.id.view_hot_search);
        O0 = view.findViewById(R.id.view_search_result);
        f9566P0 = view.findViewById(R.id.tab_search_result);
        f9567Q0 = view.findViewById(R.id.view_pager_result);
        f9568R0 = view.findViewById(R.id.button_order);
        f9569S0 = view.findViewById(R.id.text_order);
        M0 = view.findViewById(R.id.title_recent);
        f9565N0 = view.findViewById(R.id.view_flex_layout);
        T0 = new SearchSuggestionsAdapter(0);
        f9570U0 = new SearchSuggestionsAdapter(2);
        T0.f10991c = (cell, position) -> select(cell);
        f9570U0.f10991c = (cell, position) -> select(cell);
        if (f9560G0 != null) {
            f9560G0.setAdapter(T0);
            f9560G0.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        }
        if (f9561H0 != null) {
            f9561H0.setAdapter(f9570U0);
            f9561H0.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
        f9562I0 = view.findViewById(R.id.button_back_1);
        f9563K0 = view.findViewById(R.id.button_clear);
        if (f9562I0 != null) f9562I0.setOnClickListener(this);
        if (f9563K0 != null) f9563K0.setOnClickListener(this);
        if (f9568R0 != null) f9568R0.setOnClickListener(this);
        J0 = view.findViewById(R.id.edit_search);
        if (J0 != null) {
            J0.addTextChangedListener(
                    new TextWatcher() {
                        @Override
                        public void beforeTextChanged(
                                CharSequence text, int start, int count, int after) {}

                        @Override
                        public void onTextChanged(
                                CharSequence text, int start, int before, int count) {
                            if (text.length() > 1) searches.suggest(text.toString());
                            else {
                                searches.cancelSuggestions();
                                if (f9561H0 != null) f9561H0.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable value) {}
                    });
            J0.setOnFocusChangeListener(
                    (field, focused) -> {
                        if (focused) {
                            InputMethodManager input =
                                    (InputMethodManager)
                                            field.getContext()
                                                    .getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (input != null)
                                input.showSoftInput(field, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });
            J0.setOnEditorActionListener(
                    (field, action, event) -> {
                        if (action == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
                                || event != null
                                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                            f9577b1 = 0;
                            j0();
                            return true;
                        }
                        return false;
                    });
        }
        f9571V0 = new ScreenContentAdapter(1);
        if (f9567Q0 != null) {
            f9567Q0.setAdapter(f9571V0);
            f9567Q0.registerOnPageChangeCallback(
                    new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            if (f9568R0 != null)
                                f9568R0.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
                        }
                    });
            if (f9566P0 != null) {
                mediator =
                        new TabLayoutMediator(
                                f9566P0,
                                f9567Q0,
                                (tab, position) ->
                                        tab.setText(
                                                ((SearchCategoryItem)
                                                                ((java.util.List) f9571V0.f10356b)
                                                                        .get(position))
                                                        .getShowText()));
                mediator.attach();
            }
            f9567Q0.post(
                    () -> {
                        String initial =
                                getArguments() == null ? null : getArguments().getString("keyword");
                        if (!TextUtils.isEmpty(initial) && J0 != null) {
                            J0.setText(initial);
                            j0();
                        }
                    });
        }
        searches.loadHotWords();
        searches.loadHistory();
    }

    private void select(View cell) {
        if (cell != null && cell.getTag() instanceof String) {
            if (J0 != null) J0.setText((String) cell.getTag());
            f9577b1 = 2;
            j0();
        }
    }

    private void hideKeyboard() {
        if (J0 == null) return;
        InputMethodManager input =
                (InputMethodManager) J0.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (input != null) input.hideSoftInputFromWindow(J0.getWindowToken(), 0);
    }

    public void j0() {
        String keyword = J0 == null || J0.getText() == null ? "" : J0.getText().toString();
        if (TextUtils.isEmpty(keyword)) {
            if (contextOrNull() != null)
                Toast.makeText(contextOrNull(), R.string.empty_keyword, Toast.LENGTH_SHORT).show();
            return;
        }
        f9575Z0 = keyword;
        searches.search(keyword);
    }

    public void loadRecentSearch() {
        searches.loadHistory();
    }

    private void renderHistory() {
        if (M0 != null) M0.setVisibility(f9576a1.isEmpty() ? View.GONE : View.VISIBLE);
        if (f9565N0 == null || contextOrNull() == null) return;
        f9565N0.removeAllViews();
        for (int index = f9576a1.size() - 1; index >= 0; index--) {
            String term = f9576a1.get(index);
            TextView chip = new TextView(contextOrNull());
            chip.setText(term);
            chip.setTag(term);
            chip.setFocusable(true);
            chip.setClickable(true);
            TypedValue color = new TypedValue();
            if (contextOrNull()
                    .getTheme()
                    .resolveAttribute(android.R.attr.textColorPrimary, color, true)) {
                if (color.resourceId != 0)
                    chip.setTextColor(
                            androidx.core.content.ContextCompat.getColorStateList(
                                    contextOrNull(), color.resourceId));
                else chip.setTextColor(color.data);
            }
            chip.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.px30));
            chip.setLayoutParams(
                    new FlexboxLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            chip.setPadding(20, 10, 20, 10);
            chip.setBackgroundResource(R.drawable.cell_background);
            chip.setOnClickListener(this::select);
            f9565N0.addView(chip);
        }
    }

    @Override
    public void reloadContent() {
        j0();
    }

    @Override
    public void onClick(View view) {
        if (view == f9562I0) {
            MainActivity activity = hostActivity();
            if (activity != null) activity.onBackPressed();
        } else if (view == f9563K0) {
            if (J0 != null && J0.getText() != null) J0.getText().clear();
        } else if (view == f9568R0) {
            SearchVideoOrder[] orders = SearchVideoOrder.values();
            String[] names = new String[orders.length];
            for (int index = 0; index < names.length; index++)
                names[index] = orders[index].getShowName();
            new AlertDialog.Builder(requireContext())
                    .setTitle(R.string.video_order)
                    .setItems(
                            names,
                            (dialog, position) -> {
                                if (f9571V0 != null) f9571V0.c(orders[position]);
                                if (f9569S0 != null) f9569S0.setText(names[position]);
                                dialog.dismiss();
                            })
                    .show();
        }
    }

    @Override
    public boolean handleBack() {
        if (O0 != null && O0.getVisibility() == View.VISIBLE) {
            O0.setVisibility(View.GONE);
            if (f9564L0 != null) f9564L0.setVisibility(View.VISIBLE);
            searches.loadHistory();
            return true;
        }
        if (f9561H0 != null && f9561H0.getVisibility() == View.VISIBLE) {
            f9561H0.setVisibility(View.GONE);
            if (f9564L0 != null) f9564L0.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    @Override
    public void releaseScreenView() {
        searches.dispose();
        if (mediator != null) mediator.detach();
        mediator = null;
        f9560G0 = null;
        f9561H0 = null;
        f9562I0 = null;
        f9563K0 = null;
        J0 = null;
        f9564L0 = null;
        M0 = null;
        f9565N0 = null;
        O0 = null;
        f9566P0 = null;
        f9567Q0 = null;
        f9568R0 = null;
        f9569S0 = null;
        T0 = null;
        f9570U0 = null;
        f9571V0 = null;
        super.releaseScreenView();
    }
}
