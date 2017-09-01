package com.corycharlton.bittrexapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Market {
    @SerializedName("MarketCurrency") String marketCurrency;
    @SerializedName("BaseCurrency") String baseCurrency;
    @SerializedName("MarketCurrencyLong") String marketCurrencyLong;
    @SerializedName("BaseCurrencyLong") String baseCurrencyLong;
    @SerializedName("MinTradeSize") double minTradeSize;
    @SerializedName("MarketName") String marketName;
    @SerializedName("IsActive") boolean isActive;
    @SerializedName("Created") Date created;
}
