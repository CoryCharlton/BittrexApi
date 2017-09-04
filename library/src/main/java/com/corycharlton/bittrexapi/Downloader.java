package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public interface Downloader {
    Response execute(@NonNull Request request) throws IOException;

    final class Request {
        private final ArrayList<NameValuePair> _headers;
        private final String _url;

        /*
        Request(@NonNull String url) {
            this(url, null);
        }
        */

        Request(@NonNull String url, ArrayList<NameValuePair> headers) {
            Ensure.isNotNullOrWhitespace("url", url);

            _headers = headers != null ? headers : new ArrayList<NameValuePair>();
            _url = url;
        }

        @NonNull
        public ArrayList<NameValuePair> headers() {
            return _headers;
        }

        @NonNull
        public String url() {
            return _url;
        }

        @Override
        public String toString() {
            // TODO: Implement for convenience
            return super.toString();
            //return responseCode() + ": " + bodyString();
        }
    }

    final class Response {
        private final String _bodyString;
        private final int _responseCode;

        public Response(@NonNull String bodyString, int responseCode) {
            Ensure.isNotNull("bodyString", bodyString);

            _bodyString = bodyString;
            _responseCode = responseCode;
        }

        public String bodyString() {
            return _bodyString;
        }

        public int responseCode() {
            return _responseCode;
        }

        @Override
        public String toString() {
            return responseCode() + ": " + bodyString();
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
