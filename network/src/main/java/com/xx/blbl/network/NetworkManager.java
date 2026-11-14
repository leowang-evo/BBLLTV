package com.xx.blbl.network;

import com.google.gson.Gson;
import com.xx.blbl.model.WbiImage;
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
import com.xx.blbl.model.search.SearchType;
import com.xx.blbl.model.series.AllSeriesFilterModel;
import com.xx.blbl.model.series.AllSeriesFilterOption;
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

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import retrofit2.Call;
import retrofit2.Callback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class NetworkManager {
    private final NetworkEnvironment environment;
    private static final String API_BASE = "https://api.bilibili.com/";
    private static final String API_LIVE = "https://api.live.bilibili.com/";
    private static final String API_PASSPORT = "https://passport.bilibili.com/";
    private static final String API_SEARCH = "https://s.search.bilibili.com/";
    private static final String API_VC = "https://api.vc.bilibili.com/";
    public static final Companion Companion = new Companion(null);
    public static final String baseUrl = "https://api.bilibili.com/";
    private String biliTicket;
    private final NetworkEnvironment.CookieStore cookiePersistor;
    private String currentUA;
    private final Gson gson;
    private final ApiService netService;
    private String serviceTime;
    private final WbiGenerator wbiGenerator;
    private String wbiImageKey;
    private String wbiSubKey;

    public static final class Companion {
        private Companion() {}

        public /* synthetic */ Companion(DefaultConstructorMarker dVar) {
            this();
        }
    }

    public final class HeaderInterceptor implements Interceptor {
        public HeaderInterceptor() {}

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {

            Intrinsics.checkNotNullParameter(chain, "chain");
            return chain.proceed(
                    chain.request()
                            .newBuilder()
                            .header("Origin", chain.request().header("Origin") == null
                                    ? "https://www.bilibili.com" : chain.request().header("Origin"))
                            .header(
                                    "Referer",
                                    chain.request().header("Referer") == null
                                            ? "https://www.bilibili.com"
                                            : chain.request().header("Referer"))
                            .header("User-Agent", NetworkManager.this.currentUA)
                            .build());
        }
    }

    public final class HttpCacheInterceptor implements Interceptor {
        public HttpCacheInterceptor() {}

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {

            Intrinsics.checkNotNullParameter(chain, "chain");
            Response response = chain.proceed(chain.request());
            ResponseBody body = response.body();
            if (response.code() != 200 || body == null) return response;
            MediaType contentType = body.contentType();
            byte[] bytes = body.bytes();
            boolean deflate = false;
            for (String name : response.headers().names()) {
                if ((name.equalsIgnoreCase("Accept-Encoding")
                                || name.equalsIgnoreCase("Content-Encoding"))
                        && response.header(name).contains("deflate")) {
                    deflate = true;
                    break;
                }
            }
            if (deflate) {
                if (bytes.length == 0) {
                    bytes = null;
                } else {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    try (InflaterInputStream input =
                            new InflaterInputStream(
                                    new ByteArrayInputStream(bytes), new Inflater(true))) {
                        byte[] buffer = new byte[1024];
                        for (int count; (count = input.read(buffer)) >= 0; )
                            output.write(buffer, 0, count);
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                    bytes = output.toByteArray();
                }
                Intrinsics.checkNotNullExpressionValue(bytes, "uncompress(...)");
            }
            return response.newBuilder().body(ResponseBody.create(bytes, contentType)).build();
        }
    }

    public NetworkManager() {

        this(NetworkEnvironment.defaultEnvironment());
    }

    public NetworkManager(NetworkEnvironment environment) {
        this.environment = java.util.Objects.requireNonNull(environment, "environment");
        this.cookiePersistor = environment.cookieStore();
        this.serviceTime = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date());
        this.wbiImageKey = "";
        this.wbiSubKey = "";
        this.biliTicket = "";
        this.wbiGenerator = new WbiGenerator();
        this.currentUA = environment.userAgent();
        this.gson = new Gson();
        okhttp3.OkHttpClient client =
                new okhttp3.OkHttpClient.Builder()
                        .cookieJar(environment.cookieJar())
                        .addInterceptor(new HeaderInterceptor())
                        .addNetworkInterceptor(new HttpCacheInterceptor())
                        .retryOnConnectionFailure(true)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build();
        retrofit2.Retrofit.Builder builder =
                new retrofit2.Retrofit.Builder()
                        .baseUrl(environment.baseUrl())
                        .client(client)
                        .addConverterFactory(
                                retrofit2.converter.scalars.ScalarsConverterFactory.create())
                        .addConverterFactory(
                                retrofit2.converter.protobuf.ProtoConverterFactory.create())
                        .addConverterFactory(
                                retrofit2.converter.gson.GsonConverterFactory.create(gson));
        if (environment.callbackExecutor() != null)
            builder.callbackExecutor(environment.callbackExecutor());
        this.netService = builder.build().create(ApiService.class);
    }

    private final void getAnimation(
            int i4,
            long j7,
            final NetResultCallback<BaseResponse<GetLaneWrapper>> netResultCallback) {
        this.netService.getAnimations(i4, j7).enqueue(new ResponseCallback<>(netResultCallback));
    }

    private final void getCinema(
            int i4,
            long j7,
            final NetResultCallback<BaseResponse<GetLaneWrapper>> netResultCallback) {
        this.netService.getCinema(i4, j7).enqueue(new ResponseCallback<>(netResultCallback));
    }

    public final void cancelFollowSeries(
            long j7, final NetResultCallback<Base2Response<FollowSeriesResult>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String value = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                value = c0937j.value();
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        String value2 = String.valueOf(j7);
        Intrinsics.checkNotNullParameter(value2, "value");
        arrayList2.add(
                NetworkSupport.formEncode("season_id", " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        arrayList3.add(
                NetworkSupport.formEncode(value2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        Intrinsics.checkNotNullParameter(value, "value");
        arrayList2.add(
                NetworkSupport.formEncode("csrf", " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        arrayList3.add(NetworkSupport.formEncode(value, " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        this.netService
                .cancelFollowSeries(new FormBody(arrayList2, arrayList3))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void checkSeriesUserState(
            @retrofit2.http.Query("season_id") Long l7,
            @retrofit2.http.Query("ep_id") Long l8,
            final NetResultCallback<Base2Response<CheckUserSeriesResult>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .checkSeriesUserStat(l7, l8, System.currentTimeMillis())
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void checkSignInResult(
            String key, final NetResultCallback<BaseResponse<SignInResultModel>> callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .checkSignInResult(
                        "https://passport.bilibili.com/x/passport-login/web/qrcode/poll", key)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void checkUserRelation(
            String targetMid, final NetResultCallback<BaseResponse<CheckRelationModel>> callback) {
        Intrinsics.checkNotNullParameter(targetMid, "targetMid");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.checkUserRelation(targetMid).enqueue(new ResponseCallback<>(callback));
    }

    public final void collection(
            long j7,
            boolean z7,
            String fid,
            final NetResultCallback<BaseResponse<CollectionResultModel>> callback) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str = c0937j.value();
            }
        }
        FormBody.Builder vVar = new FormBody.Builder();
        vVar.add("rid", String.valueOf(j7));
        vVar.add("type", "2");
        vVar.add("csrf", str);
        vVar.add("platform", "web");
        vVar.add("eab_x", "1");
        vVar.add("gaia_source", "web_normal");
        vVar.add("ga", "1");
        if (z7) {
            vVar.add("add_media_ids", fid);
        } else {
            vVar.add("del_media_ids", fid);
        }
        this.netService.collection(vVar.build()).enqueue(new ResponseCallback<>(callback));
    }

    public final void followSeries(
            long j7, final NetResultCallback<Base2Response<FollowSeriesResult>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String value = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                value = c0937j.value();
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        String value2 = String.valueOf(j7);
        Intrinsics.checkNotNullParameter(value2, "value");
        arrayList2.add(
                NetworkSupport.formEncode("season_id", " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        arrayList3.add(
                NetworkSupport.formEncode(value2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        Intrinsics.checkNotNullParameter(value, "value");
        arrayList2.add(
                NetworkSupport.formEncode("csrf", " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        arrayList3.add(NetworkSupport.formEncode(value, " \"':;<=>@[]^`{}|/\\?#&!$(),~", 0, 0, 91));
        this.netService
                .followSeries(new FormBody(arrayList2, arrayList3))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getAllDynamic(
            int i4, Long l7, final NetResultCallback<BaseResponse<AllDynamicResponse>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getAllDynamic(l7, i4).enqueue(new ResponseCallback<>(callback));
    }

    public final void getAllSeries(
            int i4,
            int i7,
            List<AllSeriesFilterModel> filters,
            final NetResultCallback<BaseResponse<GetAllSeriesWrapper>> callback) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap map = new HashMap();
        map.put("st", String.valueOf(i7));
        map.put("season_type", String.valueOf(i7));
        map.put("page", String.valueOf(i4));
        map.put("pagesize", "24");
        map.put("type", "1");
        map.put("sort", "0");
        for (AllSeriesFilterModel allSeriesFilterModel : filters) {
            List<AllSeriesFilterOption> options = allSeriesFilterModel.getOptions();
            if (options != null
                    && allSeriesFilterModel.getCurrentSelect() >= 0
                    && allSeriesFilterModel.getCurrentSelect() < options.size()) {
                map.put(
                        allSeriesFilterModel.getKey(),
                        options.get(allSeriesFilterModel.getCurrentSelect()).getValue());
            }
        }
        this.netService.getAllSeries(map).enqueue(new ResponseCallback<>(callback));
    }

    public final void getBiliTicket(
            final NetResultCallback<BaseResponse<GetBiliTicketWrapper>> callback)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str = c0937j.value();
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis() / ((long) 1000);
        String strO = NetworkSupport.concat("ts", jCurrentTimeMillis);
        Mac mac = Mac.getInstance("HmacSHA256");
        Charset charset = StandardCharsets.UTF_8;
        mac.init(new SecretKeySpec("XgwSnGZ1p".getBytes(charset), "HmacSHA256"));
        byte[] bArrDoFinal = mac.doFinal(strO.getBytes(charset));
        StringBuilder sb = new StringBuilder();
        for (byte b5 : bArrDoFinal) {
            String hexString = Integer.toHexString(b5 & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        String string = sb.toString();
        ApiService apiService = this.netService;
        Intrinsics.checkNotNull(string);
        apiService
                .getBiliTicket("ec02", string, jCurrentTimeMillis, str)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getCaptcha(final NetResultCallback<String> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getCaptcha("http://passport.bilibili.com/x/passport-login/captcha", "main_web")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final NetworkEnvironment.CookieStore getCookiePersistor() {
        return this.cookiePersistor;
    }

    public final String getCurrentUA() {
        return this.currentUA;
    }

    public final void getDm(long j7, final NetResultCallback<List<DanmakuItem>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getDanmaku(j7)
                .enqueue(
                        new Callback() {
                            @Override
                            public void onFailure(Call call, Throwable t7) {
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(t7, "t");
                                callback.onFailure(t7);
                            }

                            @Override
                            public void onResponse(Call call, retrofit2.Response response) {
                                int i4;
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(response, "response");
                                try {
                                    XmlPullParserFactory xmlPullParserFactoryNewInstance =
                                            XmlPullParserFactory.newInstance();
                                    xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                                    XmlPullParser xmlPullParserNewPullParser =
                                            xmlPullParserFactoryNewInstance.newPullParser();
                                    xmlPullParserNewPullParser.setInput(
                                            new StringReader((String) response.body()));
                                    int eventType = xmlPullParserNewPullParser.getEventType();
                                    ArrayList arrayList = new ArrayList();
                                    int i7 = 0;
                                    String attributeValue = "";
                                    String str = attributeValue;
                                    boolean zA = false;
                                    long j8 = 1;
                                    while (eventType != 1) {
                                        if (eventType == 2) {
                                            zA =
                                                    Intrinsics.areEqual(
                                                            xmlPullParserNewPullParser.getName(),
                                                            "d");
                                            if (zA) {
                                                attributeValue =
                                                        xmlPullParserNewPullParser
                                                                .getAttributeValue(null, "p");
                                                Intrinsics.checkNotNullExpressionValue(
                                                        attributeValue, "getAttributeValue(...)");
                                                str = "";
                                            } else {
                                                attributeValue = "";
                                                str = attributeValue;
                                            }
                                        } else if (eventType == 3
                                                && zA
                                                && Intrinsics.areEqual(
                                                        xmlPullParserNewPullParser.getName(),
                                                        "d")) {
                                            List listL =
                                                    NetworkSupport.split(
                                                            attributeValue, new String[] {","});
                                            if (zA && listL.size() > 8) {
                                                double timestampMillis =
                                                        Double.parseDouble((String) listL.get(i7))
                                                                * ((double) 1000);
                                                int i8 = Integer.parseInt((String) listL.get(1));
                                                int i9 = Integer.parseInt((String) listL.get(2));
                                                int i10 = Integer.parseInt((String) listL.get(3));
                                                if (Double.isNaN(timestampMillis)) {
                                                    throw new IllegalArgumentException(
                                                            "Cannot round NaN value.");
                                                }
                                                long jRound = Math.round(timestampMillis);
                                                if (i8 != 4) {
                                                    i4 = i8 != 5 ? 1 : 5;
                                                } else {
                                                    i4 = 4;
                                                }
                                                arrayList.add(
                                                        new DanmakuItem(
                                                                j8, jRound, str, i4, i9, i10));
                                            }
                                        } else if (eventType == 4) {
                                            String text = xmlPullParserNewPullParser.getText();
                                            Intrinsics.checkNotNullExpressionValue(
                                                    text, "getText(...)");
                                            str = text;
                                        }
                                        j8++;
                                        eventType = xmlPullParserNewPullParser.next();
                                        i7 = 0;
                                    }
                                    callback.onResponse(arrayList);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    callback.onFailure(e);
                                }
                            }
                        });
    }

    public final void getFavoriteDetail(
            long j7, final NetResultCallback<BaseResponse<FolderDetailModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getFavoriteDetail(j7).enqueue(new ResponseCallback<>(callback));
    }

    public final void getFavoriteFolderDetail(
            long j7,
            int i4,
            int i7,
            final NetResultCallback<BaseResponse<FavoriteFolderDetailWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getFavoriteFolderDetail(j7, i4, i7, "mtime", 0, "web", "jsonp")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getFavoriteFolders(
            long j7, final NetResultCallback<BaseResponse<FavoriteFoldersWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getFavoritesFolder(j7, 2).enqueue(new ResponseCallback<>(callback));
    }

    public final void getFollower(
            String userId,
            int i4,
            final NetResultCallback<BaseResponse<GetFollowUserWrapper>> callback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getFollower(userId, i4, 50).enqueue(new ResponseCallback<>(callback));
    }

    public final void getFollowing(
            final NetResultCallback<BaseResponse<FollowingResponse>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getUserFollowing(
                        "https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/w_dyn_uplist", 0)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getFollowing(
            String userId,
            int i4,
            final NetResultCallback<BaseResponse<GetFollowUserWrapper>> callback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getFollowing(userId, i4, 50).enqueue(new ResponseCallback<>(callback));
    }

    public final void getHistory(
            long j7, int i4, final NetResultCallback<BaseResponse<HistoryListResponse>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getHistory(j7, i4).enqueue(new ResponseCallback<>(callback));
    }

    public final void getHotVideos(
            int i4,
            int i7,
            final NetResultCallback<BaseResponse<ListDataModel<VideoModel>>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getHotList(i4, i7).enqueue(new ResponseCallback<>(callback));
    }

    public final void getInteractionVideoInfo(
            Long l7,
            String str,
            String graph_version,
            long j7,
            final NetResultCallback<BaseResponse<InteractionModel>> callback) {
        Intrinsics.checkNotNullParameter(graph_version, "graph_version");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getInteractionVideoInfo(l7, str, graph_version, j7)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getLane(
            int i4, int i7, long j7, NetResultCallback<BaseResponse<GetLaneWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (i4 == 2) {
            getCinema(i7, j7, callback);
        } else {
            getAnimation(i7, j7, callback);
        }
    }

    public final void getLaterWatch(
            final NetResultCallback<BaseResponse<LaterWatchWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getLaterWatch().enqueue(new ResponseCallback<>(callback));
    }

    public final void getLiveArea(
            final NetResultCallback<BaseResponse<List<LiveAreaCategoryParent>>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getLiveArea("https://api.live.bilibili.com/room/v1/Area/getList")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getLiveCategoryDetailList(
            long j7,
            long j8,
            int i4,
            final NetResultCallback<BaseResponse<LiveCategoryDetailListWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getLiveCategoryDetailList(
                        "https://api.live.bilibili.com/room/v3/area/getRoomList?page_size=20&sort_type=online",
                        "web",
                        j8,
                        j7,
                        i4)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getLiveChatRoomUrl(
            long j7, final NetResultCallback<BaseResponse<ChatRoomWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(j7));
        this.netService
                .getLiveChatRoomUrlWithWbi(
                        this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getLiveList(final NetResultCallback<BaseResponse<LiveListWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getLiveList(
                        "https://api.live.bilibili.com/xlive/web-interface/v1/index/getList", "web")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getLiveUrl(
            long j7, int i4, final NetResultCallback<BaseResponse<LivePlayUrlDataModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getLivePlayInfo(
                        "https://api.live.bilibili.com/room/v1/Room/playUrl", j7, "web", i4)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getMainPage() {
        this.netService
                .getMainPage("https://www.bilibili.com/")
                .enqueue(
                        new Callback() {
                            @Override
                            public void onFailure(Call call, Throwable t7) {
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(t7, "t");
                            }

                            @Override
                            public void onResponse(Call call, retrofit2.Response response) {
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(response, "response");
                            }
                        });
    }

    public final void getMyFollowingSeries(
            int i4,
            int i7,
            int i8,
            long j7,
            final NetResultCallback<BaseResponse<MyFollowingResponseWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getMyFollowingSeries(i4, 0, i7, i8, j7, System.currentTimeMillis())
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getPlayerInfo(
            Long l7,
            String str,
            long j7,
            final NetResultCallback<BaseResponse<PlayerInfoDataWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getPlayerInfo(l7, str, j7).enqueue(new ResponseCallback<>(callback));
    }

    public final void getPlayerInfoWbi(
            Long l7,
            String str,
            long j7,
            final NetResultCallback<BaseResponse<PlayerInfoDataWrapper>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        if (l7 != null) {
            map.put("avid", String.valueOf(l7.longValue()));
        }
        if (str != null) {
            map.put("bvid", str);
        }
        map.put("cid", String.valueOf(j7));
        this.netService
                .getPlayerInfoWbi(
                        this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getQRLoginInfo(final NetResultCallback<BaseResponse<ScanQrModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getSignInQrCode(
                        "https://passport.bilibili.com/x/passport-login/web/qrcode/generate")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getRanking(
            int i4, final NetResultCallback<BaseResponse<ListDataModel<VideoModel>>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> params = new HashMap<>();
        params.put("rid", String.valueOf(i4));
        params.put("type", "all");
        params.put("web_location", "333.934");
        this.netService
                .getRanking(
                        this.wbiGenerator.generatorWbi(params, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getRecommendVideos(
            int i4,
            int i7,
            final NetResultCallback<BaseResponse<RecommendListDataModel<VideoModel>>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getRecommendList(i4, i7, "V1", i4, 1)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getRelatedVideo(
            Long l7, String str, final NetResultCallback<BaseResponse<List<VideoModel>>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getRelated(l7, str).enqueue(new ResponseCallback<>(callback));
    }

    public final void getSearchHotWord(final NetResultCallback<HotWordWrapper> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .retrieveHotWordForSearch("https://s.search.bilibili.com/main/hotword")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getSearchSuggest(
            String term, final NetResultCallback<Base2Response<SearchSuggestWrapper>> callback) {
        Intrinsics.checkNotNullParameter(term, "term");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getSearchSuggest("https://s.search.bilibili.com/main/suggest", term, "v1")
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getSeriesTimeLine(final NetResultCallback<GetTimeLineWrapper> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getSeriesTimeLine(1, 6, 6).enqueue(new ResponseCallback<>(callback));
    }

    public final String getServiceTime() {
        return this.serviceTime;
    }

    public final void getSubtitle(String url, final NetResultCallback<SubtitleResponse> callback) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getSubtitle(url).enqueue(new ResponseCallback<>(callback));
    }

    public final void getUserDetailInfo(
            final NetResultCallback<BaseResponse<UserDetailInfoModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getUserDetailInfo().enqueue(new ResponseCallback<>(callback));
    }

    public final void getUserDynamic(
            String userId,
            int i4,
            int i7,
            final NetResultCallback<BaseResponse<UserDynamicResponse>> callback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getUserDynamic(userId, i4, i7).enqueue(new ResponseCallback<>(callback));
    }

    public final void getUserInfo(final NetResultCallback<BaseResponse<UserInfoModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getUserInfo().enqueue(new ResponseCallback<>(callback));
    }

    public final void getUserSpace(
            String mid, final NetResultCallback<BaseResponse<UserSpaceInfo>> callback) {
        Intrinsics.checkNotNullParameter(mid, "mid");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        map.put("mid", mid);
        map.put("platform", "web");
        map.put("web_location", "1550101");
        map.put("token", "");
        this.netService
                .getUserSpace(this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getUserStat(final NetResultCallback<BaseResponse<UserStatModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getUserStat().enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoComment(
            long j7, long j8, int i4, final NetResultCallback<Dm.DmSegMobileReply> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getVideoComment(1, j7, j8, i4).enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoCommentWbi(
            long j7, long j8, int i4, final NetResultCallback<Dm.DmSegMobileReply> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "1");
        map.put("oid", String.valueOf(j7));
        map.put("pid", String.valueOf(j8));
        map.put("segment_index", String.valueOf(i4));
        this.netService
                .getVideoCommentWbi(
                        this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoDetail(
            Long l7, String str, final NetResultCallback<BaseResponse<VideoDetailModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (NetworkSupport.isEmpty(str)) {
            str = null;
        } else {
            l7 = null;
        }
        this.netService.getVideoDetail(l7, str).enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoEpisodes(
            @retrofit2.http.Query("season_id") Long l7,
            @retrofit2.http.Query("ep_id") Long l8,
            final NetResultCallback<Base2Response<EpisodesDetailModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getVideoEpisodes(l7, l8).enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoPlayInfo(
            Long l7,
            String str,
            long j7,
            int i4,
            int i7,
            int i8,
            final NetResultCallback<BaseResponse<PlayInfoModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getVideoPlayInfo(l7, str, j7, i4, i7, i8, 0)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoPlayInfoWbi(
            Long l7,
            String str,
            long j7,
            int i4,
            int i7,
            int i8,
            final NetResultCallback<BaseResponse<PlayInfoModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        if (l7 != null) {
            map.put("avid", String.valueOf(l7.longValue()));
        }
        if (str != null) {
            map.put("bvid", str);
        }
        map.put("cid", String.valueOf(j7));
        map.put("qn", String.valueOf(i4));
        map.put("fnver", "0");
        map.put("fnval", String.valueOf(i7));
        map.put("fourk", String.valueOf(i8));
        this.netService
                .getVideoPlayInfoWbi(
                        this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideoPlayPgcInfo(
            Long l7,
            String str,
            Long l8,
            Long l9,
            int i4,
            int i7,
            int i8,
            final NetResultCallback<Base2Response<PlayInfoModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> params = new HashMap<>();
        if (l7 != null) params.put("avid", String.valueOf(l7));
        if (str != null && !str.isEmpty()) params.put("bvid", str);
        if (l8 != null) params.put("ep_id", String.valueOf(l8));
        if (l9 != null) params.put("cid", String.valueOf(l9));
        params.put("qn", String.valueOf(i4));
        params.put("fnval", String.valueOf(i7));
        params.put("fourk", String.valueOf(i8));
        params.put("fnver", "0");
        params.put("from_client", "BROWSER");
        params.put("drm_tech_type", "2");
        params.put("web_location", "1315873");
        this.netService
                .getVideoPlayPgcInfo(
                        this.wbiGenerator.generatorWbi(params, this.wbiImageKey, this.wbiSubKey),
                        "https://www.bilibili.com/bangumi/play/ep" + l8)
                .enqueue(
                        new ResponseCallback<>(
                                new NetResultCallback<com.google.gson.JsonObject>() {
                                    public void onFailure(Throwable error) {
                                        callback.onFailure(error);
                                    }

                                    public void onResponse(com.google.gson.JsonObject body) {
                                        Base2Response<PlayInfoModel> response;
                                        try {
                                            response = PgcPlayResponse.decode(body, gson);
                                        } catch (RuntimeException error) {
                                            callback.onFailure(error);
                                            return;
                                        }
                                        callback.onResponse(response);
                                    }
                                }));
    }

    public final void getVideoPv(
            Long l7,
            String str,
            final NetResultCallback<BaseResponse<List<VideoPvModel>>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ApiService apiService = this.netService;
        if (l7 != null && l7.longValue() == 0) {
            l7 = null;
        }
        if (NetworkSupport.isEmpty(str)) {
            str = null;
        }
        apiService.getVideoPv(l7, str).enqueue(new ResponseCallback<>(callback));
    }

    public final void getVideosByNewChannel(
            long j7,
            String offset,
            int i4,
            final NetResultCallback<BaseResponse<GetVideoByChannelWrapper>> callback) {
        Intrinsics.checkNotNullParameter(offset, "offset");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .getVideoByNewChannel(j7, 0, offset, i4)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void getWatchingNumber(
            Long l7,
            String str,
            long j7,
            final NetResultCallback<BaseResponse<WatchingTotalNumberModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.watchingTotalNumber(l7, str, j7).enqueue(new ResponseCallback<>(callback));
    }

    public final void getZoneInfo(final NetResultCallback<BaseResponse<ZoneModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.getZoneInfo().enqueue(new ResponseCallback<>(callback));
    }

    public final void giveCoin(
            Long l7,
            String str,
            int i4,
            final NetResultCallback<BaseResponse<GiveCoinResultModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str2 = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str2 = c0937j.value();
            }
        }
        this.netService.giveCoin(l7, str, i4, 1, str2).enqueue(new ResponseCallback<>(callback));
    }

    public final void hasCollection(
            Long l7,
            String str,
            final NetResultCallback<BaseResponse<CheckFavoriteModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (l7 != null && l7 > 0) {
            this.netService
                    .hasCollection(String.valueOf(l7))
                    .enqueue(new ResponseCallback<>(callback));
        } else if (!NetworkSupport.isEmpty(str)) {
            getVideoDetail(
                    null,
                    str,
                    new NetResultCallback<BaseResponse<VideoDetailModel>>() {
                        public void onFailure(Throwable error) {
                            callback.onFailure(error);
                        }

                        public void onResponse(BaseResponse<VideoDetailModel> response) {
                            ApiFailure error = ApiFailure.fromResponse(response);
                            VideoModel video =
                                    response == null || response.getData() == null
                                            ? null
                                            : response.getData().getView();
                            if (error != null) callback.onFailure(error);
                            else if (video == null || video.getAid() <= 0)
                                callback.onFailure(
                                        new IllegalArgumentException("Missing video aid"));
                            else hasCollection(video.getAid(), null, callback);
                        }
                    });
        } else callback.onFailure(new IllegalArgumentException("Missing video identifier"));
    }

    public final void hasGiveCoin(
            Long l7,
            String str,
            final NetResultCallback<BaseResponse<CheckGiveCoinModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.hasGiveCoin(l7, str).enqueue(new ResponseCallback<>(callback));
    }

    public final void hasLike(
            Long l7, String str, final NetResultCallback<BaseResponse<Integer>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService.hasLike(l7, str).enqueue(new ResponseCallback<>(callback));
    }

    public final void like(
            Long l7, String str, int i4, final NetResultCallback<BaseResponse<Integer>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str2 = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str2 = c0937j.value();
            }
        }
        this.netService.like(l7, str, i4, str2).enqueue(new ResponseCallback<>(callback));
    }

    public final void loadCookie() {

        this.environment.reloadCookies();
    }

    public final void playHeartbeat(
            VideoModel model,
            long j7,
            long j8,
            int i4,
            long j9,
            int i7,
            final NetResultCallback<BaseResponse<String>> callback) {
        boolean z7;
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(callback, "callback");
        FormBody.Builder vVar = new FormBody.Builder();
        boolean z8 = true;
        if (NetworkSupport.isEmpty(model.getBvid())) {
            z7 = false;
        } else {
            vVar.add("bvid", model.getBvid());
            z7 = true;
        }
        if (model.getAid() != 0) {
            vVar.add("aid", String.valueOf(model.getAid()));
        } else {
            z8 = z7;
        }
        if (!z8) {
            callback.onFailure(new Throwable("Missing id"));
            return;
        }
        if (model.getCid() != 0) {
            vVar.add("cid", String.valueOf(model.getCid()));
        }
        if (model.getEpid() != 0) {
            vVar.add("epid", String.valueOf(model.getEpid()));
        }
        if (model.getSid() != 0) {
            vVar.add("sid", String.valueOf(model.getSid()));
        }
        if (j7 != 0) {
            vVar.add("mid", String.valueOf(j7));
        }
        vVar.add("played_time", String.valueOf(j8));
        vVar.add("realtime", String.valueOf(j8));
        vVar.add("start_ts", String.valueOf(j9));
        vVar.add("type", (model.getEpid() == 0 && model.getSid() == 0) ? "3" : "4");
        if (i7 != 0) {
            vVar.add("sub_type", String.valueOf(i7));
        }
        vVar.add("refer_url", "https://www.bilibili.com/");
        vVar.add("play_type", String.valueOf(i4));
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str = c0937j.value();
            }
        }
        if (!NetworkSupport.isEmpty(str)) {
            vVar.add("csrf", str);
        }
        this.netService.playVideoHeartbeat(vVar.build()).enqueue(new ResponseCallback<>(callback));
    }

    public final void refreshUA() {

        this.currentUA = this.environment.refreshUserAgent();
    }

    public final void searchAll(
            String keyword, final NetResultCallback<BaseResponse<SearchAllResponseData>> callback) {
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .searchAll(keyword, "pc", 0, 20, 1)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void searchAllWbi(
            String keyword, final NetResultCallback<BaseResponse<SearchAllResponseData>> callback) {
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("platform", "pc");
        map.put("highlight", "0");
        map.put("page_size", "42");
        map.put("page", "1");
        map.put("__refresh__", "true");
        map.put("order", "click");
        map.put("web_location", "1430654");
        map.put("source_tag", "3");
        this.netService
                .searchAllWbi(this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void searchByType(
            SearchType searchType,
            String keyword,
            int i4,
            String order,
            final NetResultCallback<BaseResponse<SearchResponseWrapper>> callback) {
        Intrinsics.checkNotNullParameter(searchType, "searchType");
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.netService
                .searchByType(searchType.getValue(), keyword, order, 0, 0, i4, 20, "pc", 0)
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void searchByTypeWbi(
            SearchType searchType,
            String keyword,
            int i4,
            String order,
            final NetResultCallback<BaseResponse<SearchResponseWrapper>> callback) {
        Intrinsics.checkNotNullParameter(searchType, "searchType");
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap<String, String> map = new HashMap<>();
        map.put("search_type", searchType.getValue());
        map.put("keyword", keyword);
        map.put("order", order);
        map.put("web_location", "1430654");
        map.put("duration", "0");
        map.put("tids", "0");
        map.put("page", String.valueOf(i4));
        map.put("page_size", "42");
        map.put("platform", "pc");
        map.put("highlight", "0");
        this.netService
                .searchByTypeWbi(
                        this.wbiGenerator.generatorWbi(map, this.wbiImageKey, this.wbiSubKey))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void setServiceTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serviceTime = str;
    }

    public final void setWbiInfo(WbiImage wbiImage) {
        Intrinsics.checkNotNullParameter(wbiImage, "wbiImage");
        if (!NetworkSupport.isEmpty(wbiImage.getImg_url())) {
            int iA = NetworkSupport.indexOf(wbiImage.getImg_url(), "wbi/", 0, 6);
            String strSubstring =
                    wbiImage.getImg_url()
                            .substring(
                                    iA + 4,
                                    NetworkSupport.indexOf(wbiImage.getImg_url(), ".", iA, 4));
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            this.wbiImageKey = strSubstring;
        }
        if (NetworkSupport.isEmpty(wbiImage.getSub_url())) {
            return;
        }
        int iA2 = NetworkSupport.indexOf(wbiImage.getSub_url(), "wbi/", 0, 6);
        String strSubstring2 =
                wbiImage.getSub_url()
                        .substring(
                                iA2 + 4,
                                NetworkSupport.indexOf(wbiImage.getSub_url(), ".", iA2, 4));
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        this.wbiSubKey = strSubstring2;
    }

    public final void signOut(final NetResultCallback<BaseResponse<SignOutModel>> callback) {
        MediaType uVarF;
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str = c0937j.value();
            }
        }
        if (NetworkSupport.isEmpty(str)) {
            callback.onFailure(new Throwable("Missing bilibili csrf"));
            return;
        }
        String strN = NetworkSupport.concat("biliCSRF=", str);
        try {
            uVarF = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");
        } catch (IllegalArgumentException unused) {
            uVarF = null;
        }
        this.netService
                .signOut(
                        "https://passport.bilibili.com/login/exit/v2",
                        RequestBody.create(strN, uVarF))
                .enqueue(new ResponseCallback<>(callback));
    }

    public final void tripleAction(
            Long l7,
            String str,
            final NetResultCallback<BaseResponse<TripleActionResultModel>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str2 = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str2 = c0937j.value();
            }
        }
        this.netService.tripleAction(l7, str, str2).enqueue(new ResponseCallback<>(callback));
    }

    public final void userRelationModify(
            String targetMid, int i4, final NetResultCallback<BaseBaseResponse> callback) {
        Intrinsics.checkNotNullParameter(targetMid, "targetMid");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ArrayList<Cookie> arrayList = new ArrayList<>(this.cookiePersistor.loadAll());
        if (arrayList.isEmpty()) {
            callback.onFailure(new Throwable("No cookies"));
            return;
        }
        String str = "";
        for (Cookie c0937j : arrayList) {
            if (Intrinsics.areEqual(c0937j.name(), "bili_jct")) {
                str = c0937j.value();
            }
        }
        FormBody.Builder vVar = new FormBody.Builder();
        vVar.add("fid", targetMid);
        vVar.add("act", String.valueOf(i4));
        vVar.add("csrf", str);
        vVar.add("re_src", "14");
        this.netService.userRelationModify(vVar.build()).enqueue(new ResponseCallback<>(callback));
    }

    // Preserve the original retained callback classes' synthetic accessor ABI.
    public static String access$getCurrentUA$p(NetworkManager manager) {
        return manager.currentUA;
    }
}
