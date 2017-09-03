package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.corycharlton.bittrexapi.internal.util.Ensure.*;

@SuppressWarnings("WeakerAccess")
public class BittrexApiClient {

    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s

    final Downloader downloader;
    final String key;
    final String secret;

    private BittrexApiClient(@NonNull Builder builder) {
        downloader = builder.downloader;
        key = builder.key;
        secret = builder.secret;
    }

    private Downloader.Request buildRequest(@NonNull String url) {
        return buildRequest(url, false);
    }

    private Downloader.Request buildRequest(@NonNull String url, boolean requiresAuthentication) {
        return buildRequest(url, null, requiresAuthentication);
    }

    private Downloader.Request buildRequest(@NonNull String url, List<NameValuePair> parameters) {
        return buildRequest(url, parameters, false);
    }

    private Downloader.Request buildRequest(@NonNull String url, List<NameValuePair> parameters, boolean requiresAuthenticaton) {
        boolean firstParameterAdded = url.contains("?");
        final StringBuilder urlStringBuilder = new StringBuilder(url);

        if (parameters != null) {
            for (NameValuePair parameter : parameters) {
                if (parameter != null && !StringUtils.isNullOrWhiteSpace(parameter.name())) {
                    urlStringBuilder.append(firstParameterAdded ? "&" : "?");
                    urlStringBuilder.append(parameter.name());

                    if (!StringUtils.isNullOrWhiteSpace(parameter.value())) {
                        urlStringBuilder.append("=");
                        urlStringBuilder.append(parameter.value());
                    }

                    firstParameterAdded = true;
                }
            }
        }
        
        return new Downloader.Request(Uri.parse(urlStringBuilder.toString()));
    }

    public GetCurrenciesResponse getCurrencies() throws IOException {
        final String response = downloader.execute(buildRequest("https://bittrex.com/api/v1.1/public/getcurrencies")).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetCurrenciesResponse.class);
    }

    public GetMarketsResponse getMarkets() throws IOException {
        final String response = downloader.execute(buildRequest("https://bittrex.com/api/v1.1/public/getmarkets")).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketsResponse.class);
    }

    public GetMarketHistoryResponse getMarketHistory(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final String response = downloader.execute(buildRequest("https://bittrex.com/api/v1.1/public/getmarkethistory", parameters)).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketHistoryResponse.class);
    }

    public GetMarketSummariesResponse getMarketSummaries() throws IOException {
        final String response = downloader.execute(buildRequest("https://bittrex.com/api/v1.1/public/getmarketsummaries")).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketSummariesResponse.class);
    }

    public GetMarketSummaryResponse getMarketSummary(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final String response = downloader.execute(buildRequest("https://bittrex.com/api/v1.1/public/getmarketsummary", parameters)).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketSummaryResponse.class);
    }

    // TODO: Expose the 'type' parameter?
    public GetOrderBookResponse getOrderBook(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));
        parameters.add(new NameValuePair("type", "both"));

        final String response = downloader.execute(buildRequest("https://bittrex.com/api/v1.1/public/getorderbook", parameters)).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetOrderBookResponse.class);
    }

    public GetTickerResponse getTicker(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getticker", parameters);

        final String response = downloader.execute(request).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetTickerResponse.class);
    }

    public static final class Builder {

        private Downloader downloader;
        private final String key;
        private final String secret;

        public Builder(@NonNull String key, @NonNull String secret) {
            isNotNullOrWhitespace("key", key);
            isNotNullOrWhitespace("secret", secret);

            this.key = key;
            this.secret = secret;
        }

        public BittrexApiClient build() {
            if (downloader == null) {
                this.downloader = new UrlConnectionDownloader();
            }

            return new BittrexApiClient(this);
        }

        public Builder downloader(@NonNull Downloader downloader) {
            isNotNull("downloader", downloader, "Downloader must not be null.");
            isValidState("downloader", this.downloader == null, "Downloader already set.");

            this.downloader = downloader;

            return this;
        }
    }
}
