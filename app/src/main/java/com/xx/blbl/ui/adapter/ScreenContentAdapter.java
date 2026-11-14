package com.xx.blbl.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.flexbox.FlexboxLayout;
import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.SettingModel;
import com.xx.blbl.model.episode.EpisodeModel;
import com.xx.blbl.model.episode.EpisodeStatModel;
import com.xx.blbl.model.episode.NewEpisodeInfoModel;
import com.xx.blbl.model.search.SearchAllCount;
import com.xx.blbl.model.search.SearchAllCountWrapper;
import com.xx.blbl.model.search.SearchAllResponseData;
import com.xx.blbl.model.search.SearchAllResult;
import com.xx.blbl.model.search.SearchCategoryItem;
import com.xx.blbl.model.search.SearchItemModel;
import com.xx.blbl.model.search.SearchType;
import com.xx.blbl.model.search.SearchVideoOrder;
import com.xx.blbl.model.series.EpisodeProgressModel;
import com.xx.blbl.model.series.EpisodesDetailModel;
import com.xx.blbl.model.series.EpisodesRatingModel;
import com.xx.blbl.model.series.SectionModel;
import com.xx.blbl.model.series.SeriesModel;
import com.xx.blbl.model.series.SeriesUserState;
import com.xx.blbl.model.series.UgcEpisodeModel;
import com.xx.blbl.model.series.UgcSectionModel;
import com.xx.blbl.model.series.UgcSeriesModel;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.TagModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoPvModel;
import com.xx.blbl.model.video.VideoStatModel;
import com.xx.blbl.model.video.detail.VideoDetailModel;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.platform.DisplayFormat;
import com.xx.blbl.platform.ImageLoader;
import com.xx.blbl.platform.UiSupport;
import com.xx.blbl.recovery.ItemRouterT;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;
import com.xx.blbl.ui.holder.LabelActionViewHolder;
import com.xx.blbl.ui.holder.RelatedVideoLaneViewHolder;
import com.xx.blbl.ui.holder.SearchResultsViewHolder;
import com.xx.blbl.ui.holder.SeriesDetailHeaderViewHolder;
import com.xx.blbl.ui.holder.SeriesEpisodeCardViewHolder;
import com.xx.blbl.ui.holder.SeriesEpisodesLaneViewHolder;
import com.xx.blbl.ui.holder.SeriesExtrasLaneViewHolder;
import com.xx.blbl.ui.holder.SeriesSectionViewHolder;
import com.xx.blbl.ui.holder.UgcEpisodesLaneViewHolder;
import com.xx.blbl.ui.holder.VideoCollectionCallback;
import com.xx.blbl.ui.holder.VideoHeaderViewHolder;
import com.xx.blbl.ui.holder.VideoInteractionCallback;
import com.xx.blbl.ui.holder.VideoPagesLaneViewHolder;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class ScreenContentAdapter extends Adapter {
    public final /* synthetic */ int f10355a;
    public Serializable f10356b;
    public Object f10357c;
    public Object d;
    public Object e;

    public ScreenContentAdapter(int i4) {
        this.f10355a = i4;
        switch (i4) {
            case 1:
                this.f10356b = new ArrayList();
                this.d = "";
                this.e = SearchVideoOrder.TotalRank;
                break;
            case 2:
                this.d = new CatalogClickRouter(this, 14);
                this.e = new ContentClickRouter(this, 16);
                break;
            case 3:
                break;
            case 4:
                this.f10356b = new ArrayList();
                this.e = new CatalogClickRouter(this, 21);
                break;
            default:
                this.f10356b = new ArrayList();
                this.d = new ContentClickRouter(this, 0);
                this.e = new ItemRouterT(this, 28);
                break;
        }
    }

    public void a(Context context, String keyword, SearchAllResponseData searchAllResponseData) {
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        this.d = keyword;
        this.f10357c = searchAllResponseData;
        SearchAllCountWrapper pageinfo = searchAllResponseData.getPageinfo();
        if (pageinfo != null) {
            ArrayList arrayList = (ArrayList) this.f10356b;
            int size = arrayList.size();
            arrayList.clear();
            notifyItemRangeRemoved(0, size);
            SearchAllCount video = pageinfo.getVideo();
            if (video != null && video.getNumResults() > 0) {
                SearchCategoryItem searchCategoryItem = new SearchCategoryItem();
                String string = context.getString(R.string.video);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                searchCategoryItem.setShowText(string);
                searchCategoryItem.setType(SearchType.Video);
                arrayList.add(searchCategoryItem);
            }
            SearchAllCount media_bangumi = pageinfo.getMedia_bangumi();
            if (media_bangumi != null && media_bangumi.getNumResults() > 0) {
                SearchCategoryItem searchCategoryItem2 = new SearchCategoryItem();
                String string2 = context.getString(R.string.animation);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                searchCategoryItem2.setShowText(string2);
                searchCategoryItem2.setType(SearchType.Animation);
                arrayList.add(searchCategoryItem2);
            }
            SearchAllCount media_ft = pageinfo.getMedia_ft();
            if (media_ft != null && media_ft.getNumResults() > 0) {
                SearchCategoryItem searchCategoryItem3 = new SearchCategoryItem();
                String string3 = context.getString(R.string.film_and_television);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                searchCategoryItem3.setShowText(string3);
                searchCategoryItem3.setType(SearchType.FilmAndTv);
                arrayList.add(searchCategoryItem3);
            }
            SearchAllCount live_room = pageinfo.getLive_room();
            if (live_room != null && live_room.getNumResults() > 0) {
                SearchCategoryItem searchCategoryItem4 = new SearchCategoryItem();
                String string4 = context.getString(R.string.live);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                searchCategoryItem4.setShowText(string4);
                searchCategoryItem4.setType(SearchType.LiveRoom);
                arrayList.add(searchCategoryItem4);
            }
            SearchAllCount bili_user = pageinfo.getBili_user();
            if (bili_user != null && bili_user.getNumResults() > 0) {
                SearchCategoryItem searchCategoryItem5 = new SearchCategoryItem();
                String string5 = context.getString(R.string.user);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                searchCategoryItem5.setShowText(string5);
                searchCategoryItem5.setType(SearchType.User);
                arrayList.add(searchCategoryItem5);
            }
            notifyItemRangeChanged(0, arrayList.size());
        }
    }

    public void b(List data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = (ArrayList) this.f10356b;
        int size = arrayList.size();
        arrayList.clear();
        notifyItemRangeRemoved(0, size);
        arrayList.addAll(data);
        notifyItemRangeChanged(0, data.size());
    }

    public void c(SearchVideoOrder o7) {
        Intrinsics.checkNotNullParameter(o7, "o");
        this.e = o7;
        notifyItemChanged(0);
    }

    @Override
    public final int getItemCount() {
        switch (this.f10355a) {
            case 0:
                return ((ArrayList) this.f10356b).size();
            case 1:
                return ((ArrayList) this.f10356b).size();
            case 2:
                EpisodesDetailModel episodesDetailModel = (EpisodesDetailModel) this.f10356b;
                if (episodesDetailModel == null) {
                    return 0;
                }
                List<EpisodeModel> episodes = episodesDetailModel.getEpisodes();
                int i4 = (episodes == null || !(episodes.isEmpty() ^ true)) ? 1 : 2;
                List<SectionModel> section = episodesDetailModel.getSection();
                if (section != null) {
                    Iterator<SectionModel> it = section.iterator();
                    while (it.hasNext()) {
                        List<VideoModel> episodes2 = it.next().getEpisodes();
                        if (episodes2 != null && (!episodes2.isEmpty())) {
                            i4++;
                        }
                    }
                }
                List<SeriesModel> seasons = episodesDetailModel.getSeasons();
                return (seasons == null || seasons.isEmpty()) ? i4 : i4 + 1;
            case 3:
                VideoDetailModel videoDetailModel = (VideoDetailModel) this.f10356b;
                if (videoDetailModel == null) {
                    return 0;
                }
                int i7 = videoDetailModel.getView() != null ? 1 : 0;
                if (videoDetailModel.getView() != null) {
                    VideoModel view = videoDetailModel.getView();
                    Intrinsics.checkNotNull(view);
                    if (view.getPages() != null) {
                        VideoModel view2 = videoDetailModel.getView();
                        Intrinsics.checkNotNull(view2);
                        ArrayList<VideoPvModel> pages = view2.getPages();
                        Intrinsics.checkNotNull(pages);
                        if (!pages.isEmpty()) {
                            i7++;
                        }
                    }
                }
                if (videoDetailModel.getView() != null) {
                    VideoModel view3 = videoDetailModel.getView();
                    Intrinsics.checkNotNull(view3);
                    if (view3.getUgc_season() != null) {
                        VideoModel view4 = videoDetailModel.getView();
                        Intrinsics.checkNotNull(view4);
                        UgcSeriesModel ugc_season = view4.getUgc_season();
                        Intrinsics.checkNotNull(ugc_season);
                        if (ugc_season.getSections() != null) {
                            VideoModel view5 = videoDetailModel.getView();
                            Intrinsics.checkNotNull(view5);
                            UgcSeriesModel ugc_season2 = view5.getUgc_season();
                            Intrinsics.checkNotNull(ugc_season2);
                            List<UgcSectionModel> sections = ugc_season2.getSections();
                            Intrinsics.checkNotNull(sections);
                            if (!sections.isEmpty()) {
                                i7++;
                            }
                        }
                    }
                }
                if (videoDetailModel.getRelated() == null) {
                    return i7;
                }
                List<VideoModel> related = videoDetailModel.getRelated();
                Intrinsics.checkNotNull(related);
                return !related.isEmpty() ? i7 + 1 : i7;
            default:
                return ((ArrayList) this.f10356b).size();
        }
    }

    @Override
    public int getItemViewType(int i4) {
        switch (this.f10355a) {
            case 2:
                int i7 = i4 == 0 ? 0 : -1;
                EpisodesDetailModel episodesDetailModel = (EpisodesDetailModel) this.f10356b;
                if (episodesDetailModel == null) {
                    return i7;
                }
                List<EpisodeModel> episodes = episodesDetailModel.getEpisodes();
                if (episodes != null && (!episodes.isEmpty()) && i4 == 1) {
                    i7 = 1;
                }
                List<SectionModel> section = episodesDetailModel.getSection();
                int i8 = 2;
                if (section != null) {
                    Iterator<SectionModel> it = section.iterator();
                    int i9 = 2;
                    while (it.hasNext()) {
                        List<VideoModel> episodes2 = it.next().getEpisodes();
                        if (episodes2 != null && (!episodes2.isEmpty())) {
                            if (i4 >= i9) {
                                i7 = 2;
                            }
                            i9++;
                        }
                    }
                    i8 = i9;
                }
                List<SeriesModel> seasons = episodesDetailModel.getSeasons();
                if (seasons == null || i4 < i8 || seasons.isEmpty()) {
                    return i7;
                }
                return 3;
            case 3:
                VideoDetailModel videoDetailModel = (VideoDetailModel) this.f10356b;
                int i10 = -1;
                if (videoDetailModel == null) {
                    return -1;
                }
                int i11 = 1;
                int i12 = 0;
                if (i4 == 0 && videoDetailModel.getView() != null) {
                    i10 = 0;
                    i12 = 1;
                }
                if (i4 <= i12 || videoDetailModel.getView() == null) {
                    i11 = i10;
                } else {
                    VideoModel view = videoDetailModel.getView();
                    Intrinsics.checkNotNull(view);
                    if (view.getPages() != null) {
                        VideoModel view2 = videoDetailModel.getView();
                        Intrinsics.checkNotNull(view2);
                        ArrayList<VideoPvModel> pages = view2.getPages();
                        Intrinsics.checkNotNull(pages);
                        if (pages.isEmpty()) {
                            i11 = i10;
                        } else {
                            i12++;
                        }
                    } else {
                        i11 = i10;
                    }
                }
                if (i4 > i12 && videoDetailModel.getView() != null) {
                    VideoModel view3 = videoDetailModel.getView();
                    Intrinsics.checkNotNull(view3);
                    if (view3.getUgc_season() != null) {
                        VideoModel view4 = videoDetailModel.getView();
                        Intrinsics.checkNotNull(view4);
                        UgcSeriesModel ugc_season = view4.getUgc_season();
                        Intrinsics.checkNotNull(ugc_season);
                        if (ugc_season.getSections() != null) {
                            VideoModel view5 = videoDetailModel.getView();
                            Intrinsics.checkNotNull(view5);
                            UgcSeriesModel ugc_season2 = view5.getUgc_season();
                            Intrinsics.checkNotNull(ugc_season2);
                            List<UgcSectionModel> sections = ugc_season2.getSections();
                            Intrinsics.checkNotNull(sections);
                            if (!sections.isEmpty()) {
                                i12++;
                                i11 = 2;
                            }
                        }
                    }
                }
                if (i4 > i12 && videoDetailModel.getRelated() != null) {
                    List<VideoModel> related = videoDetailModel.getRelated();
                    Intrinsics.checkNotNull(related);
                    if (!related.isEmpty()) {
                        return 3;
                    }
                }
                return i11;
            default:
                return super.getItemViewType(i4);
        }
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int i4) {
        List<SearchAllResult> result;
        EpisodesDetailModel episodesDetailModel;
        List<SeriesModel> seasons;
        List<SectionModel> section;
        int i7;
        List<EpisodeModel> episodes;
        EpisodesDetailModel episodesDetailModel2;
        List<EpisodeModel> episodes2;
        EpisodeProgressModel progress;
        kotlin.Unit jVar;
        String score;
        VideoDetailModel videoDetailModel;
        List<VideoModel> related;
        VideoDetailModel videoDetailModel2;
        VideoModel view;
        UgcSeriesModel ugc_season;
        List<UgcEpisodeModel> episodes3;
        VideoDetailModel videoDetailModel3;
        VideoModel view2;
        ArrayList<VideoPvModel> pages;
        VideoDetailModel videoDetailModel4;
        int i8 = 1;
        int i9 = 0;
        switch (this.f10355a) {
            case 0:
                LabelActionViewHolder holder2 = (LabelActionViewHolder) holder;
                Intrinsics.checkNotNullParameter(holder2, "holder");
                Object obj = ((ArrayList) this.f10356b).get(i4);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                SettingModel settingModel = (SettingModel) obj;
                holder2.f14411a.setText(settingModel.getTitle());
                holder2.f14412b.setText(settingModel.getInfo());
                break;
            case 1:
                SearchResultsViewHolder holder3 = (SearchResultsViewHolder) holder;
                Intrinsics.checkNotNullParameter(holder3, "holder");
                SearchAllResponseData searchAllResponseData = (SearchAllResponseData) this.f10357c;
                if (searchAllResponseData != null
                        && (result = searchAllResponseData.getResult()) != null
                        && !result.isEmpty()) {
                    ArrayList arrayList = (ArrayList) this.f10356b;
                    if (((SearchCategoryItem) arrayList.get(i4)).getType() == SearchType.Video) {
                        for (SearchAllResult searchAllResult : result) {
                            String result_type = searchAllResult.getResult_type();
                            SearchType searchType = SearchType.Video;
                            if (Intrinsics.areEqual(result_type, searchType.getValue())
                                    && searchAllResult.getData() != null) {
                                List<SearchItemModel> data = searchAllResult.getData();
                                Intrinsics.checkNotNull(data);
                                holder3.bindVideos(
                                        searchType,
                                        data,
                                        (String) this.d,
                                        ((SearchVideoOrder) this.e).getValue());
                                break;
                            }
                        }
                    } else if (((SearchCategoryItem) arrayList.get(i4)).getType()
                            == SearchType.Animation) {
                        for (SearchAllResult searchAllResult2 : result) {
                            String result_type2 = searchAllResult2.getResult_type();
                            SearchType searchType2 = SearchType.Animation;
                            if (Intrinsics.areEqual(result_type2, searchType2.getValue())
                                    && searchAllResult2.getData() != null) {
                                String str = (String) this.d;
                                List<SearchItemModel> data2 = searchAllResult2.getData();
                                Intrinsics.checkNotNull(data2);
                                holder3.bindSeries(searchType2, str, data2);
                                break;
                            }
                        }
                    } else if (((SearchCategoryItem) arrayList.get(i4)).getType()
                            == SearchType.FilmAndTv) {
                        for (SearchAllResult searchAllResult3 : result) {
                            String result_type3 = searchAllResult3.getResult_type();
                            SearchType searchType3 = SearchType.FilmAndTv;
                            if (Intrinsics.areEqual(result_type3, searchType3.getValue())
                                    && searchAllResult3.getData() != null) {
                                String str2 = (String) this.d;
                                List<SearchItemModel> data3 = searchAllResult3.getData();
                                Intrinsics.checkNotNull(data3);
                                holder3.bindSeries(searchType3, str2, data3);
                                break;
                            }
                        }
                    } else {
                        SearchType type = ((SearchCategoryItem) arrayList.get(i4)).getType();
                        SearchType type2 = SearchType.LiveRoom;
                        if (type == type2) {
                            String keyword = (String) this.d;
                            Intrinsics.checkNotNullParameter(type2, "type");
                            Intrinsics.checkNotNullParameter(keyword, "keyword");
                            holder3.bindVideos(type2, null, keyword, "totalrank");
                        } else {
                            SearchType type3 = ((SearchCategoryItem) arrayList.get(i4)).getType();
                            SearchType type4 = SearchType.User;
                            if (type3 == type4) {
                                String keyword2 = (String) this.d;
                                Intrinsics.checkNotNullParameter(type4, "type");
                                Intrinsics.checkNotNullParameter(keyword2, "keyword");
                                holder3.bindUsers(keyword2);
                            }
                        }
                    }
                    break;
                }
                break;
            case 2:
                Intrinsics.checkNotNullParameter(holder, "holder");
                if ((holder instanceof SeriesDetailHeaderViewHolder)
                        && (episodesDetailModel2 = (EpisodesDetailModel) this.f10356b) != null) {
                    SeriesDetailHeaderViewHolder hVar = (SeriesDetailHeaderViewHolder) holder;
                    hVar.f15047H = episodesDetailModel2;
                    Object obj2 = ImageLoader.f736a;
                    ImageLoader.loadPoster(episodesDetailModel2.getCover(), hVar.d);
                    AppCompatImageView appCompatImageView = hVar.f15056w;
                    if (appCompatImageView.getContext() instanceof MainActivity) {
                        Context context = appCompatImageView.getContext();
                        Intrinsics.checkNotNull(
                                context,
                                "null cannot be cast to non-null type com.xx.blbl.ui.MainActivity");
                        if (context.getSharedPreferences("BLBL", 0).getInt("theme", 1) == 3) {
                            appCompatImageView.setVisibility(8);
                        } else {
                            String url = episodesDetailModel2.getSquare_cover();
                            Intrinsics.checkNotNullParameter(url, "url");
                            Glide.with(AppController.instance())
                                    .asDrawable()
                                    .load(url)
                                    .into(appCompatImageView);
                        }
                    } else {
                        String url2 = episodesDetailModel2.getSquare_cover();
                        Intrinsics.checkNotNullParameter(url2, "url");
                        Glide.with(AppController.instance())
                                .asDrawable()
                                .load(url2)
                                .into(appCompatImageView);
                    }
                    hVar.e.setText(episodesDetailModel2.getSeason_title());
                    NewEpisodeInfoModel new_ep = episodesDetailModel2.getNew_ep();
                    String str3 = new_ep != null ? "" + new_ep.getDesc() : "";
                    EpisodesRatingModel rating = episodesDetailModel2.getRating();
                    if (rating != null && (score = rating.getScore()) != null) {
                        str3 = str3 + " | " + score + (char) 20998;
                    }
                    EpisodeStatModel stat = episodesDetailModel2.getStat();
                    if (stat != null) {
                        str3 =
                                str3
                                        + " | "
                                        + DisplayFormat.f(stat.getViews())
                                        + "次观看 | "
                                        + DisplayFormat.f(stat.getDanmakus())
                                        + "条弹幕";
                    }
                    hVar.f15053f.setText(str3);
                    hVar.f15054p.setText(episodesDetailModel2.getEvaluate());
                    SeriesUserState user_status = episodesDetailModel2.getUser_status();
                    AppCompatButton appCompatButton = hVar.f15058y;
                    AppCompatButton appCompatButton2 = hVar.f15057x;
                    AppCompatTextView appCompatTextView = hVar.f15055v;
                    if (user_status == null || (progress = user_status.getProgress()) == null) {
                        hVar = hVar;
                        appCompatTextView.setVisibility(8);
                        appCompatButton2.setText(
                                appCompatButton.getContext().getString(R.string.start_watch));
                        episodes2 = episodesDetailModel2.getEpisodes();
                        if (episodes2 != null && !episodes2.isEmpty()) {
                            hVar.f15048I = (EpisodeModel) CollectionsKt.first(episodes2);
                        }
                    } else {
                        appCompatTextView.setVisibility(0);
                        hVar.f15049J = progress.getLast_time();
                        Context context2 = appCompatTextView.getContext();
                        String last_ep_index = progress.getLast_ep_index();
                        long last_time = progress.getLast_time();
                        long j7 = 60;
                        long j8 = last_time % j7;
                        long j9 = last_time / j7;
                        String strO = j8 + "";
                        if (j8 < 10) {
                            strO = ("0" + j8);
                        }
                        String strO2 = j9 + "";
                        if (j9 < 10) {
                            strO2 = ("0" + j9);
                        }
                        appCompatTextView.setText(
                                context2.getString(
                                        R.string.last_watch_, last_ep_index, strO2 + ':' + strO));
                        appCompatButton2.setText(
                                appCompatButton.getContext().getString(R.string.continue_watch));
                        List<EpisodeModel> episodes4 = episodesDetailModel2.getEpisodes();
                        if (episodes4 != null) {
                            ArrayList arrayList2 = new ArrayList();
                            for (Object obj3 : episodes4) {
                                if (progress.getLast_ep_id() == ((EpisodeModel) obj3).getId()) {
                                    arrayList2.add(obj3);
                                }
                            }
                            if (arrayList2.isEmpty()) {
                                hVar = hVar;
                            } else {
                                EpisodeModel episodeModel =
                                        (EpisodeModel) CollectionsKt.first(arrayList2);
                                hVar = hVar;
                                hVar.f15048I = episodeModel;
                            }
                            jVar = kotlin.Unit.INSTANCE;
                        } else {
                            jVar = null;
                        }
                        if (jVar == null) {
                            hVar = hVar;
                            appCompatTextView.setVisibility(8);
                            appCompatButton2.setText(
                                    appCompatButton.getContext().getString(R.string.start_watch));
                            episodes2 = episodesDetailModel2.getEpisodes();
                            if (episodes2 != null) {
                                hVar.f15048I = (EpisodeModel) CollectionsKt.first(episodes2);
                            }
                        }
                    }
                    hVar = hVar;
                    long season_id = episodesDetailModel2.getSeason_id();
                    hVar.f15046G = true;
                    final SeriesDetailHeaderViewHolder seriesOwner = hVar;
                    ((NetworkManager) ((kotlin.Lazy) hVar.f15051b).getValue())
                            .checkSeriesUserState(
                                    Long.valueOf(season_id),
                                    null,
                                    new com.xx.blbl.network.NetResultCallback<
                                            com.xx.blbl.network.response.Base2Response<
                                                    com.xx.blbl.model.series
                                                            .CheckUserSeriesResult>>() {
                                        public void onFailure(Throwable error) {
                                            seriesOwner.f15046G = false;
                                        }

                                        public void onResponse(
                                                com.xx.blbl.network.response.Base2Response<
                                                                com.xx.blbl.model.series
                                                                        .CheckUserSeriesResult>
                                                        response) {
                                            seriesOwner.f15046G = false;
                                            com.xx.blbl.model.series.CheckUserSeriesResult data =
                                                    response == null ? null : response.getResult();
                                            if (data != null) {
                                                seriesOwner.f15059z = data.getFollow() == 1;
                                                SeriesDetailHeaderViewHolder.a(seriesOwner);
                                            }
                                        }
                                    });
                    break;
                } else if (holder instanceof SeriesEpisodesLaneViewHolder) {
                    EpisodesDetailModel episodesDetailModel3 = (EpisodesDetailModel) this.f10356b;
                    if (episodesDetailModel3 != null
                            && (episodes = episodesDetailModel3.getEpisodes()) != null
                            && (!episodes.isEmpty())) {
                        SeriesEpisodesLaneViewHolder dVar = (SeriesEpisodesLaneViewHolder) holder;
                        String string = holder.itemView.getContext().getString(R.string.episodes);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        EpisodesDetailModel episodesDetailModel4 =
                                (EpisodesDetailModel) this.f10356b;
                        Intrinsics.checkNotNull(episodesDetailModel4);
                        List<EpisodeModel> episodes5 = episodesDetailModel4.getEpisodes();
                        Intrinsics.checkNotNull(episodes5);
                        dVar.f15035a.setText(string);
                        List list = CollectionsKt.reversed(episodes5);
                        SeriesEpisodesAdapter bVar = dVar.d;
                        bVar.getClass();
                        Intrinsics.checkNotNullParameter(list, "list");
                        ArrayList arrayList3 = bVar.f11055a;
                        int size = arrayList3.size();
                        arrayList3.clear();
                        bVar.notifyItemRangeRemoved(0, size);
                        arrayList3.addAll(list);
                        bVar.notifyItemChanged(0, Integer.valueOf(arrayList3.size()));
                        break;
                    }
                } else if (holder instanceof SeriesExtrasLaneViewHolder) {
                    EpisodesDetailModel episodesDetailModel5 = (EpisodesDetailModel) this.f10356b;
                    if (episodesDetailModel5 != null
                            && (section = episodesDetailModel5.getSection()) != null
                            && (i7 = i4 - 2) >= 0
                            && i7 < section.size()
                            && section.get(i7).getEpisodes() != null) {
                        SeriesExtrasLaneViewHolder cVar = (SeriesExtrasLaneViewHolder) holder;
                        String title = section.get(i7).getTitle();
                        List<VideoModel> episodes6 = section.get(i7).getEpisodes();
                        Intrinsics.checkNotNull(episodes6);
                        Intrinsics.checkNotNullParameter(title, "title");
                        cVar.f15033a.setText(title);
                        LaneItemAdapter fVar = cVar.f15034b;
                        fVar.getClass();
                        ArrayList arrayList4 = fVar.f10345b;
                        int size2 = arrayList4.size();
                        arrayList4.clear();
                        fVar.notifyItemRangeRemoved(0, size2);
                        arrayList4.addAll(episodes6);
                        fVar.notifyItemChanged(0, Integer.valueOf(arrayList4.size()));
                        break;
                    }
                } else if ((holder instanceof SeriesSectionViewHolder)
                        && (episodesDetailModel = (EpisodesDetailModel) this.f10356b) != null
                        && (seasons = episodesDetailModel.getSeasons()) != null) {
                    SeriesSectionViewHolder iVar = (SeriesSectionViewHolder) holder;
                    Context context3 = holder.itemView.getContext();
                    EpisodesDetailModel episodesDetailModel6 = (EpisodesDetailModel) this.f10356b;
                    Intrinsics.checkNotNull(episodesDetailModel6);
                    String string2 =
                            context3.getString(
                                    R.string.other_series_, episodesDetailModel6.getTitle());
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    EpisodesDetailModel episodesDetailModel7 = (EpisodesDetailModel) this.f10356b;
                    Intrinsics.checkNotNull(episodesDetailModel7);
                    long season_id2 = episodesDetailModel7.getSeason_id();
                    iVar.f15061a.setText(string2);
                    SeriesHeaderAdapter cVar2 = iVar.f15062b;
                    cVar2.getClass();
                    ArrayList arrayList5 = cVar2.f11058a;
                    arrayList5.clear();
                    arrayList5.addAll(seasons);
                    cVar2.notifyDataSetChanged();
                    cVar2.f11059b = season_id2;
                    break;
                }
                break;
            case 3:
                Intrinsics.checkNotNullParameter(holder, "holder");
                if ((holder instanceof VideoHeaderViewHolder)
                        && (videoDetailModel4 = (VideoDetailModel) this.f10356b) != null) {
                    VideoHeaderViewHolder jVar2 = (VideoHeaderViewHolder) holder;
                    VideoModel videoModel = (VideoModel) this.f10357c;
                    List list2 = (List) this.d;
                    jVar2.f589J = videoModel;
                    jVar2.f590K = list2;
                    VideoModel view3 = videoDetailModel4.getView();
                    if (view3 != null) {
                        jVar2.e.setText(view3.getTitle());
                        Object obj4 = ImageLoader.f736a;
                        String url3 = view3.getPic();
                        Intrinsics.checkNotNullParameter(url3, "url");
                        AppCompatImageView imageView = jVar2.d;
                        Intrinsics.checkNotNullParameter(imageView, "imageView");
                        Glide.with(AppController.instance())
                                .asDrawable()
                                .load(ImageLoader.thumbnailRequest(url3, true))
                                .placeholder(R.mipmap.default_video)
                                .transform(
                                        new CenterCrop(),
                                        new RoundedCorners(
                                                (int)
                                                        imageView
                                                                .getContext()
                                                                .getResources()
                                                                .getDimension(R.dimen.px15)))
                                .into(imageView);
                        boolean zIsEmpty = TextUtils.isEmpty(view3.getDesc());
                        AppCompatTextView appCompatTextView2 = jVar2.f597p;
                        if (zIsEmpty) {
                            appCompatTextView2.setVisibility(8);
                        } else {
                            appCompatTextView2.setVisibility(0);
                            appCompatTextView2.setText(view3.getDesc());
                        }
                        OwnerModel owner = view3.getOwner();
                        if (owner != null) {
                            jVar2.f599w.setText(owner.getName());
                            ImageLoader.loadAvatar(owner.getFace(), jVar2.f600x, false);
                        }
                        VideoStatModel stat2 = view3.getStat();
                        if (stat2 != null) {
                            AppCompatTextView appCompatTextView3 = jVar2.f596f;
                            Context context4 = appCompatTextView3.getContext();
                            Date date = new Date(view3.getPubdate() * ((long) 1000));
                            String str4 =
                                    (date.getYear() == new Date().getYear()
                                                    ? new SimpleDateFormat(
                                                            "MM月dd日 HH:mm", Locale.CHINA)
                                                    : new SimpleDateFormat(
                                                            "yyyy年MM月dd日 HH:mm", Locale.CHINA))
                                            .format(date);
                            Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
                            appCompatTextView3.setText(
                                    String.format(
                                            "%s · %s · %s · %s · %s · %s",
                                            Arrays.copyOf(
                                                    new Object[] {
                                                        context4.getString(
                                                                R.string.publish_at, str4),
                                                        appCompatTextView3
                                                                .getContext()
                                                                .getString(
                                                                        R.string.view_,
                                                                        DisplayFormat.f(
                                                                                stat2.getView())),
                                                        appCompatTextView3
                                                                .getContext()
                                                                .getString(
                                                                        R.string.dm_,
                                                                        DisplayFormat.f(
                                                                                stat2
                                                                                        .getDanmaku())),
                                                        appCompatTextView3
                                                                .getContext()
                                                                .getString(
                                                                        R.string.like_,
                                                                        DisplayFormat.f(
                                                                                stat2.getLike())),
                                                        appCompatTextView3
                                                                .getContext()
                                                                .getString(
                                                                        R.string.coin_,
                                                                        DisplayFormat.f(
                                                                                stat2.getCoin())),
                                                        appCompatTextView3
                                                                .getContext()
                                                                .getString(
                                                                        R.string.favorite_,
                                                                        DisplayFormat.f(
                                                                                stat2
                                                                                        .getFavorite()))
                                                    },
                                                    6)));
                        }
                        if (((AppState) ((kotlin.Lazy) jVar2.f595c).getValue()).signedIn) {
                            VideoModel videoModel2 = jVar2.f589J;
                            if (videoModel2 != null) {
                                jVar2.network()
                                        .hasLike(
                                                Long.valueOf(videoModel2.getAid()),
                                                videoModel2.getBvid(),
                                                new VideoInteractionCallback(jVar2, i8));
                            }
                            VideoModel videoModel3 = jVar2.f589J;
                            if (videoModel3 != null) {
                                jVar2.network()
                                        .hasCollection(
                                                Long.valueOf(videoModel3.getAid()),
                                                videoModel3.getBvid(),
                                                new VideoCollectionCallback(jVar2, i9));
                            }
                            VideoModel videoModel4 = jVar2.f589J;
                            if (videoModel4 != null) {
                                jVar2.network()
                                        .hasGiveCoin(
                                                Long.valueOf(videoModel4.getAid()),
                                                videoModel4.getBvid(),
                                                new VideoInteractionCallback(jVar2, i9));
                            }
                        }
                    }
                    List<TagModel> tag = videoDetailModel4.getTag();
                    if (tag != null) {
                        FlexboxLayout flexboxLayout = jVar2.f598v;
                        flexboxLayout.removeAllViews();
                        for (TagModel tagModel : tag) {
                            if (!Intrinsics.areEqual(tagModel.getTag_type(), "topic")
                                    && !Intrinsics.areEqual(tagModel.getTag_type(), "bgm")) {
                                TextView textView = new TextView(flexboxLayout.getContext());
                                textView.setText(tagModel.getTag_name());
                                textView.setTag(Long.valueOf(tagModel.getTag_id()));
                                textView.setFocusable(true);
                                textView.setClickable(true);
                                Resources resources = flexboxLayout.getContext().getResources();
                                if (resources != null) {
                                    Context context5 = flexboxLayout.getContext();
                                    Intrinsics.checkNotNullExpressionValue(
                                            context5, "getContext(...)");
                                    textView.setTextColor(UiSupport.textColor(context5));
                                    textView.setTextSize(0, resources.getDimension(R.dimen.px27));
                                    FlexboxLayout.LayoutParams layoutParams =
                                            new FlexboxLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                                    layoutParams.setMargins(0, 0, 20, 15);
                                    textView.setLayoutParams(layoutParams);
                                    textView.setPadding(20, 10, 20, 10);
                                }
                                textView.setBackgroundResource(R.drawable.button_common);
                                textView.setOnClickListener(new SharedClickListener(tagModel, i8));
                                flexboxLayout.addView(textView);
                            }
                        }
                    }
                }
                if ((holder instanceof VideoPagesLaneViewHolder)
                        && (videoDetailModel3 = (VideoDetailModel) this.f10356b) != null
                        && (view2 = videoDetailModel3.getView()) != null
                        && (pages = view2.getPages()) != null) {
                    VideoModel videoModel5 = (VideoModel) this.f10357c;
                    if (videoModel5 != null) {
                        ((VideoPagesLaneViewHolder) holder).f604a.f10357c = videoModel5;
                    }
                    List list3 = (List) this.d;
                    ScreenContentAdapter jVar3 = ((VideoPagesLaneViewHolder) holder).f604a;
                    jVar3.d = list3;
                    ArrayList arrayList6 = (ArrayList) jVar3.f10356b;
                    int size3 = arrayList6.size();
                    arrayList6.clear();
                    jVar3.notifyItemRangeRemoved(0, size3);
                    arrayList6.addAll(pages);
                    jVar3.notifyItemChanged(0, Integer.valueOf(arrayList6.size()));
                }
                if ((holder instanceof UgcEpisodesLaneViewHolder)
                        && (videoDetailModel2 = (VideoDetailModel) this.f10356b) != null
                        && (view = videoDetailModel2.getView()) != null
                        && (ugc_season = view.getUgc_season()) != null) {
                    UgcEpisodesLaneViewHolder mVar = (UgcEpisodesLaneViewHolder) holder;
                    mVar.f611f = (VideoModel) this.f10357c;
                    mVar.f609b.setText(ugc_season.getTitle());
                    List<UgcSectionModel> sections = ugc_season.getSections();
                    if (sections != null
                            && !sections.isEmpty()
                            && (episodes3 = sections.get(0).getEpisodes()) != null) {
                        List list4 = CollectionsKt.reversed(episodes3);
                        OwnerVideosAdapter aVar2 = mVar.f610c;
                        aVar2.getClass();
                        Intrinsics.checkNotNullParameter(list4, "list");
                        ArrayList arrayList7 = aVar2.f11904a;
                        arrayList7.clear();
                        arrayList7.addAll(list4);
                        aVar2.notifyDataSetChanged();
                    }
                }
                if ((holder instanceof RelatedVideoLaneViewHolder)
                        && (videoDetailModel = (VideoDetailModel) this.f10356b) != null
                        && (related = videoDetailModel.getRelated()) != null) {
                    RelatedVideosAdapter hVar2 = ((RelatedVideoLaneViewHolder) holder).f606a;
                    hVar2.getClass();
                    ArrayList arrayList8 = hVar2.f10350a;
                    arrayList8.clear();
                    arrayList8.addAll(related);
                    hVar2.notifyDataSetChanged();
                    break;
                }
                break;
            default:
                SeriesEpisodeCardViewHolder holder4 = (SeriesEpisodeCardViewHolder) holder;
                Intrinsics.checkNotNullParameter(holder4, "holder");
                ArrayList arrayList9 = (ArrayList) this.f10356b;
                Object obj5 = arrayList9.get(i4);
                Intrinsics.checkNotNullExpressionValue(obj5, "get(...)");
                VideoPvModel videoPvModel = (VideoPvModel) obj5;
                VideoModel videoModel6 = (VideoModel) this.f10357c;
                String pic = videoModel6 != null ? videoModel6.getPic() : null;
                VideoModel videoModel7 = (VideoModel) this.f10357c;
                i8 =
                        (videoModel7 == null
                                        || videoModel7.getCid()
                                                != ((VideoPvModel) arrayList9.get(i4)).getCid())
                                ? 0
                                : 1;
                boolean zIsEmpty2 = TextUtils.isEmpty(videoPvModel.getFirst_frame());
                AppCompatImageView appCompatImageView2 = holder4.f15028a;
                if (!zIsEmpty2) {
                    Object obj6 = ImageLoader.f736a;
                    ImageLoader.loadThumbnail(videoPvModel.getFirst_frame(), appCompatImageView2);
                } else if (pic != null) {
                    ImageLoader.loadThumbnail(pic, appCompatImageView2);
                }
                holder4.f15031f.setVisibility(8);
                holder4.f15029b.setVisibility(8);
                holder4.d.setVisibility(8);
                boolean zIsEmpty3 = TextUtils.isEmpty(videoPvModel.getPart());
                AppCompatTextView appCompatTextView4 = holder4.f15030c;
                if (zIsEmpty3) {
                    appCompatTextView4.setVisibility(8);
                } else {
                    appCompatTextView4.setVisibility(0);
                    appCompatTextView4.setText(videoPvModel.getPart());
                }
                holder4.e.setVisibility(i8 != 0 ? 0 : 8);
                break;
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int i4) {
        switch (this.f10355a) {
            case 0:
                View viewE =
                        UiSupport.inflate(parent, "parent", R.layout.cell_setting, parent, false);
                int i7 = LabelActionViewHolder.f14410c;
                Intrinsics.checkNotNull(viewE);
                ContentClickRouter onItemClickListener = (ContentClickRouter) this.d;
                Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
                ItemRouterT onItemLongClickListener = (ItemRouterT) this.e;
                Intrinsics.checkNotNullParameter(
                        onItemLongClickListener, "onItemLongClickListener");
                return new LabelActionViewHolder(
                        viewE, onItemClickListener, onItemLongClickListener);
            case 1:
                View viewE2 =
                        UiSupport.inflate(
                                parent, "parent", R.layout.view_search_results, parent, false);
                int i8 = SearchResultsViewHolder.f14869J;
                Intrinsics.checkNotNull(viewE2);
                return new SearchResultsViewHolder(viewE2);
            case 2:
                Intrinsics.checkNotNullParameter(parent, "parent");
                if (i4 == 0) {
                    View viewInflate =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_head, parent, false);
                    int i9 = SeriesDetailHeaderViewHolder.f15045K;
                    Intrinsics.checkNotNull(viewInflate);
                    CatalogClickRouter onItemClickListener2 = (CatalogClickRouter) this.d;
                    Intrinsics.checkNotNullParameter(onItemClickListener2, "onItemClickListener");
                    ContentClickRouter onFocusListener = (ContentClickRouter) this.e;
                    Intrinsics.checkNotNullParameter(onFocusListener, "onFocusListener");
                    return new SeriesDetailHeaderViewHolder(
                            viewInflate, onItemClickListener2, onFocusListener);
                }
                if (i4 == 1) {
                    View viewInflate2 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_lane, parent, false);
                    int i10 = SeriesEpisodesLaneViewHolder.e;
                    Intrinsics.checkNotNull(viewInflate2);
                    return new SeriesEpisodesLaneViewHolder(viewInflate2);
                }
                if (i4 == 2) {
                    View viewInflate3 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_lane, parent, false);
                    int i11 = SeriesExtrasLaneViewHolder.f15032c;
                    Intrinsics.checkNotNull(viewInflate3);
                    return new SeriesExtrasLaneViewHolder(viewInflate3);
                }
                if (i4 != 3) {
                    View viewInflate4 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_lane, parent, false);
                    int i12 = SeriesSectionViewHolder.f15060c;
                    Intrinsics.checkNotNull(viewInflate4);
                    return new SeriesSectionViewHolder(viewInflate4);
                }
                View viewInflate5 =
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.cell_series_lane, parent, false);
                int i13 = SeriesSectionViewHolder.f15060c;
                Intrinsics.checkNotNull(viewInflate5);
                return new SeriesSectionViewHolder(viewInflate5);
            case 3:
                Intrinsics.checkNotNullParameter(parent, "parent");
                if (i4 == 0) {
                    View viewInflate6 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_video_detail_head, parent, false);
                    int i14 = VideoHeaderViewHolder.f585O;
                    Intrinsics.checkNotNull(viewInflate6);
                    return new VideoHeaderViewHolder(viewInflate6, (ItemFocusCallback) this.e);
                }
                if (i4 == 1) {
                    View viewInflate7 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_lane, parent, false);
                    int i15 = VideoPagesLaneViewHolder.f603b;
                    Intrinsics.checkNotNull(viewInflate7);
                    return new VideoPagesLaneViewHolder(viewInflate7);
                }
                if (i4 == 2) {
                    View viewInflate8 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_lane, parent, false);
                    int i16 = UgcEpisodesLaneViewHolder.f607p;
                    Intrinsics.checkNotNull(viewInflate8);
                    return new UgcEpisodesLaneViewHolder(viewInflate8);
                }
                if (i4 != 3) {
                    View viewInflate9 =
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cell_series_lane, parent, false);
                    int i17 = SeriesSectionViewHolder.f15060c;
                    Intrinsics.checkNotNull(viewInflate9);
                    return new SeriesSectionViewHolder(viewInflate9);
                }
                View viewInflate10 =
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.cell_series_lane, parent, false);
                int i18 = RelatedVideoLaneViewHolder.f605b;
                Intrinsics.checkNotNull(viewInflate10);
                return new RelatedVideoLaneViewHolder(viewInflate10);
            default:
                View viewE3 =
                        UiSupport.inflate(parent, "parent", R.layout.cell_episode, parent, false);
                int i19 = SeriesEpisodeCardViewHolder.f15027p;
                Intrinsics.checkNotNull(viewE3);
                CatalogClickRouter onItemClickListener3 = (CatalogClickRouter) this.e;
                Intrinsics.checkNotNullParameter(onItemClickListener3, "onItemClickListener");
                return new SeriesEpisodeCardViewHolder(viewE3, onItemClickListener3);
        }
    }
}
