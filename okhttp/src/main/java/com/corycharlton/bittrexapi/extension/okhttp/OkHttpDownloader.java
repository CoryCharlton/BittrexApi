package com.corycharlton.bittrexapi.extension.okhttp;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.Downloader;
import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.util.Ensure;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * A {@link Downloader} implementation that uses a {@link OkHttpClient} to execute requests
 */
public class OkHttpDownloader implements Downloader {
    @Override
    public Response execute(@NonNull Request request) throws IOException {
        Ensure.isNotNull("request", request);

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(BittrexApiClient.DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BittrexApiClient.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BittrexApiClient.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .build();

        final okhttp3.Request.Builder okhttpRequestBuilder = new okhttp3.Request.Builder();

        okhttpRequestBuilder.cacheControl(CacheControl.FORCE_NETWORK);
        okhttpRequestBuilder.get();
        okhttpRequestBuilder.url(request.url());

        for (NameValuePair header : request.headers()) {
            okhttpRequestBuilder.addHeader(header.name(), header.value());
        }

        final okhttp3.Response response = client.newCall(okhttpRequestBuilder.build()).execute();

        int responseCode = response.code();
        final ResponseBody responseBody = response.body();
        if (responseCode < 200 || responseCode >= 300) {
            if (responseBody != null) {
                responseBody.close();
            }

            throw new ResponseException(responseCode + ": " + response.message(), responseCode);
        }

        if (responseBody == null) {
            throw new ResponseException(responseCode + ": ResponseBody was null" , responseCode);
        }

        return new Response(responseBody.string(), responseCode);
    }
}
