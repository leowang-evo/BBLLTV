package com.xx.blbl.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.xx.blbl.model.player.PlayInfoModel;
import com.xx.blbl.network.response.Base2Response;

/** Normalize the current PGC envelope at the network boundary. */
public final class PgcPlayResponse {
    private PgcPlayResponse() {}

    public static Base2Response<PlayInfoModel> decode(JsonObject body, Gson gson) {
        if (body == null) return null;
        Base2Response<PlayInfoModel> response =
                gson.fromJson(body, new TypeToken<Base2Response<PlayInfoModel>>() {}.getType());
        JsonObject result = object(body, "result");
        JsonObject info = object(result, "video_info");
        // A challenge can replace the normal envelope; never discard it for a stream.
        PlayInfoModel challenge = gson.fromJson(result, PlayInfoModel.class);
        if (challenge == null || !challenge.requiresVerification()) {
            challenge = gson.fromJson(object(body, "data"), PlayInfoModel.class);
        }
        if (challenge != null && challenge.requiresVerification()) response.setResult(challenge);
        else if (info != null) {
            response.setResult(gson.fromJson(info, PlayInfoModel.class));
            if (info.has("code")
                    && !info.get("code").isJsonNull()
                    && info.get("code").getAsInt() != 0) {
                response.setCode(info.get("code").getAsInt());
                if (info.has("message") && !info.get("message").isJsonNull())
                    response.setMessage(info.get("message").getAsString());
            }
        }
        return response;
    }

    private static JsonObject object(JsonObject parent, String key) {
        return parent != null && parent.has(key) && parent.get(key).isJsonObject()
                ? parent.getAsJsonObject(key)
                : null;
    }
}
