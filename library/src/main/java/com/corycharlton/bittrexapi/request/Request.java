package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.NameValuePair;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.response.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Represents an api request
 * @param <T> The {@link Response} type
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Request<T extends Response> {

    private final ArrayList<NameValuePair> _parameters = new ArrayList<>();
    private final boolean _requiresAuthentication;
    private final Class<T> _responseType;
    private final String _url;

    /**
     * Creates a new {@link Request}
     * @param url The url for the request
     * @param requiresAuthentication true if the request requires authentication; false if not
     * @param responseType The {@link Response} type associated with this request
     */
    protected Request(@NonNull String url, boolean requiresAuthentication, @NonNull Class<T> responseType) {
        Ensure.isNotNull("responseType", responseType);
        Ensure.isNotNullOrWhitespace("url", url);

        _requiresAuthentication = requiresAuthentication;
        _responseType = responseType;
        _url = url;
    }

    /**
     * Add a parameter to the request.
     * @param name The name of the parameter
     * @param value The value of the parameter
     */
    protected void addParameter(@NonNull String name, double value) {
        addParameter(name, Double.toString(value));
    }

    /**
     * Add a parameter to the request.
     * @param name The name of the parameter
     * @param value The value of the parameter
     */
    protected void addParameter(@NonNull String name, @NonNull String value) {
        Ensure.isNotNullOrWhitespace("name", name);
        Ensure.isNotNullOrWhitespace("value", value);

        _parameters.add(new NameValuePair(name, value));
    }

    /**
     * Add a parameter to the request.
     * @param name The name of the parameter
     * @param value The value of the parameter
     */
    protected void addParameter(@NonNull String name, @NonNull UUID value) {
        addParameter(name, value.toString());
    }

    /**
     * The list of parameters associated with the {@link Request}
     * @return The list of parameters associated with the {@link Request}
     */
    public List<NameValuePair> parameters() {
        return Collections.unmodifiableList(_parameters);
    }

    /**
     * @return true if the request requires authentication; false if not
     */
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
