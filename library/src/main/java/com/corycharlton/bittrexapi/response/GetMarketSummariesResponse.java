package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.MarketSummary;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetMarketSummariesResponse extends Response<ArrayList<MarketSummary>> {
    private GetMarketSummariesResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<MarketSummary> result() {
        return super.result();
    }
}
