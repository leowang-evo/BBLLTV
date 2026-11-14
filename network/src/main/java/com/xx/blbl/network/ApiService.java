package com.xx.blbl.network;

import com.xx.blbl.model.common.CheckFavoriteModel;
import com.xx.blbl.model.common.CheckGiveCoinModel;
import com.xx.blbl.model.common.CollectionResultModel;
import com.xx.blbl.model.common.GiveCoinResultModel;
import com.xx.blbl.model.common.TripleActionResultModel;
import com.xx.blbl.model.common.ZoneModel;
import com.xx.blbl.model.favorite.FolderDetailModel;
import com.xx.blbl.model.interaction.InteractionModel;
import com.xx.blbl.model.live.LiveAreaCategoryParent;
import com.xx.blbl.model.live.LivePlayUrlDataModel;
import com.xx.blbl.model.player.PlayInfoModel;
import com.xx.blbl.model.player.WatchingTotalNumberModel;
import com.xx.blbl.model.proto.Dm;
import com.xx.blbl.model.search.SearchAllResponseData;
import com.xx.blbl.model.series.CheckUserSeriesResult;
import com.xx.blbl.model.series.EpisodesDetailModel;
import com.xx.blbl.model.series.FollowSeriesResult;
import com.xx.blbl.model.user.CheckRelationModel;
import com.xx.blbl.model.user.ScanQrModel;
import com.xx.blbl.model.user.SignInResultModel;
import com.xx.blbl.model.user.SignOutModel;
import com.xx.blbl.model.user.UserDetailInfoModel;
import com.xx.blbl.model.user.UserInfoModel;
import com.xx.blbl.model.user.UserSpaceInfo;
import com.xx.blbl.model.user.UserStatModel;
import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.model.video.VideoPvModel;
import com.xx.blbl.model.video.detail.VideoDetailModel;
import com.xx.blbl.network.response.AllDynamicResponse;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.network.response.BaseBaseResponse;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.ChatRoomWrapper;
import com.xx.blbl.network.response.FavoriteFolderDetailWrapper;
import com.xx.blbl.network.response.FavoriteFoldersWrapper;
import com.xx.blbl.network.response.FollowingResponse;
import com.xx.blbl.network.response.GetAllSeriesWrapper;
import com.xx.blbl.network.response.GetBiliTicketWrapper;
import com.xx.blbl.network.response.GetFollowUserWrapper;
import com.xx.blbl.network.response.GetLaneWrapper;
import com.xx.blbl.network.response.GetTimeLineWrapper;
import com.xx.blbl.network.response.GetVideoByChannelWrapper;
import com.xx.blbl.network.response.HistoryListResponse;
import com.xx.blbl.network.response.HotWordWrapper;
import com.xx.blbl.network.response.LaterWatchWrapper;
import com.xx.blbl.network.response.ListDataModel;
import com.xx.blbl.network.response.LiveCategoryDetailListWrapper;
import com.xx.blbl.network.response.LiveListWrapper;
import com.xx.blbl.network.response.MyFollowingResponseWrapper;
import com.xx.blbl.network.response.PlayerInfoDataWrapper;
import com.xx.blbl.network.response.RecommendListDataModel;
import com.xx.blbl.network.response.SearchResponseWrapper;
import com.xx.blbl.network.response.SearchSuggestWrapper;
import com.xx.blbl.network.response.SubtitleResponse;
import com.xx.blbl.network.response.UserDynamicResponse;

import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import java.util.List;
import java.util.Map;

public interface ApiService {
    @POST("pgc/web/follow/del")
    Call<Base2Response<FollowSeriesResult>> cancelFollowSeries(@Body RequestBody c8);

    @GET("pgc/view/web/season/user/status")
    Call<Base2Response<CheckUserSeriesResult>> checkSeriesUserStat(
            @Query("season_id") Long l7, @Query("ep_id") Long l8, @Query("ts") long j7);

    @GET
    Call<BaseResponse<SignInResultModel>> checkSignInResult(
            @Url String str, @Query("qrcode_key") String str2);

    @GET("x/relation")
    Call<BaseResponse<CheckRelationModel>> checkUserRelation(@Query("fid") String str);

    @POST("x/v3/fav/resource/deal")
    Call<BaseResponse<CollectionResultModel>> collection(@Body RequestBody c8);

    @POST("pgc/web/follow/add")
    Call<Base2Response<FollowSeriesResult>> followSeries(@Body RequestBody c8);

    @GET("x/polymer/web-dynamic/desktop/v1/feed/video")
    Call<BaseResponse<AllDynamicResponse>> getAllDynamic(
            @Query("offset") Long l7, @Query("page") int i4);

    @GET("pgc/season/index/result")
    Call<BaseResponse<GetAllSeriesWrapper>> getAllSeries(@QueryMap Map<String, String> map);

    @GET("pgc/page/pc/bangumi/tab")
    Call<BaseResponse<GetLaneWrapper>> getAnimations(
            @Query("is_refresh") int i4, @Query("cursor") long j7);

    @POST("bapis/bilibili.api.ticket.v1.Ticket/GenWebTicket")
    Call<BaseResponse<GetBiliTicketWrapper>> getBiliTicket(
            @Query("key_id") String str,
            @Query("hexsign") String str2,
            @Query("context[ts]") long j7,
            @Query("csrf") String str3);

    @GET
    Call<String> getCaptcha(@Url String str, @Query("source") String str2);

    @GET("pgc/page/pc/cinema/tab")
    Call<BaseResponse<GetLaneWrapper>> getCinema(
            @Query("is_refresh") int i4, @Query("cursor") long j7);

    @GET("x/v1/dm/list.so")
    Call<String> getDanmaku(@Query("oid") long j7);

    @GET("x/v3/fav/folder/info")
    Call<BaseResponse<FolderDetailModel>> getFavoriteDetail(@Query("media_id") long j7);

    @GET("x/v3/fav/resource/list")
    Call<BaseResponse<FavoriteFolderDetailWrapper>> getFavoriteFolderDetail(
            @Query("media_id") long j7,
            @Query("pn") int i4,
            @Query("ps") int i7,
            @Query("order") String str,
            @Query("type") int i8,
            @Query("platform") String str2,
            @Query("jsonp") String str3);

    @GET("x/v3/fav/folder/created/list-all")
    Call<BaseResponse<FavoriteFoldersWrapper>> getFavoritesFolder(
            @Query("up_mid") long j7, @Query("type") int i4);

    @GET("x/relation/followers")
    Call<BaseResponse<GetFollowUserWrapper>> getFollower(
            @Query("vmid") String str, @Query("pn") int i4, @Query("ps") int i7);

    @GET("x/relation/followings")
    Call<BaseResponse<GetFollowUserWrapper>> getFollowing(
            @Query("vmid") String str, @Query("pn") int i4, @Query("ps") int i7);

    @GET("x/web-interface/history/cursor")
    Call<BaseResponse<HistoryListResponse>> getHistory(
            @Query("view_at") long j7, @Query("ps") int i4);

    @GET("x/web-interface/popular")
    Call<BaseResponse<ListDataModel<VideoModel>>> getHotList(
            @Query("pn") int i4, @Query("ps") int i7);

    @GET("x/stein/edgeinfo_v2")
    Call<BaseResponse<InteractionModel>> getInteractionVideoInfo(
            @Query("aid") Long l7,
            @Query("bvid") String str,
            @Query("graph_version") String str2,
            @Query("edge_id") long j7);

    @GET("x/v2/history/toview")
    Call<BaseResponse<LaterWatchWrapper>> getLaterWatch();

    @GET
    Call<BaseResponse<List<LiveAreaCategoryParent>>> getLiveArea(@Url String str);

    @GET
    @retrofit2.http.Headers({"Referer: https://live.bilibili.com/", "Origin: https://live.bilibili.com"})
    Call<BaseResponse<LiveCategoryDetailListWrapper>> getLiveCategoryDetailList(
            @Url String str,
            @Query("platform") String str2,
            @Query("parent_area_id") long j7,
            @Query("area_id") long j8,
            @Query("page") int i4);

    @GET("https://api.live.bilibili.com/xlive/web-room/v1/index/getDanmuInfo")
    Call<BaseResponse<ChatRoomWrapper>> getLiveChatRoomUrlWithWbi(
            @QueryMap Map<String, String> map);

    @GET
    Call<BaseResponse<LiveListWrapper>> getLiveList(
            @Url String str, @Query("platform") String str2);

    @GET
    Call<BaseResponse<LivePlayUrlDataModel>> getLivePlayInfo(
            @Url String str,
            @Query("cid") long j7,
            @Query("platform") String str2,
            @Query("quality") int i4);

    @GET
    Call<String> getMainPage(@Url String str);

    @GET("x/space/bangumi/follow/list")
    Call<BaseResponse<MyFollowingResponseWrapper>> getMyFollowingSeries(
            @Query("type") int i4,
            @Query("follow_status") int i7,
            @Query("pn") int i8,
            @Query("ps") int i9,
            @Query("vmid") long j7,
            @Query("ts") long j8);

    @GET("x/player/v2")
    Call<BaseResponse<PlayerInfoDataWrapper>> getPlayerInfo(
            @Query("aid") Long l7, @Query("bvid") String str, @Query("cid") long j7);

    @GET("x/player/wbi/v2")
    Call<BaseResponse<PlayerInfoDataWrapper>> getPlayerInfoWbi(@QueryMap Map<String, String> map);

    @Headers("Referer: https://www.bilibili.com/v/popular/rank/all")
    @GET("x/web-interface/ranking/v2")
    Call<BaseResponse<ListDataModel<VideoModel>>> getRanking(@QueryMap Map<String, String> params);

    @GET("x/web-interface/index/top/feed/rcmd")
    Call<BaseResponse<RecommendListDataModel<VideoModel>>> getRecommendList(
            @Query("fresh_idx") int i4,
            @Query("ps") int i7,
            @Query("feed_version") String str,
            @Query("fresh_type") int i8,
            @Query("plat") int i9);

    @GET("x/web-interface/archive/related")
    Call<BaseResponse<List<VideoModel>>> getRelated(
            @Query("avid") Long l7, @Query("bvid") String str);

    @GET
    Call<Base2Response<SearchSuggestWrapper>> getSearchSuggest(
            @Url String str, @Query("term") String str2, @Query("main_ver") String str3);

    @GET("pgc/web/timeline")
    Call<GetTimeLineWrapper> getSeriesTimeLine(
            @Query("types") int i4, @Query("before") int i7, @Query("after") int i8);

    @GET
    Call<BaseResponse<ScanQrModel>> getSignInQrCode(@Url String str);

    @GET
    Call<SubtitleResponse> getSubtitle(@Url String str);

    @GET("x/web-interface/nav")
    Call<BaseResponse<UserDetailInfoModel>> getUserDetailInfo();

    @GET("x/space/arc/list")
    Call<BaseResponse<UserDynamicResponse>> getUserDynamic(
            @Query("mid") String str, @Query("pn") int i4, @Query("ps") int i7);

    @GET
    Call<BaseResponse<FollowingResponse>> getUserFollowing(
            @Url String str, @Query("teenagers_mode") int i4);

    @GET("x/member/web/account")
    Call<BaseResponse<UserInfoModel>> getUserInfo();

    @GET("x/space/wbi/acc/info")
    Call<BaseResponse<UserSpaceInfo>> getUserSpace(@QueryMap Map<String, String> map);

    @GET("x/web-interface/nav/stat")
    Call<BaseResponse<UserStatModel>> getUserStat();

    @GET("x/web-interface/web/channel/featured/list")
    Call<BaseResponse<GetVideoByChannelWrapper>> getVideoByNewChannel(
            @Query("channel_id") long j7,
            @Query("filter_type") int i4,
            @Query("offset") String str,
            @Query("page_size") int i7);

    @GET("x/v2/dm/web/seg.so")
    Call<Dm.DmSegMobileReply> getVideoComment(
            @Query("type") int i4,
            @Query("oid") long j7,
            @Query("pid") long j8,
            @Query("segment_index") int i7);

    @GET("x/v2/dm/web/seg.so")
    Call<Dm.DmSegMobileReply> getVideoCommentWbi(@QueryMap Map<String, String> map);

    @GET("x/web-interface/view/detail")
    Call<BaseResponse<VideoDetailModel>> getVideoDetail(
            @Query("avid") Long l7, @Query("bvid") String str);

    @GET("pgc/view/web/season")
    Call<Base2Response<EpisodesDetailModel>> getVideoEpisodes(
            @Query("season_id") Long l7, @Query("ep_id") Long l8);

    @GET("x/player/playurl")
    Call<BaseResponse<PlayInfoModel>> getVideoPlayInfo(
            @Query("avid") Long l7,
            @Query("bvid") String str,
            @Query("cid") long j7,
            @Query("qn") int i4,
            @Query("fnval") int i7,
            @Query("fourk") int i8,
            @Query("fnver") int i9);

    @GET("x/player/wbi/playurl?voice_balance=1&gaia_source=pre-load&isGaiaAvoided=true")
    Call<BaseResponse<PlayInfoModel>> getVideoPlayInfoWbi(@QueryMap Map<String, String> map);

    @GET("pgc/player/web/v2/playurl")
    Call<com.google.gson.JsonObject> getVideoPlayPgcInfo(
            @QueryMap Map<String, String> params, @Header("Referer") String referer);

    @GET("x/player/pagelist")
    Call<BaseResponse<List<VideoPvModel>>> getVideoPv(
            @Query("avid") Long l7, @Query("bvid") String str);

    @GET("x/web-interface/zone")
    Call<BaseResponse<ZoneModel>> getZoneInfo();

    @POST("x/web-interface/coin/add")
    Call<BaseResponse<GiveCoinResultModel>> giveCoin(
            @Query("avid") Long l7,
            @Query("bvid") String str,
            @Query("multiply") int i4,
            @Query("select_like") int i7,
            @Query("csrf") String str2);

    @GET("x/v2/fav/video/favoured")
    Call<BaseResponse<CheckFavoriteModel>> hasCollection(@Query("aid") String str);

    @GET("x/web-interface/archive/coins")
    Call<BaseResponse<CheckGiveCoinModel>> hasGiveCoin(
            @Query("avid") Long l7, @Query("bvid") String str);

    @GET("x/web-interface/archive/has/like")
    Call<BaseResponse<Integer>> hasLike(@Query("avid") Long l7, @Query("bvid") String str);

    @POST("x/web-interface/archive/like")
    Call<BaseResponse<Integer>> like(
            @Query("avid") Long l7,
            @Query("bvid") String str,
            @Query("like") int i4,
            @Query("csrf") String str2);

    @POST("x/click-interface/web/heartbeat")
    Call<BaseResponse<String>> playVideoHeartbeat(@Body RequestBody c8);

    @GET
    Call<HotWordWrapper> retrieveHotWordForSearch(@Url String str);

    @GET("x/web-interface/search/all/v2")
    Call<BaseResponse<SearchAllResponseData>> searchAll(
            @Query("keyword") String str,
            @Query("platform") String str2,
            @Query("highlight") int i4,
            @Query("page_size") int i7,
            @Query("page") int i8);

    @GET("x/web-interface/wbi/search/all/v2")
    Call<BaseResponse<SearchAllResponseData>> searchAllWbi(@QueryMap Map<String, String> map);

    @GET("x/web-interface/search/type")
    Call<BaseResponse<SearchResponseWrapper>> searchByType(
            @Query("search_type") String str,
            @Query("keyword") String str2,
            @Query("order") String str3,
            @Query("duration") int i4,
            @Query("tids") int i7,
            @Query("page") int i8,
            @Query("page_size") int i9,
            @Query("platform") String str4,
            @Query("highlight") int i10);

    @GET("x/web-interface/wbi/search/type")
    Call<BaseResponse<SearchResponseWrapper>> searchByTypeWbi(@QueryMap Map<String, String> map);

    @POST
    Call<BaseResponse<SignOutModel>> signOut(@Url String str, @Body RequestBody c8);

    @POST("x/web-interface/archive/like/triple")
    Call<BaseResponse<TripleActionResultModel>> tripleAction(
            @Query("avid") Long l7, @Query("bvid") String str, @Query("csrf") String str2);

    @POST("x/relation/modify")
    Call<BaseBaseResponse> userRelationModify(@Body RequestBody c8);

    @GET("x/player/online/total")
    Call<BaseResponse<WatchingTotalNumberModel>> watchingTotalNumber(
            @Query("avid") Long l7, @Query("bvid") String str, @Query("cid") long j7);
}
