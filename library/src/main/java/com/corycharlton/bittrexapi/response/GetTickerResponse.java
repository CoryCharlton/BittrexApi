package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Ticker;

@SuppressWarnings("EmptyMethod")
public class GetTickerResponse extends Response<Ticker> {
    private GetTickerResponse() {} // Cannot be instantiated

    @Override
    public Ticker result() {
        return super.result();
    }
}
