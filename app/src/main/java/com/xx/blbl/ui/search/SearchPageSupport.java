package com.xx.blbl.ui.search;

import android.text.Html;

import com.xx.blbl.AppController;
import com.xx.blbl.model.search.HotWordModel;
import com.xx.blbl.model.search.SearchAllResponseData;
import com.xx.blbl.model.search.SearchSuggestModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.HotWordWrapper;
import com.xx.blbl.network.response.SearchSuggestWrapper;
import com.xx.blbl.platform.RequestMessages;
import com.xx.blbl.ui.screen.BaseScreenFragment;

import java.util.ArrayList;
import java.util.List;

/** Shared search calls and the original cache/history transition, using typed network APIs. */
final class SearchPageSupport {
    interface Ui {
        void hotWords(List<HotWordModel> words);

        void suggestions(List<HotWordModel> words);

        void historyChanged();

        void results(String keyword, SearchAllResponseData data);
    }

    private final BaseScreenFragment owner;
    private final ArrayList<String> history;
    private final Ui ui;
    private int request;
    private int suggestionRequest;
    private int lifetime;

    SearchPageSupport(BaseScreenFragment owner, ArrayList<String> history, Ui ui) {
        this.owner = owner;
        this.history = history;
        this.ui = ui;
    }

    void loadHotWords() {
        final int generation = lifetime;
        final android.view.View requestingView = owner.screenView;
        AppController.network()
                .getSearchHotWord(
                        new NetResultCallback<HotWordWrapper>() {
                            @Override
                            public void onFailure(Throwable failure) {}

                            @Override
                            public void onResponse(HotWordWrapper data) {
                                owner.runOnMain(
                                        () -> {
                                            if (generation == lifetime
                                                    && owner.screenView == requestingView
                                                    && requestingView != null
                                                    && owner.isAttachedToHost()
                                                    && data != null
                                                    && data.getList() != null)
                                                ui.hotWords(data.getList());
                                        });
                            }
                        });
    }

    void loadHistory() {
        final int generation = lifetime;
        final android.view.View requestingView = owner.screenView;
        if (!history.isEmpty()) {
            ui.historyChanged();
            return;
        }
        owner.runIo(
                () -> {
                    Object cached = owner.applicationState().cache.a("recentSearch");
                    ArrayList<String> restored = new ArrayList<>();
                    if (cached instanceof List)
                        for (Object item : (List<?>) cached)
                            if (item instanceof String) restored.add((String) item);
                    owner.runOnMain(
                            () -> {
                                if (generation != lifetime
                                        || !owner.isAttachedToHost()
                                        || owner.screenView != requestingView
                                        || requestingView == null) return;
                                if (!history.isEmpty()) {
                                    ui.historyChanged();
                                    return;
                                }
                                history.clear();
                                history.addAll(restored);
                                ui.historyChanged();
                            });
                });
    }

    void suggest(String term) {
        final int generation = ++suggestionRequest;
        final int viewLifetime = lifetime;
        AppController.network()
                .getSearchSuggest(
                        term,
                        new NetResultCallback<Base2Response<SearchSuggestWrapper>>() {
                            @Override
                            public void onFailure(Throwable failure) {}

                            @Override
                            public void onResponse(Base2Response<SearchSuggestWrapper> response) {
                                SearchSuggestWrapper wrapper =
                                        response == null ? null : response.getResult();
                                List<SearchSuggestModel> tags =
                                        wrapper == null ? null : wrapper.getTag();
                                if (tags == null) return;
                                List<HotWordModel> words = new ArrayList<>();
                                for (SearchSuggestModel suggestion : tags) {
                                    HotWordModel word = new HotWordModel();
                                    word.setKeyword(suggestion.getValue());
                                    word.setShow_name(
                                            Html.fromHtml(suggestion.getName()).toString());
                                    word.setHot_id("0");
                                    words.add(word);
                                }
                                owner.runOnMain(
                                        () -> {
                                            if (generation == suggestionRequest
                                                    && viewLifetime == lifetime
                                                    && owner.isAttachedToHost()
                                                    && owner.screenView != null)
                                                ui.suggestions(words);
                                        });
                            }
                        });
    }

    void search(String keyword) {
        suggestionRequest++;
        final int generation = ++request;
        owner.setLoading(true);
        AppController.network()
                .searchAll(
                        keyword,
                        new NetResultCallback<BaseResponse<SearchAllResponseData>>() {
                            @Override
                            public void onFailure(Throwable failure) {
                                owner.runOnMain(
                                        () -> {
                                            if (generation == request && owner.isAttachedToHost()) {
                                                owner.setLoading(false);
                                                owner.showError(
                                                        RequestMessages.error(
                                                                owner.requireContext(), failure));
                                            }
                                        });
                            }

                            @Override
                            public void onResponse(BaseResponse<SearchAllResponseData> response) {
                                owner.runOnMain(
                                        () -> {
                                            if (generation != request
                                                    || !owner.isAttachedToHost()
                                                    || owner.screenView == null) return;
                                            owner.setLoading(false);
                                            com.xx.blbl.network.ApiFailure failure =
                                                    com.xx.blbl.network.ApiFailure.fromResponse(
                                                            response);
                                            if (failure != null) {
                                                owner.showError(
                                                        RequestMessages.error(
                                                                owner.requireContext(), failure));
                                                return;
                                            }
                                            owner.showContent();
                                            history.remove(keyword);
                                            int length = 0;
                                            for (String old : history) length += old.length();
                                            if (length > 90) history.remove(0);
                                            history.add(keyword);
                                            ArrayList<String> snapshot = new ArrayList<>(history);
                                            owner.runIo(
                                                    () ->
                                                            owner.applicationState()
                                                                    .cache
                                                                    .b("recentSearch", snapshot));
                                            SearchAllResponseData data =
                                                    response == null ? null : response.getData();
                                            if (data != null) ui.results(keyword, data);
                                        });
                            }
                        });
    }

    void cancelSuggestions() {
        suggestionRequest++;
    }

    void dispose() {
        request++;
        suggestionRequest++;
        lifetime++;
    }
}
