package com.corycharlton.bittrexapi.response;


import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"WeakerAccess", "unused"})
abstract class Response<T> {
    @SerializedName("message") private String _message;
    @SerializedName("success") private boolean _success;
    @SerializedName("result") private T _result;

    protected Response() {

    }

    public boolean success() {
        return _success;
    }

    public String message() {
        return _message;
    }

    public T result() {
        return _result;
    }

    @Override
    public String toString() {
        return Gson.toJson(this);
    }
}
