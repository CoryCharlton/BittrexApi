package com.corycharlton.bittrexapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class OrderBook {
    
    @SerializedName("buy") private List<Order> _buys;
    @SerializedName("sell") private List<Order> _sells;

    private OrderBook() {} // Cannot be instantiated

    public List<Order> buys() {
        return _buys;
    }

    public List<Order> sells() {
        return _sells;
    }
}
