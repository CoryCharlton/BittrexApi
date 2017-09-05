package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.HttpHeader;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.internal.NameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A {@link Downloader} implementation that uses a {@link HttpURLConnection} to execute requests
 */
public class UrlConnectionDownloader implements Downloader {

    @Override
    public Response execute(@NonNull Request request) throws IOException {
        Ensure.isNotNull("request", request);

        final HttpURLConnection connection = openConnection(Uri.parse(request.url()));
        connection.setUseCaches(false);

        connection.addRequestProperty(HttpHeader.Accept, "application/json");
        connection.addRequestProperty(HttpHeader.CacheControl, "no-cache, no-store, must-revalidate");

        for (NameValuePair header : request.headers()) {
            connection.addRequestProperty(header.name(), header.value());
        }

        int responseCode = connection.getResponseCode();
        if (responseCode < 200 || responseCode >= 300) {
            connection.disconnect();
            throw new ResponseException(responseCode + " " + connection.getResponseMessage(), responseCode);
        }

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        final StringBuilder stringBuilder = new StringBuilder();

        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(currentLine);
        }

        return new Response(stringBuilder.toString(), responseCode);
    }

    @NonNull
    private HttpURLConnection openConnection(Uri path) throws IOException {
        final HttpURLConnection connection = (HttpURLConnection) new URL(path.toString()).openConnection();

        connection.setConnectTimeout(BittrexApiClient.DEFAULT_CONNECT_TIMEOUT_MILLIS);
        connection.setReadTimeout(BittrexApiClient.DEFAULT_READ_TIMEOUT_MILLIS);

        return connection;
    }

}
