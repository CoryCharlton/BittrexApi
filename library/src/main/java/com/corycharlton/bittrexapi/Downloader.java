package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;

public interface Downloader {
    Response execute(@NonNull Request request) throws IOException;

    final class Request {
        private final ArrayList<NameValuePair> _headers;
        private final Uri _uri;

        Request(@NonNull Uri uri) {
            this(uri, null);
        }

        Request(@NonNull Uri uri, ArrayList<NameValuePair> headers) {
            Ensure.isNotNull("uri", uri);

            _headers = headers != null ? headers : new ArrayList<NameValuePair>();
            _uri = uri;
        }

        @NonNull
        public ArrayList<NameValuePair> headers() {
            return _headers;
        }

        @NonNull
        public Uri uri() {
            return _uri;
        }
    }

    final class Response {
        private final int _responseCode;
        private final String _responseString;

        public Response(@NonNull String responseString, int responseCode) {
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

    class ResponseException extends IOException {
        final int responseCode;

        public ResponseException(String message, int responseCode) {
            super(message);
            this.responseCode = responseCode;
        }
    }
}
