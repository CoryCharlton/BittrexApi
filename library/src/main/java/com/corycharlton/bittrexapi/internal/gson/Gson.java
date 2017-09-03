package com.corycharlton.bittrexapi.internal.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.Date;

public final class Gson {

    static com.google.gson.Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DefaultDateTypeAdapter(Date.class))
                .create();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return getGson().fromJson(json, classOfT);
    }

    public static String toJson(Object src) {
        return getGson().toJson(src);
    }

    private Gson() {} // Cannot instantiate
}
