package com.corycharlton.bittrexapi.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Balance {
    /*
    {
        "Currency": "BTC",
        "Balance": 0.01524815,
        "Available": 0.00111290,
        "Pending": 0.00000000,
        "CryptoAddress": "15wAyo1YUwgmEG192hxrF2w4fPzX5jyFXt"
    }
    */

    @SerializedName("Available") private double _available;
    @SerializedName("Balance") private double _balance;
    @SerializedName("CryptoAddress") private String _cryptoAddress;
    @SerializedName("Currency") private String _currency;
    @SerializedName("Pending") private double _pending;

    private Balance() {} // Cannot be instantiated

    public double get_available() {
        return _available;
    }

    public double balance() {
        return _balance;
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
}
