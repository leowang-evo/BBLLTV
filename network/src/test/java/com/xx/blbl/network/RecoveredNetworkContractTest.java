package com.xx.blbl.network;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import okio.Buffer;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.Url;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Runs offline against published Retrofit/OkHttp; no APK or declaration headers. */
public final class RecoveredNetworkContractTest {
    private static int assertions;

    private static void equal(Object expected, Object actual) {
        assertions++;
        if (!Objects.equals(expected, actual))
            throw new AssertionError("expected=" + expected + "; actual=" + actual);
    }

    private static void throwsType(Class<? extends Throwable> type, Runnable action) {
        assertions++;
        try {
            action.run();
        } catch (Throwable thrown) {
            if (type.isInstance(thrown)) return;
            throw new AssertionError("Expected " + type + ", got " + thrown, thrown);
        }
        throw new AssertionError("Expected " + type);
    }

    private static void wbi() throws Exception {
        equal("d41d8cd98f00b204e9800998ecf8427e", MD5Util.md5(""));
        equal("900150983cd24fb0d6963f7d28e17f72", MD5Util.md5("abc"));
        WbiGenerator wbi = new WbiGenerator(() -> 1700000000000L);
        String key = "0123456789abcdef0123456789abcdef";
        Method filter = WbiGenerator.class.getDeclaredMethod("filterStr", String.class);
        filter.setAccessible(true);
        equal(",.", filter.invoke(wbi, "!'()*,."));
        equal("a+b%2Bc", filter.invoke(wbi, "a+b%2Bc"));
        equal("你好,弹幕.", filter.invoke(wbi, "你好,弹幕."));
        equal("%GG", filter.invoke(wbi, "%GG"));
        Method mixin = WbiGenerator.class.getDeclaredMethod("getMixinKey", String.class);
        mixin.setAccessible(true);
        equal("ef225870f2afa3d3bb5119a3dce7c69d", mixin.invoke(wbi, key + key));
        HashMap<String, String> params = new HashMap<>();
        params.put("z", "a+b");
        params.put("a", "!'()*,.");
        params.put("title", "你好,弹幕.");
        equal(params, wbi.generatorWbi(params, key, key));
        equal("a+b", params.get("z"));
        equal(",.", params.get("a"));
        equal("你好,弹幕.", params.get("title"));
        equal("1700000000", params.get("wts"));
        equal("63b68dfc664eaaffa022ff28891e9794", params.get("w_rid"));
        HashMap<String, String> same = new HashMap<>();
        same.put("raw", "a+b.");
        equal(same, wbi.generatorWbi(same, "", key));
        equal("a+b.", same.get("raw"));
        equal(1, same.size());
        HashMap<String, String> empty = new HashMap<>();
        equal(empty, wbi.generatorWbi(empty, key, key));
        equal(0, empty.size());
        throwsType(NullPointerException.class, () -> wbi.generatorWbi(null, key, key));
        HashMap<String, String> shortKeys = new HashMap<>();
        shortKeys.put("aid", "123");
        throwsType(
                StringIndexOutOfBoundsException.class, () -> wbi.generatorWbi(shortKeys, "x", "y"));
        for (long[] boundary :
                new long[][] {
                    {2147483648000L, Integer.MAX_VALUE}, {-2147483649000L, Integer.MIN_VALUE}
                }) {
            HashMap<String, String> one = new HashMap<>();
            one.put("aid", "123");
            new WbiGenerator(() -> boundary[0]).generatorWbi(one, key, key);
            equal(String.valueOf(boundary[1]), one.get("wts"));
            equal(
                    boundary[1] > 0
                            ? "47caf51ce3e14a6739084f1d26084b3c"
                            : "eb475f8cdf6084f178517a458c579c67",
                    one.get("w_rid"));
        }
        HashMap<String, String> reused = new HashMap<>();
        reused.put("w_rid", "old.signature");
        reused.put("wts", "1");
        reused.put("a", "x+y");
        wbi.generatorWbi(reused, key, key);
        equal("795696ffef7bee996e006d9ecc5487ba", reused.get("w_rid"));
    }

    private static ApiService api() {
        // This converter is a request-construction fixture only. These tests do not execute
        // responses.
        return new Retrofit.Builder()
                .baseUrl("https://api.bilibili.com/")
                .addConverterFactory(
                        new Converter.Factory() {
                            @Override
                            public Converter<ResponseBody, ?> responseBodyConverter(
                                    Type type, Annotation[] annotations, Retrofit retrofit) {
                                return body -> body;
                            }
                        })
                .validateEagerly(true)
                .build()
                .create(ApiService.class);
    }

    private static void requestContracts() throws Exception {
        ApiService service = api();
        int methods = 0;
        for (Method method : ApiService.class.getDeclaredMethods()) {
            Class<?>[] types = method.getParameterTypes();
            Object[] values = new Object[types.length];
            for (int i = 0; i < types.length; i++) {
                if (types[i] == int.class) values[i] = 1;
                else if (types[i] == long.class || types[i] == Long.class) values[i] = 2L;
                else if (types[i] == String.class) values[i] = "fixture_value";
                else if (types[i] == Map.class) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("aid", "123");
                    values[i] = map;
                } else if (types[i] == RequestBody.class)
                    values[i] = RequestBody.create("{}", MediaType.get("application/json"));
                else throw new AssertionError("Uncovered API argument " + types[i]);
                for (Annotation annotation : method.getParameterAnnotations()[i])
                    if (annotation instanceof Url) values[i] = "https://fixture.invalid/endpoint";
            }
            Request request = ((Call<?>) method.invoke(service, values)).request();
            equal(true, request.url().isHttps());
            methods++;
        }
        equal(69, methods);
        Request relation = service.checkUserRelation("42").request();
        equal("GET", relation.method());
        equal("https://api.bilibili.com/x/relation?fid=42", relation.url().toString());
        HashMap<String, String> signed = new HashMap<>();
        signed.put("keyword", "a b+中");
        signed.put("w_rid", "signature");
        Request search = service.searchByTypeWbi(signed).request();
        equal("/x/web-interface/wbi/search/type", search.url().encodedPath());
        equal("a b+中", search.url().queryParameter("keyword"));
        Request play = service.getVideoPlayInfoWbi(signed).request();
        equal("1", play.url().queryParameter("voice_balance"));
        equal("pre-load", play.url().queryParameter("gaia_source"));
        equal("true", play.url().queryParameter("isGaiaAvoided"));
        Request ticket = service.getBiliTicket("ec02", "hex", 123, "csrf").request();
        equal("POST", ticket.method());
        equal("123", ticket.url().queryParameter("context[ts]"));
        equal("csrf", ticket.url().queryParameter("csrf"));
        Request live = service.getLiveChatRoomUrlWithWbi(signed).request();
        equal("api.live.bilibili.com", live.url().host());
        RequestBody body = RequestBody.create("{\"id\":1}", MediaType.get("application/json"));
        Request collection = service.collection(body).request();
        equal("POST", collection.method());
        equal(body, collection.body());
        Buffer buffer = new Buffer();
        collection.body().writeTo(buffer);
        equal("{\"id\":1}", buffer.readUtf8());
        Request dynamic =
                service.getSignInQrCode(
                                "https://passport.bilibili.com/x/passport-login/web/qrcode/generate")
                        .request();
        equal("passport.bilibili.com", dynamic.url().host());
        System.out.println("API_CONTRACT_OK methods=" + methods);
    }

    private static void pgcV2Responses() {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        String[] bodies = {
            "{\"code\":0,\"result\":{\"video_info\":{\"quality\":80,\"dash\":{\"video\":[{\"id\":80,\"base_url\":\"https://example.test/video.m4s\"}]}}}}",
            "{\"code\":0,\"result\":{\"video_info\":{\"v_voucher\":\"fixture\"}}}",
            "{\"code\":0,\"result\":{\"v_voucher\":\"fixture\",\"video_info\":{\"dash\":{\"video\":[{\"base_url\":\"https://example.test/video.m4s\"}]}}}}",
            "{\"code\":0,\"result\":{\"video_info\":{\"code\":-10403,\"message\":\"not"
                + " entitled\"}}}",
            "{\"code\":0,\"data\":{\"v_voucher\":\"fixture\"}}",
            "{\"code\":0,\"result\":{\"play_check\":{}}}"
        };
        VideoPlayResponse[] outcomes = {
            VideoPlayResponse.PLAYABLE,
            VideoPlayResponse.VERIFICATION_REQUIRED,
            VideoPlayResponse.VERIFICATION_REQUIRED,
            VideoPlayResponse.FAILED,
            VideoPlayResponse.VERIFICATION_REQUIRED,
            VideoPlayResponse.MISSING_STREAMS
        };
        for (int n = 0; n < bodies.length; n++) {
            com.xx.blbl.network.response.Base2Response<com.xx.blbl.model.player.PlayInfoModel>
                    response =
                            PgcPlayResponse.decode(
                                    gson.fromJson(bodies[n], com.google.gson.JsonObject.class),
                                    gson);
            equal(outcomes[n], VideoPlayResponse.classifyPgc(response));
            if (n == 0) equal(80, response.getResult().getQuality());
            if (n == 3) {
                equal(-10403, response.getCode());
                equal("not entitled", response.getMessage());
            }
        }
        equal(null, PgcPlayResponse.decode(null, gson));
    }

    private static void playbackResponses() {
        com.xx.blbl.network.response.Base2Response<com.xx.blbl.model.player.PlayInfoModel> pgc =
                new com.xx.blbl.network.response.Base2Response<>();
        equal(VideoPlayResponse.MISSING_STREAMS, VideoPlayResponse.classifyPgc(pgc));
        pgc.setCode(-351);
        equal(VideoPlayResponse.FAILED, VideoPlayResponse.classifyPgc(pgc));
        com.xx.blbl.model.player.PlayInfoModel voucher =
                new com.google.gson.Gson()
                        .fromJson(
                                "{\"v_voucher\":\"fixture\"}",
                                com.xx.blbl.model.player.PlayInfoModel.class);
        pgc.setCode(0);
        pgc.setResult(voucher);
        equal(VideoPlayResponse.VERIFICATION_REQUIRED, VideoPlayResponse.classifyPgc(pgc));

        com.google.gson.Gson gson = new com.google.gson.Gson();
        java.lang.reflect.Type type =
                new com.google.gson.reflect.TypeToken<
                        com.xx.blbl.network.response.BaseResponse<
                                com.xx.blbl.model.player.PlayInfoModel>>() {}.getType();
        equal(
                VideoPlayResponse.VERIFICATION_REQUIRED,
                VideoPlayResponse.classify(
                        gson.fromJson(
                                "{\"code\":0,\"data\":{\"v_voucher\":\"fixture-verification\"}}",
                                type)));
        equal(
                VideoPlayResponse.VERIFICATION_REQUIRED,
                VideoPlayResponse.classify(
                        gson.fromJson(
                                "{\"code\":0,\"data\":{\"v_voucher\":\"fixture-verification\",\"durl\":[{\"url\":\"https://example.test/video.mp4\"}]}}",
                                type)));
        equal(
                VideoPlayResponse.PLAYABLE,
                VideoPlayResponse.classify(
                        gson.fromJson(
                                "{\"code\":0,\"data\":{\"dash\":{\"video\":[{\"base_url\":\"https://example.test/video.m4s\"}]}}}",
                                type)));
        equal(
                VideoPlayResponse.PLAYABLE,
                VideoPlayResponse.classify(
                        gson.fromJson(
                                "{\"code\":0,\"data\":{\"durl\":[{\"url\":\"https://example.test/video.mp4\"}]}}",
                                type)));
        equal(
                VideoPlayResponse.MISSING_STREAMS,
                VideoPlayResponse.classify(
                        gson.fromJson(
                                "{\"code\":0,\"data\":{\"dash\":{\"video\":[]},\"durl\":[{\"url\":\"\"}]}}",
                                type)));
        equal(
                VideoPlayResponse.MISSING_STREAMS,
                VideoPlayResponse.classify(gson.fromJson("{\"code\":0,\"data\":null}", type)));
        equal(
                VideoPlayResponse.FAILED,
                VideoPlayResponse.classify(gson.fromJson("{\"code\":-351}", type)));
    }

    public static void main(String[] args) throws Exception {
        wbi();
        requestContracts();
        playbackResponses();
        pgcV2Responses();
        System.out.println(
                "NETWORK_CONTRACT_OK assertions="
                        + assertions
                        + " network_requests=0 apk_inputs=0 header_jars=0");
    }
}
