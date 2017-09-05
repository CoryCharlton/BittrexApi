package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DepositAddress {

    @SerializedName("Address") private String _address;
    @SerializedName("Currency") private String _currency;

    private DepositAddress() {} // Cannot be instantiated

    public String address() {
        return _address;
    }

    public String currency() {
        return _currency;
    }
}
