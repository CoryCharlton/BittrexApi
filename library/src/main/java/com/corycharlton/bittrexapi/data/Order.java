package com.corycharlton.bittrexapi.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Order {

    // TODO: Calculate total cost here?

    @SerializedName("Quantity") private double _quantity;
    @SerializedName("Rate") private double _rate;

    private Order() {} // Cannot be instantiated

    public double quantity() {
        return _quantity;
    }

    public double rate() {
        return _rate;
    }
}
