package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class Market {
    @SerializedName("BaseCurrency") private String _baseCurrency;
    @SerializedName("BaseCurrencyLong") private String _baseCurrencyLong;
    @SerializedName("Created") private Date _created;
    @SerializedName("IsActive") private boolean _isActive;
    @SerializedName("IsSponsored") private boolean _isSponsored;
    @SerializedName("LogoUrl") private String _logoUrl;
    @SerializedName("MarketCurrency") private String _marketCurrency;
    @SerializedName("MarketCurrencyLong") private String _marketCurrencyLong;
    @SerializedName("MarketName") private String _marketName;
    @SerializedName("MinTradeSize") private double _minTradeSize;
    @SerializedName("Notice") private String _notice;

    private Market() {} // Cannot be instantiated

    public String baseCurrency() {
        return _baseCurrency;
    }

    public String  baseCurrencyLong() {
        return _baseCurrencyLong;
    }

    public Date created() {
        return _created;
    }

    public boolean isActive() {
        return _isActive;
    }

    public boolean isSponsored() {
        return _isSponsored;
    }

    public String logoUrl() {
        return _logoUrl;
    }

    public String marketCurrency() {
        return _marketCurrency;
    }

    public String marketCurrencyLong() {
        return _marketCurrencyLong;
    }

    public String marketName() {
        return _marketName;
    }

    public double minTradeSize() {
        return _minTradeSize;
    }

    public String notice() {
        return _notice;
    }
}
