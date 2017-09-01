package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.data.Market;
import com.corycharlton.bittrexapi.internal.gson.GsonFactory;
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

    public Response<List<Market>> getMarkets() throws IOException {
        return GsonFactory.getGson().fromJson(downloader.load(Uri.parse("https://bittrex.com/api/v1.1/public/getmarkets")).getResponseString(), new TypeToken<Response<List<Market>>>(){}.getType());
    }

    public static final class Builder {

        private Downloader downloader;
        private String key;
        private String secret;

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
