package com.xx.blbl.network;

import com.xx.blbl.model.video.VideoModel;
import com.xx.blbl.network.response.Base2Response;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.ListDataModel;

import okhttp3.*;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/** Local HTTP and real-library boundary checks; never connects to an external host. */
public final class NetworkManagerIntegrationTest {
    static int assertions;

    static void equal(Object expected, Object actual) {
        assertions++;
        if (!Objects.equals(expected, actual))
            throw new AssertionError("Expected " + expected + "; got " + actual);
    }

    static final class Environment implements NetworkEnvironment {
        final String baseUrl;
        final List<Cookie> cookies;

        Environment(String url) {
            this(url, Collections.emptyList());
        }

        Environment(String url, List<Cookie> cookies) {
            baseUrl = url;
            this.cookies = cookies;
        }

        public CookieStore cookieStore() {
            return () -> cookies;
        }

        public CookieJar cookieJar() {
            if (cookies.isEmpty()) return CookieJar.NO_COOKIES;
            return new CookieJar() {
                public void saveFromResponse(HttpUrl url, List<Cookie> responseCookies) {}

                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> matches = new java.util.ArrayList<>();
                    for (Cookie cookie : cookies) if (cookie.matches(url)) matches.add(cookie);
                    return matches;
                }
            };
        }

        public String userAgent() {
            return "BBLL-source-integration/1";
        }

        public String refreshUserAgent() {
            return "BBLL-source-integration/2";
        }

        public void reloadCookies() {}

        public String baseUrl() {
            return baseUrl;
        }

        public Executor callbackExecutor() {
            return Runnable::run;
        }
    }

    static final class Server implements AutoCloseable {
        final ServerSocket socket;
        final Thread thread;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        volatile String request;

        Server(byte[] payload, String encoding) throws Exception {
            socket = new ServerSocket(0, 1, InetAddress.getByName("127.0.0.1"));
            thread =
                    new Thread(
                            () -> {
                                try (Socket client = socket.accept()) {
                                    client.setSoTimeout(5000);
                                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                                    InputStream input = client.getInputStream();
                                    int value;
                                    while ((value = input.read()) != -1) {
                                        bytes.write(value);
                                        byte[] data = bytes.toByteArray();
                                        int n = data.length;
                                        if (n >= 4
                                                && data[n - 4] == 13
                                                && data[n - 3] == 10
                                                && data[n - 2] == 13
                                                && data[n - 1] == 10) break;
                                    }
                                    request =
                                            new String(bytes.toByteArray(), StandardCharsets.UTF_8);
                                    java.util.regex.Matcher length =
                                            java.util.regex.Pattern.compile(
                                                            "(?im)^Content-Length: (\\d+)")
                                                    .matcher(request);
                                    int bodyLength =
                                            length.find() ? Integer.parseInt(length.group(1)) : 0;
                                    for (int i = 0; i < bodyLength; i++) {
                                        int next = input.read();
                                        if (next < 0) throw new IOException("Truncated body");
                                        bytes.write(next);
                                    }
                                    request =
                                            new String(bytes.toByteArray(), StandardCharsets.UTF_8);
                                    OutputStream output = client.getOutputStream();
                                    String headers =
                                            "HTTP/1.1 200 OK\r\n"
                                                    + "Content-Type: application/json;"
                                                    + " charset=utf-8\r\n"
                                                    + (encoding == null
                                                            ? ""
                                                            : "Aardvark: first\r\n"
                                                                    + "Content-Encoding: "
                                                                    + encoding
                                                                    + "\r\n")
                                                    + "Content-Length: "
                                                    + payload.length
                                                    + "\r\nConnection: close\r\n\r\n";
                                    output.write(headers.getBytes(StandardCharsets.US_ASCII));
                                    output.write(payload);
                                    output.flush();
                                } catch (Throwable failure) {
                                    error.set(failure);
                                }
                            },
                            "bbll-loopback-http");
            thread.setDaemon(true);
            thread.start();
        }

        String url() {
            return "http://127.0.0.1:" + socket.getLocalPort() + "/";
        }

        public void close() throws Exception {
            socket.close();
            thread.join(6000);
            if (error.get() != null)
                throw new AssertionError("Loopback fixture failed", error.get());
        }
    }

    static byte[] deflate(byte[] value) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(6, true);
        try (DeflaterOutputStream stream = new DeflaterOutputStream(output, compressor)) {
            stream.write(value);
        } finally {
            compressor.end();
        }
        return output.toByteArray();
    }

    static void http(boolean compressed) throws Exception {
        String json =
                "{\"code\":0,\"message\":\"ok\",\"data\":{\"list\":[{\"aid\":123,\"bvid\":\"BV-source\",\"title\":\"恢复验证·独立网络\",\"owner\":{\"mid\":42,\"name\":\"测试\"}}]}}";
        byte[] raw = json.getBytes(StandardCharsets.UTF_8);
        try (Server server =
                new Server(compressed ? deflate(raw) : raw, compressed ? "deflate" : null)) {
            NetworkManager manager = new NetworkManager(new Environment(server.url()));
            manager.refreshUA();
            CountDownLatch ready = new CountDownLatch(1);
            AtomicReference<BaseResponse<ListDataModel<VideoModel>>> result =
                    new AtomicReference<>();
            AtomicReference<Throwable> failure = new AtomicReference<>();
            manager.getHotVideos(
                    2,
                    7,
                    new NetResultCallback<BaseResponse<ListDataModel<VideoModel>>>() {
                        public void onFailure(Throwable error) {
                            failure.set(error);
                            ready.countDown();
                        }

                        public void onResponse(BaseResponse<ListDataModel<VideoModel>> response) {
                            result.set(response);
                            ready.countDown();
                        }
                    });
            if (!ready.await(8, TimeUnit.SECONDS))
                throw new AssertionError("Network callback timed out");
            if (failure.get() != null)
                throw new AssertionError("Network callback failed", failure.get());
            equal(0, result.get().getCode());
            VideoModel video = result.get().getData().getList().get(0);
            equal(123L, video.getAid());
            equal("BV-source", video.getBvid());
            equal("恢复验证·独立网络", video.getTitle());
            equal("测试", video.getOwner().getName());
            equal(
                    true,
                    server.request.startsWith("GET /x/web-interface/popular?pn=2&ps=7 HTTP/1.1"));
            equal(true, server.request.contains("Origin: https://www.bilibili.com\r\n"));
            equal(true, server.request.contains("Referer: https://www.bilibili.com\r\n"));
            equal(true, server.request.contains("User-Agent: BBLL-source-integration/2\r\n"));
        }
    }

    static void rankingRequest() throws Exception {
        try (Server server =
                new Server(
                        "{\"code\":0,\"data\":{\"list\":[]}}".getBytes(StandardCharsets.UTF_8),
                        null)) {
            NetworkManager manager = new NetworkManager(new Environment(server.url()));
            com.xx.blbl.model.WbiImage keys = new com.xx.blbl.model.WbiImage();
            keys.setImg_url("https://i0.hdslb.com/bfs/wbi/7cd084941338484aae1ad9425b84077c.png");
            keys.setSub_url("https://i0.hdslb.com/bfs/wbi/4932caff0ff746eab6f01bf08b70ac45.png");
            manager.setWbiInfo(keys);
            java.util.concurrent.CompletableFuture<BaseResponse<ListDataModel<VideoModel>>> done =
                    new java.util.concurrent.CompletableFuture<>();
            manager.getRanking(1010, complete(done));
            equal(0, done.get(8, TimeUnit.SECONDS).getCode());
            String first = server.request.substring(0, server.request.indexOf("\r\n"));
            equal(true, first.contains("rid=1010"));
            equal(true, first.contains("type=all"));
            equal(true, first.contains("web_location=333.934"));
            equal(true, first.matches(".*[?&]w_rid=[0-9a-f]{32}.*"));
            equal(true, first.contains("wts="));
            equal(
                    true,
                    server.request.contains(
                            "Referer: https://www.bilibili.com/v/popular/rank/all\r\n"));
        }
    }

    static void formAndCookieBoundary() throws Exception {
        equal("%E4%BD%A0%E5%A5%BD%20%2B%25", NetworkSupport.formEncode("你好 +%", "", 0, 0, 91));
        equal(Arrays.asList("1", "", "3", ""), NetworkSupport.split("1,,3,", new String[] {","}));
        NetworkManager manager = new NetworkManager(new Environment("https://example.invalid/"));
        AtomicReference<String> error = new AtomicReference<>();
        manager.cancelFollowSeries(
                1,
                new NetResultCallback<
                        Base2Response<com.xx.blbl.model.series.FollowSeriesResult>>() {
                    public void onFailure(Throwable failure) {
                        error.set(failure.getMessage());
                    }

                    public void onResponse(
                            Base2Response<com.xx.blbl.model.series.FollowSeriesResult> value) {
                        throw new AssertionError("No-cookie request should stop before networking");
                    }
                });
        equal("No cookies", error.get());
    }

    static void xmlDanmaku() throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><i>"
                        + "<d p=\"1.25,1,25,16777215,0,0,user,1,0\">恢复验证 &amp; 弹幕</d>"
                        + "<d p=\"2.5,4,18,255,0,0,user,2,0\">bottom</d>"
                        + "<d p=\"3.0,5,30,65280,0,0,user,3,0\">top</d></i>";
        try (Server server = new Server(xml.getBytes(StandardCharsets.UTF_8), null)) {
            NetworkManager manager = new NetworkManager(new Environment(server.url()));
            CountDownLatch ready = new CountDownLatch(1);
            AtomicReference<List<DanmakuItem>> result = new AtomicReference<>();
            AtomicReference<Throwable> error = new AtomicReference<>();
            manager.getDm(
                    123,
                    new NetResultCallback<List<DanmakuItem>>() {
                        public void onFailure(Throwable failure) {
                            error.set(failure);
                            ready.countDown();
                        }

                        public void onResponse(List<DanmakuItem> items) {
                            result.set(items);
                            ready.countDown();
                        }
                    });
            if (!ready.await(8, TimeUnit.SECONDS))
                throw new AssertionError("XML callback timed out");
            if (error.get() != null) throw new AssertionError("XML callback failed", error.get());
            equal(3, result.get().size());
            equal(1250L, result.get().get(0).getPositionMillis());
            equal("恢复验证 & 弹幕", result.get().get(0).getContent());
            equal(25, result.get().get(0).getTextSize());
            equal(16777215, result.get().get(0).getColor());
            equal(1, result.get().get(0).getMode());
            equal(4, result.get().get(1).getMode());
            equal(5, result.get().get(2).getMode());
            equal(true, server.request.startsWith("GET /x/v1/dm/list.so?oid=123 HTTP/1.1"));
        }
    }

    private static <T> NetResultCallback<T> complete(
            java.util.concurrent.CompletableFuture<T> result) {
        return new NetResultCallback<T>() {
            public void onFailure(Throwable error) {
                result.completeExceptionally(error);
            }

            public void onResponse(T response) {
                result.complete(response);
            }
        };
    }

    static void playbackUsesCookieHeader() throws Exception {
        String fixtureSession = "fake-session-cookie-for-loopback";
        Cookie cookie =
                new Cookie.Builder()
                        .name("SESSDATA")
                        .value(fixtureSession)
                        .hostOnlyDomain("127.0.0.1")
                        .path("/")
                        .build();
        byte[] reply = "{\"code\":0,\"data\":{},\"result\":{}}".getBytes(StandardCharsets.UTF_8);
        String[] paths = {
            "/x/player/wbi/playurl", "/x/player/playurl", "/pgc/player/web/v2/playurl"
        };
        for (int index = 0; index < paths.length; index++) {
            try (Server server = new Server(reply, null)) {
                NetworkManager manager =
                        new NetworkManager(
                                new Environment(server.url(), Collections.singletonList(cookie)));
                if (index == 2) {
                    com.xx.blbl.model.WbiImage keys = new com.xx.blbl.model.WbiImage();
                    keys.setImg_url(
                            "https://i0.hdslb.com/bfs/wbi/7cd084941338484aae1ad9425b84077c.png");
                    keys.setSub_url(
                            "https://i0.hdslb.com/bfs/wbi/4932caff0ff746eab6f01bf08b70ac45.png");
                    manager.setWbiInfo(keys);
                    java.util.concurrent.CompletableFuture<
                                    Base2Response<com.xx.blbl.model.player.PlayInfoModel>>
                            result = new java.util.concurrent.CompletableFuture<>();
                    manager.getVideoPlayPgcInfo(
                            123L, "BV-fixture", 456L, 789L, 80, 16, 0, complete(result));
                    equal(0, result.get(8, TimeUnit.SECONDS).getCode());
                } else {
                    java.util.concurrent.CompletableFuture<
                                    BaseResponse<com.xx.blbl.model.player.PlayInfoModel>>
                            result = new java.util.concurrent.CompletableFuture<>();
                    if (index == 0)
                        manager.getVideoPlayInfoWbi(
                                123L, "BV-fixture", 789L, 80, 16, 0, complete(result));
                    else
                        manager.getVideoPlayInfo(
                                123L, "BV-fixture", 789L, 80, 16, 0, complete(result));
                    equal(0, result.get(8, TimeUnit.SECONDS).getCode());
                }
                String requestLine = server.request.split("\r\n", 2)[0];
                if (index == 2) {
                    equal(true, requestLine.matches(".*[?&]w_rid=[0-9a-f]{32}.*"));
                    equal(
                            true,
                            server.request.contains(
                                    "Referer: https://www.bilibili.com/bangumi/play/ep456\r\n"));
                }
                equal(true, requestLine.startsWith("GET " + paths[index] + "?"));
                equal(false, requestLine.contains(fixtureSession));
                equal(true, server.request.contains("Cookie: SESSDATA=" + fixtureSession + "\r\n"));
            }
        }
    }

    static void accountActions() throws Exception {
        Cookie csrf =
                new Cookie.Builder()
                        .name("bili_jct")
                        .value("fixture-csrf")
                        .hostOnlyDomain("127.0.0.1")
                        .path("/")
                        .build();
        Cookie session =
                new Cookie.Builder()
                        .name("SESSDATA")
                        .value("fixture-session")
                        .hostOnlyDomain("127.0.0.1")
                        .path("/")
                        .build();
        Object[][] cases = {
            {
                "like",
                new Object[] {123L, "BV-fixture", 1},
                "/x/web-interface/archive/like",
                "like=1"
            },
            {
                "like",
                new Object[] {123L, "BV-fixture", 2},
                "/x/web-interface/archive/like",
                "like=2"
            },
            {
                "collection",
                new Object[] {123L, true, "42,43"},
                "/x/v3/fav/resource/deal",
                "add_media_ids=42%2C43"
            },
            {
                "collection",
                new Object[] {123L, false, "42"},
                "/x/v3/fav/resource/deal",
                "del_media_ids=42"
            },
            {"userRelationModify", new Object[] {"42", 1}, "/x/relation/modify", "act=1"},
            {"userRelationModify", new Object[] {"42", 2}, "/x/relation/modify", "act=2"},
            {"followSeries", new Object[] {1733L}, "/pgc/web/follow/add", "season_id=1733"},
            {"cancelFollowSeries", new Object[] {1733L}, "/pgc/web/follow/del", "season_id=1733"},
            {
                "giveCoin",
                new Object[] {123L, "BV-fixture", 1},
                "/x/web-interface/coin/add",
                "multiply=1"
            },
            {
                "tripleAction",
                new Object[] {123L, "BV-fixture"},
                "/x/web-interface/archive/like/triple",
                "csrf=fixture-csrf"
            }
        };
        for (Object[] fixture : cases) {
            String payload =
                    fixture[0].equals("like")
                            ? "{\"code\":0,\"data\":0}"
                            : "{\"code\":0,\"data\":{},\"result\":{}}";
            try (Server server = new Server(payload.getBytes(StandardCharsets.UTF_8), null)) {
                NetworkManager manager =
                        new NetworkManager(
                                new Environment(server.url(), Arrays.asList(csrf, session)));
                java.util.concurrent.CompletableFuture<Object> done =
                        new java.util.concurrent.CompletableFuture<>();
                Object[] arguments = (Object[]) fixture[1];
                Object[] values = Arrays.copyOf(arguments, arguments.length + 1);
                values[arguments.length] = complete(done);
                for (java.lang.reflect.Method method : NetworkManager.class.getMethods())
                    if (method.getName().equals(fixture[0])
                            && method.getParameterCount() == values.length) {
                        method.invoke(manager, values);
                        break;
                    }
                Object response = done.get(8, TimeUnit.SECONDS);
                equal(0, response.getClass().getMethod("getCode").invoke(response));
                equal(true, server.request.startsWith("POST " + fixture[2]));
                equal(true, server.request.contains((String) fixture[3]));
                equal(true, server.request.contains("csrf=fixture-csrf"));
                equal(true, server.request.contains("SESSDATA=fixture-session"));
            }
        }
        try (Server server =
                new Server(
                        "{\"code\":0,\"data\":{\"favoured\":false}}"
                                .getBytes(StandardCharsets.UTF_8),
                        null)) {
            NetworkManager manager = new NetworkManager(new Environment(server.url()));
            java.util.concurrent.CompletableFuture<Object> done =
                    new java.util.concurrent.CompletableFuture<>();
            manager.hasCollection(123L, "BV-fixture", (NetResultCallback) complete(done));
            done.get(8, TimeUnit.SECONDS);
            equal(true, server.request.startsWith("GET /x/v2/fav/video/favoured?aid=123 "));
        }
    }

    static void sharedResponseBoundary() {
        List<Object> responses = new ArrayList<>();
        List<Throwable> failures = new ArrayList<>();
        ResponseCallback<String> callback =
                new ResponseCallback<>(
                        new NetResultCallback<String>() {
                            public void onResponse(String body) {
                                responses.add(body);
                            }

                            public void onFailure(Throwable failure) {
                                failures.add(failure);
                            }
                        });
        callback.onResponse(null, retrofit2.Response.success("body"));
        callback.onResponse(null, retrofit2.Response.success(null));
        callback.onResponse(
                null,
                retrofit2.Response.error(
                        403, ResponseBody.create(MediaType.get("text/plain"), "denied")));
        IOException error = new IOException("fixture disconnect");
        callback.onFailure(null, error);
        equal(Arrays.asList("body", null, null), responses);
        equal(1, failures.size());
        equal(error, failures.get(0));
    }

    static void liveAreaRequest() throws Exception {
        String json = "{\"code\":0,\"data\":{\"count\":42,\"list\":[{\"roomid\":99,\"title\":\"area room\",\"uname\":\"anchor\"}]}}";
        try (Server server = new Server(json.getBytes(StandardCharsets.UTF_8), null)) {
            NetworkManager manager = new NetworkManager(new Environment(server.url()));
            // Rewrite only the dynamic @Url at the service seam; preserve the real
            // manager parameters, Retrofit converter and OkHttp header interceptor.
            java.lang.reflect.Field serviceField = NetworkManager.class.getDeclaredField("netService");
            serviceField.setAccessible(true);
            ApiService delegate = (ApiService) serviceField.get(manager);
            String[] requestedUrl = {null};
            ApiService local = (ApiService) java.lang.reflect.Proxy.newProxyInstance(
                    ApiService.class.getClassLoader(), new Class<?>[]{ApiService.class}, (proxy, method, args) -> {
                        if (method.getName().equals("getLiveCategoryDetailList")) {
                            requestedUrl[0] = (String) args[0];
                            args[0] = server.url() + requestedUrl[0].substring("https://api.live.bilibili.com/".length());
                        }
                        return method.invoke(delegate, args);
                    });
            serviceField.set(manager, local);
            CompletableFuture<Object> done = new CompletableFuture<>();
            manager.getLiveCategoryDetailList(86L, 2L, 2, (NetResultCallback) complete(done));
            BaseResponse<com.xx.blbl.network.response.LiveCategoryDetailListWrapper> response =
                    (BaseResponse<com.xx.blbl.network.response.LiveCategoryDetailListWrapper>) done.get(8, TimeUnit.SECONDS);
            equal("https://api.live.bilibili.com/room/v3/area/getRoomList?page_size=20&sort_type=online", requestedUrl[0]);
            equal(0, response.getCode());
            equal(42, response.getData().getCount());
            equal(99L, response.getData().getList().get(0).getRoomid());
            String line = server.request.split("\r\n")[0];
            equal(true, line.startsWith("GET /room/v3/area/getRoomList?"));
            for (String parameter : new String[]{"page_size=20", "sort_type=online", "parent_area_id=2", "area_id=86", "page=2"})
                equal(true, line.contains(parameter));
            equal(true, server.request.contains("Origin: https://live.bilibili.com\r\n"));
            equal(true, server.request.contains("Referer: https://live.bilibili.com/\r\n"));
        }
    }

    static void businessResponseBoundary() {
        equal(Integer.MIN_VALUE, ApiFailure.fromResponse(null).code);
        BaseResponse<Object> response = new BaseResponse<>();
        equal(null, ApiFailure.fromResponse(response));
        response.setCode(-352);
        response.setMessage("request limited");
        equal(-352, ApiFailure.fromResponse(response).code);
        equal("request limited", ApiFailure.fromResponse(response).getMessage());
        Base2Response<Object> alternate = new Base2Response<>();
        alternate.setCode(-101);
        equal(-101, ApiFailure.fromResponse(alternate).code);
        com.xx.blbl.network.response.BaseBaseResponse action =
                new com.xx.blbl.network.response.BaseBaseResponse();
        action.setCode(-403);
        equal(-403, ApiFailure.fromResponse(action).code);
        response.setCode(0);
        equal(null, ApiFailure.fromResponse(response));
    }

    public static void main(String[] args) throws Exception {
        try {
            accountActions();
            liveAreaRequest();
            businessResponseBoundary();
            sharedResponseBoundary();
            rankingRequest();
            http(false);
            http(true);
            formAndCookieBoundary();
            xmlDanmaku();
            playbackUsesCookieHeader();
            System.out.println(
                    "NETWORK_MANAGER_INTEGRATION_OK assertions="
                            + assertions
                            + " external_requests=0 loopback_requests=19 apk_inputs=0"
                            + " header_jars=0");
            // OkHttp's shared dispatcher threads are non-daemon on the host JVM.
            System.exit(0);
        } catch (Throwable failure) {
            failure.printStackTrace();
            System.exit(1);
        }
    }
}
