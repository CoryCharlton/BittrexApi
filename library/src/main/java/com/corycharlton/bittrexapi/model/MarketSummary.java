package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class MarketSummary {

    @SerializedName("Ask") private double _ask;
    @SerializedName("BaseVolume") private double _baseVolume;
    @SerializedName("Bid") private double _bid;
    @SerializedName("Created") private Date _created;
    @SerializedName("High") private double _high;
    @SerializedName("Last") private double _last;
    @SerializedName("Low") private double _low;
    @SerializedName("MarketName") private String _marketName;
    @SerializedName("OpenBuyOrders") private long _openBuyOrders;
    @SerializedName("OpenSellOrders") private long _openSellOrders;
    @SerializedName("PrevDay") private double _prevDay;
    @SerializedName("TimeStamp") private Date _timeStamp;
    @SerializedName("Volume") private double _volume;

    private MarketSummary() {} // Cannot be instantiated

    public double ask() {
        return _ask;
    }

    public double baseVolume() {
        return _baseVolume;
    }

    public double bid() {
        return _bid;
    }

    public Date created() {
        return _created;
    }

    public double high() {
        return _high;
    }

    public double last() {
        return _last;
    }

    public double low() {
        return _low;
    }

    public String marketName() {
        return _marketName;
    }

    public long openBuyOrders() {
        return _openBuyOrders;
    }

    public long openSellOrders() {
        return _openSellOrders;
    }

    public double prevDay() {
        return _prevDay;
    }

    public Date timeStamp() {
        return _timeStamp;
    }

    public double volume() {
        return _volume;
    }
}
