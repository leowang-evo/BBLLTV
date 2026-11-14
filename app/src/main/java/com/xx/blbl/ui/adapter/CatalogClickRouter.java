package com.xx.blbl.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.xx.blbl.model.ContinuationPlayEnum;
import com.xx.blbl.model.favorite.FavoriteFolderModel;
import com.xx.blbl.model.lane.LaneItemModel;
import com.xx.blbl.model.live.LiveRoomItem;
import com.xx.blbl.model.search.SearchItemModel;
import com.xx.blbl.model.series.timeline.SeriesTimeLineModel;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoPvModel;
import com.xx.blbl.platform.VideoModelMapper;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.catalog.VideoDetailFragment;
import com.xx.blbl.ui.navigation.ScreenNavigator;
import com.xx.blbl.ui.playback.PlaybackFragment;
import com.xx.blbl.ui.user.FavoriteFolderFragment;

import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

/** Only application item click/focus branches from the former R8 mixed dispatcher. */
public final class CatalogClickRouter implements ItemClickCallback, ItemFocusCallback {
    public final int f10358a;
    public Object f10359b;

    public CatalogClickRouter(Object target, int variant) {
        this.f10359b = target;
        this.f10358a = variant;
    }

    public void onClick(View view, int i4) {
        int i7;
        kotlin.Unit jVar = null;
        Object obj = this.f10359b;
        switch (this.f10358a) {
            case 0:
                if (i4 >= 0) {
                    DynamicVideoFeedAdapter lVar = (DynamicVideoFeedAdapter) obj;
                    if (i4 < lVar.items.size()) {
                        int i8 =
                                ((AppState) ((kotlin.Lazy) lVar.appState).getValue())
                                        .continuationMode;
                        int order = ContinuationPlayEnum.CurrentList.getOrder();
                        ArrayList arrayList = lVar.items;
                        if (i8 != order || (i7 = i4 + 1) >= arrayList.size()) {
                            Object obj2 = ScreenNavigator.f738a;
                            Context context = view != null ? view.getContext() : null;
                            Object obj3 = arrayList.get(i4);
                            Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                            ScreenNavigator.openVideo(context, (VideoModel) obj3, true);
                        } else {
                            ArrayList arrayList2 =
                                    new ArrayList(arrayList.subList(i7, arrayList.size()));
                            Object obj4 = ScreenNavigator.f738a;
                            Context context2 = view != null ? view.getContext() : null;
                            Object obj5 = arrayList.get(i4);
                            Intrinsics.checkNotNullExpressionValue(obj5, "get(...)");
                            ScreenNavigator.openPlaylist(
                                    context2, (VideoModel) obj5, arrayList2, true);
                        }
                    }
                }
                break;
            case 1:
            case 2:
            case 10:
            default:
                if ((view != null ? view.getContext() : null) instanceof MainActivity) {
                    ScreenContentAdapter jVar2 = (ScreenContentAdapter) obj;
                    VideoModel videoModel = (VideoModel) jVar2.f10357c;
                    if (videoModel != null) {
                        long cid = videoModel.getCid();
                        ArrayList arrayList3 = (ArrayList) jVar2.f10356b;
                        if (cid != ((VideoPvModel) arrayList3.get(i4)).getCid()) {
                            videoModel.setCid(((VideoPvModel) arrayList3.get(i4)).getCid());
                            videoModel.setProgress(0L);
                        }
                        List list = (List) jVar2.d;
                        if (list != null) {
                            Object obj6 = ScreenNavigator.f738a;
                            ScreenNavigator.openPlaylist(
                                    view.getContext(), videoModel, list, false);
                            jVar = kotlin.Unit.INSTANCE;
                        }
                        if (jVar == null) {
                            Object obj7 = ScreenNavigator.f738a;
                            ScreenNavigator.openVideo(view.getContext(), videoModel, false);
                        }
                    }
                }
                break;
            case 3:
                Object obj8 = ScreenNavigator.f738a;
                Context context3 = view != null ? view.getContext() : null;
                Object obj9 = ((LaneItemAdapter) obj).f10345b.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj9, "get(...)");
                FavoriteFolderModel favoriteFolderModel = (FavoriteFolderModel) obj9;
                if (context3 instanceof MainActivity) {
                    long id = favoriteFolderModel.getId();
                    String title = favoriteFolderModel.getTitle();
                    Intrinsics.checkNotNullParameter(title, "title");
                    FavoriteFolderFragment bVar = new FavoriteFolderFragment();
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", id);
                    bundle.putString("title", title);
                    bVar.setArguments(bundle);
                    ((MainActivity) context3).showScreen(bVar, "favoriteDetail");
                }
                break;
            case 4:
                LiveRoomItem liveRoomItem = (LiveRoomItem) ((CatalogAdapter) obj).itemAt(i4);
                if (liveRoomItem != null) {
                    Object obj10 = ScreenNavigator.f738a;
                    Context context4 = view != null ? view.getContext() : null;
                    VideoModel videoModel2 = new VideoModel();
                    videoModel2.setAid(liveRoomItem.getRoomid());
                    OwnerModel ownerModel = new OwnerModel();
                    ownerModel.setName(liveRoomItem.getUname());
                    videoModel2.setOwner(ownerModel);
                    videoModel2.setTitle(liveRoomItem.getTitle());
                    videoModel2.setRedirect_url(
                            "https://live.bilibili.com/" + liveRoomItem.getRoomid());
                    ScreenNavigator.openVideo(context4, videoModel2, false);
                }
                break;
            case 5:
                ItemClickCallback xVar =
                        (ItemClickCallback) ((SearchSuggestionsAdapter) obj).f10991c;
                if (xVar != null) {
                    xVar.onClick(view, i4);
                }
                break;
            case 6:
                SearchItemModel searchItemModel =
                        (SearchItemModel) ((CatalogAdapter) obj).itemAt(i4);
                if (searchItemModel != null) {
                    Object obj11 = ScreenNavigator.f738a;
                    ScreenNavigator.openSeries(
                            view != null ? view.getContext() : null,
                            Long.valueOf(searchItemModel.getPgc_season_id()),
                            null,
                            searchItemModel.getTitle());
                }
                break;
            case 7:
                ItemClickCallback vVar =
                        (ItemClickCallback) ((SearchSuggestionsAdapter) obj).f10991c;
                if (vVar != null) {
                    vVar.onClick(view, i4);
                }
                break;
            case 8:
                SearchItemModel searchItemModel2 =
                        (SearchItemModel) ((CatalogAdapter) obj).itemAt(i4);
                if (searchItemModel2 != null) {
                    Object obj12 = ScreenNavigator.f738a;
                    ScreenNavigator.openUserSpace(
                            view != null ? view.getContext() : null,
                            searchItemModel2.getMid(),
                            searchItemModel2.getUname(),
                            searchItemModel2.getUpic());
                }
                break;
            case 9:
                VideoGridAdapter bVar2 = (VideoGridAdapter) obj;
                SearchItemModel searchItemModel3 = (SearchItemModel) bVar2.itemAt(i4);
                if (searchItemModel3 != null) {
                    if (((AppState) ((kotlin.Lazy) bVar2.f10334f).getValue()).continuationMode
                            == ContinuationPlayEnum.CurrentList.getOrder()) {
                        int i9 = i4 + 1;
                        ArrayList arrayList4 = bVar2.items;
                        if (i9 < arrayList4.size()) {
                            List<SearchItemModel> listSubList =
                                    arrayList4.subList(i9, arrayList4.size());
                            Intrinsics.checkNotNullExpressionValue(listSubList, "subList(...)");
                            ArrayList arrayList5 = new ArrayList();
                            for (SearchItemModel searchItemModel4 : listSubList) {
                                if (searchItemModel4.getRoomid() != 0) {
                                    searchItemModel4.setArcurl(
                                            "https://live.bilibili.com/"
                                                    + searchItemModel4.getRoomid());
                                    searchItemModel4.setAid(searchItemModel4.getRoomid());
                                    searchItemModel4.setAuthor(searchItemModel4.getUname());
                                }
                                VideoModel videoModel3 = new VideoModel();
                                videoModel3.setBvid(searchItemModel4.getBvid());
                                videoModel3.setAid(searchItemModel4.getAid());
                                videoModel3.setPic("https:" + searchItemModel4.getPic());
                                videoModel3.setRedirect_url(searchItemModel4.getArcurl());
                                videoModel3.setTitle(
                                        Html.fromHtml(searchItemModel4.getTitle()).toString());
                                videoModel3.setOwner(new OwnerModel());
                                OwnerModel owner = videoModel3.getOwner();
                                if (owner != null) {
                                    owner.setMid(searchItemModel4.getMid());
                                }
                                OwnerModel owner2 = videoModel3.getOwner();
                                if (owner2 != null) {
                                    owner2.setFace(searchItemModel4.getUpic());
                                }
                                OwnerModel owner3 = videoModel3.getOwner();
                                if (owner3 != null) {
                                    owner3.setName(searchItemModel4.getAuthor());
                                }
                                arrayList5.add(videoModel3);
                            }
                            ArrayList arrayList6 = new ArrayList(arrayList5);
                            Object obj13 = ScreenNavigator.f738a;
                            ScreenNavigator.openPlaylist(
                                    view != null ? view.getContext() : null,
                                    VideoModelMapper.fromSearch(searchItemModel3),
                                    arrayList6,
                                    true);
                            return;
                        }
                    }
                    Object obj14 = ScreenNavigator.f738a;
                    Context context5 = view != null ? view.getContext() : null;
                    if (context5 instanceof MainActivity) {
                        if (((AppState) ((kotlin.Lazy) ScreenNavigator.f738a).getValue())
                                .showVideoDetails) {
                            VideoModel videoModelC = VideoModelMapper.fromSearch(searchItemModel3);
                            VideoDetailFragment pVar = new VideoDetailFragment();
                            Bundle bundle2 = new Bundle();
                            bundle2.putSerializable("model", videoModelC);
                            pVar.setArguments(bundle2);
                            ((MainActivity) context5).showScreen(pVar, "videoDetail");
                        } else {
                            VideoModel videoModelC2 = VideoModelMapper.fromSearch(searchItemModel3);
                            PlaybackFragment uVar = new PlaybackFragment();
                            Bundle bundle3 = new Bundle();
                            bundle3.putSerializable("model", videoModelC2);
                            uVar.setArguments(bundle3);
                            ((MainActivity) context5).showScreen(uVar, "videoPlayer");
                        }
                    }
                }
                break;
            case 11:
                ItemClickCallback aVar = ((SeriesFilterAdapter) obj).f11053b;
                if (aVar != null) {
                    aVar.onClick(view, i4);
                }
                break;
            case 12:
                LaneItemModel laneItemModel = (LaneItemModel) ((CatalogAdapter) obj).itemAt(i4);
                if (laneItemModel != null) {
                    Object obj15 = ScreenNavigator.f738a;
                    ScreenNavigator.openSeries(
                            view != null ? view.getContext() : null,
                            Long.valueOf(laneItemModel.getSeason_id()),
                            null,
                            laneItemModel.getTitle());
                }
                break;
            case 13:
                Object obj16 = ScreenNavigator.f738a;
                Context context6 = view != null ? view.getContext() : null;
                Object obj17 = ((LaneItemAdapter) obj).f10345b.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj17, "get(...)");
                ScreenNavigator.openVideo(context6, (VideoModel) obj17, false);
                break;
            case 14:
                ((ScreenContentAdapter) obj).getClass();
                break;
            case 15:
                Object obj18 = ScreenNavigator.f738a;
                Context context7 = view != null ? view.getContext() : null;
                LaneItemAdapter fVar = (LaneItemAdapter) obj;
                Long lValueOf =
                        Long.valueOf(((SeriesTimeLineModel) fVar.f10345b.get(i4)).getSeason_id());
                ArrayList arrayList7 = fVar.f10345b;
                ScreenNavigator.openSeries(
                        context7,
                        lValueOf,
                        Long.valueOf(((SeriesTimeLineModel) arrayList7.get(i4)).getEpisode_id()),
                        ((SeriesTimeLineModel) arrayList7.get(i4)).getTitle());
                break;
        }
    }

    public void onFocusChange(View view, int i4, boolean z7) {
        switch (this.f10358a) {
            case 18:
                ((CatalogAdapter) this.f10359b).lastFocusedItem = view;
                break;
            default:
                UserSpaceAdapter aVar = (UserSpaceAdapter) this.f10359b;
                aVar.f11503f = view;
                ItemFocusCallback c0170l = aVar.f11504p;
                if (c0170l != null) {
                    c0170l.onFocusChange(view, i4, z7);
                }
                break;
        }
    }
}
