package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.corycharlton.bittrexapi.internal.util.Ensure.*;

@SuppressWarnings("unused, WeakerAccess")
public class BittrexApiClient {

    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s

    private final Downloader _downloader;
    private final String _key;
    private final String _secret;

    private BittrexApiClient(@NonNull Builder builder) {
        _downloader = builder.downloader;
        _key = builder.key;
        _secret = builder.secret;
    }

    private Downloader.Request buildRequest(@NonNull String url) {
        return buildRequest(url, false);
    }

    private Downloader.Request buildRequest(@NonNull String url, boolean requiresAuthentication) {
        return buildRequest(url, null, requiresAuthentication);
    }

    private Downloader.Request buildRequest(@NonNull String url, ArrayList<NameValuePair> parameters) {
        return buildRequest(url, parameters, false);
    }

    private Downloader.Request buildRequest(@NonNull String url, ArrayList<NameValuePair> parameters, boolean requiresAuthenticaton) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }

        boolean firstParameterAdded = url.contains("?");
        final StringBuilder urlStringBuilder = new StringBuilder(url);

        final long nonce = System.currentTimeMillis();
        parameters.add(new NameValuePair("_", Long.toString(nonce)));
        parameters.add(new NameValuePair("nonce", Long.toString(nonce)));

        if (requiresAuthenticaton) {
            parameters.add(new NameValuePair("apikey", _key));
        }

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

        final ArrayList<NameValuePair> headers = new ArrayList<>();
        final Uri uri = Uri.parse(urlStringBuilder.toString());

        if (requiresAuthenticaton) {
            headers.add(new NameValuePair("apisign", signUrl(uri.toString())));
        }

        return new Downloader.Request(uri, headers);
    }

    public GetBalancesResponse getBalances() throws IOException {
        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/account/getbalances", true);
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetBalancesResponse.class);
    }

    public GetCurrenciesResponse getCurrencies() throws IOException {
        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getcurrencies");
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetCurrenciesResponse.class);
    }

    public GetMarketsResponse getMarkets() throws IOException {
        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getmarkets");
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketsResponse.class);
    }

    public GetMarketHistoryResponse getMarketHistory(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getmarkethistory", parameters);
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketHistoryResponse.class);
    }

    public GetMarketSummariesResponse getMarketSummaries() throws IOException {
        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getmarketsummaries");
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketSummariesResponse.class);
    }

    public GetMarketSummaryResponse getMarketSummary(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getmarketsummary", parameters);
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetMarketSummaryResponse.class);
    }

    // TODO: Expose the 'type' parameter?
    public GetOrderBookResponse getOrderBook(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));
        parameters.add(new NameValuePair("type", "both"));

        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getorderbook", parameters);
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetOrderBookResponse.class);
    }

    public GetTickerResponse getTicker(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest("https://bittrex.com/api/v1.1/public/getticker", parameters);
        final String response = _downloader.execute(request).getResponseString();

        Log.w(BittrexApiLibraryInfo.TAG, response);

        return Gson.fromJson(response, GetTickerResponse.class);
    }

    public String signUrl(@NonNull String url) {
        Ensure.isNotNullOrWhitespace("url", url);
        String digest = null;

        try {
            final SecretKeySpec key = new SecretKeySpec((_secret).getBytes("UTF-8"), "HmacSHA512");
            final Mac mac = Mac.getInstance("HmacSHA512");

            mac.init(key);

            byte[] bytes = mac.doFinal(url.getBytes("UTF-8"));
            final StringBuilder hash = new StringBuilder();

            for (byte aByte : bytes) {
                String hex = Integer.toHexString(0xFF & aByte);

                if (hex.length() == 1) {
                    hash.append('0');
                }

                hash.append(hex);
            }

            digest = hash.toString();
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            // I should throw...
        }

        return digest;
    }

    public static final class Builder {

        private Downloader downloader;
        private final String key;
        private final String secret;

        public Builder(@NonNull String key, @NonNull String secret) {
            isNotNullOrWhitespace("_key", key);
            isNotNullOrWhitespace("_secret", secret);

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
            isNotNull("_downloader", downloader, "Downloader must not be null.");
            isValidState("_downloader", this.downloader == null, "Downloader already set.");

            this.downloader = downloader;

            return this;
        }
    }
}
