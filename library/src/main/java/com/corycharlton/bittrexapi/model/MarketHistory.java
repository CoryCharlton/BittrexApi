package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class MarketHistory {

    @SerializedName("FillType") private String _fillType;
    @SerializedName("Id") private long _id;
    @SerializedName("OrderType") private String _orderType;
    @SerializedName("Price") private double _price;
    @SerializedName("TimeStamp") private Date _timeStamp;
    @SerializedName("Total") private double _total;
    @SerializedName("Quantity") private double _quantity;

    private MarketHistory() {} // Cannot be instantiated

    public String fillType() {
        return _fillType;
    }

    public long id() {
        return _id;
    }

    public String orderType() {
        return _orderType;
    }

    public double price() {
        return _price;
    }

    public Date timeStamp() {
        return _timeStamp;
    }

    public double total() {
        return _total;
    }

    public double quantity() {
        return _quantity;
    }
}
