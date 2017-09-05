package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.MarketHistory;

import java.util.ArrayList;

public class GetMarketHistoryResponse extends Response<ArrayList<MarketHistory>> {
    private GetMarketHistoryResponse() {} // Cannot be instantiated
}
