package com.corycharlton.bittrexapi.request;


import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.Downloader;
import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Request<T extends Response> {

    private final ArrayList<NameValuePair> _parameters = new ArrayList<>();
    private final boolean _requiresAuthentication;
    private final Class<T> _responseType;
    private final String _url;

    protected Request(@NonNull String url, boolean requiresAuthentication, @NonNull Class<T> responseType) {
        Ensure.isNotNull("responseType", responseType);
        Ensure.isNotNullOrWhitespace("url", url);

        _requiresAuthentication = requiresAuthentication;
        _responseType = responseType;
        _url = url;
    }

    protected void addParameter(@NonNull String name, double value) {
        addParameter(name, Double.toString(value));
    }

    protected void addParameter(@NonNull String name, @NonNull String value) {
        Ensure.isNotNullOrWhitespace("name", name);
        Ensure.isNotNullOrWhitespace("value", value);

        _parameters.add(new NameValuePair(name, value));
    }

    protected void addParameter(@NonNull String name, @NonNull UUID value) {
        addParameter(name, value.toString());
    }

    public List<NameValuePair> parameters() {
        return Collections.unmodifiableList(_parameters);
    }

    public boolean requiresAuthentication() {
        return _requiresAuthentication;
    }

    public Class<T> responseType() {
        return _responseType;
    }

    /**
     * @return The url for the request
     */
    public String url() {
        return _url;
    }

}
