package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.MarketSummary;

import java.util.ArrayList;
import java.util.List;

public class GetMarketSummariesResponse extends Response<ArrayList<MarketSummary>> {
    private GetMarketSummariesResponse() {} // Cannot be instantiated
}
