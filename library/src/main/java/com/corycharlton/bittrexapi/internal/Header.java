package com.corycharlton.bittrexapi.internal;

public class Header {
    private final String _name;
    private final String _value;

    public Header(String name, String value) {
        _name = name;
        _value = value;
    }

    public String name() {
        return _name;
    }

    public String value() {
        return _value;
    }
}
