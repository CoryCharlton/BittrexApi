package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class Deposit {

    @SerializedName("Amount") private double _amount;
    @SerializedName("Confirmations") private long _confirmations;
    @SerializedName("CryptoAddress") private String _cryptoAddress;
    @SerializedName("Currency") private String _currency;
    @SerializedName("Id") private long _id;
    @SerializedName("LastUpdated") private Date _lastUpdated;
    @SerializedName("TxId") private String _txId;

    private Deposit() {} // Cannot be instantiated

    public double amount() {
        return _amount;
    }

    public long confirmations() {
        return _confirmations;
    }

    public String cryptoAddress() {
        return _cryptoAddress;
    }

    public String currency() {
        return _currency;
    }

    public long id() {
        return _id;
    }

    public Date lastUpdated() {
        return _lastUpdated;
    }

    public String txId() {
        return _txId;
    }
}
