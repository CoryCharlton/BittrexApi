package com.corycharlton.bittrexapi.internal;

import android.support.annotation.NonNull;

public class NameValuePair {
    private final String _name;
    private final String _value;

    public NameValuePair(@NonNull String name, @NonNull String value) {
        _name = name.trim();
        _value = value.trim();
    }

    public String name() {
        return _name;
    }

    public String value() {
        return _value;
    }
}
