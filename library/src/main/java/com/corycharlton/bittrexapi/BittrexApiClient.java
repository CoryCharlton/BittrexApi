package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.corycharlton.bittrexapi.internal.gson.GsonFactory;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
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

    private Uri buildUri(@NonNull String url, List<Object> parameters, boolean requiresAuthenticaton) {
        return Uri.parse("blag");
    }

    public GetCurrenciesResponse getCurrencies() throws IOException {
        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getcurrencies"), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetCurrenciesResponse.class);
    }

    public GetMarketsResponse getMarkets() throws IOException {
        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getmarkets"), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetMarketsResponse.class);
    }

    public GetMarketHistoryResponse getMarketHistory(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getmarkethistory?market=" + market.trim()), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetMarketHistoryResponse.class);
    }

    public GetMarketSummariesResponse getMarketSummaries() throws IOException {
        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getmarketsummaries"), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetMarketSummariesResponse.class);
    }

    public GetMarketSummaryResponse getMarketSummary(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getmarketsummary?market=" + market.trim()), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetMarketSummaryResponse.class);
    }

    // TODO: Expose the 'type' parameter?
    public GetOrderBookResponse getOrderBook(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getorderbook?market=" + market.trim() + "&type=both"), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetOrderBookResponse.class);
    }

    public GetTickerResponse getTicker(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final String response = downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getticker?market=" + market.trim()), null).getResponseString();
        Log.w(BittrexApiLibraryInfo.TAG, response);

        return GsonFactory.getGson().fromJson(response, GetTickerResponse.class);
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
