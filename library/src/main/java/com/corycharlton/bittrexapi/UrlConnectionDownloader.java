package com.corycharlton.bittrexapi;

import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionDownloader implements Downloader {

    @Override public Response load(@NonNull Uri uri) throws IOException {

        HttpURLConnection connection = openConnection(uri);
        connection.setUseCaches(false);

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

    private HttpURLConnection openConnection(Uri path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(path.toString()).openConnection();
        connection.setConnectTimeout(BittrexApiClient.DEFAULT_CONNECT_TIMEOUT_MILLIS);
        connection.setReadTimeout(BittrexApiClient.DEFAULT_READ_TIMEOUT_MILLIS);
        return connection;
    }

}
