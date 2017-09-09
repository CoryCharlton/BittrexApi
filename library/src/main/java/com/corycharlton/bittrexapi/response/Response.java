package com.corycharlton.bittrexapi.response;


import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Represents an api response
 * @param <T> The type of the {@link Response#result()}
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Response<T> {
    @SerializedName("message") private String _message;
    @SerializedName("success") private boolean _success;
    @SerializedName("result") private T _result;

    protected Response() {

    }

    /**
     * @return true if the api call was successful; false if not
     */
    public boolean success() {
        return _success;
    }

    /**
     * @return A message from the api call
     */
    public String message() {
        return _message;
    }

    /**
     * @return The result from the api call
     */
    public T result() {
        return _result;
    }

    @Override
    public String toString() {
        return Gson.toJson(this);
    }
}
