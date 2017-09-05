package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class OrderBook {
    
    @SerializedName("buy") private List<OrderBookEntry> _buys;
    @SerializedName("sell") private List<OrderBookEntry> _sells;

    private OrderBook() {} // Cannot be instantiated

    public List<OrderBookEntry> buys() {
        return _buys;
    }

    public List<OrderBookEntry> sells() {
        return _sells;
    }
}
