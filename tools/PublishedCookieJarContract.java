import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import com.franmontiel.persistentcookiejar.persistence.SerializableCookie;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

import java.util.*;

/** Host-side external-library contract fixture; no application storage/network. */
public final class PublishedCookieJarContract {
    static final class MemoryPersistor implements CookiePersistor {
        final List<Cookie> cookies = new ArrayList<Cookie>();

        public List<Cookie> loadAll() {
            return new ArrayList<Cookie>(cookies);
        }

        public void saveAll(Collection<Cookie> values) {
            cookies.addAll(values);
        }

        public void removeAll(Collection<Cookie> values) {
            cookies.removeAll(values);
        }

        public void clear() {
            cookies.clear();
        }
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        HttpUrl url = HttpUrl.get("https://example.invalid/tv/video");
        MemoryPersistor store = new MemoryPersistor();
        PersistentCookieJar jar = new PersistentCookieJar(new SetCookieCache(), store);
        Cookie persistent =
                new Cookie.Builder()
                        .name("persistent")
                        .value("fixture")
                        .hostOnlyDomain("example.invalid")
                        .path("/tv")
                        .secure()
                        .httpOnly()
                        .expiresAt(System.currentTimeMillis() + 60000)
                        .build();
        Cookie session =
                new Cookie.Builder()
                        .name("session")
                        .value("fixture")
                        .hostOnlyDomain("example.invalid")
                        .path("/tv")
                        .build();
        jar.saveFromResponse(url, Arrays.asList(persistent, session));
        check(
                store.cookies.size() == 1 && jar.loadForRequest(url).size() == 2,
                "session/persistent partition");
        check(
                jar.loadForRequest(HttpUrl.get("https://different.invalid/tv/video")).isEmpty(),
                "host matching");
        Cookie expired =
                new Cookie.Builder()
                        .name("expired")
                        .value("fixture")
                        .hostOnlyDomain("example.invalid")
                        .expiresAt(1)
                        .build();
        jar.saveFromResponse(url, Collections.singletonList(expired));
        check(
                jar.loadForRequest(url).size() == 2 && store.cookies.size() == 1,
                "expiration removal");
        jar.clearSession();
        check(jar.loadForRequest(url).size() == 1, "clearSession preserves persisted cookies");
        SerializableCookie codec = new SerializableCookie();
        check(
                persistent.equals(codec.decode(codec.encode(persistent))),
                "Cookie4.12 serialization compatibility");
        jar.clear();
        check(
                jar.loadForRequest(url).isEmpty() && store.cookies.isEmpty(),
                "clear empties both stores");
        System.out.println(
                "PUBLISHED_COOKIEJAR_OK assertions=6 sharedprefs_factory_signature_compiled=true");
    }
}

/** Compile-only usage proof of the official Android persistence API. */
final class PublishedCookieJarAndroidFactory {
    static PersistentCookieJar create(android.content.Context context) {
        return new PersistentCookieJar(
                new SetCookieCache(), new SharedPrefsCookiePersistor(context));
    }
}
