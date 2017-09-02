package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.MarketHistory;
import com.corycharlton.bittrexapi.data.MarketSummary;

import java.util.List;

public class GetMarketHistoryResponse extends Response<List<MarketHistory>> {
    private GetMarketHistoryResponse() {} // Cannot be instantiated
}
