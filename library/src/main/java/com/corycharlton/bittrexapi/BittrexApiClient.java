package com.corycharlton.bittrexapi;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.constants.HttpHeader;
import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.response.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNull;
import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;
import static com.corycharlton.bittrexapi.internal.util.Ensure.isValidState;

/**
 * An implementation of the Bittrex Api
 */
@SuppressWarnings("unused, WeakerAccess")
public class BittrexApiClient {
    //TODO: Add a logging interface

    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000; // 20s
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s

    private final Downloader _downloader;
    private final Executor _executor;
    private final String _key;
    private final String _secret;

    private BittrexApiClient(@NonNull Builder builder) {
        _downloader = builder.downloader;
        _executor = builder.executor;
        _key = builder.key;
        _secret = builder.secret;
    }

    private Downloader.Request buildDownloaderRequest(@NonNull Request request) {
        final ArrayList<NameValuePair> parameters = new ArrayList<>();
        final boolean requiresAuthentication = request.requiresAuthentication();
        final String url = request.url();

        @SuppressWarnings("unchecked") final List<NameValuePair> requestParameters = request.parameters();

        for (int i = 0; i < requestParameters.size(); i++) {
            parameters.add(requestParameters.get(i));
        }

        boolean firstParameterAdded = url.contains("?");
        final StringBuilder urlStringBuilder = new StringBuilder(url);

        if (requiresAuthentication) {
            Ensure.isValidState("authentication", !StringUtils.isNullOrWhiteSpace(_key) && !StringUtils.isNullOrWhiteSpace(_secret), "An api key and secret are required for authenticated calls");

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

        if (requiresAuthentication) {
            requestHeaders.add(new NameValuePair(HttpHeader.ApiSign, signUrl(requestUrl)));
        }

        return new Downloader.Request(requestUrl, requestHeaders);
    }

    /**
     * Executes a {@link Request}
     * @param request The {@link Request} to execute
     * @param <T> The {@link Response} returned by the request
     * @return A {@link Response} that contains the result of the request
     * @throws IOException If there is a network error
     */
    @WorkerThread
    public <T extends Response> T execute(@NonNull Request<T> request) throws IOException {
        isNotNull("request", request);

        final Downloader.Request downloaderRequest = buildDownloaderRequest(request);
        final String response = _downloader.execute(downloaderRequest).bodyString();

        return Gson.fromJson(response, request.responseType());
    }

    /**
     * Asynchronously executes a {@link Request}
     * @param request The {@link Request} to execute
     * @param callback A {@link Callback} that will be executed when the {@link Request} completes
     * @param <T> The {@link Response} returned by the request
     */
    @UiThread
    public <T extends Response> void executeAsync(@NonNull Request<T> request, Callback<T> callback) {
        isNotNull("request", request);

        new ExecuteAsyncTask<>(request, callback).executeOnExecutor(_executor);
    }

    private String signUrl(@NonNull String url) {
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
        private Executor executor;
        private String key;
        private String secret;

        /**
         * A builder used to validate and build a {@link BittrexApiClient}
         */
        public Builder() {
            // Empty constructor
        }

        /**
         * Generates the {@link BittrexApiClient}
         * @return A {@link BittrexApiClient}
         */
        @NonNull
        public BittrexApiClient build() {
            if (downloader == null) {
                downloader = new UrlConnectionDownloader();
            }

            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }

            return new BittrexApiClient(this);
        }

        /**
         * Sets the {@link Downloader} implementation used to execute api calls.
         * @param downloader The {@link Downloader} implementation used to execute api calls. A downloader must not already be set
         * @return This {@link Builder} instance for method chaining
         */
        @NonNull
        public Builder downloader(@NonNull Downloader downloader) {
            isNotNull("downloader", downloader, "Downloader must not be null.");
            isValidState("downloader", this.downloader == null, "Downloader already set.");

            this.downloader = downloader;

            return this;
        }

        /**
         * Sets the {@link Executor} used to execute asynchronous calls.
         * @param executor The {@link Executor} used to execute asynchronous calls
         * @return This {@link Builder} instance for method chaining
         */
        public Builder executor(@NonNull Executor executor) {
            isNotNull("executor", executor, "Executor must not be null.");
            isValidState("executor", this.executor == null, "Executor already set.");

            this.executor = executor;

            return this;
        }

        /**
         * Set the api key
         * @param key The Bittrex api key
         * @return This {@link Builder} instance for method chaining
         */
        @NonNull
        public Builder key(@NonNull String key) {
            isNotNullOrWhitespace("key", key);

            this.key = key;

            return this;
        }

        /**
         * Set the api secret
         * @param secret The Bittrex api secret
         * @return This {@link Builder} instance for method chaining
         */
        @NonNull
        public Builder secret(@NonNull String secret) {
            isNotNullOrWhitespace("secret", secret);

            this.secret = secret;

            return this;
        }
    }

    private final class ExecuteAsyncTask<T extends Response> extends AsyncTask<Void, Void, T> {

        private IOException _backgroundException;
        private final Callback<T> _callback;
        private final Request<T> _request;

        public ExecuteAsyncTask(@NonNull Request<T> request, Callback<T> callback) {
            _callback = callback;
            _request = request;
        }

        @Override
        protected T doInBackground(Void... voids) {
            // TODO: Do I want to catch all exceptions and pass them to the callback?
            try {
                return BittrexApiClient.this.execute(_request);
            } catch (IOException e) {
                _backgroundException = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(T result) {
            super.onPostExecute(result);

            if (_callback != null) {
                if (_backgroundException != null) {
                    _callback.onFailure(_request, _backgroundException);
                } else {
                    _callback.onResponse(_request, result);
                }
            }
        }
    }
}
