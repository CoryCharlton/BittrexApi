package com.corycharlton.bittrexapi.internal.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public final class GsonFactory {

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DefaultDateTypeAdapter(Date.class))
                .create();
    }

    private GsonFactory() {} // Cannot instantiate
}
