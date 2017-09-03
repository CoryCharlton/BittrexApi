package com.corycharlton.bittrexapi.response;


import com.corycharlton.bittrexapi.internal.gson.Gson;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"WeakerAccess", "unused"})
abstract class Response<T> {
    @SerializedName("success") private boolean _isSuccess;
    @SerializedName("message") private String _message;
    @SerializedName("result") private T _result;

    protected Response() {

    }

    public boolean isSuccess() {
        return _isSuccess;
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
