package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.MarketHistory;
import com.corycharlton.bittrexapi.data.MarketSummary;

import java.util.ArrayList;
import java.util.List;

public class GetMarketHistoryResponse extends Response<ArrayList<MarketHistory>> {
    private GetMarketHistoryResponse() {} // Cannot be instantiated
}
