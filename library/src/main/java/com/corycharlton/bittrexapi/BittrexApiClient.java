package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetDepositAddressResponse;
import com.corycharlton.bittrexapi.response.GetDepositHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetOrderHistoryResponse;
import com.corycharlton.bittrexapi.response.GetOrderResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;
import com.corycharlton.bittrexapi.response.GetWithdrawalHistoryResponse;
import com.corycharlton.bittrexapi.response.PlaceBuyLimitOrderResponse;
import com.corycharlton.bittrexapi.response.PlaceSellLimitOrderResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.corycharlton.bittrexapi.internal.util.Ensure.*;

@SuppressWarnings("unused, WeakerAccess")
public class BittrexApiClient {
    //TODO: Add a logging interface

    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s

    static final String URL_CANCELORDER = "https://bittrex.com/api/v1.1/market/cancel";
    static final String URL_GETBALANCE = "https://bittrex.com/api/v1.1/account/getbalance";
    static final String URL_GETBALANCES = "https://bittrex.com/api/v1.1/account/getbalances";
    static final String URL_GETCURRENCIES = "https://bittrex.com/api/v1.1/public/getcurrencies";
    static final String URL_GETDEPOSITADDRESS = "https://bittrex.com/api/v1.1/account/getdepositaddress";
    static final String URL_GETDEPOSITHISTORY = "https://bittrex.com/api/v1.1/account/getdeposithistory";
    static final String URL_GETMARKETHISTORY = "https://bittrex.com/api/v1.1/public/getmarkethistory";
    static final String URL_GETMARKETS = "https://bittrex.com/api/v1.1/public/getmarkets";
    static final String URL_GETMARKETSUMMARIES = "https://bittrex.com/api/v1.1/public/getmarketsummaries";
    static final String URL_GETMARKETSUMMARY = "https://bittrex.com/api/v1.1/public/getmarketsummary";
    static final String URL_GETORDER = "https://bittrex.com/api/v1.1/account/getorder";
    static final String URL_GETORDERBOOK = "https://bittrex.com/api/v1.1/public/getorderbook";
    static final String URL_GETORDERHISTORY = "https://bittrex.com/api/v1.1/account/getorderhistory";
    static final String URL_GETTICKER = "https://bittrex.com/api/v1.1/public/getticker";
    static final String URL_GETWITHDRAWALHISTORY = "https://bittrex.com/api/v1.1/account/getwithdrawalhistory";
    static final String URL_PLACEBUYLIMITORDER = "https://bittrex.com/api/v1.1/market/buylimit";
    static final String URL_PLACESELLLIMITORDER = "https://bittrex.com/api/v1.1/market/selllimit";

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

        if (requiresAuthenticaton) {
            parameters.add(new NameValuePair("apikey", _key));
        }

        final long nonce = System.currentTimeMillis();
        parameters.add(new NameValuePair("_", Long.toString(nonce)));
        parameters.add(new NameValuePair("nonce", Long.toString(nonce)));

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

        final ArrayList<NameValuePair> requestHeaders = new ArrayList<>();
        final String requestUrl = urlStringBuilder.toString();

        if (requiresAuthenticaton) {
            requestHeaders.add(new NameValuePair("apisign", signUrl(requestUrl)));
        }

        return new Downloader.Request(requestUrl, requestHeaders);
    }

    public CancelOrderResponse cancelOrder(@NonNull UUID uuid) throws IOException {
        Ensure.isNotNull("uuid", uuid);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("uuid", uuid.toString()));

        final Downloader.Request request = buildRequest(URL_CANCELORDER, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, CancelOrderResponse.class);
    }

    public GetBalanceResponse getBalance(@NonNull String currency) throws IOException {
        Ensure.isNotNullOrWhitespace("currency", currency);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("currency", currency));

        final Downloader.Request request = buildRequest(URL_GETBALANCE, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetBalanceResponse.class);
    }

    public GetBalancesResponse getBalances() throws IOException {
        final Downloader.Request request = buildRequest(URL_GETBALANCES, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetBalancesResponse.class);
    }

    public GetCurrenciesResponse getCurrencies() throws IOException {
        final Downloader.Request request = buildRequest(URL_GETCURRENCIES);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetCurrenciesResponse.class);
    }

    public GetDepositAddressResponse getDepositAddress(@NonNull String currency) throws IOException {
        Ensure.isNotNullOrWhitespace("currency", currency);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("currency", currency));

        final Downloader.Request request = buildRequest(URL_GETDEPOSITADDRESS, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetDepositAddressResponse.class);
    }

    public GetDepositHistoryResponse getDepositHistory() throws IOException {
        return getDepositHistory(null);
    }

    public GetDepositHistoryResponse getDepositHistory(String currency) throws IOException {
        final ArrayList<NameValuePair> parameters = new ArrayList<>();

        if (!StringUtils.isNullOrWhiteSpace(currency)) {
            parameters.add(new NameValuePair("currency", currency));
        }

        final Downloader.Request request = buildRequest(URL_GETDEPOSITHISTORY, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetDepositHistoryResponse.class);
    }

    public GetMarketHistoryResponse getMarketHistory(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest(URL_GETMARKETHISTORY, parameters);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetMarketHistoryResponse.class);
    }

    public GetMarketsResponse getMarkets() throws IOException {
        final Downloader.Request request = buildRequest(URL_GETMARKETS);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetMarketsResponse.class);
    }

    public GetMarketSummariesResponse getMarketSummaries() throws IOException {
        final Downloader.Request request = buildRequest(URL_GETMARKETSUMMARIES);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetMarketSummariesResponse.class);
    }

    public GetMarketSummaryResponse getMarketSummary(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest(URL_GETMARKETSUMMARY, parameters);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetMarketSummaryResponse.class);
    }

    public GetOrderResponse getOrder(@NonNull UUID uuid) throws IOException {
        Ensure.isNotNull("uuid", uuid);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("uuid", uuid.toString()));

        final Downloader.Request request = buildRequest(URL_GETORDER, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetOrderResponse.class);
    }

    // TODO: Expose the 'type' parameter?
    public GetOrderBookResponse getOrderBook(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));
        parameters.add(new NameValuePair("type", "both"));

        final Downloader.Request request = buildRequest(URL_GETORDERBOOK, parameters);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetOrderBookResponse.class);
    }

    public GetOrderHistoryResponse getOrderHistory() throws IOException {
        return getOrderHistory(null);
    }

    public GetOrderHistoryResponse getOrderHistory(String market) throws IOException {
        final ArrayList<NameValuePair> parameters = new ArrayList<>();

        if (!StringUtils.isNullOrWhiteSpace(market)) {
            parameters.add(new NameValuePair("market", market));
        }

        final Downloader.Request request = buildRequest(URL_GETORDERHISTORY, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetOrderHistoryResponse.class);
    }

    public GetTickerResponse getTicker(@NonNull String market) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));

        final Downloader.Request request = buildRequest(URL_GETTICKER, parameters);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetTickerResponse.class);
    }

    public GetWithdrawalHistoryResponse getWithdrawalHistory() throws IOException {
        return getWithdrawalHistory(null);
    }

    public GetWithdrawalHistoryResponse getWithdrawalHistory(String currency) throws IOException {
        final ArrayList<NameValuePair> parameters = new ArrayList<>();

        if (!StringUtils.isNullOrWhiteSpace(currency)) {
            parameters.add(new NameValuePair("currency", currency));
        }

        final Downloader.Request request = buildRequest(URL_GETWITHDRAWALHISTORY, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, GetWithdrawalHistoryResponse.class);
    }

    public PlaceBuyLimitOrderResponse placeBuyLimitOrder(@NonNull String market, double quantity, double rate) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);
        Ensure.isTrue("quantity", quantity > 0.0);
        Ensure.isTrue("rate", rate > 0.0);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));
        parameters.add(new NameValuePair("quantity", Double.toString(quantity)));
        parameters.add(new NameValuePair("rate", Double.toString(rate)));

        final Downloader.Request request = buildRequest(URL_PLACEBUYLIMITORDER, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, PlaceBuyLimitOrderResponse.class);
    }

    /*
    market 	required 	a string literal for the market (ex: BTC-LTC)
    quantity 	required 	the amount to purchase
    rate 	required 	the rate at which to place the order
     */
    public PlaceSellLimitOrderResponse placeSellLimitOrder(@NonNull String market, double quantity, double rate) throws IOException {
        Ensure.isNotNullOrWhitespace("market", market);
        Ensure.isTrue("quantity", quantity > 0.0);
        Ensure.isTrue("rate", rate > 0.0);

        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new NameValuePair("market", market));
        parameters.add(new NameValuePair("quantity", Double.toString(quantity)));
        parameters.add(new NameValuePair("rate", Double.toString(rate)));

        final Downloader.Request request = buildRequest(URL_PLACESELLLIMITORDER, parameters, true);
        final String response = _downloader.execute(request).bodyString();

        return Gson.fromJson(response, PlaceSellLimitOrderResponse.class);
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

        @NonNull
        public BittrexApiClient build() {
            if (downloader == null) {
                this.downloader = new UrlConnectionDownloader();
            }

            return new BittrexApiClient(this);
        }

        @NonNull
        public Builder downloader(@NonNull Downloader downloader) {
            isNotNull("downloader", downloader, "Downloader must not be null.");
            isValidState("downloader", this.downloader == null, "Downloader already set.");

            this.downloader = downloader;

            return this;
        }
    }
}
