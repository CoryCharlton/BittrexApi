package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class OrderBookEntry {

    // TODO: Calculate total cost here?

    @SerializedName("Quantity") private double _quantity;
    @SerializedName("Rate") private double _rate;

    private OrderBookEntry() {} // Cannot be instantiated

    public double quantity() {
        return _quantity;
    }

    public double rate() {
        return _rate;
    }
}
