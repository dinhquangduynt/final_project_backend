package com.dinhquangduy.electronic.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DataUtil {
    
    public static JsonObject getJsonObject(String strJson) throws Exception {
        JsonObject json = null;
        try {
            if (null != strJson && 0 != strJson.length()) {
                json = new Gson().fromJson(strJson, JsonObject.class);
            }
            if (json != null && isJsonObjectHasKey(json, "data")) {
                json = json.getAsJsonObject("data");
            }
        } catch (Exception ex) {
            json = null;
        }

        if (null == json) {
            // The JSON format is incorrect.
            throw new Exception();
        }
        return json;
    }
    
    public static boolean isJsonObjectHasKey(JsonObject json, String key) {
        return json.has(key);
    }
}
