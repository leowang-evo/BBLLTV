package com.xx.blbl.ui.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.xx.blbl.model.CategoryModel;
import com.xx.blbl.model.live.LiveAreaCategoryParent;

import java.util.ArrayList;

/** Application page selection; upstream FragmentStatePagerAdapter owns saved state. */
@SuppressWarnings("deprecation")
public final class TabPagerAdapter extends FragmentStatePagerAdapter {
    public final FragmentManager fragmentManager;
    public final int tabKind;
    public ArrayList pages = new ArrayList();

    public TabPagerAdapter(FragmentManager manager, int type) {
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentManager = manager;
        this.tabKind = type;
    }

    public int pageCount() {
        return getCount();
    }

    public String pageTitle(int position) {
        return getPageTitle(position).toString();
    }

    public void refreshPages() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.pages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < 0 || position >= this.pages.size()) return "";
        Object model = this.pages.get(position);
        return this.tabKind == 2
                ? ((LiveAreaCategoryParent) model).getName()
                : ((CategoryModel) model).getName();
    }

    private Fragment create(String name, Bundle arguments) {
        Fragment result =
                this.fragmentManager
                        .getFragmentFactory()
                        .instantiate(TabPagerAdapter.class.getClassLoader(), name);
        if (arguments != null) result.setArguments(arguments);
        return result;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0 || position >= getCount())
            throw new IndexOutOfBoundsException("page=" + position);
        Bundle arguments = new Bundle();
        switch (this.tabKind) {
            case 0:
                arguments.putSerializable("model", (CategoryModel) this.pages.get(position));
                return create("com.xx.blbl.ui.catalog.RankingFragment", arguments);
            case 1:
                if (position == 0)
                    return create("com.xx.blbl.ui.home.RecommendedVideosFragment", null);
                if (position == 1) return create("com.xx.blbl.ui.home.PopularVideosFragment", null);
                arguments.putInt("type", position == 2 ? 1 : 2);
                return create("com.xx.blbl.ui.home.SeriesLanesFragment", arguments);
            case 2:
                if (position == 0)
                    return create("com.xx.blbl.ui.live.LiveRecommendationsFragment", null);
                arguments.putSerializable(
                        "category", (LiveAreaCategoryParent) this.pages.get(position));
                return create("com.xx.blbl.ui.live.LiveCategoriesFragment", arguments);
            default:
                if (position == 0) return create("com.xx.blbl.ui.user.HistoryFragment", null);
                if (position == 1)
                    return create("com.xx.blbl.ui.user.FavoriteFoldersFragment", null);
                if (position == 4) return create("com.xx.blbl.ui.user.WatchLaterFragment", null);
                arguments.putInt("type", position == 2 ? 1 : 2);
                return create("com.xx.blbl.ui.user.FollowedSeriesFragment", arguments);
        }
    }
}
