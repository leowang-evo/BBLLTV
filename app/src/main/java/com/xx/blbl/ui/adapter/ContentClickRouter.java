package com.xx.blbl.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.xx.blbl.R;
import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.live.LiveAreaCategory;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.model.user.UserInfoModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.live.LiveAreaFragment;
import com.xx.blbl.ui.navigation.ScreenNavigator;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

/** Only application item click/focus branches from the former R8 mixed dispatcher. */
public final class ContentClickRouter implements ItemClickCallback, ItemFocusCallback {
    public final int f10353a;
    public Object f10354b;

    public ContentClickRouter(Object target, int variant) {
        this.f10354b = target;
        this.f10353a = variant;
    }

    public void onClick(View view, int i4) {
        Context context;
        switch (this.f10353a) {
            case 0:
                ItemClickCallback cVar =
                        (ItemClickCallback) ((ScreenContentAdapter) this.f10354b).f10357c;
                if (cVar != null) {
                    cVar.onClick(view, i4);
                }
                break;
            case 3:
                DynamicFeedAdapter choices = (DynamicFeedAdapter) this.f10354b;
                if (choices.d != null) choices.d.onClick(view, i4);
                break;
            case 5:
                HistoryVideoAdapter aVar = (HistoryVideoAdapter) this.f10354b;
                HistoryVideoModel historyVideoModel = (HistoryVideoModel) aVar.itemAt(i4);
                if (historyVideoModel != null) {
                    if (((AppState) ((kotlin.Lazy) aVar.e).getValue()).continuationMode
                            == ContinuationPlayEnum.CurrentList.getOrder()) {
                        int i7 = 1 + i4;
                        ArrayList arrayList = aVar.items;
                        if (i7 < arrayList.size()) {
                            ArrayList arrayList2 =
                                    new ArrayList(arrayList.subList(i7, arrayList.size()));
                            Object obj2 = ScreenNavigator.f738a;
                            Context context2 = view != null ? view.getContext() : null;
                            Object obj3 = arrayList.get(i4);
                            Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                            ScreenNavigator.openHistoryPlaylist(
                                    context2, (HistoryVideoModel) obj3, arrayList2);
                            return;
                        }
                    }
                    Object obj4 = ScreenNavigator.f738a;
                    ScreenNavigator.openHistoryVideo(
                            view != null ? view.getContext() : null, historyVideoModel);
                }
                break;
            case 6:
                LaneItemAdapter fVar = (LaneItemAdapter) this.f10354b;
                long id = ((LiveAreaCategory) fVar.f10345b.get(i4)).getId();
                ArrayList arrayList3 = fVar.f10345b;
                if (((LiveAreaCategory) arrayList3.get(i4)).getArea_v2_id() != 0) {
                    id = ((LiveAreaCategory) arrayList3.get(i4)).getArea_v2_id();
                }
                long parent_id = ((LiveAreaCategory) arrayList3.get(i4)).getParent_id();
                if (((LiveAreaCategory) arrayList3.get(i4)).getArea_v2_parent_id() != 0) {
                    parent_id = ((LiveAreaCategory) arrayList3.get(i4)).getArea_v2_parent_id();
                }
                String title = ((LiveAreaCategory) arrayList3.get(i4)).getTitle();
                if (!TextUtils.isEmpty(((LiveAreaCategory) arrayList3.get(i4)).getName())) {
                    title = ((LiveAreaCategory) arrayList3.get(i4)).getName();
                }
                Object obj5 = ScreenNavigator.f738a;
                Context context3 = view != null ? view.getContext() : null;
                Intrinsics.checkNotNullParameter(title, "title");
                if (context3 instanceof MainActivity) {
                    LiveAreaFragment bVar = new LiveAreaFragment();
                    Bundle bundle = new Bundle();
                    bundle.putLong("areaId", id);
                    bundle.putLong("areaParentId", parent_id);
                    bundle.putString("title", title);
                    bVar.setArguments(bundle);
                    ((MainActivity) context3).showScreen(bVar, "liveCategoryDetail");
                }
                break;
            case 10:
                ItemClickCallback listener = ((SearchSuggestionsAdapter) this.f10354b).f10991c;
                if (listener != null) listener.onClick(view, i4);
                break;
            case 15:
                if ((view != null ? view.getContext() : null) instanceof MainActivity) {
                    Object obj6 = ScreenNavigator.f738a;
                    Context context4 = view.getContext();
                    Object obj7 = ((SeriesEpisodesAdapter) this.f10354b).f11055a.get(i4);
                    Intrinsics.checkNotNullExpressionValue(obj7, "get(...)");
                    EpisodeModel episodeModel = (EpisodeModel) obj7;
                    VideoModel videoModel3 = new VideoModel();
                    videoModel3.setTitle(episodeModel.getLong_title());
                    videoModel3.setPic(episodeModel.getCover());
                    videoModel3.setAid(episodeModel.getAid());
                    videoModel3.setBvid(episodeModel.getBvid());
                    videoModel3.setCid(episodeModel.getCid());
                    videoModel3.setPubdate(episodeModel.getPub_time());
                    videoModel3.setRedirect_url(episodeModel.getShare_url());
                    ScreenNavigator.openVideo(context4, videoModel3, false);
                }
                break;
            case 17:
                SeriesHeaderAdapter cVar2 = (SeriesHeaderAdapter) this.f10354b;
                if (((SeriesModel) cVar2.f11058a.get(i4)).getSeason_id() != cVar2.f11059b) {
                    Object obj8 = ScreenNavigator.f738a;
                    Context context5 = view != null ? view.getContext() : null;
                    ArrayList arrayList4 = cVar2.f11058a;
                    ScreenNavigator.openSeries(
                            context5,
                            Long.valueOf(((SeriesModel) arrayList4.get(i4)).getSeason_id()),
                            null,
                            ((SeriesModel) arrayList4.get(i4)).getSeason_title());
                    break;
                } else if (view != null && (context = view.getContext()) != null) {
                    UiSupport.toast(context, context.getString(R.string.series_already_open));
                    break;
                }
                break;
            case 20:
                UserInfoModel userInfoModel =
                        (UserInfoModel) ((CatalogAdapter) this.f10354b).itemAt(i4);
                if (userInfoModel != null) {
                    Object obj9 = ScreenNavigator.f738a;
                    ScreenNavigator.openUserSpace(
                            view != null ? view.getContext() : null,
                            userInfoModel.getMid(),
                            userInfoModel.getUname(),
                            userInfoModel.getFace());
                }
                break;
            case 21:
                int i8 = i4 - 1;
                if (i8 >= 0) {
                    UserSpaceAdapter aVar3 = (UserSpaceAdapter) this.f10354b;
                    if (i8 < aVar3.f11500a.size()) {
                        int i9 =
                                ((AppState) ((kotlin.Lazy) aVar3.f11501b).getValue())
                                        .continuationMode;
                        int order = ContinuationPlayEnum.CurrentList.getOrder();
                        ArrayList arrayList5 = aVar3.f11500a;
                        if (i9 != order) {
                            Object obj10 = ScreenNavigator.f738a;
                            Context context6 = view != null ? view.getContext() : null;
                            Object obj11 = arrayList5.get(i8);
                            Intrinsics.checkNotNullExpressionValue(obj11, "get(...)");
                            ScreenNavigator.openVideo(context6, (VideoModel) obj11, true);
                        } else {
                            ArrayList arrayList6 =
                                    new ArrayList(arrayList5.subList(i4, arrayList5.size()));
                            Object obj12 = ScreenNavigator.f738a;
                            Context context7 = view != null ? view.getContext() : null;
                            Object obj13 = arrayList5.get(i8);
                            Intrinsics.checkNotNullExpressionValue(obj13, "get(...)");
                            ScreenNavigator.openPlaylist(
                                    context7, (VideoModel) obj13, arrayList6, true);
                        }
                    }
                }
                break;
            default:
                ItemClickCallback gVar = ((OwnerVideosAdapter) this.f10354b).f11905b;
                if (gVar != null) {
                    gVar.onClick(view, i4);
                }
                break;
        }
    }

    public void onFocusChange(View view, int i4, boolean z7) {
        switch (this.f10353a) {
            case 1:
                ((CatalogAdapter) this.f10354b).lastFocusedItem = view;
                break;
            case 2:
                ((DynamicVideoFeedAdapter) this.f10354b).lastFocusedItem = view;
                break;
            case 9:
                ((CatalogAdapter) this.f10354b).lastFocusedItem = view;
                break;
            case 11:
                ((CatalogAdapter) this.f10354b).lastFocusedItem = view;
                break;
            case 12:
                ((VideoGridAdapter) this.f10354b).lastFocusedItem = view;
                break;
            case 13:
                ItemFocusCallback c0217t = ((SeriesFilterAdapter) this.f10354b).f11054c;
                if (c0217t != null) {
                    c0217t.onFocusChange(view, i4, z7);
                }
                break;
            case 14:
                ((CatalogAdapter) this.f10354b).lastFocusedItem = view;
                break;
            default:
                ItemFocusCallback c0170l =
                        (ItemFocusCallback) ((ScreenContentAdapter) this.f10354b).f10357c;
                if (c0170l != null) {
                    c0170l.onFocusChange(view, i4, z7);
                }
                break;
        }
    }
}
