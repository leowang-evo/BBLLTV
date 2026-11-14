package com.xx.blbl.ui.live;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.xx.blbl.R;
import com.xx.blbl.model.live.LiveAreaCategoryParent;
import com.xx.blbl.ui.adapter.LaneItemAdapter;
import com.xx.blbl.ui.screen.BaseScreenFragment;
import com.xx.blbl.ui.view.WrapContentGirdLayoutManager;

public final class LiveCategoriesFragment extends BaseScreenFragment {
    public RecyclerView f12097G0;
    public LiveAreaCategoryParent f12098H0;
    public LaneItemAdapter f12099I0;

    @Override
    public int layoutResource() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void initializeScreen(Bundle state) {
        super.initializeScreen(state);
        Bundle args = getArguments();
        if (args != null && args.getSerializable("category") instanceof LiveAreaCategoryParent)
            f12098H0 = (LiveAreaCategoryParent) args.getSerializable("category");
    }

    @Override
    public void bindScreen(View view) {
        f12097G0 = view.findViewById(R.id.recyclerView);
        f12099I0 = new LaneItemAdapter(2);
        f12097G0.setLayoutManager(new WrapContentGirdLayoutManager(view.getContext(), 8));
        f12097G0.setAdapter(f12099I0);
        f12097G0.post(
                () -> {
                    if (f12098H0 != null && f12098H0.getList() != null)
                        f12099I0.a(f12098H0.getList());
                });
    }
}
