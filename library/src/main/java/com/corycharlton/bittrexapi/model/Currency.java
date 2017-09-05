package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Currency {

    @SerializedName("BaseAddress") private String _baseAddress;
    @SerializedName("CoinType") private String _coinType;
    @SerializedName("Currency") private String _currency;
    @SerializedName("CurrencyLong") private String _currencyLong;
    @SerializedName("IsActive") private boolean _isActive;
    // Is this an int?
    @SerializedName("MinConfirmation") private double _minConfirmation;
    // Not sure what this type is
    @SerializedName("Notice") private String _notice;
    @SerializedName("TxFee") private double _txFee;

    private Currency() {} // Cannot be instantiated

    public String baseAddress() {
        return _baseAddress;
    }

    public String coinType() {
        return _coinType;
    }

    public String currency() {
        return _currency;
    }

    public String currencyLong() {
        return _currencyLong;
    }

    public boolean isActive() {
        return _isActive;
    }

    public double minConfirmation() {
        return _minConfirmation;
    }

    public String notice() {
        return _notice;
    }

    public double txFee() {
        return _txFee;
    }
}
