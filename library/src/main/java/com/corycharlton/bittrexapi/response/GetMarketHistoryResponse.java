package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.MarketHistory;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetMarketHistoryResponse extends Response<ArrayList<MarketHistory>> {
    private GetMarketHistoryResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<MarketHistory> result() {
        return super.result();
    }
}
