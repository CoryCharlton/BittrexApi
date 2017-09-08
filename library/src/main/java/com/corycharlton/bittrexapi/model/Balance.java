package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Balance {

    @SerializedName("Available") private double _available;
    @SerializedName("Balance") private double _total;
    @SerializedName("CryptoAddress") private String _cryptoAddress;
    @SerializedName("Currency") private String _currency;
    @SerializedName("Pending") private double _pending;

    private Balance() {} // Cannot be instantiated

    public double available() {
        return _available;
    }

    public String cryptoAddress() {
        return _cryptoAddress;
    }

    public String currency() {
        return _currency;
    }

    public double pending() {
        return _pending;
    }

    public double reserved() {
        return _total - _available;
    }

    public double total() {
        return _total;
    }
}
