package com.xx.blbl.ui.search;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.search.HotWordModel;
import com.xx.blbl.model.search.SearchAllResponseData;
import com.xx.blbl.model.search.SearchVideoOrder;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.adapter.ItemClickCallback;
import com.xx.blbl.ui.adapter.ScreenContentAdapter;
import com.xx.blbl.ui.adapter.SearchSuggestionsAdapter;
import com.xx.blbl.ui.screen.TabScreenFragment;
import com.xx.blbl.ui.view.keyboard.KeyboardInput;
import com.xx.blbl.ui.view.keyboard.KeyboardView;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.util.ArrayList;
import java.util.List;

/** Remote-keyboard search tab, preserving hot words, recent terms and result categories. */
public final class SearchFragment extends TabScreenFragment
        implements KeyboardView.KeySelectListener, View.OnClickListener, MainActivity.BackHandler {
    public AppCompatEditText f9583G0;
    public KeyboardView f9584H0;
    public RecyclerView f9585I0, J0;
    public SearchSuggestionsAdapter f9586K0, f9587L0;
    public ConstraintLayout M0;
    public TabLayout f9588N0;
    public ViewPager2 O0;
    public LinearLayoutCompat f9589P0;
    public AppCompatTextView f9590Q0;
    public ScreenContentAdapter f9591R0;
    public final Lazy<NetworkManager> f9592S0 = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> T0 = LazyKt.lazy(AppController::state);
    public String f9593U0 = "";
    public final ArrayList<String> f9594V0 = new ArrayList<>();
    public final ItemClickCallback f9595W0 = (view, position) -> select(view);
    private TabLayoutMediator mediator;
    private final SearchPageSupport searches =
            new SearchPageSupport(
                    this,
                    f9594V0,
                    new SearchPageSupport.Ui() {
                        @Override
                        public void hotWords(List<HotWordModel> words) {
                            if (f9587L0 != null) f9587L0.a(words);
                        }

                        @Override
                        public void suggestions(List<HotWordModel> words) {
                            if (f9586K0 != null) f9586K0.a(words);
                            if (f9585I0 != null && !words.isEmpty())
                                f9585I0.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void historyChanged() {
                            j0(SearchFragment.this);
                        }

                        @Override
                        public void results(String keyword, SearchAllResponseData data) {
                            if (contextOrNull() != null && f9591R0 != null)
                                f9591R0.a(contextOrNull(), keyword, data);
                            n0(true);
                        }
                    });

    public static void j0(SearchFragment owner) {
        List<HotWordModel> words = new ArrayList<>();
        for (String term : owner.f9594V0) {
            HotWordModel word = new HotWordModel();
            word.setKeyword(term);
            word.setShow_name(term);
            word.setHot_id("-1");
            words.add(word);
        }
        if (owner.f9586K0 != null) owner.f9586K0.a(words);
        if (owner.f9585I0 != null)
            owner.f9585I0.setVisibility(words.isEmpty() ? View.GONE : View.VISIBLE);
    }

    @Override
    public int layoutResource() {
        return R.layout.fragment_search_new;
    }

    @Override
    public void bindScreen(View view) {
        f9583G0 = view.findViewById(R.id.edit_text);
        f9584H0 = view.findViewById(R.id.view_keyboard);
        if (f9584H0 != null) f9584H0.setKeySelectListener(this);
        f9585I0 = view.findViewById(R.id.recycler_view_center);
        J0 = view.findViewById(R.id.recycler_view_end);
        f9586K0 = new SearchSuggestionsAdapter(1);
        f9587L0 = new SearchSuggestionsAdapter(1);
        if (f9585I0 != null) {
            f9585I0.setLayoutManager(new LinearLayoutManager(view.getContext()));
            f9585I0.setAdapter(f9586K0);
        }
        if (J0 != null) {
            J0.setLayoutManager(new LinearLayoutManager(view.getContext()));
            J0.setAdapter(f9587L0);
        }
        f9586K0.f10991c = f9595W0;
        f9587L0.f10991c = f9595W0;
        M0 = view.findViewById(R.id.view_search_result);
        f9588N0 = view.findViewById(R.id.tab_search_result);
        O0 = view.findViewById(R.id.view_pager_result);
        f9589P0 = view.findViewById(R.id.button_order);
        f9590Q0 = view.findViewById(R.id.text_order);
        if (f9589P0 != null) f9589P0.setOnClickListener(this);
        f9591R0 = new ScreenContentAdapter(1);
        if (O0 != null) {
            O0.setAdapter(f9591R0);
            O0.registerOnPageChangeCallback(
                    new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            if (f9589P0 != null)
                                f9589P0.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
                        }
                    });
            if (f9588N0 != null) {
                mediator =
                        new TabLayoutMediator(
                                f9588N0,
                                O0,
                                (tab, position) ->
                                        tab.setText(
                                                ((com.xx.blbl.model.search.SearchCategoryItem)
                                                                ((java.util.List) f9591R0.f10356b)
                                                                        .get(position))
                                                        .getShowText()));
                mediator.attach();
            }
            O0.post(
                    () -> {
                        String initial =
                                getArguments() == null ? null : getArguments().getString("keyword");
                        if (!TextUtils.isEmpty(initial)) {
                            f9593U0 = initial;
                            if (f9583G0 != null) f9583G0.setText(initial);
                            o0();
                        }
                    });
        }
        if (f9583G0 != null)
            f9583G0.setOnEditorActionListener(
                    (field, action, event) -> {
                        if (action == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
                                || event != null
                                        && event.getKeyCode() == android.view.KeyEvent.KEYCODE_ENTER
                                        && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                            onSearch();
                            return true;
                        }
                        return false;
                    });
        searches.loadHotWords();
        k0();
    }

    private void select(View view) {
        if (view != null && view.getTag() instanceof String) {
            f9593U0 = (String) view.getTag();
            if (f9583G0 != null) f9583G0.setText(f9593U0);
            o0();
        }
    }

    public void k0() {
        searches.loadHistory();
    }

    public void l0() {
        onDelete();
    }

    public void m0(String character) {
        onCharacter(character);
    }

    public void n0(boolean results) {
        if (M0 != null) M0.setVisibility(results ? View.VISIBLE : View.GONE);
        if (f9584H0 != null) f9584H0.setVisibility(results ? View.GONE : View.VISIBLE);
        if (f9585I0 != null) f9585I0.setVisibility(results ? View.GONE : View.VISIBLE);
        if (J0 != null) J0.setVisibility(results ? View.GONE : View.VISIBLE);
    }

    public void o0() {
        if (TextUtils.isEmpty(f9593U0)) {
            if (contextOrNull() != null)
                Toast.makeText(contextOrNull(), R.string.empty_keyword, Toast.LENGTH_SHORT).show();
            return;
        }
        searches.search(f9593U0);
    }

    @Override
    public void onCharacter(String character) {
        String term =
                f9583G0 == null || f9583G0.getText() == null
                        ? ""
                        : KeyboardInput.trimKey(f9583G0.getText().toString());
        if (term.equals(getString(R.string.search))) term = "";
        term += character;
        if (f9583G0 != null) f9583G0.setText(term);
        if (term.length() > 1) searches.suggest(term);
    }

    @Override
    public void onDelete() {
        String term =
                f9583G0 == null || f9583G0.getText() == null
                        ? ""
                        : KeyboardInput.trimKey(f9583G0.getText().toString());
        if (!term.isEmpty()) term = term.substring(0, term.length() - 1);
        if (f9583G0 != null) f9583G0.setText(term);
        if (term.length() <= 1) searches.cancelSuggestions();
        if (term.isEmpty()) k0();
    }

    @Override
    public void onClear() {
        searches.cancelSuggestions();
        if (f9583G0 != null) f9583G0.setText("");
        k0();
    }

    @Override
    public void onSearch() {
        f9593U0 =
                f9583G0 == null || f9583G0.getText() == null
                        ? ""
                        : KeyboardInput.trimKey(f9583G0.getText().toString());
        o0();
    }

    @Override
    public void reloadContent() {
        o0();
    }

    @Override
    public void onClick(View view) {
        SearchVideoOrder[] orders = SearchVideoOrder.values();
        String[] names = new String[orders.length];
        for (int i = 0; i < names.length; i++) names[i] = orders[i].getShowName();
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.video_order)
                .setItems(
                        names,
                        (dialog, position) -> {
                            if (f9591R0 != null) f9591R0.c(orders[position]);
                            if (f9590Q0 != null) f9590Q0.setText(names[position]);
                            dialog.dismiss();
                        })
                .show();
    }

    @Override
    public boolean handleBack() {
        if (M0 != null && M0.getVisibility() == View.VISIBLE) {
            n0(false);
            if (f9583G0 != null) f9583G0.setText("");
            k0();
        } else {
            MainActivity activity = hostActivity();
            if (activity == null) return false;
            activity.selectTab(0);
        }
        return true;
    }

    @Override
    public void releaseScreenView() {
        searches.dispose();
        if (mediator != null) mediator.detach();
        mediator = null;
        if (f9584H0 != null) f9584H0.setKeySelectListener(null);
        f9583G0 = null;
        f9584H0 = null;
        f9585I0 = null;
        J0 = null;
        M0 = null;
        f9588N0 = null;
        O0 = null;
        f9589P0 = null;
        f9590Q0 = null;
        f9586K0 = null;
        f9587L0 = null;
        f9591R0 = null;
        super.releaseScreenView();
    }
}
