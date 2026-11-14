package com.xx.blbl.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.xx.blbl.R;
import com.xx.blbl.model.search.HotWordModel;
import com.xx.blbl.model.search.SearchSuggestModel;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.ui.holder.SearchCategoryViewHolder;
import com.xx.blbl.ui.holder.SearchSuggestionViewHolder;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SearchSuggestionsAdapter extends Adapter {
    public final /* synthetic */ int f10989a;
    public final ArrayList f10990b;
    public ItemClickCallback f10991c;
    public final ItemClickCallback d;

    public SearchSuggestionsAdapter(int i4) {
        this.f10989a = i4;
        switch (i4) {
            case 1:
                this.f10990b = new ArrayList();
                this.d = new ContentClickRouter(this, 10);
                break;
            case 2:
                this.f10990b = new ArrayList();
                this.d = new CatalogClickRouter(this, 7);
                break;
            default:
                this.f10990b = new ArrayList();
                this.d = new CatalogClickRouter(this, 5);
                break;
        }
    }

    public void a(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = this.f10990b;
        int size = arrayList.size();
        arrayList.clear();
        notifyItemRangeRemoved(0, size);
        arrayList.addAll(list);
        notifyItemRangeChanged(0, arrayList.size());
    }

    @Override
    public final int getItemCount() {
        switch (this.f10989a) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f10990b.size();
    }

    @Override
    public final void onBindViewHolder(ViewHolder e0Var, int i4) {
        ArrayList arrayList = this.f10990b;
        switch (this.f10989a) {
            case 0:
                SearchSuggestionViewHolder holder = (SearchSuggestionViewHolder) e0Var;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Object obj = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                HotWordModel hotWordModel = (HotWordModel) obj;
                holder.f14864b.setTag(hotWordModel.getKeyword());
                holder.f14863a.setText(
                        String.format(
                                "%d、%s",
                                Arrays.copyOf(
                                        new Object[] {
                                            Integer.valueOf(hotWordModel.getPos()),
                                            hotWordModel.getShow_name()
                                        },
                                        2)));
                break;
            case 1:
                SearchCategoryViewHolder holder2 = (SearchCategoryViewHolder) e0Var;
                Intrinsics.checkNotNullParameter(holder2, "holder");
                Object obj2 = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                HotWordModel hotWordModel2 = (HotWordModel) obj2;
                holder2.f14867c.setTag(hotWordModel2.getKeyword());
                holder2.f14866b.setText(hotWordModel2.getShow_name());
                String hot_id = hotWordModel2.getHot_id();
                boolean zA = Intrinsics.areEqual(hot_id, "-1");
                AppCompatImageView appCompatImageView = holder2.f14865a;
                if (zA) {
                    appCompatImageView.setVisibility(0);
                    appCompatImageView.setImageResource(R.drawable.ic_history);
                } else if (!Intrinsics.areEqual(hot_id, "0")) {
                    appCompatImageView.setVisibility(0);
                    appCompatImageView.setImageResource(R.drawable.ic_hot);
                } else {
                    appCompatImageView.setVisibility(8);
                    appCompatImageView.setImageResource(R.drawable.ic_history);
                }
                break;
            default:
                SearchSuggestionViewHolder holder3 = (SearchSuggestionViewHolder) e0Var;
                Intrinsics.checkNotNullParameter(holder3, "holder");
                Object obj3 = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                SearchSuggestModel searchSuggestModel = (SearchSuggestModel) obj3;
                holder3.f14864b.setTag(searchSuggestModel.getValue());
                holder3.f14863a.setText(searchSuggestModel.getName());
                break;
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i4) {
        ItemClickCallback cVar = this.d;
        switch (this.f10989a) {
            case 0:
                View viewE =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_hot_word, viewGroup, false);
                int i7 = SearchSuggestionViewHolder.f14862c;
                Intrinsics.checkNotNull(viewE);
                CatalogClickRouter onItemClick = (CatalogClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
                return new SearchSuggestionViewHolder(viewE, onItemClick);
            case 1:
                View viewE2 =
                        UiSupport.inflate(
                                viewGroup,
                                "parent",
                                R.layout.cell_search_recently,
                                viewGroup,
                                false);
                int i8 = SearchCategoryViewHolder.d;
                Intrinsics.checkNotNull(viewE2);
                ContentClickRouter onItemClick2 = (ContentClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClick2, "onItemClick");
                return new SearchCategoryViewHolder(viewE2, onItemClick2);
            default:
                View viewE3 =
                        UiSupport.inflate(
                                viewGroup, "parent", R.layout.cell_hot_word, viewGroup, false);
                int i9 = SearchSuggestionViewHolder.f14862c;
                Intrinsics.checkNotNull(viewE3);
                CatalogClickRouter onItemClick3 = (CatalogClickRouter) cVar;
                Intrinsics.checkNotNullParameter(onItemClick3, "onItemClick");
                return new SearchSuggestionViewHolder(viewE3, onItemClick3);
        }
    }
}
