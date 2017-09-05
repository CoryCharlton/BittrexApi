package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Ticker {

    @SerializedName("Ask") private double _ask;
    @SerializedName("Bid") private double _bid;
    @SerializedName("Last") private double _last;
    
    private Ticker() {} // Cannot be instantiated

    public double ask() {
        return _ask;
    }

    public double bid() {
        return _bid;
    }

    public double last() {
        return _last;
    }
}
