package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for making network calls to the Bittrex Api
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public interface Downloader {

    /**
     * Executes the request
     * @param request The api request being executed
     * @return A {@link Response} with the results of the request
     * @throws IOException If there is a network error
     */
    Response execute(@NonNull Request request) throws IOException;

    /**
     * Represents a HTTP request
     */
    final class Request {
        private final ArrayList<NameValuePair> _headers;
        private final String _url;

        /**
         * Construct a new {@link Request}
         * @param url The url for the request
         * @param headers A list of HTTP headers associated with the request
         */
        Request(@NonNull String url, ArrayList<NameValuePair> headers) {
            Ensure.isNotNullOrWhitespace("url", url);

            _headers = headers != null ? headers : new ArrayList<NameValuePair>();
            _url = url;
        }

        /**
         * A list of HTTP headers associated with the request
         * @return A list of HTTP headers associated with the request
         */
        @NonNull
        public ArrayList<NameValuePair> headers() {
            return _headers;
        }

        /**
         * The url for the request
         * @return The url for the request
         */
        @NonNull
        public String url() {
            return _url;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder("Url: " + _url);

            for (NameValuePair header : _headers) {
                if (header != null && !StringUtils.isNullOrWhiteSpace(header.name())) {
                    stringBuilder.append(" - ");
                    stringBuilder.append(header.name());

                    if (!StringUtils.isNullOrWhiteSpace(header.value())) {
                        stringBuilder.append(" = ");
                        stringBuilder.append(header.value());
                    }
                }
            }

            return stringBuilder.toString();
        }
    }

    /**
     * Represents a HTTP response
     */
    final class Response {
        private final String _bodyString;
        private final int _responseCode;

        /**
         * Construct a new {@link Response}
         * @param bodyString The contents of the response
         * @param responseCode The HTTP status code of the response
         */
        public Response(@NonNull String bodyString, int responseCode) {
            Ensure.isNotNull("bodyString", bodyString);

            _bodyString = bodyString;
            _responseCode = responseCode;
        }

        /**
         * The contents of the response
         * @return The contents of the response
         */
        public String bodyString() {
            return _bodyString;
        }

        /**
         * The HTTP status code of the response
         * @return The HTTP status code of the response
         */
        public int responseCode() {
            return _responseCode;
        }

        @Override
        public String toString() {
            return responseCode() + ": " + bodyString();
        }
    }

    /**
     * An {@link IOException} that includes the HTTP status code
     */
    class ResponseException extends IOException {
        final int responseCode;

        public ResponseException(String message, int responseCode) {
            super(message);
            this.responseCode = responseCode;
        }
    }
}
