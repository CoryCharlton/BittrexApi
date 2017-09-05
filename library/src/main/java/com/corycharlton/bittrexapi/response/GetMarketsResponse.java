package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Market;

import java.util.ArrayList;

public class GetMarketsResponse extends Response<ArrayList<Market>> {
    private GetMarketsResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<Market> result() {
        return super.result();
    }
}
