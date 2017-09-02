package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.Header;

import java.io.IOException;

public interface Downloader {
    Response load(@NonNull Uri uri, Header apisign) throws IOException;

    class ResponseException extends IOException {
        final int responseCode;

        public ResponseException(String message, int responseCode) {
            super(message);
            this.responseCode = responseCode;
        }
    }

    final class Response {
        private final int _responseCode;
        private final String _responseString;

        Response(@NonNull String responseString, int responseCode) {
            Ensure.isNotNull("responseString", responseString);

            _responseCode = responseCode;
            _responseString = responseString;
        }

        public int getResponseCode() {
            return _responseCode;
        }

        public String getResponseString() {
            return _responseString;
        }
    }
}
